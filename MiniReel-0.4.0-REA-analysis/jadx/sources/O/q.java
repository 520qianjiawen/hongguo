package O;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class q implements p011k.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f345a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f346b;

    public /* synthetic */ q(int i2, Object obj) {
        this.f345a = i2;
        this.f346b = obj;
    }

    @Override // p011k.a
    public final void accept(Object obj) {
        switch (this.f345a) {
            case 0:
                ((t) this.f346b).setWindowInfoListenerDisplayFeatures((p020w.k) obj);
                break;
            default:
                ((z0.q) ((z0.r) this.f346b)).d((p020w.k) obj);
                break;
        }
    }
}
