#pragma once

#include "guo/provider.hpp"

namespace guo {

struct History {
    Drama drama;
    Episode episode;
    int seconds = 0;
    int duration = 0;
    bool completed = false;
};

class Library {
public:
    explicit Library(std::string path);
    void load();
    void record(const History& history);
    std::vector<History> history() const;
    History progress(const std::string& dramaId) const;

private:
    std::string path;
    std::vector<History> entries;
    bool writable = true;
};

}
