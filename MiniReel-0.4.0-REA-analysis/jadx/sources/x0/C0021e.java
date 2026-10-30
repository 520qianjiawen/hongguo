package x0;

import C0.AbstractC0000a;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: renamed from: x0.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0021e extends AbstractC0038w implements InterfaceC0020d, j0.c, c0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f1341i = AtomicIntegerFieldUpdater.newUpdater(C0021e.class, "_decisionAndIndex");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f1342j = AtomicReferenceFieldUpdater.newUpdater(C0021e.class, Object.class, "_state");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f1343k = AtomicReferenceFieldUpdater.newUpdater(C0021e.class, Object.class, "_parentHandle");
    private volatile int _decisionAndIndex;
    private volatile Object _parentHandle;
    private volatile Object _state;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final h0.d f1344g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final h0.i f1345h;

    public C0021e(h0.d dVar, int i2) {
        super(i2);
        this.f1344g = dVar;
        this.f1345h = dVar.getContext();
        this._decisionAndIndex = 536870911;
        this._state = C0018b.f1338a;
    }

    public static void r(z0.k kVar, Object obj) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + kVar + ", already has " + obj).toString());
    }

    @Override // x0.c0
    public final void a(z0.k kVar, int i2) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i3;
        do {
            atomicIntegerFieldUpdater = f1341i;
            i3 = atomicIntegerFieldUpdater.get(this);
            if ((i3 & 536870911) != 536870911) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once");
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i3, ((i3 >> 29) << 29) + i2));
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1342j;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof C0018b) {
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, kVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                    }
                }
                return;
            }
            if (obj instanceof C0.v) {
                r(kVar, obj);
                throw null;
            }
            if (!(obj instanceof C0027k)) {
                if (obj instanceof C0026j) {
                    ((C0026j) obj).getClass();
                    return;
                }
                return;
            }
            C0027k c0027k = (C0027k) obj;
            c0027k.getClass();
            if (!C0027k.f1353b.compareAndSet(c0027k, 0, 1)) {
                r(kVar, obj);
                throw null;
            }
            if (obj instanceof C0022f) {
                if (obj == null) {
                    c0027k = null;
                }
                j(kVar, c0027k != null ? c0027k.f1354a : null);
                return;
            }
            return;
        }
    }

    @Override // x0.AbstractC0038w
    public final void b(Object obj, CancellationException cancellationException) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1342j;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof V) {
                throw new IllegalStateException("Not completed");
            }
            if (obj2 instanceof C0027k) {
                return;
            }
            if (!(obj2 instanceof C0026j)) {
                C0026j c0026j = new C0026j(obj2, (o0.l) null, cancellationException, 14);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, c0026j)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj2) {
                    }
                }
                return;
            }
            C0026j c0026j2 = (C0026j) obj2;
            if (c0026j2.f1352d != null) {
                throw new IllegalStateException("Must be called at most once");
            }
            Object obj3 = c0026j2.f1349a;
            if ((15 & 2) != 0) {
                c0026j2.getClass();
            }
            o0.l lVar = c0026j2.f1350b;
            Object obj4 = c0026j2.f1351c;
            Throwable th = (15 & 16) != 0 ? c0026j2.f1352d : cancellationException;
            c0026j2.getClass();
            C0026j c0026j3 = new C0026j(obj3, lVar, obj4, th);
            do {
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj2, c0026j3)) {
                    o0.l lVar2 = c0026j2.f1350b;
                    if (lVar2 != null) {
                        i(lVar2, cancellationException);
                        return;
                    }
                    return;
                }
            } while (atomicReferenceFieldUpdater.get(this) == obj2);
        }
    }

    @Override // j0.c
    public final j0.c c() {
        h0.d dVar = this.f1344g;
        if (dVar instanceof j0.c) {
            return (j0.c) dVar;
        }
        return null;
    }

    @Override // x0.AbstractC0038w
    public final h0.d d() {
        return this.f1344g;
    }

    @Override // x0.AbstractC0038w
    public final Throwable e(Object obj) {
        Throwable thE = super.e(obj);
        if (thE != null) {
            return thE;
        }
        return null;
    }

    @Override // x0.AbstractC0038w
    public final Object f(Object obj) {
        return obj instanceof C0026j ? ((C0026j) obj).f1349a : obj;
    }

    @Override // h0.d
    public final h0.i getContext() {
        return this.f1345h;
    }

    @Override // x0.AbstractC0038w
    public final Object h() {
        return f1342j.get(this);
    }

    public final void i(o0.l lVar, Throwable th) {
        try {
            lVar.h(th);
        } catch (Throwable th2) {
            AbstractC0034s.c(this.f1345h, new H.c("Exception in resume onCancellation handler for " + this, th2));
        }
    }

    public final void j(C0.v vVar, Throwable th) {
        h0.i iVar = this.f1345h;
        int i2 = f1341i.get(this) & 536870911;
        if (i2 == 536870911) {
            throw new IllegalStateException("The index for Segment.onCancellation(..) is broken");
        }
        try {
            vVar.g(i2, iVar);
        } catch (Throwable th2) {
            AbstractC0034s.c(iVar, new H.c("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    public final void k(Throwable th) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1342j;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (!(obj instanceof V)) {
                return;
            }
            C0022f c0022f = new C0022f(this, th, obj instanceof C0.v);
            do {
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj, c0022f)) {
                    if (((V) obj) instanceof C0.v) {
                        j((C0.v) obj, th);
                    }
                    if (!q()) {
                        l();
                    }
                    m(this.f1375f);
                    return;
                }
            } while (atomicReferenceFieldUpdater.get(this) == obj);
        }
    }

    public final void l() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1343k;
        InterfaceC0040y interfaceC0040y = (InterfaceC0040y) atomicReferenceFieldUpdater.get(this);
        if (interfaceC0040y == null) {
            return;
        }
        interfaceC0040y.c();
        atomicReferenceFieldUpdater.set(this, U.f1334d);
    }

    public final void m(int i2) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i3;
        do {
            atomicIntegerFieldUpdater = f1341i;
            i3 = atomicIntegerFieldUpdater.get(this);
            int i4 = i3 >> 29;
            if (i4 != 0) {
                if (i4 != 1) {
                    throw new IllegalStateException("Already resumed");
                }
                boolean z2 = i2 == 4;
                h0.d dVar = this.f1344g;
                if (!z2 && (dVar instanceof C0.h)) {
                    boolean z3 = i2 == 1 || i2 == 2;
                    int i5 = this.f1375f;
                    if (z3 == (i5 == 1 || i5 == 2)) {
                        AbstractC0032p abstractC0032p = ((C0.h) dVar).f60g;
                        h0.i iVar = ((C0.h) dVar).f61h.f1072e;
                        p0.h.b(iVar);
                        if (abstractC0032p.d()) {
                            abstractC0032p.c(iVar, this);
                            return;
                        }
                        C cA = Z.a();
                        if (cA.f1309f >= 4294967296L) {
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
                            AbstractC0034s.f(this, dVar, true);
                            do {
                            } while (cA.l());
                        } catch (Throwable th) {
                            try {
                                g(th, null);
                            } finally {
                                cA.e();
                            }
                        }
                        return;
                    }
                }
                AbstractC0034s.f(this, dVar, z2);
                return;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i3, 1073741824 + (536870911 & i3)));
    }

    public final Object n() throws Throwable {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i2;
        J j2;
        boolean zQ = q();
        do {
            atomicIntegerFieldUpdater = f1341i;
            i2 = atomicIntegerFieldUpdater.get(this);
            int i3 = i2 >> 29;
            if (i3 != 0) {
                if (i3 != 2) {
                    throw new IllegalStateException("Already suspended");
                }
                if (zQ) {
                    s();
                }
                Object obj = f1342j.get(this);
                if (obj instanceof C0027k) {
                    throw ((C0027k) obj).f1354a;
                }
                int i4 = this.f1375f;
                if ((i4 != 1 && i4 != 2) || (j2 = (J) this.f1345h.i(C0033q.f1363e)) == null || j2.b()) {
                    return f(obj);
                }
                CancellationException cancellationExceptionT = ((S) j2).t();
                b(obj, cancellationExceptionT);
                throw cancellationExceptionT;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, 536870912 + (536870911 & i2)));
        if (((InterfaceC0040y) f1343k.get(this)) == null) {
            p();
        }
        if (zQ) {
            s();
        }
        return i0.a.f747d;
    }

    public final void o() {
        InterfaceC0040y interfaceC0040yP = p();
        if (interfaceC0040yP == null || (f1342j.get(this) instanceof V)) {
            return;
        }
        interfaceC0040yP.c();
        f1343k.set(this, U.f1334d);
    }

    public final InterfaceC0040y p() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        J j2 = (J) this.f1345h.i(C0033q.f1363e);
        if (j2 == null) {
            return null;
        }
        InterfaceC0040y interfaceC0040yD = AbstractC0034s.d(j2, new C0023g(this), 2);
        do {
            atomicReferenceFieldUpdater = f1343k;
            if (atomicReferenceFieldUpdater.compareAndSet(this, null, interfaceC0040yD)) {
                break;
            }
        } while (atomicReferenceFieldUpdater.get(this) == null);
        return interfaceC0040yD;
    }

    public final boolean q() {
        if (this.f1375f != 2) {
            return false;
        }
        h0.d dVar = this.f1344g;
        p0.h.c(dVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        return C0.h.f59k.get((C0.h) dVar) != null;
    }

    @Override // h0.d
    public final void resumeWith(Object obj) {
        Throwable thA = f0.d.a(obj);
        if (thA != null) {
            obj = new C0027k(thA, false);
        }
        t(obj, this.f1375f, null);
    }

    public final void s() {
        h0.d dVar = this.f1344g;
        Throwable th = null;
        C0.h hVar = dVar instanceof C0.h ? (C0.h) dVar : null;
        if (hVar != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = C0.h.f59k;
            loop0: while (true) {
                Object obj = atomicReferenceFieldUpdater.get(hVar);
                C0.x xVar = AbstractC0000a.f49d;
                if (obj != xVar) {
                    if (!(obj instanceof Throwable)) {
                        throw new IllegalStateException(("Inconsistent state " + obj).toString());
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(hVar, obj, null)) {
                        if (atomicReferenceFieldUpdater.get(hVar) != obj) {
                            throw new IllegalArgumentException("Failed requirement.");
                        }
                    }
                    th = (Throwable) obj;
                    break;
                }
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(hVar, xVar, this)) {
                        break loop0;
                    }
                } while (atomicReferenceFieldUpdater.get(hVar) == xVar);
            }
            if (th == null) {
                return;
            }
            l();
            k(th);
        }
    }

    public final void t(Object obj, int i2, o0.l lVar) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1342j;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (!(obj2 instanceof V)) {
                if (obj2 instanceof C0022f) {
                    C0022f c0022f = (C0022f) obj2;
                    c0022f.getClass();
                    if (C0022f.f1346c.compareAndSet(c0022f, 0, 1)) {
                        if (lVar != null) {
                            i(lVar, c0022f.f1354a);
                            return;
                        }
                        return;
                    }
                }
                throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
            }
            Object c0026j = (!(obj instanceof C0027k) && (i2 == 1 || i2 == 2) && lVar != null) ? new C0026j(obj, lVar, (CancellationException) null, 16) : obj;
            do {
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj2, c0026j)) {
                    if (!q()) {
                        l();
                    }
                    m(i2);
                    return;
                }
            } while (atomicReferenceFieldUpdater.get(this) == obj2);
        }
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("CancellableContinuation(");
        sb.append(AbstractC0034s.g(this.f1344g));
        sb.append("){");
        Object obj = f1342j.get(this);
        if (obj instanceof V) {
            str = "Active";
        } else {
            str = obj instanceof C0022f ? "Cancelled" : "Completed";
        }
        sb.append(str);
        sb.append("}@");
        sb.append(AbstractC0034s.a(this));
        return sb.toString();
    }
}
