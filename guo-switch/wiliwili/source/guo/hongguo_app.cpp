#include "guo/hongguo_app.hpp"

#include <algorithm>
#include <chrono>
#include <cctype>
#include <random>
#include <stdexcept>
#include <regex>

#include "guo/hongguo_crypto.hpp"
#include "guo/hongguo_sign.hpp"

namespace guo {
namespace {

const char* kUserAgent =
    "com.phoenix.read/73932 (Linux; U; Android 16; zh_CN; 25053RT47C; "
    "Build/BP2A.250605.031.A3; Cronet/TTNetVersion:04657795 2026-01-23 "
    "QuicVersion:c67e9834 2025-09-08)";

bool validId(const std::string& id) {
    if (id.empty() || id.size() > 30) return false;
    return std::all_of(id.begin(), id.end(), [](unsigned char c) { return std::isdigit(c); });
}

bool httpUrl(const std::string& value) {
    return value.size() <= 8192 && (value.rfind("http://", 0) == 0 || value.rfind("https://", 0) == 0);
}

std::string trim(const std::string& value) {
    const size_t begin = value.find_first_not_of(" \t\r\n");
    if (begin == std::string::npos) return "";
    const size_t end = value.find_last_not_of(" \t\r\n");
    return value.substr(begin, end - begin + 1);
}

std::string jsonText(const nlohmann::json& value) {
    if (value.is_string()) return value.get<std::string>();
    if (value.is_number_unsigned()) return std::to_string(value.get<unsigned long long>());
    if (value.is_number_integer()) return std::to_string(value.get<long long>());
    if (value.is_boolean()) return value.get<bool>() ? "true" : "false";
    return "";
}

double jsonNumber(const nlohmann::json& value, double fallback = 0) {
    if (value.is_number()) return value.get<double>();
    if (value.is_string()) {
        try {
            return std::stod(value.get<std::string>());
        } catch (...) {
            return fallback;
        }
    }
    return fallback;
}

bool jsonTruthy(const nlohmann::json& value) {
    if (value.is_boolean()) return value.get<bool>();
    if (value.is_number()) return value.get<double>() != 0;
    if (value.is_string()) return !value.get<std::string>().empty();
    return !value.is_null();
}

int64_t nowMs() {
    return std::chrono::duration_cast<std::chrono::milliseconds>(
               std::chrono::system_clock::now().time_since_epoch())
        .count();
}

// Percent-encode one component the way encodeURIComponent does after the
// extra [! ' ( ) *] replacements: unreserved set is [A-Za-z0-9-._~].
std::string encodeComponent(const std::string& value) {
    std::string out;
    for (unsigned char c : value) {
        const bool safe = (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') ||
                          (c >= '0' && c <= '9') || c == '-' || c == '.' || c == '_' || c == '~';
        if (safe) {
            out += static_cast<char>(c);
        } else {
            char buf[4];
            std::snprintf(buf, sizeof(buf), "%%%02X", c);
            out += buf;
        }
    }
    return out;
}

std::string randomHexBytes(int bytes) {
    static thread_local std::mt19937_64 rng(
        std::chrono::high_resolution_clock::now().time_since_epoch().count() ^
        static_cast<uint64_t>(reinterpret_cast<uintptr_t>(&rng)));
    std::string out;
    for (int i = 0; i < bytes; ++i) {
        const auto b = static_cast<uint8_t>(rng() & 0xff);
        char buf[3];
        std::snprintf(buf, sizeof(buf), "%02x", b);
        out += buf;
    }
    return out;
}

std::string makeUuid() {
    // Same shape as the JS: 8-4-4-4-12 hex with fixed '4' and 'a' nibbles.
    return randomHexBytes(4) + "-" + randomHexBytes(2) + "-4" + randomHexBytes(2).substr(1) +
           "-a" + randomHexBytes(2).substr(1) + "-" + randomHexBytes(6);
}

const std::vector<std::pair<std::string, std::string>>& deviceProfile() {
    static const std::vector<std::pair<std::string, std::string>> profile = {
        {"app_name", "novelread"}, {"os_version", "16"}, {"ac", "wifi"},
        {"version_code", "73932"}, {"update_version_code", "73932"},
        {"manifest_version_code", "73932"}, {"pv_player", "73932"}, {"aid", "8662"},
        {"os_api", "36"}, {"resolution", "1280*2772"}, {"ssmix", "a"}, {"language", "zh"},
        {"device_brand", "Redmi"}, {"device_type", "25053RT47C"}, {"dpi", "520"},
        {"version_name", "7.3.9.32"}, {"device_platform", "android"}, {"channel", "update_64"},
        {"os", "android"},
    };
    return profile;
}

std::string sortedQuery(std::vector<std::pair<std::string, std::string>> values) {
    std::sort(values.begin(), values.end(),
              [](const auto& a, const auto& b) { return a.first < b.first; });
    return pythonUrlEncode(values);
}

}  // namespace

int qualityNumber(const std::string& value) {
    static const char* kKnown[] = {"2160", "1440", "1080", "720", "576", "540", "480", "360"};
    for (const char* known : kKnown) {
        if (value.find(known) != std::string::npos) return std::atoi(known);
    }
    static const std::map<std::string, int> kByDimension = {
        {"1920", 1080}, {"1280", 720}, {"1024", 576}, {"854", 480}, {"640", 360}};
    const auto it = kByDimension.find(value);
    if (it != kByDimension.end()) return it->second;
    if (value.size() >= 3 && value.size() <= 4 &&
        std::all_of(value.begin(), value.end(), [](unsigned char c) { return std::isdigit(c); })) {
        return std::atoi(value.c_str());
    }
    return 0;
}

std::string pythonUrlEncode(const std::vector<std::pair<std::string, std::string>>& values) {
    std::string out;
    for (size_t i = 0; i < values.size(); ++i) {
        if (i) out += '&';
        out += encodeComponent(values[i].first);
        out += '=';
        out += encodeComponent(values[i].second);
    }
    return out;
}

nlohmann::json parseJsonProtected(const std::string& text) {
    // Replicates the JS regex /"(?:\\.|[^"\\])*"|(-?\d{16,})(?=[,}\]\s])/g:
    // strings pass through untouched; long integer literals outside strings
    // are wrapped in quotes so they parse as exact strings.
    std::string out;
    out.reserve(text.size() + 8);
    size_t i = 0;
    while (i < text.size()) {
        const char c = text[i];
        if (c == '"') {
            out += c;
            ++i;
            while (i < text.size()) {
                out += text[i];
                if (text[i] == '\\' && i + 1 < text.size()) {
                    out += text[i + 1];
                    i += 2;
                    continue;
                }
                if (text[i] == '"') {
                    ++i;
                    break;
                }
                ++i;
            }
            continue;
        }
        if (c == '-' || (c >= '0' && c <= '9')) {
            size_t end = i;
            if (text[end] == '-') ++end;
            const size_t digitsBegin = end;
            while (end < text.size() && text[end] >= '0' && text[end] <= '9') ++end;
            const size_t digits = end - digitsBegin;
            const char next = end < text.size() ? text[end] : '\0';
            const bool followed = next == ',' || next == '}' || next == ']' || next == ' ' ||
                                  next == '\t' || next == '\n' || next == '\r';
            if (digits >= 16 && followed) {
                out += '"';
                out.append(text, i, end - i);
                out += '"';
            } else {
                out.append(text, i, end - i);
            }
            i = end;
            continue;
        }
        out += c;
        ++i;
    }
    return nlohmann::json::parse(out);
}

std::vector<uint8_t> contentKeyFromSpade(const std::string& spade) {
    if (spade.empty() || spade.size() > 1024) throw std::runtime_error("媒体密钥编码无效");
    std::vector<uint8_t> raw;
    try {
        raw = base64Decode(spade);
    } catch (...) {
        throw std::runtime_error("媒体密钥编码无效");
    }
    if (raw.size() < 3) throw std::runtime_error("媒体密钥编码无效");
    const int tagLength = (raw[0] ^ raw[1] ^ raw[2]) - 48;
    const int n = static_cast<int>(raw.size()) - tagLength - 1;
    if (tagLength < 1 || n < 33 || n >= static_cast<int>(raw.size())) throw std::runtime_error("媒体密钥结构无效");
    const uint8_t seed = raw[raw.size() - tagLength - 2] ^ raw[raw.size() - tagLength - 1];
    std::string tag;
    for (size_t k = raw.size() - tagLength; k < raw.size(); ++k) tag += static_cast<char>(raw[k] ^ seed);
    if (tag == "app_v2" || tag == "web_v2") throw std::runtime_error("媒体密钥版本暂不支持");
    std::string decoded;
    decoded.resize(n);
    int even = 250, odd = 85;
    for (int i = 0; i < n; ++i) {
        const int c = raw[i + 1];
        const int previous = (i % 2) ? odd : even;
        if (i % 2) odd = c; else even = c;
        int pop = 0;
        for (int b = i; b; b >>= 1) pop += b & 1;
        decoded[i] = static_cast<char>(((previous ^ c) - 21 - pop) & 255);
    }
    // Validation mirrors the JS: first char is a base36 length digit and the
    // next 32 chars are the hex key.
    auto base36 = [](char ch) -> int {
        if (ch >= '0' && ch <= '9') return ch - '0';
        if (ch >= 'a' && ch <= 'z') return ch - 'a' + 10;
        if (ch >= 'A' && ch <= 'Z') return ch - 'A' + 10;
        return -1;
    };
    const int first = decoded.empty() ? -1 : base36(decoded[0]);
    bool hexOk = n >= 33;
    for (int i = 1; i < 33 && hexOk; ++i) {
        const char ch = decoded[i];
        hexOk = (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F');
    }
    if (first < 0 || n - first - 1 != 32 || !hexOk) throw std::runtime_error("媒体密钥内容无效");
    return hexDecode(decoded.substr(1, 32));
}

nlohmann::json decodeFallbackBody(const std::string& body) {
    const std::string text = trim(body);
    if (text.rfind("v2.", 0) != 0) return parseJsonProtected(text);
    const size_t firstDot = text.find('.');
    const size_t secondDot = text.find('.', firstDot + 1);
    if (firstDot == std::string::npos || secondDot == std::string::npos ||
        text.find('.', secondDot + 1) != std::string::npos) {
        throw std::runtime_error("备用接口响应密钥无效");
    }
    const std::string part = text.substr(firstDot + 1, secondDot - firstDot - 1);
    const std::string cipherPart = text.substr(secondDot + 1);
    bool hexOk = part.size() > 4;
    for (size_t i = 4; i < part.size() && hexOk; ++i) {
        const char ch = part[i];
        hexOk = (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F');
    }
    if (part.size() <= 4 || part.size() > 1028 || !hexOk || part.size() % 2) {
        throw std::runtime_error("备用接口响应密钥无效");
    }
    const auto encoded = hexDecode(part.substr(4));
    if (encoded.size() < 32) throw std::runtime_error("备用接口响应密钥无效");
    constexpr uint8_t kMask[20] = {104, 64, 70, 166, 190, 168, 143, 130, 225, 254,
                                   251, 217, 196, 34, 45, 60, 29, 20, 103, 105};
    std::vector<uint8_t> material(encoded.size());
    for (size_t i = 0; i < encoded.size(); ++i) {
        const int slot = static_cast<int>(i % 20);
        const int x = (encoded[i] + 215 - 11 * static_cast<int>(i)) & 255;
        material[i] = static_cast<uint8_t>((i ? encoded[i - 1] : 109) ^ kMask[slot] ^
                                           ((90 + 13 * slot) & 255) ^ 85 ^ (((x << 3) | (x >> 5)) & 255));
    }
    std::vector<uint8_t> ciphertext;
    try {
        ciphertext = base64Decode(cipherPart);
    } catch (...) {
        throw std::runtime_error("备用接口加密响应无效");
    }
    if (ciphertext.empty() || ciphertext.size() % 16) throw std::runtime_error("备用接口加密响应无效");
    auto plain = aes128CbcDecrypt(material.data(), material.data() + 16, ciphertext.data(),
                                  ciphertext.size());
    if (plain.empty()) throw std::runtime_error("备用接口加密响应无效");
    const uint8_t pad = plain.back();
    if (pad < 1 || pad > 16 || pad > plain.size()) throw std::runtime_error("备用接口加密响应无效");
    plain.resize(plain.size() - pad);
    return parseJsonProtected(std::string(plain.begin(), plain.end()));
}

std::string decryptSpadeUrl(const std::string& encoded, const std::vector<uint8_t>& seed) {
    if (encoded.empty()) return "";
    std::vector<uint8_t> raw;
    try {
        raw = base64Decode(encoded);
    } catch (...) {
        throw std::runtime_error("spade URL 解密失败");
    }
    if (raw.size() < 5) throw std::runtime_error("spade URL ciphertext too short");
    if (raw[0] != 0xa8 || raw[2] != 0x01 || raw[3] != 0x00) throw std::runtime_error("spade URL header format error");
    const size_t cipherLength = (raw.size() - 4) / 16 * 16;
    if (!cipherLength) throw std::runtime_error("spade URL ciphertext has no complete block");
    static const auto kConstants = hexDecode(
        "4dd4c2e6b83162090e52b3c7a6733ba41cb2462b829ab58a196b39db57177524f49baf7f08e8d68d26a72e37"
        "c1a95a2f1f05a51892aef2949732b62a38aadd58");
    const auto h1 = sha512(seed.data(), seed.size());
    std::vector<uint8_t> joined = h1;
    joined.insert(joined.end(), kConstants.begin(), kConstants.end());
    const auto h2 = sha512(joined.data(), joined.size());
    auto plain = aes128CbcDecrypt(h2.data(), h2.data() + 16, raw.data() + 4, cipherLength);
    if (!plain.empty()) {
        const uint8_t padding = plain.back();
        if (padding >= 1 && padding <= 16 && padding <= plain.size()) plain.resize(plain.size() - padding);
    }
    while (!plain.empty() && plain.back() == 0) plain.pop_back();
    return std::string(plain.begin(), plain.end());
}

namespace {

void collectAddresses(const nlohmann::json& value, std::vector<std::string>& out) {
    if (value.is_array()) {
        for (const auto& item : value) collectAddresses(item, out);
        return;
    }
    if (!value.is_string()) return;
    std::string url = trim(value.get<std::string>());
    if (!httpUrl(url)) {
        try {
            const auto decoded = base64Decode(url);
            url = trim(std::string(decoded.begin(), decoded.end()));
        } catch (...) {
            return;
        }
    }
    if (httpUrl(url) && std::find(out.begin(), out.end(), url) == out.end()) out.push_back(url);
}

bool containsCi(const std::string& haystack, const std::string& needle) {
    std::string h = haystack, n = needle;
    std::transform(h.begin(), h.end(), h.begin(), [](unsigned char c) { return std::tolower(c); });
    std::transform(n.begin(), n.end(), n.begin(), [](unsigned char c) { return std::tolower(c); });
    return h.find(n) != std::string::npos;
}

}  // namespace

std::vector<StreamRow> rowsFromModel(const nlohmann::json& model) {
    std::vector<StreamRow> rows;
    const auto list = model.value("video_list", nlohmann::json());
    std::vector<const nlohmann::json*> entries;
    if (list.is_array()) {
        for (const auto& item : list) entries.push_back(&item);
    } else if (list.is_object()) {
        for (auto it = list.begin(); it != list.end(); ++it) entries.push_back(&it.value());
    }
    std::vector<uint8_t> seed;
    if (model.contains("key_seed") && model["key_seed"].is_string()) {
        try {
            seed = base64Decode(model["key_seed"].get<std::string>());
        } catch (...) {
            seed.clear();
        }
    }
    for (const nlohmann::json* rowPtr : entries) {
        const auto& row = *rowPtr;
        if (!row.is_object()) continue;
        const auto meta = row.value("video_meta", nlohmann::json::object());
        std::string codec = jsonText(meta.value("codec_type", nlohmann::json()));
        if (codec.empty()) codec = jsonText(row.value("codec_type", nlohmann::json()));
        if (containsCi(codec, "bytevc2") || containsCi(codec, "bvc2") ||
            containsCi(jsonText(row.value("gear_des_key", nlohmann::json())), "bytevc2") ||
            containsCi(jsonText(row.value("gear_des_key", nlohmann::json())), "bvc2")) {
            continue;
        }
        std::vector<std::string> urls;
        for (const char* field : {"main_url", "backup_url", "backup_url_1", "backup_url_2",
                                  "backup_urls", "url_list"}) {
            if (row.contains(field)) collectAddresses(row[field], urls);
        }
        if (urls.empty() && !seed.empty()) {
            for (const char* field : {"main_url", "backup_url"}) {
                const std::string encoded = jsonText(row.value(field, nlohmann::json()));
                if (encoded.empty()) continue;
                try {
                    const std::string decoded = decryptSpadeUrl(encoded, seed);
                    if (httpUrl(decoded) &&
                        std::find(urls.begin(), urls.end(), decoded) == urls.end()) {
                        urls.push_back(decoded);
                    }
                } catch (...) {
                }
            }
        }
        if (urls.empty()) continue;
        const auto encryption = row.value("encrypt_info", nlohmann::json::object());
        std::string spade = jsonText(row.value("spade_a", nlohmann::json()));
        if (spade.empty()) spade = jsonText(encryption.value("spade_a", nlohmann::json()));
        std::vector<uint8_t> key;
        const bool needsKey = !spade.empty() || jsonTruthy(row.value("encrypt", nlohmann::json())) ||
                              jsonTruthy(encryption.value("encrypt", nlohmann::json())) ||
                              jsonText(encryption.value("encryption_method", nlohmann::json())) ==
                                  "cenc-aes-ctr";
        if (needsKey) {
            try {
                key = contentKeyFromSpade(spade);
            } catch (...) {
                continue;  // JS drops the row when the key cannot be derived
            }
        }
        int quality = qualityNumber(jsonText(meta.value("definition", nlohmann::json())));
        if (!quality) quality = qualityNumber(jsonText(row.value("definition", nlohmann::json())));
        if (!quality) quality = qualityNumber(jsonText(row.value("quality_desc", nlohmann::json())));
        if (!quality) quality = qualityNumber(jsonText(row.value("quality", nlohmann::json())));
        if (!quality) {
            // JS: dims = [vheight chain, vwidth chain].filter(>0); quality = min(...dims)
            std::vector<int> both;
            const double h = [&]() {
                for (double d : {jsonNumber(meta.value("vheight", nlohmann::json())),
                                 jsonNumber(row.value("vheight", nlohmann::json())),
                                 jsonNumber(row.value("height", nlohmann::json()))})
                    if (d > 0) return d;
                return 0.0;
            }();
            const double w = [&]() {
                for (double d : {jsonNumber(meta.value("vwidth", nlohmann::json())),
                                 jsonNumber(row.value("vwidth", nlohmann::json())),
                                 jsonNumber(row.value("width", nlohmann::json()))})
                    if (d > 0) return d;
                return 0.0;
            }();
            if (h > 0) both.push_back(static_cast<int>(h));
            if (w > 0) both.push_back(static_cast<int>(w));
            if (!both.empty()) quality = *std::min_element(both.begin(), both.end());
        }
        StreamRow out;
        out.url = urls[0];
        out.backupUrl = urls.size() > 1 ? urls[1] : "";
        out.codec = codec;
        out.quality = quality;
        out.size = static_cast<long long>(jsonNumber(meta.value("size", nlohmann::json())));
        out.key = key;
        rows.push_back(out);
    }
    if (rows.empty()) throw std::runtime_error("新版 App 未返回兼容片源");
    std::stable_sort(rows.begin(), rows.end(),
                     [](const StreamRow& a, const StreamRow& b) { return a.quality > b.quality; });
    return rows;
}

}  // namespace guo

namespace guo {

HongguoAppClient::HongguoAppClient(FetchHttp fetch, FetchText fetchText)
    : fetch_(std::move(fetch)), fetchText_(std::move(fetchText)) {}

void HongguoAppClient::ensureIdentity() {
    if (!deviceId_.empty() && !installId_.empty()) return;
    if (identityInFlight_) throw std::runtime_error("红果设备注册进行中");
    identityInFlight_ = true;
    try {
        const int64_t now = nowMs();
        nlohmann::ordered_json header;
        header["display_name"] = "红果免费短剧";
        header["aid"] = 8662;
        header["app_version"] = "7.3.9.32";
        header["version_code"] = 73932;
        header["manifest_version_code"] = 73932;
        header["update_version_code"] = 73932;
        header["channel"] = "update_64";
        header["package"] = "com.phoenix.read";
        header["app_language"] = "zh";
        header["os"] = "Android";
        header["os_version"] = "16";
        header["os_api"] = 36;
        header["device_model"] = "25053RT47C";
        header["device_brand"] = "Redmi";
        header["device_manufacturer"] = "Xiaomi";
        header["cpu_abi"] = "arm64-v8a";
        header["density_dpi"] = 520;
        header["display_density"] = "xxhdpi";
        header["resolution"] = "2772x1280";
        header["language"] = "zh";
        header["timezone"] = 8;
        header["access"] = "wifi";
        header["openudid"] = randomHexBytes(8);
        header["clientudid"] = makeUuid();
        header["cdid"] = makeUuid();
        header["region"] = "CN";
        header["tz_name"] = "Asia/Shanghai";
        header["tz_offset"] = 28800;
        header["sim_region"] = "cn";
        header["device_platform"] = "android";
        header["not_request_sender"] = 0;
        header["is_system_app"] = 0;
        header["sdk_flavor"] = "china";
        header["rom"] = "OS2.0";
        header["rom_version"] = "BP2A.250605.031.A3";
        auto values = deviceProfile();
        values.emplace_back("req_id", makeUuid());
        values.emplace_back("_rticket", std::to_string(now));
        const std::string query = sortedQuery(values);
        nlohmann::ordered_json body;
        body["magic_tag"] = "ss_app_log";
        body["header"] = header;
        body["_gen_time"] = now;
        Request request;
        request.method = "POST";
        request.url = "https://log.snssdk.com/service/2/device_register/?" + query;
        request.headers = {std::string("User-Agent: ") + kUserAgent, "Accept: application/json",
                           "Content-Type: application/json; charset=utf-8"};
        request.body = body.dump();
        const Response response = fetch_(request);
        if (response.body.size() > 65536) throw std::runtime_error("红果设备响应过大");
        const auto data = parseJsonProtected(response.body);
        const std::string device = jsonText(data.value("device_id_str", nlohmann::json())).empty()
                                       ? jsonText(data.value("device_id", nlohmann::json()))
                                       : jsonText(data["device_id_str"]);
        const std::string install = jsonText(data.value("install_id_str", nlohmann::json())).empty()
                                        ? jsonText(data.value("install_id", nlohmann::json()))
                                        : jsonText(data["install_id_str"]);
        if (!validId(device) || !validId(install) || device == "0" || install == "0") {
            throw std::runtime_error("红果设备注册未返回有效设备号");
        }
        deviceId_ = device;
        installId_ = install;
    } catch (...) {
        identityInFlight_ = false;
        throw;
    }
    identityInFlight_ = false;
}

nlohmann::json HongguoAppClient::appRequest(const std::string& path,
                                            const nlohmann::ordered_json& payload) {
    ensureIdentity();
    const std::string body = payload.dump();
    for (int attempt = 0; attempt < 3; ++attempt) {
        const int64_t now = nowMs() + (attempt ? attempt * 37 : 0);
        auto values = deviceProfile();
        values.emplace_back("device_id", deviceId_);
        values.emplace_back("iid", installId_);
        values.emplace_back("_rticket", std::to_string(now));
        const std::string query = sortedQuery(values);
        auto signatures = signRequest(query, body, now);
        if (path == "/novel/player/multi_video_model/v1/") {
            const int64_t seconds = now / 1000;
            signatures["X-Gorgon"] = xGorgon(query, body, seconds, 12345);
            uint8_t buffer[4];
            buffer[0] = static_cast<uint8_t>(seconds);
            buffer[1] = static_cast<uint8_t>(seconds >> 8);
            buffer[2] = static_cast<uint8_t>(seconds >> 16);
            buffer[3] = static_cast<uint8_t>(seconds >> 24);
            signatures["X-Argus"] = base64Encode(buffer, 4);
            buffer[0] = static_cast<uint8_t>(seconds >> 24);
            buffer[1] = static_cast<uint8_t>(seconds >> 16);
            buffer[2] = static_cast<uint8_t>(seconds >> 8);
            buffer[3] = static_cast<uint8_t>(seconds);
            signatures["X-Ladon"] = base64Encode(buffer, 4);
        }
        Request request;
        request.method = "POST";
        request.url = "https://api5-normal-sinfonlineb.fqnovel.com" + path + "?" + query;
        request.headers = {std::string("User-Agent: ") + kUserAgent, "Accept: application/json",
                           "X-XS-From-Web: 0", "Sdk-Version: 2",
                           "Content-Type: application/json; charset=utf-8"};
        for (const auto& [name, value] : signatures) request.headers.push_back(name + ": " + value);
        Response response;
        try {
            response = fetch_(request);
        } catch (const std::runtime_error&) {
            if (attempt < 2) continue;
            throw;
        }
        if (trim(response.body).empty()) {
            if (attempt < 2) continue;
            throw std::runtime_error("红果 App 接口返回空响应，请稍后重新取流");
        }
        const auto data = parseJsonProtected(response.body);
        double code = 0;
        bool haveCode = false;
        for (const char* field : {"code", "Code", "status_code"}) {
            if (data.contains(field) && data[field].is_number()) {
                code = data[field].get<double>();
                haveCode = true;
                break;
            }
        }
        if (!haveCode && data.contains("BaseResp") && data["BaseResp"].is_object() &&
            data["BaseResp"].contains("StatusCode")) {
            code = jsonNumber(data["BaseResp"]["StatusCode"]);
        }
        if (response.code != 200 || code != 0) {
            if (attempt < 2) continue;
            throw std::runtime_error("红果 App 接口暂不可用（" + std::to_string(static_cast<long long>(code)) + "）");
        }
        return data;
    }
    throw std::runtime_error("红果 App 接口暂不可用");
}

StreamSet HongguoAppClient::videoInfo(const std::string& episodeId) {
    if (!validId(episodeId)) throw std::runtime_error("分集 ID 无效");
    const auto hit = cache_.find(episodeId);
    if (hit != cache_.end() && nowMs() - hit->second.timeMs < 1800000) return hit->second.value;

    nlohmann::ordered_json biz;
    biz["detail_page_version"] = 0;
    biz["device_level"] = 1;
    biz["disable_digg_stat"] = false;
    biz["need_all_video_definition"] = true;
    biz["need_mp4_align"] = false;
    biz["use_os_player"] = false;
    biz["use_server_dns"] = false;
    biz["video_platform"] = 1024;
    nlohmann::ordered_json payload;
    payload["biz_param"] = biz;
    payload["mixed_video_id_map"] = nlohmann::ordered_json{{"1004", nlohmann::json::array({episodeId})}};
    const auto data = appRequest("/novel/player/multi_video_model/v1/", payload);

    const auto root = data.value("data", nlohmann::json::object());
    const auto entry = root.value(episodeId, nlohmann::json());
    nlohmann::json raw;
    if (entry.is_object() && entry.contains("video_model")) raw = entry["video_model"];
    else if (root.contains("video_model")) raw = root["video_model"];
    nlohmann::json model = raw.is_string() ? parseJsonProtected(raw.get<std::string>()) : raw;
    if (!model.is_object()) throw std::runtime_error("红果 App 未返回所选分集的播放模型");
    if (!entry.is_object() && !root.contains("video_model")) throw std::runtime_error("红果 App 返回了其他分集");

    if (!model.contains("video_list") && model.contains("fallback_api")) {
        nlohmann::json fallback = model["fallback_api"];
        for (int depth = 0; depth < 4 && !fallback.is_string(); ++depth) {
            if (fallback.is_array() && !fallback.empty()) fallback = fallback[0];
            else if (fallback.is_object()) fallback = fallback.value("fallback_api", nlohmann::json());
            else break;
        }
        if (fallback.is_string()) {
            std::string url = fallback.get<std::string>();
            if (url.rfind("https://", 0) != 0) {
                try {
                    url = jsonText(parseJsonProtected(url).value("fallback_api", nlohmann::json()));
                } catch (...) {
                    url.clear();
                }
            }
            static const std::regex kFallbackHost(
                R"(^https://[^\s/@]+(\.fqnovel\.com|\.snssdk\.com)(:443)?/)",
                std::regex_constants::icase);
            if (!std::regex_search(url, kFallbackHost)) throw std::runtime_error("红果播放模型地址无效");
            Request fallbackRequest;
            fallbackRequest.url = url;
            fallbackRequest.headers = {std::string("User-Agent: ") + kUserAgent};
            const std::string text = fetchText_(fallbackRequest);
            const auto outer = decodeFallbackBody(text);
            nlohmann::json extra;
            if (outer.contains("video_info") && outer["video_info"].is_object() &&
                outer["video_info"].contains("data")) {
                extra = outer["video_info"]["data"];
            } else if (outer.contains("data")) {
                extra = outer["data"];
            }
            if (extra.is_object()) {
                for (auto it = extra.begin(); it != extra.end(); ++it) model[it.key()] = it.value();
            }
        }
    }

    StreamSet value{rowsFromModel(model)};
    cache_[episodeId] = CacheEntry{nowMs(), value};
    cacheOrder_.push_back(episodeId);
    while (cache_.size() > 8) {
        cache_.erase(cacheOrder_.front());
        cacheOrder_.pop_front();
    }
    return value;
}

StreamSet HongguoAppClient::backup(const std::string& seriesId, const std::string& episodeId) {
    if (!validId(seriesId) || !validId(episodeId)) throw std::runtime_error("备用取流缺少剧集 ID");
    nlohmann::ordered_json reference;
    reference["content_type"] = 1004;
    reference["from_video_id"] = "";
    reference["series_id"] = seriesId;
    reference["vid"] = episodeId;
    reference["video_platform"] = 3;
    const std::string encoded = base64Encode(
        reinterpret_cast<const uint8_t*>(reference.dump().data()), reference.dump().size());
    // URLSearchParams form encoding for the id parameter.
    std::string idParam;
    for (unsigned char c : encoded) {
        const bool safe = (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') ||
                          (c >= '0' && c <= '9') || c == '*' || c == '-' || c == '.' || c == '_';
        if (safe) idParam += static_cast<char>(c);
        else {
            char buf[4];
            std::snprintf(buf, sizeof(buf), "%%%02X", c);
            idParam += buf;
        }
    }
    Request backupRequest;
    backupRequest.url = "https://djapi.999888456.xyz/api/hongguo/play?id=" + idParam;
    backupRequest.headers = {"User-Agent: Mozilla/5.0", "Referer: https://hongguoduanju.com/"};
    const std::string text = fetchText_(backupRequest);
    const auto data = decodeFallbackBody(text);
    for (const char* flag : {"parse", "jx"}) {
        if (!data.contains(flag)) continue;
        const auto& value = data[flag];
        const bool excluded = value.is_null() || (value.is_boolean() && !value.get<bool>()) ||
                              (value.is_number() && value.get<double>() == 0) ||
                              (value.is_string() &&
                               (value.get<std::string>() == "0" || value.get<std::string>().empty()));
        if (!excluded) throw std::runtime_error("备用接口未返回直接媒体");
    }
    std::vector<StreamRow> rows;
    const auto list = data.value("key_urls", nlohmann::json::array());
    if (list.is_array()) {
        static const std::regex kKid(R"(^[0-9a-fA-F]{32}$)");
        for (const auto& row : list) {
            const std::string src = jsonText(row.value("src", nlohmann::json()));
            const std::string kid = jsonText(row.value("kid", nlohmann::json()));
            if (!httpUrl(src) || !std::regex_match(kid, kKid)) continue;
            try {
                StreamRow out;
                out.url = src;
                out.quality = qualityNumber(jsonText(row.value("name", nlohmann::json())));
                out.key = contentKeyFromSpade(jsonText(row.value("spade_a", nlohmann::json())));
                rows.push_back(out);
            } catch (...) {
            }
        }
    }
    if (rows.empty()) throw std::runtime_error("备用接口没有可用片源");
    std::stable_sort(rows.begin(), rows.end(),
                     [](const StreamRow& a, const StreamRow& b) { return a.quality > b.quality; });
    return StreamSet{rows};
}

void HongguoAppClient::clearCache() {
    cache_.clear();
    cacheOrder_.clear();
}

}  // namespace guo
