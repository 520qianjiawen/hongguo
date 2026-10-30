package C0;

/* JADX INFO: loaded from: classes.dex */
public final class A extends p0.i implements o0.p {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final A f39f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final A f40g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final A f41h;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f42e;

    static {
        int i2 = 2;
        f39f = new A(i2, 0);
        f40g = new A(i2, 1);
        f41h = new A(i2, 2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ A(int i2, int i3) {
        super(i2);
        this.f42e = i3;
    }

    @Override // o0.p
    public final Object g(Object obj, Object obj2) {
        switch (this.f42e) {
            case 0:
                return obj;
            case 1:
                z.f(obj);
                return null;
            default:
                return (C) obj;
        }
    }
}
