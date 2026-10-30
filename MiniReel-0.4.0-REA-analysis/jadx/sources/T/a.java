package T;

import J.e;
import android.content.Context;
import android.os.Build;
import android.os.Trace;
import io.flutter.embedding.engine.FlutterJNI;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Context f503a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ c f504b;

    public a(c cVar, Context context) {
        this.f504b = cVar;
        this.f503a = context;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        c cVar = this.f504b;
        Context context = this.f503a;
        e0.a.b("FlutterLoader initTask");
        int i2 = 0;
        try {
            try {
                FlutterJNI flutterJNI = cVar.f512f;
                flutterJNI.loadLibrary(context);
                flutterJNI.updateRefreshRate();
                cVar.f513g.execute(new e(2, this));
                File filesDir = context.getFilesDir();
                if (filesDir == null) {
                    filesDir = new File(context.getDataDir().getPath(), "files");
                }
                String path = filesDir.getPath();
                File codeCacheDir = context.getCodeCacheDir();
                if (codeCacheDir == null) {
                    codeCacheDir = context.getCacheDir();
                }
                if (codeCacheDir == null) {
                    codeCacheDir = new File(context.getDataDir().getPath(), "cache");
                }
                String path2 = codeCacheDir.getPath();
                File dir = context.getDir("flutter", 0);
                if (dir == null) {
                    dir = new File(context.getDataDir().getPath(), "app_flutter");
                }
                dir.getPath();
                b bVar = new b(path, path2);
                Trace.endSection();
                return bVar;
            } catch (UnsatisfiedLinkError e2) {
                if (!e2.toString().contains("couldn't find \"libflutter.so\"") && !e2.toString().contains("dlopen failed: library \"libflutter.so\" not found")) {
                    throw e2;
                }
                String property = System.getProperty("os.arch");
                File file = new File((String) cVar.f511e.f166f);
                String[] list = file.list();
                ArrayList arrayList = new ArrayList();
                String[] strArr = Build.SUPPORTED_ABIS;
                int length = strArr.length;
                int i3 = 0;
                while (i3 < length) {
                    String str = strArr[i3];
                    StringBuilder sb = new StringBuilder();
                    sb.append("!");
                    String str2 = File.separator;
                    sb.append(str2);
                    sb.append("lib");
                    sb.append(str2);
                    sb.append(str);
                    String string = sb.toString();
                    String[] strArr2 = context.getApplicationInfo().splitSourceDirs;
                    ArrayList arrayList2 = new ArrayList();
                    if (strArr2 != null) {
                        int length2 = strArr2.length;
                        for (int i4 = i2; i4 < length2; i4++) {
                            arrayList2.add(strArr2[i4] + string);
                        }
                        arrayList.addAll(arrayList2);
                    }
                    String str3 = context.getApplicationInfo().sourceDir;
                    if (str3 != null && !str3.isEmpty()) {
                        arrayList.add(str3 + string);
                    }
                    i3++;
                    i2 = 0;
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Could not load libflutter.so this is possibly because the application is running on an architecture that Flutter Android does not support (e.g. x86) see https://docs.flutter.dev/deployment/android#what-are-the-supported-target-architectures for more detail.\nApp is using cpu architecture: ");
                sb2.append(property);
                sb2.append(", and the native libraries directory (with path ");
                sb2.append(file.getAbsolutePath());
                sb2.append(") ");
                sb2.append(file.exists() ? "contains the following files: " + Arrays.toString(list) : "does not exist");
                sb2.append(arrayList.isEmpty() ? "" : ", and the split and source libraries directory (with path(s) " + arrayList + ")");
                sb2.append(".");
                throw new UnsupportedOperationException(sb2.toString(), e2);
            }
        } catch (Throwable th) {
            try {
                Trace.endSection();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }
}
