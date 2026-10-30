package p021y;

import O.q;
import android.app.Activity;
import android.content.Context;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.extensions.layout.WindowLayoutInfo;
import f0.g;
import g0.m;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.concurrent.locks.ReentrantLock;
import p0.n;
import p018s.b;
import p019t.d;
import x.a;

/* JADX INFO: loaded from: classes.dex */
public final class c implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WindowLayoutComponent f1379a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f1380b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ReentrantLock f1381c = new ReentrantLock();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinkedHashMap f1382d = new LinkedHashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinkedHashMap f1383e = new LinkedHashMap();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final LinkedHashMap f1384f = new LinkedHashMap();

    public c(WindowLayoutComponent windowLayoutComponent, b bVar) {
        this.f1379a = windowLayoutComponent;
        this.f1380b = bVar;
    }

    @Override // x.a
    public final void a(q qVar) {
        LinkedHashMap linkedHashMap = this.f1382d;
        LinkedHashMap linkedHashMap2 = this.f1383e;
        ReentrantLock reentrantLock = this.f1381c;
        reentrantLock.lock();
        try {
            Context context = (Context) linkedHashMap2.get(qVar);
            if (context == null) {
                reentrantLock.unlock();
                return;
            }
            f fVar = (f) linkedHashMap.get(context);
            if (fVar == null) {
                reentrantLock.unlock();
                return;
            }
            LinkedHashSet linkedHashSet = fVar.f1392d;
            ReentrantLock reentrantLock2 = fVar.f1390b;
            reentrantLock2.lock();
            try {
                linkedHashSet.remove(qVar);
                reentrantLock2.unlock();
                linkedHashMap2.remove(qVar);
                if (linkedHashSet.isEmpty()) {
                    linkedHashMap.remove(context);
                    d dVar = (d) this.f1384f.remove(fVar);
                    if (dVar != null) {
                        dVar.f1244a.invoke(dVar.f1245b, dVar.f1246c);
                    }
                }
                reentrantLock.unlock();
            } catch (Throwable th) {
                reentrantLock2.unlock();
                throw th;
            }
        } catch (Throwable th2) {
            reentrantLock.unlock();
            throw th2;
        }
    }

    @Override // x.a
    public final void b(Context context, p014o.d dVar, q qVar) {
        g gVar;
        LinkedHashMap linkedHashMap = this.f1382d;
        ReentrantLock reentrantLock = this.f1381c;
        reentrantLock.lock();
        try {
            f fVar = (f) linkedHashMap.get(context);
            LinkedHashMap linkedHashMap2 = this.f1383e;
            if (fVar != null) {
                fVar.b(qVar);
                linkedHashMap2.put(qVar, context);
                gVar = g.f725a;
            } else {
                gVar = null;
            }
            if (gVar == null) {
                f fVar2 = new f(context);
                linkedHashMap.put(context, fVar2);
                linkedHashMap2.put(qVar, context);
                fVar2.b(qVar);
                if (!(context instanceof Activity)) {
                    fVar2.accept(new WindowLayoutInfo(m.f733d));
                } else {
                    this.f1384f.put(fVar2, this.f1380b.a(this.f1379a, n.a(WindowLayoutInfo.class), (Activity) context, new b(1, fVar2, f.class, "accept", "accept(Landroidx/window/extensions/layout/WindowLayoutInfo;)V", 0)));
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}
