#pragma once

#include <cstdint>
#include <deque>
#include <functional>
#include <map>
#include <memory>
#include <mutex>
#include <string>
#include <vector>

#include "guo/hongguo_app.hpp"
#include "guo/hongguo_codec.hpp"
#include "guo/provider.hpp"

namespace guo {

struct RangeResult {
    std::vector<uint8_t> data;
    uint64_t total = 0;
};

// Fetches the inclusive byte range [start, end] of a media URL with the
// adapter remoteRange validation semantics (206 + Content-Range checks, or
// a sliced 200 response; one retry). Throws std::runtime_error on failure.
using RangeFetcher = std::function<RangeResult(const std::string& url, uint64_t start, uint64_t end)>;
RangeFetcher makeHttpRangeFetcher(FetchHttp fetch);

// One opened stream: a preference-ordered row set sharing one content key.
// Bytes are served through the engine's local HTTP proxy, decrypting chunks
// on demand (the APK's buffered playback engine shape).
struct StreamSession {
    std::string id;
    std::vector<StreamRow> rows;
    size_t rowIndex = 0;
    RangeFetcher fetcher;

    std::recursive_mutex mutex;
    bool prepared = false;
    bool prepareAttempted = false;
    std::string prepareError;
    PreparedStream index;  // patched header + sample table (keyed streams)
    uint64_t total = 0;

    std::map<uint64_t, std::vector<uint8_t>> chunks;  // playable bytes
    std::deque<uint64_t> chunkOrder;
};

// Keeps the sessions referenced by a Media alive for the player.
struct EngineLease {
    std::shared_ptr<StreamSession> primary;
    std::vector<std::shared_ptr<StreamSession>> alternates;
};

class StreamEngine {
public:
    static StreamEngine& instance();

    // Creates and registers a session (unprepared; see prepare()).
    std::shared_ptr<StreamSession> createSession(std::vector<StreamRow> rows, RangeFetcher fetcher);
    // Header fetch + CENC index build with row failover. Throws when every
    // row fails. Safe to call again (no-op once prepared).
    void prepare(StreamSession& session);
    std::string urlFor(const StreamSession& session);
    uint16_t port();

    // Serves one HTTP request for a session. Implemented in the .cpp with
    // the mongoose plumbing; exposed for the host-side engine test.
    struct ServedResponse {
        int status = 200;
        std::vector<std::pair<std::string, std::string>> headers;
        std::vector<uint8_t> body;
        size_t contentLength = 0;  // > body.size() for HEAD responses
    };
    ServedResponse serve(const std::shared_ptr<StreamSession>& session, const std::string& rangeHeader,
                         bool headOnly);
    std::shared_ptr<StreamSession> findSession(const std::string& id);

private:
    StreamEngine();
    void ensureServer();
    std::vector<uint8_t> chunk(StreamSession& session, uint64_t index);

    std::mutex registryMutex_;
    std::map<std::string, std::weak_ptr<StreamSession>> registry_;
    uint64_t nextId_ = 0;
    uint16_t port_ = 0;
    bool serverStarted_ = false;
};

}  // namespace guo
