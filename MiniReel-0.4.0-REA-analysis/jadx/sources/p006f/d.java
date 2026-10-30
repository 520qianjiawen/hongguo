package p006f;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import p000a.a;

/* JADX INFO: loaded from: classes.dex */
public final class d extends a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f704i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f705j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f706k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f707l;
    public final AtomicReferenceFieldUpdater m;

    public d(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        super(14);
        this.f704i = atomicReferenceFieldUpdater;
        this.f705j = atomicReferenceFieldUpdater2;
        this.f706k = atomicReferenceFieldUpdater3;
        this.f707l = atomicReferenceFieldUpdater4;
        this.m = atomicReferenceFieldUpdater5;
    }

    @Override // p000a.a
    public final void G(f fVar, f fVar2) {
        this.f705j.lazySet(fVar, fVar2);
    }

    @Override // p000a.a
    public final void H(f fVar, Thread thread) {
        this.f704i.lazySet(fVar, thread);
    }

    @Override // p000a.a
    public final boolean f(g gVar, c cVar) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f707l;
            if (atomicReferenceFieldUpdater.compareAndSet(gVar, cVar, c.f702b)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(gVar) == cVar);
        return false;
    }

    @Override // p000a.a
    public final boolean g(g gVar, Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.m;
            if (atomicReferenceFieldUpdater.compareAndSet(gVar, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(gVar) == obj);
        return false;
    }

    @Override // p000a.a
    public final boolean h(g gVar, f fVar, f fVar2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f706k;
            if (atomicReferenceFieldUpdater.compareAndSet(gVar, fVar, fVar2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(gVar) == fVar);
        return false;
    }
}
