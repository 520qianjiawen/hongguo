package x0;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: renamed from: x0.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0027k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f1353b = AtomicIntegerFieldUpdater.newUpdater(C0027k.class, "_handled");
    private volatile int _handled;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Throwable f1354a;

    public C0027k(Throwable th, boolean z2) {
        this.f1354a = th;
        this._handled = z2 ? 1 : 0;
    }

    public final String toString() {
        return getClass().getSimpleName() + '[' + this.f1354a + ']';
    }
}
