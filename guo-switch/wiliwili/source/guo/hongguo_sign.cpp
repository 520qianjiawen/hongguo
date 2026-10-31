#include "guo/hongguo_sign.hpp"

#include <array>
#include <cstring>

namespace guo {
namespace {

// ---- MD5 (RFC 1321), compact self-contained implementation. ----

constexpr uint32_t kShift[64] = {
    7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22,
    5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20,
    4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23,
    6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21,
};

constexpr uint32_t kTable[64] = {
    0xd76aa478, 0xe8c7b756, 0x242070db, 0xc1bdceee, 0xf57c0faf, 0x4787c62a, 0xa8304613, 0xfd469501,
    0x698098d8, 0x8b44f7af, 0xffff5bb1, 0x895cd7be, 0x6b901122, 0xfd987193, 0xa679438e, 0x49b40821,
    0xf61e2562, 0xc040b340, 0x265e5a51, 0xe9b6c7aa, 0xd62f105d, 0x02441453, 0xd8a1e681, 0xe7d3fbc8,
    0x21e1cde6, 0xc33707d6, 0xf4d50d87, 0x455a14ed, 0xa9e3e905, 0xfcefa3f8, 0x676f02d9, 0x8d2a4c8a,
    0xfffa3942, 0x8771f681, 0x6d9d6122, 0xfde5380c, 0xa4beea44, 0x4bdecfa9, 0xf6bb4b60, 0xbebfbc70,
    0x289b7ec6, 0xeaa127fa, 0xd4ef3085, 0x04881d05, 0xd9d4d039, 0xe6db99e5, 0x1fa27cf8, 0xc4ac5665,
    0xf4292244, 0x432aff97, 0xab9423a7, 0xfc93a039, 0x655b59c3, 0x8f0ccc92, 0xffeff47d, 0x85845dd1,
    0x6fa87e4f, 0xfe2ce6e0, 0xa3014314, 0x4e0811a1, 0xf7537e82, 0xbd3af235, 0x2ad7d2bb, 0xeb86d391,
};

uint32_t rotateLeft(uint32_t value, uint32_t count) {
    return (value << count) | (value >> (32 - count));
}

std::array<uint8_t, 16> md5Digest(const uint8_t* data, size_t length) {
    uint32_t a0 = 0x67452301, b0 = 0xefcdab89, c0 = 0x98badcfe, d0 = 0x10325476;
    const uint64_t bitLength = static_cast<uint64_t>(length) * 8;

    auto processBlock = [&](const uint8_t* block) {
        uint32_t words[16];
        for (int i = 0; i < 16; ++i) {
            words[i] = static_cast<uint32_t>(block[i * 4]) |
                       (static_cast<uint32_t>(block[i * 4 + 1]) << 8) |
                       (static_cast<uint32_t>(block[i * 4 + 2]) << 16) |
                       (static_cast<uint32_t>(block[i * 4 + 3]) << 24);
        }
        uint32_t a = a0, b = b0, c = c0, d = d0;
        for (int i = 0; i < 64; ++i) {
            uint32_t f;
            int g;
            if (i < 16) {
                f = (b & c) | (~b & d);
                g = i;
            } else if (i < 32) {
                f = (d & b) | (~d & c);
                g = (5 * i + 1) % 16;
            } else if (i < 48) {
                f = b ^ c ^ d;
                g = (3 * i + 5) % 16;
            } else {
                f = c ^ (b | ~d);
                g = (7 * i) % 16;
            }
            const uint32_t tmp = d;
            d = c;
            c = b;
            b = b + rotateLeft(a + f + kTable[i] + words[g], kShift[i]);
            a = tmp;
        }
        a0 += a;
        b0 += b;
        c0 += c;
        d0 += d;
    };

    size_t offset = 0;
    while (offset + 64 <= length) {
        processBlock(data + offset);
        offset += 64;
    }
    uint8_t tail[128] = {0};
    const size_t remaining = length - offset;
    if (remaining) std::memcpy(tail, data + offset, remaining);
    tail[remaining] = 0x80;
    const size_t tailLength = remaining < 56 ? 64 : 128;
    for (int i = 0; i < 8; ++i) tail[tailLength - 8 + i] = static_cast<uint8_t>(bitLength >> (8 * i));
    processBlock(tail);
    if (tailLength == 128) processBlock(tail + 64);

    std::array<uint8_t, 16> digest{};
    const uint32_t state[4] = {a0, b0, c0, d0};
    for (int i = 0; i < 4; ++i) {
        digest[i * 4] = static_cast<uint8_t>(state[i]);
        digest[i * 4 + 1] = static_cast<uint8_t>(state[i] >> 8);
        digest[i * 4 + 2] = static_cast<uint8_t>(state[i] >> 16);
        digest[i * 4 + 3] = static_cast<uint8_t>(state[i] >> 24);
    }
    return digest;
}

std::array<uint8_t, 16> md5Digest(const std::string& data) {
    return md5Digest(reinterpret_cast<const uint8_t*>(data.data()), data.size());
}

std::string toHex(const uint8_t* data, size_t length, bool uppercase = false) {
    const char* digits = uppercase ? "0123456789ABCDEF" : "0123456789abcdef";
    std::string out;
    out.reserve(length * 2);
    for (size_t i = 0; i < length; ++i) {
        out += digits[data[i] >> 4];
        out += digits[data[i] & 15];
    }
    return out;
}

uint8_t reverseBits(uint8_t value) {
    uint8_t result = 0;
    for (int i = 0; i < 8; ++i) result = static_cast<uint8_t>((result << 1) | ((value >> i) & 1));
    return result;
}

void writeBe32(uint8_t* out, uint32_t value) {
    out[0] = static_cast<uint8_t>(value >> 24);
    out[1] = static_cast<uint8_t>(value >> 16);
    out[2] = static_cast<uint8_t>(value >> 8);
    out[3] = static_cast<uint8_t>(value);
}

void writeLe32(uint8_t* out, uint32_t value) {
    out[0] = static_cast<uint8_t>(value);
    out[1] = static_cast<uint8_t>(value >> 8);
    out[2] = static_cast<uint8_t>(value >> 16);
    out[3] = static_cast<uint8_t>(value >> 24);
}

// Faithful transcription of the JS rc4Gorgon: the KSA overwrites s[i] with
// s[j] (no swap), and the PRGA state update differs from textbook RC4. Do
// not "fix" these; the reference vectors pin this exact behavior.
std::array<uint8_t, 20> rc4Gorgon(const std::array<uint8_t, 20>& data,
                                  const std::array<uint8_t, 8>& key) {
    uint8_t s[256];
    for (int i = 0; i < 256; ++i) s[i] = static_cast<uint8_t>(i);
    int j = 0;
    for (int i = 0; i < 256; ++i) {
        j = (j + s[i] + key[i % 8]) & 255;
        s[i] = s[j];
    }
    std::array<uint8_t, 20> out{};
    int i = 0;
    j = 0;
    for (size_t n = 0; n < data.size(); ++n) {
        i += 1;
        const int x = s[i];
        j += x;
        const uint8_t y = s[j & 255];
        s[i] = y;
        out[n] = static_cast<uint8_t>(data[n] ^ s[(y + y) & 255]);
    }
    return out;
}

}  // namespace

std::string md5Hex(const std::string& data) {
    const auto digest = md5Digest(data);
    return toHex(digest.data(), digest.size());
}

std::map<std::string, std::string> signRequest(const std::string& query, const std::string& body,
                                               int64_t nowMs) {
    const auto queryHash = md5Digest(query);
    const auto bodyHash = md5Digest(body);
    std::array<uint8_t, 20> payload{};
    std::memcpy(payload.data(), queryHash.data(), 4);
    std::memcpy(payload.data() + 4, bodyHash.data(), 4);
    payload[12] = 0;
    payload[13] = 6;
    payload[14] = 11;
    payload[15] = 28;
    writeBe32(payload.data() + 16, static_cast<uint32_t>(nowMs / 1000));
    constexpr std::array<uint8_t, 20> key = {0x44, 0xb9, 0xb9, 0xd9, 0xa4, 0xae, 0xf9, 0xfc,
                                             0xa4, 0x93, 0xaa, 0x75, 0x7c, 0xa3, 0xc2, 0xc4,
                                             0xa4, 0x96, 0x93, 0x8f};
    for (int i = 0; i < 20; ++i) payload[i] ^= key[i];
    for (int i = 0; i < 20; ++i) {
        const uint8_t x = static_cast<uint8_t>(((payload[i] << 4) | (payload[i] >> 4)) & 255) ^
                          payload[(i + 1) % 20];
        payload[i] = static_cast<uint8_t>(reverseBits(x) ^ 255 ^ 20);
    }
    return {
        {"X-Khronos", std::to_string(nowMs / 1000)},
        {"X-Gorgon", "8404401c0000" + toHex(payload.data(), payload.size())},
        {"X-SS-Req-Ticket", std::to_string(nowMs)},
        {"X-SS-STUB", toHex(bodyHash.data(), bodyHash.size(), true)},
    };
}

std::string xGorgon(const std::string& query, const std::string& body, int64_t timestampSec,
                    uint32_t randomValue) {
    std::array<uint8_t, 20> input{};
    const auto queryHash = md5Digest(query);
    std::memcpy(input.data(), queryHash.data(), 4);
    if (!body.empty()) {
        const auto bodyHash = md5Digest(body);
        std::memcpy(input.data() + 4, bodyHash.data(), 4);
    }
    writeLe32(input.data() + 12, 67503104);
    writeBe32(input.data() + 16, static_cast<uint32_t>(timestampSec));
    const std::array<uint8_t, 8> key = {0x4a, 0x40, 0x16,
                                        static_cast<uint8_t>((randomValue >> 8) & 255),
                                        0x47, 0x6c, 0x01,
                                        static_cast<uint8_t>(randomValue & 255)};
    auto out = rc4Gorgon(input, key);
    for (size_t i = 0; i < out.size(); ++i) {
        uint8_t value = static_cast<uint8_t>(((out[i] >> 4) | (out[i] << 4)) & 255);
        const uint8_t next = (i + 1 < out.size()) ? out[i + 1] : out[0];
        out[i] = static_cast<uint8_t>(~(reverseBits(static_cast<uint8_t>(next ^ value)) ^ 20));
    }
    std::array<uint8_t, 26> packed{};
    packed[0] = 0x84;
    packed[1] = 0x04;
    packed[2] = static_cast<uint8_t>(randomValue & 255);
    packed[3] = static_cast<uint8_t>((randomValue >> 8) & 255);
    packed[4] = 0x40;
    packed[5] = 0x01;
    std::memcpy(packed.data() + 6, out.data(), out.size());
    return toHex(packed.data(), packed.size());
}

}  // namespace guo
