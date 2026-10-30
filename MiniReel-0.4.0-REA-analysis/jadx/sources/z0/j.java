package z0;

import x0.C0021e;
import x0.C0033q;

/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f1451a = new i();

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object a(r rVar, p020w.i iVar, j0.b bVar) throws Throwable {
        o oVar;
        if (bVar instanceof o) {
            oVar = (o) bVar;
            int i2 = oVar.f1457i;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                oVar.f1457i = i2 - Integer.MIN_VALUE;
            } else {
                oVar = new o(bVar);
            }
        } else {
            oVar = new o(bVar);
        }
        Object obj = oVar.f1456h;
        int i3 = oVar.f1457i;
        try {
            if (i3 == 0) {
                p000a.a.P(obj);
                h0.i iVar2 = oVar.f1072e;
                p0.h.b(iVar2);
                if (iVar2.i(C0033q.f1363e) != rVar) {
                    throw new IllegalStateException("awaitClose() can only be invoked from the producer context");
                }
                oVar.f1455g = iVar;
                oVar.f1457i = 1;
                C0021e c0021e = new C0021e(p000a.a.y(oVar), 1);
                c0021e.o();
                ((q) rVar).L(new p(c0021e));
                Object objN = c0021e.n();
                i0.a aVar = i0.a.f747d;
                if (objN == aVar) {
                    return aVar;
                }
            } else {
                if (i3 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                iVar = oVar.f1455g;
                p000a.a.P(obj);
            }
            iVar.a();
            return f0.g.f725a;
        } catch (Throwable th) {
            iVar.a();
            throw th;
        }
    }
}
