#pragma once

#include <array>
#include <cstdint>
#include <vector>

namespace guo {

// CENC (ISO-BMFF) header preparation and partial decryption, a 1:1 port of
// hongguo.cjs prepareCencHeader/decryptPartial plus the adapter's
// repairVideoEntries step. The media files are classic (non-fragmented) MP4
// with the sample tables and CENC auxiliary data inside moov.

struct CencSample {
    uint64_t start = 0;
    uint64_t end = 0;
    std::array<uint8_t, 16> iv{};  // 8-byte IV from the aux record, zero padded
};

struct PreparedStream {
    uint64_t total = 0;
    // Patched ftyp+moov (encv -> real format, sinf -> free). Empty for plain
    // streams (no key): the engine then serves the file untouched.
    std::vector<uint8_t> header;
    std::vector<CencSample> samples;  // sorted by start
};

// Minimal MP4 box header reader shared with the streaming engine.
struct BoxInfo {
    uint64_t offset = 0;
    uint64_t size = 0;
    uint32_t headerSize = 0;
    char type[5] = {0, 0, 0, 0, 0};
    uint64_t bodyStart() const { return offset + headerSize; }
};
// Returns false when no complete box header fits at `offset`.
bool readBoxAt(const uint8_t* data, size_t length, uint64_t offset, BoxInfo& out);

// Parses the ftyp+moov prefix (`length` bytes starting at file offset 0) of
// an encrypted stream and builds the patched header + sample index.
// `total` is the full file size. Throws std::runtime_error on bad input.
PreparedStream prepareCencHeader(const uint8_t* data, size_t length, const uint8_t key[16],
                                  uint64_t total);

// Rewrites `data` (the bytes of [start, start+length)) into playable form:
// the overlapping header region is replaced with the patched header and all
// overlapping sample ranges are AES-128-CTR decrypted in place.
void decryptPartial(uint8_t* data, size_t length, uint64_t start, const uint8_t key[16],
                    const PreparedStream& prepared);

}  // namespace guo
