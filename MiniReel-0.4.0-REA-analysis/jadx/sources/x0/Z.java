package x0;

/* JADX INFO: loaded from: classes.dex */
public abstract class Z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f1335a = new ThreadLocal();

    public static C a() {
        ThreadLocal threadLocal = f1335a;
        C c2 = (C) threadLocal.get();
        if (c2 != null) {
            return c2;
        }
        C0019c c0019c = new C0019c(Thread.currentThread());
        threadLocal.set(c0019c);
        return c0019c;
    }
}
