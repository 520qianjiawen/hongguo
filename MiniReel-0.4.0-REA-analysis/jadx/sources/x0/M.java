package x0;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class M extends S {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f1318f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public M() {
        super(true);
        boolean z2 = true;
        z(null);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = S.f1333e;
        InterfaceC0024h interfaceC0024h = (InterfaceC0024h) atomicReferenceFieldUpdater.get(this);
        C0025i c0025i = interfaceC0024h instanceof C0025i ? (C0025i) interfaceC0024h : null;
        if (c0025i == null) {
            z2 = false;
            break;
        }
        S sN = c0025i.n();
        while (!sN.u()) {
            InterfaceC0024h interfaceC0024h2 = (InterfaceC0024h) atomicReferenceFieldUpdater.get(sN);
            C0025i c0025i2 = interfaceC0024h2 instanceof C0025i ? (C0025i) interfaceC0024h2 : null;
            if (c0025i2 == null) {
                z2 = false;
                break;
            }
            sN = c0025i2.n();
        }
        this.f1318f = z2;
    }

    @Override // x0.S
    public final boolean u() {
        return this.f1318f;
    }
}
