package C0;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import x0.AbstractC0032p;
import x0.AbstractC0034s;
import x0.C0027k;
import x0.C0033q;
import x0.J;
import x0.S;
import x0.Y;
import x0.Z;

/* JADX INFO: renamed from: C0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0000a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final x f46a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final x f47b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final x f48c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final x f49d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final x f50e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final x f51f;

    static {
        int i2 = 0;
        f46a = new x(i2, "NO_DECISION");
        f47b = new x(i2, "CLOSED");
        f48c = new x(i2, "UNDEFINED");
        f49d = new x(i2, "REUSABLE_CLAIMED");
        f50e = new x(i2, "CONDITION_FALSE");
        f51f = new x(i2, "NO_THREAD_ELEMENTS");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [z0.d] */
    /* JADX WARN: Type inference failed for: r8v0, types: [z0.k] */
    /* JADX WARN: Type inference failed for: r8v1, types: [C0.d, C0.v, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final Object a(long j2, z0.k kVar) {
        ?? r1 = z0.d.f1428l;
        while (true) {
            long j3 = kVar.f87c;
            if (j3 >= j2 && !kVar.c()) {
                return kVar;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = d.f54a;
            Object obj = atomicReferenceFieldUpdater.get(kVar);
            x xVar = f47b;
            if (obj == xVar) {
                return xVar;
            }
            v vVar = (v) ((d) obj);
            if (vVar != null) {
                kVar = vVar;
            } else {
                v vVar2 = (v) r1.g(Long.valueOf(j3 + 1), kVar);
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(kVar, null, vVar2)) {
                        if (kVar.c()) {
                            kVar.d();
                        }
                        kVar = vVar2;
                        break;
                    }
                } while (atomicReferenceFieldUpdater.get(kVar) == null);
            }
        }
    }

    public static final v b(Object obj) {
        if (obj == f47b) {
            throw new IllegalStateException("Does not contain segment");
        }
        p0.h.c(obj, "null cannot be cast to non-null type S of kotlinx.coroutines.internal.SegmentOrClosed");
        return (v) obj;
    }

    public static final void c(h0.i iVar, Throwable th) throws IllegalAccessException, InvocationTargetException {
        Throwable runtimeException;
        Iterator it = f.f57a.iterator();
        while (it.hasNext()) {
            try {
                ((y0.b) it.next()).c(th);
            } catch (Throwable th2) {
                if (th == th2) {
                    runtimeException = th;
                } else {
                    runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                    p000a.a.a(runtimeException, th);
                }
                Thread threadCurrentThread = Thread.currentThread();
                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, runtimeException);
            }
        }
        try {
            p000a.a.a(th, new g(iVar));
        } catch (Throwable unused) {
        }
        Thread threadCurrentThread2 = Thread.currentThread();
        threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th);
    }

    public static final boolean d(Object obj) {
        return obj == f47b;
    }

    public static final Object e(Object obj, Object obj2) {
        if (obj == null) {
            return obj2;
        }
        if (obj instanceof ArrayList) {
            ((ArrayList) obj).add(obj2);
            return obj;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(obj2);
        return arrayList;
    }

    public static final void f(h0.i iVar, Object obj) {
        if (obj == f51f) {
            return;
        }
        if (!(obj instanceof C)) {
            Object objF = iVar.f(null, A.f40g);
            p0.h.c(objF, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
            z.f(objF);
            throw null;
        }
        C c2 = (C) obj;
        Y[] yArr = c2.f45b;
        int length = yArr.length - 1;
        if (length < 0) {
            return;
        }
        Y y2 = yArr[length];
        p0.h.b(null);
        Object obj2 = c2.f44a[length];
        throw null;
    }

    public static final void g(h0.d dVar, Object obj) {
        if (!(dVar instanceof h)) {
            dVar.resumeWith(obj);
            return;
        }
        h hVar = (h) dVar;
        AbstractC0032p abstractC0032p = hVar.f60g;
        Throwable thA = f0.d.a(obj);
        Object c0027k = thA == null ? obj : new C0027k(thA, false);
        j0.b bVar = hVar.f61h;
        bVar.getContext();
        h0.i iVar = bVar.f1072e;
        if (abstractC0032p.d()) {
            hVar.f62i = c0027k;
            hVar.f1375f = 1;
            p0.h.b(iVar);
            abstractC0032p.c(iVar, hVar);
            return;
        }
        x0.C cA = Z.a();
        if (cA.f1309f >= 4294967296L) {
            hVar.f62i = c0027k;
            hVar.f1375f = 1;
            g0.c cVar = cA.f1311h;
            if (cVar == null) {
                cVar = new g0.c();
                cA.f1311h = cVar;
            }
            cVar.addLast(hVar);
            return;
        }
        cA.k(true);
        try {
            p0.h.b(iVar);
            J j2 = (J) iVar.i(C0033q.f1363e);
            if (j2 == null || j2.b()) {
                Object obj2 = hVar.f63j;
                p0.h.b(iVar);
                Object objK = k(iVar, obj2);
                if (objK != f51f) {
                    AbstractC0034s.h(bVar, iVar);
                }
                try {
                    bVar.resumeWith(obj);
                    f(iVar, objK);
                } catch (Throwable th) {
                    f(iVar, objK);
                    throw th;
                }
            } else {
                CancellationException cancellationExceptionT = ((S) j2).t();
                hVar.b(c0027k, cancellationExceptionT);
                hVar.resumeWith(p000a.a.k(cancellationExceptionT));
            }
            while (cA.l()) {
            }
        } catch (Throwable th2) {
            try {
                hVar.g(th2, null);
            } finally {
                cA.e();
            }
        }
    }

    public static final long h(String str, long j2, long j3, long j4) {
        String property;
        Long lValueOf;
        boolean z2;
        int i2 = y.f91a;
        try {
            property = System.getProperty(str);
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null) {
            return j2;
        }
        int length = property.length();
        if (length != 0) {
            int i3 = 0;
            char cCharAt = property.charAt(0);
            long j5 = -9223372036854775807L;
            if (cCharAt < '0') {
                z2 = true;
                if (length != 1) {
                    if (cCharAt == '+') {
                        z2 = false;
                        i3 = 1;
                    } else if (cCharAt == '-') {
                        j5 = Long.MIN_VALUE;
                        i3 = 1;
                    }
                }
                lValueOf = null;
                break;
            }
            z2 = false;
            long j6 = 0;
            long j7 = -256204778801521550L;
            while (true) {
                if (i3 >= length) {
                    if (!z2) {
                        lValueOf = Long.valueOf(-j6);
                        break;
                    }
                    lValueOf = Long.valueOf(j6);
                    break;
                }
                int iDigit = Character.digit((int) property.charAt(i3), 10);
                if (iDigit >= 0) {
                    if (j6 < j7) {
                        if (j7 == -256204778801521550L) {
                            j7 = j5 / ((long) 10);
                            if (j6 < j7) {
                            }
                        }
                    }
                    long j8 = j6 * ((long) 10);
                    long j9 = iDigit;
                    if (j8 >= j5 + j9) {
                        j6 = j8 - j9;
                        i3++;
                        length = length;
                    }
                }
                lValueOf = null;
                break;
            }
        }
        lValueOf = null;
        break;
        if (lValueOf == null) {
            throw new IllegalStateException(("System property '" + str + "' has unrecognized value '" + property + '\'').toString());
        }
        long jLongValue = lValueOf.longValue();
        if (j3 <= jLongValue && jLongValue <= j4) {
            return jLongValue;
        }
        throw new IllegalStateException(("System property '" + str + "' should be in range " + j3 + ".." + j4 + ", but is '" + jLongValue + '\'').toString());
    }

    public static int i(String str, int i2, int i3) {
        return (int) h(str, i2, 1, (i3 & 8) != 0 ? Integer.MAX_VALUE : 2097150);
    }

    public static final Object j(h0.i iVar) {
        Object objF = iVar.f(0, A.f39f);
        p0.h.b(objF);
        return objF;
    }

    public static final Object k(h0.i iVar, Object obj) {
        if (obj == null) {
            obj = j(iVar);
        }
        if (obj == 0) {
            return f51f;
        }
        if (obj instanceof Integer) {
            return iVar.f(new C(((Number) obj).intValue(), iVar), A.f41h);
        }
        z.f(obj);
        throw null;
    }
}
