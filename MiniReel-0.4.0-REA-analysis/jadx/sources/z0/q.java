package z0;

import C0.x;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import x0.AbstractC0017a;
import x0.AbstractC0034s;
import x0.C0027k;
import x0.K;
import x0.P;

/* JADX INFO: loaded from: classes.dex */
public final class q extends AbstractC0017a implements r, g {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final c f1459g;

    public q(h0.i iVar, c cVar) {
        super(iVar, true);
        this.f1459g = cVar;
    }

    @Override // x0.AbstractC0017a
    public final void I(Throwable th, boolean z2) {
        if (this.f1459g.f(th, false) || z2) {
            return;
        }
        AbstractC0034s.c(this.f1336f, th);
    }

    @Override // x0.AbstractC0017a
    public final void J(Object obj) {
        this.f1459g.f(null, false);
    }

    public final void L(p pVar) {
        c cVar = this.f1459g;
        cVar.getClass();
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = c.m;
        while (!atomicReferenceFieldUpdater.compareAndSet(cVar, null, pVar)) {
            if (atomicReferenceFieldUpdater.get(cVar) != null) {
                while (true) {
                    Object obj = atomicReferenceFieldUpdater.get(cVar);
                    x xVar = e.f1444q;
                    if (obj != xVar) {
                        if (obj == e.f1445r) {
                            throw new IllegalStateException("Another handler was already registered and successfully invoked");
                        }
                        throw new IllegalStateException(("Another handler is already registered: " + obj).toString());
                    }
                    x xVar2 = e.f1445r;
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(cVar, xVar, xVar2)) {
                            pVar.h(cVar.k());
                            return;
                        }
                    } while (atomicReferenceFieldUpdater.get(cVar) == xVar);
                }
            }
        }
    }

    @Override // x0.S, x0.J
    public final void a(CancellationException cancellationException) {
        Object objW = w();
        if (objW instanceof C0027k) {
            return;
        }
        if ((objW instanceof P) && ((P) objW).d()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new K(p(), null, this);
        }
        n(cancellationException);
    }

    @Override // z0.t
    public final Object d(Object obj) {
        return this.f1459g.d(obj);
    }

    @Override // x0.S
    public final void n(CancellationException cancellationException) {
        this.f1459g.f(cancellationException, true);
        m(cancellationException);
    }
}
