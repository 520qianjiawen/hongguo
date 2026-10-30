package A;

import android.content.Context;
import android.view.WindowInsets;
import android.view.WindowManager;
import p0.h;
import p012l.r;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f0a = new b();

    public final r a(Context context) {
        h.e(context, "context");
        WindowInsets windowInsets = ((WindowManager) context.getSystemService(WindowManager.class)).getCurrentWindowMetrics().getWindowInsets();
        h.d(windowInsets, "context.getSystemService…indowMetrics.windowInsets");
        return r.a(null, windowInsets);
    }
}
