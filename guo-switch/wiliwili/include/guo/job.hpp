#pragma once

#include "guo/provider.hpp"
#include <borealis/core/thread.hpp>
#include <exception>
#include <utility>

namespace guo {
class Job {
public:
    ~Job() { cancel(); }
    Job() = default;
    Job(const Job&) = delete;
    Job& operator=(const Job&) = delete;

    void cancel() {
        if (token) token->store(true);
    }

    template <typename Work, typename Done, typename Error>
    void run(Work work, Done done, Error error) {
        cancel();
        token = std::make_shared<std::atomic_bool>(false);
        auto current = token;
        brls::async([current, work = std::move(work), done = std::move(done), error = std::move(error)]() {
            if (current->load()) return;
            try {
                auto result = work(current);
                brls::sync([current, result = std::move(result), done]() {
                    if (!current->load()) done(result);
                });
            } catch (const std::exception& exception) {
                const std::string message = exception.what();
                brls::sync([current, message, error]() {
                    if (!current->load()) error(message);
                });
            }
        });
    }

private:
    Cancellation token;
};
}
