package x0;

/* JADX INFO: renamed from: x0.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0036u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f1374a = 0;

    static {
        String property;
        int i2 = C0.y.f91a;
        try {
            property = System.getProperty("kotlinx.coroutines.main.delay");
        } catch (SecurityException unused) {
            property = null;
        }
        if (!(property != null ? Boolean.parseBoolean(property) : false)) {
            RunnableC0035t runnableC0035t = RunnableC0035t.f1373l;
            return;
        }
        D0.d dVar = AbstractC0039x.f1376a;
        y0.c cVar = C0.p.f83a;
        y0.c cVar2 = cVar.f1395h;
        if (cVar == null) {
            RunnableC0035t runnableC0035t2 = RunnableC0035t.f1373l;
        }
    }
}
