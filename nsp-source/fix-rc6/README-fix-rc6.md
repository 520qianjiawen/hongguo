# fix-rc6: stop the concurrent-resolve storm that makes open time out

Supersedes fix-rc5. Romfs-only; package with rc2's patched exefs.
Changed file: adapter.js only (prelude.js / hongguo.cjs / phone_source.js
are byte-identical to fix-rc5).

## Symptom

v1.2.2 (fix-rc5 romfs) still fails to play on device. The rc4 device log
already showed the shape of it: after api:open:begin there are ~10 HTTP
requests (five 200s, code=0 bursts, a 404, a 206) and then
playback:failed / mpv "Failed to open saf://", repeated on every retry.

## Root cause

Three callers resolve the same episode at the same time:

1. SwitchAPI.detail's wrapper fires SwitchAPI.prefetch(ep1) — rc2-era code.
2. warmFirstEpisode (added in fix-rc3) fires provider.stream(ep1) from
   inside provider.detail.
3. The user's tap fires SwitchAPI.open(ep1, fresh=true), which clears the
   caches and resolves AGAIN, in parallel with 1 and 2.

Each resolve runs its own sign-in/video-model flow and its own candidate
walk with header range fetches against the CDN. Reproduced in the Node
harness with the exact series from the device log (7699683909419666457,
episode 7699685880826448921): detail+open produced 16 HTTP requests,
5 code=0 connection failures and 4 parallel header 206s, and the open
took 104 seconds on a datacenter network. On the Switch the native side
gives up on the open long before that and reports "Failed to open".

This also explains why rc2 played (only caller 1 existed, so the storm
was a drizzle) and why the failures look random: they depend on how the
parallel resolves interleave and which CDN edges refuse connections.

## Fix

provider.stream now keeps an in-flight promise per cache key
(id|quality|tier). Concurrent callers join the same resolve instead of
starting competing ones; the entry is removed when the resolve settles.
A fresh open that arrives while a warm-up resolve is running joins it
(the in-flight result is by definition freshly fetched). Read/open
semantics are otherwise unchanged from fix-rc5 (reopen continuity and
read-failure eviction included).

Harness after the fix, same series and flow: 9 HTTP requests total for
detail+open, no parallel header fetches, single candidate walk.
Continuity regression (reopen 0ms / 0 HTTP / identical bytes) still
passes; the decrypt path is untouched since its ground-truth checks.

## Acceptance

Install, open a series, tap episode 1, let it autoplay several episodes.
Pull runtime.log: expect one clean request sequence per open (a handful
of 200s, one 206 header), loaded in low seconds, prefetch completing,
no playback:failed.
