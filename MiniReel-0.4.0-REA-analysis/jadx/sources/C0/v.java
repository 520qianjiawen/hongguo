package C0;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import x0.V;

/* JADX INFO: loaded from: classes.dex */
public abstract class v extends d implements V {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f86d = AtomicIntegerFieldUpdater.newUpdater(v.class, "cleanedAndPointers");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f87c;
    private volatile int cleanedAndPointers;

    public v(long j2, z0.k kVar, int i2) {
        super(kVar);
        this.f87c = j2;
        this.cleanedAndPointers = i2 << 16;
    }

    @Override // C0.d
    public final boolean c() {
        return f86d.get(this) == f() && b() != null;
    }

    public final boolean e() {
        return f86d.addAndGet(this, -65536) == f() && b() != null;
    }

    public abstract int f();

    public abstract void g(int i2, h0.i iVar);

    public final void h() {
        if (f86d.incrementAndGet(this) == z0.e.f1430b) {
            d();
        }
    }

    public final boolean i() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i2;
        do {
            atomicIntegerFieldUpdater = f86d;
            i2 = atomicIntegerFieldUpdater.get(this);
            if (i2 == f() && b() != null) {
                return false;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, 65536 + i2));
        return true;
    }
}
