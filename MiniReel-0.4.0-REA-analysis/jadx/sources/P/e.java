package P;

import C0.x;
import O.AbstractActivityC0007e;
import O.C0011i;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Trace;
import android.util.Log;
import android.view.Surface;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f420a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f421b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f422c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f423d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f424e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f425f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f426g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f427h;

    public e(Context context, c cVar, T.c cVar2) {
        this.f421b = new HashMap();
        this.f422c = new HashMap();
        this.f420a = false;
        new HashMap();
        new HashMap();
        new HashMap();
        this.f423d = cVar;
        this.f424e = cVar2;
        Q.b bVar = cVar.f394b;
        io.flutter.embedding.engine.renderer.j jVar = cVar.f393a;
        k kVar = cVar.f409r.f893d;
        this.f425f = new V.a(context, cVar, bVar, jVar);
    }

    public void a(V.b bVar) {
        HashMap map = (HashMap) this.f421b;
        e0.a.b("FlutterEngineConnectionRegistry#add ".concat(bVar.getClass().getSimpleName()));
        try {
            if (map.containsKey(bVar.getClass())) {
                Log.w("FlutterEngineCxnRegstry", "Attempted to register plugin (" + bVar + ") but it was already registered with this FlutterEngine (" + ((c) this.f423d) + ").");
                Trace.endSection();
                return;
            }
            bVar.toString();
            map.put(bVar.getClass(), bVar);
            bVar.onAttachedToEngine((V.a) this.f425f);
            if (bVar instanceof W.a) {
                W.a aVar = (W.a) bVar;
                ((HashMap) this.f422c).put(bVar.getClass(), aVar);
                if (f()) {
                    aVar.onAttachedToActivity((d) this.f427h);
                }
            }
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

    public void b(AbstractActivityC0007e abstractActivityC0007e, androidx.lifecycle.j jVar) {
        this.f427h = new d(abstractActivityC0007e, jVar);
        Intent intent = abstractActivityC0007e.getIntent();
        if (intent != null ? intent.getBooleanExtra("enable-software-rendering", false) : false) {
            String str = g.f438h.f429b;
        } else {
            boolean z2 = ((T.c) this.f424e).f507a;
        }
        c cVar = (c) this.f423d;
        cVar.f409r.getClass();
        H.a aVar = cVar.f411t;
        io.flutter.embedding.engine.renderer.j jVar2 = cVar.f393a;
        Q.b bVar = cVar.f394b;
        io.flutter.plugin.platform.j jVar3 = (io.flutter.plugin.platform.j) aVar.f170e;
        if (jVar3.f894e != null) {
            throw new AssertionError("A PlatformViewsController can only be attached to a single output target.\nattach was called while the PlatformViewsController was already attached.");
        }
        jVar3.f894e = abstractActivityC0007e;
        jVar3.f897h = jVar2;
        jVar3.f899j = new x(bVar, 18);
        io.flutter.plugin.platform.i iVar = (io.flutter.plugin.platform.i) aVar.f171f;
        if (iVar.f881e != null) {
            throw new AssertionError("A PlatformViewsController can only be attached to a single output target.\nattach was called while the PlatformViewsController was already attached.");
        }
        iVar.f881e = abstractActivityC0007e;
        x xVar = new x(bVar, 17);
        iVar.f884h = xVar;
        xVar.f90e = iVar.f892q;
        jVar3.f899j.f90e = aVar;
        for (W.a aVar2 : ((HashMap) this.f422c).values()) {
            if (this.f420a) {
                aVar2.onReattachedToActivityForConfigChanges((d) this.f427h);
            } else {
                aVar2.onAttachedToActivity((d) this.f427h);
            }
        }
        this.f420a = false;
    }

    public void c() {
        if (!f()) {
            Log.e("FlutterEngineCxnRegstry", "Attempted to detach plugins from an Activity when no Activity was attached.");
            return;
        }
        e0.a.b("FlutterEngineConnectionRegistry#detachFromActivity");
        try {
            Iterator it = ((HashMap) this.f422c).values().iterator();
            while (it.hasNext()) {
                ((W.a) it.next()).onDetachedFromActivity();
            }
            d();
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

    public void d() {
        c cVar = (c) this.f423d;
        io.flutter.plugin.platform.j jVar = cVar.f409r;
        x xVar = jVar.f899j;
        if (xVar != null) {
            xVar.f90e = null;
        }
        jVar.c();
        jVar.f899j = null;
        jVar.f894e = null;
        jVar.f897h = null;
        io.flutter.plugin.platform.i iVar = cVar.f410s;
        x xVar2 = iVar.f884h;
        if (xVar2 != null) {
            xVar2.f90e = null;
        }
        Surface surface = iVar.f889n;
        if (surface != null) {
            surface.release();
            iVar.f889n = null;
            iVar.f890o = null;
        }
        iVar.f884h = null;
        iVar.f881e = null;
        this.f426g = null;
        this.f427h = null;
    }

    public void e() {
        if (f()) {
            c();
        }
    }

    public boolean f() {
        return ((C0011i) this.f426g) != null;
    }

    public FileInputStream g(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e2) {
            String message = e2.getMessage();
            if (message == null || !message.contains("compressed")) {
                return null;
            }
            ((p014o.e) this.f422c).d();
            return null;
        }
    }

    public void h(final int i2, final Serializable serializable) {
        ((Executor) this.f421b).execute(new Runnable() { // from class: o.a
            @Override // java.lang.Runnable
            public final void run() {
                ((e) this.f1161d.f422c).h(i2, serializable);
            }
        });
    }

    public e(AssetManager assetManager, Executor executor, p014o.e eVar, String str, File file) {
        this.f420a = false;
        this.f421b = executor;
        this.f422c = eVar;
        this.f425f = str;
        this.f424e = file;
        int i2 = Build.VERSION.SDK_INT;
        byte[] bArr = null;
        if (i2 <= 34) {
            switch (i2) {
                case 24:
                case 25:
                    bArr = p014o.f.f1181h;
                    break;
                case 26:
                    bArr = p014o.f.f1180g;
                    break;
                case 27:
                    bArr = p014o.f.f1179f;
                    break;
                case 28:
                case 29:
                case 30:
                    bArr = p014o.f.f1178e;
                    break;
                case 31:
                case 32:
                case 33:
                case 34:
                    bArr = p014o.f.f1177d;
                    break;
            }
        }
        this.f423d = bArr;
    }
}
