package z0;

import C0.w;
import C0.x;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import x0.AbstractC0034s;
import x0.C0021e;
import x0.c0;

/* JADX INFO: loaded from: classes.dex */
public final class a implements c0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f1416d = e.f1443p;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C0021e f1417e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ c f1418f;

    public a(c cVar) {
        this.f1418f = cVar;
    }

    @Override // x0.c0
    public final void a(k kVar, int i2) {
        C0021e c0021e = this.f1417e;
        if (c0021e != null) {
            c0021e.a(kVar, i2);
        }
    }

    public final Object b(A0.e eVar) throws Throwable {
        k kVarJ;
        Boolean bool;
        int i2;
        k kVarJ2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = c.f1424j;
        c cVar = this.f1418f;
        k kVar = (k) atomicReferenceFieldUpdater.get(cVar);
        while (!cVar.o(c.f1419e.get(cVar), true)) {
            long andIncrement = c.f1420f.getAndIncrement(cVar);
            long j2 = e.f1430b;
            long j3 = andIncrement / j2;
            int i3 = (int) (andIncrement % j2);
            if (kVar.f87c != j3) {
                kVarJ = cVar.j(j3, kVar);
                if (kVarJ == null) {
                    continue;
                }
            } else {
                kVarJ = kVar;
            }
            Object objW = cVar.w(kVarJ, i3, andIncrement, null);
            x xVar = e.m;
            if (objW == xVar) {
                throw new IllegalStateException("unreachable");
            }
            x xVar2 = e.f1442o;
            if (objW == xVar2) {
                if (andIncrement < cVar.m()) {
                    kVarJ.a();
                }
                kVar = kVarJ;
            } else {
                if (objW != e.f1441n) {
                    kVarJ.a();
                    this.f1416d = objW;
                    return Boolean.TRUE;
                }
                C0021e c0021eB = AbstractC0034s.b(p000a.a.y(eVar));
                try {
                    this.f1417e = c0021eB;
                    try {
                        Object objW2 = cVar.w(kVarJ, i3, andIncrement, this);
                        if (objW2 != xVar) {
                            if (objW2 == xVar2) {
                                if (andIncrement < cVar.m()) {
                                    kVarJ.a();
                                }
                                k kVar2 = (k) c.f1424j.get(cVar);
                                while (true) {
                                    if (cVar.o(c.f1419e.get(cVar), true)) {
                                        C0021e c0021e = this.f1417e;
                                        p0.h.b(c0021e);
                                        this.f1417e = null;
                                        this.f1416d = e.f1440l;
                                        Throwable thK = cVar.k();
                                        if (thK != null) {
                                            c0021e.resumeWith(p000a.a.k(thK));
                                            break;
                                        }
                                        c0021e.resumeWith(Boolean.FALSE);
                                        break;
                                    }
                                    long andIncrement2 = c.f1420f.getAndIncrement(cVar);
                                    long j4 = e.f1430b;
                                    long j5 = andIncrement2 / j4;
                                    int i4 = (int) (andIncrement2 % j4);
                                    if (kVar2.f87c != j5) {
                                        kVarJ2 = cVar.j(j5, kVar2);
                                        if (kVarJ2 == null) {
                                        }
                                    } else {
                                        kVarJ2 = kVar2;
                                    }
                                    Object objW3 = cVar.w(kVarJ2, i4, andIncrement2, this);
                                    if (objW3 == e.m) {
                                        a(kVarJ2, i4);
                                        break;
                                    }
                                    if (objW3 == e.f1442o) {
                                        if (andIncrement2 < cVar.m()) {
                                            kVarJ2.a();
                                        }
                                        kVar2 = kVarJ2;
                                    } else {
                                        if (objW3 == e.f1441n) {
                                            throw new IllegalStateException("unexpected");
                                        }
                                        kVarJ2.a();
                                        this.f1416d = objW3;
                                        this.f1417e = null;
                                        bool = Boolean.TRUE;
                                        i2 = c0021eB.f1375f;
                                    }
                                }
                            } else {
                                kVarJ.a();
                                this.f1416d = objW2;
                                this.f1417e = null;
                                bool = Boolean.TRUE;
                                i2 = c0021eB.f1375f;
                            }
                            c0021eB.t(bool, i2, null);
                            break;
                        }
                        a(kVarJ, i3);
                        return c0021eB.n();
                    } catch (Throwable th) {
                        th = th;
                        c0021eB.s();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
        this.f1416d = e.f1440l;
        Throwable thK2 = cVar.k();
        if (thK2 == null) {
            return Boolean.FALSE;
        }
        int i5 = w.f88a;
        throw thK2;
    }
}
