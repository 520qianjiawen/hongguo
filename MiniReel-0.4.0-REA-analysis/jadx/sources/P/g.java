package P;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f431a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f f432b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final f f433c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final f f434d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f f435e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final f f436f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final f f437g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final f f438h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final f f439i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final f f440j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final List f441k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final List f442l;
    public static final G.b m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Map f443n;

    static {
        f fVar = new f("--aot-shared-library-name=", "AOTSharedLibraryName");
        f431a = fVar;
        f fVar2 = new f("--aot-shared-library-name=", "aot-shared-library-name", "io.flutter.embedding.engine.loader.FlutterLoader.", true);
        f432b = fVar2;
        f fVar3 = new f("--flutter-assets-dir=", "FlutterAssetsDir");
        f433c = fVar3;
        f fVar4 = new f("--flutter-assets-dir=", "flutter-assets-dir", "io.flutter.embedding.engine.loader.FlutterLoader.", true);
        f434d = fVar4;
        f fVar5 = new f("--old-gen-heap-size=", "OldGenHeapSize");
        f435e = fVar5;
        f fVar6 = new f("--enable-impeller=", "EnableImpeller");
        f fVar7 = new f("--impeller-backend=", "ImpellerBackend");
        f fVar8 = new f("--enable-dart-profiling", "EnableDartProfiling");
        f fVar9 = new f("--profile-startup", "ProfileStartup");
        f fVar10 = new f("--trace-startup", "TraceStartup");
        f fVar11 = new f("--merged-platform-ui-thread", "MergedPlatformUIThread");
        f fVar12 = new f("--vm-snapshot-data=", "VmSnapshotData");
        f436f = fVar12;
        f fVar13 = new f("--isolate-snapshot-data=", "IsolateSnapshotData");
        f437g = fVar13;
        f fVar14 = new f("--enable-hcpp-and-surface-control", "EnableHcpp");
        f fVar15 = new f("--enable-flutter-gpu", "EnableFlutterGPU");
        f fVar16 = new f("--impeller-lazy-shader-mode", "ImpellerLazyShaderInitialization");
        f fVar17 = new f("--impeller-antialias-lines", "ImpellerAntialiasLines");
        f fVar18 = new f(0, "--enable-opengl-gpu-tracing", "EnableOpenGLGPUTracing");
        f fVar19 = new f(0, "--enable-vulkan-gpu-tracing", "EnableVulkanGPUTracing");
        f fVar20 = new f(0, "--skia-deterministic-rendering", "SkiaDeterministicRendering");
        f fVar21 = new f(0, "--enable-software-rendering", "EnableSoftwareRendering");
        f438h = fVar21;
        f fVar22 = new f(0, "--use-test-fonts", "UseTestFonts");
        f fVar23 = new f(0, "--vm-service-port=", "VMServicePort");
        f fVar24 = new f(0, "--enable-vulkan-validation", "EnableVulkanValidation");
        f fVar25 = new f(0, "--test-flag", "TestFlag");
        f439i = fVar25;
        f fVar26 = new f(0, "--leak-vm=", "LeakVM");
        f440j = fVar26;
        f fVar27 = new f(0, "--start-paused", "StartPaused");
        f fVar28 = new f(0, "--disable-service-auth-codes", "DisableServiceAuthCodes");
        f fVar29 = new f(0, "--disable-service-origin-check", "DisableServiceOriginCheck");
        f fVar30 = new f(0, "--endless-trace-buffer", "EndlessTraceBuffer");
        f fVar31 = new f(0, "--trace-skia", "TraceSkia");
        f fVar32 = new f(0, "--trace-skia-allowlist=", "TraceSkiaAllowList");
        f fVar33 = new f(0, "--trace-systrace", "TraceSystrace");
        f fVar34 = new f(0, "--trace-to-file=", "TraceToFile");
        f fVar35 = new f(0, "--profile-microtasks", "ProfileMicrotasks");
        f fVar36 = new f(0, "--dump-skp-on-shader-compilation", "DumpSkpOnShaderCompilation");
        f fVar37 = new f(0, "--purge-persistent-cache", "PurgePersistentCache");
        f fVar38 = new f(0, "--verbose-logging", "VerboseLogging");
        f fVar39 = new f(0, "--dart-flags=", "DartFlags");
        f fVar40 = new f(0, "--no-enable-merged-platform-ui-thread", "DisableMergedPlatformUIThread");
        List<f> listUnmodifiableList = Collections.unmodifiableList(Arrays.asList(fVar23, fVar22, fVar21, fVar20, fVar, fVar3, fVar6, fVar7, fVar24, fVar27, fVar28, fVar29, fVar30, fVar8, fVar9, fVar31, fVar32, fVar33, fVar34, fVar35, fVar36, fVar38, fVar39, fVar11, fVar40, fVar2, fVar4, fVar5, fVar12, fVar13, fVar37, fVar10, fVar26, fVar25, fVar15, fVar16, fVar17, fVar18, fVar19, fVar14));
        f441k = listUnmodifiableList;
        f442l = Collections.unmodifiableList(Arrays.asList(fVar40));
        G.b bVar = new G.b();
        bVar.put(fVar2, fVar);
        bVar.put(fVar4, fVar3);
        m = bVar;
        HashMap map = new HashMap(listUnmodifiableList.size());
        HashMap map2 = new HashMap(listUnmodifiableList.size());
        for (f fVar41 : listUnmodifiableList) {
            map.put(fVar41.f428a, fVar41);
            map2.put(fVar41.f429b, fVar41);
        }
        f443n = Collections.unmodifiableMap(map);
        Collections.unmodifiableMap(map2);
    }

    public static f a(String str) {
        int iIndexOf = str.indexOf(61);
        if (iIndexOf != -1) {
            str = str.substring(0, iIndexOf + 1);
        }
        f fVar = (f) f443n.get(str);
        f fVar2 = (f) m.get(fVar);
        return fVar2 != null ? fVar2 : fVar;
    }
}
