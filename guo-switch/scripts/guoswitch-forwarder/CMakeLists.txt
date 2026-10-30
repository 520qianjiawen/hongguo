cmake_minimum_required(VERSION 3.13)

if (NOT DEFINED ENV{DEVKITPRO})
    message(FATAL_ERROR "DEVKITPRO is required")
endif ()
set(DEVKITPRO "$ENV{DEVKITPRO}")
include("${DEVKITPRO}/cmake/Switch.cmake")
project(guoswitch_forwarder LANGUAGES C ASM)

set(GUO_ROOT "${CMAKE_CURRENT_LIST_DIR}/../..")
set(GUO_TITLE_ID "010f47554f530000")
add_executable(guoswitch_forwarder
    "${GUO_ROOT}/scripts/switch-forwarder/source/main.c"
    "${GUO_ROOT}/scripts/switch-forwarder/source/trampoline.s")
target_include_directories(guoswitch_forwarder PRIVATE "${DEVKITPRO}/libnx/include")
target_compile_options(guoswitch_forwarder PRIVATE
    -march=armv8-a -mtune=cortex-a57 -mtp=soft -fPIE -O2 -ffunction-sections)
target_compile_definitions(guoswitch_forwarder PRIVATE
    __SWITCH__ VERSION="2.4.3"
    DEFAULT_NRO="sdmc:/switch/guoswitch/guoswitch.nro"
    APP_STORE_NRO="sdmc:/switch/guoswitch/guoswitch.nro")
target_link_options(guoswitch_forwarder PRIVATE
    "-L${DEVKITPRO}/libnx/lib"
    -march=armv8-a -mtune=cortex-a57 -mtp=soft -fPIE -Wl,--gc-sections)
target_link_libraries(guoswitch_forwarder PRIVATE nx)

add_custom_target(guoswitch_forwarder_assets ALL
    DEPENDS guoswitch_forwarder
    COMMAND "${CMAKE_COMMAND}" -E make_directory exefs control
    COMMAND "${NX_ELF2NSO_EXE}" guoswitch_forwarder.elf exefs/main
    COMMAND "${NX_NPDMTOOL_EXE}" "${CMAKE_CURRENT_LIST_DIR}/guoswitch.json" exefs/main.npdm
    COMMAND "${NX_NACPTOOL_EXE}" --create "果剧" "Guo Switch" "0.1.0" control/control.nacp --titleid=${GUO_TITLE_ID}
    COMMAND python3 "${CMAKE_CURRENT_LIST_DIR}/prepare_control.py" control/control.nacp
    COMMAND "${CMAKE_COMMAND}" -E copy "${GUO_ROOT}/resources/icon/guoswitch-desktop.jpg" control/icon_AmericanEnglish.dat
    VERBATIM)
