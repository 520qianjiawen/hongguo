package p012l;

import android.view.View;
import android.view.Window;
import p000a.a;

/* JADX INFO: loaded from: classes.dex */
public class s extends a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Window f1103i;

    public s(Window window) {
        super(24);
        this.f1103i = window;
    }

    @Override // p000a.a
    public final void M(boolean z2) {
        Window window = this.f1103i;
        if (!z2) {
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (-8193));
        } else {
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            View decorView2 = window.getDecorView();
            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() | 8192);
        }
    }
}
