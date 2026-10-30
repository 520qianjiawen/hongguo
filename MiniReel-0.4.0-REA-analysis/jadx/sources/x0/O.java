package x0;

/* JADX INFO: loaded from: classes.dex */
public final class O extends N {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final S f1320h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final P f1321i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final C0025i f1322j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Object f1323k;

    public O(S s2, P p2, C0025i c0025i, Object obj) {
        this.f1320h = s2;
        this.f1321i = p2;
        this.f1322j = c0025i;
        this.f1323k = obj;
    }

    @Override // o0.l
    public final /* bridge */ /* synthetic */ Object h(Object obj) {
        o((Throwable) obj);
        return f0.g.f725a;
    }

    @Override // x0.N
    public final void o(Throwable th) {
        C0025i c0025iB = S.B(this.f1322j);
        S s2 = this.f1320h;
        P p2 = this.f1321i;
        Object obj = this.f1323k;
        if (c0025iB != null) {
            while (AbstractC0034s.d(c0025iB.f1348h, new O(s2, p2, c0025iB, obj), 1) == U.f1334d) {
                c0025iB = S.B(c0025iB);
                if (c0025iB == null) {
                }
            }
            return;
        }
        s2.k(s2.s(p2, obj));
    }
}
