package h0;

import java.io.Serializable;
import o0.p;

/* JADX INFO: loaded from: classes.dex */
public final class c implements i, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i f743d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g f744e;

    public c(g gVar, i iVar) {
        p0.h.e(iVar, "left");
        this.f743d = iVar;
        this.f744e = gVar;
    }

    public final boolean equals(Object obj) {
        boolean zA;
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            cVar.getClass();
            int i2 = 2;
            c cVar2 = cVar;
            int i3 = 2;
            while (true) {
                i iVar = cVar2.f743d;
                cVar2 = iVar instanceof c ? (c) iVar : null;
                if (cVar2 == null) {
                    break;
                }
                i3++;
            }
            c cVar3 = this;
            while (true) {
                i iVar2 = cVar3.f743d;
                cVar3 = iVar2 instanceof c ? (c) iVar2 : null;
                if (cVar3 == null) {
                    break;
                }
                i2++;
            }
            if (i3 == i2) {
                c cVar4 = this;
                while (true) {
                    g gVar = cVar4.f744e;
                    if (!p0.h.a(cVar.i(gVar.getKey()), gVar)) {
                        zA = false;
                        break;
                    }
                    i iVar3 = cVar4.f743d;
                    if (!(iVar3 instanceof c)) {
                        p0.h.c(iVar3, "null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
                        g gVar2 = (g) iVar3;
                        zA = p0.h.a(cVar.i(gVar2.getKey()), gVar2);
                        break;
                    }
                    cVar4 = (c) iVar3;
                }
                if (zA) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // h0.i
    public final Object f(Object obj, p pVar) {
        return pVar.g(this.f743d.f(obj, pVar), this.f744e);
    }

    @Override // h0.i
    public final i g(h hVar) {
        p0.h.e(hVar, "key");
        g gVar = this.f744e;
        g gVarI = gVar.i(hVar);
        i iVar = this.f743d;
        if (gVarI != null) {
            return iVar;
        }
        i iVarG = iVar.g(hVar);
        if (iVarG == iVar) {
            return this;
        }
        return iVarG == j.f746d ? gVar : new c(gVar, iVarG);
    }

    @Override // h0.i
    public final /* bridge */ i h(i iVar) {
        return p000a.a.F(this, iVar);
    }

    public final int hashCode() {
        return this.f744e.hashCode() + this.f743d.hashCode();
    }

    @Override // h0.i
    public final g i(h hVar) {
        p0.h.e(hVar, "key");
        c cVar = this;
        while (true) {
            g gVarI = cVar.f744e.i(hVar);
            if (gVarI != null) {
                return gVarI;
            }
            i iVar = cVar.f743d;
            if (!(iVar instanceof c)) {
                return iVar.i(hVar);
            }
            cVar = (c) iVar;
        }
    }

    public final String toString() {
        return "[" + ((String) f("", new b(0))) + ']';
    }
}
