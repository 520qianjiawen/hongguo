package p012l;

import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import p000a.a;

/* JADX INFO: loaded from: classes.dex */
public final class v extends a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final WindowInsetsController f1104i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Window f1105j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(Window window) {
        super(24);
        WindowInsetsController insetsController = window.getInsetsController();
        this.f1104i = insetsController;
        this.f1105j = window;
    }

    @Override // p000a.a
    public final void L(boolean z2) {
        Window window = this.f1105j;
        if (z2) {
            if (window != null) {
                View decorView = window.getDecorView();
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 16);
            }
            this.f1104i.setSystemBarsAppearance(16, 16);
            return;
        }
        if (window != null) {
            View decorView2 = window.getDecorView();
            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() & (-17));
        }
        this.f1104i.setSystemBarsAppearance(0, 16);
    }

    @Override // p000a.a
    public final void M(boolean z2) {
        Window window = this.f1105j;
        if (z2) {
            if (window != null) {
                View decorView = window.getDecorView();
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 8192);
            }
            this.f1104i.setSystemBarsAppearance(8, 8);
            return;
        }
        if (window != null) {
            View decorView2 = window.getDecorView();
            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() & (-8193));
        }
        this.f1104i.setSystemBarsAppearance(0, 8);
    }
}
