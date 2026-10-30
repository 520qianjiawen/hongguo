package androidx.lifecycle;

import C0.x;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public final class m implements i {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final m f640i = new m();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f641a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f642b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Handler f645e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f643c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f644d = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final j f646f = new j(this);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final J.e f647g = new J.e(3, this);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final x f648h = new x(29, this);

    @Override // androidx.lifecycle.i
    public final j a() {
        return this.f646f;
    }

    public final void b() {
        int i2 = this.f642b + 1;
        this.f642b = i2;
        if (i2 == 1) {
            if (this.f643c) {
                this.f646f.a(d.ON_RESUME);
                this.f643c = false;
            } else {
                Handler handler = this.f645e;
                p0.h.b(handler);
                handler.removeCallbacks(this.f647g);
            }
        }
    }
}
