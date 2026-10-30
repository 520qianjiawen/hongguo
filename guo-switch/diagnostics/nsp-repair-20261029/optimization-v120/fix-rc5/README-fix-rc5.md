# fix-rc5: fixes the fix-rc4 regression — DO NOT package fix-rc4

fix-rc4 broke playback entirely (device log rc4-runtime.log: candidate
fetches hit code=0 / 404, then playback:failed 'Failed to open saf://').
Root cause, reproduced in the harness against the exact episode from the
device log (7699685764447079449): fix-rc4 sorted the FINAL candidate list
by quality descending, which destroyed the preference structure
(requested-quality tier first). A 480 request was served the 1080P file
(16.5MB) after the higher candidates' URLs failed on dead CDN nodes.

fix-rc5 = fix-rc4 with the sort moved to construction time only:
`compatible` is sorted once (quality desc, codec, key) BEFORE the
preferred/tier candidate lists are derived from it, so the requested
quality tier is always tried first and the order is still deterministic.
Harness verification on the same episode: open returns 480P
(total 6918138); reopen continuity still 0ms / 0 HTTP / same bytes.

Everything else is identical to fix-rc4 (fast hex prelude, episode-1
warm-up, reopen continuity + read-failure eviction, prefetch backoff).
Package THIS folder with rc2's exefs. Suggested display 1.2.1 /
CNMT 589824 (rc4's build used 524288; DBI needs a higher version to
downgrade-free reinstall over the broken rc4 build).

Acceptance: play several episodes; pull runtime.log; expect loaded < 4s,
prefetch completing, no Packet corrupt, no playback:failed.
