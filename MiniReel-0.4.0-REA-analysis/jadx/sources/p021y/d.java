package p021y;

import O.q;
import android.content.Context;
import androidx.window.extensions.layout.WindowLayoutComponent;
import f0.g;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.concurrent.locks.ReentrantLock;
import x.a;

/* JADX INFO: loaded from: classes.dex */
public final class d implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WindowLayoutComponent f1385a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ReentrantLock f1386b = new ReentrantLock();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinkedHashMap f1387c = new LinkedHashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinkedHashMap f1388d = new LinkedHashMap();

    public d(WindowLayoutComponent windowLayoutComponent) {
        this.f1385a = windowLayoutComponent;
    }

    @Override // x.a
    public final void a(q qVar) {
        LinkedHashMap linkedHashMap = this.f1387c;
        LinkedHashMap linkedHashMap2 = this.f1388d;
        ReentrantLock reentrantLock = this.f1386b;
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
                    this.f1385a.removeWindowLayoutInfoListener(fVar);
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
        LinkedHashMap linkedHashMap = this.f1387c;
        ReentrantLock reentrantLock = this.f1386b;
        reentrantLock.lock();
        try {
            f fVar = (f) linkedHashMap.get(context);
            LinkedHashMap linkedHashMap2 = this.f1388d;
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
                this.f1385a.addWindowLayoutInfoListener(context, fVar2);
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}
