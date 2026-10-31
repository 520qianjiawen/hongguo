#pragma once

#include <limits>
#include <optional>
#include <borealis/core/activity.hpp>
#include <borealis/core/bind.hpp>
#include "guo/job.hpp"
#include "guo/library.hpp"
#include "utils/event_helper.hpp"

class RecyclingGrid;
class VideoView;
namespace brls { class Label; class Button; class AppletFrame; }

namespace guo {
class HomeActivity : public brls::Activity {
public:
    CONTENT_FROM_XML_RES("activity/guo_home.xml");
    void onContentAvailable() override;
    void willAppear(bool resetState = false) override;

private:
    void load(bool reset);
    void showHistory();
    void search();
    void refresh();
    void switchSource();
    void updateCategoryButtons();
    void display();
    Job job;
    std::vector<Drama> items;
    std::string currentSource = "huangju";
    std::string category = "hot";
    std::string keyword;
    int page = 0;
    bool hasMore = true;
    bool busy = false;
    bool historyMode = false;
    std::shared_ptr<bool> alive = std::make_shared<bool>(true);
    BRLS_BIND(brls::AppletFrame, frame, "guo/frame");
    BRLS_BIND(brls::Label, status, "guo/status");
    BRLS_BIND(brls::Box, categoriesBox, "guo/categories");
    BRLS_BIND(brls::Button, sourceButton, "guo/source");
    BRLS_BIND(brls::Button, searchButton, "guo/search");
    BRLS_BIND(brls::Button, historyButton, "guo/history");
    BRLS_BIND(brls::Button, refreshButton, "guo/refresh");
    BRLS_BIND(RecyclingGrid, grid, "guo/grid");
public:
    ~HomeActivity() override { job.cancel(); *alive = false; }
};

class DetailActivity : public brls::Activity {
public:
    CONTENT_FROM_XML_RES("activity/guo_detail.xml");
    explicit DetailActivity(Drama drama);
    void onContentAvailable() override;
    void willAppear(bool resetState = false) override;

private:
    void load();
    void resume();
    Job job;
    Job warmJob;
    Drama selected;
    Detail detail;
    BRLS_BIND(brls::AppletFrame, frame, "guo/frame");
    BRLS_BIND(brls::Label, status, "guo/status");
    BRLS_BIND(brls::Label, intro, "guo/intro");
    BRLS_BIND(brls::Button, resumeButton, "guo/resume");
    BRLS_BIND(brls::Button, retryButton, "guo/retry");
    BRLS_BIND(RecyclingGrid, grid, "guo/grid");
};

class PlayerActivity : public brls::Activity {
public:
    CONTENT_FROM_XML_RES("activity/video_activity.xml");
    PlayerActivity(Detail detail, size_t index);
    ~PlayerActivity() override;
    void onContentAvailable() override;

private:
    void play(size_t index, bool restore);
    void beginPlayback(const Media& result, bool restore, const Episode& episode);
    void warmNext();
    void save(bool completed = false);
    void fail(const std::string& message);
    void next(int direction);
    Job job;
    Job warmJob;
    std::optional<Media> warmed;
    size_t warmedIndex = std::numeric_limits<size_t>::max();
    Detail detail;
    size_t index;
    size_t backupIndex = 0;
    Media media;
    bool loaded = false;
    bool loading = false;
    bool completed = false;
    bool subscribed = false;
    bool saveErrorShown = false;
    int lastSaved = -1;
    MPVEvent::Subscription subscription;
    BRLS_BIND(VideoView, video, "video");
};

std::shared_ptr<const Provider> provider(const std::string& source = "");
Library& library();
}
