package x0;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public class S implements J, W {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f1332d = AtomicReferenceFieldUpdater.newUpdater(S.class, Object.class, "_state");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f1333e = AtomicReferenceFieldUpdater.newUpdater(S.class, Object.class, "_parentHandle");
    private volatile Object _parentHandle;
    private volatile Object _state;

    public S(boolean z2) {
        this._state = z2 ? AbstractC0034s.f1372i : AbstractC0034s.f1371h;
    }

    public static C0025i B(C0.l lVar) {
        while (lVar.m()) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = C0.l.f72e;
            C0.l lVarI = lVar.i();
            if (lVarI == null) {
                Object obj = atomicReferenceFieldUpdater.get(lVar);
                while (true) {
                    lVar = (C0.l) obj;
                    if (!lVar.m()) {
                        break;
                    }
                    obj = atomicReferenceFieldUpdater.get(lVar);
                }
            } else {
                lVar = lVarI;
            }
        }
        while (true) {
            lVar = lVar.l();
            if (!lVar.m()) {
                if (lVar instanceof C0025i) {
                    return (C0025i) lVar;
                }
                if (lVar instanceof T) {
                    return null;
                }
            }
        }
    }

    public static String G(Object obj) {
        if (!(obj instanceof P)) {
            if (obj instanceof G) {
                return ((G) obj).b() ? "Active" : "New";
            }
            return obj instanceof C0027k ? "Cancelled" : "Completed";
        }
        P p2 = (P) obj;
        if (p2.d()) {
            return "Cancelling";
        }
        return p2.e() ? "Completing" : "Active";
    }

    public final Object A(Object obj) throws IllegalAccessException, InvocationTargetException {
        Object objH;
        do {
            objH = H(w(), obj);
            if (objH == AbstractC0034s.f1366c) {
                String str = "Job " + this + " is already complete or completing, but is being completed with " + obj;
                C0027k c0027k = obj instanceof C0027k ? (C0027k) obj : null;
                throw new IllegalStateException(str, c0027k != null ? c0027k.f1354a : null);
            }
        } while (objH == AbstractC0034s.f1368e);
        return objH;
    }

    public final void C(T t2, Throwable th) throws IllegalAccessException, InvocationTargetException {
        Object objK = t2.k();
        p0.h.c(objK, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        H.c cVar = null;
        for (C0.l lVarL = (C0.l) objK; !lVarL.equals(t2); lVarL = lVarL.l()) {
            if (lVarL instanceof L) {
                N n2 = (N) lVarL;
                try {
                    n2.o(th);
                } catch (Throwable th2) {
                    if (cVar != null) {
                        p000a.a.a(cVar, th2);
                    } else {
                        cVar = new H.c("Exception in completion handler " + n2 + " for " + this, th2);
                    }
                }
            }
        }
        if (cVar != null) {
            y(cVar);
        }
        o(th);
    }

    public final void F(N n2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        T t2 = new T();
        n2.getClass();
        C0.l.f72e.lazySet(t2, n2);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = C0.l.f71d;
        atomicReferenceFieldUpdater2.lazySet(t2, n2);
        loop0: while (n2.k() == n2) {
            do {
                if (atomicReferenceFieldUpdater2.compareAndSet(n2, n2, t2)) {
                    t2.j(n2);
                    break loop0;
                }
            } while (atomicReferenceFieldUpdater2.get(n2) == n2);
        }
        C0.l lVarL = n2.l();
        do {
            atomicReferenceFieldUpdater = f1332d;
            if (atomicReferenceFieldUpdater.compareAndSet(this, n2, lVarL)) {
                return;
            }
        } while (atomicReferenceFieldUpdater.get(this) == n2);
    }

    public final Object H(Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
        if (!(obj instanceof G)) {
            return AbstractC0034s.f1366c;
        }
        if (((obj instanceof C0041z) || (obj instanceof N)) && !(obj instanceof C0025i) && !(obj2 instanceof C0027k)) {
            G g2 = (G) obj;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1332d;
            Object h2 = obj2 instanceof G ? new H((G) obj2) : obj2;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, g2, h2)) {
                if (atomicReferenceFieldUpdater.get(this) != g2) {
                    return AbstractC0034s.f1368e;
                }
            }
            D(obj2);
            q(g2, obj2);
            return obj2;
        }
        G g3 = (G) obj;
        T tV = v(g3);
        if (tV == null) {
            return AbstractC0034s.f1368e;
        }
        C0025i c0025iB = null;
        P p2 = g3 instanceof P ? (P) g3 : null;
        if (p2 == null) {
            p2 = new P(tV, null);
        }
        synchronized (p2) {
            if (p2.e()) {
                return AbstractC0034s.f1366c;
            }
            P.f1324e.set(p2, 1);
            if (p2 != g3) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f1332d;
                while (!atomicReferenceFieldUpdater2.compareAndSet(this, g3, p2)) {
                    if (atomicReferenceFieldUpdater2.get(this) != g3) {
                        return AbstractC0034s.f1368e;
                    }
                }
            }
            boolean zD = p2.d();
            C0027k c0027k = obj2 instanceof C0027k ? (C0027k) obj2 : null;
            if (c0027k != null) {
                p2.a(c0027k.f1354a);
            }
            Throwable thC = p2.c();
            if (zD) {
                thC = null;
            }
            if (thC != null) {
                C(tV, thC);
            }
            C0025i c0025i = g3 instanceof C0025i ? (C0025i) g3 : null;
            if (c0025i == null) {
                T tF = g3.f();
                if (tF != null) {
                    c0025iB = B(tF);
                }
            } else {
                c0025iB = c0025i;
            }
            if (c0025iB != null) {
                while (AbstractC0034s.d(c0025iB.f1348h, new O(this, p2, c0025iB, obj2), 1) == U.f1334d) {
                    c0025iB = B(c0025iB);
                    if (c0025iB == null) {
                    }
                }
                return AbstractC0034s.f1367d;
            }
            return s(p2, obj2);
        }
    }

    @Override // x0.J
    public void a(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new K(p(), null, this);
        }
        n(cancellationException);
    }

    @Override // x0.J
    public boolean b() {
        Object objW = w();
        return (objW instanceof G) && ((G) objW).b();
    }

    @Override // h0.i
    public final Object f(Object obj, o0.p pVar) {
        return pVar.g(obj, this);
    }

    @Override // h0.i
    public final h0.i g(h0.h hVar) {
        return p000a.a.C(this, hVar);
    }

    @Override // h0.g
    public final h0.h getKey() {
        return C0033q.f1363e;
    }

    @Override // h0.i
    public final h0.i h(h0.i iVar) {
        return p000a.a.E(this, iVar);
    }

    @Override // h0.i
    public final h0.g i(h0.h hVar) {
        p0.h.e(hVar, "key");
        if (p0.h.a(C0033q.f1363e, hVar)) {
            return this;
        }
        return null;
    }

    public final boolean j(G g2, T t2, N n2) {
        char c2;
        Q q2 = new Q(n2, this, g2);
        do {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = C0.l.f72e;
            C0.l lVarI = t2.i();
            if (lVarI == null) {
                Object obj = atomicReferenceFieldUpdater.get(t2);
                while (true) {
                    lVarI = (C0.l) obj;
                    if (!lVarI.m()) {
                        break;
                    }
                    obj = atomicReferenceFieldUpdater.get(lVarI);
                }
            }
            C0.l.f72e.lazySet(n2, lVarI);
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = C0.l.f71d;
            atomicReferenceFieldUpdater2.lazySet(n2, t2);
            q2.f1329c = t2;
            while (true) {
                if (atomicReferenceFieldUpdater2.compareAndSet(lVarI, t2, q2)) {
                    if (q2.a(lVarI) != null) {
                        c2 = 2;
                        break;
                    }
                    c2 = 1;
                    break;
                }
                if (atomicReferenceFieldUpdater2.get(lVarI) != t2) {
                    c2 = 0;
                    break;
                }
            }
            if (c2 == 1) {
                return true;
            }
        } while (c2 != 2);
        return false;
    }

    public void l(Object obj) {
        k(obj);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0038 A[PHI: r0
      0x0038: PHI (r0v1 java.lang.Object) = (r0v0 java.lang.Object), (r0v12 java.lang.Object) binds: [B:3:0x0006, B:16:0x0034] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:20:0x003c  */
    /* JADX WARN: Code duplicated, block: B:26:0x0057  */
    /* JADX WARN: Code duplicated, block: B:27:0x0059  */
    /* JADX WARN: Code duplicated, block: B:29:0x005c A[Catch: all -> 0x0062, TRY_LEAVE, TryCatch #0 {, blocks: (B:24:0x0047, B:29:0x005c, B:34:0x0064, B:36:0x006d, B:37:0x0071), top: B:81:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x0064 A[Catch: all -> 0x0062, TRY_ENTER, TryCatch #0 {, blocks: (B:24:0x0047, B:29:0x005c, B:34:0x0064, B:36:0x006d, B:37:0x0071), top: B:81:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x006d A[Catch: all -> 0x0062, TryCatch #0 {, blocks: (B:24:0x0047, B:29:0x005c, B:34:0x0064, B:36:0x006d, B:37:0x0071), top: B:81:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x0080  */
    /* JADX WARN: Code duplicated, block: B:42:0x0084  */
    /* JADX WARN: Code duplicated, block: B:46:0x0090  */
    /* JADX WARN: Code duplicated, block: B:48:0x0094 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:49:0x0096  */
    /* JADX WARN: Code duplicated, block: B:59:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:64:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:78:0x0102 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:79:0x0103  */
    /* JADX WARN: Code duplicated, block: B:81:0x0047 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x00c5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:87:0x00aa A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:88:0x0046 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:89:0x00f0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x00b7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x00d6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:0x00a3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:93:0x00d8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:95:0x003e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:96:0x003e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:97:0x003e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:98:? A[LOOP:2: B:56:0x00b1->B:98:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:20:0x003c, please report this as an issue */
    public final boolean m(Object obj) {
        Throwable thR;
        Object objW;
        P p2;
        boolean z2;
        Throwable thC;
        C0.x xVar;
        G g2;
        T tV;
        P p3;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Object objH;
        Object objH2 = AbstractC0034s.f1366c;
        if (this instanceof M) {
            do {
                Object objW2 = w();
                if (!(objW2 instanceof G) || ((objW2 instanceof P) && ((P) objW2).e())) {
                    objH2 = AbstractC0034s.f1366c;
                    break;
                }
                objH2 = H(objW2, new C0027k(r(obj), false));
            } while (objH2 == AbstractC0034s.f1368e);
            if (objH2 != AbstractC0034s.f1367d) {
                if (objH2 == AbstractC0034s.f1366c) {
                    thR = null;
                    loop1: while (true) {
                        objW = w();
                        if (objW instanceof P) {
                            synchronized (objW) {
                                p2 = (P) objW;
                                p2.getClass();
                                if (P.f1326g.get(p2) == AbstractC0034s.f1370g) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                if (z2) {
                                    xVar = AbstractC0034s.f1369f;
                                } else {
                                    boolean zD = ((P) objW).d();
                                    if (thR == null) {
                                        thR = r(obj);
                                    }
                                    ((P) objW).a(thR);
                                    thC = zD ? null : ((P) objW).c();
                                    if (thC != null) {
                                        C(((P) objW).f1327d, thC);
                                    }
                                    xVar = AbstractC0034s.f1366c;
                                }
                            }
                        } else if (objW instanceof G) {
                            if (thR == null) {
                                thR = r(obj);
                            }
                            g2 = (G) objW;
                            if (g2.b()) {
                                tV = v(g2);
                                if (tV == null) {
                                    continue;
                                } else {
                                    p3 = new P(tV, thR);
                                    atomicReferenceFieldUpdater = f1332d;
                                    while (true) {
                                        if (atomicReferenceFieldUpdater.compareAndSet(this, g2, p3)) {
                                            C(tV, thR);
                                            xVar = AbstractC0034s.f1366c;
                                        } else if (atomicReferenceFieldUpdater.get(this) != g2) {
                                        }
                                    }
                                }
                            } else {
                                objH = H(objW, new C0027k(thR, false));
                                if (objH != AbstractC0034s.f1366c) {
                                    throw new IllegalStateException(("Cannot happen in " + objW).toString());
                                }
                                if (objH != AbstractC0034s.f1368e) {
                                    objH2 = objH;
                                    break;
                                }
                            }
                        } else {
                            xVar = AbstractC0034s.f1369f;
                        }
                        objH2 = xVar;
                        break;
                    }
                }
                if (objH2 != AbstractC0034s.f1366c && objH2 != AbstractC0034s.f1367d) {
                    if (objH2 == AbstractC0034s.f1369f) {
                        return false;
                    }
                    k(objH2);
                    return true;
                }
            }
        } else {
            if (objH2 == AbstractC0034s.f1366c) {
                thR = null;
                loop1: while (true) {
                    objW = w();
                    if (objW instanceof P) {
                        synchronized (objW) {
                            p2 = (P) objW;
                            p2.getClass();
                            if (P.f1326g.get(p2) == AbstractC0034s.f1370g) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (z2) {
                                xVar = AbstractC0034s.f1369f;
                            } else {
                                boolean zD2 = ((P) objW).d();
                                if (thR == null) {
                                    thR = r(obj);
                                }
                                ((P) objW).a(thR);
                                if (zD2) {
                                }
                                if (thC != null) {
                                    C(((P) objW).f1327d, thC);
                                }
                                xVar = AbstractC0034s.f1366c;
                            }
                        }
                    } else if (objW instanceof G) {
                        if (thR == null) {
                            thR = r(obj);
                        }
                        g2 = (G) objW;
                        if (g2.b()) {
                            tV = v(g2);
                            if (tV == null) {
                                continue;
                            } else {
                                p3 = new P(tV, thR);
                                atomicReferenceFieldUpdater = f1332d;
                                while (true) {
                                    if (atomicReferenceFieldUpdater.compareAndSet(this, g2, p3)) {
                                        C(tV, thR);
                                        xVar = AbstractC0034s.f1366c;
                                    } else if (atomicReferenceFieldUpdater.get(this) != g2) {
                                    }
                                }
                            }
                        } else {
                            objH = H(objW, new C0027k(thR, false));
                            if (objH != AbstractC0034s.f1366c) {
                                throw new IllegalStateException(("Cannot happen in " + objW).toString());
                            }
                            if (objH != AbstractC0034s.f1368e) {
                                objH2 = objH;
                                break;
                            }
                        }
                    } else {
                        xVar = AbstractC0034s.f1369f;
                    }
                    objH2 = xVar;
                    break;
                }
            }
            if (objH2 != AbstractC0034s.f1366c) {
                if (objH2 == AbstractC0034s.f1369f) {
                    return false;
                }
                k(objH2);
                return true;
            }
        }
        return true;
    }

    public void n(CancellationException cancellationException) {
        m(cancellationException);
    }

    public final boolean o(Throwable th) {
        if (this instanceof C0.u) {
            return true;
        }
        boolean z2 = th instanceof CancellationException;
        InterfaceC0024h interfaceC0024h = (InterfaceC0024h) f1333e.get(this);
        if (interfaceC0024h == null || interfaceC0024h == U.f1334d) {
            return z2;
        }
        return interfaceC0024h.e(th) || z2;
    }

    public String p() {
        return "Job was cancelled";
    }

    public final void q(G g2, Object obj) throws IllegalAccessException, InvocationTargetException {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1333e;
        InterfaceC0024h interfaceC0024h = (InterfaceC0024h) atomicReferenceFieldUpdater.get(this);
        if (interfaceC0024h != null) {
            interfaceC0024h.c();
            atomicReferenceFieldUpdater.set(this, U.f1334d);
        }
        H.c cVar = null;
        C0027k c0027k = obj instanceof C0027k ? (C0027k) obj : null;
        Throwable th = c0027k != null ? c0027k.f1354a : null;
        if (g2 instanceof N) {
            try {
                ((N) g2).o(th);
                return;
            } catch (Throwable th2) {
                y(new H.c("Exception in completion handler " + g2 + " for " + this, th2));
                return;
            }
        }
        T tF = g2.f();
        if (tF != null) {
            Object objK = tF.k();
            p0.h.c(objK, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            for (C0.l lVarL = (C0.l) objK; !lVarL.equals(tF); lVarL = lVarL.l()) {
                if (lVarL instanceof N) {
                    N n2 = (N) lVarL;
                    try {
                        n2.o(th);
                    } catch (Throwable th3) {
                        if (cVar != null) {
                            p000a.a.a(cVar, th3);
                        } else {
                            cVar = new H.c("Exception in completion handler " + n2 + " for " + this, th3);
                        }
                    }
                }
            }
            if (cVar != null) {
                y(cVar);
            }
        }
    }

    public final Throwable r(Object obj) {
        Throwable thC;
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        S s2 = (S) ((W) obj);
        Object objW = s2.w();
        if (objW instanceof P) {
            thC = ((P) objW).c();
        } else if (objW instanceof C0027k) {
            thC = ((C0027k) objW).f1354a;
        } else {
            if (objW instanceof G) {
                throw new IllegalStateException(("Cannot be cancelling child in this state: " + objW).toString());
            }
            thC = null;
        }
        CancellationException cancellationException = thC instanceof CancellationException ? (CancellationException) thC : null;
        return cancellationException == null ? new K("Parent job is ".concat(G(objW)), thC, s2) : cancellationException;
    }

    public final Object s(P p2, Object obj) throws IllegalAccessException, InvocationTargetException {
        Object obj2 = null;
        Throwable k2 = null;
        C0027k c0027k = obj instanceof C0027k ? (C0027k) obj : null;
        Throwable th = c0027k != null ? c0027k.f1354a : null;
        synchronized (p2) {
            p2.d();
            ArrayList arrayListG = p2.g(th);
            if (!arrayListG.isEmpty()) {
                int size = arrayListG.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj3 = arrayListG.get(i2);
                    i2++;
                    if (!(((Throwable) obj3) instanceof CancellationException)) {
                        obj2 = obj3;
                        break;
                    }
                }
                k2 = (Throwable) obj2;
                if (k2 == null) {
                    k2 = (Throwable) arrayListG.get(0);
                }
            } else if (p2.d()) {
                k2 = new K(p(), null, this);
            }
            if (k2 != null && arrayListG.size() > 1) {
                Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap(arrayListG.size()));
                int size2 = arrayListG.size();
                int i3 = 0;
                while (i3 < size2) {
                    Object obj4 = arrayListG.get(i3);
                    i3++;
                    Throwable th2 = (Throwable) obj4;
                    if (th2 != k2 && th2 != k2 && !(th2 instanceof CancellationException) && setNewSetFromMap.add(th2)) {
                        p000a.a.a(k2, th2);
                    }
                }
            }
        }
        if (k2 != null && k2 != th) {
            obj = new C0027k(k2, false);
        }
        if (k2 != null && (o(k2) || x(k2))) {
            p0.h.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
            C0027k.f1353b.compareAndSet((C0027k) obj, 0, 1);
        }
        D(obj);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1332d;
        Object h2 = obj instanceof G ? new H((G) obj) : obj;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, p2, h2) && atomicReferenceFieldUpdater.get(this) == p2) {
        }
        q(p2, obj);
        return obj;
    }

    public final CancellationException t() {
        CancellationException cancellationException;
        Object objW = w();
        if (!(objW instanceof P)) {
            if (objW instanceof G) {
                throw new IllegalStateException(("Job is still new or active: " + this).toString());
            }
            if (!(objW instanceof C0027k)) {
                return new K(getClass().getSimpleName().concat(" has completed normally"), null, this);
            }
            Throwable th = ((C0027k) objW).f1354a;
            cancellationException = th instanceof CancellationException ? (CancellationException) th : null;
            return cancellationException == null ? new K(p(), th, this) : cancellationException;
        }
        Throwable thC = ((P) objW).c();
        if (thC == null) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        String strConcat = getClass().getSimpleName().concat(" is cancelling");
        cancellationException = thC instanceof CancellationException ? (CancellationException) thC : null;
        if (cancellationException != null) {
            return cancellationException;
        }
        if (strConcat == null) {
            strConcat = p();
        }
        return new K(strConcat, thC, this);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName() + '{' + G(w()) + '}');
        sb.append('@');
        sb.append(AbstractC0034s.a(this));
        return sb.toString();
    }

    public boolean u() {
        return true;
    }

    public final T v(G g2) {
        T tF = g2.f();
        if (tF != null) {
            return tF;
        }
        if (g2 instanceof C0041z) {
            return new T();
        }
        if (g2 instanceof N) {
            F((N) g2);
            return null;
        }
        throw new IllegalStateException(("State should have list: " + g2).toString());
    }

    public final Object w() {
        while (true) {
            Object obj = f1332d.get(this);
            if (!(obj instanceof C0.r)) {
                return obj;
            }
            ((C0.r) obj).a(this);
        }
    }

    public boolean x(Throwable th) {
        return false;
    }

    public final void z(J j2) {
        byte b2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1333e;
        U u2 = U.f1334d;
        if (j2 == null) {
            atomicReferenceFieldUpdater.set(this, u2);
            return;
        }
        S s2 = (S) j2;
        do {
            Object objW = s2.w();
            boolean z2 = objW instanceof C0041z;
            b2 = 0;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f1332d;
            if (z2) {
                if (!((C0041z) objW).f1378d) {
                    C0041z c0041z = AbstractC0034s.f1372i;
                    while (true) {
                        if (atomicReferenceFieldUpdater2.compareAndSet(s2, objW, c0041z)) {
                            s2.getClass();
                            b2 = 1;
                        } else if (atomicReferenceFieldUpdater2.get(s2) != objW) {
                            b2 = -1;
                        }
                    }
                }
            } else if (objW instanceof F) {
                T t2 = ((F) objW).f1313d;
                while (true) {
                    if (atomicReferenceFieldUpdater2.compareAndSet(s2, objW, t2)) {
                        s2.getClass();
                        b2 = 1;
                    } else if (atomicReferenceFieldUpdater2.get(s2) != objW) {
                        b2 = -1;
                    }
                }
            }
            if (b2 == 0) {
                break;
            }
        } while (b2 != 1);
        InterfaceC0024h interfaceC0024h = (InterfaceC0024h) AbstractC0034s.d(s2, new C0025i(this), 2);
        atomicReferenceFieldUpdater.set(this, interfaceC0024h);
        if (w() instanceof G) {
            return;
        }
        interfaceC0024h.c();
        atomicReferenceFieldUpdater.set(this, u2);
    }

    public void E() {
    }

    public void D(Object obj) {
    }

    public void k(Object obj) {
    }

    public void y(H.c cVar) {
        throw cVar;
    }
}
