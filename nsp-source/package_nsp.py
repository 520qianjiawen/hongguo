#!/usr/bin/env python3
"""
Package Hongguo Switch NSP from local source directory (control, exefs, romfs).
Produces byte-identical [v1.2.4]红果短剧.nsp using hacbrewpack.
"""
from __future__ import annotations

import argparse
from pathlib import Path
import shutil
import subprocess
import sys

ROOT = Path(__file__).resolve().parent
PROJECT_ROOT = ROOT.parent
DEFAULT_KEYS = Path('/Volumes/Damao/Switch_CFW_Backup/switch/prod.keys')
DEFAULT_OUTPUT = PROJECT_ROOT / '[v1.2.4]红果短剧.nsp'
DEFAULT_TOOL = ROOT / 'tools' / 'hacbrewpack'


def package(keys_path: Path, output_nsp: Path, tool_path: Path = DEFAULT_TOOL):
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

    for d in (nsp_dir, nca_dir, temp_dir, backup_dir):
        d.mkdir(parents=True, exist_ok=True)

    cmd = [
        str(tool_path),
        '-k', str(keys_path.resolve()),
        '--exefsdir', str(ROOT / 'exefs'),
        '--romfsdir', str(ROOT / 'romfs'),
        '--controldir', str(ROOT / 'control'),
        '--nologo',
        '--nopatchnacplogo',
        '--nosignncasig2',
        '--keepncadir',
        '--nspdir', str(nsp_dir),
        '--ncadir', str(nca_dir),
        '--tempdir', str(temp_dir),
        '--backupdir', str(backup_dir)
    ]

    print(f'Running hacbrewpack...')
    res = subprocess.run(cmd, capture_output=True, text=True)
    if res.returncode != 0:
        print(res.stderr, file=sys.stderr)
        raise RuntimeError(f'hacbrewpack failed with code {res.returncode}')

    generated_nsp = nsp_dir / '0100f00d53414600.nsp'
    if not generated_nsp.is_file():
        raise FileNotFoundError(f'Expected NSP not created: {generated_nsp}')

    output_nsp.parent.mkdir(parents=True, exist_ok=True)
    shutil.copy2(generated_nsp, output_nsp)
    print(f'Successfully built: {output_nsp} ({output_nsp.stat().st_size} bytes)')

    shutil.rmtree(staging, ignore_errors=True)


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Build Hongguo Switch NSP package.')
    parser.add_argument('--keys', type=Path, default=DEFAULT_KEYS, help='Path to switch prod.keys')
    parser.add_argument('--output', type=Path, default=DEFAULT_OUTPUT, help='Path for output .nsp file')
    args = parser.parse_args()
    package(args.keys, args.output)
