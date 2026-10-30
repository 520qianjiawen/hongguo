package p016q;

import android.os.Trace;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static void a(String str, int i2) {
        Trace.beginAsyncSection(str, i2);
    }

    public static void b(String str, int i2) {
        Trace.endAsyncSection(str, i2);
    }

    public static boolean c() {
        return Trace.isEnabled();
    }
}
