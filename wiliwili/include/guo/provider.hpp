#pragma once

#include <atomic>
#include <functional>
#include <map>
#include <memory>
#include <mutex>
#include <string>
#include <vector>

namespace guo {

using Cancellation = std::shared_ptr<std::atomic_bool>;

struct Category {
    std::string id;
    std::string title;
};

struct Drama {
    std::string id;
    std::string title;
    std::string intro;
    std::string cover;
    std::string category;
    std::string remark;
    std::string source;
};

struct Episode {
    std::string id;
    std::string title;
    int number = 0;
};

struct CatalogPage {
    std::vector<Drama> items;
    bool hasMore = false;
    std::string notice;
};

struct Detail {
    Drama drama;
    std::vector<Episode> episodes;
};

struct Media {
    std::string url;
    std::string referer;
    std::vector<std::string> backups;
    std::string cookies;
    // Keeps local streaming-engine sessions alive while this media is in
    // use (Hongguo App channel); empty for direct URLs.
    std::shared_ptr<void> session;
};

struct Request {
    std::string url;
    std::string referer;
    Cancellation cancelled;
    std::string method = "GET";
    std::string body;
    std::vector<std::string> headers;

    Request() = default;
    Request(std::string url, std::string referer = {}, Cancellation cancelled = {},
            std::string method = "GET", std::string body = {}, std::vector<std::string> headers = {})
        : url(std::move(url)), referer(std::move(referer)), cancelled(std::move(cancelled)),
          method(std::move(method)), body(std::move(body)), headers(std::move(headers)) {}
};

struct Response {
    std::string body;
    std::vector<std::string> headers;
    long code = 0;
};

using FetchText = std::function<std::string(const Request&)>;
using FetchHttp = std::function<Response(const Request&)>;

class Provider {
public:
    virtual ~Provider() = default;
    virtual std::string name() const = 0;
    virtual std::vector<Category> categories() const = 0;
    virtual CatalogPage catalog(const std::string& category, int page, const Cancellation& cancelled) const = 0;
    virtual CatalogPage search(const std::string& keyword, const Cancellation& cancelled) const = 0;
    virtual Detail detail(const std::string& id, const Cancellation& cancelled) const = 0;
    virtual Media resolve(const std::string& dramaId, const Episode& episode,
                          const Cancellation& cancelled) const = 0;
};

class HongguoAppClient;

class HongguoProvider final : public Provider {
public:
    explicit HongguoProvider(FetchText fetch, FetchHttp fetchHttp = nullptr);
    // Defined in provider.cpp: the unique_ptr member needs the complete
    // HongguoAppClient type.
    ~HongguoProvider() override;
    std::string name() const override { return "hongguo"; }
    std::vector<Category> categories() const override;
    CatalogPage catalog(const std::string& category, int page, const Cancellation& cancelled) const override;
    CatalogPage search(const std::string& keyword, const Cancellation& cancelled) const override;
    Detail detail(const std::string& id, const Cancellation& cancelled) const override;
    Media resolve(const std::string& dramaId, const Episode& episode,
                  const Cancellation& cancelled) const override;

private:
    Media resolveWeb(const std::string& dramaId, const Episode& episode,
                     const Cancellation& cancelled) const;
    Media resolveApp(const std::string& dramaId, const Episode& episode,
                     const Cancellation& cancelled) const;
    HongguoAppClient& appClient() const;

    FetchText fetch;
    FetchHttp fetchHttp;
    mutable std::mutex appMutex;
    mutable std::unique_ptr<HongguoAppClient> app;
};

class HuangjuProvider final : public Provider {
public:
    explicit HuangjuProvider(FetchHttp fetch);
    std::string name() const override { return "huangju"; }
    std::vector<Category> categories() const override;
    CatalogPage catalog(const std::string& category, int page, const Cancellation& cancelled) const override;
    CatalogPage search(const std::string& keyword, const Cancellation& cancelled) const override;
    Detail detail(const std::string& id, const Cancellation& cancelled) const override;
    Media resolve(const std::string& dramaId, const Episode& episode,
                  const Cancellation& cancelled) const override;

private:
    FetchHttp fetch;
    mutable std::mutex authMutex;
    mutable std::string deviceId;
    mutable std::string token;
    std::string ensureGuestToken(const Cancellation& cancelled) const;
};

bool isHttpUrl(const std::string& value);
std::string encodePath(const std::string& value);
std::string extractCookies(const std::vector<std::string>& headers);
Response curlFetchHttp(const Request& request);
std::string curlFetchText(const Request& request);

}
