package C0;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import x0.AbstractC0034s;

/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f71d = AtomicReferenceFieldUpdater.newUpdater(l.class, Object.class, "_next");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f72e = AtomicReferenceFieldUpdater.newUpdater(l.class, Object.class, "_prev");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f73f = AtomicReferenceFieldUpdater.newUpdater(l.class, Object.class, "_removedRef");
    private volatile Object _next = this;
    private volatile Object _prev = this;
    private volatile Object _removedRef;

    public final l i() {
        l lVar;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Object obj;
        loop0: while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f72e;
            l lVar2 = (l) atomicReferenceFieldUpdater2.get(this);
            lVar = lVar2;
            while (true) {
                l lVar3 = null;
                while (true) {
                    atomicReferenceFieldUpdater = f71d;
                    obj = atomicReferenceFieldUpdater.get(lVar);
                    if (obj == this) {
                        if (lVar2 != lVar) {
                            while (!atomicReferenceFieldUpdater2.compareAndSet(this, lVar2, lVar)) {
                                if (atomicReferenceFieldUpdater2.get(this) != lVar2) {
                                    break;
                                }
                            }
                            break loop0;
                        }
                        break;
                    }
                    if (m()) {
                        return null;
                    }
                    if (obj == null) {
                        break loop0;
                    }
                    if (obj instanceof r) {
                        ((r) obj).a(lVar);
                        break;
                    }
                    if (!(obj instanceof s)) {
                        p0.h.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
                        lVar3 = lVar;
                        lVar = (l) obj;
                    } else {
                        if (lVar3 != null) {
                            break;
                        }
                        lVar = (l) atomicReferenceFieldUpdater2.get(lVar);
                    }
                }
                l lVar4 = ((s) obj).f84a;
                while (!atomicReferenceFieldUpdater.compareAndSet(lVar3, lVar, lVar4)) {
                    if (atomicReferenceFieldUpdater.get(lVar3) != lVar) {
                        break;
                    }
                }
                lVar = lVar3;
            }
        }
        return lVar;
    }

    public final void j(l lVar) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f72e;
            l lVar2 = (l) atomicReferenceFieldUpdater.get(lVar);
            if (k() != lVar) {
                return;
            }
            do {
                if (atomicReferenceFieldUpdater.compareAndSet(lVar, lVar2, this)) {
                    if (m()) {
                        lVar.i();
                        return;
                    }
                    return;
                }
            } while (atomicReferenceFieldUpdater.get(lVar) == lVar2);
        }
    }

    public final Object k() {
        while (true) {
            Object obj = f71d.get(this);
            if (!(obj instanceof r)) {
                return obj;
            }
            ((r) obj).a(this);
        }
    }

    public final l l() {
        l lVar;
        Object objK = k();
        s sVar = objK instanceof s ? (s) objK : null;
        if (sVar != null && (lVar = sVar.f84a) != null) {
            return lVar;
        }
        p0.h.c(objK, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        return (l) objK;
    }

    public boolean m() {
        return k() instanceof s;
    }

    public String toString() {
        return new k(this, AbstractC0034s.class, "classSimpleName", "getClassSimpleName(Ljava/lang/Object;)Ljava/lang/String;", 1) + '@' + AbstractC0034s.a(this);
    }
}
