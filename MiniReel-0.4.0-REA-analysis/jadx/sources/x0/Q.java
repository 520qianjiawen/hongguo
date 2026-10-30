package x0;

import C0.AbstractC0000a;
import C0.AbstractC0001b;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class Q extends AbstractC0001b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final N f1328b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public T f1329c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ S f1330d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f1331e;

    public Q(N n2, S s2, Object obj) {
        this.f1330d = s2;
        this.f1331e = obj;
        this.f1328b = n2;
    }

    @Override // C0.AbstractC0001b
    public final void b(Object obj, Object obj2) {
        C0.l lVar = (C0.l) obj;
        boolean z2 = obj2 == null;
        C0.l lVar2 = this.f1328b;
        C0.l lVar3 = z2 ? lVar2 : this.f1329c;
        if (lVar3 != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = C0.l.f71d;
            while (!atomicReferenceFieldUpdater.compareAndSet(lVar, this, lVar3)) {
                if (atomicReferenceFieldUpdater.get(lVar) != this) {
                    return;
                }
            }
            if (z2) {
                C0.l lVar4 = this.f1329c;
                p0.h.b(lVar4);
                lVar2.j(lVar4);
            }
        }
    }

    @Override // C0.AbstractC0001b
    public final C0.x c(Object obj) {
        if (this.f1330d.w() == this.f1331e) {
            return null;
        }
        return AbstractC0000a.f50e;
    }
}
