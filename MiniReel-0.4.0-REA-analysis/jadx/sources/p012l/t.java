package p012l;

import android.view.View;
import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public final class t extends s {
    @Override // p000a.a
    public final void L(boolean z2) {
        Window window = this.f1103i;
        if (!z2) {
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (-17));
        } else {
            window.clearFlags(134217728);
            window.addFlags(Integer.MIN_VALUE);
            View decorView2 = window.getDecorView();
            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() | 16);
        }
    }
}
