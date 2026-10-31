# 红果短剧 (Hongguo) 跨平台与 Nintendo Switch 移植工程

本项目包含红果短剧在 Nintendo Switch 平台上的移植客户端源码、解密性能优化实现、DBI MTP 直连安装工具及 Android 原版逆向工程分析。

## 目录结构

- **`guo-switch/`**：Nintendo Switch 客户端工程
  - `wiliwili/source/guo/`：短剧业务逻辑、页面与接口请求实现
  - `library/`：Switch 平台图形与基础库（Borealis、OpenCC、CPR、Mongoose 等）
  - `diagnostics/nsp-repair-20261029/`：
    - `optimization-v120/`：启播延迟优化与 AArch64 原生 AES-CTR 批量解密桥
    - `package_nsp.py`：NSP 自动化打包与校验脚本
    - `build_patched_main.py`：AArch64 汇编补丁构建器
- **`[v1.2.4]红果短剧.nsp`**：当前最新 Nintendo Switch 安装包（v1.2.4，包含 AArch64 批量解密桥、首集预热优化及 fix-rc6 并发流请求防风暴优化）
- **`dbi_mtp_installer` & `dbi_mtp_installer.c`**：针对 Switch DBI MTP Responder 开发的免弹窗高速安装器
- **`MiniReel-0.4.0-REA-analysis/`**：MiniReel Android TV 原版客户端逆向分析报告与接口签名对照
- **`MiniReel-0.4.0-android-tv-armeabi-v7a.apk`**：作为协议与功能对照的 Android TV 版本测试 APK

## 安装与部署

可通过 DBI MTP 直接将 NSP 安装至 Switch：

```bash
./dbi_mtp_installer "[v1.2.4]红果短剧.nsp"
```
