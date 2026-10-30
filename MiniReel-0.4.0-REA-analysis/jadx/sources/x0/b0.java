package x0;

/* JADX INFO: loaded from: classes.dex */
public final class b0 implements h0.g, h0.h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b0 f1339d = new b0();

    @Override // h0.i
    public final Object f(Object obj, o0.p pVar) {
        return pVar.g(obj, this);
    }

    @Override // h0.i
    public final h0.i g(h0.h hVar) {
        return p000a.a.C(this, hVar);
    }

    @Override // h0.i
    public final h0.i h(h0.i iVar) {
        return p000a.a.E(this, iVar);
    }

    @Override // h0.i
    public final h0.g i(h0.h hVar) {
        p0.h.e(hVar, "key");
        if (p0.h.a(this, hVar)) {
            return this;
        }
        return null;
    }

    @Override // h0.g
    public final h0.h getKey() {
        return this;
    }
}
