#include "guo/hongguo_crypto.hpp"

#include <cstring>
#include <stdexcept>

namespace guo {
namespace {

// ---------------- SHA-256 / SHA-512 ----------------
// Round constants are the fractional parts of the cube roots of the first
// primes (FIPS 180-4); initial states use the square roots.

constexpr uint32_t kSha256K[64] = {
    0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
    0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
    0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
    0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
    0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
    0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
    0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
    0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2,
};

uint32_t rotr32(uint32_t x, int n) { return (x >> n) | (x << (32 - n)); }
uint64_t rotr64(uint64_t x, int n) { return (x >> n) | (x << (64 - n)); }

void sha256Compress(uint32_t state[8], const uint8_t* block) {
    uint32_t w[64];
    for (int i = 0; i < 16; ++i) {
        w[i] = (static_cast<uint32_t>(block[i * 4]) << 24) |
               (static_cast<uint32_t>(block[i * 4 + 1]) << 16) |
               (static_cast<uint32_t>(block[i * 4 + 2]) << 8) | block[i * 4 + 3];
    }
    for (int i = 16; i < 64; ++i) {
        const uint32_t s0 = rotr32(w[i - 15], 7) ^ rotr32(w[i - 15], 18) ^ (w[i - 15] >> 3);
        const uint32_t s1 = rotr32(w[i - 2], 17) ^ rotr32(w[i - 2], 19) ^ (w[i - 2] >> 10);
        w[i] = w[i - 16] + s0 + w[i - 7] + s1;
    }
    uint32_t a = state[0], b = state[1], c = state[2], d = state[3];
    uint32_t e = state[4], f = state[5], g = state[6], h = state[7];
    for (int i = 0; i < 64; ++i) {
        const uint32_t s1 = rotr32(e, 6) ^ rotr32(e, 11) ^ rotr32(e, 25);
        const uint32_t ch = (e & f) ^ (~e & g);
        const uint32_t t1 = h + s1 + ch + kSha256K[i] + w[i];
        const uint32_t s0 = rotr32(a, 2) ^ rotr32(a, 13) ^ rotr32(a, 22);
        const uint32_t maj = (a & b) ^ (a & c) ^ (b & c);
        const uint32_t t2 = s0 + maj;
        h = g; g = f; f = e; e = d + t1; d = c; c = b; b = a; a = t1 + t2;
    }
    state[0] += a; state[1] += b; state[2] += c; state[3] += d;
    state[4] += e; state[5] += f; state[6] += g; state[7] += h;
}

constexpr uint64_t kSha512K[80] = {
    0x428a2f98d728ae22ULL, 0x7137449123ef65cdULL, 0xb5c0fbcfec4d3b2fULL, 0xe9b5dba58189dbbcULL,
    0x3956c25bf348b538ULL, 0x59f111f1b605d019ULL, 0x923f82a4af194f9bULL, 0xab1c5ed5da6d8118ULL,
    0xd807aa98a3030242ULL, 0x12835b0145706fbeULL, 0x243185be4ee4b28cULL, 0x550c7dc3d5ffb4e2ULL,
    0x72be5d74f27b896fULL, 0x80deb1fe3b1696b1ULL, 0x9bdc06a725c71235ULL, 0xc19bf174cf692694ULL,
    0xe49b69c19ef14ad2ULL, 0xefbe4786384f25e3ULL, 0x0fc19dc68b8cd5b5ULL, 0x240ca1cc77ac9c65ULL,
    0x2de92c6f592b0275ULL, 0x4a7484aa6ea6e483ULL, 0x5cb0a9dcbd41fbd4ULL, 0x76f988da831153b5ULL,
    0x983e5152ee66dfabULL, 0xa831c66d2db43210ULL, 0xb00327c898fb213fULL, 0xbf597fc7beef0ee4ULL,
    0xc6e00bf33da88fc2ULL, 0xd5a79147930aa725ULL, 0x06ca6351e003826fULL, 0x142929670a0e6e70ULL,
    0x27b70a8546d22ffcULL, 0x2e1b21385c26c926ULL, 0x4d2c6dfc5ac42aedULL, 0x53380d139d95b3dfULL,
    0x650a73548baf63deULL, 0x766a0abb3c77b2a8ULL, 0x81c2c92e47edaee6ULL, 0x92722c851482353bULL,
    0xa2bfe8a14cf10364ULL, 0xa81a664bbc423001ULL, 0xc24b8b70d0f89791ULL, 0xc76c51a30654be30ULL,
    0xd192e819d6ef5218ULL, 0xd69906245565a910ULL, 0xf40e35855771202aULL, 0x106aa07032bbd1b8ULL,
    0x19a4c116b8d2d0c8ULL, 0x1e376c085141ab53ULL, 0x2748774cdf8eeb99ULL, 0x34b0bcb5e19b48a8ULL,
    0x391c0cb3c5c95a63ULL, 0x4ed8aa4ae3418acbULL, 0x5b9cca4f7763e373ULL, 0x682e6ff3d6b2b8a3ULL,
    0x748f82ee5defb2fcULL, 0x78a5636f43172f60ULL, 0x84c87814a1f0ab72ULL, 0x8cc702081a6439ecULL,
    0x90befffa23631e28ULL, 0xa4506cebde82bde9ULL, 0xbef9a3f7b2c67915ULL, 0xc67178f2e372532bULL,
    0xca273eceea26619cULL, 0xd186b8c721c0c207ULL, 0xeada7dd6cde0eb1eULL, 0xf57d4f7fee6ed178ULL,
    0x06f067aa72176fbaULL, 0x0a637dc5a2c898a6ULL, 0x113f9804bef90daeULL, 0x1b710b35131c471bULL,
    0x28db77f523047d84ULL, 0x32caab7b40c72493ULL, 0x3c9ebe0a15c9bebcULL, 0x431d67c49c100d4cULL,
    0x4cc5d4becb3e42b6ULL, 0x597f299cfc657e2aULL, 0x5fcb6fab3ad6faecULL, 0x6c44198c4a475817ULL,
};

void sha512Compress(uint64_t state[8], const uint8_t* block) {
    uint64_t w[80];
    for (int i = 0; i < 16; ++i) {
        uint64_t v = 0;
        for (int j = 0; j < 8; ++j) v = (v << 8) | block[i * 8 + j];
        w[i] = v;
    }
    for (int i = 16; i < 80; ++i) {
        const uint64_t s0 = rotr64(w[i - 15], 1) ^ rotr64(w[i - 15], 8) ^ (w[i - 15] >> 7);
        const uint64_t s1 = rotr64(w[i - 2], 19) ^ rotr64(w[i - 2], 61) ^ (w[i - 2] >> 6);
        w[i] = w[i - 16] + s0 + w[i - 7] + s1;
    }
    uint64_t a = state[0], b = state[1], c = state[2], d = state[3];
    uint64_t e = state[4], f = state[5], g = state[6], h = state[7];
    for (int i = 0; i < 80; ++i) {
        const uint64_t s1 = rotr64(e, 14) ^ rotr64(e, 18) ^ rotr64(e, 41);
        const uint64_t ch = (e & f) ^ (~e & g);
        const uint64_t t1 = h + s1 + ch + kSha512K[i] + w[i];
        const uint64_t s0 = rotr64(a, 28) ^ rotr64(a, 34) ^ rotr64(a, 39);
        const uint64_t maj = (a & b) ^ (a & c) ^ (b & c);
        const uint64_t t2 = s0 + maj;
        h = g; g = f; f = e; e = d + t1; d = c; c = b; b = a; a = t1 + t2;
    }
    state[0] += a; state[1] += b; state[2] += c; state[3] += d;
    state[4] += e; state[5] += f; state[6] += g; state[7] += h;
}

// ---------------- AES-128 ----------------
// S-boxes are generated at first use from the GF(2^8) definition, so there
// are no hand-typed tables to get wrong; the known-answer tests in
// scripts/check_guo.py pin the results.

uint8_t gfMul(uint8_t a, uint8_t b) {
    uint8_t p = 0;
    for (int i = 0; i < 8; ++i) {
        if (b & 1) p ^= a;
        const bool hi = a & 0x80;
        a = static_cast<uint8_t>(a << 1);
        if (hi) a ^= 0x1b;
        b >>= 1;
    }
    return p;
}

struct AesTables {
    uint8_t sbox[256];
    uint8_t invSbox[256];
    AesTables() {
        for (int i = 0; i < 256; ++i) {
            uint8_t inv = 0;
            if (i != 0) {
                // Multiplicative inverse = i^254 in GF(2^8).
                uint8_t result = 1, base = static_cast<uint8_t>(i);
                int exp = 254;
                while (exp) {
                    if (exp & 1) result = gfMul(result, base);
                    base = gfMul(base, base);
                    exp >>= 1;
                }
                inv = result;
            }
            const uint8_t x = inv;
            auto rotl = [](uint8_t v, int n) { return static_cast<uint8_t>((v << n) | (v >> (8 - n))); };
            const uint8_t s = static_cast<uint8_t>(x ^ rotl(x, 1) ^ rotl(x, 2) ^ rotl(x, 3) ^ rotl(x, 4) ^ 0x63);
            sbox[i] = s;
            invSbox[s] = static_cast<uint8_t>(i);
        }
    }
};

const AesTables& aesTables() {
    static const AesTables tables;
    return tables;
}

uint8_t xtime(uint8_t x) {
    return static_cast<uint8_t>((x << 1) ^ ((x & 0x80) ? 0x1b : 0x00));
}

}  // namespace

std::vector<uint8_t> sha256(const uint8_t* data, size_t length) {
    uint32_t state[8] = {0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a,
                         0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19};
    const uint64_t bitLength = static_cast<uint64_t>(length) * 8;
    size_t offset = 0;
    while (offset + 64 <= length) {
        sha256Compress(state, data + offset);
        offset += 64;
    }
    uint8_t tail[128] = {0};
    const size_t remaining = length - offset;
    if (remaining) std::memcpy(tail, data + offset, remaining);
    tail[remaining] = 0x80;
    const size_t tailLength = remaining < 56 ? 64 : 128;
    for (int i = 0; i < 8; ++i) tail[tailLength - 1 - i] = static_cast<uint8_t>(bitLength >> (8 * i));
    sha256Compress(state, tail);
    if (tailLength == 128) sha256Compress(state, tail + 64);
    std::vector<uint8_t> out(32);
    for (int i = 0; i < 8; ++i) {
        out[i * 4] = static_cast<uint8_t>(state[i] >> 24);
        out[i * 4 + 1] = static_cast<uint8_t>(state[i] >> 16);
        out[i * 4 + 2] = static_cast<uint8_t>(state[i] >> 8);
        out[i * 4 + 3] = static_cast<uint8_t>(state[i]);
    }
    return out;
}

std::vector<uint8_t> sha512(const uint8_t* data, size_t length) {
    uint64_t state[8] = {0x6a09e667f3bcc908ULL, 0xbb67ae8584caa73bULL, 0x3c6ef372fe94f82bULL,
                         0xa54ff53a5f1d36f1ULL, 0x510e527fade682d1ULL, 0x9b05688c2b3e6c1fULL,
                         0x1f83d9abfb41bd6bULL, 0x5be0cd19137e2179ULL};
    const uint64_t bitLengthLow = static_cast<uint64_t>(length) * 8;
    size_t offset = 0;
    while (offset + 128 <= length) {
        sha512Compress(state, data + offset);
        offset += 128;
    }
    uint8_t tail[256] = {0};
    const size_t remaining = length - offset;
    if (remaining) std::memcpy(tail, data + offset, remaining);
    tail[remaining] = 0x80;
    const size_t tailLength = remaining < 112 ? 128 : 256;
    for (int i = 0; i < 8; ++i) tail[tailLength - 1 - i] = static_cast<uint8_t>(bitLengthLow >> (8 * i));
    sha512Compress(state, tail);
    if (tailLength == 256) sha512Compress(state, tail + 128);
    std::vector<uint8_t> out(64);
    for (int i = 0; i < 8; ++i)
        for (int j = 0; j < 8; ++j) out[i * 8 + j] = static_cast<uint8_t>(state[i] >> (56 - 8 * j));
    return out;
}

std::string sha512Hex(const std::string& data) {
    const auto digest = sha512(reinterpret_cast<const uint8_t*>(data.data()), data.size());
    return hexEncode(digest.data(), digest.size());
}

Aes128::Aes128(const uint8_t key[16]) {
    const auto& tables = aesTables();
    std::memcpy(roundKeys_, key, 16);
    int bytesGenerated = 16;
    int rconIteration = 1;
    uint8_t temp[4];
    while (bytesGenerated < 176) {
        std::memcpy(temp, roundKeys_ + bytesGenerated - 4, 4);
        if (bytesGenerated % 16 == 0) {
            const uint8_t t = temp[0];
            temp[0] = tables.sbox[temp[1]];
            temp[1] = tables.sbox[temp[2]];
            temp[2] = tables.sbox[temp[3]];
            temp[3] = tables.sbox[t];
            uint8_t rcon = 1;
            for (int i = 1; i < rconIteration; ++i) rcon = xtime(rcon);
            temp[0] ^= rcon;
            ++rconIteration;
        }
        for (int i = 0; i < 4; ++i) {
            roundKeys_[bytesGenerated] = roundKeys_[bytesGenerated - 16] ^ temp[i];
            ++bytesGenerated;
        }
    }
}

void Aes128::encryptBlock(const uint8_t in[16], uint8_t out[16]) const {
    const auto& tables = aesTables();
    uint8_t state[16];
    for (int i = 0; i < 16; ++i) state[i] = in[i] ^ roundKeys_[i];
    for (int round = 1; round <= 10; ++round) {
        for (int i = 0; i < 16; ++i) state[i] = tables.sbox[state[i]];
        uint8_t shifted[16];
        for (int row = 0; row < 4; ++row)
            for (int col = 0; col < 4; ++col)
                shifted[col * 4 + row] = state[((col + row) % 4) * 4 + row];
        std::memcpy(state, shifted, 16);
        if (round < 10) {
            for (int col = 0; col < 4; ++col) {
                uint8_t* s = state + col * 4;
                const uint8_t a0 = s[0], a1 = s[1], a2 = s[2], a3 = s[3];
                s[0] = gfMul(a0, 2) ^ gfMul(a1, 3) ^ a2 ^ a3;
                s[1] = a0 ^ gfMul(a1, 2) ^ gfMul(a2, 3) ^ a3;
                s[2] = a0 ^ a1 ^ gfMul(a2, 2) ^ gfMul(a3, 3);
                s[3] = gfMul(a0, 3) ^ a1 ^ a2 ^ gfMul(a3, 2);
            }
        }
        for (int i = 0; i < 16; ++i) state[i] ^= roundKeys_[round * 16 + i];
    }
    std::memcpy(out, state, 16);
}

void Aes128::decryptBlock(const uint8_t in[16], uint8_t out[16]) const {
    const auto& tables = aesTables();
    uint8_t state[16];
    for (int i = 0; i < 16; ++i) state[i] = in[i] ^ roundKeys_[160 + i];
    for (int round = 9; round >= 0; --round) {
        uint8_t shifted[16];
        for (int row = 0; row < 4; ++row)
            for (int col = 0; col < 4; ++col)
                shifted[col * 4 + row] = state[((col - row + 4) % 4) * 4 + row];
        std::memcpy(state, shifted, 16);
        for (int i = 0; i < 16; ++i) state[i] = tables.invSbox[state[i]];
        for (int i = 0; i < 16; ++i) state[i] ^= roundKeys_[round * 16 + i];
        if (round > 0) {
            for (int col = 0; col < 4; ++col) {
                uint8_t* s = state + col * 4;
                const uint8_t a0 = s[0], a1 = s[1], a2 = s[2], a3 = s[3];
                s[0] = gfMul(a0, 14) ^ gfMul(a1, 11) ^ gfMul(a2, 13) ^ gfMul(a3, 9);
                s[1] = gfMul(a0, 9) ^ gfMul(a1, 14) ^ gfMul(a2, 11) ^ gfMul(a3, 13);
                s[2] = gfMul(a0, 13) ^ gfMul(a1, 9) ^ gfMul(a2, 14) ^ gfMul(a3, 11);
                s[3] = gfMul(a0, 11) ^ gfMul(a1, 13) ^ gfMul(a2, 9) ^ gfMul(a3, 14);
            }
        }
    }
    std::memcpy(out, state, 16);
}

std::vector<uint8_t> aes128CbcDecrypt(const uint8_t key[16], const uint8_t iv[16],
                                      const uint8_t* data, size_t length) {
    Aes128 aes(key);
    std::vector<uint8_t> out(length);
    uint8_t previous[16];
    std::memcpy(previous, iv, 16);
    for (size_t offset = 0; offset + 16 <= length; offset += 16) {
        uint8_t block[16];
        aes.decryptBlock(data + offset, block);
        for (int i = 0; i < 16; ++i) out[offset + i] = block[i] ^ previous[i];
        std::memcpy(previous, data + offset, 16);
    }
    return out;
}

std::vector<uint8_t> aes128CbcEncryptPkcs7(const uint8_t key[16], const uint8_t iv[16],
                                           const uint8_t* data, size_t length) {
    Aes128 aes(key);
    const size_t padded = (length / 16 + 1) * 16;
    std::vector<uint8_t> out(padded);
    uint8_t previous[16];
    std::memcpy(previous, iv, 16);
    const uint8_t pad = static_cast<uint8_t>(padded - length);
    for (size_t offset = 0; offset < padded; offset += 16) {
        uint8_t block[16];
        for (int i = 0; i < 16; ++i) {
            const size_t idx = offset + i;
            block[i] = (idx < length ? data[idx] : pad) ^ previous[i];
        }
        aes.encryptBlock(block, out.data() + offset);
        std::memcpy(previous, out.data() + offset, 16);
    }
    return out;
}

void aes128CtrXor(const uint8_t key[16], const uint8_t counter[16], uint64_t byteOffset,
                  uint8_t* data, size_t length) {
    Aes128 aes(key);
    uint8_t ctr[16];
    std::memcpy(ctr, counter, 16);
    // Advance the big-endian counter by whole blocks.
    uint64_t blocks = byteOffset / 16;
    for (int i = 15; i >= 0 && blocks; --i) {
        const uint64_t sum = static_cast<uint64_t>(ctr[i]) + (blocks & 0xff);
        ctr[i] = static_cast<uint8_t>(sum);
        blocks = (blocks >> 8) + (sum >> 8);
    }
    size_t done = 0;
    size_t skip = byteOffset % 16;  // only the first block starts mid-stream
    while (done < length) {
        uint8_t stream[16];
        aes.encryptBlock(ctr, stream);
        const size_t n = std::min(length - done, 16 - skip);
        for (size_t i = 0; i < n; ++i) data[done + i] ^= stream[skip + i];
        done += n;
        skip = 0;
        // Increment big-endian counter.
        for (int i = 15; i >= 0; --i) {
            if (++ctr[i]) break;
        }
    }
}

std::vector<uint8_t> base64Decode(const std::string& text) {
    static const char* kAlphabet =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    int table[256];
    for (int i = 0; i < 256; ++i) table[i] = -1;
    for (int i = 0; i < 64; ++i) table[static_cast<uint8_t>(kAlphabet[i])] = i;
    std::vector<uint8_t> out;
    uint32_t acc = 0;
    int bits = 0;
    for (char ch : text) {
        if (ch == '=') break;
        if (ch == '\n' || ch == '\r' || ch == ' ' || ch == '\t') continue;
        const int v = table[static_cast<uint8_t>(ch)];
        if (v < 0) throw std::runtime_error("invalid base64 input");
        acc = (acc << 6) | static_cast<uint32_t>(v);
        bits += 6;
        if (bits >= 8) {
            bits -= 8;
            out.push_back(static_cast<uint8_t>(acc >> bits));
        }
    }
    return out;
}

std::string base64Encode(const uint8_t* data, size_t length) {
    static const char* kAlphabet =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    std::string out;
    for (size_t i = 0; i < length; i += 3) {
        const uint32_t a = data[i];
        const uint32_t b = i + 1 < length ? data[i + 1] : 0;
        const uint32_t c = i + 2 < length ? data[i + 2] : 0;
        out += kAlphabet[a >> 2];
        out += kAlphabet[((a & 3) << 4) | (b >> 4)];
        out += (i + 1 < length) ? kAlphabet[((b & 15) << 2) | (c >> 6)] : '=';
        out += (i + 2 < length) ? kAlphabet[c & 63] : '=';
    }
    return out;
}

std::vector<uint8_t> hexDecode(const std::string& text) {
    auto value = [](char ch) -> int {
        if (ch >= '0' && ch <= '9') return ch - '0';
        if (ch >= 'a' && ch <= 'f') return ch - 'a' + 10;
        if (ch >= 'A' && ch <= 'F') return ch - 'A' + 10;
        return -1;
    };
    std::vector<uint8_t> out;
    for (size_t i = 0; i + 1 < text.size(); i += 2) {
        const int hi = value(text[i]), lo = value(text[i + 1]);
        if (hi < 0 || lo < 0) throw std::runtime_error("invalid hex input");
        out.push_back(static_cast<uint8_t>((hi << 4) | lo));
    }
    return out;
}

std::string hexEncode(const uint8_t* data, size_t length, bool uppercase) {
    const char* digits = uppercase ? "0123456789ABCDEF" : "0123456789abcdef";
    std::string out;
    out.reserve(length * 2);
    for (size_t i = 0; i < length; ++i) {
        out += digits[data[i] >> 4];
        out += digits[data[i] & 15];
    }
    return out;
}

}  // namespace guo
