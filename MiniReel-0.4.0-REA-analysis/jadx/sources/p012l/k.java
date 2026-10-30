package p012l;

import android.view.WindowInsets;
import p008h.a;

/* JADX INFO: loaded from: classes.dex */
public class k extends j {
    public a m;

    public k(r rVar, WindowInsets windowInsets) {
        super(rVar, windowInsets);
        this.m = null;
    }

    @Override // p012l.p
    public r b() {
        return r.a(null, this.f1091c.consumeStableInsets());
    }

    @Override // p012l.p
    public r c() {
        return r.a(null, this.f1091c.consumeSystemWindowInsets());
    }

    @Override // p012l.p
    public final a g() {
        if (this.m == null) {
            WindowInsets windowInsets = this.f1091c;
            this.m = a.a(windowInsets.getStableInsetLeft(), windowInsets.getStableInsetTop(), windowInsets.getStableInsetRight(), windowInsets.getStableInsetBottom());
        }
        return this.m;
    }

    @Override // p012l.p
    public boolean k() {
        return this.f1091c.isConsumed();
    }

    @Override // p012l.p
    public void p(a aVar) {
        this.m = aVar;
    }
}
