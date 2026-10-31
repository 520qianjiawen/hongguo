#!/usr/bin/env python3
"""Host-side checks for the guo layer (no network, no Switch toolchain).

Compiles the platform-independent guo sources with the host C++ compiler and
runs them against the recorded fixtures:

1. HongguoProvider web-channel reproduction: every fixture in
   diagnostics/hongguo-playback-20261029/ must resolve to the documented
   "no public playback address" outcome (the App channel is the only way to
   play those dramas; the web channel must keep failing loudly, not silently
   return a wrong address).
2. Library watch-history round-trip: record -> save -> reload -> compare.

Exit code 0 means all checks passed.
"""

import glob
import json
import os
import shutil
import subprocess
import sys
import tempfile

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
FIXTURE_DIR = os.path.join(ROOT, "diagnostics", "hongguo-playback-20261029")
REPRODUCER = os.path.join(FIXTURE_DIR, "reproduce_provider.cpp")
GUO_INCLUDE = os.path.join(ROOT, "wiliwili", "include")
JSON_INCLUDE = os.path.join(ROOT, "library", "borealis", "library", "include", "borealis", "extern")

SIGN_VECTORS = os.path.join(ROOT, "diagnostics", "guo-native", "sign-vectors.json")

SIGN_TEST = r"""
#include "guo/hongguo_sign.hpp"
#include <fstream>
#include <iostream>
#include <nlohmann/json.hpp>
int main(int argc, char** argv) {
    if (argc != 2) return 2;
    if (guo::md5Hex("abc") != "900150983cd24fb0d6963f7d28e17f72") {
        std::cerr << "md5 known-answer failed\n";
        return 1;
    }
    std::ifstream input(argv[1]);
    nlohmann::json vectors;
    input >> vectors;
    for (const auto& row : vectors.at("sign")) {
        const auto out = guo::signRequest(row.at("query").get<std::string>(),
                                          row.at("body").get<std::string>(),
                                          row.at("now").get<int64_t>());
        for (const auto& [name, expected] : row.at("out").items()) {
            const auto it = out.find(name);
            if (it == out.end() || it->second != expected.get<std::string>()) {
                std::cerr << "sign mismatch for " << name << ": got "
                          << (it == out.end() ? std::string("<missing>") : it->second)
                          << " want " << expected.get<std::string>() << "\n";
                return 1;
            }
        }
    }
    for (const auto& row : vectors.at("xGorgon")) {
        const auto out = guo::xGorgon(row.at("query").get<std::string>(),
                                      row.at("body").get<std::string>(),
                                      row.at("timestamp").get<int64_t>(),
                                      row.at("random").get<uint32_t>());
        if (out != row.at("out").get<std::string>()) {
            std::cerr << "xGorgon mismatch: got " << out << " want "
                      << row.at("out").get<std::string>() << "\n";
            return 1;
        }
    }
    std::cout << "sign vectors ok\n";
    return 0;
}
"""

APP_VECTORS = os.path.join(ROOT, "diagnostics", "guo-native", "media-vectors.json")
APP_RESPONSE = os.path.join(ROOT, "diagnostics", "guo-native", "videoinfo-response.json")
APP_ROWS = os.path.join(ROOT, "diagnostics", "guo-native", "resolved-rows.json")

APP_TEST = r"""
#include "guo/hongguo_app.hpp"
#include "guo/hongguo_crypto.hpp"
#include <fstream>
#include <iostream>
#include <sstream>
using nlohmann::json;
static std::string slurp(const char* path) {
    std::ifstream input(path);
    std::stringstream buffer;
    buffer << input.rdbuf();
    return buffer.str();
}
static int fail(const std::string& what) {
    std::cerr << "app test failed: " << what << "\n";
    return 1;
}
int main(int argc, char** argv) {
    if (argc != 4) return 2;
    const auto vectors = json::parse(slurp(argv[1]));
    const auto key = guo::contentKeyFromSpade(vectors.at("spade_a").get<std::string>());
    if (guo::hexEncode(key.data(), key.size()) != vectors.at("content_key_hex").get<std::string>())
        return fail("contentKeyFromSpade");
    const auto seed = guo::hexDecode(vectors.at("spade_url").at("seed_hex").get<std::string>());
    if (guo::decryptSpadeUrl(vectors.at("spade_url").at("encoded").get<std::string>(), seed) !=
        vectors.at("spade_url").at("plain").get<std::string>())
        return fail("decryptSpadeUrl");
    const auto fallback = guo::decodeFallbackBody(vectors.at("fallback_body").get<std::string>());
    if (fallback != vectors.at("fallback_json")) return fail("decodeFallbackBody");
    const auto protectedDoc = guo::parseJsonProtected("{\"id\":7701336720221932568,\"n\":42}");
    if (protectedDoc.at("id").get<std::string>() != "7701336720221932568" ||
        protectedDoc.at("n").get<int>() != 42)
        return fail("parseJsonProtected");

    const std::string responseBody = slurp(argv[2]);
    const auto expectedRows = json::parse(slurp(argv[3]));
    bool sawGorgon = false, sawArgus = false, sawLadon = false;
    guo::FetchHttp fakeHttp = [&](const guo::Request& request) -> guo::Response {
        guo::Response response;
        response.code = 200;
        if (request.url.find("device_register") != std::string::npos) {
            response.body = "{\"device_id_str\":\"1234567890123456789\",\"install_id_str\":\"9876543210987654321\"}";
            return response;
        }
        if (request.url.find("multi_video_model") == std::string::npos)
            return fail("unexpected URL " + request.url), response;
        for (const auto& header : request.headers) {
            if (header.rfind("X-Gorgon: 8404", 0) == 0) sawGorgon = true;
            if (header.rfind("X-Argus: ", 0) == 0) sawArgus = true;
            if (header.rfind("X-Ladon: ", 0) == 0) sawLadon = true;
        }
        response.body = responseBody;
        return response;
    };
    guo::FetchText fakeText = [](const guo::Request&) -> std::string {
        throw std::runtime_error("unexpected text fetch");
    };
    guo::HongguoAppClient client(fakeHttp, fakeText);
    const auto set = client.videoInfo("7701336720221932568");
    if (!sawGorgon || !sawArgus || !sawLadon) return fail("signed headers missing");
    if (set.rows.size() != expectedRows.size()) return fail("row count");
    for (size_t i = 0; i < set.rows.size(); ++i) {
        const auto& row = set.rows[i];
        const auto& expected = expectedRows[i];
        if (std::to_string(row.quality) != expected.at("quality").get<std::string>())
            return fail("row quality order");
        if (row.url != expected.at("main_url").get<std::string>()) return fail("row url");
        if (row.codec != expected.at("codec_type").get<std::string>()) return fail("row codec");
        const std::string keyHex = row.key.empty() ? "" : guo::hexEncode(row.key.data(), row.key.size());
        if (keyHex != expected.at("phone_key").get<std::string>()) return fail("row key");
        if (row.quality == 480 && row.size != 4303144) return fail("row size");
    }
    std::cout << "app vectors ok\n";
    return 0;
}
"""

CODEC_MEDIA = os.path.join(ROOT, "diagnostics", "guo-native", "media")

CODEC_TEST = r"""
#include "guo/hongguo_codec.hpp"
#include "guo/hongguo_crypto.hpp"
#include <cstring>
#include <fstream>
#include <iostream>
#include <vector>
static std::vector<uint8_t> slurp(const char* path) {
    std::ifstream input(path, std::ios::binary);
    return std::vector<uint8_t>((std::istreambuf_iterator<char>(input)),
                                std::istreambuf_iterator<char>());
}
static int fail(const std::string& what) {
    std::cerr << "codec test failed: " << what << "\n";
    return 1;
}
// The content key of the captured episode (see media-vectors.json).
static const uint8_t kKey[16] = {0x63, 0x67, 0xbf, 0x35, 0x22, 0x1c, 0x88, 0x0a,
                                 0xdc, 0x6e, 0xa7, 0x8d, 0x68, 0x6c, 0xed, 0x0a};
int main(int argc, char** argv) {
    if (argc != 3) return 2;
    const auto raw = slurp(argv[1]);
    const auto decoded = slurp(argv[2]);
    if (raw.size() != decoded.size() || raw.empty()) return fail("fixture sizes");
    const auto prepared = guo::prepareCencHeader(raw.data(), raw.size(), kKey, 4303144);
    if (prepared.samples.empty()) return fail("no samples parsed");
    if (prepared.header.empty() || prepared.header.size() >= raw.size()) return fail("header size");
    if (std::memcmp(prepared.header.data(), decoded.data(), prepared.header.size()) != 0)
        return fail("patched header differs from the JS output");
    auto checkRange = [&](size_t start, size_t end) {
        std::vector<uint8_t> slice(raw.begin() + start, raw.begin() + end);
        guo::decryptPartial(slice.data(), slice.size(), start, kKey, prepared);
        if (std::memcmp(slice.data(), decoded.data() + start, slice.size()) != 0)
            return fail("range " + std::to_string(start) + "-" + std::to_string(end));
        return 0;
    };
    if (int rc = checkRange(0, raw.size())) return rc;
    if (int rc = checkRange(0, 511)) return rc;
    if (int rc = checkRange(123457, 234568)) return rc;
    if (int rc = checkRange(244800, 260000)) return rc;
    if (int rc = checkRange(299000, 300000)) return rc;
    if (int rc = checkRange(250001, 250017)) return rc;
    std::cout << "codec vectors ok (" << prepared.samples.size() << " samples)\n";
    return 0;
}
"""

ENGINE_TEST = r"""
#include "guo/hongguo_engine.hpp"
#include <cstring>
#include <fstream>
#include <iostream>
#include <vector>
static std::vector<uint8_t> slurp(const char* path) {
    std::ifstream input(path, std::ios::binary);
    return std::vector<uint8_t>((std::istreambuf_iterator<char>(input)),
                                std::istreambuf_iterator<char>());
}
static int fail(const std::string& what) {
    std::cerr << "engine test failed: " << what << "\n";
    return 1;
}
// The content key of the captured episode (see media-vectors.json).
static const uint8_t kKeyArr[16] = {0x63, 0x67, 0xbf, 0x35, 0x22, 0x1c, 0x88, 0x0a,
                                    0xdc, 0x6e, 0xa7, 0x8d, 0x68, 0x6c, 0xed, 0x0a};
int main(int argc, char** argv) {
    if (argc != 3) return 2;
    const auto raw = slurp(argv[1]);
    const auto decoded = slurp(argv[2]);
    if (raw.empty() || raw.size() != decoded.size()) return fail("fixture sizes");
    auto& engine = guo::StreamEngine::instance();
    auto fileFetcher = [](const std::vector<uint8_t>& file) {
        return guo::RangeFetcher([file](const std::string&, uint64_t start, uint64_t end) {
            if (start >= file.size() || end < start) throw std::runtime_error("range out of file");
            end = std::min<uint64_t>(end, static_cast<uint64_t>(file.size()) - 1);
            guo::RangeResult result;
            result.total = file.size();
            result.data.assign(file.begin() + start, file.begin() + end + 1);
            return result;
        });
    };
    guo::StreamRow row;
    row.url = "https://cdn.invalid/video.mp4";
    row.codec = "bytevc1";
    row.quality = 480;
    row.key.assign(kKeyArr, kKeyArr + 16);
    auto session = engine.createSession({row}, fileFetcher(raw));
    engine.prepare(*session);
    if (!session->prepared || session->total != raw.size()) return fail("prepare");
    auto expect = [&](const std::string& range, bool headOnly, int status, size_t begin, size_t end) {
        const auto resp = engine.serve(session, range, headOnly);
        if (resp.status != status) return fail("status for " + range);
        if (headOnly) {
            if (!resp.body.empty() || resp.contentLength != end - begin) return fail("head for " + range);
            return 0;
        }
        if (resp.body.size() != end - begin) return fail("length for " + range);
        if (end > begin && std::memcmp(resp.body.data(), decoded.data() + begin, resp.body.size()) != 0)
            return fail("bytes for " + range);
        return 0;
    };
    if (int rc = expect("", false, 200, 0, raw.size())) return rc;
    if (int rc = expect("bytes=0-511", false, 206, 0, 512)) return rc;
    if (int rc = expect("bytes=123457-234567", false, 206, 123457, 234568)) return rc;
    if (int rc = expect("bytes=-1024", false, 206, raw.size() - 1024, raw.size())) return rc;
    const std::string lastByte = "bytes=" + std::to_string(raw.size() - 1) + "-";
    if (int rc = expect(lastByte, false, 206, raw.size() - 1, raw.size())) return rc;
    if (int rc = expect("bytes=0-511", true, 206, 0, 512)) return rc;
    const std::string beyond = "bytes=" + std::to_string(raw.size()) + "-";
    if (int rc = expect(beyond, false, 416, 0, 0)) return rc;
    {
        // The patched header must be the bytes mpv sees (hvc1 at offset 457).
        const auto resp = engine.serve(session, "bytes=0-511", false);
        if (resp.body.size() < 461 || std::memcmp(resp.body.data() + 457, "hvc1", 4) != 0)
            return fail("patched header");
    }
    guo::StreamRow plain;
    plain.url = "https://cdn.invalid/plain.mp4";
    plain.quality = 720;
    auto plainSession = engine.createSession({plain}, fileFetcher(decoded));
    {
        const auto resp = engine.serve(plainSession, "bytes=100-199", false);
        if (resp.status != 206 || resp.body.size() != 100 ||
            std::memcmp(resp.body.data(), decoded.data() + 100, 100) != 0)
            return fail("plain passthrough");
    }
    std::cout << "engine serve ok\n" << std::flush;
    // Real HTTP through the mongoose listener; the driver curls this URL,
    // then releases us with a line on stdin.
    std::cout << "ENGINE_URL " << engine.urlFor(*session) << "\n" << std::flush;
    std::string line;
    std::getline(std::cin, line);
    return 0;
}
"""

LIBRARY_TEST = r"""
#include "guo/library.hpp"
#include <cassert>
#include <iostream>
int main(int argc, char** argv) {
    if (argc != 2) return 2;
    const std::string path = argv[1];
    {
        guo::Library lib(path);
        lib.load();
        guo::History h;
        h.drama.id = "7701334722449787928";
        h.drama.title = "round-trip";
        h.drama.source = "hongguo";
        h.episode.id = "7701336720221932568";
        h.episode.number = 2;
        h.seconds = 123;
        h.duration = 600;
        lib.record(h);
    }
    guo::Library lib(path);
    lib.load();
    auto history = lib.history();
    if (history.size() != 1) return 1;
    const auto& e = history.front();
    if (e.drama.id != "7701334722449787928" || e.episode.number != 2 || e.seconds != 123) return 1;
    auto p = lib.progress("7701334722449787928");
    if (p.episode.id != "7701336720221932568") return 1;
    std::cout << "library round-trip ok\n";
    return 0;
}
"""


def find_compiler():
    for candidate in (os.environ.get("CXX"), "g++", "clang++", "c++"):
        if candidate and shutil.which(candidate):
            return candidate
    return None


def compile_binary(cxx, sources, output, workdir, extra_includes=(), extra_link=()):
    cmd = [
        cxx, "-std=c++17", "-O1", "-Wall",
        "-I", GUO_INCLUDE,
        "-I", JSON_INCLUDE,
    ]
    for include in extra_includes:
        cmd += ["-I", include]
    cmd += [*sources, "-o", output, *extra_link]
    result = subprocess.run(cmd, cwd=workdir, capture_output=True, text=True)
    if result.returncode != 0:
        print(result.stdout)
        print(result.stderr, file=sys.stderr)
        raise SystemExit(f"compile failed: {' '.join(sources)}")


def main():
    cxx = find_compiler()
    if not cxx:
        print("no host C++ compiler found (set CXX)", file=sys.stderr)
        return 1
    fixtures = sorted(glob.glob(os.path.join(FIXTURE_DIR, "fixture-*.json")))
    if not fixtures:
        print(f"no fixtures found in {FIXTURE_DIR}", file=sys.stderr)
        return 1

    with tempfile.TemporaryDirectory(prefix="guo-check-") as workdir:
        # The provider now pulls in the App client + stream engine, so most
        # binaries link the full platform-independent guo source set plus
        # the vendored mongoose (used by the engine's local HTTP proxy).
        mongoose_dir = os.path.join(ROOT, "library", "mongoose")
        mongoose_obj = os.path.join(workdir, "mongoose.o")
        cc = shutil.which("cc") or shutil.which("gcc")
        if not cc:
            print("no host C compiler found (needed for mongoose)", file=sys.stderr)
            return 1
        result = subprocess.run(
            [cc, "-O1", "-I", mongoose_dir, "-c",
             os.path.join(mongoose_dir, "mongoose.c"), "-o", mongoose_obj],
            capture_output=True, text=True,
        )
        if result.returncode != 0:
            print(result.stderr, file=sys.stderr)
            print("FAIL: mongoose compile", file=sys.stderr)
            return 1
        guo_dir = os.path.join(ROOT, "wiliwili", "source", "guo")
        guo_sources = [
            os.path.join(guo_dir, name)
            for name in ("provider.cpp", "hongguo_app.cpp", "hongguo_sign.cpp",
                         "hongguo_crypto.cpp", "hongguo_codec.cpp", "hongguo_engine.cpp")
        ]

        # 1. Provider reproduction against fixtures.
        binary = os.path.join(workdir, "reproduce_provider")
        compile_binary(
            cxx,
            [REPRODUCER, *guo_sources, mongoose_obj],
            binary,
            workdir,
            extra_includes=[mongoose_dir],
            extra_link=["-lpthread"],
        )
        result = subprocess.run([binary, *fixtures], capture_output=True, text=True)
        print(result.stdout.strip())
        if result.returncode != 0:
            print(result.stderr, file=sys.stderr)
            print("FAIL: provider reproduction", file=sys.stderr)
            return 1
        try:
            report = json.loads(result.stdout)
        except json.JSONDecodeError:
            print("FAIL: reproducer output is not JSON", file=sys.stderr)
            return 1
        if not report.get("reproduced"):
            print("FAIL: fixtures no longer reproduce the documented outcome", file=sys.stderr)
            return 1
        print(f"provider reproduction ok ({len(fixtures)} fixtures)")

        # 2. Library round-trip.
        test_source = os.path.join(workdir, "library_test.cpp")
        with open(test_source, "w", encoding="utf-8") as handle:
            handle.write(LIBRARY_TEST)
        binary = os.path.join(workdir, "library_test")
        compile_binary(
            cxx,
            [test_source, os.path.join(ROOT, "wiliwili", "source", "guo", "library.cpp"),
             *guo_sources, mongoose_obj],
            binary,
            workdir,
            extra_includes=[mongoose_dir],
            extra_link=["-lpthread"],
        )
        result = subprocess.run(
            [binary, os.path.join(workdir, "guo_history.json")], capture_output=True, text=True
        )
        print(result.stdout.strip())
        if result.returncode != 0:
            print(result.stderr, file=sys.stderr)
            print("FAIL: library round-trip", file=sys.stderr)
            return 1

        # 3. Signer reference vectors (only when the signer sources exist).
        sign_source = os.path.join(ROOT, "wiliwili", "source", "guo", "hongguo_sign.cpp")
        if os.path.exists(sign_source) and os.path.exists(SIGN_VECTORS):
            test_source = os.path.join(workdir, "sign_test.cpp")
            with open(test_source, "w", encoding="utf-8") as handle:
                handle.write(SIGN_TEST)
            binary = os.path.join(workdir, "sign_test")
            compile_binary(cxx, [test_source, sign_source], binary, workdir)
            result = subprocess.run([binary, SIGN_VECTORS], capture_output=True, text=True)
            print(result.stdout.strip())
            if result.returncode != 0:
                print(result.stderr, file=sys.stderr)
                print("FAIL: signer vectors", file=sys.stderr)
                return 1

        # 4. App client: crypto vectors + full videoInfo flow against the
        # captured live API response, with a fake transport.
        app_source = os.path.join(ROOT, "wiliwili", "source", "guo", "hongguo_app.cpp")
        if os.path.exists(app_source) and os.path.exists(APP_VECTORS):
            test_source = os.path.join(workdir, "app_test.cpp")
            with open(test_source, "w", encoding="utf-8") as handle:
                handle.write(APP_TEST)
            binary = os.path.join(workdir, "app_test")
            compile_binary(
                cxx,
                [test_source, app_source,
                 os.path.join(ROOT, "wiliwili", "source", "guo", "hongguo_crypto.cpp"),
                 os.path.join(ROOT, "wiliwili", "source", "guo", "hongguo_sign.cpp")],
                binary,
                workdir,
            )
            result = subprocess.run(
                [binary, APP_VECTORS, APP_RESPONSE, APP_ROWS], capture_output=True, text=True
            )
            print(result.stdout.strip())
            if result.returncode != 0:
                print(result.stderr, file=sys.stderr)
                print("FAIL: app vectors", file=sys.stderr)
                return 1

        # 5. Playback codec: patched header + range decryption against the
        # captured raw/decoded excerpt pair (base64 in the repo; the decoded
        # excerpt comes from a single JS read window starting at 0, so it is
        # free of the harness batch-fallback boundary artifact).
        codec_source = os.path.join(ROOT, "wiliwili", "source", "guo", "hongguo_codec.cpp")
        excerpt_raw = os.path.join(CODEC_MEDIA, "excerpt-raw.bin")
        excerpt_decoded = os.path.join(CODEC_MEDIA, "excerpt-decoded.bin")
        if not os.path.exists(excerpt_raw) and glob.glob(
            os.path.join(CODEC_MEDIA, "excerpt-raw.b64.part*")
        ):
            import base64

            for name, target in (("excerpt-raw", excerpt_raw), ("excerpt-decoded", excerpt_decoded)):
                encoded = ""
                for part in sorted(glob.glob(os.path.join(CODEC_MEDIA, name + ".b64.part*"))):
                    with open(part, "r", encoding="ascii") as handle:
                        encoded += handle.read()
                payload = base64.b64decode(encoded)
                with open(os.path.join(workdir, os.path.basename(target)), "wb") as handle:
                    handle.write(payload)
            excerpt_raw = os.path.join(workdir, "excerpt-raw.bin")
            excerpt_decoded = os.path.join(workdir, "excerpt-decoded.bin")
        if os.path.exists(codec_source) and os.path.exists(excerpt_raw):
            test_source = os.path.join(workdir, "codec_test.cpp")
            with open(test_source, "w", encoding="utf-8") as handle:
                handle.write(CODEC_TEST)
            binary = os.path.join(workdir, "codec_test")
            compile_binary(
                cxx,
                [test_source, codec_source,
                 os.path.join(ROOT, "wiliwili", "source", "guo", "hongguo_crypto.cpp")],
                binary,
                workdir,
            )
            result = subprocess.run(
                [binary, excerpt_raw, excerpt_decoded], capture_output=True, text=True
            )
            print(result.stdout.strip())
            if result.returncode != 0:
                print(result.stderr, file=sys.stderr)
                print("FAIL: codec vectors", file=sys.stderr)
                return 1

        # 6. Stream engine end-to-end: a file-backed fetcher over the raw
        # excerpt; serve() must hand out exactly the decoded bytes for full,
        # ranged, suffix and HEAD requests, and the real mongoose listener
        # must do the same over HTTP (checked with curl by this driver).
        engine_source = os.path.join(ROOT, "wiliwili", "source", "guo", "hongguo_engine.cpp")
        if os.path.exists(engine_source) and os.path.exists(excerpt_raw):
            curl = shutil.which("curl")
            if not curl:
                print("FAIL: engine test needs curl", file=sys.stderr)
                return 1
            test_source = os.path.join(workdir, "engine_test.cpp")
            with open(test_source, "w", encoding="utf-8") as handle:
                handle.write(ENGINE_TEST)
            binary = os.path.join(workdir, "engine_test")
            compile_binary(
                cxx,
                [test_source, engine_source,
                 os.path.join(ROOT, "wiliwili", "source", "guo", "hongguo_codec.cpp"),
                 os.path.join(ROOT, "wiliwili", "source", "guo", "hongguo_crypto.cpp"),
                 mongoose_obj],
                binary,
                workdir,
                extra_includes=[mongoose_dir],
                extra_link=["-lpthread"],
            )
            with open(excerpt_decoded, "rb") as handle:
                decoded_bytes = handle.read()
            proc = subprocess.Popen(
                [binary, excerpt_raw, excerpt_decoded],
                stdin=subprocess.PIPE, stdout=subprocess.PIPE,
                stderr=subprocess.PIPE, text=True,
            )
            engine_url = None
            serve_ok = False
            try:
                for line in proc.stdout:
                    line = line.strip()
                    if line == "engine serve ok":
                        serve_ok = True
                    elif line.startswith("ENGINE_URL "):
                        engine_url = line.split(" ", 1)[1]
                        break
                if not serve_ok or not engine_url:
                    print(proc.stderr.read() if proc.stderr else "", file=sys.stderr)
                    print("FAIL: engine serve", file=sys.stderr)
                    return 1
                full_path = os.path.join(workdir, "engine-full.bin")
                result = subprocess.run(
                    [curl, "-sS", "-o", full_path, engine_url],
                    capture_output=True, text=True, timeout=60,
                )
                if result.returncode != 0:
                    print(result.stderr, file=sys.stderr)
                    print("FAIL: engine http full download", file=sys.stderr)
                    return 1
                with open(full_path, "rb") as handle:
                    if handle.read() != decoded_bytes:
                        print("FAIL: engine http bytes differ from the decoded excerpt", file=sys.stderr)
                        return 1
                part_path = os.path.join(workdir, "engine-part.bin")
                result = subprocess.run(
                    [curl, "-sS", "-o", part_path, "-H", "Range: bytes=123457-234567", engine_url],
                    capture_output=True, text=True, timeout=60,
                )
                if result.returncode != 0:
                    print(result.stderr, file=sys.stderr)
                    print("FAIL: engine http range download", file=sys.stderr)
                    return 1
                with open(part_path, "rb") as handle:
                    if handle.read() != decoded_bytes[123457:234568]:
                        print("FAIL: engine http range bytes differ", file=sys.stderr)
                        return 1
                print("engine http ok")
            finally:
                try:
                    proc.stdin.write("\n")
                    proc.stdin.flush()
                    proc.wait(timeout=10)
                except Exception:
                    proc.kill()

    print("all guo checks passed")
    return 0


if __name__ == "__main__":
    sys.exit(main())
