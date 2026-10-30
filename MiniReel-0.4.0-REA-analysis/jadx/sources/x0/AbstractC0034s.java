package x0;

import C0.AbstractC0000a;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: renamed from: x0.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0034s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0.x f1364a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0.x f1365b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final C0.x f1366c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final C0.x f1367d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C0.x f1368e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C0.x f1369f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C0.x f1370g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final C0041z f1371h = new C0041z(false);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final C0041z f1372i = new C0041z(true);

    static {
        int i2 = 0;
        f1364a = new C0.x(i2, "RESUME_TOKEN");
        f1365b = new C0.x(i2, "CLOSED_EMPTY");
        f1366c = new C0.x(i2, "COMPLETING_ALREADY");
        f1367d = new C0.x(i2, "COMPLETING_WAITING_CHILDREN");
        f1368e = new C0.x(i2, "COMPLETING_RETRY");
        f1369f = new C0.x(i2, "TOO_LATE_TO_CANCEL");
        f1370g = new C0.x(i2, "SEALED");
    }

    public static final String a(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static final C0021e b(h0.d dVar) {
        C0021e c0021e;
        C0021e c0021e2;
        if (!(dVar instanceof C0.h)) {
            return new C0021e(dVar, 1);
        }
        C0.h hVar = (C0.h) dVar;
        hVar.getClass();
        C0.x xVar = AbstractC0000a.f49d;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = C0.h.f59k;
        loop0: while (true) {
            Object obj = atomicReferenceFieldUpdater.get(hVar);
            c0021e = null;
            if (obj == null) {
                atomicReferenceFieldUpdater.set(hVar, xVar);
                c0021e2 = null;
                break;
            }
            if (obj instanceof C0021e) {
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(hVar, obj, xVar)) {
                        c0021e2 = (C0021e) obj;
                        break loop0;
                    }
                } while (atomicReferenceFieldUpdater.get(hVar) == obj);
            } else if (obj != xVar && !(obj instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        }
        if (c0021e2 != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = C0021e.f1342j;
            Object obj2 = atomicReferenceFieldUpdater2.get(c0021e2);
            if (!(obj2 instanceof C0026j) || ((C0026j) obj2).f1351c == null) {
                C0021e.f1341i.set(c0021e2, 536870911);
                atomicReferenceFieldUpdater2.set(c0021e2, C0018b.f1338a);
                c0021e = c0021e2;
            } else {
                c0021e2.l();
            }
            if (c0021e != null) {
                return c0021e;
            }
        }
        return new C0021e(dVar, 2);
    }

    public static final void c(h0.i iVar, Throwable th) {
        try {
            y0.b bVar = (y0.b) iVar.i(C0033q.f1362d);
            if (bVar != null) {
                bVar.c(th);
            } else {
                AbstractC0000a.c(iVar, th);
            }
        } catch (Throwable th2) {
            if (th != th2) {
                RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                p000a.a.a(runtimeException, th);
                th = runtimeException;
            }
            AbstractC0000a.c(iVar, th);
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x00bd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:102:0x00cb A[EDGE_INSN: B:102:0x00cb->B:80:0x00cb BREAK  A[LOOP:0: B:20:0x002b->B:110:0x002b], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:111:0x002b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:76:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:78:0x00c3  */
    public static InterfaceC0040y d(J j2, N n2, int i2) {
        N i3;
        Throwable thC;
        boolean z2 = (i2 & 1) == 0;
        boolean z3 = (i2 & 2) != 0;
        S s2 = (S) j2;
        s2.getClass();
        if (z2) {
            i3 = n2 instanceof L ? (L) n2 : null;
            if (i3 == null) {
                i3 = new I(n2);
            }
        } else {
            i3 = n2;
        }
        i3.f1319g = s2;
        loop0: while (true) {
            Object objW = s2.w();
            if (objW instanceof C0041z) {
                C0041z c0041z = (C0041z) objW;
                if (c0041z.f1378d) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = S.f1332d;
                    while (!atomicReferenceFieldUpdater.compareAndSet(s2, objW, i3)) {
                        if (atomicReferenceFieldUpdater.get(s2) != objW) {
                        }
                    }
                    break loop0;
                }
                T t2 = new T();
                G f2 = c0041z.f1378d ? t2 : new F(t2);
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = S.f1332d;
                while (!atomicReferenceFieldUpdater2.compareAndSet(s2, c0041z, f2) && atomicReferenceFieldUpdater2.get(s2) == c0041z) {
                }
            } else {
                if (!(objW instanceof G)) {
                    if (z3) {
                        C0027k c0027k = objW instanceof C0027k ? (C0027k) objW : null;
                        n2.h(c0027k != null ? c0027k.f1354a : null);
                    }
                    return U.f1334d;
                }
                T tF = ((G) objW).f();
                if (tF == null) {
                    p0.h.c(objW, "null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                    s2.F((N) objW);
                } else {
                    InterfaceC0040y interfaceC0040y = U.f1334d;
                    if (z2 && (objW instanceof P)) {
                        synchronized (objW) {
                            try {
                                thC = ((P) objW).c();
                                if (thC == null || ((n2 instanceof C0025i) && !((P) objW).e())) {
                                    if (s2.j((G) objW, tF, i3)) {
                                        if (thC == null) {
                                            return i3;
                                        }
                                        interfaceC0040y = i3;
                                    }
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        if (thC != null) {
                            if (z3) {
                                n2.h(thC);
                            }
                            return interfaceC0040y;
                        }
                        if (s2.j((G) objW, tF, i3)) {
                            break;
                            break;
                        }
                    } else {
                        thC = null;
                        if (thC != null) {
                            if (z3) {
                                n2.h(thC);
                            }
                            return interfaceC0040y;
                        }
                        if (s2.j((G) objW, tF, i3)) {
                            break;
                        }
                    }
                }
            }
        }
        return i3;
    }

    public static final h0.i e(r rVar, h0.i iVar) {
        h0.i iVarH;
        h0.i iVarE = rVar.e();
        Boolean bool = Boolean.FALSE;
        C0029m c0029m = C0029m.f1356g;
        boolean zBooleanValue = ((Boolean) iVarE.f(bool, c0029m)).booleanValue();
        boolean zBooleanValue2 = ((Boolean) iVar.f(bool, c0029m)).booleanValue();
        if (zBooleanValue || zBooleanValue2) {
            C0029m c0029m2 = new C0029m(2, 2);
            h0.j jVar = h0.j.f746d;
            h0.i iVar2 = (h0.i) iVarE.f(jVar, c0029m2);
            Object objF = iVar;
            if (zBooleanValue2) {
                objF = iVar.f(jVar, C0029m.f1355f);
            }
            iVarH = iVar2.h((h0.i) objF);
        } else {
            iVarH = iVarE.h(iVar);
        }
        D0.d dVar = AbstractC0039x.f1376a;
        return (iVarH == dVar || iVarH.i(h0.e.f745d) != null) ? iVarH : iVarH.h(dVar);
    }

    public static final void f(C0021e c0021e, h0.d dVar, boolean z2) {
        Object obj = C0021e.f1342j.get(c0021e);
        Throwable thE = c0021e.e(obj);
        Object objK = thE != null ? p000a.a.k(thE) : c0021e.f(obj);
        if (!z2) {
            dVar.resumeWith(objK);
            return;
        }
        p0.h.c(dVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTaskKt.resume>");
        C0.h hVar = (C0.h) dVar;
        j0.b bVar = hVar.f61h;
        Object obj2 = hVar.f63j;
        h0.i iVar = bVar.f1072e;
        p0.h.b(iVar);
        Object objK2 = AbstractC0000a.k(iVar, obj2);
        if (objK2 != AbstractC0000a.f51f) {
            h(bVar, iVar);
        }
        try {
            bVar.resumeWith(objK);
        } finally {
            AbstractC0000a.f(iVar, objK2);
        }
    }

    public static final String g(h0.d dVar) {
        Object objK;
        if (dVar instanceof C0.h) {
            return dVar.toString();
        }
        try {
            objK = dVar + '@' + a(dVar);
        } catch (Throwable th) {
            objK = p000a.a.k(th);
        }
        if (f0.d.a(objK) != null) {
            objK = dVar.getClass().getName() + '@' + a(dVar);
        }
        return (String) objK;
    }

    public static final void h(h0.d dVar, h0.i iVar) {
        if ((dVar instanceof j0.c) && iVar.i(b0.f1339d) != null) {
            j0.c cVarC = (j0.c) dVar;
            do {
                cVarC = cVarC.c();
            } while (cVarC != null);
        }
    }
}
