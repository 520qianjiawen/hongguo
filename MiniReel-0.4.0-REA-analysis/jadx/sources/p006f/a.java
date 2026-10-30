package p006f;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f699b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f700c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Throwable f701a;

    static {
        if (g.f711g) {
            f700c = null;
            f699b = null;
        } else {
            f700c = new a(null, false);
            f699b = new a(null, true);
        }
    }

    public a(Throwable th, boolean z2) {
        this.f701a = th;
    }
}
