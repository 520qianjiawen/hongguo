#pragma once

#include <cstdint>
#include <deque>
#include <map>
#include <string>
#include <utility>
#include <vector>

#include <nlohmann/json.hpp>

#include "guo/provider.hpp"

namespace guo {

// One playable variant of an episode from the App channel.
struct StreamRow {
    std::string url;
    std::string backupUrl;
    std::string codec;           // e.g. "bytevc1"; empty when unknown
    int quality = 0;             // vertical resolution, e.g. 480
    long long size = 0;          // bytes, 0 when the model does not say
    std::vector<uint8_t> key;    // 16-byte CENC content key; empty = plain
};

struct StreamSet {
    std::vector<StreamRow> rows;  // sorted by quality, descending
};

// --- Pure helpers (host-tested against live-captured vectors) ---

// 2160/1440/1080/720/576/540/480/360 extraction + dimension fallbacks.
int qualityNumber(const std::string& value);

// encodeURIComponent + the extra [! ' ( ) *] escaping the signed query needs.
std::string pythonUrlEncode(const std::vector<std::pair<std::string, std::string>>& values);

// JSON.parse with the drpy big-integer protection: integers of 16+ digits
// outside strings are quoted so episode/series ids survive exactly.
nlohmann::json parseJsonProtected(const std::string& text);

// spade_a -> 16-byte content key. Throws std::runtime_error on malformed/unsupported.
std::vector<uint8_t> contentKeyFromSpade(const std::string& spade);

// Fallback-API body: either plain JSON or the 'v2.' AES envelope.
nlohmann::json decodeFallbackBody(const std::string& body);

// key_seed-encrypted CDN address -> plain URL. Throws std::runtime_error.
std::string decryptSpadeUrl(const std::string& encoded, const std::vector<uint8_t>& seed);

// video_model JSON -> playable rows (bytevc2 rows are dropped).
std::vector<StreamRow> rowsFromModel(const nlohmann::json& model);

// Hongguo App API client (device registration, signed requests, videoInfo),
// a 1:1 port of phone_source.js. The device identity is server-issued and
// kept in memory only, like the JS.
class HongguoAppClient {
public:
    HongguoAppClient(FetchHttp fetch, FetchText fetchText);

    StreamSet videoInfo(const std::string& episodeId);
    StreamSet backup(const std::string& seriesId, const std::string& episodeId);
    void clearCache();

private:
    void ensureIdentity();
    nlohmann::json appRequest(const std::string& path, const nlohmann::ordered_json& payload);

    FetchHttp fetch_;
    FetchText fetchText_;
    std::string deviceId_;
    std::string installId_;
    bool identityInFlight_ = false;

    struct CacheEntry {
        int64_t timeMs = 0;
        StreamSet value;
    };
    std::map<std::string, CacheEntry> cache_;
    std::deque<std::string> cacheOrder_;
};

}  // namespace guo
