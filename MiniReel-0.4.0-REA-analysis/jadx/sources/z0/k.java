package z0;

import C0.v;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import x0.c0;

/* JADX INFO: loaded from: classes.dex */
public final class k extends v {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c f1452e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AtomicReferenceArray f1453f;

    public k(long j2, k kVar, c cVar, int i2) {
        super(j2, kVar, i2);
        this.f1452e = cVar;
        this.f1453f = new AtomicReferenceArray(e.f1430b * 2);
    }

    @Override // C0.v
    public final int f() {
        return e.f1430b;
    }

    @Override // C0.v
    public final void g(int i2, h0.i iVar) {
        c cVar;
        int i3 = e.f1430b;
        boolean z2 = i2 >= i3;
        if (z2) {
            i2 -= i3;
        }
        this.f1453f.get(i2 * 2);
        while (true) {
            Object objK = k(i2);
            boolean z3 = objK instanceof c0;
            cVar = this.f1452e;
            if (z3 || (objK instanceof u)) {
                if (j(i2, objK, z2 ? e.f1438j : e.f1439k)) {
                    m(i2, null);
                    l(i2, !z2);
                    if (z2) {
                        p0.h.b(cVar);
                        return;
                    }
                    return;
                }
            } else {
                if (objK == e.f1438j || objK == e.f1439k) {
                    break;
                }
                if (objK != e.f1435g && objK != e.f1434f) {
                    if (objK == e.f1437i || objK == e.f1432d || objK == e.f1440l) {
                        return;
                    }
                    throw new IllegalStateException(("unexpected state: " + objK).toString());
                }
            }
        }
        m(i2, null);
        if (z2) {
            p0.h.b(cVar);
        }
    }

    public final boolean j(int i2, Object obj, Object obj2) {
        AtomicReferenceArray atomicReferenceArray;
        int i3 = (i2 * 2) + 1;
        do {
            atomicReferenceArray = this.f1453f;
            if (atomicReferenceArray.compareAndSet(i3, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceArray.get(i3) == obj);
        return false;
    }

    public final Object k(int i2) {
        return this.f1453f.get((i2 * 2) + 1);
    }

    public final void l(int i2, boolean z2) {
        long j2;
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j3;
        if (z2) {
            c cVar = this.f1452e;
            p0.h.b(cVar);
            long j4 = (this.f87c * ((long) e.f1430b)) + ((long) i2);
            AtomicLongFieldUpdater atomicLongFieldUpdater2 = c.f1422h;
            AtomicLongFieldUpdater atomicLongFieldUpdater3 = c.f1421g;
            if (!cVar.r()) {
                while (atomicLongFieldUpdater3.get(cVar) <= j4) {
                }
                int i3 = e.f1431c;
                for (int i4 = 0; i4 < i3; i4++) {
                    long j5 = atomicLongFieldUpdater3.get(cVar);
                    if (j5 != (4611686018427387903L & atomicLongFieldUpdater2.get(cVar)) || j5 != atomicLongFieldUpdater3.get(cVar)) {
                    }
                }
                do {
                    j2 = atomicLongFieldUpdater2.get(cVar);
                } while (!atomicLongFieldUpdater2.compareAndSet(cVar, j2, (j2 & 4611686018427387903L) + 4611686018427387904L));
                while (true) {
                    long j6 = atomicLongFieldUpdater3.get(cVar);
                    atomicLongFieldUpdater = c.f1422h;
                    long j7 = atomicLongFieldUpdater.get(cVar);
                    long j8 = j7 & 4611686018427387903L;
                    boolean z3 = (j7 & 4611686018427387904L) != 0;
                    if (j6 == j8 && j6 == atomicLongFieldUpdater3.get(cVar)) {
                        break;
                    } else if (!z3) {
                        atomicLongFieldUpdater.compareAndSet(cVar, j7, 4611686018427387904L + j8);
                    }
                }
                do {
                    j3 = atomicLongFieldUpdater.get(cVar);
                } while (!atomicLongFieldUpdater.compareAndSet(cVar, j3, j3 & 4611686018427387903L));
            }
        }
        h();
    }

    public final void m(int i2, Object obj) {
        this.f1453f.lazySet(i2 * 2, obj);
    }

    public final void n(int i2, Object obj) {
        this.f1453f.set((i2 * 2) + 1, obj);
    }
}
