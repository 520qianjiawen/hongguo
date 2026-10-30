package C0;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f54a = AtomicReferenceFieldUpdater.newUpdater(d.class, Object.class, "_next");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f55b = AtomicReferenceFieldUpdater.newUpdater(d.class, Object.class, "_prev");
    private volatile Object _next;
    private volatile Object _prev;

    public d(z0.k kVar) {
        this._prev = kVar;
    }

    public final void a() {
        f55b.lazySet(this, null);
    }

    public final d b() {
        Object obj = f54a.get(this);
        if (obj == AbstractC0000a.f47b) {
            return null;
        }
        return (d) obj;
    }

    public abstract boolean c();

    public final void d() {
        d dVarB;
        if (b() == null) {
            return;
        }
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f55b;
            d dVar = (d) atomicReferenceFieldUpdater.get(this);
            while (dVar != null && dVar.c()) {
                dVar = (d) atomicReferenceFieldUpdater.get(dVar);
            }
            d dVarB2 = b();
            p0.h.b(dVarB2);
            while (dVarB2.c() && (dVarB = dVarB2.b()) != null) {
                dVarB2 = dVarB;
            }
            while (true) {
                Object obj = atomicReferenceFieldUpdater.get(dVarB2);
                d dVar2 = ((d) obj) == null ? null : dVar;
                while (true) {
                    if (atomicReferenceFieldUpdater.compareAndSet(dVarB2, obj, dVar2)) {
                        break;
                    } else if (atomicReferenceFieldUpdater.get(dVarB2) != obj) {
                    }
                }
            }
            if (dVar != null) {
                f54a.set(dVar, dVarB2);
            }
            if (!dVarB2.c() || dVarB2.b() == null) {
                if (dVar == null || !dVar.c()) {
                    return;
                }
            }
        }
    }
}
