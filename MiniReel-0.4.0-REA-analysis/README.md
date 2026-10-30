# MiniReel 0.4.0 Android TV — REA 静态分析

分析日期：2026-10-29（America/New_York）。本报告针对用户指定 APK；未安装、运行或修改 APK，未访问包内网址，未测试第三方接口。

## 结论

这是一个采用 Flutter/Dart AOT 的 Android TV 应用。Android 入口及插件注册代码表明，其 Java 层主要负责 Flutter 宿主和媒体、设备控制、数据库插件；主要业务代码位于 `libapp.so`。

包内配置启用一个名为 `hongguo` 的数据源；Dart 二进制保留短剧搜索、目录、榜单、详情、播放、收藏、历史、WebDAV 同步以及 TV 遥控器相关模块名。模块名和字符串能够定位功能候选，但不能证明页面实际可达、接口可用或功能正确。

REA 已完成 APK 清单式归档检查、签名结构检查、Flutter 构建识别。REA 的 JADX MCP 与 apktool 后端不可用；本报告以已安装的独立 JADX CLI 补充 Android 清单解码和 Java 反编译。REA Dart 读取器不支持此包的统一快照结构，完整 Dart 源码和业务调用链未恢复。

## 1. 目标身份与兼容性（静态观察）

| 项目 | 结果 |
|---|---|
| 文件 | `MiniReel-0.4.0-android-tv-armeabi-v7a.apk` |
| 文件大小 | 33,477,843 bytes，约 31.93 MiB |
| SHA-256 | `54aa2113ea9b26d360ce56924ab14dc522f911c8000693c07909ff1462229c6e` |
| 应用名称 | MiniReel TV |
| 包名 | `app.minireel.minireel.tv` |
| 版本 | `0.4.0`，versionCode `1016` |
| minSdk / targetSdk / compileSdk | 24 / 36 / 36 |
| 实际打包的原生 ABI | 仅 `armeabi-v7a`，即 32 位 ARM |
| 入口 | `app.minireel.minireel.MainActivity` |
| 屏幕方向 | `landscape` |
| 启动器 | `LAUNCHER` 与 `LEANBACK_LAUNCHER` |
| 硬件要求 | touchscreen 与 leanback 均声明 `required=false` |

来源：[解码 Manifest](jadx/resources/AndroidManifest.xml)、[结构化摘要](manifest-summary.json)、REA Flutter 与签名证据、[本地成员哈希清单](member-inventory.json)。minSdk 24 对应 Android 7.0；实际安装与播放兼容性未验证。

ZIP 有 75 个成员。REA 归档图包含根节点在内的 76 个 occurrence，未报告完整性矛盾；这与本地 ZIP 成员计数的口径不同。

## 2. 权限与组件（静态观察）

完整清单中的 uses-permission 为：

- `android.permission.INTERNET`
- `android.permission.MODIFY_AUDIO_SETTINGS`
- 应用自定义签名级权限 `app.minireel.minireel.tv.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`

在这份完整清单中未声明相机、麦克风、位置、联系人或广泛存储权限。这仅描述权限声明，不是隐私或恶意软件判定。

组件声明：MainActivity 导出；AndroidX InitializationProvider 不导出；ProfileInstallReceiver 导出，但声明由 `android.permission.DUMP` 保护。未声明 service。

应用允许明文流量 `usesCleartextTraffic=true`。这说明平台配置允许 HTTP，不能证明应用实际发送了明文请求。Flutter Impeller 明确禁用。

来源：[Manifest](jadx/resources/AndroidManifest.xml)。

## 3. 技术栈与原生桥接

| 库 | 未压缩大小 | 可支持的结论 |
|---|---:|---|
| `libapp.so` | 9,847,368 | Dart AOT 业务载体 |
| `libflutter.so` | 8,615,900 | Flutter 引擎 |
| `libmpv.so` | 11,746,532 | 打包 mpv 播放库 |
| `libmediakitandroidhelper.so` | 286,812 | media_kit Android 辅助库 |
| `libdartjni.so` | 81,444 | Dart JNI 辅助库 |
| `libsqlite3.so` | 1,713,736 | 打包 SQLite 原生库 |

`MainActivity` 是极薄的宿主子类。其父类的反编译文本包含 FlutterActivity 的引擎与生命周期处理；插件注册器显式注册 jni、jni_flutter、media_kit_libs_android_video、media_kit_video、package_info_plus、screen_brightness_android、sqflite_android、volume_controller、wakelock_plus。

来源：[MainActivity](jadx/sources/app/minireel/minireel/MainActivity.java)、[插件注册器](jadx/sources/io/flutter/plugins/GeneratedPluginRegistrant.java)、[父类](jadx/sources/O/AbstractActivityC0007e.java)、[成员清单](member-inventory.json)。上述桥接注册有 Java 证据，播放器实际调用路径仍需运行或 Dart 语义证据。

REA 观察到 `libapp.so` 使用 `_kDartSnapshotText` / `_kDartSnapshotData` 统一快照导出。快照哈希候选为 `0451907c2eaa8467e848c0067bfe8ed4`；只观察到一个快照头，构建识别覆盖为 partial。`libflutter.so` GNU build-id 为 `c1eb5824e73454eb87bc61f347c1ae21d5d6aa1a`。未确定准确 Flutter/Dart SDK 版本。

## 4. 功能结构（字符串观察与推断）

本地扫描提取 22,643 条长度至少 5 的 ASCII 可打印序列，其中 140 条以 `package:minireel/` 开头。数量仅代表匹配字符串，不代表完整模块或类型数量。文件偏移均相对于解压后的 `lib/armeabi-v7a/libapp.so`。

| 功能候选 | 示例路径 / 字符串 | 文件偏移示例 |
|---|---|---|
| TV 导航与焦点 | `features/tv/tv_shell.dart`、`tv_focus.dart` | `0x2b6f7c`、`0x308914` |
| TV 搜索 | `features/tv/tv_search_screen.dart` | `0x2d861c` |
| TV 详情与播放 | `tv_detail_screen.dart`、`tv_player_screen.dart` | `0x2447d4`、`0x22ef6c` |
| TV 遥控器设置 | `tv_remote_key_settings.dart`、`RemoteKeyMap.fromJson` | `0x2dc68c`、`0x26cfac` |
| 推荐与榜单 | `features/feed/recommendation_policy.dart`、`features/tv/tv_rankings_screen.dart` | `0x285814`、`0x2cdf0c` |
| 收藏与历史 | 建表 SQL `favorites`、`history`，`watch_record.dart` | `0x2db344`、`0x2ab994`、`0x224ed4` |
| 本地目录索引 | `data/local/sqlite_catalog_index.dart` | `0x245614` |
| WebDAV 同步 | `data/sync/webdav_client.dart`、`webdav_sync_service.dart` | `0x2f254c`、`0x2f64f4` |
| 下一集预取 | `playback/next_episode_prefetch.dart`、`prefetchNextEpisode` | `0x2ed934`、`0x24688c` |
| 播放异常与诊断 | `native_error_guard.dart`、`playback_diagnostics.dart` | `0x3093e4`、`0x289464` |

高置信推断：应用包含针对电视交互的专用页面与焦点、按键处理设计。包内同时存在手机、桌面相关路径，不能据此断言它们在 TV 构建中可达。

候选分层为 UI → AppController / DramaRepository → Hongguo 数据源 / 本地存储 → 播放与同步服务。该分层依据命名和打包组成推断，尚未证明具体调用边。

来源：[全部 ASCII 观察](libapp-ascii-strings.json)、[应用模块候选](minireel-module-candidates.json)。字符串也可能是保留元数据、错误消息或不可达代码。

## 5. 数据源与网络

直接读取的配置 [sources.json](sources.json) 显示：

- 配置版本 1，只有一个数据源键 `hongguo`，`enabled=true`。
- 网页 baseUrl 为 `https://hongguoduanju.com`。
- App API baseUrl 为 `https://api5-normal-sinfonlineb.fqnovel.com`。
- 媒体 Referer 配置为 `https://novel.snssdk.com/`。
- maxPages 20，pageSize 24，timeoutSeconds 12，retries 3。
- 存在模拟 App 客户端的 User-Agent 和设备/版本参数；appHeaders 在该文件中为空对象。

这些是配置字面值；尚未验证读取优先级、运行时覆盖、各请求的使用方式或当前服务可用性。

二进制另外保留以下请求路径候选：

| 字符串 | 文件偏移 |
|---|---|
| `/reading/distribution/category/landpage/v/` | `0x24dd14` |
| `/novel/player/video_detail/v1/` | `0x257c3c` |
| `/novel/player/multi_video_model/v1/` | `0x29763c` |
| `/incent_resource/suggestion` | `0x29aaa4` |
| `https://log.snssdk.com/service/2/device_register/` | `0x24896c` |

还观察到 `hongguo_signer.dart`、`signHongguoRequest`、`HongguoPlaybackCodec`、`X-Gorgon` / `X-Argus` / `X-Ladon` / `X-Khronos` 等名称。它们提示存在请求签名与播放数据处理代码，不能证明算法正确、每个请求都使用这些头，或服务器接受请求。

WebDAV 相关模块与 `Authorization`、`Basic `、`MKCOL` 字符串共存，提示同步实现可能包含 Basic 认证及目录创建；仅凭共存不能证明具体请求关系。`https://dav.example.com/remote.php/dav` 是包内示例 URL，不是已验证的用户服务器。

来源：[配置原文](sources.json)、[URL 候选](url-string-candidates.json)、[字符串与偏移](libapp-ascii-strings.json)。没有调用、注册设备、获取视频或提交任何凭据。

## 6. 本地数据

二进制包含 `minireel.db`，以及以下 SQL 定义字符串：

- `settings(key, value)`
- `details(id, payload, updated_at)`
- `history(id, payload, updated_at)`
- `favorites(id, payload, created_at)`
- `catalog(id, source, payload)`
- `catalog_entries(catalog_key, drama_id)`
- `catalog_tags(drama_id, tag, label)`

还存在目录索引、字段迁移和历史记录清理 SQL，其中一条清理语句保留按更新时间排序的前 200 条 history。以上证明 SQL 字符串存在；实际数据库版本、完整 schema、迁移顺序、清理触发条件与用户数据均未观察。

## 7. 签名结构

REA 发现 APK Signature Scheme v2，一个声明的签名者，证书 SHA-256 指纹：

`0790b5e1da4c162ef8c8b8f13ac47a10740081c0ba74226c098f951c44e99d34`

声明内容摘要与当前 APK 字节匹配。未观察到 v1/v3/v3.1 签名记录或 source stamp。REA 报告 4 个未解释的签名字段外字节，不能仅据此判定畸形。

此检查不进行密码学签名验证、证书信任链或发布者真实性验证，不能称为“官方正版”或“安全认证”。

## 8. 证据与覆盖边界

[rea-evidence.json](rea-evidence.json) 导出了 6 条 REA Evidence。主要父证据：

| 检查 | Evidence ID |
|---|---|
| APK 归档检查 | `ev_d8294a9953a354dfd57e78e1dd4cafab9fbb8acb76d413a9b70b8565306c4a87` |
| 成员 inventory | `ev_f3699d277e34c8e865fdf0e0da889223e9850928a1ad666df01aee3f03ee569d` |
| APK 签名结构 | `ev_6357f3a52de04032c445c95e09569ae2c635bc921339d59a00d688e9727fd589` |
| Flutter 构建识别 | `ev_692180e22fa39b526ab0de91a607c8826a2abd31dd4ba1619fe6f1e29f7ac6f3` |

Android 清单、Java 反编译和自写扫描结果是本地工具补充证据，不冒充 REA Evidence。

| 调查问题 | 状态 | 剩余未知 |
|---|---|---|
| 精确文件身份、归档组成 | 已回答 | 不代表运行行为 |
| SDK、权限、入口与导出组件 | 已回答（独立 JADX） | 实机安装和系统行为 |
| Flutter 与原生依赖 | 已回答 | 精确 Flutter/Dart 版本 |
| 数据源默认配置 | 已回答 | 运行时覆盖与接口可用性 |
| 页面、播放、收藏、历史、同步设计 | 部分回答 | 页面可达性与具体业务调用链 |
| Java 源码表示 | 部分回答 | JADX 报告 6 个错误；存在失败方法 |
| Dart 业务源码与签名/解码算法 | 未恢复 | 统一快照需要兼容解析器/原生分析或匹配源码 |
| 播放、搜索、同步实际效果 | 未验证 | 需要明确运行场景与 Android 环境 |
| 签名真实性与应用安全 | 未验证 | 需要额外验证；本次不是完整安全审计 |

REA 后端原始失败信息见 [rea-provider-status.json](rea-provider-status.json)：缺少指定版本的 jadx-headless-mcp JAR；apktool launcher 不存在；统一 Dart 快照不受此读取器支持。没有更改 REA 配置或安装依赖。

独立 JADX 输出保留于 [jadx](jadx)，日志见 [jadx.log](jadx.log)。其退出码为 3，报告 6 个反编译错误，不能把输出视为完整原始源码。可见错误标记位于 `A0/g.java`、`p013n/g.java`、`Y/o.java`，错误总数不等于失败文件数。

包内 License 声明 `PolyForm Noncommercial License 1.0.0`，并带有 MiniReel GitHub 项目的 Required Notice。仅记录打包声明，未核验仓库是否存在或与本 APK 匹配。[License 原文](License) 已保留。

## 9. 复核方式

`python3 scan_apk.py` 可重新生成本地成员哈希、配置提取、ASCII 字符串和 URL 候选。它不执行 APK；扫描覆盖只包含 ASCII 序列，不完整覆盖 UTF-16 中文字符串、压缩/编码数据或 Dart 对象语义。

本次交付完成首轮静态映射；未开展文档示例中的重建、双版本比较或动态对照。
