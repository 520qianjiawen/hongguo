# 红果短剧修复版 1.1.4 测试包

日期：2026-10-29（America/New_York）。用户要求优先修复指定的 1.1.3 NSP 并通过 DBI 更新，待其测试；GuoSwitch 项目业务代码暂未修改。

## 交付

新包：`/Users/cavon/Downloads/[v1.1.4]红果短剧修复版[0100F00D53414600].nsp`

- 桌面名称：红果短剧修复版。
- NACP 显示版本：1.1.4。
- CNMT 内容版本：65536。
- Title ID：0100F00D53414600，与原包相同。
- SHA-256：`362a5084380ff07b0435126dd7576420b26c9ec80320d808c2fb1dbc3c263226`。
- 原 1.1.3 NSP 保留；程序 NSO、NPDM 和控制权限保持原字节。原生运行日志中的硬编码版本仍可能显示 1.1.3，以桌面属性中的 1.1.4 为准。

已检测到 DBI USB/MTP，向 **5: SD Card install** 安装通道发送 21,785,816 bytes，DBI/MTP 返回 `UPLOAD status 0`。这不是普通 SD 文件复制。当前 MTP 日志仍打开，回读安装日志失败；因此设备界面最终状态与实机播放仍需用户确认，不能把桌面解码测试称为实机验收。[传输回执](installation-receipt.json)。

退出 DBI/MTP 后打开“红果短剧修复版”，优先测试同一剧的第 1、2 集，切集和拖动；如失败，可回读 `switch/hongguo-saf/runtime.log`。同 Title ID 允许沿用原应用数据，测试中未改动收藏与历史文件。

## 实际修复

本轮只修改 ROMFS 的 `phone_source.js`：

1. 按用户 APK 0.4.0 包内配置更新 App 客户端资料为 73932 / 7.3.9.32。
2. 请求设备注册接口并使用服务端返回的 device_id 与 install_id；进程内合并注册请求，不再把随机生成的 19 位数字当作有效设备信息。设备标识只留在内存，诊断文件未记录其值。
3. 将已复现空响应的单视频接口改为 `/novel/player/multi_video_model/v1/`，使用 `mixed_video_id_map: {1004: [所选分集]}` 与 `video_platform=1024`。
4. 配套发送 RC4 变体 X-Gorgon、X-Argus、X-Ladon、时间戳、请求票据与正文 MD5；签名覆盖实际编码的 query 和正文。当前通过验证的 Gorgon nonce 为 12345，时间和注册身份随请求/进程生成。
5. 正确读取返回 `data[分集ID].video_model`，支持字符串/对象模型及 fallback 模型；仅选择请求 ID 对应项，不以模型内部的 CDN video_id 错判分集。
6. 兼容顶层与 encrypt_info 内的 spade_a、顶层/嵌套编码与画质字段，以及普通、Base64 和带 seed 的媒体地址。
7. 对 HTTP 200 空正文做有限的两次重试并返回明确错误。

原包已有的 MP4/CENC 分段解密与播放器代码未改动。PhoneSource.contentKey 的严格校验保留，未绕过不支持的 app_v2/web_v2 密钥格式。首页、搜索、详情保留原有官网回退；App 分类/详情目前仍可能返回空体，已确认官网回退可显示列表和完整分集。

公开保留源码用作协议对照，不冒充从 APK 恢复的原始源码：

- [MiniReel App 客户端](https://github.com/cartawi/minireel/blob/main/lib/data/sources/hongguo/hongguo_app_client.dart)
- [MiniReel 播放模型和内容密钥处理](https://github.com/cartawi/minireel/blob/main/lib/data/sources/hongguo/hongguo_playback_codec.dart)
- [HongguoTV 批量取流](https://github.com/qq294716498/HongguoTV-Phone-TV-NoAds/blob/8eafa3908e9191c0a5ae186d5beff11e3a971f3c/native/core/provider_hongguo_native_media.go)
- [HongguoTV 设备注册](https://github.com/qq294716498/HongguoTV-Phone-TV-NoAds/blob/8eafa3908e9191c0a5ae186d5beff11e3a971f3c/native/core/provider_hongguo_device.go)

## 验证

- **实际两集取流通过**：分集 `7701336636495252504` 与 `7701336720221932568` 分别返回其自身的播放模型，HTTP 206 Range 读取成功；完整媒体在临时目录解密后由 ffprobe 识别 HEVC/AAC，前 15 秒 FFmpeg 解码退出 0 且没有错误。临时媒体已删除。[记录](playback-validation.json)。
- **原生桥接兼容通过**：使用原包自己的 deps.js Buffer shim 与 prelude.js，以及对应的哈希/AES桥接约定，成功取流、解析视频头，并完成 301 bytes 的非对齐 Range 解密。启动 selftest 与原 1.1.3 完全相同。[记录](native-bridge-validation.json)。这仍是在本机 VM 模拟桥接，不是 Switch 实机运行。
- **导航通过**：首页回退获得 24 个条目，详情获得 96 集。[记录](navigation-validation.json)。
- **包结构校验通过**：对重打包结果重新解密，核对 Program、Control、CNMT 内容摘要与版本；NSO 和 NPDM 与原包完全相同，ROMFS 实际变化只有 phone_source.js。[记录](release-candidate-1/package-verification.json)。

测试取到的两个媒体分别包含 1080×1920 和 480×854 的 HEVC，Switch 硬件解码、持续播放和拖动表现仍由本次实机测试确认。

## 文件

- `modified-romfs/phone_source.js`：实际打包的修复代码；同目录其余三文件与原包相同。
- `package_nsp.py`：使用本地工具、原包提取物及当前 overlay 重打包，不覆盖原 NSP。
- `validate-playback.cjs`、`validate-native-bridge.cjs`：可重复验证脚本，运行会请求真实数据源。
- 其他 probe/reference 文件记录调查过程。`registered-player-sign.js` 是单独验证过的备用完整签名实现，本轮最终包没有采用它或 fullsign-compat.js；实际成功方案以上述 phone_source.js 为准。
- `smoke-unmodified` 是打包工具的原内容试验结果，**不是修复包，不能用于验收**。

只读分析与本机验证已完成，DBI 安装通道已返回成功。后续以用户的 Switch 实测结果继续排查。
