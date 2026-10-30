package Y;

import android.util.DisplayMetrics;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f561c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f562a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DisplayMetrics f563b;

    public m(DisplayMetrics displayMetrics) {
        int i2 = f561c;
        f561c = i2 + 1;
        this.f562a = i2;
        this.f563b = displayMetrics;
    }
}
