#include "guo/provider.hpp"
#include <fstream>
#include <iostream>
#include <nlohmann/json.hpp>
#include <stdexcept>

int main(int argc, char** argv) {
    nlohmann::json results = nlohmann::json::array();
    bool correct = argc > 1;
    for (int i = 1; i < argc; ++i) {
        std::ifstream input(argv[i]);
        nlohmann::json fixture;
        input >> fixture;
        const auto series = fixture.at("series_id").get<std::string>();
        const auto episode = fixture.at("episode_id").get<std::string>();
        int fetches = 0;
        guo::HongguoProvider provider([&](const guo::Request& request) {
            ++fetches;
            if (request.url != "https://hongguoduanju.com/player/" + series + "/" + episode)
                throw std::runtime_error("Unexpected request");
            return "window._ROUTER_DATA = " + fixture.at("page").dump() + ";";
        });
        nlohmann::json row = {{"fixture", argv[i]}, {"series_id", series}, {"episode_id", episode}};
        try {
            provider.resolve(series, {episode, "test", 1}, {});
            row["unexpected_success"] = true;
            correct = false;
        } catch (const std::exception& error) {
            row["error"] = error.what();
            if (std::string(error.what()) != "该集没有公开播放地址，可能需要登录或 App 授权")
                correct = false;
        }
        row["fetch_count"] = fetches;
        results.push_back(row);
    }
    std::cout << nlohmann::json({{"reproduced", correct}, {"cases", results}}).dump(2) << '\n';
    return correct ? 0 : 1;
}
