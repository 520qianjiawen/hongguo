package io.flutter.plugin.platform;

import C0.x;
import O.AbstractActivityC0007e;
import O.C0013k;
import O.t;
import android.util.SparseArray;
import android.view.View;
import io.flutter.embedding.engine.FlutterJNI;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class j implements h {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AbstractActivityC0007e f894e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public t f895f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public io.flutter.embedding.engine.renderer.j f897h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public io.flutter.plugin.editing.m f898i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public x f899j;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final H.a f910w;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public FlutterJNI f896g = null;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f906r = 0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f907s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f908t = true;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final p002b0.a f911x = new p002b0.a(3, this);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final P.k f893d = new P.k(2);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final HashMap f901l = new HashMap();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final a f900k = new a();
    public final HashMap m = new HashMap();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final SparseArray f904p = new SparseArray();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final HashSet f909u = new HashSet();
    public final HashSet v = new HashSet();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final SparseArray f905q = new SparseArray();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final SparseArray f902n = new SparseArray();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final SparseArray f903o = new SparseArray();

    public j() {
        if (H.a.f167g == null) {
            H.a.f167g = new H.a(3);
        }
        this.f910w = H.a.f167g;
    }

    public static void b(j jVar, Y.g gVar) {
        int i2 = gVar.f533c;
        if (i2 == 0 || i2 == 1) {
            return;
        }
        throw new IllegalStateException("Trying to create a view with unknown direction value: " + i2 + "(view id: " + gVar.f531a + ")");
    }

    @Override // io.flutter.plugin.platform.h
    public final void a() {
        this.f900k.f868a = null;
    }

    public final void c() {
        int i2 = 0;
        while (true) {
            SparseArray sparseArray = this.f904p;
            if (i2 >= sparseArray.size()) {
                return;
            }
            b bVar = (b) sparseArray.valueAt(i2);
            bVar.c();
            bVar.f326a.close();
            i2++;
        }
    }

    @Override // io.flutter.plugin.platform.h
    public final void d(io.flutter.view.n nVar) {
        this.f900k.f868a = nVar;
    }

    public final void e(boolean z2) {
        int i2 = 0;
        while (true) {
            SparseArray sparseArray = this.f904p;
            if (i2 >= sparseArray.size()) {
                break;
            }
            int iKeyAt = sparseArray.keyAt(i2);
            b bVar = (b) sparseArray.valueAt(i2);
            if (this.f909u.contains(Integer.valueOf(iKeyAt))) {
                P.c cVar = this.f895f.f360i;
                if (cVar != null) {
                    bVar.a(cVar.f393a);
                }
                z2 &= bVar.e();
            } else {
                if (!this.f907s) {
                    bVar.c();
                }
                bVar.setVisibility(8);
                this.f895f.removeView(bVar);
            }
            i2++;
        }
        int i3 = 0;
        while (true) {
            SparseArray sparseArray2 = this.f903o;
            if (i3 >= sparseArray2.size()) {
                return;
            }
            int iKeyAt2 = sparseArray2.keyAt(i3);
            View view = (View) sparseArray2.get(iKeyAt2);
            if (!this.v.contains(Integer.valueOf(iKeyAt2)) || (!z2 && this.f908t)) {
                view.setVisibility(8);
            } else {
                view.setVisibility(0);
            }
            i3++;
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [android.view.View, io.flutter.embedding.engine.renderer.m] */
    public final void f() {
        if (!this.f908t || this.f907s) {
            return;
        }
        t tVar = this.f895f;
        tVar.f356e.b();
        C0013k c0013k = tVar.f355d;
        if (c0013k == null) {
            C0013k c0013k2 = new C0013k(tVar.getContext(), tVar.getWidth(), tVar.getHeight(), 1);
            tVar.f355d = c0013k2;
            tVar.addView(c0013k2);
        } else {
            c0013k.g(tVar.getWidth(), tVar.getHeight());
        }
        tVar.f357f = tVar.f356e;
        C0013k c0013k3 = tVar.f355d;
        tVar.f356e = c0013k3;
        P.c cVar = tVar.f360i;
        if (cVar != null) {
            c0013k3.a(cVar.f393a);
        }
        this.f907s = true;
    }

    public final int g(double d2) {
        return (int) Math.round(d2 * ((double) this.f894e.getResources().getDisplayMetrics().density));
    }

    @Override // io.flutter.plugin.platform.h
    public final boolean h(int i2) {
        return this.f901l.containsKey(Integer.valueOf(i2));
    }

    @Override // io.flutter.plugin.platform.h
    public final void r(int i2) {
        if (h(i2)) {
            ((p) this.f901l.get(Integer.valueOf(i2))).getClass();
        } else if (this.f902n.get(i2) != null) {
            throw new ClassCastException();
        }
    }
}
