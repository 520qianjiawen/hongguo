package p014o;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {
    public static Handler a(Looper looper) {
        return Handler.createAsync(looper);
    }
}
