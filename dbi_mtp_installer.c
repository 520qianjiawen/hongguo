#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <sys/stat.h>
#include <sys/time.h>
#include <libgen.h>
#include <unistd.h>
#include <libmtp.h>

typedef struct {
    char *path;
    char *name;
    uint64_t size;
} FileItem;

static uint64_t last_time_ms = 0;
static uint64_t last_sent = 0;
static double current_speed_mb = 0.0;
static const char *current_filename = "";

static uint64_t get_time_ms(void) {
    struct timeval tv;
    gettimeofday(&tv, NULL);
    return (uint64_t)tv.tv_sec * 1000 + (uint64_t)tv.tv_usec / 1000;
}

static void format_bytes(uint64_t bytes, char *buf, size_t buf_len) {
    if (bytes >= 1024ULL * 1024ULL * 1024ULL) {
        snprintf(buf, buf_len, "%.2f GB", (double)bytes / (1024ULL * 1024ULL * 1024ULL));
    } else if (bytes >= 1024ULL * 1024ULL) {
        snprintf(buf, buf_len, "%.1f MB", (double)bytes / (1024ULL * 1024ULL));
    } else if (bytes >= 1024ULL) {
        snprintf(buf, buf_len, "%.1f KB", (double)bytes / 1024ULL);
    } else {
        snprintf(buf, buf_len, "%llu B", bytes);
    }
}

static int progress_callback(uint64_t const sent, uint64_t const total, void const * const data) {
    uint64_t now = get_time_ms();
    if (now - last_time_ms >= 500 || sent == total) {
        if (now > last_time_ms) {
            uint64_t diff_bytes = sent - last_sent;
            uint64_t diff_ms = now - last_time_ms;
            current_speed_mb = ((double)diff_bytes / (1024.0 * 1024.0)) / ((double)diff_ms / 1000.0);
        }
        last_time_ms = now;
        last_sent = sent;

        double pct = total > 0 ? ((double)sent / (double)total) * 100.0 : 0.0;
        char sent_str[32], total_str[32];
        format_bytes(sent, sent_str, sizeof(sent_str));
        format_bytes(total, total_str, sizeof(total_str));

        int bar_width = 25;
        int filled = (int)(pct * bar_width / 100.0);
        if (filled > bar_width) filled = bar_width;

        char bar[64];
        memset(bar, '=', filled);
        if (filled < bar_width) {
            bar[filled] = '>';
            memset(bar + filled + 1, ' ', bar_width - filled - 1);
        }
        bar[bar_width] = '\0';

        printf("\r[%s] %5.1f%% | %9s / %9s | %5.1f MB/s | %s",
               bar, pct, sent_str, total_str, current_speed_mb, current_filename);
        fflush(stdout);
    }
    return 0;
}

int main(int argc, char **argv) {
    if (argc < 2) {
        printf("Usage: %s <file1> [file2 ...]\n", argv[0]);
        return 1;
    }

    printf("====================================================\n");
    printf(" 🎮 Switch DBI MTP Installer (macOS Direct Stream)\n");
    printf("====================================================\n");

    LIBMTP_Init();
    printf("🔍 正在连接 Switch DBI (MTP Responder)... \n");
    LIBMTP_mtpdevice_t *device = LIBMTP_Get_First_Device();
    if (!device) {
        fprintf(stderr, "❌ 未检测到 Switch MTP 设备！\n");
        fprintf(stderr, "👉 请在 Switch 上的 DBI 中选择并运行【Run MTP responder】！\n");
        return 1;
    }

    uint32_t sd_install_storage = 0;
    uint32_t nand_install_storage = 0;
    LIBMTP_devicestorage_t *st = device->storage;
    while (st) {
        if (st->StorageDescription) {
            if (strstr(st->StorageDescription, "SD Card install") || strstr(st->StorageDescription, "5: SD")) {
                sd_install_storage = st->id;
            } else if (strstr(st->StorageDescription, "NAND install") || strstr(st->StorageDescription, "6: NAND")) {
                nand_install_storage = st->id;
            }
        }
        st = st->next;
    }

    if (!sd_install_storage) {
        sd_install_storage = 0x00010005; // Default DBI SD install ID
    }

    printf("✅ 成功连接 DBI MTP！安装目标: SD 卡 (Storage 0x%08x)\n", sd_install_storage);
    printf("📦 待安装文件列表 (%d 个文件):\n", argc - 1);

    for (int i = 1; i < argc; i++) {
        struct stat sb;
        if (stat(argv[i], &sb) == 0) {
            char size_str[32];
            format_bytes(sb.st_size, size_str, sizeof(size_str));
            printf("  %2d. [%9s] %s\n", i, size_str, basename(argv[i]));
        } else {
            printf("  %2d. [ 文件缺失 ] %s\n", i, argv[i]);
        }
    }
    printf("----------------------------------------------------\n\n");

    int success_count = 0;
    int fail_count = 0;

    for (int i = 1; i < argc; i++) {
        char *filepath = argv[i];
        struct stat sb;
        if (stat(filepath, &sb) != 0) {
            fprintf(stderr, "❌ 文件不存在，跳过: %s\n", filepath);
            fail_count++;
            continue;
        }

        char *filename = basename(filepath);
        current_filename = filename;
        last_time_ms = get_time_ms();
        last_sent = 0;
        current_speed_mb = 0.0;

        printf("🚀 [%d/%d] 正在安装: %s\n", i, argc - 1, filename);

        LIBMTP_file_t *genfile = LIBMTP_new_file_t();
        genfile->filesize = (uint64_t)sb.st_size;
        genfile->filename = strdup(filename);
        genfile->filetype = LIBMTP_FILETYPE_UNKNOWN;
        genfile->parent_id = 0xFFFFFFFF;
        genfile->storage_id = sd_install_storage;

        int ret = LIBMTP_Send_File_From_File(device, filepath, genfile, progress_callback, NULL);
        printf("\n");

        if (ret == 0) {
            printf("✅ [%d/%d] 安装成功: %s\n\n", i, argc - 1, filename);
            success_count++;
        } else {
            printf("❌ [%d/%d] 安装失败: %s (错误代码: %d)\n\n", i, argc - 1, filename, ret);
            LIBMTP_Dump_Errorstack(device);
            LIBMTP_Clear_Errorstack(device);
            fail_count++;
        }

        LIBMTP_destroy_file_t(genfile);
    }

    printf("====================================================\n");
    printf("🏁 安装完成！成功: %d 个，失败: %d 个\n", success_count, fail_count);
    printf("====================================================\n");

    return fail_count == 0 ? 0 : 1;
}
