#!/usr/bin/env python3
"""
DBI Backend for Nintendo Switch - macOS CLI
Communicates with DBI homebrew over USB to install NSP, NSZ, XCI, and XCZ files.
"""

import sys
import os
import time
import struct
import argparse
from pathlib import Path

try:
    import usb.core
    import usb.util
except ImportError:
    print("Error: PyUSB is required. Install it using 'pip install pyusb'.")
    sys.exit(1)

CMD_ID_EXIT = 0
CMD_ID_LIST_OLD = 1
CMD_ID_FILE_RANGE = 2
CMD_ID_LIST = 3

CMD_TYPE_REQUEST = 0
CMD_TYPE_RESPONSE = 1
CMD_TYPE_ACK = 2

BUFFER_SEGMENT_DATA_SIZE = 0x100000  # 1 MB chunk

VALID_EXTENSIONS = {'.nsp', '.nsz', '.xci', '.xcz'}

def scan_files(paths):
    file_map = {}
    for p in paths:
        path_obj = Path(p).expanduser().resolve()
        if path_obj.is_file():
            if path_obj.suffix.lower() in VALID_EXTENSIONS:
                file_map[path_obj.name] = path_obj
        elif path_obj.is_dir():
            for f in sorted(path_obj.rglob('*')):
                if f.is_file() and f.suffix.lower() in VALID_EXTENSIONS:
                    file_map[f.name] = f
    return file_map

def format_size(bytes_num):
    for unit in ['B', 'KB', 'MB', 'GB', 'TB']:
        if bytes_num < 1024.0:
            return f"{bytes_num:3.1f} {unit}"
        bytes_num /= 1024.0
    return f"{bytes_num:.1f} PB"

def connect_to_switch(vendor_id=0x057E, product_id=0x3000):
    last_prompt = 0
    while True:
        dev = usb.core.find(idVendor=vendor_id, idProduct=product_id)
        if dev is not None:
            try:
                dev.set_configuration()
            except Exception:
                pass
            return dev

        mtp_dev = usb.core.find(idVendor=vendor_id, idProduct=0x201d)
        normal_dev = usb.core.find(idVendor=vendor_id, idProduct=0x2000)
        curr_time = time.time()
        if curr_time - last_prompt > 3:
            if mtp_dev is not None:
                print("⚡ 检测到 Switch 当前正处于【DBI MTP 模式】！")
                print("👉 请在 Switch 手柄上按【X 键】退出 MTP，并在 DBI 主菜单中选择：【Install title from DBIbackend】(从 DBIbackend 安装)")
            elif normal_dev is not None:
                print("⚡ 检测到 Switch 已连接 (普通模式)，正在等待进入 DBI Backend...")
                print("👉 请在 Switch 上打开 DBI 并在主菜单中选择：【Install title from DBIbackend】(从 DBIbackend 安装)")
            else:
                print("⏳ 等待 Switch 连接... 请确保 USB 线已连接并在 Switch 上打开 DBI -> 选择【Install title from DBIbackend】")
            last_prompt = curr_time
        time.sleep(1)

def run_backend_cli(file_list):
    total_size = sum(f.stat().st_size for f in file_list.values())
    print("\n" + "=" * 65)
    print(" 🎮 DBI Backend USB 安装服务 (macOS)")
    print("=" * 65)
    print(f"📦 发现有效安装包数量: {len(file_list)} 个 (总容量: {format_size(total_size)}):")
    for idx, (name, path) in enumerate(sorted(file_list.items()), 1):
        sz = format_size(path.stat().st_size)
        print(f"  {idx:2d}. [{sz:>9s}] {name}")
    print("-" * 65)

    last_file_reported = None

    while True:
        dev = connect_to_switch()
        print("\n🎉 成功与 Switch DBI 建立 USB 连接！正在监听安装请求...")

        try:
            while True:
                try:
                    cmd_header = bytes(dev.read(0x81, 16, timeout=0))
                except usb.core.USBError as e:
                    print(f"\n⚠️ USB 通信中断: {e}")
                    break

                magic = cmd_header[:4]
                if magic != b'DBI0':
                    continue

                cmd_type = struct.unpack('<I', cmd_header[4:8])[0]
                cmd_id = struct.unpack('<I', cmd_header[8:12])[0]
                data_size = struct.unpack('<I', cmd_header[12:16])[0]

                if cmd_id == CMD_ID_EXIT:
                    print("\n✅ Switch DBI 发送退出指令，安装完成或连接关闭。")
                    try:
                        dev.write(0x01, struct.pack('<4sIII', b'DBI0', CMD_TYPE_RESPONSE, CMD_ID_EXIT, 0))
                    except Exception:
                        pass
                    return

                elif cmd_id == CMD_ID_LIST:
                    print("📋 Switch 请求获取游戏列表，正在同步...")
                    nsp_path_list = "\n".join(file_list.keys()) + "\n"
                    nsp_path_list_bytes = nsp_path_list.encode('utf-8')
                    nsp_path_list_len = len(nsp_path_list_bytes)

                    dev.write(0x01, struct.pack('<4sIII', b'DBI0', CMD_TYPE_RESPONSE, CMD_ID_LIST, nsp_path_list_len))
                    if nsp_path_list_len > 0:
                        ack = bytes(dev.read(0x81, 16, timeout=0))
                        dev.write(0x01, nsp_path_list_bytes)
                    print(f"✨ 游戏列表已同步 (共 {len(file_list)} 项)！")
                    print("👉 请在 Switch 屏幕上勾选要安装的游戏/补丁/DLC，并按 A 开始安装！\n")

                elif cmd_id == CMD_ID_FILE_RANGE:
                    dev.write(0x01, struct.pack('<4sIII', b'DBI0', CMD_TYPE_ACK, CMD_ID_FILE_RANGE, data_size))
                    file_range_header = dev.read(0x81, data_size)

                    range_size = struct.unpack('<I', file_range_header[:4])[0]
                    range_offset = struct.unpack('<Q', file_range_header[4:12])[0]
                    nsp_name_len = struct.unpack('<I', file_range_header[12:16])[0]
                    nsp_name = bytes(file_range_header[16:16+nsp_name_len]).decode('utf-8', errors='ignore')

                    file_path = file_list.get(nsp_name)
                    if not file_path or not file_path.exists():
                        print(f"\n❌ 找不到请求的文件: {nsp_name}")
                        break

                    total_file_size = file_path.stat().st_size
                    if last_file_reported != nsp_name:
                        if last_file_reported is not None:
                            print(f"\n✅ 前序传输完成")
                        print(f"\n📥 正在安装: {nsp_name} ({format_size(total_file_size)})")
                        last_file_reported = nsp_name

                    pct = ((range_offset + range_size) / total_file_size) * 100 if total_file_size > 0 else 0
                    print(f"   🚀 传输进度: {format_size(range_offset + range_size)} / {format_size(total_file_size)} ({pct:5.1f}%)", end='\r')

                    response_bytes = struct.pack('<4sIII', b'DBI0', CMD_TYPE_RESPONSE, CMD_ID_FILE_RANGE, range_size)
                    dev.write(0x01, response_bytes)

                    ack = bytes(dev.read(0x81, 16, timeout=0))

                    with open(file_path, 'rb') as f:
                        f.seek(range_offset)
                        curr_off = 0
                        end_off = range_size
                        read_size = BUFFER_SEGMENT_DATA_SIZE

                        while curr_off < end_off:
                            if curr_off + read_size >= end_off:
                                read_size = end_off - curr_off
                            buf = f.read(read_size)
                            dev.write(0x01, data=buf, timeout=0)
                            curr_off += read_size

        except KeyboardInterrupt:
            print("\n🛑 用户手动中断服务。")
            return

def main():
    parser = argparse.ArgumentParser(description="DBI Backend installer for Nintendo Switch on macOS")
    parser.add_argument("paths", nargs="*", default=["/Volumes/Damao/Switch Games"],
                        help="Path(s) to game files or directories")
    args = parser.parse_args()

    files = scan_files(args.paths)
    if not files:
        print(f"❌ 在指定路径中未找到任何 .nsp / .nsz / .xci / .xcz 文件: {args.paths}")
        sys.exit(1)

    run_backend_cli(files)

if __name__ == "__main__":
    main()
