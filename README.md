# 红果短剧 (Hongguo) Nintendo Switch 移植工程

本项目包含红果短剧在 Nintendo Switch 平台上的独立 NSP 安装包、完整工程源码（RomFS / ExeFS / Control）、AArch64 原生硬件解密优化、DBI MTP 直连安装工具及 Android 原版逆向工程分析。

## 目录结构

- **`[v1.2.5]红果短剧.nsp`**：当前最新 Nintendo Switch 独立安装包（v1.2.5，Title ID: `0100F00D53414600`，Title Version: `786432`，集成 AArch64 AES-CTR 硬件加速解密、首集预热优化及 fix-rc6 并发流请求防风暴优化）。
- **`nsp-source/`**：`[v1.2.4]红果短剧.nsp` 的完整原始文件工程与重打包工具链：
  - `control/`：NACP 应用信息与主屏高清图标。
  - `exefs/`：集成了 AArch64 AES-CTR 原生解密补丁的 `main` 可执行文件与 `main.npdm` 权限描述。
  - `romfs/`：QuickJS 运行时、`adapter.js` (fix-rc6)、`phone_source.js` (接口与签名实现)、资源及证书文件。
  - `tools/`：macOS 原生 `hacbrewpack` 打包二进制与源码。
  - `build_patched_main.py`：AArch64 汇编补丁注入脚本（为 NSO 二进制注入原生硬件解密指令）。
  - `package_nsp.py`：一键重打包脚本（自动化产出字节完全一致的 NSP）。
- **`dbi_mtp_installer` & `dbi_mtp_installer.c` & `dbi_backend.py`**：针对 Switch DBI MTP Responder 开发的免弹窗高速直连安装器。
- **`MiniReel-0.4.0-android-tv-armeabi-v7a.apk`**：红果短剧 Android TV 原版客户端 APK。
- **`MiniReel-0.4.0-REA-analysis/`**：MiniReel Android TV 原版客户端的逆向工程分析报告与接口签名对照。

## 安装与部署

在 Switch 启动 DBI 并开启 MTP Responder 后，连接 USB 数据线，在终端运行：

```bash
./dbi_mtp_installer "[v1.2.4]红果短剧.nsp"
```

## 重新打包 NSP

如需对 RomFS 前端逻辑或 ExeFS 进行调整，可在 `nsp-source` 目录下执行：

```bash
cd nsp-source
python3 package_nsp.py
```
