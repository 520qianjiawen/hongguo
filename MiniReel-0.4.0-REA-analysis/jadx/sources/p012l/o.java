package p012l;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public final class o extends n {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ int f1099q = 0;

    static {
        r.a(null, WindowInsets.CONSUMED);
    }

    public o(r rVar, WindowInsets windowInsets) {
        super(rVar, windowInsets);
    }

    @Override // p012l.j, p012l.p
    public boolean m(int i2) {
        return this.f1091c.isVisible(q.a(i2));
    }

    @Override // p012l.j, p012l.p
    public final void d(View view) {
    }
}
