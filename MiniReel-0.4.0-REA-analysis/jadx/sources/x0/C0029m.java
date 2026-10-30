package x0;

/* JADX INFO: renamed from: x0.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0029m extends p0.i implements o0.p {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C0029m f1355f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C0029m f1356g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1357e;

    static {
        int i2 = 2;
        f1355f = new C0029m(i2, 0);
        f1356g = new C0029m(i2, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0029m(int i2, int i3) {
        super(i2);
        this.f1357e = i3;
    }

    @Override // o0.p
    public final Object g(Object obj, Object obj2) {
        switch (this.f1357e) {
            case 0:
                return ((h0.i) obj).h((h0.g) obj2);
            case 1:
                Boolean bool = (Boolean) obj;
                bool.getClass();
                return bool;
            default:
                return ((h0.i) obj).h((h0.g) obj2);
        }
    }
}
