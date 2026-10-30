package io.flutter.embedding.engine.renderer;

import O.C0008f;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.os.Handler;
import android.view.Surface;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.view.TextureRegistry$SurfaceProducer;
import io.flutter.view.x;
import io.flutter.view.y;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final FlutterJNI f793a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Surface f795c;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C0008f f800h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicLong f794b = new AtomicLong(0);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f796d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Handler f797e = new Handler();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final HashSet f798f = new HashSet();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f799g = new ArrayList();

    public j(FlutterJNI flutterJNI) {
        C0008f c0008f = new C0008f(3, this);
        this.f800h = c0008f;
        this.f793a = flutterJNI;
        flutterJNI.addIsDisplayingFlutterUiListener(c0008f);
    }

    public final void a(k kVar) {
        this.f793a.addIsDisplayingFlutterUiListener(kVar);
        if (this.f796d) {
            kVar.b();
        }
    }

    public final void b(x xVar) {
        HashSet hashSet = this.f798f;
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            if (((x) ((WeakReference) it.next()).get()) == null) {
                it.remove();
            }
        }
        hashSet.add(new WeakReference(xVar));
    }

    public final TextureRegistry$SurfaceProducer c(int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 29 || (i3 <= 29 && "HUAWEI".equalsIgnoreCase(Build.MANUFACTURER))) {
            SurfaceTexture surfaceTexture = new SurfaceTexture(0);
            long andIncrement = this.f794b.getAndIncrement();
            surfaceTexture.detachFromGLContext();
            g gVar = new g(this, andIncrement, surfaceTexture);
            this.f793a.registerTexture(gVar.f762a, gVar.f763b);
            b(gVar);
            return new n(gVar.f762a, this.f797e, this.f793a, gVar);
        }
        long andIncrement2 = this.f794b.getAndIncrement();
        FlutterRenderer$ImageReaderSurfaceProducer flutterRenderer$ImageReaderSurfaceProducer = new FlutterRenderer$ImageReaderSurfaceProducer(this, andIncrement2);
        boolean z2 = i2 == 2;
        this.f793a.registerImageTexture(andIncrement2, flutterRenderer$ImageReaderSurfaceProducer, z2);
        if (z2) {
            b(flutterRenderer$ImageReaderSurfaceProducer);
        }
        this.f799g.add(flutterRenderer$ImageReaderSurfaceProducer);
        return flutterRenderer$ImageReaderSurfaceProducer;
    }

    public final void d(int i2) {
        Iterator it = this.f798f.iterator();
        while (it.hasNext()) {
            x xVar = (x) ((WeakReference) it.next()).get();
            if (xVar != null) {
                xVar.onTrimMemory(i2);
            } else {
                it.remove();
            }
        }
    }

    public final void e(k kVar) {
        this.f793a.removeIsDisplayingFlutterUiListener(kVar);
    }

    public final void f(x xVar) {
        HashSet<WeakReference> hashSet = this.f798f;
        for (WeakReference weakReference : hashSet) {
            if (weakReference.get() == xVar) {
                hashSet.remove(weakReference);
                return;
            }
        }
    }

    public final void g() {
        ArrayList arrayList = this.f799g;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            FlutterRenderer$ImageReaderSurfaceProducer flutterRenderer$ImageReaderSurfaceProducer = (FlutterRenderer$ImageReaderSurfaceProducer) obj;
            y yVar = flutterRenderer$ImageReaderSurfaceProducer.callback;
            if (yVar != null && flutterRenderer$ImageReaderSurfaceProducer.notifiedDestroy) {
                flutterRenderer$ImageReaderSurfaceProducer.notifiedDestroy = false;
                ((G.e) yVar).b();
            }
        }
    }

    public final void h() {
        if (this.f795c != null) {
            this.f793a.onSurfaceDestroyed();
            if (this.f796d) {
                this.f800h.a();
            }
            this.f796d = false;
            this.f795c = null;
        }
    }
}
