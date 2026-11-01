#!/usr/bin/env python3
"""
Package Hongguo Switch NSP from local source directory (control, exefs, romfs).
Supports version bumping, updating NACP and CNMT title_version.
"""
from __future__ import annotations

import argparse
import os
from pathlib import Path
import shutil
import subprocess
import sys

ROOT = Path(__file__).resolve().parent
PROJECT_ROOT = ROOT.parent
DEFAULT_KEYS = Path('/Volumes/Damao/Switch_CFW_Backup/switch/prod.keys')
DEFAULT_TOOL = ROOT / 'tools' / 'hacbrewpack'


def patch_nacp_version(nacp_path: Path, display_version: str):
    data = bytearray(nacp_path.read_bytes())
    if len(data) != 16384:
        raise ValueError(f'Invalid NACP size: {len(data)}')
    ver_bytes = display_version.encode('utf-8')[:15]
    data[0x3060:0x3070] = ver_bytes.ljust(16, b'\x00')
    nacp_path.write_bytes(data)


def package(keys_path: Path, output_nsp: Path, display_version: str = '1.2.5', cnmt_version: int = 786432, tool_path: Path = DEFAULT_TOOL):
    if not tool_path.is_file():
        raise FileNotFoundError(f'hacbrewpack binary not found at {tool_path}')
    if not keys_path.is_file():
        user_keys = Path.home() / '.switch' / 'prod.keys'
        if user_keys.is_file():
            keys_path = user_keys
        else:
            raise FileNotFoundError(f'prod.keys not found at {keys_path}')

    staging = ROOT / '.build_temp'
    if staging.exists():
        shutil.rmtree(staging)
    staging.mkdir(parents=True)

    nsp_dir = staging / 'nsp'
    nca_dir = staging / 'nca'
    temp_dir = staging / 'temp'
    backup_dir = staging / 'backup'
    control_dir = staging / 'control'

    for d in (nsp_dir, nca_dir, temp_dir, backup_dir):
        d.mkdir(parents=True, exist_ok=True)

    # Copy control dir and patch NACP
    shutil.copytree(ROOT / 'control', control_dir)
    patch_nacp_version(control_dir / 'control.nacp', display_version)

    env = os.environ.copy()
    env['HACBREWPACK_TITLE_VERSION'] = str(cnmt_version)

    cmd = [
        str(tool_path),
        '-k', str(keys_path.resolve()),
        '--exefsdir', str(ROOT / 'exefs'),
        '--romfsdir', str(ROOT / 'romfs'),
        '--controldir', str(control_dir),
        '--nologo',
        '--nopatchnacplogo',
        '--nosignncasig2',
        '--keepncadir',
        '--nspdir', str(nsp_dir),
        '--ncadir', str(nca_dir),
        '--tempdir', str(temp_dir),
        '--backupdir', str(backup_dir)
    ]

    print(f'Running hacbrewpack for version {display_version} (CNMT {cnmt_version})...')
    res = subprocess.run(cmd, env=env, capture_output=True, text=True)
    if res.returncode != 0:
        print(res.stderr, file=sys.stderr)
        raise RuntimeError(f'hacbrewpack failed with code {res.returncode}')

    generated_nsp = nsp_dir / '0100f00d53414600.nsp'
    if not generated_nsp.is_file():
        raise FileNotFoundError(f'Expected NSP not created: {generated_nsp}')

    output_nsp.parent.mkdir(parents=True, exist_ok=True)
    shutil.copy2(generated_nsp, output_nsp)
    print(f'Successfully built: {output_nsp} ({output_nsp.stat().st_size} bytes)')

    # Also update nsp-source/control/control.nacp
    patch_nacp_version(ROOT / 'control' / 'control.nacp', display_version)

    shutil.rmtree(staging, ignore_errors=True)


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Build Hongguo Switch NSP package.')
    parser.add_argument('--keys', type=Path, default=DEFAULT_KEYS, help='Path to switch prod.keys')
    parser.add_argument('--display-version', default='1.2.5', help='Display version in NACP (e.g. 1.2.5)')
    parser.add_argument('--cnmt-version', type=int, default=786432, help='Numeric Title Version in CNMT (e.g. 786432 for v1.2.5)')
    parser.add_argument('--output', type=Path, default=None, help='Path for output .nsp file')
    args = parser.parse_args()

    if args.output is None:
        args.output = PROJECT_ROOT / f'[v{args.display_version}]红果短剧.nsp'

    package(args.keys, args.output, display_version=args.display_version, cnmt_version=args.cnmt_version)
