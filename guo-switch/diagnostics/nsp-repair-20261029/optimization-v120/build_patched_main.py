#!/usr/bin/env python3
"""Build patched NSO executable main with native CENC batch decryption bridge.
"""
from __future__ import annotations
import ctypes
import hashlib
from pathlib import Path
import struct
import subprocess

ROOT = Path(__file__).resolve().parent
ORIGINAL_EXEFS = Path('/Volumes/Damao/APP/switch-diagnostics-20261029/v113-inspect/c862f5406836756312cad567b57daab1-exefs')
OUTPUT_EXEFS = ROOT / 'modified-exefs'

liblz4 = ctypes.CDLL('/opt/homebrew/lib/liblz4.dylib')
LZ4_compress_default = liblz4.LZ4_compress_default
LZ4_compress_default.argtypes = [ctypes.c_char_p, ctypes.c_char_p, ctypes.c_int, ctypes.c_int]
LZ4_compress_default.restype = ctypes.c_int

LZ4_decompress_safe = liblz4.LZ4_decompress_safe
LZ4_decompress_safe.argtypes = [ctypes.c_char_p, ctypes.c_char_p, ctypes.c_int, ctypes.c_int]
LZ4_decompress_safe.restype = ctypes.c_int


def assemble_patch() -> bytearray:
    asm = """
.text
.global _start
_start:
    ldr x1, [sp, #0xf8]
    cmp x1, #10
    b.ne normal_flow

    ldr x2, [sp, #0xf0]
    ldr x3, [x2]
    mov x4, #0x6563
    movk x4, #0x636e, lsl #16
    movk x4, #0x622d, lsl #32
    movk x4, #0x7461, lsl #48
    cmp x3, x4
    b.ne normal_flow

    ldrh w3, [x2, #8]
    mov w4, #0x6863
    cmp w3, w4
    b.ne normal_flow

    cmp x0, #0x10
    b.ne error_exit

    ldp x24, x28, [sp, #0xa8]
    sub x27, x28, x24

    ldp x22, x21, [sp, #0xc0]
    subs x21, x21, x22
    b.eq empty_data

    mov x0, x21
    bl 0xce1920
    mov x20, x0
    str x0, [sp, #0xd8]
    add x26, x0, x21
    str x26, [sp, #0xe8]

    mov x0, x20
    mov x1, x22
    mov x2, x21
    bl 0x15e5340

    cbz x27, finish_batch

    mov x0, x19
    bl 0x226220

    mov x0, x19
    mov x1, x25
    mov w2, #128
    bl 0x226240

    sub sp, sp, #0x40
    stp x29, x30, [sp, #0x30]
    mov x29, sp

    mov x26, x24

sample_loop:
    cmp x26, x28
    b.hs sample_loop_done

    ldr w10, [x26]
    ldr w11, [x26, #4]

    add w12, w10, w11
    cmp w12, w21
    b.hi next_sample

    cbz w11, next_sample

    ldr q0, [x26, #8]
    str q0, [sp]

    str xzr, [sp, #0x20]
    stp xzr, xzr, [sp, #0x10]

    uxtw x10, w10
    add x5, x20, x10
    mov x6, x5
    add x4, sp, #0x10
    mov x3, sp
    add x2, sp, #0x20
    uxtw x1, w11
    mov x0, x19
    bl 0x2271f0

next_sample:
    add x26, x26, #24
    b sample_loop

sample_loop_done:
    ldp x29, x30, [sp, #0x30]
    add sp, sp, #0x40

finish_batch:
    mov w27, #0
    add x20, x20, x21
    b 0xe7580

empty_data:
    b 0xe76c4

error_exit:
    b 0xe7794

normal_flow:
    cmp x0, #0x10
    b.ne 1f
    b 0xe7520
1:
    b 0xe7794
"""
    tmp_s = Path('/tmp/cenc_patch.s')
    tmp_o = Path('/tmp/cenc_patch.o')
    tmp_s.write_text(asm)
    subprocess.run(['clang', '-target', 'aarch64-none-elf', '-c', str(tmp_s), '-o', str(tmp_o)], check=True)
    elf = tmp_o.read_bytes()
    e_shoff, = struct.unpack_from('<Q', elf, 0x28)
    e_shentsize, e_shnum, e_shstrndx = struct.unpack_from('<HHH', elf, 0x3a)
    shstr_off, = struct.unpack_from('<Q', elf, e_shoff + e_shstrndx * e_shentsize + 0x18)
    text_off, text_sz = 0, 0
    for i in range(e_shnum):
        sh = elf[e_shoff + i * e_shentsize : e_shoff + (i+1) * e_shentsize]
        name_idx, sh_type, sh_flags, sh_addr, sh_offset, sh_size = struct.unpack_from('<IIQQQQ', sh, 0)
        end = elf.find(b'\x00', shstr_off + name_idx)
        name = elf[shstr_off + name_idx:end].decode('ascii')
        if name == '.text':
            text_off, text_sz = sh_offset, sh_size
            break
    if not text_sz:
        raise RuntimeError('No .text section found in patch.o')
    code = bytearray(elf[text_off:text_off+text_sz])
    BASE = 0x1618b80

    def patch_bl(offset: int, target: int) -> None:
        pc = BASE + offset
        imm26 = ((target - pc) >> 2) & 0x03ffffff
        code[offset:offset+4] = struct.pack('<I', 0x94000000 | imm26)

    def patch_b(offset: int, target: int) -> None:
        pc = BASE + offset
        imm26 = ((target - pc) >> 2) & 0x03ffffff
        code[offset:offset+4] = struct.pack('<I', 0x14000000 | imm26)

    patch_bl(0x5c, 0xce1920)
    patch_bl(0x7c, 0x15e5340)
    patch_bl(0x88, 0x226220)
    patch_bl(0x98, 0x226240)
    patch_bl(0xfc, 0x2271f0)
    patch_b(0x118, 0xe7580)
    patch_b(0x11c, 0xe76c4)
    patch_b(0x120, 0xe7794)
    patch_b(0x12c, 0xe7520)
    patch_b(0x130, 0xe7794)
    return code


def build() -> Path:
    OUTPUT_EXEFS.mkdir(parents=True, exist_ok=True)
    orig_nso_path = ORIGINAL_EXEFS / 'main'
    orig_nso = orig_nso_path.read_bytes()
    hdr = bytearray(orig_nso[:0x100])

    # 1. Decompress original .text
    t_fo, t_dst, t_dsz = struct.unpack_from('<III', hdr, 0x10)
    t_cs, = struct.unpack_from('<I', hdr, 0x60)
    comp_text = orig_nso[t_fo:t_fo+t_cs]
    decomp_text = ctypes.create_string_buffer(t_dsz)
    ret = LZ4_decompress_safe(comp_text, decomp_text, t_cs, t_dsz)
    if ret != t_dsz:
        raise RuntimeError('Decompress text failed')
    text = bytearray(decomp_text.raw[:ret])

    # 2. Hook at 0xe7518
    hook_pc = 0xe7518
    target_pc = 0x1618b80
    imm26 = ((target_pc - hook_pc) >> 2) & 0x03ffffff
    insn_b_patch = 0x14000000 | imm26
    insn_nop = 0xd503201f
    text[hook_pc:hook_pc+8] = struct.pack('<II', insn_b_patch, insn_nop)

    # 3. Append patch code
    patch_code = assemble_patch()
    text.extend(patch_code)
    pad_len = 0x1619000 - len(text)
    if pad_len < 0:
        raise RuntimeError('Patch code exceeded 0x1619000 boundary')
    text.extend(b'\x00' * pad_len)
    new_text_bytes = bytes(text)

    # 4. Extract rodata and data compressed sections
    ro_fo, ro_dst, ro_dsz = struct.unpack_from('<III', hdr, 0x20)
    ro_cs, = struct.unpack_from('<I', hdr, 0x64)
    comp_rodata = orig_nso[ro_fo:ro_fo+ro_cs]

    d_fo, d_dst, d_dsz = struct.unpack_from('<III', hdr, 0x30)
    d_cs, = struct.unpack_from('<I', hdr, 0x68)
    comp_data = orig_nso[d_fo:d_fo+d_cs]

    # 5. Compress new .text
    dst_cap = len(new_text_bytes) + 65536
    comp_buf = ctypes.create_string_buffer(dst_cap)
    new_t_cs = LZ4_compress_default(new_text_bytes, comp_buf, len(new_text_bytes), dst_cap)
    if new_t_cs <= 0:
        raise RuntimeError('Compress new text failed')
    new_comp_text = comp_buf.raw[:new_t_cs]

    # 6. Update NSO header
    struct.pack_into('<I', hdr, 0x18, len(new_text_bytes))
    struct.pack_into('<I', hdr, 0x60, new_t_cs)
    hdr[0xa0:0xc0] = hashlib.sha256(new_text_bytes).digest()

    new_t_fo = 0x100
    new_ro_fo = new_t_fo + new_t_cs
    new_d_fo = new_ro_fo + ro_cs

    struct.pack_into('<I', hdr, 0x10, new_t_fo)
    struct.pack_into('<I', hdr, 0x20, new_ro_fo)
    struct.pack_into('<I', hdr, 0x30, new_d_fo)

    new_nso = bytes(hdr) + new_comp_text + comp_rodata + comp_data
    out_path = OUTPUT_EXEFS / 'main'
    out_path.write_bytes(new_nso)

    # Copy npdm alongside
    shutil_npdm = ORIGINAL_EXEFS / 'main.npdm'
    (OUTPUT_EXEFS / 'main.npdm').write_bytes(shutil_npdm.read_bytes())
    print(f'Successfully built patched NSO main at {out_path} ({len(new_nso)} bytes)')
    return out_path


if __name__ == '__main__':
    build()
