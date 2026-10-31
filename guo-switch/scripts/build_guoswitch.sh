#!/bin/bash
# Build the Guo Switch development snapshot (NRO) with devkitA64.
#
# Usage:
#   bash scripts/build_guoswitch.sh [--prepare]
#
# --prepare additionally downloads and installs the prebuilt Switch
# ffmpeg/mpv port libraries published with wiliwili v0.1.0 (the stock
# devkitPro packages cannot play network streams). Run inside the
# devkitpro/devkita64 container or on a machine with devkitPro set up:
#
#   docker run --rm -v $(pwd):/data devkitpro/devkita64:20251117 \
#       bash -c "cd /data && bash scripts/build_guoswitch.sh --prepare"
#
# Output: dist/switch/guoswitch/guoswitch.nro
set -e

BUILD_DIR=cmake-build-switch

cd "$(dirname "$0")/.."
git config --global --add safe.directory "$(pwd)" 2>/dev/null || true

if [[ "${1:-}" == "--prepare" ]]; then
    BASE_URL="https://github.com/xfangfang/wiliwili/releases/download/v0.1.0/"
    PKGS=(
        "switch-ffmpeg-7.1-1-any.pkg.tar.zst"
        "switch-libmpv-0.36.0-3-any.pkg.tar.zst"
        "switch-nspmini-48d4fc2-1-any.pkg.tar.xz"
        "hacBrewPack-3.05-1-any.pkg.tar.zst"
    )
    for PKG in "${PKGS[@]}"; do
        [ -f "${PKG}" ] || curl -LO "${BASE_URL}${PKG}"
        dkp-pacman -U --noconfirm "${PKG}"
    done
fi

cmake -B "${BUILD_DIR}" -DCMAKE_BUILD_TYPE=Release -DBUILTIN_NSP=ON \
    -DPLATFORM_SWITCH=ON -DBRLS_UNITY_BUILD=ON -DCMAKE_UNITY_BUILD_BATCH_SIZE=16
make -C "${BUILD_DIR}" wiliwili.nro -j"$(nproc)"

NRO="$(find "${BUILD_DIR}" -maxdepth 2 -name 'wiliwili.nro' | head -n 1)"
if [[ -z "${NRO}" ]]; then
    echo "wiliwili.nro not found under ${BUILD_DIR}" >&2
    exit 1
fi

mkdir -p dist/switch/guoswitch
cp "${NRO}" dist/switch/guoswitch/guoswitch.nro
echo "built dist/switch/guoswitch/guoswitch.nro"
