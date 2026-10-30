package x0;

import C0.AbstractC0000a;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: renamed from: x0.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0023g extends L {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C0021e f1347h;

    public C0023g(C0021e c0021e) {
        this.f1347h = c0021e;
    }

    @Override // o0.l
    public final /* bridge */ /* synthetic */ Object h(Object obj) {
        o((Throwable) obj);
        return f0.g.f725a;
    }

    @Override // x0.N
    public final void o(Throwable th) {
        CancellationException cancellationExceptionT = n().t();
        C0021e c0021e = this.f1347h;
        if (c0021e.q()) {
            h0.d dVar = c0021e.f1344g;
            p0.h.c(dVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            C0.h hVar = (C0.h) dVar;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = C0.h.f59k;
            loop0: while (true) {
                Object obj = atomicReferenceFieldUpdater.get(hVar);
                C0.x xVar = AbstractC0000a.f49d;
                if (p0.h.a(obj, xVar)) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(hVar, xVar, cancellationExceptionT)) {
                        if (atomicReferenceFieldUpdater.get(hVar) != xVar) {
                        }
                    }
                    return;
                } else {
                    if (obj instanceof Throwable) {
                        return;
                    }
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(hVar, obj, null)) {
                            break loop0;
                        }
                    } while (atomicReferenceFieldUpdater.get(hVar) == obj);
                }
            }
        }
        c0021e.k(cancellationExceptionT);
        if (c0021e.q()) {
            return;
        }
        c0021e.l();
    }
}
