#include "guo/provider.hpp"

#include <curl/curl.h>
#include <memory>
#include <stdexcept>

namespace guo {
namespace {
struct Buffer {
    std::string body;
    Cancellation cancelled;
};

size_t receive(char* data, size_t size, size_t count, void* context) {
    auto& buffer = *static_cast<Buffer*>(context);
    constexpr size_t maximum = 4 * 1024 * 1024;
    if ((buffer.cancelled && buffer.cancelled->load()) || size > maximum || count > maximum ||
        size * count > maximum - buffer.body.size()) return 0;
    buffer.body.append(data, size * count);
    return size * count;
}

size_t receiveHeader(char* buffer, size_t size, size_t nitems, void* userdata) {
    auto* headers = static_cast<std::vector<std::string>*>(userdata);
    const size_t total = size * nitems;
    headers->emplace_back(buffer, total);
    return total;
}

int progress(void* context, curl_off_t, curl_off_t, curl_off_t, curl_off_t) {
    auto& buffer = *static_cast<Buffer*>(context);
    return buffer.cancelled && buffer.cancelled->load() ? 1 : 0;
}
}

Response curlFetchHttp(const Request& request) {
    if (!isHttpUrl(request.url) || (!request.referer.empty() && !isHttpUrl(request.referer)))
        throw std::invalid_argument("请求地址无效");
    Buffer buffer{{}, request.cancelled};
    if (request.cancelled && request.cancelled->load()) throw std::runtime_error("请求已取消");
    std::unique_ptr<CURL, decltype(&curl_easy_cleanup)> handle(curl_easy_init(), curl_easy_cleanup);
    if (!handle) throw std::runtime_error("网络客户端初始化失败");
    CURL* curl = handle.get();
    curl_easy_setopt(curl, CURLOPT_URL, request.url.c_str());
    if (!request.referer.empty()) curl_easy_setopt(curl, CURLOPT_REFERER, request.referer.c_str());
    curl_easy_setopt(curl, CURLOPT_USERAGENT, "Mozilla/5.0 (Linux; Android 11; Pixel 5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.91 Mobile Safari/537.36");
    curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
    curl_easy_setopt(curl, CURLOPT_MAXREDIRS, 5L);
    curl_easy_setopt(curl, CURLOPT_PROTOCOLS, CURLPROTO_HTTP | CURLPROTO_HTTPS);
    curl_easy_setopt(curl, CURLOPT_REDIR_PROTOCOLS, CURLPROTO_HTTP | CURLPROTO_HTTPS);
    curl_easy_setopt(curl, CURLOPT_CONNECTTIMEOUT, 8L);
    curl_easy_setopt(curl, CURLOPT_TIMEOUT, 20L);
    curl_easy_setopt(curl, CURLOPT_NOSIGNAL, 1L);
    curl_easy_setopt(curl, CURLOPT_SSL_VERIFYPEER, 1L);
    curl_easy_setopt(curl, CURLOPT_SSL_VERIFYHOST, 2L);
#ifdef __SWITCH__
    curl_easy_setopt(curl, CURLOPT_CAINFO, "romfs:/certs/cacert.pem");
#endif
    curl_easy_setopt(curl, CURLOPT_ACCEPT_ENCODING, "");

    if (request.method == "POST") {
        curl_easy_setopt(curl, CURLOPT_POST, 1L);
        if (!request.body.empty()) {
            curl_easy_setopt(curl, CURLOPT_POSTFIELDS, request.body.c_str());
            curl_easy_setopt(curl, CURLOPT_POSTFIELDSIZE, static_cast<long>(request.body.size()));
        }
    } else if (request.method != "GET") {
        curl_easy_setopt(curl, CURLOPT_CUSTOMREQUEST, request.method.c_str());
    }

    curl_slist* headers = nullptr;
    headers = curl_slist_append(headers, "Accept: application/json,text/html;q=0.9,*/*;q=0.8");
    headers = curl_slist_append(headers, "Accept-Language: zh-CN,zh;q=0.9");
    for (const auto& header : request.headers) {
        headers = curl_slist_append(headers, header.c_str());
    }
    std::unique_ptr<curl_slist, decltype(&curl_slist_free_all)> headerList(headers, curl_slist_free_all);
    curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headerList.get());

    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, receive);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, &buffer);

    std::vector<std::string> responseHeaders;
    curl_easy_setopt(curl, CURLOPT_HEADERFUNCTION, receiveHeader);
    curl_easy_setopt(curl, CURLOPT_HEADERDATA, &responseHeaders);

    curl_easy_setopt(curl, CURLOPT_NOPROGRESS, 0L);
    curl_easy_setopt(curl, CURLOPT_XFERINFOFUNCTION, progress);
    curl_easy_setopt(curl, CURLOPT_XFERINFODATA, &buffer);

    const auto status = curl_easy_perform(curl);
    if (request.cancelled && request.cancelled->load()) throw std::runtime_error("请求已取消");
    if (status != CURLE_OK) throw std::runtime_error("网络请求失败，请检查网络后重试");
    long code = 0;
    curl_easy_getinfo(curl, CURLINFO_RESPONSE_CODE, &code);
    if (code < 200 || code >= 300) throw std::runtime_error("站点返回 HTTP " + std::to_string(code) + "，请稍后重试");

    return Response{std::move(buffer.body), std::move(responseHeaders), code};
}

std::string curlFetchText(const Request& request) {
    return curlFetchHttp(request).body;
}

}
