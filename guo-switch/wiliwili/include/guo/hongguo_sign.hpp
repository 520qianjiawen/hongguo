#pragma once

#include <cstdint>
#include <map>
#include <string>

namespace guo {

// Playback-path request signing for the Hongguo App API, ported 1:1 from the
// verified JS implementation (phone_source.js sign() and hongguo.cjs
// xGorgon()). The heavier x-medusa / x-helios suite is not used by the App
// playback endpoints and is intentionally not ported.

// Lowercase hex MD5 digest of `data` (raw bytes are fine).
std::string md5Hex(const std::string& data);

// Base signature headers used by every App API request:
// X-Khronos, X-Gorgon (8404401c form), X-SS-Req-Ticket, X-SS-STUB.
// `query` is the exact URL-encoded query string, `body` the exact request
// body, `nowMs` the request ticket time in milliseconds.
std::map<std::string, std::string> signRequest(const std::string& query, const std::string& body,
                                               int64_t nowMs);

// Stronger X-Gorgon (8404 form) used only for /novel/player/multi_video_model.
// `body` may be empty. `randomValue` is the caller-chosen random (the JS uses
// a fixed 12345 for the video model request).
std::string xGorgon(const std::string& query, const std::string& body, int64_t timestampSec,
                    uint32_t randomValue);

}  // namespace guo
