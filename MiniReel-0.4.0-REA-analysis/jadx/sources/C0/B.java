package C0;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public class B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f43a = AtomicIntegerFieldUpdater.newUpdater(B.class, "_size");
    private volatile int _size;
}
