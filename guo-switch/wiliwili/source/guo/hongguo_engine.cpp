#include "guo/hongguo_engine.hpp"

#include <algorithm>
#include <cctype>
#include <cstdio>
#include <cstring>
#include <random>
#include <thread>

#include <mongoose.h>

namespace guo {
namespace {

constexpr uint64_t kChunkSize = 524288;
constexpr size_t kMaxChunksCached = 12;
constexpr uint64_t kMaxResponseBytes = 2 * 1024 * 1024;
constexpr const char* kMediaUserAgent =
    "com.phoenix.read/71332 (Linux; U; Android 16; zh_CN; 25053RT47C; "
    "Build/BP2A.250605.031.A3; Cronet/TTNetVersion:04657795 2026-01-23 "
    "QuicVersion:c67e9834 2025-09-08)";

uint32_t readBe32(const uint8_t* p) {
    return (static_cast<uint32_t>(p[0]) << 24) | (static_cast<uint32_t>(p[1]) << 16) |
           (static_cast<uint32_t>(p[2]) << 8) | p[3];
}

std::string headerValue(const std::vector<std::string>& headers, const std::string& name) {
    std::string found;
    for (const auto& line : headers) {
        if (line.size() > name.size() + 1 &&
            std::equal(name.begin(), name.end(), line.begin(),
                       [](char a, char b) { return std::tolower((unsigned char)a) == std::tolower((unsigned char)b); }) &&
            line[name.size()] == ':') {
            std::string value = line.substr(name.size() + 1);
            const size_t begin = value.find_first_not_of(" \t");
            const size_t end = value.find_last_not_of(" \t\r\n");
            if (begin != std::string::npos) found = value.substr(begin, end - begin + 1);
        }
    }
    return found;
}

bool parseContentRange(const std::string& value, uint64_t& start, uint64_t& end, uint64_t& total) {
    // "bytes 0-511/4303144"
    if (value.rfind("bytes ", 0) != 0) return false;
    const size_t dash = value.find('-', 6);
    const size_t slash = value.find('/', 6);
    if (dash == std::string::npos || slash == std::string::npos || slash < dash) return false;
    try {
        start = std::stoull(value.substr(6, dash - 6));
        end = std::stoull(value.substr(dash + 1, slash - dash - 1));
        total = std::stoull(value.substr(slash + 1));
    } catch (...) {
        return false;
    }
    return true;
}

StreamEngine* gEngine = nullptr;

void engineHttpHandler(mg_connection* connection, int event, void* eventData) {
    if (event != MG_EV_HTTP_MSG) return;
    auto* message = static_cast<mg_http_message*>(eventData);
    const std::string uri(message->uri.buf, message->uri.len);
    auto replyError = [&](int status, const std::string& text) {
        mg_http_reply(connection, status, "Content-Type: text/plain; charset=utf-8\r\n", "%s",
                      text.c_str());
    };
    if (uri.rfind("/s/", 0) != 0) {
        replyError(404, "not found\n");
        return;
    }
    auto session = gEngine ? gEngine->findSession(uri.substr(3)) : nullptr;
    if (!session) {
        replyError(404, "unknown session\n");
        return;
    }
    std::string range;
    if (const mg_str* header = mg_http_get_header(message, "Range")) {
        range.assign(header->buf, header->len);
    }
    const bool headOnly = message->method.len == 4 && std::memcmp(message->method.buf, "HEAD", 4) == 0;
    const auto response = gEngine->serve(session, range, headOnly);
    const char* reason = response.status == 206   ? "Partial Content"
                         : response.status == 200 ? "OK"
                         : response.status == 416 ? "Range Not Satisfiable"
                                                  : "Bad Gateway";
    // Compose the head manually: mongoose's printf does not support %zu.
    std::string head = "HTTP/1.1 " + std::to_string(response.status) + " " + reason + "\r\n";
    for (const auto& [name, value] : response.headers) head += name + ": " + value + "\r\n";
    head += "Content-Length: " +
            std::to_string(headOnly ? response.contentLength : response.body.size()) +
            "\r\nConnection: close\r\n\r\n";
    mg_send(connection, head.data(), head.size());
    if (!response.body.empty()) mg_send(connection, response.body.data(), response.body.size());
    connection->is_resp = 0;
}

}  // namespace

RangeFetcher makeHttpRangeFetcher(FetchHttp fetch) {
    return [fetch](const std::string& url, uint64_t start, uint64_t end) -> RangeResult {
        std::runtime_error lastError("媒体响应异常");
        for (int attempt = 0; attempt < 2; ++attempt) {
            try {
                Request request;
                request.method = "GET";
                request.url = url;
                request.headers = {std::string("User-Agent: ") + kMediaUserAgent,
                                   "Referer: https://novel.snssdk.com/",
                                   "Range: bytes=" + std::to_string(start) + "-" + std::to_string(end),
                                   "Accept-Encoding: identity"};
                const Response response = fetch(request);
                std::vector<uint8_t> data(response.body.begin(), response.body.end());
                if (response.code == 206) {
                    uint64_t a = 0, b = 0, total = 0;
                    if (!parseContentRange(headerValue(response.headers, "Content-Range"), a, b, total)) {
                        throw std::runtime_error("媒体 Range 响应缺少位置");
                    }
                    if (total == 0 || a != start || b != std::min(end, total - 1) ||
                        data.size() != b - a + 1) {
                        throw std::runtime_error("媒体 Range 位置异常");
                    }
                    if (start >= total || data.size() != std::min(end, total - 1) - start + 1) {
                        throw std::runtime_error("媒体 Range 长度异常");
                    }
                    return {data, total};
                }
                if (response.code == 200) {
                    uint64_t total = data.size();
                    const std::string length = headerValue(response.headers, "Content-Length");
                    if (!length.empty()) {
                        try {
                            total = std::stoull(length);
                        } catch (...) {
                        }
                    }
                    if (total != data.size()) throw std::runtime_error("媒体完整响应长度异常");
                    if (start >= total) throw std::runtime_error("媒体 Range 长度异常");
                    const uint64_t sliceEnd = std::min(end + 1, total);
                    return {std::vector<uint8_t>(data.begin() + start, data.begin() + sliceEnd), total};
                }
                throw std::runtime_error("媒体响应异常");
            } catch (const std::runtime_error& error) {
                lastError = error;
            }
        }
        throw lastError;
    };
}

StreamEngine& StreamEngine::instance() {
    static StreamEngine engine;
    return engine;
}

StreamEngine::StreamEngine() = default;

void StreamEngine::ensureServer() {
    if (serverStarted_) return;
    serverStarted_ = true;
    gEngine = this;
    std::thread([this] {
        mg_mgr manager;
        mg_mgr_init(&manager);
        mg_connection* listener = nullptr;
        for (int port = 18765; port < 18785 && !listener; ++port) {
            char url[64];
            std::snprintf(url, sizeof(url), "http://127.0.0.1:%d", port);
            listener = mg_http_listen(&manager, url, engineHttpHandler, nullptr);
            if (listener) port_ = static_cast<uint16_t>(port);
        }
        if (!listener) return;
        for (;;) mg_mgr_poll(&manager, 50);
    }).detach();
}

uint16_t StreamEngine::port() {
    ensureServer();
    return port_;
}

std::shared_ptr<StreamSession> StreamEngine::createSession(std::vector<StreamRow> rows,
                                                           RangeFetcher fetcher) {
    ensureServer();
    auto session = std::make_shared<StreamSession>();
    session->rows = std::move(rows);
    session->fetcher = std::move(fetcher);
    static thread_local std::mt19937_64 rng(std::random_device{}());
    char id[32];
    std::snprintf(id, sizeof(id), "%llx%llx", (unsigned long long)rng(), (unsigned long long)++nextId_);
    session->id = id;
    {
        std::lock_guard<std::mutex> lock(registryMutex_);
        registry_[session->id] = session;
    }
    return session;
}

std::shared_ptr<StreamSession> StreamEngine::findSession(const std::string& id) {
    std::lock_guard<std::mutex> lock(registryMutex_);
    const auto it = registry_.find(id);
    if (it == registry_.end()) return nullptr;
    return it->second.lock();
}

std::string StreamEngine::urlFor(const StreamSession& session) {
    return "http://127.0.0.1:" + std::to_string(port()) + "/s/" + session.id;
}

void StreamEngine::prepare(StreamSession& session) {
    std::lock_guard<std::recursive_mutex> lock(session.mutex);
    if (session.prepared) return;
    session.prepareAttempted = true;
    while (session.rowIndex < session.rows.size()) {
        const StreamRow& row = session.rows[session.rowIndex];
        try {
            if (row.key.empty()) {
                const auto probe = session.fetcher(row.url, 0, 262143);
                session.total = probe.total;
                session.index = PreparedStream{};
                session.index.total = probe.total;
                session.prepared = true;
                return;
            }
            auto head = session.fetcher(row.url, 0, 262143);
            const uint64_t total = head.total;
            if (head.data.size() < 16 || std::memcmp(head.data.data() + 4, "ftyp", 4) != 0) {
                throw std::runtime_error("视频头格式异常");
            }
            const uint32_t ftypSize = readBe32(head.data.data());
            if (ftypSize < 8 || ftypSize + 8 > head.data.size() ||
                std::memcmp(head.data.data() + ftypSize + 4, "moov", 4) != 0) {
                throw std::runtime_error("视频索引格式异常");
            }
            const uint32_t moovSize = readBe32(head.data.data() + ftypSize);
            const uint64_t moovEnd = static_cast<uint64_t>(ftypSize) + moovSize;
            if (moovSize < 8 || moovEnd > total || moovEnd > 8 * 1024 * 1024) {
                throw std::runtime_error("视频索引超出限制");
            }
            std::vector<uint8_t> headerBytes = head.data;
            while (headerBytes.size() < moovEnd) {
                const uint64_t from = headerBytes.size();
                const uint64_t to = std::min(from + kChunkSize, moovEnd) - 1;
                const auto more = session.fetcher(row.url, from, to);
                headerBytes.insert(headerBytes.end(), more.data.begin(), more.data.end());
            }
            headerBytes.resize(moovEnd);
            session.index = prepareCencHeader(headerBytes.data(), headerBytes.size(),
                                              row.key.data(), total);
            session.total = total;
            session.prepared = true;
            return;
        } catch (const std::exception& error) {
            session.prepareError = error.what();
            ++session.rowIndex;
        }
    }
    throw std::runtime_error(session.prepareError.empty() ? "没有兼容的播放资源" : session.prepareError);
}

std::vector<uint8_t> StreamEngine::chunk(StreamSession& session, uint64_t index) {
    std::lock_guard<std::recursive_mutex> lock(session.mutex);
    const auto hit = session.chunks.find(index);
    if (hit != session.chunks.end()) return hit->second;
    const StreamRow& row = session.rows[session.rowIndex];
    const uint64_t start = index * kChunkSize;
    const uint64_t end = std::min(start + kChunkSize, session.total) - 1;
    auto result = session.fetcher(row.url, start, end);
    if (result.total != session.total) throw std::runtime_error("媒体文件长度发生变化");
    if (!row.key.empty()) {
        decryptPartial(result.data.data(), result.data.size(), start, row.key.data(), session.index);
    }
    session.chunks[index] = result.data;
    session.chunkOrder.push_back(index);
    while (session.chunkOrder.size() > kMaxChunksCached) {
        session.chunks.erase(session.chunkOrder.front());
        session.chunkOrder.pop_front();
    }
    return result.data;
}

StreamEngine::ServedResponse StreamEngine::serve(const std::shared_ptr<StreamSession>& session,
                                                 const std::string& rangeHeader, bool headOnly) {
    ServedResponse response;
    try {
        prepare(*session);
    } catch (const std::exception& error) {
        response.status = 502;
        response.headers = {{"Content-Type", "text/plain; charset=utf-8"}};
        const std::string text = error.what();
        response.body.assign(text.begin(), text.end());
        response.contentLength = response.body.size();
        return response;
    }
    const uint64_t total = session->total;
    if (total == 0) {
        response.status = 200;
        response.headers = {{"Content-Type", "video/mp4"}, {"Accept-Ranges", "bytes"}};
        response.contentLength = 0;
        return response;
    }
    uint64_t start = 0, end = total - 1;
    bool ranged = false;
    if (!rangeHeader.empty()) {
        // bytes=a-b | bytes=a- | bytes=-suffix
        if (rangeHeader.rfind("bytes=", 0) != 0) {
            response.status = 416;
            response.headers = {{"Content-Range", "bytes */" + std::to_string(total)}};
            return response;
        }
        const std::string spec = rangeHeader.substr(6);
        const size_t dash = spec.find('-');
        try {
            if (dash == std::string::npos) throw std::runtime_error("range");
            const std::string first = spec.substr(0, dash);
            const std::string second = spec.substr(dash + 1);
            if (first.empty()) {
                const uint64_t suffix = std::stoull(second);
                if (suffix == 0 || total == 0) throw std::runtime_error("range");
                start = total - std::min(suffix, total);
                end = total - 1;
            } else {
                start = std::stoull(first);
                end = second.empty() ? total - 1 : std::stoull(second);
            }
        } catch (...) {
            response.status = 416;
            response.headers = {{"Content-Range", "bytes */" + std::to_string(total)}};
            return response;
        }
        if (total == 0 || start >= total || end < start) {
            response.status = 416;
            response.headers = {{"Content-Range", "bytes */" + std::to_string(total)}};
            return response;
        }
        end = std::min(end, total - 1);
        ranged = true;
    }
    if (end - start + 1 > kMaxResponseBytes) end = start + kMaxResponseBytes - 1;
    response.status = ranged ? 206 : 200;
    response.headers = {{"Content-Type", "video/mp4"}, {"Accept-Ranges", "bytes"}};
    if (ranged) {
        response.headers.emplace_back(
            "Content-Range",
            "bytes " + std::to_string(start) + "-" + std::to_string(end) + "/" + std::to_string(total));
    }
    response.contentLength = end - start + 1;
    if (headOnly) return response;
    try {
        response.body.reserve(end - start + 1);
        uint64_t position = start;
        while (position <= end) {
            const uint64_t index = position / kChunkSize;
            const auto bytes = chunk(*session, index);
            const uint64_t chunkStart = index * kChunkSize;
            const size_t offset = static_cast<size_t>(position - chunkStart);
            const size_t take = static_cast<size_t>(
                std::min<uint64_t>(bytes.size() - offset, end - position + 1));
            response.body.insert(response.body.end(), bytes.begin() + offset,
                                 bytes.begin() + offset + take);
            position += take;
        }
        // Sequential prefetch: warm the next chunk after a substantial read.
        if (end + 1 < total && end - start + 1 >= 262144) {
            try {
                chunk(*session, end / kChunkSize + 1);
            } catch (...) {
            }
        }
    } catch (const std::exception& error) {
        response.status = 502;
        response.headers = {{"Content-Type", "text/plain; charset=utf-8"}};
        const std::string text = error.what();
        response.body.assign(text.begin(), text.end());
        response.contentLength = response.body.size();
    }
    return response;
}

}  // namespace guo
