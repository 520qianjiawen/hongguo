package C0;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import x0.AbstractC0032p;
import x0.AbstractC0036u;
import x0.InterfaceC0037v;

/* JADX INFO: loaded from: classes.dex */
public final class j extends AbstractC0032p implements InterfaceC0037v {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f66j = AtomicIntegerFieldUpdater.newUpdater(j.class, "runningWorkers");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AbstractC0032p f67f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f68g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final m f69h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f70i;
    private volatile int runningWorkers;

    /* JADX WARN: Multi-variable type inference failed */
    public j(AbstractC0032p abstractC0032p, int i2) {
        this.f67f = abstractC0032p;
        this.f68g = i2;
        if ((abstractC0032p instanceof InterfaceC0037v ? (InterfaceC0037v) abstractC0032p : null) == null) {
            int i3 = AbstractC0036u.f1374a;
        }
        this.f69h = new m();
        this.f70i = new Object();
    }

    @Override // x0.AbstractC0032p
    public final void c(h0.i iVar, Runnable runnable) {
        this.f69h.a(runnable);
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f66j;
        if (atomicIntegerFieldUpdater.get(this) < this.f68g) {
            synchronized (this.f70i) {
                if (atomicIntegerFieldUpdater.get(this) >= this.f68g) {
                    return;
                }
                atomicIntegerFieldUpdater.incrementAndGet(this);
                Runnable runnableE = e();
                if (runnableE == null) {
                    return;
                }
                this.f67f.c(this, new i(this, runnableE));
            }
        }
    }

    public final Runnable e() {
        while (true) {
            Runnable runnable = (Runnable) this.f69h.d();
            if (runnable != null) {
                return runnable;
            }
            synchronized (this.f70i) {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f66j;
                atomicIntegerFieldUpdater.decrementAndGet(this);
                if (this.f69h.c() == 0) {
                    return null;
                }
                atomicIntegerFieldUpdater.incrementAndGet(this);
            }
        }
    }
}
