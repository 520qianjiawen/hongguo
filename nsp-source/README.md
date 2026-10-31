# 红果短剧 Nintendo Switch NSP 源码工程

本目录包含了用于构建与打包 `[v1.2.4]红果短剧.nsp` (Title ID: `0100F00D53414600`) 的完整原始文件与工具链。

## 目录结构

- `control/`: 应用元数据与图标
  - `control.nacp`: 应用标题、版本号 (v1.2.4)、发布者信息
  - `icon_AmericanEnglish.dat`: 主屏图标 (256x256 JPEG)
- `exefs/`: 可执行二进制与权限配置
  - `main`: AArch64 原生可执行文件，集成了 AArch64 AES-CTR 硬件加速解密补丁
  - `main.npdm`: 进程权限与能力描述文件
- `romfs/`: 运行时环境与前端逻辑 (QuickJS / Borealis)
  - `adapter.js`: 视频流调度、解析并发控制 (fix-rc6)
  - `phone_source.js`: 红果短剧 App 接口与加密协议 (签名算法与视频模型解析)
  - `prelude.js`: QuickJS 与原生层桥接
  - `deps.js`: 基础库
  - `cacert.pem`: SSL 证书
  - `hongguo.cfg`: 运行时配置
  - 资源文件 (`i18n`, `img`, `licenses`, `material`)
- `fix-rc6/`: RomFS 核心修复补丁与说明
- `build_patched_main.py`: AArch64 汇编补丁注入脚本 (为 NSO `main` 注入原生硬件解密指令)
- `tools/`:
  - `hacbrewpack`: macOS AArch64/x86_64 原生打包工具
  - `hacbrewpack-src/`: hacBrewPack 源码备用
- `package_nsp.py`: 一键重打包 NSP 脚本

## 打包说明

确保 Switch `prod.keys` 存在（默认路径 `/Volumes/Damao/Switch_CFW_Backup/switch/prod.keys` 或 `~/.switch/prod.keys`）：

```bash
python3 package_nsp.py
```

执行后将自动调用 `hacbrewpack` 生成与官方交付完全一致的 `[v1.2.4]红果短剧.nsp`。
