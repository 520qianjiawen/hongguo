#include "guo/provider.hpp"

#include <algorithm>
#include <cctype>
#include <cstdio>
#include <cstring>
#include <random>
#include <regex>
#include <set>
#include <stdexcept>
#include <nlohmann/json.hpp>

namespace guo {
namespace {
using Json = nlohmann::json;
const std::string base = "https://hongguoduanju.com";
const std::string huangjuBase = "https://api.huangju.net";
const std::string huangjuSite = "https://huangju.net";

void checkCancelled(const Cancellation& cancelled) {
    if (cancelled && cancelled->load()) throw std::runtime_error("请求已取消");
}

bool validId(const std::string& value) {
    return !value.empty() && value.size() <= 32 &&
           std::all_of(value.begin(), value.end(), [](unsigned char c) { return c >= '0' && c <= '9'; });
}

std::string text(const Json& object, std::initializer_list<const char*> keys) {
    if (!object.is_object()) return {};
    for (const auto* key : keys) {
        const auto it = object.find(key);
        if (it == object.end()) continue;
        if (it->is_string() && !it->get_ref<const std::string&>().empty()) return it->get<std::string>();
        if (it->is_number_integer() || it->is_number_unsigned()) return it->dump();
    }
    return {};
}

Json routerData(const std::string& body) {
    if (body.size() > 4 * 1024 * 1024) throw std::runtime_error("站点响应过大");
    static const std::regex marker(R"((?:window\.)?_ROUTER_DATA\s*=\s*)");
    std::smatch match;
    if (!std::regex_search(body, match, marker)) throw std::runtime_error("未找到站点数据，请重试");
    const size_t start = match.position() + match.length();
    if (start >= body.size() || body[start] != '{') throw std::runtime_error("站点数据格式已变化");
    size_t depth = 0;
    bool inString = false, escaped = false;
    for (size_t i = start; i < body.size(); ++i) {
        const char c = body[i];
        if (inString) {
            if (escaped) escaped = false;
            else if (c == '\\') escaped = true;
            else if (c == '"') inString = false;
        } else if (c == '"') inString = true;
        else if (c == '{' || c == '[') {
            if (++depth > 128) throw std::runtime_error("站点数据嵌套过深");
        } else if (c == '}' || c == ']') {
            if (--depth == 0) {
                try {
                    return Json::parse(body.substr(start, i - start + 1));
                } catch (const Json::exception&) {
                    throw std::runtime_error("站点数据格式无效");
                }
            }
        }
    }
    throw std::runtime_error("站点数据不完整");
}

Json loader(const std::string& body, std::initializer_list<const char*> names) {
    const auto data = routerData(body);
    const auto entries = data.find("loaderData");
    if (entries == data.end() || !entries->is_object()) throw std::runtime_error("站点未返回页面内容");
    for (const auto* name : names) {
        const auto it = entries->find(name);
        if (it != entries->end() && it->is_object()) return *it;
    }
    for (const auto& entry : entries->items()) {
        for (const auto* name : names) {
            std::string prefix(name);
            if (!prefix.empty() && prefix.back() == '$') prefix.pop_back();
            if (entry.key().compare(0, prefix.size(), prefix) == 0 && entry.value().is_object()) return entry.value();
        }
    }
    throw std::runtime_error("站点页面结构已变化");
}

Json rows(const Json& value) {
    if (value.is_array()) return value;
    if (value.is_object()) {
        for (const auto* key : {"list", "items", "data"}) {
            const auto it = value.find(key);
            if (it != value.end() && it->is_array()) return *it;
        }
    }
    throw std::runtime_error("站点列表格式无效");
}

Drama parseDrama(const Json& row, const std::string& category) {
    if (!row.is_object()) return {};
    const Json& video = row.contains("video_data") && row["video_data"].is_object() ? row["video_data"] : row;
    Drama result;
    result.id = text(video, {"series_id_str", "series_id"});
    if (result.id.empty()) result.id = text(row, {"series_id_str", "series_id"});
    if (!validId(result.id)) return {};
    result.title = text(video, {"series_title", "series_name", "title"});
    if (result.title.empty()) result.title = text(row, {"series_name", "name"});
    if (result.title.empty()) result.title = result.id;
    result.intro = text(video, {"series_intro", "video_desc"});
    result.cover = text(video, {"series_cover", "cover"});
    result.category = category;
    result.remark = text(video, {"episode_right_text"});
    result.source = "hongguo";
    const auto count = text(video, {"episode_cnt"});
    if (result.remark.empty() && !count.empty()) result.remark = "共" + count + "集";
    return result;
}

int nonnegativeInt(const Json& object, const char* key) {
    const auto value = text(object, {key});
    try {
        size_t end = 0;
        int number = std::stoi(value, &end);
        return number >= 0 && end == value.size() ? number : -1;
    } catch (...) {
        return -1;
    }
}

Drama parseHuangjuDrama(const Json& item) {
    if (!item.is_object()) return {};
    Drama drama;
    std::string slug = text(item, {"slug"});
    std::string idStr = text(item, {"id"});
    if (idStr.empty()) return {};
    drama.id = slug.empty() ? idStr : (slug + "-" + idStr);
    drama.title = text(item, {"title"});
    if (drama.title.empty()) drama.title = drama.id;
    drama.intro = text(item, {"description"});
    drama.cover = text(item, {"coverUrl"});
    drama.source = "huangju";
    if (item.contains("category") && item["category"].is_object()) {
        drama.category = text(item["category"], {"name"});
    }
    if (drama.category.empty()) drama.category = "短剧";
    int totalEp = nonnegativeInt(item, "totalEpisodes");
    if (totalEp > 0) drama.remark = "共" + std::to_string(totalEp) + "集";
    else drama.remark = drama.category;
    return drama;
}

std::vector<Drama> dramas(const Json& list, const std::string& category) {
    std::vector<Drama> result;
    std::set<std::string> seen;
    const auto records = rows(list);
    for (const auto& row : records) {
        auto drama = parseDrama(row, category);
        if (!drama.id.empty() && seen.insert(drama.id).second) result.push_back(std::move(drama));
    }
    if (!records.empty() && result.empty()) throw std::runtime_error("没有可识别的剧集，请重试");
    return result;
}

std::string decodeBase64(std::string value) {
    const std::string alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    if (value.empty() || value.size() > 8192 || value.size() % 4 == 1) return {};
    const auto padding = value.find('=');
    if (padding != std::string::npos) {
        if (value.size() % 4 != 0 || value.size() - padding > 2 ||
            value.find_first_not_of('=', padding) != std::string::npos) return {};
        value.resize(padding);
    }
    unsigned int bits = 0;
    int count = 0;
    std::string output;
    for (unsigned char c : value) {
        const auto index = alphabet.find(c);
        if (index == std::string::npos) return {};
        bits = (bits << 6) | static_cast<unsigned int>(index);
        count += 6;
        if (count >= 8) {
            count -= 8;
            output += static_cast<char>((bits >> count) & 255);
        }
    }
    if (count != 0 && (bits & ((1u << count) - 1)) != 0) return {};
    return output;
}

bool encrypted(const Json& info) {
    if (!info.is_object()) return true;
    for (const auto* key : {"spade_a", "kid", "key_id"}) {
        if (!text(info, {key}).empty()) return true;
    }
    if (info.contains("encrypt")) {
        const auto& flag = info["encrypt"];
        if (!flag.is_null() && flag != false && flag != 0 && flag != "0" && flag != "") return true;
    }
    auto method = text(info, {"encryption_method"});
    if (!method.empty() && method != "none") return true;
    return info.contains("encrypt_info") && encrypted(info["encrypt_info"]);
}
}

bool isHttpUrl(const std::string& value) {
    if (value.size() > 8192 || value.find_first_of("\r\n\t ") != std::string::npos ||
        std::any_of(value.begin(), value.end(), [](unsigned char c) { return c < 32 || c == 127; })) return false;
    size_t start = value.compare(0, 8, "https://") == 0 ? 8 : value.compare(0, 7, "http://") == 0 ? 7 : 0;
    if (start == 0) return false;
    const auto end = value.find_first_of("/?#", start);
    const auto authority = value.substr(start, end - start);
    return !authority.empty() && authority.find('@') == std::string::npos && authority.front() != ':';
}

std::string encodePath(const std::string& value) {
    static const char* hex = "0123456789ABCDEF";
    std::string result;
    for (unsigned char c : value) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') ||
            c == '-' || c == '_' || c == '.' || c == '~') result += static_cast<char>(c);
        else {
            result += '%';
            result += hex[c >> 4];
            result += hex[c & 15];
        }
    }
    return result;
}

HongguoProvider::HongguoProvider(FetchText fetch) : fetch(std::move(fetch)) {
    if (!this->fetch) throw std::invalid_argument("未配置网络客户端");
}

std::vector<Category> HongguoProvider::categories() const {
    return {{"real-drama", "真人剧"}, {"comic-drama", "漫剧"}, {"ai-drama", "AI 剧"}, {"comic", "动漫"}};
}

CatalogPage HongguoProvider::catalog(const std::string& category, int page, const Cancellation& cancelled) const {
    const auto options = categories();
    auto selected = std::find_if(options.begin(), options.end(), [&](const auto& c) { return c.id == category; });
    if (selected == options.end() || page < 1 || page > 500) throw std::invalid_argument("分类或页码无效");
    checkCancelled(cancelled);
    const auto data = loader(fetch({base + "/category/" + category + "?page=" + std::to_string(page), base + "/", cancelled}),
                             {"category_page", "category_$"});
    checkCancelled(cancelled);
    if (data.contains("isSuccess") && data["isSuccess"] == false) throw std::runtime_error("分类暂不可用，请重试");
    CatalogPage result;
    result.items = dramas(data.value("recommendList", Json()), selected->title);
    const auto pagination = data.value("pagination", Json::object());
    const int totalPages = nonnegativeInt(pagination, "totalPages");
    result.hasMore = !result.items.empty() && page < 500 && (totalPages >= 0 ? page < totalPages : result.items.size() >= 24);
    return result;
}

CatalogPage HongguoProvider::search(const std::string& keyword, const Cancellation& cancelled) const {
    if (keyword.empty() || keyword.size() > 320 ||
        std::any_of(keyword.begin(), keyword.end(), [](unsigned char c) { return c < 32 || c == 127; }))
        throw std::invalid_argument("请输入有效的搜索词");
    checkCancelled(cancelled);
    try {
        const auto data = loader(fetch({base + "/search/" + encodePath(keyword), base + "/", cancelled}),
                                 {"search_(keyword)/page", "search_"});
        checkCancelled(cancelled);
        if (!data.contains("isSuccess") || data["isSuccess"] != true || text(data, {"query"}) != keyword)
            throw std::runtime_error("站点未返回所搜索的内容，请重试");
        auto items = dramas(data.value("searchList", Json()), "短剧");
        const auto total = nonnegativeInt(data, "totalCount");
        return {items, false, total > static_cast<int>(items.size()) ? "官网仅返回部分搜索结果" : ""};
    } catch (const std::exception&) {
        checkCancelled(cancelled);
        const auto body = fetch({base + "/incent_resource/suggestion?app_id=8662&count=50&query=" + encodePath(keyword), base + "/", cancelled});
        checkCancelled(cancelled);
        if (body.size() > 256 * 1024) throw std::runtime_error("搜索响应过大");
        Json data;
        try { data = Json::parse(body); }
        catch (const Json::exception&) { throw std::runtime_error("搜索服务暂不可用，请重试"); }
        if (data.is_object() && !data.contains("suggest_list") && data.contains("data")) data = data["data"];
        if (!data.is_object() || !data.contains("suggest_list") || !data["suggest_list"].is_array())
            throw std::runtime_error("搜索服务未返回有效数据");
        Json matches = Json::array();
        for (const auto& row : data["suggest_list"]) {
            if (text(row, {"word_type"}) == "short_play_name") matches.push_back(row);
        }
        return {dramas(matches, "名称匹配"), false, "名称匹配结果，可能不完整"};
    }
}

Detail HongguoProvider::detail(const std::string& id, const Cancellation& cancelled) const {
    if (!validId(id)) throw std::invalid_argument("剧集 ID 无效");
    checkCancelled(cancelled);
    const auto data = loader(fetch({base + "/detail?series_id=" + id, base + "/", cancelled}), {"detail_page", "detail_"});
    checkCancelled(cancelled);
    const auto series = data.value("seriesDetail", Json::object());
    Detail result;
    result.drama = parseDrama(series, "短剧");
    if (result.drama.id != id) throw std::runtime_error("站点未返回所请求的剧集");
    const auto vids = rows(series.value("vid_list", Json()));
    std::set<std::string> seen;
    for (const auto& item : vids) {
        const std::string vid = item.is_string() ? item.get<std::string>() : item.is_number_integer() ? item.dump() : "";
        if (!validId(vid) || !seen.insert(vid).second) throw std::runtime_error("站点分集列表无效，请重试");
        const int index = static_cast<int>(result.episodes.size()) + 1;
        result.episodes.push_back({vid, "第" + std::to_string(index) + "集", index});
    }
    if (result.episodes.empty()) throw std::runtime_error("该剧暂未提供分集");
    return result;
}

Media HongguoProvider::resolve(const std::string& dramaId, const Episode& episode, const Cancellation& cancelled) const {
    if (!validId(dramaId) || !validId(episode.id)) throw std::invalid_argument("分集 ID 无效");
    checkCancelled(cancelled);
    const auto data = loader(fetch({base + "/player/" + dramaId + "/" + episode.id, base + "/", cancelled}),
                             {"player_", "player_page"});
    checkCancelled(cancelled);
    if (text(data, {"vid"}) != episode.id || text(data, {"series_id"}) != dramaId)
        throw std::runtime_error("站点只返回了试看集，无法播放所选分集");
    const auto info = data.value("video_player_info", Json::object());
    if (encrypted(info)) throw std::runtime_error("该集需要 App 取流，当前版本尚未接入");
    std::vector<std::string> addresses;
    const auto add = [&](const Json& value) {
        if (!value.is_string()) return;
        auto address = value.get<std::string>();
        if (!isHttpUrl(address)) address = decodeBase64(address);
        if (isHttpUrl(address) && std::find(addresses.begin(), addresses.end(), address) == addresses.end())
            addresses.push_back(std::move(address));
    };
    for (const auto* key : {"main_url", "backup_url", "backup_url_1", "backup_url_2", "backup_urls", "url_list"}) {
        if (!info.is_object() || !info.contains(key)) continue;
        const auto& value = info[key];
        if (value.is_array()) for (const auto& item : value) add(item);
        else add(value);
    }
    if (addresses.empty()) throw std::runtime_error("该集没有公开播放地址，可能需要登录或 App 授权");
    Media result{addresses.front(), base + "/", {}, ""};
    result.backups.assign(addresses.begin() + 1, addresses.end());
    return result;
}

std::string extractCookies(const std::vector<std::string>& headers) {
    std::vector<std::string> cookies;
    for (const auto& line : headers) {
        if (line.size() >= 11) {
            bool matches = true;
            static const char prefix[] = "set-cookie:";
            for (size_t i = 0; i < 11; ++i) {
                if (std::tolower(static_cast<unsigned char>(line[i])) != prefix[i]) {
                    matches = false;
                    break;
                }
            }
            if (matches) {
                size_t start = 11;
                while (start < line.size() && (line[start] == ' ' || line[start] == '\t')) ++start;
                size_t end = line.find(';', start);
                if (end == std::string::npos) {
                    end = line.find_first_of("\r\n", start);
                    if (end == std::string::npos) end = line.size();
                }
                while (end > start && (line[end - 1] == ' ' || line[end - 1] == '\t' || line[end - 1] == '\r' || line[end - 1] == '\n')) --end;
                if (end > start) {
                    cookies.push_back(line.substr(start, end - start));
                }
            }
        }
    }
    std::string result;
    for (size_t i = 0; i < cookies.size(); ++i) {
        if (i > 0) result += "; ";
        result += cookies[i];
    }
    return result;
}

HuangjuProvider::HuangjuProvider(FetchHttp fetch) : fetch(std::move(fetch)) {
    if (!this->fetch) throw std::invalid_argument("未配置网络客户端");
}

std::vector<Category> HuangjuProvider::categories() const {
    return {
        {"hot", "热门"},
        {"new", "最新"},
        {"urban", "都市"},
        {"rebirth", "重生"},
        {"modern-romance", "现言"},
        {"ancient-romance", "古言"},
        {"sweet-love", "甜宠"},
        {"fantasy", "玄幻"},
        {"suspense", "悬疑"},
        {"brain-hole", "脑洞"},
    };
}

std::string HuangjuProvider::ensureGuestToken(const Cancellation& cancelled) const {
    std::lock_guard<std::mutex> lock(authMutex);
    if (!token.empty()) return token;

    if (deviceId.empty()) {
        std::random_device rd;
        std::mt19937_64 gen(rd());
        std::uniform_int_distribution<uint64_t> dis;
        uint64_t part1 = dis(gen);
        uint64_t part2 = dis(gen);
        part1 = (part1 & ~0x000000000000f000ULL) | 0x0000000000004000ULL;
        part2 = (part2 & ~0xc000000000000000ULL) | 0x8000000000000000ULL;
        char buf[37];
        snprintf(buf, sizeof(buf), "%08x-%04x-%04x-%04x-%012llx",
                 static_cast<uint32_t>(part1 >> 32),
                 static_cast<uint16_t>(part1 >> 16),
                 static_cast<uint16_t>(part1),
                 static_cast<uint16_t>(part2 >> 48),
                 static_cast<unsigned long long>(part2 & 0xffffffffffffULL));
        deviceId = buf;
    }

    std::string authUrl = huangjuBase + "/auth/guest";
    std::string body = "{\"deviceId\":\"" + deviceId + "\"}";
    Request req{authUrl, huangjuSite + "/", cancelled, "POST", body, {
        "Content-Type: application/json",
        "Origin: " + huangjuSite
    }};

    const auto resp = fetch(req);
    Json data;
    try {
        data = Json::parse(resp.body);
    } catch (...) {
        throw std::runtime_error("获取剧果访客授权返回格式错误");
    }
    if (data.is_object()) {
        if (data.contains("token") && data["token"].is_string()) {
            token = data["token"].get<std::string>();
        } else if (data.contains("data") && data["data"].is_object() && data["data"].contains("token") && data["data"]["token"].is_string()) {
            token = data["data"]["token"].get<std::string>();
        }
    }
    if (token.empty()) throw std::runtime_error("获取剧果访客授权失败，请稍后重试");
    return token;
}

CatalogPage HuangjuProvider::catalog(const std::string& category, int page, const Cancellation& cancelled) const {
    if (page < 1 || page > 10000) throw std::invalid_argument("页码无效");
    checkCancelled(cancelled);
    std::string url = huangjuBase + "/dramas?page=" + std::to_string(page);
    if (category == "hot") url += "&sort=hot";
    else if (category == "new") url += "&sort=new";
    else if (!category.empty()) url += "&category=" + encodePath(category);
    else url += "&sort=hot";

    Request req{url, huangjuSite + "/", cancelled};
    const auto resp = fetch(req);
    checkCancelled(cancelled);

    Json data;
    try {
        data = Json::parse(resp.body);
    } catch (...) {
        throw std::runtime_error("站点返回数据格式错误");
    }
    if (!data.is_object()) throw std::runtime_error("站点返回数据无效");

    CatalogPage result;
    std::set<std::string> seen;
    const auto items = data.value("items", Json::array());
    for (const auto& item : items) {
        const auto drama = parseHuangjuDrama(item);
        if (!drama.id.empty() && seen.insert(drama.id).second) {
            result.items.push_back(drama);
        }
    }
    const int total = data.value("total", 0);
    const int pageSize = data.value("pageSize", 20);
    result.hasMore = !result.items.empty() && (page * pageSize < total);
    return result;
}

CatalogPage HuangjuProvider::search(const std::string& keyword, const Cancellation& cancelled) const {
    if (keyword.empty() || keyword.size() > 320) throw std::invalid_argument("搜索关键词无效");
    checkCancelled(cancelled);
    std::string url = huangjuBase + "/dramas?page=1&q=" + encodePath(keyword);

    Request req{url, huangjuSite + "/", cancelled};
    const auto resp = fetch(req);
    checkCancelled(cancelled);

    Json data;
    try {
        data = Json::parse(resp.body);
    } catch (...) {
        throw std::runtime_error("站点返回数据格式错误");
    }
    if (!data.is_object()) throw std::runtime_error("站点返回数据无效");

    CatalogPage result;
    std::set<std::string> seen;
    const auto items = data.value("items", Json::array());
    for (const auto& item : items) {
        const auto drama = parseHuangjuDrama(item);
        if (!drama.id.empty() && seen.insert(drama.id).second) {
            result.items.push_back(drama);
        }
    }
    const int total = data.value("total", 0);
    const int pageSize = data.value("pageSize", 20);
    result.hasMore = !result.items.empty() && (pageSize < total);
    return result;
}

Detail HuangjuProvider::detail(const std::string& id, const Cancellation& cancelled) const {
    if (id.empty()) throw std::invalid_argument("剧集 ID 无效");
    checkCancelled(cancelled);
    std::string url = huangjuBase + "/dramas/" + encodePath(id);

    Request req{url, huangjuSite + "/", cancelled};
    const auto resp = fetch(req);
    checkCancelled(cancelled);

    Json data;
    try {
        data = Json::parse(resp.body);
    } catch (...) {
        throw std::runtime_error("站点返回数据格式错误");
    }
    if (!data.is_object() || !data.contains("id")) throw std::runtime_error("未找到所请求的剧集");

    Detail result;
    result.drama = parseHuangjuDrama(data);
    result.drama.intro = text(data, {"description"});
    const auto episodes = data.value("episodes", Json::array());
    std::set<std::string> seen;
    int fallbackIndex = 1;
    for (const auto& ep : episodes) {
        if (!ep.is_object()) continue;
        if (!ep.value("playable", true)) continue;
        const int epNo = nonnegativeInt(ep, "epNo") > 0 ? nonnegativeInt(ep, "epNo") : fallbackIndex;
        std::string epId = text(ep, {"id"});
        if (epId.empty() || !seen.insert(epId).second) continue;
        std::string epTitle = text(ep, {"title"});
        if (epTitle.empty()) epTitle = "第" + std::to_string(epNo) + "集";
        result.episodes.push_back({epId, epTitle, epNo});
        fallbackIndex++;
    }
    if (result.episodes.empty()) throw std::runtime_error("该剧暂未提供可用分集");
    return result;
}

Media HuangjuProvider::resolve(const std::string& dramaId, const Episode& episode, const Cancellation& cancelled) const {
    (void)dramaId;
    if (episode.id.empty()) throw std::invalid_argument("分集 ID 无效");
    checkCancelled(cancelled);

    const std::string guestToken = ensureGuestToken(cancelled);
    checkCancelled(cancelled);

    std::string url = huangjuBase + "/play/" + encodePath(episode.id);
    Request req{url, huangjuSite + "/", cancelled, "GET", "", {
        "Authorization: Bearer " + guestToken,
        "Origin: " + huangjuSite
    }};
    const auto resp = fetch(req);
    checkCancelled(cancelled);

    Json data;
    try {
        data = Json::parse(resp.body);
    } catch (...) {
        throw std::runtime_error("播放解析返回格式错误");
    }
    if (!data.is_object() || !data.contains("url"))
        throw std::runtime_error("剧果未提供该集播放地址，请稍后重试");

    std::string playUrl = data.value("url", "");
    if (!isHttpUrl(playUrl)) throw std::runtime_error("播放地址无效");

    std::string cookies = extractCookies(resp.headers);
    Media result;
    result.url = playUrl;
    result.referer = huangjuSite + "/";
    result.cookies = cookies;
    return result;
}

}
