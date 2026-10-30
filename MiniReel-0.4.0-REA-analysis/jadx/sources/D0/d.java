package D0;

/* JADX INFO: loaded from: classes.dex */
public final class d extends g {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final d f128g;

    static {
        int i2 = k.f137c;
        int i3 = k.f138d;
        long j2 = k.f139e;
        String str = k.f135a;
        d dVar = new d();
        dVar.f130f = new b(i2, i3, j2, str);
        f128g = dVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    @Override // x0.AbstractC0032p
    public final String toString() {
        return "Dispatchers.Default";
    }
}
