# GuoSwitch 红果源播放故障检查

检查时间：2026-10-29，America/New_York。用户确认的项目为 `/Users/cavon/Downloads/guo-switch`。

## 结论

当前红果源的网页取流实现与官网实际返回结构不匹配，且未接入 App 取流链路。本次抽查 3 部剧的前两集，共 6 个播放页，全部返回 `protectedPlayback=true`，全部缺少 `video_player_info`。当前实现只从这个旧字段读取地址，因此均在进入 MPV 前失败。

本次没有改动业务源码、NRO 或安装文件。新增内容仅为此目录内的诊断脚本、脱敏响应样本、复现程序和报告。没有运行 APK、修改 Switch 系统或请求登录。

## 直接证据

### 当前源码的失败路径

`wiliwili/source/guo/provider.cpp`：

1. 第 313 行：只请求官网 `/player/{seriesId}/{episodeId}`。
2. 第 318 行：只读取 `video_player_info`；缺失时得到空对象。
3. 第 319 行：若旧字段包含加密标记，直接报“该集需要 App 取流，当前版本尚未接入”。
4. 第 328 行：只从旧字段读取 main_url / backup_url 等公开地址，支持普通 HTTP URL 或 Base64 URL。
5. 第 334 行：地址列表为空，报“该集没有公开播放地址，可能需要登录或 App 授权”。

目前没有处理本次网页返回的 `protectedPlayback`。该标记不是播放地址；把字段名直接替换过去无法补出媒体 URL。

`wiliwili/source/guo/activities.cpp` 第 388–396 行表明，只有 resolve 成功才调用 `MPVCore::setUrl`。本次 6 个样本在 resolve 内就抛异常，因此这些失败样本尚未涉及 Switch 视频解码、画面输出或媒体 CDN 连接。它们不能排除修好取流后另有播放问题。

### 实际网页抽查

以项目 `http.cpp` 相同的 User-Agent、Referer、Accept、Accept-Language 请求官网详情和播放页，没有使用账号、Cookie 或 App 签名。检查包含 3 次详情请求与 6 次播放页请求。

| 剧名 | 剧集总数 | 抽查 | protectedPlayback | video_player_info | 当前 C++ 结果 |
|---|---:|---|---|---|---|
| 一念沉沦，霍总心动了 | 96 | 第 1、2 集 | 两集均 true | 两集均缺失 | 两集均无公开播放地址 |
| 王妈在顾家当保姆后全家只会搞笑了 | 86 | 第 1、2 集 | 两集均 true | 两集均缺失 | 两集均无公开播放地址 |
| 归路 | 78 | 第 1、2 集 | 两集均 true | 两集均缺失 | 两集均无公开播放地址 |

6 个样本的返回 series_id 和 vid 均与请求相符，因此本次没有触发“只返回试看集”的 ID 不匹配分支。第一集页面的 publicVideoSeo 仅包含 poster、时长和尺寸等元数据，没有媒体播放 URL；第二集该字段为空或缺失。

官网内容入口：[真人剧分类](https://hongguoduanju.com/category/real-drama?page=1)。样本详情：[一念沉沦](https://hongguoduanju.com/detail?series_id=7701334722449787928)、[王妈](https://hongguoduanju.com/detail?series_id=7694653385668037657)、[归路](https://hongguoduanju.com/detail?series_id=7699683909419666457)。

页面形状和来源摘要在 [web-probe-results.json](web-probe-results.json)。脱敏的 fixture JSON 保留实际页面字段结构，删除非空 URL 和敏感字段值。

### 用项目原代码验证

[reproduce_provider.cpp](reproduce_provider.cpp) 与项目原 `provider.cpp` 一起使用本机 Clang 编译，注入这 6 份脱敏响应。未改写 resolve 算法。结果：**6/6 均准确复现“该集没有公开播放地址，可能需要登录或 App 授权”**，每例仅发生一次官网播放页请求。复现程序退出码 0；表示预期失败被复现，不表示播放成功。

结果保存在 [provider-reproduction.json](provider-reproduction.json)。编译仅出现项目内 nlohmann/json 的 literal-operator 弃用警告。

本地 `dist/switch/guoswitch/guoswitch.nro` 也包含“当前版本尚未接入”和“没有公开播放地址”两条错误字符串。它的 SHA-256 为 `6bc8aa50536bb2a5777c8b454448c825f895d414ad5c405773fc24aa4556b56c`。字符串一致不等于完整源码/二进制对应证明，也未核验主机实际安装的是这个 NRO。

## 为什么 APK 可以，当前 Switch 源不行

用户报告 MiniReel APK 可以播放。对这个 APK 的前轮静态分析确实发现另一套取流实现线索：

| 环节 | 当前 GuoSwitch 红果源 | MiniReel APK 静态证据 |
|---|---|---|
| 默认来源 | 官网网页 SSR | 配置含官网与 App API baseUrl |
| 播放接口 | `/player/{series}/{episode}` | `/novel/player/video_detail/v1/`、`/novel/player/multi_video_model/v1/` 字符串 |
| App 客户端与签名 | 红果 resolve 中没有 App 请求分支 | `hongguo_app_client.dart`、`hongguo_device.dart`、`hongguo_signer.dart`、`signHongguoRequest` |
| 播放数据解析 | 普通 URL / Base64 解码 | `hongguo_playback_codec.dart`、`HongguoPlaybackCodec` |
| 加密标记 | 明确拒绝，提示尚未接入 | APK 包含相关处理模块名，具体算法未恢复 |
| 媒体请求 Referer | 当前返回 `https://hongguoduanju.com/` | 配置 `mediaReferer=https://novel.snssdk.com/` |

APK 分析目录为 `/Volumes/Damao/APP/guo/MiniReel-0.4.0-REA-analysis`。其中 `sources.json` 为实际包内配置，`libapp-ascii-strings.json` 保留文件偏移。REA Evidence 与限制已在该目录报告记录。

这些差异支持“APK 使用不同取流链路”的判断，但 APK 未在本次运行，不能把模块名当作已验证的完整请求链或算法。Referer 和 User-Agent 差异属于后续媒体兼容性检查点；本次的直接失败在媒体地址产生之前。

## 修复范围

1. 优先补齐红果的 App 取流回退：按准确分集 ID 请求播放模型，并处理设备信息、请求签名及当前返回结构；不能仅替换主机名或提高超时。
2. 核实并实现播放地址/播放模型的解析；若媒体存在加密，需明确播放器所需的解密参数或代理处理。当前 `Media` 只有 URL、Referer、备选 URL 和 Cookie，当前播放选项没有接收红果内容密钥的路径。
3. 保持请求与返回分集一致性检查，避免把返回第一集误当作后续集成功。
4. 对 protectedPlayback 和缺失 video_player_info 单独诊断，明确提示当前来源能力，避免把结构缺失笼统归因为登录。
5. 接入后逐项验证：前两集、后段集、切集、拖动、重新取流；再核查媒体 Referer、User-Agent、URL 有效期、备选 CDN、Range 与 Switch 解码。

本次诊断已定位可复现的取流缺口，没有声称已修复或实机验收。完整接入仍需取得可核实的 App 取流/解码实现或运行请求证据。此前另外一个红果 NSP 的 saf:// 与 HTTP code 0 日志不用于推断本项目的根因。

## 复核

运行 `python3 probe_web.py` 会对固定的 3 部剧重新发出这 9 个只读网页请求，覆盖本目录的诊断事实与脱敏样本。响应结构可能随时间或网络环境变化。

复现程序编译方式：

```sh
clang++ -std=c++17 -O0 \
  -I /Users/cavon/Downloads/guo-switch/wiliwili/include \
  -I /Users/cavon/Downloads/guo-switch/library/borealis/library/include/borealis/extern \
  reproduce_provider.cpp \
  /Users/cavon/Downloads/guo-switch/wiliwili/source/guo/provider.cpp \
  -o reproduce_provider
./reproduce_provider fixture-*.json
```

以上命令在本诊断目录运行。它只调用当前 Provider 处理保存的样本，不访问网络或启动播放器。
