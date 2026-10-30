package p012l;

import android.view.WindowInsets;
import p008h.a;

/* JADX INFO: loaded from: classes.dex */
public class n extends l {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public a f1096n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public a f1097o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public a f1098p;

    public n(r rVar, WindowInsets windowInsets) {
        super(rVar, windowInsets);
        this.f1096n = null;
        this.f1097o = null;
        this.f1098p = null;
    }

    @Override // p012l.p
    public a f() {
        if (this.f1097o == null) {
            this.f1097o = a.b(this.f1091c.getMandatorySystemGestureInsets());
        }
        return this.f1097o;
    }

    @Override // p012l.p
    public a h() {
        if (this.f1096n == null) {
            this.f1096n = a.b(this.f1091c.getSystemGestureInsets());
        }
        return this.f1096n;
    }

    @Override // p012l.p
    public a j() {
        if (this.f1098p == null) {
            this.f1098p = a.b(this.f1091c.getTappableElementInsets());
        }
        return this.f1098p;
    }

    @Override // p012l.k, p012l.p
    public void p(a aVar) {
    }
}
