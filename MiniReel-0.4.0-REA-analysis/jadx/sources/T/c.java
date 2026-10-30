package T;

import G.f;
import P.g;
import android.app.ActivityManager;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.os.Trace;
import android.util.DisplayMetrics;
import android.util.Log;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.view.B;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f507a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f508b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public H.b f509c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f510d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public f f511e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public FlutterJNI f512f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ExecutorService f513g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Future f514h;

    public static String b(Context context, String str) throws IOException {
        File file = new File(str);
        try {
            String canonicalPath = file.getCanonicalPath();
            boolean zStartsWith = canonicalPath.startsWith(context.getApplicationContext().getFilesDir().getCanonicalPath() + File.separator);
            boolean zEndsWith = canonicalPath.endsWith(".so");
            if (zStartsWith && zEndsWith) {
                return canonicalPath;
            }
            Log.e("FlutterLoader", "External path " + canonicalPath + " rejected; not overriding aot-shared-library-name.");
            return null;
        } catch (IOException unused) {
            Log.e("FlutterLoader", "External path " + file.getPath() + " is not a valid path. Please ensure this shared AOT library exists.");
            return null;
        }
    }

    public static void c(Context context, String str, ArrayList arrayList) {
        String strB;
        try {
            strB = b(context, str);
        } catch (IOException e2) {
            Log.e("FlutterLoader", "Error while validating AOT shared library name flag: " + str, e2);
            strB = null;
        }
        if (strB != null) {
            arrayList.add(0, g.f431a.f428a + strB);
            return;
        }
        Log.e("FlutterLoader", "Skipping unsafe AOT shared library name flag: " + str + ". Please ensure that the library is vetted and placed in your application's internal storage.");
    }

    public final void a(Context context, String[] strArr) {
        boolean z2;
        boolean z3;
        Iterator it;
        if (this.f508b) {
            return;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ensureInitializationComplete must be called on the main thread");
        }
        if (this.f509c == null) {
            throw new IllegalStateException("ensureInitializationComplete must be called after startInitialization");
        }
        try {
            e0.a.b("FlutterLoader#ensureInitializationComplete");
            try {
                b bVar = (b) this.f514h.get();
                ArrayList arrayList = new ArrayList();
                arrayList.add("--icu-symbol-prefix=_binary_icudtl_dat");
                arrayList.add("--icu-native-lib-path=" + ((String) this.f511e.f166f) + File.separator + "libflutter.so");
                Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
                if (bundle != null) {
                    z2 = false;
                    z3 = false;
                    for (Iterator it2 = g.f441k.iterator(); it2.hasNext(); it2 = it) {
                        P.f fVar = (P.f) it2.next();
                        String str = fVar.f429b;
                        if (!bundle.containsKey(str)) {
                            it = it2;
                        } else if (fVar == g.f439i) {
                            Log.w("FlutterLoader", "For testing purposes only: test flag specified in the manifest was loaded by the FlutterLoader.");
                            it = it2;
                        } else {
                            if (g.f442l.contains(fVar)) {
                                throw new IllegalArgumentException(str + " is disabled and no longer allowed. Please remove this flag from your application manifest.");
                            }
                            G.b bVar2 = g.m;
                            if (((P.f) bVar2.get(fVar)) != null) {
                                StringBuilder sb = new StringBuilder();
                                it = it2;
                                sb.append("If you are trying to specify ");
                                sb.append(str);
                                sb.append(" in your application manifest, please make sure to use the new metadata key name: ");
                                sb.append(((P.f) bVar2.get(fVar)).f429b);
                                Log.w("FlutterLoader", sb.toString());
                            } else {
                                it = it2;
                                if (!fVar.f430c) {
                                    Log.e("FlutterLoader", "Flag with metadata key " + str + " is not allowed in release builds and will be ignored if specified in the application manifest or via the command line.");
                                }
                            }
                            if (fVar == g.f435e) {
                                z2 = true;
                            } else if (fVar == g.f440j) {
                                z3 = true;
                            } else {
                                P.f fVar2 = g.f438h;
                                if (fVar == fVar2) {
                                    this.f507a = bundle.getBoolean(fVar2.f429b, false);
                                } else if (fVar == g.f431a || fVar == g.f432b) {
                                    String string = bundle.getString(str);
                                    if (string == null) {
                                        Log.e("FlutterLoader", "Flag " + str + " was specified with an empty path. Please specify a path to the desired AOT shared library.");
                                    } else {
                                        c(context, string, arrayList);
                                    }
                                }
                            }
                            String str2 = fVar.f428a;
                            if (str2.endsWith("=")) {
                                Object obj = bundle.get(str);
                                String string2 = obj != null ? obj.toString() : null;
                                if (string2 == null) {
                                    Log.e("FlutterLoader", "Flag with metadata key " + str + " requires a value, but no value was found. Please specify a value.");
                                } else {
                                    arrayList.add(str2 + string2);
                                }
                            } else if (bundle.getBoolean(str, false)) {
                                arrayList.add(str2);
                            }
                        }
                    }
                } else {
                    z2 = false;
                    z3 = false;
                }
                if (strArr != null) {
                    for (String str3 : strArr) {
                        P.f fVarA = g.a(str3);
                        if (fVarA == null) {
                            arrayList.add(str3);
                        } else if (fVarA.equals(g.f439i)) {
                            Log.w("FlutterLoader", "For testing purposes only: test flag specified on the command line was loaded by the FlutterLoader.");
                        } else {
                            P.f fVar3 = g.f431a;
                            if (fVarA.equals(fVar3) || fVarA.equals(g.f432b)) {
                                c(context, str3.substring(fVar3.f428a.length()), arrayList);
                            } else if (fVarA.f430c) {
                                arrayList.add(str3);
                            } else {
                                Log.e("FlutterLoader", "Command line argument " + str3 + " is not allowed in release builds and will be ignored if specified in the application manifest or via the command line.");
                            }
                        }
                    }
                }
                StringBuilder sb2 = new StringBuilder();
                P.f fVar4 = g.f431a;
                sb2.append(fVar4.f428a);
                sb2.append((String) this.f511e.f164d);
                arrayList.add(sb2.toString());
                arrayList.add(fVar4.f428a + ((String) this.f511e.f166f) + File.separator + ((String) this.f511e.f164d));
                StringBuilder sb3 = new StringBuilder();
                sb3.append("--cache-dir-path=");
                sb3.append(bVar.f506b);
                arrayList.add(sb3.toString());
                Objects.requireNonNull(this.f511e);
                StringBuilder sb4 = new StringBuilder();
                sb4.append("--domain-network-policy=");
                Objects.requireNonNull(this.f511e);
                sb4.append("");
                arrayList.add(sb4.toString());
                this.f509c.getClass();
                if (!z2) {
                    ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                    ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                    activityManager.getMemoryInfo(memoryInfo);
                    arrayList.add(g.f435e.f428a + ((int) ((memoryInfo.totalMem / 1000000.0d) / 2.0d)));
                }
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                arrayList.add("--resource-cache-max-bytes-threshold=" + (displayMetrics.widthPixels * displayMetrics.heightPixels * 48));
                arrayList.add("--prefetched-default-font-manager");
                if (!z3) {
                    arrayList.add(g.f440j.f428a + "true");
                }
                this.f512f.init(context, (String[]) arrayList.toArray(new String[0]), null, bVar.f505a, bVar.f506b, SystemClock.uptimeMillis() - this.f510d, Build.VERSION.SDK_INT);
                this.f508b = true;
                Trace.endSection();
            } catch (Throwable th) {
                try {
                    Trace.endSection();
                    throw th;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                    throw th;
                }
            }
        } catch (Exception e2) {
            Log.e("FlutterLoader", "Flutter initialization failed.", e2);
            throw new RuntimeException(e2);
        }
    }

    public final void d(Context context) {
        H.b bVar = new H.b(5);
        if (this.f509c != null) {
            return;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("startInitialization must be called on the main thread");
        }
        e0.a.b("FlutterLoader#startInitialization");
        try {
            Context applicationContext = context.getApplicationContext();
            this.f509c = bVar;
            this.f510d = SystemClock.uptimeMillis();
            this.f511e = p000a.a.B(applicationContext);
            B bA = B.a((DisplayManager) applicationContext.getSystemService("display"), this.f512f);
            bA.f927b.setAsyncWaitForVsyncDelegate(bA.f929d);
            this.f514h = this.f513g.submit(new a(this, applicationContext));
            Trace.endSection();
        } catch (Throwable th) {
            try {
                Trace.endSection();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
