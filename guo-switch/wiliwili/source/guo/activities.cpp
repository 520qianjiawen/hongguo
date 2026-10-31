#include "guo/activities.hpp"

#include <algorithm>
#include <limits>
#include <borealis.hpp>
#include <cpr/filesystem.h>
#include "utils/activity_helper.hpp"
#include "utils/config_helper.hpp"
#include "view/recycling_grid.hpp"
#include "view/video_view.hpp"
#include "view/mpv_core.hpp"

namespace guo {
namespace {
class Card : public RecyclingGridItem {
public:
    Card() { inflateFromXMLRes("xml/views/guo_card.xml"); }
    void set(const std::string& title, const std::string& subtitle) {
        titleLabel->setText(title);
        subtitleLabel->setText(subtitle);
        subtitleLabel->setVisibility(subtitle.empty() ? brls::Visibility::GONE : brls::Visibility::VISIBLE);
    }
private:
    BRLS_BIND(brls::Label, titleLabel, "guo/card/title");
    BRLS_BIND(brls::Label, subtitleLabel, "guo/card/subtitle");
};

struct CardData {
    std::string title;
    std::string subtitle;
};

class Cards : public RecyclingGridDataSource {
public:
    Cards(std::vector<CardData> values, std::function<void(size_t)> select)
        : values(std::move(values)), select(std::move(select)) {}
    size_t getItemCount() override { return values.size(); }
    RecyclingGridItem* cellForRow(RecyclingGrid* recycler, size_t index) override {
        auto* card = static_cast<Card*>(recycler->dequeueReusableCell("GuoCard"));
        card->set(values.at(index).title, values.at(index).subtitle);
        return card;
    }
    void onItemSelected(RecyclingGrid*, size_t index) override {
        const auto callback = select;
        if (index < values.size()) callback(index);
    }
    void clearData() override { values.clear(); }
    void update(std::vector<CardData> next) { values = std::move(next); }
private:
    std::vector<CardData> values;
    std::function<void(size_t)> select;
};

void installCards(RecyclingGrid* grid) {
    grid->registerCell("GuoCard", []() { return new Card(); });
}

size_t resumeIndex(const Detail& detail, const History& history) {
    const auto it = std::find_if(detail.episodes.begin(), detail.episodes.end(), [&](const auto& episode) {
        return episode.id == history.episode.id;
    });
    if (it == detail.episodes.end()) return 0;
    const auto index = static_cast<size_t>(it - detail.episodes.begin());
    return history.completed && index + 1 < detail.episodes.size() ? index + 1 : index;
}

std::string optionValue(const std::string& value) {
    return '%' + std::to_string(value.size()) + '%' + value;
}

std::string playbackOptions(const Media& media, int start = 0) {
    std::string result = "referrer=" + optionValue(media.referer) + ",user-agent=" + optionValue("Mozilla/5.0 GuoSwitch/0.1") +
                         ",network-timeout=20,tls-verify=yes,start=" + std::to_string(start);
#ifdef __SWITCH__
    result += ",tls-ca-file=" + optionValue(BRLS_ASSET("certs/cacert.pem"));
#endif
    if (!media.cookies.empty()) {
        result += ",http-header-fields=" + optionValue("Cookie: " + media.cookies);
        result += ",demuxer-lavf-o=" + optionValue("headers=Cookie: " + media.cookies + "\r\n");
    }
    return result;
}

int boundedTime(int64_t seconds) {
    return static_cast<int>(std::max<int64_t>(0, std::min<int64_t>(seconds, std::numeric_limits<int>::max())));
}

std::string resolveSource(const Drama& drama, const std::string& fallback = "huangju") {
    if (drama.source == "hongguo" || drama.source == "huangju") return drama.source;
    if (!drama.id.empty() && std::all_of(drama.id.begin(), drama.id.end(), [](char c) { return ::isdigit(static_cast<unsigned char>(c)); }))
        return "hongguo";
    if (drama.id.find('-') != std::string::npos)
        return "huangju";
    return fallback;
}
}

std::shared_ptr<const Provider> provider(const std::string& source) {
    static const auto huangju = std::make_shared<const HuangjuProvider>(curlFetchHttp);
    static const auto hongguo = std::make_shared<const HongguoProvider>(curlFetchText, curlFetchHttp);
    if (source == "hongguo") return hongguo;
    return huangju;
}

Library& library() {
    static Library value(ProgramConfig::instance().getConfigDir() + "/guo_history.json");
    static const bool initialized = [&]() {
        try {
            cpr::fs::create_directories(ProgramConfig::instance().getConfigDir());
            value.load();
        }
        catch (const std::exception&) { brls::Application::notify("观看记录读取失败，请保留原文件检查"); }
        return true;
    }();
    (void)initialized;
    return value;
}

void HomeActivity::onContentAvailable() {
    installCards(grid);
    library();
    sourceButton->setText(currentSource == "huangju" ? "站源: 剧果" : "站源: 红果");
    sourceButton->registerClickAction([this](brls::View*) { switchSource(); return true; });
    updateCategoryButtons();
    searchButton->registerClickAction([this](brls::View*) { search(); return true; });
    historyButton->registerClickAction([this](brls::View*) { showHistory(); return true; });
    refreshButton->registerClickAction([this](brls::View*) { refresh(); return true; });
    grid->setRefreshAction([this]() { refresh(); });
    grid->onNextPage([this]() { if (!historyMode && keyword.empty() && hasMore && !busy) load(false); });
    getContentView()->registerAction("搜索", brls::ControllerButton::BUTTON_X,
        [this](brls::View*) { search(); return true; });
    getContentView()->registerAction("刷新", brls::ControllerButton::BUTTON_Y,
        [this](brls::View*) { refresh(); return true; });
    load(true);
}

void HomeActivity::updateCategoryButtons() {
    categoriesBox->clearViews();
    for (const auto& choice : provider(currentSource)->categories()) {
        auto* button = new brls::Button();
        button->setText(choice.title);
        button->setWidth(116);
        button->setMarginRight(10);
        button->registerClickAction([this, choice](brls::View*) {
            category = choice.id;
            keyword.clear();
            historyMode = false;
            load(true);
            return true;
        });
        categoriesBox->addView(button);
    }
}

void HomeActivity::switchSource() {
    currentSource = (currentSource == "huangju") ? "hongguo" : "huangju";
    sourceButton->setText(currentSource == "huangju" ? "站源: 剧果" : "站源: 红果");
    const auto cats = provider(currentSource)->categories();
    category = cats.empty() ? "" : cats.front().id;
    keyword.clear();
    historyMode = false;
    updateCategoryButtons();
    load(true);
}

void HomeActivity::willAppear(bool resetState) {
    brls::Activity::willAppear(resetState);
    if (historyMode && grid) showHistory();
}

void HomeActivity::refresh() {
    if (historyMode) showHistory();
    else load(true);
}

void HomeActivity::search() {
    auto lifetime = alive;
    const std::string sourceName = (currentSource == "huangju") ? "剧果" : "红果";
    brls::Application::getImeManager()->openForText([this, lifetime](const std::string& value) {
        if (!*lifetime || value.empty()) return;
        keyword = value;
        historyMode = false;
        load(true);
    }, "搜索" + sourceName + "短剧", "输入剧名", 80, keyword);
}

void HomeActivity::load(bool reset) {
    if (!reset && (busy || !hasMore)) return;
    if (reset) {
        job.cancel();
        items.clear();
        page = 0;
        hasMore = true;
        grid->showSkeleton(9);
    }
    busy = true;
    const auto activeSource = currentSource;
    const auto source = provider(activeSource);
    const auto selectedCategory = category;
    const auto query = keyword;
    const int requestedPage = page + 1;
    const std::string sourceName = (activeSource == "huangju") ? "剧果" : "红果";
    frame->setTitle(query.empty() ? "果剧 · " + sourceName : "搜索 · " + query);
    status->setText(query.empty() ? "正在加载分类…" : "正在搜索…");
    job.run([source, selectedCategory, query, requestedPage](const Cancellation& token) {
        return query.empty() ? source->catalog(selectedCategory, requestedPage, token) : source->search(query, token);
    }, [this, requestedPage, activeSource](const CatalogPage& result) {
        const size_t oldSize = items.size();
        for (auto drama : result.items) {
            if (drama.source.empty()) drama.source = activeSource;
            if (std::none_of(items.begin(), items.end(), [&](const auto& item) { return item.id == drama.id; }))
                items.push_back(std::move(drama));
        }
        page = requestedPage;
        hasMore = result.hasMore && items.size() > oldSize;
        busy = false;
        display();
        std::string name = "搜索";
        if (keyword.empty()) for (const auto& choice : provider(activeSource)->categories()) if (choice.id == category) name = choice.title;
        status->setText(name + " · " + std::to_string(items.size()) + " 部" +
            (result.notice.empty() ? (hasMore ? " · 向下滚动加载更多" : " · 已加载当前结果") : " · " + result.notice));
    }, [this](const std::string& message) {
        busy = false;
        status->setText(message + " · 按 Y 重试");
        if (items.empty()) grid->setError(message);
    });
}

void HomeActivity::display() {
    std::vector<CardData> cards;
    for (const auto& drama : items) cards.push_back({drama.title, drama.remark.empty() ? drama.category : drama.remark});
    if (auto* source = dynamic_cast<Cards*>(grid->getDataSource())) {
        source->update(std::move(cards));
        grid->notifyDataChanged();
    } else {
        grid->setDataSource(new Cards(std::move(cards), [this](size_t index) { Intent::openGuoDetail(items.at(index)); }));
    }
    if (items.empty()) grid->setEmpty("没有找到剧集");
}

void HomeActivity::showHistory() {
    job.cancel();
    busy = false;
    historyMode = true;
    hasMore = false;
    frame->setTitle("果剧 · 观看历史");
    items.clear();
    std::vector<CardData> cards;
    for (const auto& entry : library().history()) {
        items.push_back(entry.drama);
        cards.push_back({entry.drama.title, entry.episode.title + (entry.completed ? " · 已看完" : " · " + std::to_string(entry.seconds) + " 秒")});
    }
    grid->setDataSource(new Cards(std::move(cards), [this](size_t index) { Intent::openGuoDetail(items.at(index)); }));
    status->setText("观看记录保存在本机 · 选择剧集继续播放");
    if (items.empty()) grid->setEmpty("还没有观看记录");
}

DetailActivity::DetailActivity(Drama drama) : selected(std::move(drama)) {}

void DetailActivity::onContentAvailable() {
    installCards(grid);
    frame->setTitle(selected.title);
    intro->setText(selected.intro.empty() ? "红果短剧" : selected.intro);
    resumeButton->registerClickAction([this](brls::View*) { resume(); return true; });
    retryButton->registerClickAction([this](brls::View*) { load(); return true; });
    grid->setRefreshAction([this]() { load(); });
    load();
}

void DetailActivity::willAppear(bool resetState) {
    brls::Activity::willAppear(resetState);
    if (!detail.episodes.empty()) {
        const auto history = library().progress(selected.id);
        resumeButton->setText(history.episode.id.empty() ? "立即播放" : "继续播放");
        grid->setDefaultCellFocus(resumeIndex(detail, history));
    }
}

void DetailActivity::load() {
    detail = {};
    job.cancel();
    grid->showSkeleton(18);
    status->setText("正在加载分集…");
    const std::string src = resolveSource(selected);
    const auto source = provider(src);
    const auto id = selected.id;
    job.run([source, id](const Cancellation& token) { return source->detail(id, token); },
        [this, src](const Detail& result) {
            detail = result;
            selected = result.drama;
            if (selected.source.empty()) selected.source = src;
            if (detail.drama.source.empty()) detail.drama.source = src;
            frame->setTitle(selected.title);
            intro->setText(selected.intro.empty() ? (src == "huangju" ? "剧果短剧" : "红果短剧") : selected.intro);
            std::vector<CardData> cards;
            for (const auto& episode : detail.episodes) cards.push_back({episode.title, ""});
            const auto history = library().progress(selected.id);
            grid->setDefaultCellFocus(resumeIndex(detail, history));
            grid->setDataSource(new Cards(std::move(cards), [this](size_t index) { Intent::openGuoPlayer(detail, index); }));
            resumeButton->setText(history.episode.id.empty() ? "立即播放" : "继续播放");
            status->setText("共 " + std::to_string(detail.episodes.size()) + " 集 · 选择分集播放");
            // Warm the App channel (device identity + stream info) for the
            // resume episode in the background; the result is discarded,
            // the caches it fills make the actual play start immediately.
            const size_t warmIndex = resumeIndex(detail, history);
            if (warmIndex < detail.episodes.size()) {
                const auto warmSource = provider(resolveSource(selected));
                const auto warmId = selected.id;
                const auto warmEpisode = detail.episodes[warmIndex];
                warmJob.run([warmSource, warmId, warmEpisode](const Cancellation& token) {
                    return warmSource->resolve(warmId, warmEpisode, token);
                }, [](const Media&) {}, [](const std::string&) {});
            }
        }, [this](const std::string& message) {
            status->setText(message);
            grid->setError(message);
        });
}

void DetailActivity::resume() {
    if (detail.episodes.empty()) return;
    Intent::openGuoPlayer(detail, resumeIndex(detail, library().progress(selected.id)));
}

PlayerActivity::PlayerActivity(Detail detail, size_t index) : detail(std::move(detail)), index(index) {}

void PlayerActivity::onContentAvailable() {
    video->hideDLNAButton();
    video->hideDanmakuButton();
    video->hideVideoQualityButton();
    video->hideHistorySetting();
    video->hideVideoRelatedSetting();
    video->hideSubtitleSetting();
    video->hideHighlightLineSetting();
    video->hideSkipOpeningCreditsSetting();
    video->disableCloseOnEndOfFile();
    video->setFullscreenIcon(true);
    video->getFullscreenIcon()->getParent()->registerClickAction([](brls::View*) {
        brls::Application::popActivity(brls::TransitionAnimation::NONE);
        return true;
    });
    video->registerCommonActions(this);
    video->registerAction("返回选集", brls::ControllerButton::BUTTON_B, [](brls::View*) {
        brls::Application::popActivity(brls::TransitionAnimation::NONE);
        return true;
    }, true);
    video->registerAction("上一集", brls::ControllerButton::BUTTON_LT,
        [this](brls::View*) { next(-1); return true; });
    video->registerAction("下一集", brls::ControllerButton::BUTTON_RT,
        [this](brls::View*) { next(1); return true; });
    video->registerAction("重新取流", brls::ControllerButton::BUTTON_Y,
        [this](brls::View*) { play(index, true); return true; });
    subscription = MPV_E->subscribe([this](MpvEventEnum event) {
        if (event == MpvEventEnum::MPV_LOADED && !loading) {
            loaded = true;
            warmNext();
        }
        else if (event == MpvEventEnum::UPDATE_PROGRESS && loaded) {
            const int seconds = boundedTime(MPVCore::instance().video_progress);
            if (lastSaved < 0 || std::abs(seconds - lastSaved) >= 5) save();
        } else if (event == MpvEventEnum::END_OF_FILE && loaded && !loading) {
            save(true);
            loaded = false;
            if (index + 1 < detail.episodes.size()) play(index + 1, false);
            else video->setTitle(detail.drama.title + " · 全剧播放完毕");
        } else if (event == MpvEventEnum::MPV_FILE_ERROR && !loading) {
            if (backupIndex < media.backups.size()) {
                const int start = loaded ? boundedTime(MPVCore::instance().video_progress) : 0;
                loaded = false;
                MPVCore::instance().setUrl(media.backups[backupIndex++], playbackOptions(media, start));
            } else {
                save();
                loaded = false;
                fail("播放失败，按 Y 重新取流或按 B 返回选集");
            }
        }
    });
    subscribed = true;
    brls::Application::giveFocus(video);
    play(index, true);
}

void PlayerActivity::play(size_t selectedIndex, bool restore) {
    if (selectedIndex >= detail.episodes.size()) return;
    save();
    job.cancel();
    warmJob.cancel();
    loaded = false;
    loading = true;
    completed = false;
    video->stop();
    index = selectedIndex;
    media = {};
    backupIndex = 0;
    lastSaved = -1;
    const auto episode = detail.episodes[index];
    video->setTitle(detail.drama.title + " · " + episode.title);
    video->setOnlineCount("正在获取播放地址…");
    video->showLoading();
    video->showOSD(false);
    if (warmed && warmedIndex == selectedIndex) {
        const Media cached = *warmed;
        warmed.reset();
        warmedIndex = std::numeric_limits<size_t>::max();
        beginPlayback(cached, restore, episode);
        return;
    }
    warmed.reset();
    warmedIndex = std::numeric_limits<size_t>::max();
    const std::string src = resolveSource(detail.drama);
    const auto source = provider(src);
    const auto dramaId = detail.drama.id;
    job.run([source, dramaId, episode](const Cancellation& token) { return source->resolve(dramaId, episode, token); },
        [this, restore, episode](const Media& result) { beginPlayback(result, restore, episode); },
        [this](const std::string& message) { loading = false; fail(message); });
}

void PlayerActivity::beginPlayback(const Media& result, bool restore, const Episode& episode) {
    media = result;
    loading = false;
    const auto history = library().progress(detail.drama.id);
    const int start = restore && history.episode.id == episode.id && !history.completed ? std::max(0, history.seconds - 3) : 0;
    video->setOnlineCount("ZL 上一集 · ZR 下一集 · Y 重试 · B 选集");
    MPVCore::instance().setUrl(media.url, playbackOptions(media, start));
}

void PlayerActivity::warmNext() {
    const size_t nextIndex = index + 1;
    if (nextIndex >= detail.episodes.size()) return;
    if (warmed && warmedIndex == nextIndex) return;
    const std::string src = resolveSource(detail.drama);
    const auto source = provider(src);
    const auto dramaId = detail.drama.id;
    const auto episode = detail.episodes[nextIndex];
    warmJob.run([source, dramaId, episode](const Cancellation& token) { return source->resolve(dramaId, episode, token); },
        [this, nextIndex](const Media& result) {
            warmed = result;
            warmedIndex = nextIndex;
        }, [](const std::string&) {});
}

void PlayerActivity::fail(const std::string& message) {
    video->hideLoading();
    video->setOnlineCount(message);
    video->setTitle(detail.drama.title + " · " + detail.episodes.at(index).title + " · 暂无法播放");
    video->showOSD(false);
    brls::Application::notify(message);
}

void PlayerActivity::next(int direction) {
    if (direction < 0 && index > 0) play(index - 1, false);
    else if (direction > 0 && index + 1 < detail.episodes.size()) play(index + 1, false);
}

void PlayerActivity::save(bool finished) {
    if (!loaded || loading || index >= detail.episodes.size()) return;
    completed = completed || finished;
    const int seconds = boundedTime(MPVCore::instance().video_progress);
    try {
        library().record({detail.drama, detail.episodes[index], seconds, boundedTime(MPVCore::instance().duration), completed});
        lastSaved = seconds;
    } catch (const std::exception& exception) {
        lastSaved = seconds;
        if (!saveErrorShown) brls::Application::notify(exception.what());
        saveErrorShown = true;
    }
}

PlayerActivity::~PlayerActivity() {
    job.cancel();
    warmJob.cancel();
    if (subscribed) MPV_E->unsubscribe(subscription);
    save();
    if (video) video->stop();
}
}
