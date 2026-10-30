#!/usr/bin/env python3
"""Repack the supplied Hongguo homebrew NSP from isolated staging copies.

Never prints key contents or raw hactool output. Originals remain untouched.
Uses the locally retained hacBrewPack Git archive; no network dependency.
"""
from __future__ import annotations

import argparse
import hashlib
import io
import json
import os
from pathlib import Path
import re
import shutil
import struct
import subprocess
import tarfile

ROOT = Path(__file__).resolve().parent
PROJECT = ROOT.parent.parent
EXTRACTED = Path('/Volumes/Damao/APP/switch-diagnostics-20261029/v113-inspect')
ORIGINAL_ROMFS = EXTRACTED / 'c862f5406836756312cad567b57daab1'
ORIGINAL_EXEFS = EXTRACTED / 'c862f5406836756312cad567b57daab1-exefs'
ORIGINAL_CONTROL = EXTRACTED / '22b2198b0de393ef65f67fa4e88a6e4b'
ORIGINAL_NSP = Path('/Users/cavon/Downloads/[v1.1.3][0100F00D53414600][v0].nsp')
TITLE_ID = 0x0100F00D53414600


def digest(path: Path) -> str:
    return hashlib.sha256(path.read_bytes()).hexdigest()


def run(command: list[str], *, cwd: Path | None = None, log: Path | None = None) -> str:
    result = subprocess.run(command, cwd=cwd, capture_output=True, text=True)
    # Old Switch tools can print unknown key *values*. Discard all key lines.
    lines = [line for line in (result.stdout + result.stderr).splitlines()
             if not re.search(r'\bkey\b|keyset|keygeneration|key_area|modulus|signature', line, re.I)]
    safe = '\n'.join(lines)
    if log:
        log.write_text(safe + '\n')
    if result.returncode:
        raise RuntimeError('Command failed: ' + Path(command[0]).name + '\n' + safe[-6000:])
    return result.stdout


def bootstrap_tool() -> Path:
    source = ROOT / 'tools' / 'hacbrewpack-src'
    binary = source / 'hacbrewpack'
    if binary.exists():
        return binary
    source.mkdir(parents=True, exist_ok=True)
    repo = PROJECT / '.checks/hacBrewPack'
    archive = subprocess.run(['git', '-C', str(repo), 'archive', 'HEAD'], capture_output=True, check=True).stdout
    with tarfile.open(fileobj=io.BytesIO(archive)) as tar:
        tar.extractall(source, filter='data')
    # Keep display version and CNMT numeric revision separate. Original CNMT=0.
    cnmt = source / 'cnmt.c'
    data = cnmt.read_text()
    needle = '    // Common values\n'
    if data.count(needle) != 1:
        raise RuntimeError('Unexpected hacBrewPack cnmt.c source')
    cnmt.write_text(data.replace(needle, needle + '    cnmt_ctx->cnmt_header.title_version = 65536; // local repair revision\n'))
    (source / 'config.mk').write_text('CC = clang\nCFLAGS = -O2 -std=gnu11 -fPIC\nLDFLAGS = -lmbedtls -lmbedx509 -lmbedcrypto\n')
    run(['make', '-j4'], cwd=source, log=ROOT / 'tool-build.log')
    if not binary.exists():
        raise RuntimeError('hacbrewpack build produced no binary')
    return binary


def inventory(directory: Path) -> dict[str, str]:
    return {str(f.relative_to(directory)): digest(f) for f in sorted(directory.rglob('*')) if f.is_file()}


def patch_control(path: Path, name: str, version: str) -> list[int]:
    before = path.read_bytes()
    if len(before) != 0x4000:
        raise RuntimeError('Unexpected original NACP size')
    data = bytearray(before)
    encoded_name = name.encode('utf-8')
    encoded_version = version.encode('ascii')
    if not 0 < len(encoded_name) < 0x200 or not 0 < len(encoded_version) < 16:
        raise ValueError('Invalid title name/version length')
    changed_locales = []
    for index in range(16):
        offset = index * 0x300
        if data[offset]:
            data[offset:offset+0x200] = encoded_name.ljust(0x200, b'\0')
            changed_locales.append(index)
    data[0x3060:0x3070] = encoded_version.ljust(16, b'\0')
    allowed = set(range(0x3060, 0x3070))
    for index in changed_locales:
        allowed.update(range(index*0x300, index*0x300+0x200))
    if any(a != b and i not in allowed for i, (a, b) in enumerate(zip(before, data))):
        raise RuntimeError('Unexpected NACP modification')
    path.write_bytes(data)
    return changed_locales


def npdm_title(path: Path) -> int:
    data = path.read_bytes()
    if data[:4] != b'META':
        raise RuntimeError('Invalid NPDM')
    aci0 = struct.unpack_from('<I', data, 0x70)[0]
    if data[aci0:aci0+4] != b'ACI0':
        raise RuntimeError('Invalid NPDM ACI0')
    return struct.unpack_from('<Q', data, aci0+0x10)[0]


def pfs0_entries(path: Path) -> list[dict]:
    data = path.read_bytes()
    if data[:4] != b'PFS0':
        raise RuntimeError('Invalid output NSP magic')
    count, names_size = struct.unpack_from('<II', data, 4)
    names_start = 0x10 + count * 0x18
    content_start = names_start + names_size
    entries = []
    for index in range(count):
        offset, size, name_offset = struct.unpack_from('<QQI', data, 0x10 + index * 0x18)
        start = names_start + name_offset
        end = data.index(b'\0', start, names_start + names_size)
        name = data[start:end].decode()
        blob = data[content_start+offset:content_start+offset+size]
        if len(blob) != size:
            raise RuntimeError('Truncated NSP content')
        sha256 = hashlib.sha256(blob).hexdigest()
        if name.endswith('.nca') and name.split('.')[0] != sha256[:32]:
            raise RuntimeError('NCA content name/digest mismatch')
        entries.append({'name': name, 'bytes': size, 'sha256': sha256})
    return entries


def verify_roundtrip(destination: Path, keyfile: Path, exefs: Path, romfs: Path, control: Path) -> dict:
    hactool = Path('/Volumes/Damao/APP/switch-diagnostics-20261029/hactool/hactool')
    verify = destination / 'roundtrip'
    verify.mkdir()
    observed = {}
    for nca in sorted((destination / 'nca').glob('*.nca')):
        header = run([str(hactool), '--disablekeywarns', '--suppresskeys', '-k', str(keyfile), str(nca)])
        match = re.search(r'^Content Type:\s+(\w+)', header, re.M)
        if not match:
            raise RuntimeError('NCA content type unavailable')
        content_type = match.group(1)
        if content_type == 'Program':
            run([str(hactool), '--disablekeywarns', '--suppresskeys', '-k', str(keyfile),
                 '-x', '--exefsdir=' + str(verify / 'exefs'), '--romfsdir=' + str(verify / 'romfs'), str(nca)])
            if inventory(verify / 'exefs') != inventory(exefs) or inventory(verify / 'romfs') != inventory(romfs):
                raise RuntimeError('Program NCA roundtrip mismatch')
            observed['program_roundtrip'] = True
        elif content_type == 'Control':
            run([str(hactool), '--disablekeywarns', '--suppresskeys', '-k', str(keyfile),
                 '-x', '--romfsdir=' + str(verify / 'control'), str(nca)])
            if inventory(verify / 'control') != inventory(control):
                raise RuntimeError('Control NCA roundtrip mismatch')
            observed['control_roundtrip'] = True
        elif content_type == 'Meta':
            run([str(hactool), '--disablekeywarns', '--suppresskeys', '-k', str(keyfile),
                 '-x', '--section0dir=' + str(verify / 'meta'), str(nca)])
            metadata = list((verify / 'meta').glob('*.cnmt'))
            if len(metadata) != 1:
                raise RuntimeError('Expected one CNMT')
            data = metadata[0].read_bytes()
            title, version = struct.unpack_from('<QI', data)
            if title != TITLE_ID or version != 65536 or data[0xC] != 0x80:
                raise RuntimeError('Unexpected CNMT application identity or version')
            count = struct.unpack_from('<H', data, 0x10)[0]
            extended = struct.unpack_from('<H', data, 0xE)[0]
            content_start = 0x20 + extended
            for index in range(count):
                start = content_start + index * 0x38
                sha256 = data[start:start+0x20].hex()
                nca_id = data[start+0x20:start+0x30].hex()
                source = destination / 'nca' / (nca_id + '.nca')
                if not source.is_file() or digest(source) != sha256:
                    raise RuntimeError('CNMT content record/digest mismatch')
            observed['cnmt_content_records_verified'] = count
            observed['cnmt_identity_verified'] = True
        else:
            raise RuntimeError('Unexpected NCA type: ' + content_type)
    if not all(observed.get(key) for key in ['program_roundtrip', 'control_roundtrip', 'cnmt_identity_verified']):
        raise RuntimeError('Incomplete NCA roundtrip verification')
    return observed


def package(args: argparse.Namespace) -> None:
    tool = bootstrap_tool()
    destination = args.output.resolve()
    if destination.exists():
        raise RuntimeError('Output directory already exists; use a new staging directory')
    destination.mkdir(parents=True, mode=0o700)
    if not args.keys.is_file():
        raise RuntimeError('Existing user key file not found')
    hashes = {'original_nsp': digest(ORIGINAL_NSP), 'original_exefs': inventory(ORIGINAL_EXEFS),
              'original_romfs': inventory(ORIGINAL_ROMFS), 'original_control': inventory(ORIGINAL_CONTROL)}
    exefs = destination / 'exefs'
    romfs = destination / 'romfs'
    control = destination / 'control'
    shutil.copytree(ORIGINAL_EXEFS, exefs)
    shutil.copytree(ORIGINAL_ROMFS, romfs)
    shutil.copytree(ORIGINAL_CONTROL, control)
    if npdm_title(exefs / 'main.npdm') != TITLE_ID:
        raise RuntimeError('Original NPDM title ID differs from requested application')
    overlay = args.overlay.resolve()
    allowed_overlay = {'hongguo.cjs', 'prelude.js', 'adapter.js', 'phone_source.js'}
    for file in sorted(overlay.rglob('*')):
        if file.is_file():
            relative = file.relative_to(overlay)
            if relative.as_posix() not in allowed_overlay:
                raise RuntimeError('Unexpected ROMFS overlay file: ' + str(relative))
            shutil.copy2(file, romfs / relative)
    locales = patch_control(control / 'control.nacp', args.name, '1.1.4')
    before_npdm = digest(exefs / 'main.npdm')
    run([str(tool), '-k', str(args.keys.resolve()), '--exefsdir', str(exefs),
         '--romfsdir', str(romfs), '--controldir', str(control), '--nologo',
         '--nopatchnacplogo', '--nosignncasig2', '--keepncadir',
         '--nspdir', str(destination / 'nsp'), '--ncadir', str(destination / 'nca'),
         '--tempdir', str(destination / 'temp'), '--backupdir', str(destination / 'backup')],
        cwd=destination, log=destination / 'packaging.log')
    output = destination / 'nsp' / '0100f00d53414600.nsp'
    if not output.is_file():
        raise RuntimeError('Expected application NSP missing')
    if inventory(ORIGINAL_EXEFS) != hashes['original_exefs'] or inventory(ORIGINAL_ROMFS) != hashes['original_romfs'] or inventory(ORIGINAL_CONTROL) != hashes['original_control']:
        raise RuntimeError('Original extracted application unexpectedly changed')
    if digest(exefs / 'main') != hashes['original_exefs']['main'] or digest(exefs / 'main.npdm') != before_npdm:
        raise RuntimeError('Staged executable or NPDM unexpectedly changed')
    roundtrip = verify_roundtrip(destination, args.keys.resolve(), exefs, romfs, control)
    report = {'name': args.name, 'display_version': '1.1.4', 'cnmt_version': 65536,
              'title_id': f'{TITLE_ID:016x}', 'output': str(output), 'bytes': output.stat().st_size,
              'sha256': digest(output), 'entries': pfs0_entries(output), 'renamed_locales': locales,
              'original_files_preserved': True, 'executable_preserved': True, 'npdm_preserved': True,
              'romfs_changes': [f for f, h in inventory(romfs).items() if hashes['original_romfs'].get(f) != h],
              'control': {'before_sha256': hashes['original_control']['control.nacp'],
                          'after_sha256': digest(control / 'control.nacp')}, 'roundtrip': roundtrip, 'installed': False}
    (destination / 'package-verification.json').write_text(json.dumps(report, ensure_ascii=False, indent=2) + '\n')
    print(json.dumps(report, ensure_ascii=False, indent=2))


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--bootstrap-only', action='store_true')
    parser.add_argument('--keys', type=Path, default=Path('/Volumes/Damao/Switch_CFW_Backup/switch/prod.keys'))
    parser.add_argument('--overlay', type=Path)
    parser.add_argument('--output', type=Path)
    parser.add_argument('--name', default='红果短剧修复版')
    args = parser.parse_args()
    if args.bootstrap_only:
        print(bootstrap_tool())
    else:
        if args.overlay is None or args.output is None:
            parser.error('--overlay and --output are required')
        package(args)
