package io.flutter.plugin.platform;

import C0.x;
import O.AbstractActivityC0007e;
import O.t;
import android.util.SparseArray;
import android.view.Surface;
import android.view.SurfaceControl;
import io.flutter.embedding.engine.FlutterJNI;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class i implements h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public P.k f880d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AbstractActivityC0007e f881e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public t f882f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public x f884h;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public FlutterJNI f883g = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Surface f889n = null;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public SurfaceControl f890o = null;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final HashSet f891p = new HashSet();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final p002b0.a f892q = new p002b0.a(4, this);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final a f885i = new a();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final SparseArray f886j = new SparseArray();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final SparseArray f887k = new SparseArray();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList f888l = new ArrayList();
    public final ArrayList m = new ArrayList();

    public i() {
        if (H.a.f167g == null) {
            H.a.f167g = new H.a(3);
        }
    }

    @Override // io.flutter.plugin.platform.h
    public final void a() {
        this.f885i.f868a = null;
    }

    @Override // io.flutter.plugin.platform.h
    public final void d(io.flutter.view.n nVar) {
        this.f885i.f868a = nVar;
    }

    @Override // io.flutter.plugin.platform.h
    public final boolean h(int i2) {
        return false;
    }

    @Override // io.flutter.plugin.platform.h
    public final void r(int i2) {
        if (this.f886j.get(i2) != null) {
            throw new ClassCastException();
        }
    }
}
