# fix-rc4: supersedes fix-rc3 (includes everything in it)

Romfs-only, on top of release-candidate-2's patched exefs (unchanged).
Package THIS folder instead of fix-rc3. Suggested display 1.2.1 / CNMT 524288.

## Contents

1. prelude.js fast hex codecs (from fix-rc3): the bundled Buffer shim's
   per-byte hex loops were the real read/open bottleneck. Decode 1MB
   348ms -> 6ms in the harness; fuzz-verified identical semantics.
2. adapter.js warmFirstEpisode (from fix-rc3): episode 1 is resolved while
   the user browses the detail page. Harness: open after detail = 0ms,
   0 HTTP requests.
3. NEW — deterministic candidate order in appMedia (quality desc, codec,
   row key): the API's row order is not stable, and signed URLs rotate on
   every request, so neither could pin down which encode gets picked.
4. NEW — reopen continuity in SwitchAPI.open: a mid-playback reopen for
   the same episode+quality reuses the active stream for up to 10 minutes
   instead of re-resolving. Evidence: fresh re-resolves of one episode
   returned files of 3.2 / 4.3 / 5.1 / 8.6 MB (different encodes); serving
   a different file's bytes to a demuxer that parsed the previous file's
   sample index is the one JS-side mechanism consistent with the isolated
   'Packet corrupt' errors that a reopen then cures. Harness: reopen now
   0ms, 0 HTTP, byte-identical reads. SwitchAPI.read evicts a stream whose
   chunk() fails, so a genuinely dead stream still forces a real
   re-resolve on the next open.
5. NEW — prefetch backoff: attempts at 0s / +1.5s / +4s instead of two
   immediate tries. Prefetch failures in the rc2 log are instant code=0
   connection errors; immediate retries hit the same refusal window.

## What was ruled out for 'Packet corrupt'

The JS data path is byte-correct: sequential AND unaligned reads
(8 probes incl. mid-sample starts and the 256KB prefix boundary) match a
fully assembled ground-truth file byte for byte, with and without the
batch hook. remoteRange also validates range/length strictly. If corrupt
packets still appear with this build, the remaining suspect is the native
bridge's handling of a load_begin issued while a read is in flight —
that needs native-side work, not romfs changes.

## Acceptance (pull runtime.log the same way as rc2)

Baseline rc2: loaded 13.2s; first open 5.4s; first read 6.0s; prefetch
always deferred after code=0 bursts; 2x Packet corrupt with slow reopens.
Expected now: loaded < 4s; episode-1 open after browsing detail ~0s;
prefetch complete (or visibly retried over ~6s before deferring);
no Packet corrupt, and any reopen is instant.
Play several episodes in a row to exercise prefetch + continuity.
