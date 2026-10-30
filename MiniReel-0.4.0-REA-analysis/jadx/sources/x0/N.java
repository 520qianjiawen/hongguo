package x0;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class N extends C0.l implements InterfaceC0040y, G, o0.l {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public S f1319g;

    @Override // x0.G
    public final boolean b() {
        return true;
    }

    @Override // x0.InterfaceC0040y
    public final void c() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        S sN = n();
        while (true) {
            Object objW = sN.w();
            if (objW instanceof N) {
                if (objW != this) {
                    return;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = S.f1332d;
                C0041z c0041z = AbstractC0034s.f1372i;
                while (!atomicReferenceFieldUpdater2.compareAndSet(sN, objW, c0041z)) {
                    if (atomicReferenceFieldUpdater2.get(sN) != objW) {
                    }
                }
                return;
            }
            if (!(objW instanceof G) || ((G) objW).f() == null) {
                return;
            }
            while (true) {
                Object objK = k();
                if (objK instanceof C0.s) {
                    C0.l lVar = ((C0.s) objK).f84a;
                    return;
                }
                if (objK == this) {
                    return;
                }
                p0.h.c(objK, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
                C0.l lVar2 = (C0.l) objK;
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3 = C0.l.f73f;
                C0.s sVar = (C0.s) atomicReferenceFieldUpdater3.get(lVar2);
                if (sVar == null) {
                    sVar = new C0.s(lVar2);
                    atomicReferenceFieldUpdater3.lazySet(lVar2, sVar);
                }
                do {
                    atomicReferenceFieldUpdater = C0.l.f71d;
                    if (atomicReferenceFieldUpdater.compareAndSet(this, objK, sVar)) {
                        lVar2.i();
                        return;
                    }
                } while (atomicReferenceFieldUpdater.get(this) == objK);
            }
        }
    }

    @Override // x0.G
    public final T f() {
        return null;
    }

    public final S n() {
        S s2 = this.f1319g;
        if (s2 != null) {
            return s2;
        }
        p0.h.g("job");
        throw null;
    }

    public abstract void o(Throwable th);

    @Override // C0.l
    public final String toString() {
        return getClass().getSimpleName() + '@' + AbstractC0034s.a(this) + "[job@" + AbstractC0034s.a(n()) + ']';
    }
}
