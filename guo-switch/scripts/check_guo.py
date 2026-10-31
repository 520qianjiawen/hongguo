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


def compile_binary(cxx, sources, output, workdir):
    cmd = [
        cxx, "-std=c++17", "-O1", "-Wall",
        "-I", GUO_INCLUDE,
        "-I", JSON_INCLUDE,
        *sources,
        "-o", output,
    ]
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
        # 1. Provider reproduction against fixtures.
        binary = os.path.join(workdir, "reproduce_provider")
        compile_binary(
            cxx,
            [REPRODUCER, os.path.join(ROOT, "wiliwili", "source", "guo", "provider.cpp")],
            binary,
            workdir,
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
             os.path.join(ROOT, "wiliwili", "source", "guo", "provider.cpp")],
            binary,
            workdir,
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

    print("all guo checks passed")
    return 0


if __name__ == "__main__":
    sys.exit(main())
