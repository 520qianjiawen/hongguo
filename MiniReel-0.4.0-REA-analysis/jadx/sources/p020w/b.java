package p020w;

import x.a;

/* JADX INFO: loaded from: classes.dex */
public final class b implements h {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b f1274f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final b f1275g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final b f1276h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final b f1277i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final b f1278j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final b f1279k;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1280d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f1281e;

    static {
        int i2 = 0;
        f1274f = new b("NONE", i2);
        f1275g = new b("FULL", i2);
        int i3 = 1;
        f1276h = new b("FLAT", i3);
        f1277i = new b("HALF_OPENED", i3);
        int i4 = 2;
        f1278j = new b("FOLD", i4);
        f1279k = new b("HINGE", i4);
    }

    public /* synthetic */ b(String str, int i2) {
        this.f1280d = i2;
        this.f1281e = str;
    }

    public String toString() {
        switch (this.f1280d) {
            case 0:
                return (String) this.f1281e;
            case 1:
                return (String) this.f1281e;
            case 2:
                return (String) this.f1281e;
            default:
                return super.toString();
        }
    }

    public b(a aVar) {
        this.f1280d = 3;
        int i2 = n.f1305b;
        this.f1281e = aVar;
    }
}
