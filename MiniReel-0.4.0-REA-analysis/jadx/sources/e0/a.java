package e0;

import android.os.Trace;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements AutoCloseable {
    public static String a(String str) {
        if (str.length() < 124) {
            return str;
        }
        return str.substring(0, 124) + "...";
    }

    public static void b(String str) {
        Trace.beginSection(p000a.a.Q(a(str)));
    }
}
