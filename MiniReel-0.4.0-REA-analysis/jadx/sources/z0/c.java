package z0;

import C0.AbstractC0000a;
import C0.v;
import C0.x;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import x0.C0021e;
import x0.InterfaceC0020d;
import x0.c0;

/* JADX INFO: loaded from: classes.dex */
public class c implements g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f1419e = AtomicLongFieldUpdater.newUpdater(c.class, "sendersAndCloseStatus");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f1420f = AtomicLongFieldUpdater.newUpdater(c.class, "receivers");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f1421g = AtomicLongFieldUpdater.newUpdater(c.class, "bufferEnd");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f1422h = AtomicLongFieldUpdater.newUpdater(c.class, "completedExpandBuffersAndPauseFlag");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f1423i = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "sendSegment");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f1424j = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "receiveSegment");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f1425k = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "bufferEndSegment");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f1426l = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "_closeCause");
    public static final AtomicReferenceFieldUpdater m = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "closeHandler");
    private volatile Object _closeCause;
    private volatile long bufferEnd;
    private volatile Object bufferEndSegment;
    private volatile Object closeHandler;
    private volatile long completedExpandBuffersAndPauseFlag;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1427d;
    private volatile Object receiveSegment;
    private volatile long receivers;
    private volatile Object sendSegment;
    private volatile long sendersAndCloseStatus;

    public c(int i2) {
        this.f1427d = i2;
        if (i2 < 0) {
            throw new IllegalArgumentException(("Invalid channel capacity: " + i2 + ", should be >=0").toString());
        }
        k kVar = e.f1429a;
        this.bufferEnd = i2 != 0 ? i2 != Integer.MAX_VALUE ? i2 : Long.MAX_VALUE : 0L;
        this.completedExpandBuffersAndPauseFlag = f1421g.get(this);
        k kVar2 = new k(0L, null, this, 3);
        this.sendSegment = kVar2;
        this.receiveSegment = kVar2;
        if (r()) {
            kVar2 = e.f1429a;
            p0.h.c(kVar2, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
        }
        this.bufferEndSegment = kVar2;
        this._closeCause = e.f1446s;
    }

    public static final k b(c cVar, long j2, k kVar) {
        Object objA;
        c cVar2;
        k kVar2 = e.f1429a;
        d dVar = d.f1428l;
        loop0: while (true) {
            objA = AbstractC0000a.a(j2, kVar);
            if (!AbstractC0000a.d(objA)) {
                v vVarB = AbstractC0000a.b(objA);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1423i;
                    v vVar = (v) atomicReferenceFieldUpdater.get(cVar);
                    if (vVar.f87c >= vVarB.f87c) {
                        break loop0;
                    }
                    if (!vVarB.i()) {
                        break;
                    }
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(cVar, vVar, vVarB)) {
                            if (!vVar.e()) {
                                break loop0;
                            }
                            vVar.d();
                            break loop0;
                        }
                    } while (atomicReferenceFieldUpdater.get(cVar) == vVar);
                    if (vVarB.e()) {
                        vVarB.d();
                    }
                }
            } else {
                break;
            }
        }
        boolean zD = AbstractC0000a.d(objA);
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1420f;
        if (zD) {
            cVar.p();
            if (kVar.f87c * ((long) e.f1430b) < atomicLongFieldUpdater.get(cVar)) {
                kVar.a();
                return null;
            }
        } else {
            k kVar3 = (k) AbstractC0000a.b(objA);
            long j3 = kVar3.f87c;
            if (j3 <= j2) {
                return kVar3;
            }
            long j4 = ((long) e.f1430b) * j3;
            while (true) {
                long j5 = f1419e.get(cVar);
                long j6 = 1152921504606846975L & j5;
                if (j6 >= j4) {
                    cVar2 = cVar;
                    break;
                }
                cVar2 = cVar;
                if (f1419e.compareAndSet(cVar2, j5, (((long) ((int) (j5 >> 60))) << 60) + j6)) {
                    break;
                }
                cVar = cVar2;
            }
            if (j3 * ((long) e.f1430b) < atomicLongFieldUpdater.get(cVar2)) {
                kVar3.a();
            }
        }
        return null;
    }

    public static final int c(c cVar, k kVar, int i2, Object obj, long j2, Object obj2, boolean z2) {
        kVar.m(i2, obj);
        if (z2) {
            return cVar.x(kVar, i2, obj, j2, obj2, z2);
        }
        Object objK = kVar.k(i2);
        if (objK == null) {
            if (cVar.e(j2)) {
                if (kVar.j(i2, null, e.f1432d)) {
                    return 1;
                }
            } else {
                if (obj2 == null) {
                    return 3;
                }
                if (kVar.j(i2, null, obj2)) {
                    return 2;
                }
            }
        } else if (objK instanceof c0) {
            kVar.m(i2, null);
            if (cVar.u(objK, obj)) {
                kVar.n(i2, e.f1437i);
                return 0;
            }
            x xVar = e.f1439k;
            if (kVar.f1453f.getAndSet((i2 * 2) + 1, xVar) == xVar) {
                return 5;
            }
            kVar.l(i2, true);
            return 5;
        }
        return cVar.x(kVar, i2, obj, j2, obj2, z2);
    }

    public static void n(c cVar) {
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1422h;
        if ((atomicLongFieldUpdater.addAndGet(cVar, 1L) & 4611686018427387904L) != 0) {
            while ((atomicLongFieldUpdater.get(cVar) & 4611686018427387904L) != 0) {
            }
        }
    }

    public static boolean v(Object obj) {
        if (obj instanceof InterfaceC0020d) {
            p0.h.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            return e.a((InterfaceC0020d) obj, f0.g.f725a, null);
        }
        throw new IllegalStateException(("Unexpected waiter: " + obj).toString());
    }

    @Override // z0.s
    public final void a(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new CancellationException("Channel was cancelled");
        }
        f(cancellationException, true);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0068  */
    /* JADX WARN: Code duplicated, block: B:24:0x006b  */
    /* JADX WARN: Code duplicated, block: B:26:0x006e  */
    /* JADX WARN: Code duplicated, block: B:28:0x0071  */
    /* JADX WARN: Code duplicated, block: B:30:0x0074  */
    /* JADX WARN: Code duplicated, block: B:33:0x0078  */
    /* JADX WARN: Code duplicated, block: B:37:0x0087  */
    /* JADX WARN: Code duplicated, block: B:43:0x009e  */
    /* JADX WARN: Code duplicated, block: B:45:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:47:0x00af  */
    /* JADX WARN: Code duplicated, block: B:48:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:50:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:57:0x00be A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:58:0x00bd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x009c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:60:0x0094 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:61:0x007d A[SYNTHETIC] */
    @Override // z0.t
    public Object d(Object obj) {
        int iC;
        f0.g gVar;
        c0 c0Var;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1419e;
        long j2 = atomicLongFieldUpdater.get(this);
        boolean z2 = false;
        long j3 = 1152921504606846975L;
        boolean z3 = o(j2, false) ? false : !e(j2 & 1152921504606846975L);
        i iVar = j.f1451a;
        if (z3) {
            return iVar;
        }
        K.c cVar = e.f1438j;
        k kVar = (k) f1423i.get(this);
        while (true) {
            long andIncrement = atomicLongFieldUpdater.getAndIncrement(this);
            long j4 = andIncrement & j3;
            boolean zO = o(andIncrement, z2);
            int i2 = e.f1430b;
            long j5 = i2;
            long j6 = j4 / j5;
            int i3 = (int) (j4 % j5);
            if (kVar.f87c == j6) {
                iC = c(this, kVar, i3, obj, j4, cVar, zO);
                gVar = f0.g.f725a;
                if (iC != 0) {
                    kVar.a();
                    return gVar;
                }
                if (iC != 1) {
                    return gVar;
                }
                if (iC != 2) {
                    if (zO) {
                        kVar.h();
                        return new h(l());
                    }
                    if (cVar instanceof c0) {
                        c0Var = (c0) cVar;
                    } else {
                        c0Var = null;
                    }
                    if (c0Var != null) {
                        c0Var.a(kVar, i3 + i2);
                    }
                    kVar.h();
                    return iVar;
                }
                if (iC != 3) {
                    throw new IllegalStateException("unexpected");
                }
                if (iC != 4) {
                    if (j4 < f1420f.get(this)) {
                        kVar.a();
                    }
                    return new h(l());
                }
                if (iC == 5) {
                    kVar.a();
                }
                z2 = false;
            } else {
                k kVarB = b(this, j6, kVar);
                if (kVarB != null) {
                    kVar = kVarB;
                    iC = c(this, kVar, i3, obj, j4, cVar, zO);
                    gVar = f0.g.f725a;
                    if (iC != 0) {
                        kVar.a();
                        return gVar;
                    }
                    if (iC != 1) {
                        return gVar;
                    }
                    if (iC != 2) {
                        if (zO) {
                            kVar.h();
                            return new h(l());
                        }
                        if (cVar instanceof c0) {
                            c0Var = (c0) cVar;
                        } else {
                            c0Var = null;
                        }
                        if (c0Var != null) {
                            c0Var.a(kVar, i3 + i2);
                        }
                        kVar.h();
                        return iVar;
                    }
                    if (iC != 3) {
                        throw new IllegalStateException("unexpected");
                    }
                    if (iC != 4) {
                        if (j4 < f1420f.get(this)) {
                            kVar.a();
                        }
                        return new h(l());
                    }
                    if (iC == 5) {
                        kVar.a();
                    }
                    z2 = false;
                } else {
                    if (zO) {
                        return new h(l());
                    }
                    z2 = false;
                }
            }
            j3 = 1152921504606846975L;
        }
    }

    public final boolean e(long j2) {
        return j2 < f1421g.get(this) || j2 < f1420f.get(this) + ((long) this.f1427d);
    }

    public final boolean f(Throwable th, boolean z2) {
        c cVar;
        boolean z3;
        long j2;
        long j3;
        long j4;
        Object obj;
        long j5;
        long j6;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1419e;
        if (!z2) {
            cVar = this;
            break;
        }
        do {
            j6 = atomicLongFieldUpdater.get(this);
            if (((int) (j6 >> 60)) != 0) {
                cVar = this;
                break;
            }
            k kVar = e.f1429a;
            cVar = this;
        } while (!atomicLongFieldUpdater.compareAndSet(cVar, j6, (j6 & 1152921504606846975L) + (((long) 1) << 60)));
        x xVar = e.f1446s;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1426l;
            if (atomicReferenceFieldUpdater.compareAndSet(this, xVar, th)) {
                z3 = true;
                break;
            }
            if (atomicReferenceFieldUpdater.get(this) != xVar) {
                z3 = false;
                break;
            }
        }
        if (z2) {
            do {
                j5 = atomicLongFieldUpdater.get(this);
            } while (!atomicLongFieldUpdater.compareAndSet(cVar, j5, (((long) 3) << 60) + (j5 & 1152921504606846975L)));
        } else {
            do {
                j2 = atomicLongFieldUpdater.get(this);
                int i2 = (int) (j2 >> 60);
                if (i2 == 0) {
                    j3 = j2 & 1152921504606846975L;
                    j4 = 2;
                } else {
                    if (i2 != 1) {
                        break;
                    }
                    j3 = j2 & 1152921504606846975L;
                    j4 = 3;
                }
            } while (!atomicLongFieldUpdater.compareAndSet(cVar, j2, (j4 << 60) + j3));
        }
        p();
        if (z3) {
            loop3: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = m;
                obj = atomicReferenceFieldUpdater2.get(this);
                x xVar2 = obj == null ? e.f1444q : e.f1445r;
                do {
                    if (atomicReferenceFieldUpdater2.compareAndSet(this, obj, xVar2)) {
                        break loop3;
                    }
                } while (atomicReferenceFieldUpdater2.get(this) == obj);
            }
            if (obj != null) {
                p0.p.a(1, obj);
                ((o0.l) obj).h(k());
                return z3;
            }
        }
        return z3;
    }

    public final k g(long j2) {
        Object objE;
        long j3;
        Object obj = f1425k.get(this);
        k kVar = (k) f1423i.get(this);
        if (kVar.f87c > ((k) obj).f87c) {
            obj = kVar;
        }
        k kVar2 = (k) f1424j.get(this);
        if (kVar2.f87c > ((k) obj).f87c) {
            obj = kVar2;
        }
        C0.d dVar = (C0.d) obj;
        loop0: while (true) {
            dVar.getClass();
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = C0.d.f54a;
            Object obj2 = atomicReferenceFieldUpdater.get(dVar);
            x xVar = AbstractC0000a.f47b;
            objE = null;
            if (obj2 == xVar) {
                break;
            }
            C0.d dVar2 = (C0.d) obj2;
            if (dVar2 == null) {
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(dVar, null, xVar)) {
                        break loop0;
                    }
                } while (atomicReferenceFieldUpdater.get(dVar) == null);
            } else {
                dVar = dVar2;
            }
        }
        k kVar3 = (k) dVar;
        if (q()) {
            k kVar4 = kVar3;
            loop2: while (true) {
                int i2 = e.f1430b - 1;
                while (true) {
                    if (-1 < i2) {
                        j3 = (kVar4.f87c * ((long) e.f1430b)) + ((long) i2);
                        if (j3 >= f1420f.get(this)) {
                            while (true) {
                                Object objK = kVar4.k(i2);
                                if (objK != null && objK != e.f1433e) {
                                    if (objK != e.f1432d) {
                                        break;
                                    }
                                    break loop2;
                                }
                                if (kVar4.j(i2, objK, e.f1440l)) {
                                    kVar4.h();
                                    break;
                                }
                            }
                            i2--;
                        }
                    } else {
                        kVar4 = (k) ((C0.d) C0.d.f55b.get(kVar4));
                        if (kVar4 == null) {
                        }
                    }
                    j3 = -1;
                    break;
                }
            }
            if (j3 != -1) {
                h(j3);
            }
        }
        loop5: for (k kVar5 = kVar3; kVar5 != null; kVar5 = (k) ((C0.d) C0.d.f55b.get(kVar5))) {
            for (int i3 = e.f1430b - 1; -1 < i3; i3--) {
                if ((kVar5.f87c * ((long) e.f1430b)) + ((long) i3) < j2) {
                    break loop5;
                }
                while (true) {
                    Object objK2 = kVar5.k(i3);
                    if (objK2 != null && objK2 != e.f1433e) {
                        if (!(objK2 instanceof u)) {
                            if (!(objK2 instanceof c0)) {
                                break;
                            }
                            if (kVar5.j(i3, objK2, e.f1440l)) {
                                objE = AbstractC0000a.e(objE, objK2);
                                kVar5.l(i3, true);
                                break;
                            }
                        } else {
                            if (kVar5.j(i3, objK2, e.f1440l)) {
                                objE = AbstractC0000a.e(objE, ((u) objK2).f1460a);
                                kVar5.l(i3, true);
                                break;
                            }
                        }
                    } else {
                        if (kVar5.j(i3, objK2, e.f1440l)) {
                            kVar5.h();
                            break;
                        }
                    }
                }
            }
        }
        if (objE != null) {
            if (!(objE instanceof ArrayList)) {
                t((c0) objE, true);
                return kVar3;
            }
            ArrayList arrayList = (ArrayList) objE;
            for (int size = arrayList.size() - 1; -1 < size; size--) {
                t((c0) arrayList.get(size), true);
            }
        }
        return kVar3;
    }

    public final void h(long j2) {
        k kVar = (k) f1424j.get(this);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f1420f;
            long j3 = atomicLongFieldUpdater.get(this);
            if (j2 < Math.max(((long) this.f1427d) + j3, f1421g.get(this))) {
                return;
            }
            if (atomicLongFieldUpdater.compareAndSet(this, j3, 1 + j3)) {
                long j4 = e.f1430b;
                long j5 = j3 / j4;
                int i2 = (int) (j3 % j4);
                if (kVar.f87c != j5) {
                    k kVarJ = j(j5, kVar);
                    if (kVarJ != null) {
                        kVar = kVarJ;
                    }
                }
                k kVar2 = kVar;
                if (w(kVar2, i2, j3, null) != e.f1442o || j3 < m()) {
                    kVar2.a();
                }
                kVar = kVar2;
            }
        }
    }

    public final void i() {
        Object objA;
        if (r()) {
            return;
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1425k;
        k kVar = (k) atomicReferenceFieldUpdater.get(this);
        while (true) {
            long andIncrement = f1421g.getAndIncrement(this);
            long j2 = andIncrement / ((long) e.f1430b);
            if (m() <= andIncrement) {
                if (kVar.f87c < j2 && kVar.b() != null) {
                    s(j2, kVar);
                }
                n(this);
                return;
            }
            if (kVar.f87c != j2) {
                d dVar = d.f1428l;
                while (true) {
                    objA = AbstractC0000a.a(j2, kVar);
                    if (!AbstractC0000a.d(objA)) {
                        v vVarB = AbstractC0000a.b(objA);
                        while (true) {
                            v vVar = (v) atomicReferenceFieldUpdater.get(this);
                            if (vVar.f87c >= vVarB.f87c) {
                                break;
                            }
                            if (!vVarB.i()) {
                                break;
                            }
                            do {
                                if (atomicReferenceFieldUpdater.compareAndSet(this, vVar, vVarB)) {
                                    if (!vVar.e()) {
                                        break;
                                    }
                                    vVar.d();
                                    break;
                                }
                            } while (atomicReferenceFieldUpdater.get(this) == vVar);
                            if (vVarB.e()) {
                                vVarB.d();
                            }
                        }
                    } else {
                        break;
                    }
                }
                k kVar2 = null;
                if (AbstractC0000a.d(objA)) {
                    p();
                    s(j2, kVar);
                    n(this);
                } else {
                    k kVar3 = (k) AbstractC0000a.b(objA);
                    long j3 = kVar3.f87c;
                    if (j3 > j2) {
                        long j4 = j3 * ((long) e.f1430b);
                        if (f1421g.compareAndSet(this, 1 + andIncrement, j4)) {
                            AtomicLongFieldUpdater atomicLongFieldUpdater = f1422h;
                            if ((atomicLongFieldUpdater.addAndGet(this, j4 - andIncrement) & 4611686018427387904L) != 0) {
                                while ((atomicLongFieldUpdater.get(this) & 4611686018427387904L) != 0) {
                                }
                            }
                        } else {
                            n(this);
                        }
                    } else {
                        kVar2 = kVar3;
                    }
                }
                if (kVar2 == null) {
                    continue;
                } else {
                    kVar = kVar2;
                }
            }
            int i2 = (int) (andIncrement % ((long) e.f1430b));
            Object objK = kVar.k(i2);
            boolean z2 = objK instanceof c0;
            AtomicLongFieldUpdater atomicLongFieldUpdater2 = f1420f;
            if (!z2 || andIncrement < atomicLongFieldUpdater2.get(this) || !kVar.j(i2, objK, e.f1435g)) {
                while (true) {
                    Object objK2 = kVar.k(i2);
                    if (objK2 instanceof c0) {
                        if (andIncrement < atomicLongFieldUpdater2.get(this)) {
                            if (kVar.j(i2, objK2, new u((c0) objK2))) {
                                n(this);
                                return;
                            }
                        } else if (kVar.j(i2, objK2, e.f1435g)) {
                            if (!v(objK2)) {
                                kVar.n(i2, e.f1438j);
                                kVar.h();
                                break;
                            } else {
                                kVar.n(i2, e.f1432d);
                                n(this);
                                return;
                            }
                        }
                    } else {
                        if (objK2 == e.f1438j) {
                            break;
                        }
                        if (objK2 == null) {
                            if (kVar.j(i2, objK2, e.f1433e)) {
                                n(this);
                                return;
                            }
                        } else if (objK2 == e.f1432d || objK2 == e.f1436h || objK2 == e.f1437i || objK2 == e.f1439k || objK2 == e.f1440l) {
                            n(this);
                            return;
                        } else if (objK2 != e.f1434f) {
                            throw new IllegalStateException(("Unexpected cell state: " + objK2).toString());
                        }
                    }
                }
                n(this);
            } else if (v(objK)) {
                kVar.n(i2, e.f1432d);
                n(this);
                return;
            } else {
                kVar.n(i2, e.f1438j);
                kVar.h();
                n(this);
            }
        }
    }

    public final k j(long j2, k kVar) {
        Object objA;
        long j3;
        k kVar2 = e.f1429a;
        d dVar = d.f1428l;
        loop0: while (true) {
            objA = AbstractC0000a.a(j2, kVar);
            if (!AbstractC0000a.d(objA)) {
                v vVarB = AbstractC0000a.b(objA);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1424j;
                    v vVar = (v) atomicReferenceFieldUpdater.get(this);
                    if (vVar.f87c >= vVarB.f87c) {
                        break loop0;
                    }
                    if (!vVarB.i()) {
                        break;
                    }
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(this, vVar, vVarB)) {
                            if (!vVar.e()) {
                                break loop0;
                            }
                            vVar.d();
                            break loop0;
                        }
                    } while (atomicReferenceFieldUpdater.get(this) == vVar);
                    if (vVarB.e()) {
                        vVarB.d();
                    }
                }
            } else {
                break;
            }
        }
        if (AbstractC0000a.d(objA)) {
            p();
            if (kVar.f87c * ((long) e.f1430b) < m()) {
                kVar.a();
                return null;
            }
        } else {
            k kVar3 = (k) AbstractC0000a.b(objA);
            long j4 = kVar3.f87c;
            if (!r() && j2 <= f1421g.get(this) / ((long) e.f1430b)) {
                loop3: while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f1425k;
                    v vVar2 = (v) atomicReferenceFieldUpdater2.get(this);
                    if (vVar2.f87c >= j4 || !kVar3.i()) {
                        break;
                    }
                    do {
                        if (atomicReferenceFieldUpdater2.compareAndSet(this, vVar2, kVar3)) {
                            if (!vVar2.e()) {
                                break loop3;
                            }
                            vVar2.d();
                            break loop3;
                        }
                    } while (atomicReferenceFieldUpdater2.get(this) == vVar2);
                    if (kVar3.e()) {
                        kVar3.d();
                    }
                }
            }
            if (j4 <= j2) {
                return kVar3;
            }
            long j5 = j4 * ((long) e.f1430b);
            do {
                j3 = f1420f.get(this);
                if (j3 >= j5) {
                    break;
                }
            } while (!f1420f.compareAndSet(this, j3, j5));
            if (j4 * ((long) e.f1430b) < m()) {
                kVar3.a();
            }
        }
        return null;
    }

    public final Throwable k() {
        return (Throwable) f1426l.get(this);
    }

    public final Throwable l() {
        Throwable thK = k();
        return thK == null ? new m("Channel was closed") : thK;
    }

    public final long m() {
        return f1419e.get(this) & 1152921504606846975L;
    }

    public final boolean o(long j2, boolean z2) {
        int i2 = (int) (j2 >> 60);
        if (i2 != 0 && i2 != 1) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f1420f;
            if (i2 == 2) {
                g(1152921504606846975L & j2);
                if (z2) {
                    while (true) {
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1424j;
                        k kVarJ = (k) atomicReferenceFieldUpdater.get(this);
                        long j3 = atomicLongFieldUpdater.get(this);
                        if (m() <= j3) {
                            break;
                        }
                        long j4 = e.f1430b;
                        long j5 = j3 / j4;
                        if (kVarJ.f87c != j5 && (kVarJ = j(j5, kVarJ)) == null) {
                            if (((k) atomicReferenceFieldUpdater.get(this)).f87c < j5) {
                                break;
                            }
                        } else {
                            kVarJ.a();
                            int i3 = (int) (j3 % j4);
                            while (true) {
                                Object objK = kVarJ.k(i3);
                                if (objK != null && objK != e.f1433e) {
                                    if (objK != e.f1432d && (objK == e.f1438j || objK == e.f1440l || objK == e.f1437i || objK == e.f1436h || (objK != e.f1435g && (objK == e.f1434f || j3 != atomicLongFieldUpdater.get(this))))) {
                                        break;
                                        break;
                                        break;
                                        break;
                                        break;
                                        break;
                                    }
                                } else if (kVarJ.j(i3, objK, e.f1436h)) {
                                    i();
                                    break;
                                }
                            }
                            f1420f.compareAndSet(this, j3, j3 + 1);
                        }
                    }
                }
            } else {
                if (i2 != 3) {
                    throw new IllegalStateException(("unexpected close status: " + i2).toString());
                }
                k kVarG = g(1152921504606846975L & j2);
                Object objE = null;
                loop0: do {
                    for (int i4 = e.f1430b - 1; -1 < i4; i4--) {
                        long j6 = (kVarG.f87c * ((long) e.f1430b)) + ((long) i4);
                        while (true) {
                            Object objK2 = kVarG.k(i4);
                            if (objK2 == e.f1437i) {
                                break loop0;
                            }
                            if (objK2 != e.f1432d) {
                                if (objK2 != e.f1433e && objK2 != null) {
                                    if (!(objK2 instanceof c0) && !(objK2 instanceof u)) {
                                        x xVar = e.f1435g;
                                        if (objK2 == xVar || objK2 == e.f1434f) {
                                            break loop0;
                                        }
                                        if (objK2 != xVar) {
                                            break;
                                        }
                                    } else {
                                        if (j6 < atomicLongFieldUpdater.get(this)) {
                                            break loop0;
                                        }
                                        c0 c0Var = objK2 instanceof u ? ((u) objK2).f1460a : (c0) objK2;
                                        if (kVarG.j(i4, objK2, e.f1440l)) {
                                            objE = AbstractC0000a.e(objE, c0Var);
                                            kVarG.m(i4, null);
                                            kVarG.h();
                                            break;
                                        }
                                    }
                                } else {
                                    if (kVarG.j(i4, objK2, e.f1440l)) {
                                        kVarG.h();
                                        break;
                                    }
                                }
                            } else {
                                if (j6 < atomicLongFieldUpdater.get(this)) {
                                    break loop0;
                                }
                                if (kVarG.j(i4, objK2, e.f1440l)) {
                                    kVarG.m(i4, null);
                                    kVarG.h();
                                    break;
                                }
                            }
                        }
                    }
                    kVarG = (k) ((C0.d) C0.d.f55b.get(kVarG));
                } while (kVarG != null);
                if (objE != null) {
                    if (objE instanceof ArrayList) {
                        ArrayList arrayList = (ArrayList) objE;
                        for (int size = arrayList.size() - 1; -1 < size; size--) {
                            t((c0) arrayList.get(size), false);
                        }
                    } else {
                        t((c0) objE, false);
                    }
                }
            }
            return true;
        }
        return false;
    }

    public final boolean p() {
        return o(f1419e.get(this), false);
    }

    public boolean q() {
        return false;
    }

    public final boolean r() {
        long j2 = f1421g.get(this);
        return j2 == 0 || j2 == Long.MAX_VALUE;
    }

    public final void s(long j2, k kVar) {
        k kVar2;
        k kVar3;
        while (kVar.f87c < j2 && (kVar3 = (k) kVar.b()) != null) {
            kVar = kVar3;
        }
        while (true) {
            if (!kVar.c() || (kVar2 = (k) kVar.b()) == null) {
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1425k;
                    v vVar = (v) atomicReferenceFieldUpdater.get(this);
                    if (vVar.f87c >= kVar.f87c) {
                        return;
                    }
                    if (!kVar.i()) {
                        break;
                    }
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(this, vVar, kVar)) {
                            if (vVar.e()) {
                                vVar.d();
                                return;
                            }
                            return;
                        }
                    } while (atomicReferenceFieldUpdater.get(this) == vVar);
                    if (kVar.e()) {
                        kVar.d();
                    }
                }
            } else {
                kVar = kVar2;
            }
        }
    }

    public final void t(c0 c0Var, boolean z2) {
        Throwable thL;
        if (c0Var instanceof InterfaceC0020d) {
            h0.d dVar = (h0.d) c0Var;
            if (z2) {
                thL = k();
                if (thL == null) {
                    thL = new l("Channel was closed");
                }
            } else {
                thL = l();
            }
            dVar.resumeWith(p000a.a.k(thL));
            return;
        }
        if (!(c0Var instanceof a)) {
            throw new IllegalStateException(("Unexpected waiter: " + c0Var).toString());
        }
        a aVar = (a) c0Var;
        C0021e c0021e = aVar.f1417e;
        p0.h.b(c0021e);
        aVar.f1417e = null;
        aVar.f1416d = e.f1440l;
        Throwable thK = aVar.f1418f.k();
        if (thK == null) {
            c0021e.resumeWith(Boolean.FALSE);
        } else {
            c0021e.resumeWith(p000a.a.k(thK));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toString() {
        String string;
        StringBuilder sb = new StringBuilder();
        int i2 = (int) (f1419e.get(this) >> 60);
        if (i2 == 2) {
            sb.append("closed,");
        } else if (i2 == 3) {
            sb.append("cancelled,");
        }
        sb.append("capacity=" + this.f1427d + ',');
        sb.append("data=[");
        int i3 = 0;
        boolean z2 = true;
        List listT = g0.f.T(f1424j.get(this), f1423i.get(this), f1425k.get(this));
        ArrayList arrayList = new ArrayList();
        for (Object obj : listT) {
            if (((k) obj) != e.f1429a) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        Object next = it.next();
        if (it.hasNext()) {
            long j2 = ((k) next).f87c;
            do {
                Object next2 = it.next();
                long j3 = ((k) next2).f87c;
                if (j2 > j3) {
                    next = next2;
                    j2 = j3;
                }
            } while (it.hasNext());
        }
        k kVar = (k) next;
        long j4 = f1420f.get(this);
        long jM = m();
        loop2: while (true) {
            int i4 = e.f1430b;
            int i5 = i3;
            while (i5 < i4) {
                long j5 = (kVar.f87c * ((long) e.f1430b)) + ((long) i5);
                if (j5 >= jM && j5 >= j4) {
                    break loop2;
                }
                Object objK = kVar.k(i5);
                boolean z3 = z2;
                Object obj2 = kVar.f1453f.get(i5 * 2);
                if (objK instanceof InterfaceC0020d) {
                    string = (j5 >= j4 || j5 < jM) ? (j5 >= jM || j5 < j4) ? "cont" : "send" : "receive";
                } else if (objK instanceof u) {
                    string = "EB(" + objK + ')';
                } else if (p0.h.a(objK, e.f1434f) ? z3 : p0.h.a(objK, e.f1435g)) {
                    string = "resuming_sender";
                } else {
                    if (!(objK == null ? z3 : objK.equals(e.f1433e) ? z3 : p0.h.a(objK, e.f1437i) ? z3 : p0.h.a(objK, e.f1436h) ? z3 : p0.h.a(objK, e.f1439k) ? z3 : p0.h.a(objK, e.f1438j) ? z3 : p0.h.a(objK, e.f1440l))) {
                        string = objK.toString();
                    }
                    i5++;
                    z2 = z3;
                }
                if (obj2 != null) {
                    sb.append("(" + string + ',' + obj2 + "),");
                } else {
                    sb.append(string + ',');
                }
                i5++;
                z2 = z3;
            }
            boolean z4 = z2;
            kVar = (k) kVar.b();
            if (kVar == null) {
                break;
            }
            z2 = z4;
            i3 = 0;
        }
        if (sb.length() == 0) {
            throw new NoSuchElementException("Char sequence is empty.");
        }
        if (sb.charAt(sb.length() - 1) == ',') {
            p0.h.d(sb.deleteCharAt(sb.length() - 1), "this.deleteCharAt(index)");
        }
        sb.append("]");
        return sb.toString();
    }

    public final boolean u(Object obj, Object obj2) {
        if (!(obj instanceof a)) {
            if (obj instanceof InterfaceC0020d) {
                p0.h.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<E of kotlinx.coroutines.channels.BufferedChannel>");
                return e.a((InterfaceC0020d) obj, obj2, null);
            }
            throw new IllegalStateException(("Unexpected receiver type: " + obj).toString());
        }
        p0.h.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator<E of kotlinx.coroutines.channels.BufferedChannel>");
        a aVar = (a) obj;
        C0021e c0021e = aVar.f1417e;
        p0.h.b(c0021e);
        aVar.f1417e = null;
        aVar.f1416d = obj2;
        return e.a(c0021e, Boolean.TRUE, null);
    }

    public final Object w(k kVar, int i2, long j2, a aVar) {
        AtomicReferenceArray atomicReferenceArray = kVar.f1453f;
        Object objK = kVar.k(i2);
        AtomicLongFieldUpdater atomicLongFieldUpdater = f1419e;
        if (objK == null) {
            if (j2 >= (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                if (aVar == null) {
                    return e.f1441n;
                }
                if (kVar.j(i2, objK, aVar)) {
                    i();
                    return e.m;
                }
            }
        } else if (objK == e.f1432d && kVar.j(i2, objK, e.f1437i)) {
            i();
            Object obj = atomicReferenceArray.get(i2 * 2);
            kVar.m(i2, null);
            return obj;
        }
        while (true) {
            Object objK2 = kVar.k(i2);
            if (objK2 == null || objK2 == e.f1433e) {
                if (j2 < (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                    if (kVar.j(i2, objK2, e.f1436h)) {
                        i();
                        return e.f1442o;
                    }
                } else {
                    if (aVar == null) {
                        return e.f1441n;
                    }
                    if (kVar.j(i2, objK2, aVar)) {
                        i();
                        return e.m;
                    }
                }
            } else if (objK2 != e.f1432d) {
                x xVar = e.f1438j;
                if (objK2 == xVar) {
                    return e.f1442o;
                }
                if (objK2 == e.f1436h) {
                    return e.f1442o;
                }
                if (objK2 == e.f1440l) {
                    i();
                    return e.f1442o;
                }
                if (objK2 != e.f1435g && kVar.j(i2, objK2, e.f1434f)) {
                    boolean z2 = objK2 instanceof u;
                    if (z2) {
                        objK2 = ((u) objK2).f1460a;
                    }
                    if (v(objK2)) {
                        kVar.n(i2, e.f1437i);
                        i();
                        Object obj2 = atomicReferenceArray.get(i2 * 2);
                        kVar.m(i2, null);
                        return obj2;
                    }
                    kVar.n(i2, xVar);
                    kVar.h();
                    if (z2) {
                        i();
                    }
                    return e.f1442o;
                }
            } else if (kVar.j(i2, objK2, e.f1437i)) {
                i();
                Object obj3 = atomicReferenceArray.get(i2 * 2);
                kVar.m(i2, null);
                return obj3;
            }
        }
    }

    public final int x(k kVar, int i2, Object obj, long j2, Object obj2, boolean z2) {
        while (true) {
            Object objK = kVar.k(i2);
            if (objK == null) {
                if (!e(j2) || z2) {
                    if (z2) {
                        if (kVar.j(i2, null, e.f1438j)) {
                            kVar.h();
                            return 4;
                        }
                    } else {
                        if (obj2 == null) {
                            return 3;
                        }
                        if (kVar.j(i2, null, obj2)) {
                            return 2;
                        }
                    }
                } else if (kVar.j(i2, null, e.f1432d)) {
                    break;
                }
            } else {
                if (objK != e.f1433e) {
                    x xVar = e.f1439k;
                    if (objK == xVar) {
                        kVar.m(i2, null);
                        return 5;
                    }
                    if (objK == e.f1436h) {
                        kVar.m(i2, null);
                        return 5;
                    }
                    if (objK == e.f1440l) {
                        kVar.m(i2, null);
                        p();
                        return 4;
                    }
                    kVar.m(i2, null);
                    if (objK instanceof u) {
                        objK = ((u) objK).f1460a;
                    }
                    if (u(objK, obj)) {
                        kVar.n(i2, e.f1437i);
                        return 0;
                    }
                    if (kVar.f1453f.getAndSet((i2 * 2) + 1, xVar) != xVar) {
                        kVar.l(i2, true);
                    }
                    return 5;
                }
                if (kVar.j(i2, objK, e.f1432d)) {
                    break;
                }
            }
        }
        return 1;
    }
}
