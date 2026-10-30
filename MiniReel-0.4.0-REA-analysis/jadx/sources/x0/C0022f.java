package x0;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: renamed from: x0.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0022f extends C0027k {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f1346c = AtomicIntegerFieldUpdater.newUpdater(C0022f.class, "_resumed");
    private volatile int _resumed;

    public C0022f(C0021e c0021e, Throwable th, boolean z2) {
        super(th, z2);
        this._resumed = 0;
    }
}
