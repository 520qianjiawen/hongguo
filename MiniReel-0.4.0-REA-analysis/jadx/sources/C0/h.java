package C0;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import x0.AbstractC0028l;
import x0.AbstractC0032p;
import x0.AbstractC0034s;
import x0.AbstractC0038w;
import x0.C0027k;
import x0.Z;

/* JADX INFO: loaded from: classes.dex */
public final class h extends AbstractC0038w implements j0.c, h0.d {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f59k = AtomicReferenceFieldUpdater.newUpdater(h.class, Object.class, "_reusableCancellableContinuation");
    private volatile Object _reusableCancellableContinuation;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AbstractC0032p f60g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final j0.b f61h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f62i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f63j;

    public h(AbstractC0032p abstractC0032p, j0.b bVar) {
        super(-1);
        this.f60g = abstractC0032p;
        this.f61h = bVar;
        this.f62i = AbstractC0000a.f48c;
        h0.i iVar = bVar.f1072e;
        p0.h.b(iVar);
        this.f63j = AbstractC0000a.j(iVar);
    }

    @Override // x0.AbstractC0038w
    public final void b(Object obj, CancellationException cancellationException) {
        if (obj instanceof AbstractC0028l) {
            ((AbstractC0028l) obj).getClass();
            throw null;
        }
    }

    @Override // j0.c
    public final j0.c c() {
        j0.b bVar = this.f61h;
        if (bVar != null) {
            return bVar;
        }
        return null;
    }

    @Override // h0.d
    public final h0.i getContext() {
        h0.i iVar = this.f61h.f1072e;
        p0.h.b(iVar);
        return iVar;
    }

    @Override // x0.AbstractC0038w
    public final Object h() {
        Object obj = this.f62i;
        this.f62i = AbstractC0000a.f48c;
        return obj;
    }

    @Override // h0.d
    public final void resumeWith(Object obj) {
        j0.b bVar = this.f61h;
        h0.i iVar = bVar.f1072e;
        p0.h.b(iVar);
        Throwable thA = f0.d.a(obj);
        Object c0027k = thA == null ? obj : new C0027k(thA, false);
        AbstractC0032p abstractC0032p = this.f60g;
        if (abstractC0032p.d()) {
            this.f62i = c0027k;
            this.f1375f = 0;
            abstractC0032p.c(iVar, this);
            return;
        }
        x0.C cA = Z.a();
        if (cA.f1309f >= 4294967296L) {
            this.f62i = c0027k;
            this.f1375f = 0;
            g0.c cVar = cA.f1311h;
            if (cVar == null) {
                cVar = new g0.c();
                cA.f1311h = cVar;
            }
            cVar.addLast(this);
            return;
        }
        cA.k(true);
        try {
            h0.i iVar2 = bVar.f1072e;
            p0.h.b(iVar2);
            Object objK = AbstractC0000a.k(iVar2, this.f63j);
            try {
                bVar.resumeWith(obj);
                AbstractC0000a.f(iVar2, objK);
                while (cA.l()) {
                }
            } catch (Throwable th) {
                AbstractC0000a.f(iVar2, objK);
                throw th;
            }
        } catch (Throwable th2) {
            try {
                g(th2, null);
            } finally {
                cA.e();
            }
        }
    }

    public final String toString() {
        return "DispatchedContinuation[" + this.f60g + ", " + AbstractC0034s.g(this.f61h) + ']';
    }

    @Override // x0.AbstractC0038w
    public final h0.d d() {
        return this;
    }
}
