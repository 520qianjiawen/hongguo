#include "guo/library.hpp"

#include <algorithm>
#include <cstdio>
#include <fstream>
#include <stdexcept>
#include <nlohmann/json.hpp>

namespace guo {
Library::Library(std::string path) : path(std::move(path)) {}

void Library::load() {
    std::ifstream file(path);
    if (!file) return;
    writable = false;
    if (file.seekg(0, std::ios::end).tellg() > 1024 * 1024) throw std::runtime_error("观看记录文件过大");
    file.seekg(0);
    const auto data = nlohmann::json::parse(file);
    if (!data.is_object() || data.value("version", 0) != 1 || !data.contains("history") || !data["history"].is_array())
        throw std::runtime_error("观看记录格式无效");
    std::vector<History> loaded;
    std::map<std::string, bool> seen;
    for (const auto& row : data["history"]) {
        History entry;
        entry.drama.id = row.at("drama_id").get<std::string>();
        entry.drama.title = row.at("title").get<std::string>();
        entry.episode.id = row.at("episode_id").get<std::string>();
        entry.episode.number = row.at("episode_number").get<int>();
        entry.episode.title = "第" + std::to_string(entry.episode.number) + "集";
        entry.seconds = row.at("seconds").get<int>();
        entry.duration = row.at("duration").get<int>();
        entry.completed = row.value("completed", false);
        if (entry.drama.id.empty() || entry.episode.id.empty() || entry.episode.number < 1 ||
            entry.seconds < 0 || entry.duration < 0 || seen[entry.drama.id]) continue;
        seen[entry.drama.id] = true;
        loaded.push_back(std::move(entry));
        if (loaded.size() == 100) break;
    }
    entries = std::move(loaded);
    writable = true;
}

void Library::record(const History& history) {
    if (!writable) throw std::runtime_error("观看记录读取失败，已保留原文件");
    if (history.drama.id.empty() || history.episode.id.empty() || history.episode.number < 1 ||
        history.seconds < 0 || history.duration < 0) return;
    auto next = entries;
    next.erase(std::remove_if(next.begin(), next.end(), [&](const auto& entry) {
        return entry.drama.id == history.drama.id;
    }), next.end());
    next.insert(next.begin(), history);
    if (next.size() > 100) next.resize(100);
    nlohmann::json data = {{"version", 1}, {"history", nlohmann::json::array()}};
    for (const auto& entry : next) {
        data["history"].push_back({{"drama_id", entry.drama.id}, {"title", entry.drama.title},
            {"episode_id", entry.episode.id}, {"episode_number", entry.episode.number},
            {"seconds", entry.seconds}, {"duration", entry.duration}, {"completed", entry.completed}});
    }
    const auto temporary = path + ".tmp";
    std::ofstream file(temporary, std::ios::trunc);
    if (!file || !(file << data.dump(2)) || !file.flush()) throw std::runtime_error("无法保存观看进度");
    file.close();
    if (!file || std::rename(temporary.c_str(), path.c_str()) != 0) throw std::runtime_error("无法写入观看记录");
    entries = std::move(next);
}

std::vector<History> Library::history() const { return entries; }

History Library::progress(const std::string& dramaId) const {
    const auto it = std::find_if(entries.begin(), entries.end(), [&](const auto& entry) { return entry.drama.id == dramaId; });
    return it == entries.end() ? History{} : *it;
}
}
