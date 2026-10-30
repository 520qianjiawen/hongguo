package p014o;

import H.b;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.File;
import java.io.IOException;
import p006f.g;
import p006f.h;

/* JADX INFO: loaded from: classes.dex */
public abstract class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f1191a = new h();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f1192b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static b f1193c = null;

    public static long a(Context context) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        return Build.VERSION.SDK_INT >= 33 ? l.a(packageManager, context).lastUpdateTime : packageManager.getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    public static b b() {
        b bVar = new b(15);
        f1193c = bVar;
        h hVar = f1191a;
        hVar.getClass();
        if (g.f713i.g(hVar, null, bVar)) {
            g.b(hVar);
        }
        return f1193c;
    }

    public static void c(Context context, boolean z2) {
        m mVarA;
        int i2;
        if (z2 || f1193c == null) {
            synchronized (f1192b) {
                if (!z2) {
                    try {
                        if (f1193c != null) {
                            return;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                int i3 = Build.VERSION.SDK_INT;
                if (i3 >= 28 && i3 != 30) {
                    File file = new File(new File("/data/misc/profiles/ref/", context.getPackageName()), "primary.prof");
                    long length = file.length();
                    int i4 = 0;
                    boolean z3 = file.exists() && length > 0;
                    File file2 = new File(new File("/data/misc/profiles/cur/0/", context.getPackageName()), "primary.prof");
                    long length2 = file2.length();
                    boolean z4 = file2.exists() && length2 > 0;
                    try {
                        long jA = a(context);
                        File file3 = new File(context.getFilesDir(), "profileInstalled");
                        if (file3.exists()) {
                            try {
                                mVarA = m.a(file3);
                            } catch (IOException unused) {
                                b();
                                return;
                            }
                        } else {
                            mVarA = null;
                        }
                        if (mVarA != null && mVarA.f1189c == jA && (i2 = mVarA.f1188b) != 2) {
                            i4 = i2;
                        } else if (z3) {
                            i4 = 1;
                        } else if (z4) {
                            i4 = 2;
                        }
                        if (z2 && z4 && i4 != 1) {
                            i4 = 2;
                        }
                        if (mVarA != null && mVarA.f1188b == 2 && i4 == 1 && length < mVarA.f1190d) {
                            i4 = 3;
                        }
                        m mVar = new m(1, i4, jA, length2);
                        if (mVarA == null || !mVarA.equals(mVar)) {
                            try {
                                mVar.b(file3);
                            } catch (IOException unused2) {
                            }
                        }
                        b();
                        return;
                    } catch (PackageManager.NameNotFoundException unused3) {
                        b();
                        return;
                    }
                }
                b();
            }
        }
    }
}
