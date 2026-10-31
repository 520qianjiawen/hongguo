#pragma once

#include <cstdint>
#include <string>
#include <vector>

namespace guo {

// Small self-contained crypto helpers for the Hongguo App channel:
// SHA-256 / SHA-512, AES-128 (block, CBC, CTR), base64 and hex.
// (MD5 lives in hongguo_sign.hpp; the playback path needs nothing else.)

std::vector<uint8_t> sha256(const uint8_t* data, size_t length);
std::vector<uint8_t> sha512(const uint8_t* data, size_t length);
std::string sha512Hex(const std::string& data);

class Aes128 {
public:
    explicit Aes128(const uint8_t key[16]);
    void encryptBlock(const uint8_t in[16], uint8_t out[16]) const;
    void decryptBlock(const uint8_t in[16], uint8_t out[16]) const;

private:
    uint8_t roundKeys_[176];
};

// AES-128-CBC decrypt without padding handling (input must be block aligned).
std::vector<uint8_t> aes128CbcDecrypt(const uint8_t key[16], const uint8_t iv[16],
                                      const uint8_t* data, size_t length);
// AES-128-CBC encrypt with PKCS#7 padding (used by tests / fixture builders).
std::vector<uint8_t> aes128CbcEncryptPkcs7(const uint8_t key[16], const uint8_t iv[16],
                                           const uint8_t* data, size_t length);
// AES-128-CTR: XORs `length` bytes starting at the given 128-bit big-endian
// counter value plus a byte offset into the counter stream.
void aes128CtrXor(const uint8_t key[16], const uint8_t counter[16], uint64_t byteOffset,
                  uint8_t* data, size_t length);

std::vector<uint8_t> base64Decode(const std::string& text);  // throws std::runtime_error
std::string base64Encode(const uint8_t* data, size_t length);
std::vector<uint8_t> hexDecode(const std::string& text);
std::string hexEncode(const uint8_t* data, size_t length, bool uppercase = false);

}  // namespace guo
