package x0;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class I extends L {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f1315i = AtomicIntegerFieldUpdater.newUpdater(I.class, "_invoked");
    private volatile int _invoked;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final o0.l f1316h;

    public I(o0.l lVar) {
        this.f1316h = lVar;
    }

    @Override // o0.l
    public final /* bridge */ /* synthetic */ Object h(Object obj) {
        o((Throwable) obj);
        return f0.g.f725a;
    }

    @Override // x0.N
    public final void o(Throwable th) {
        if (f1315i.compareAndSet(this, 0, 1)) {
            this.f1316h.h(th);
        }
    }
}
