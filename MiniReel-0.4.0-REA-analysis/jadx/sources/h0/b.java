package h0;

import o0.p;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f742d;

    public /* synthetic */ b(int i2) {
        this.f742d = i2;
    }

    @Override // o0.p
    public final Object g(Object obj, Object obj2) {
        c cVar;
        switch (this.f742d) {
            case 0:
                String str = (String) obj;
                g gVar = (g) obj2;
                p0.h.e(str, "acc");
                if (str.length() == 0) {
                    return gVar.toString();
                }
                return str + ", " + gVar;
            default:
                i iVar = (i) obj;
                g gVar2 = (g) obj2;
                p0.h.e(iVar, "acc");
                i iVarG = iVar.g(gVar2.getKey());
                j jVar = j.f746d;
                if (iVarG == jVar) {
                    return gVar2;
                }
                e eVar = e.f745d;
                f fVar = (f) iVarG.i(eVar);
                if (fVar == null) {
                    cVar = new c(gVar2, iVarG);
                } else {
                    i iVarG2 = iVarG.g(eVar);
                    if (iVarG2 == jVar) {
                        return new c(fVar, gVar2);
                    }
                    cVar = new c(fVar, new c(gVar2, iVarG2));
                }
                return cVar;
        }
    }
}
