package C0;

import x0.AbstractC0017a;
import x0.C0027k;

/* JADX INFO: loaded from: classes.dex */
public class u extends AbstractC0017a implements j0.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final j0.f f85g;

    public u(h0.i iVar, j0.f fVar) {
        super(iVar, true);
        this.f85g = fVar;
    }

    @Override // j0.c
    public final j0.c c() {
        j0.f fVar = this.f85g;
        if (fVar != null) {
            return fVar;
        }
        return null;
    }

    @Override // x0.S
    public final void k(Object obj) {
        h0.d dVarY = p000a.a.y(this.f85g);
        if (obj instanceof C0027k) {
            obj = p000a.a.k(((C0027k) obj).f1354a);
        }
        AbstractC0000a.g(dVarY, obj);
    }

    @Override // x0.S
    public final void l(Object obj) {
        if (obj instanceof C0027k) {
            obj = p000a.a.k(((C0027k) obj).f1354a);
        }
        this.f85g.resumeWith(obj);
    }
}
