# fix-rc3: romfs-only changes on top of release-candidate-2

ExeFS: reuse rc2's patched main unchanged (its batch hook was audited: the
magic check reads the algorithm std::string exactly the way the original
dispatch does — size at [sp,#0xf8], chars via [sp,#0xf0]; rc2's asm keeps the
CTR counter in its own 0x40-byte frame, so no vector slots are clobbered).
This folder only replaces romfs files.

## What was wrong (from rc2's real-device runtime.log)

loaded = 13.2s / 13.8s / 10.4s, while every HTTP request was <= 0.8s.
Breakdown of the first play: open 5.4s (HTTP 0.37s) + fresh reopen 2.6s +
first read 6.0s (HTTP 0.19s). The JS batch call was verified correct in a
Node harness (exactly one __aes('cenc-batch') per read, 2039 samples,
SHA-256 identical to v1.1.9), so decryption calls were never the dominant
cost: the bundled Buffer shim's hex codecs were. Buffer.from(hex) decodes
with per-byte substr+parseInt and toString('hex') encodes with per-byte
concatenation; every media byte crosses those loops 3x per read
(fetch decode, decrypt-result decode, read-result encode) in QuickJS.

## Changes vs rc2 romfs

1. prelude.js: fast table-driven hex codecs for large inputs
   (decode >= 16384 chars, encode >= 8192 bytes). Malformed input delegates
   the whole conversion back to the original implementation; small inputs
   always use the original path. Harness benchmark (V8): decode 1MB
   348ms -> 6ms, encode 1MB 71ms -> 36ms; the QuickJS decode gap is larger.
   Equivalence fuzzed against the original shim (sizes, ranges, subarrays,
   uppercase, odd length, invalid pairs) — all identical.
2. adapter.js: detail-page warm-up of episode 1 (warmFirstEpisode, both
   detail success paths). rc2's adapter already had the index cache, the
   1800s TTLs and the prefetch retry; only the warm-up was missing.
   Harness: open(ep1, fresh) after detail = 0ms, 0 HTTP requests.

hongguo.cjs / phone_source.js: byte-identical to rc2 romfs.

## Packaging

Use rc2's modified-exefs + this folder as modified-romfs. Suggest
 display version 1.2.1 / CNMT 524288 (next step after rc2's 458752).

## Acceptance (pull runtime.log the same way as rc2)

Baseline rc2: api:open 5.38s/2.62s, first api:read 6.04s, loaded 13183ms.
Expected: first api:read local time drops to well under 1s of processing;
open after browsing a detail page ~0s (warmed); loaded target < 4s.
Packet corrupt + full reopen and prefetch code=0 bursts are pre-existing
issues (present since the v1.1.8 log) and are NOT addressed here.
