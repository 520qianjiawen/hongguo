package h0;

import o0.p;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final h f741d;

    public a(h hVar) {
        this.f741d = hVar;
    }

    @Override // h0.i
    public final Object f(Object obj, p pVar) {
        return pVar.g(obj, this);
    }

    @Override // h0.i
    public /* bridge */ i g(h hVar) {
        return p000a.a.C(this, hVar);
    }

    @Override // h0.g
    public final h getKey() {
        return this.f741d;
    }

    @Override // h0.i
    public final /* bridge */ i h(i iVar) {
        return p000a.a.E(this, iVar);
    }

    @Override // h0.i
    public g i(h hVar) {
        p0.h.e(hVar, "key");
        if (p0.h.a(getKey(), hVar)) {
            return this;
        }
        return null;
    }
}
