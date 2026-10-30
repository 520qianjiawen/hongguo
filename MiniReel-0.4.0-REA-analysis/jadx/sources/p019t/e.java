package p019t;

import androidx.window.extensions.WindowExtensionsProvider;
import p0.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {
    static {
        n.a(e.class).b();
    }

    public static int a() {
        try {
            return WindowExtensionsProvider.getWindowExtensions().getVendorApiLevel();
        } catch (NoClassDefFoundError | UnsupportedOperationException unused) {
            return 0;
        }
    }
}
