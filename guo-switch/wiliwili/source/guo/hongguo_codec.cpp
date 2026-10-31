#include "guo/hongguo_codec.hpp"

#include <algorithm>
#include <cstring>
#include <stdexcept>

#include "guo/hongguo_crypto.hpp"

namespace guo {
namespace {

uint32_t readU32(const uint8_t* p) {
    return (static_cast<uint32_t>(p[0]) << 24) | (static_cast<uint32_t>(p[1]) << 16) |
           (static_cast<uint32_t>(p[2]) << 8) | p[3];
}

uint64_t readU64(const uint8_t* p) {
    return (static_cast<uint64_t>(readU32(p)) << 32) | readU32(p + 4);
}

// JS readBox over a bounded buffer; returns false for invalid boxes.
bool readBox(const uint8_t* data, size_t length, int64_t offset, BoxInfo& out) {
    if (offset < 0 || static_cast<uint64_t>(offset) + 8 > length) return false;
    uint64_t size = readU32(data + offset);
    uint32_t header = 8;
    if (size == 1) {
        if (static_cast<uint64_t>(offset) + 16 > length) return false;
        size = readU64(data + offset + 8);
        header = 16;
    } else if (size == 0) {
        size = length - static_cast<uint64_t>(offset);
    }
    if (size < header || static_cast<uint64_t>(offset) + size > length) return false;
    out.offset = static_cast<uint64_t>(offset);
    out.size = size;
    out.headerSize = header;
    std::memcpy(out.type, data + offset + 4, 4);
    out.type[4] = 0;
    return true;
}

// JS findBox: scan for the fourcc, then validate the box starting 4 bytes
// before it. `start` is a byte offset into the buffer.
bool findBox(const uint8_t* data, size_t length, const char type[4], size_t start, BoxInfo& out) {
    size_t position = std::max<size_t>(start, 0) + 4;
    while (position + 4 <= length) {
        const void* hit = memmem(data + position, length - position, type, 4);
        if (!hit) return false;
        position = static_cast<const uint8_t*>(hit) - data;
        if (readBox(data, length, static_cast<int64_t>(position) - 4, out) &&
            std::memcmp(out.type, type, 4) == 0) {
            return true;
        }
        position += 1;
    }
    return false;
}

bool boxBody(const uint8_t* data, size_t length, const char type[4], size_t start,
             const uint8_t*& body, size_t& bodyLength) {
    BoxInfo box;
    if (!findBox(data, length, type, start, box)) return false;
    body = data + box.bodyStart();
    bodyLength = box.size - box.headerSize;
    return true;
}

struct TrackInfo {
    std::vector<uint32_t> sizes;
    std::vector<uint64_t> offsets;
    std::vector<uint32_t> samplesPerChunk;
    uint64_t auxOffset = 0;
    std::vector<uint32_t> auxSizes;
    uint32_t sampleCount = 0;
};

bool parseTrack(const uint8_t* moov, size_t moovLength, const BoxInfo& track, TrackInfo& out) {
    BoxInfo stbl;
    if (!findBox(moov, moovLength, "stbl", static_cast<size_t>(track.bodyStart()), stbl)) return false;
    const uint8_t *stsz, *stsc, *saiz, *saio;
    size_t stszLen, stscLen, saizLen, saioLen;
    if (!boxBody(moov, moovLength, "stsz", static_cast<size_t>(stbl.bodyStart()), stsz, stszLen) ||
        !boxBody(moov, moovLength, "stsc", static_cast<size_t>(stbl.bodyStart()), stsc, stscLen) ||
        !boxBody(moov, moovLength, "saiz", static_cast<size_t>(stbl.bodyStart()), saiz, saizLen) ||
        !boxBody(moov, moovLength, "saio", static_cast<size_t>(stbl.bodyStart()), saio, saioLen)) {
        return false;
    }
    BoxInfo chunkBox;
    if (!findBox(moov, moovLength, "stco", static_cast<size_t>(stbl.bodyStart()), chunkBox) &&
        !findBox(moov, moovLength, "co64", static_cast<size_t>(stbl.bodyStart()), chunkBox)) {
        return false;
    }
    if (stszLen < 12 || stscLen < 8 || saizLen < 9 || saioLen < 8) return false;
    const uint32_t defaultSampleSize = readU32(stsz + 4);
    const uint32_t sampleCount = readU32(stsz + 8);
    std::vector<uint32_t> sizes;
    if (defaultSampleSize) {
        sizes.assign(sampleCount, defaultSampleSize);
    } else {
        for (uint32_t i = 0; i < sampleCount && 12 + (i + 1) * 4 <= stszLen; ++i)
            sizes.push_back(readU32(stsz + 12 + i * 4));
    }
    if (sizes.size() != sampleCount) return false;
    const bool is64 = std::memcmp(chunkBox.type, "co64", 4) == 0;
    const size_t entrySize = is64 ? 8 : 4;
    uint32_t chunkCount = 0;
    if (chunkBox.bodyStart() + 8 <= chunkBox.offset + chunkBox.size)
        chunkCount = readU32(moov + chunkBox.bodyStart() + 4);
    std::vector<uint64_t> offsets;
    for (uint32_t i = 0; i < chunkCount; ++i) {
        const uint64_t at = chunkBox.bodyStart() + 8 + i * entrySize;
        if (at + entrySize > chunkBox.offset + chunkBox.size) return false;
        offsets.push_back(is64 ? readU64(moov + at) : readU32(moov + at));
    }
    const uint32_t entryCount = readU32(stsc + 4);
    struct StscEntry { uint32_t firstChunk, samplesPerChunk; };
    std::vector<StscEntry> entries;
    for (uint32_t i = 0; i < entryCount; ++i) {
        const size_t at = 8 + i * 12;
        if (at + 8 > stscLen) return false;
        entries.push_back({readU32(stsc + at), readU32(stsc + at + 4)});
    }
    std::vector<uint32_t> samplesPerChunk(offsets.size(), 0);
    size_t entryIdx = 0;
    for (size_t i = 0; i < offsets.size(); ++i) {
        const uint32_t chunkNum = static_cast<uint32_t>(i) + 1;
        while (entryIdx + 1 < entries.size() && entries[entryIdx + 1].firstChunk <= chunkNum) ++entryIdx;
        samplesPerChunk[i] = entries.empty() ? 0 : entries[entryIdx].samplesPerChunk;
    }
    const uint32_t auxDefault = saiz[4];
    const uint32_t auxCount = readU32(saiz + 5);
    std::vector<uint32_t> auxSizes;
    if (auxDefault) {
        auxSizes.assign(auxCount, auxDefault);
    } else {
        for (uint32_t i = 0; i < auxCount && 9 + i < saizLen; ++i) auxSizes.push_back(saiz[9 + i]);
    }
    if (auxSizes.size() != auxCount) return false;
    const uint32_t saioVersion = saio[0];
    const size_t offsetSize = saioVersion == 1 ? 8 : 4;
    const uint32_t saioCount = readU32(saio + 4);
    if (!saioCount || 8 + offsetSize > saioLen) return false;
    const uint64_t auxOffset = offsetSize == 8 ? readU64(saio + 8) : readU32(saio + 8);
    out = TrackInfo{sizes, offsets, samplesPerChunk, auxOffset, auxSizes, sampleCount};
    return true;
}

std::vector<CencSample> buildSamples(const uint8_t* data, size_t length,
                                     const std::vector<TrackInfo>& tracks) {
    std::vector<CencSample> samples;
    for (const auto& track : tracks) {
        uint64_t auxTotal = 0;
        for (uint32_t size : track.auxSizes) auxTotal += std::max<uint32_t>(size, 8);
        if (track.auxOffset + auxTotal > length) throw std::runtime_error("MP4 auxiliary data out of range");
        const uint8_t* aux = data + track.auxOffset;
        size_t sampleIndex = 0;
        uint64_t auxPos = 0;
        for (size_t chunk = 0; chunk < track.offsets.size() && sampleIndex < track.sampleCount; ++chunk) {
            uint64_t offset = track.offsets[chunk];
            const uint32_t count = track.samplesPerChunk[chunk];
            for (uint32_t index = 0; index < count && sampleIndex < track.sampleCount; ++index) {
                const uint32_t size = track.sizes[sampleIndex];
                const uint32_t auxSize = std::max<uint32_t>(
                    sampleIndex < track.auxSizes.size() ? track.auxSizes[sampleIndex] : 8, 8);
                if (auxPos + auxSize > auxTotal) throw std::runtime_error("MP4 sample index out of range");
                CencSample sample;
                sample.start = offset;
                sample.end = offset + size;
                sample.iv.fill(0);
                std::memcpy(sample.iv.data(), aux + auxPos, std::min<size_t>(8, auxSize));
                samples.push_back(sample);
                offset += size;
                ++sampleIndex;
                auxPos += auxSize;
            }
        }
    }
    std::sort(samples.begin(), samples.end(),
              [](const CencSample& a, const CencSample& b) { return a.start < b.start; });
    return samples;
}

void replaceFourcc(std::vector<uint8_t>& data, const char from[4], const char to[4]) {
    size_t position = 0;
    while (position + 4 <= data.size()) {
        void* hit = memmem(data.data() + position, data.size() - position, from, 4);
        if (!hit) return;
        position = static_cast<uint8_t*>(hit) - data.data();
        std::memcpy(data.data() + position, to, 4);
        position += 4;
    }
}

void replaceSinf(std::vector<uint8_t>& data) {
    size_t position = 0;
    while (position + 4 <= data.size()) {
        void* hit = memmem(data.data() + position, data.size() - position, "sinf", 4);
        if (!hit) return;
        position = static_cast<uint8_t*>(hit) - data.data();
        if (position < 4) {
            position += 4;
            continue;
        }
        const uint32_t size = readU32(data.data() + position - 4);
        const size_t end = position - 4 + size;
        if (size >= 8 && size < 50000 && end <= data.size()) {
            std::memcpy(data.data() + position, "free", 4);
            std::fill(data.begin() + position + 4, data.begin() + end, 0);
            position = end;
        } else {
            position += 4;
        }
    }
}

// The adapter's repair step: prepareCencHeader's blanket encv->hvc1 is
// corrected per sample entry, using the declared original format (frma) or
// the presence of an avcC box inside the entry.
void repairVideoEntries(const uint8_t* raw, size_t rawLength, std::vector<uint8_t>& header) {
    size_t pos = 0;
    while (pos + 4 <= rawLength) {
        const void* hit = memmem(raw + pos, rawLength - pos, "encv", 4);
        if (!hit) return;
        pos = static_cast<const uint8_t*>(hit) - raw;
        if (pos >= 4) {
            const uint64_t end = pos - 4 + readU32(raw + pos - 4);
            if (end > pos + 8 && end <= rawLength) {
                const std::string span(reinterpret_cast<const char*>(raw + pos + 4),
                                       reinterpret_cast<const char*>(raw + std::min<uint64_t>(end, rawLength)));
                std::string declared;
                const size_t frma = span.find("frma");
                if (frma != std::string::npos && frma + 8 <= span.size()) declared = span.substr(frma + 4, 4);
                std::string format = "hvc1";
                if (declared == "avc1" || declared == "avc3" || declared == "hvc1" || declared == "hev1") {
                    format = declared;
                } else if (span.find("avcC") != std::string::npos) {
                    format = "avc1";
                }
                if (pos + 4 <= header.size()) std::memcpy(header.data() + pos, format.data(), 4);
            }
        }
        pos += 4;
    }
}

std::array<uint8_t, 16> addCounter(const std::array<uint8_t, 16>& iv, uint64_t blocks) {
    std::array<uint8_t, 16> out = iv;
    uint64_t carry = blocks;
    for (int i = 15; i >= 0 && carry; --i) {
        const uint64_t sum = static_cast<uint64_t>(out[i]) + (carry & 0xff);
        out[i] = static_cast<uint8_t>(sum);
        carry = (carry >> 8) + (sum >> 8);
    }
    return out;
}

size_t findSampleIndex(const std::vector<CencSample>& samples, uint64_t start) {
    size_t low = 0, high = samples.size(), ans = samples.size();
    while (low < high) {
        const size_t mid = (low + high) / 2;
        if (samples[mid].end > start) {
            ans = mid;
            high = mid;
        } else {
            low = mid + 1;
        }
    }
    return ans;
}

}  // namespace

bool readBoxAt(const uint8_t* data, size_t length, uint64_t offset, BoxInfo& out) {
    return readBox(data, length, static_cast<int64_t>(offset), out);
}

PreparedStream prepareCencHeader(const uint8_t* data, size_t length, const uint8_t key[16],
                                 uint64_t total) {
    const uint32_t ftypSize = length >= 4 ? readU32(data) : 0;
    BoxInfo moov;
    if (!readBox(data, length, ftypSize, moov) || std::memcmp(moov.type, "moov", 4) != 0) {
        throw std::runtime_error("invalid MP4 moov");
    }
    const uint8_t* moovBody = data + moov.bodyStart();
    const size_t moovBodyLength = moov.size - moov.headerSize;
    std::vector<TrackInfo> parsed;
    size_t cursor = 0;
    while (true) {
        BoxInfo track;
        if (!findBox(moovBody, moovBodyLength, "trak", cursor, track)) break;
        TrackInfo info;
        if (parseTrack(moovBody, moovBodyLength, track, info)) parsed.push_back(std::move(info));
        cursor = static_cast<size_t>(track.offset + track.size);
    }
    PreparedStream result;
    result.total = total;
    result.samples = buildSamples(data, length, parsed);
    if (result.samples.size() > 300000) throw std::runtime_error("视频索引过大");
    result.header.assign(data, data + moov.offset + moov.size);
    replaceFourcc(result.header, "encv", "hvc1");
    replaceFourcc(result.header, "enca", "mp4a");
    replaceSinf(result.header);
    repairVideoEntries(data, length, result.header);
    return result;
}

void decryptPartial(uint8_t* data, size_t length, uint64_t start, const uint8_t key[16],
                    const PreparedStream& prepared) {
    const uint64_t end = start + length;
    if (start < prepared.header.size()) {
        const uint64_t copyEnd = std::min<uint64_t>(end, prepared.header.size());
        std::memcpy(data, prepared.header.data() + start, copyEnd - start);
    }
    if (!key || prepared.samples.empty()) return;
    size_t index = findSampleIndex(prepared.samples, start);
    for (; index < prepared.samples.size(); ++index) {
        const auto& sample = prepared.samples[index];
        if (sample.start >= end) break;
        const uint64_t overlapStart = std::max(start, sample.start);
        const uint64_t overlapEnd = std::min(end, sample.end);
        if (overlapStart >= overlapEnd) continue;
        const uint64_t relative = overlapStart - sample.start;
        const uint64_t block = relative / 16;
        const uint64_t skip = relative % 16;
        const auto counter = addCounter(sample.iv, block);
        aes128CtrXor(key, counter.data(), skip, data + (overlapStart - start),
                     static_cast<size_t>(overlapEnd - overlapStart));
    }
}

}  // namespace guo
