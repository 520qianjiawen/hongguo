package O;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: O.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0011i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbstractActivityC0007e f314a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public P.c f315b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public t f316c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public io.flutter.plugin.platform.e f317d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public J.m f318e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ViewTreeObserverOnPreDrawListenerC0009g f319f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f320g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f321h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f323j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Integer f324k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final C0008f f325l = new C0008f(0, this);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f322i = false;

    public C0011i(AbstractActivityC0007e abstractActivityC0007e) {
        this.f314a = abstractActivityC0007e;
    }

    public final void a(P.i iVar) {
        String strB = this.f314a.b();
        if (strB == null || strB.isEmpty()) {
            strB = (String) ((T.c) G.f.d().f164d).f511e.f165e;
        }
        Q.a aVar = new Q.a(strB, this.f314a.e());
        String strF = this.f314a.f();
        if (strF == null) {
            AbstractActivityC0007e abstractActivityC0007e = this.f314a;
            abstractActivityC0007e.getClass();
            strF = d(abstractActivityC0007e.getIntent());
            if (strF == null) {
                strF = "/";
            }
        }
        iVar.f447b = aVar;
        iVar.f448c = strF;
        iVar.f449d = (List) this.f314a.getIntent().getSerializableExtra("dart_entrypoint_args");
    }

    public final void b() {
        if (this.f314a.i()) {
            throw new AssertionError("The internal FlutterEngine created by " + this.f314a + " has been attached to by another activity. To persist a FlutterEngine beyond the ownership of this activity, explicitly create a FlutterEngine");
        }
        AbstractActivityC0007e abstractActivityC0007e = this.f314a;
        abstractActivityC0007e.getClass();
        Log.w("FlutterActivity", "FlutterActivity " + abstractActivityC0007e + " connection to the engine " + abstractActivityC0007e.f307b.f315b + " evicted by another attaching activity");
        C0011i c0011i = abstractActivityC0007e.f307b;
        if (c0011i != null) {
            c0011i.e();
            abstractActivityC0007e.f307b.f();
        }
    }

    public final void c() {
        if (this.f314a == null) {
            throw new IllegalStateException("Cannot execute method on a destroyed FlutterActivityAndFragmentDelegate.");
        }
    }

    public final String d(Intent intent) {
        boolean z2;
        Uri data;
        AbstractActivityC0007e abstractActivityC0007e = this.f314a;
        abstractActivityC0007e.getClass();
        try {
            Bundle bundleG = abstractActivityC0007e.g();
            z2 = (bundleG == null || !bundleG.containsKey("flutter_deeplinking_enabled")) ? true : bundleG.getBoolean("flutter_deeplinking_enabled");
        } catch (PackageManager.NameNotFoundException unused) {
            z2 = false;
        }
        if (!z2 || (data = intent.getData()) == null) {
            return null;
        }
        return data.toString();
    }

    public final void e() {
        c();
        if (this.f319f != null) {
            this.f316c.getViewTreeObserver().removeOnPreDrawListener(this.f319f);
            this.f319f = null;
        }
        t tVar = this.f316c;
        if (tVar != null) {
            tVar.a();
            t tVar2 = this.f316c;
            tVar2.f358g.remove(this.f325l);
        }
    }

    public final void f() {
        if (this.f323j) {
            c();
            this.f314a.getClass();
            this.f314a.getClass();
            AbstractActivityC0007e abstractActivityC0007e = this.f314a;
            abstractActivityC0007e.getClass();
            if (abstractActivityC0007e.isChangingConfigurations()) {
                P.e eVar = this.f315b.f395c;
                if (eVar.f()) {
                    e0.a.b("FlutterEngineConnectionRegistry#detachFromActivityForConfigChanges");
                    try {
                        eVar.f420a = true;
                        Iterator it = ((HashMap) eVar.f422c).values().iterator();
                        while (it.hasNext()) {
                            ((W.a) it.next()).onDetachedFromActivityForConfigChanges();
                        }
                        eVar.d();
                        Trace.endSection();
                    } catch (Throwable th) {
                        try {
                            Trace.endSection();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                } else {
                    Log.e("FlutterEngineCxnRegstry", "Attempted to detach plugins from an Activity when no Activity was attached.");
                }
            } else {
                this.f315b.f395c.c();
            }
            io.flutter.plugin.platform.e eVar2 = this.f317d;
            if (eVar2 != null) {
                eVar2.f875b.f171f = null;
                this.f317d = null;
            }
            J.m mVar = this.f318e;
            if (mVar != null) {
                ((C0.x) mVar.f223c).f90e = null;
                mVar.f222b = null;
                this.f318e = null;
            }
            this.f314a.getClass();
            P.c cVar = this.f315b;
            if (cVar != null) {
                Y.d dVar = cVar.f398f;
                dVar.a(1, dVar.f523c);
            }
            if (this.f314a.i()) {
                this.f315b.a();
                if (this.f314a.d() != null) {
                    if (P.k.f454c == null) {
                        P.k.f454c = new P.k(1);
                    }
                    P.k kVar = P.k.f454c;
                    kVar.f455a.remove(this.f314a.d());
                }
                this.f315b = null;
            }
            this.f323j = false;
        }
    }
}
