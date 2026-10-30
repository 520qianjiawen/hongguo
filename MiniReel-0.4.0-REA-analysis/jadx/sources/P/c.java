package P;

import C0.x;
import Z.p;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Trace;
import android.util.SparseArray;
import io.flutter.embedding.engine.FlutterJNI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class c implements e0.b {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static long f391x = 1;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final HashMap f392y = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final io.flutter.embedding.engine.renderer.j f393a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Q.b f394b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e f395c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final p001a0.a f396d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final G.f f397e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Y.d f398f;
    private final FlutterJNI flutterJNI;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final x f399g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Y.b f400h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Y.b f401i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Y.l f402j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final H.a f403k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final x f404l;
    public final x m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Y.n f405n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final x f406o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Y.c f407p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final H.a f408q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final io.flutter.plugin.platform.j f409r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final io.flutter.plugin.platform.i f410s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final H.a f411t;
    public final long v;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final HashSet f412u = new HashSet();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final a f413w = new a(this);

    public c(Context context, FlutterJNI flutterJNI, io.flutter.plugin.platform.j jVar, boolean z2, boolean z3) throws Exception {
        AssetManager assets;
        long j2 = f391x;
        f391x = 1 + j2;
        this.v = j2;
        f392y.put(Long.valueOf(j2), this);
        try {
            assets = context.createPackageContext(context.getPackageName(), 0).getAssets();
        } catch (PackageManager.NameNotFoundException unused) {
            assets = context.getAssets();
        }
        G.f fVarD = G.f.d();
        if (flutterJNI == null) {
            Object obj = fVarD.f165e;
            flutterJNI = new FlutterJNI();
        }
        this.flutterJNI = flutterJNI;
        Q.b bVar = new Q.b(flutterJNI, assets, this.v);
        this.f394b = bVar;
        flutterJNI.setPlatformMessageHandler(bVar.f463g);
        G.f.d().getClass();
        G.f fVar = new G.f();
        x xVar = new x(9, fVar);
        G.f fVar2 = new G.f(bVar, "flutter/accessibility", p.f595a);
        fVar.f164d = fVar2;
        fVar2.i(xVar);
        fVar.f165e = flutterJNI;
        this.f397e = fVar;
        new H.b(bVar);
        this.f398f = new Y.d(bVar);
        H.a aVar = new H.a(bVar, 5);
        this.f399g = new x(bVar, 13);
        this.f400h = new Y.b(bVar, 1);
        this.f401i = new Y.b(bVar, 0);
        this.f403k = new H.a(bVar, 6);
        H.a aVar2 = new H.a(bVar, context.getPackageManager());
        this.f402j = new Y.l(bVar, z3);
        this.f404l = new x(bVar, 22);
        this.m = new x(bVar, 24);
        Y.n nVar = new Y.n(bVar);
        this.f405n = nVar;
        this.f406o = new x(bVar, 26);
        this.f407p = new Y.c(bVar);
        this.f408q = new H.a(bVar, 9);
        p001a0.a aVar3 = new p001a0.a(context, aVar);
        this.f396d = aVar3;
        T.c cVar = (T.c) fVarD.f164d;
        if (!flutterJNI.isAttached()) {
            cVar.d(context.getApplicationContext());
            cVar.a(context, null);
        }
        io.flutter.plugin.platform.i iVar = new io.flutter.plugin.platform.i();
        iVar.f880d = jVar.f893d;
        iVar.f883g = flutterJNI;
        jVar.f896g = flutterJNI;
        flutterJNI.addEngineLifecycleListener(this.f413w);
        flutterJNI.setPlatformViewsController(jVar);
        flutterJNI.setPlatformViewsController2(iVar);
        flutterJNI.setLocalizationPlugin(aVar3);
        fVarD.getClass();
        flutterJNI.setDeferredComponentManager(null);
        flutterJNI.setSettingsChannel(nVar);
        if (!flutterJNI.isAttached()) {
            flutterJNI.attachToNative();
            if (!flutterJNI.isAttached()) {
                throw new RuntimeException("FlutterEngine failed to attach to its native Object reference.");
            }
        }
        this.f393a = new io.flutter.embedding.engine.renderer.j(flutterJNI);
        this.f409r = jVar;
        this.f410s = iVar;
        H.a aVar4 = new H.a(16, false);
        aVar4.f170e = jVar;
        aVar4.f171f = iVar;
        this.f411t = aVar4;
        e eVar = new e(context.getApplicationContext(), this, cVar);
        this.f395c = eVar;
        aVar3.b(context.getResources().getConfiguration());
        if (z2) {
            Objects.requireNonNull(cVar.f511e);
            p000a.a.J(this);
        }
        p000a.a.c(context, this);
        eVar.a(new p004c0.a(aVar2));
    }

    public final void a() {
        Iterator it = this.f412u.iterator();
        while (it.hasNext()) {
            ((b) it.next()).b();
        }
        e eVar = this.f395c;
        eVar.e();
        HashMap map = (HashMap) eVar.f421b;
        for (Class cls : new HashSet(map.keySet())) {
            V.b bVar = (V.b) map.get(cls);
            if (bVar != null) {
                e0.a.b("FlutterEngineConnectionRegistry#remove ".concat(cls.getSimpleName()));
                try {
                    if (bVar instanceof W.a) {
                        if (eVar.f()) {
                            ((W.a) bVar).onDetachedFromActivity();
                        }
                        ((HashMap) eVar.f422c).remove(cls);
                    }
                    bVar.onDetachedFromEngine((V.a) eVar.f425f);
                    map.remove(cls);
                    Trace.endSection();
                } catch (Throwable th) {
                    try {
                        Trace.endSection();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
        }
        map.clear();
        io.flutter.plugin.platform.j jVar = this.f409r;
        SparseArray sparseArray = jVar.f902n;
        while (sparseArray.size() > 0) {
            jVar.f911x.l(sparseArray.keyAt(0));
        }
        io.flutter.plugin.platform.i iVar = this.f410s;
        SparseArray sparseArray2 = iVar.f886j;
        while (sparseArray2.size() > 0) {
            iVar.f892q.l(sparseArray2.keyAt(0));
        }
        this.f394b.f460d.setPlatformMessageHandler(null);
        this.flutterJNI.removeEngineLifecycleListener(this.f413w);
        this.flutterJNI.setDeferredComponentManager(null);
        this.flutterJNI.detachFromNativeAndReleaseResources();
        G.f.d().getClass();
        f392y.remove(Long.valueOf(this.v));
    }

    public final c b(Context context, Q.a aVar, String str, List list, io.flutter.plugin.platform.j jVar, boolean z2, boolean z3) {
        if (this.flutterJNI.isAttached()) {
            return new c(context, this.flutterJNI.spawn(aVar.f459c, aVar.f458b, str, list, f391x), jVar, z2, z3);
        }
        throw new IllegalStateException("Spawn can only be called on a fully constructed FlutterEngine");
    }

    public final void c(float f2, float f3, float f4) {
        this.flutterJNI.updateDisplayMetrics(0, f2, f3, f4);
    }
}
