package A0;

import C0.u;
import java.util.ArrayList;
import p0.p;
import p020w.j;
import x0.AbstractC0034s;
import x0.C0027k;
import x0.G;
import x0.H;
import z0.q;
import z0.r;

/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final h0.i f5d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f6e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f7f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final j f8g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final j f9h;

    public b(j jVar, h0.i iVar, int i2, int i3) {
        this.f5d = iVar;
        this.f6e = i2;
        this.f7f = i3;
        this.f8g = jVar;
        this.f9h = jVar;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object a(r rVar, h0.d dVar) throws Throwable {
        a aVar;
        if (dVar instanceof a) {
            aVar = (a) dVar;
            int i2 = aVar.f4j;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                aVar.f4j = i2 - Integer.MIN_VALUE;
            } else {
                aVar = new a(this, (j0.b) dVar);
            }
        } else {
            aVar = new a(this, (j0.b) dVar);
        }
        Object obj = aVar.f2h;
        int i3 = aVar.f4j;
        f0.g gVar = f0.g.f725a;
        if (i3 == 0) {
            p000a.a.P(obj);
            aVar.f1g = rVar;
            aVar.f4j = 1;
            Object objG = this.f8g.g(rVar, aVar);
            i0.a aVar2 = i0.a.f747d;
            if (objG != aVar2) {
                objG = gVar;
            }
            if (objG == aVar2) {
                return aVar2;
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            rVar = aVar.f1g;
            p000a.a.P(obj);
        }
        if (((q) rVar).f1459g.p()) {
            return gVar;
        }
        throw new IllegalStateException("'awaitClose { yourCallbackOrListener.cancel() }' should be used in the end of callbackFlow block.\nOtherwise, a callback/listener may leak in case of external cancellation.\nSee callbackFlow API documentation for the details.");
    }

    @Override // A0.c
    public Object b(d dVar, h0.d dVar2) throws Throwable {
        Object c0027k;
        Object objA;
        G g2;
        B0.c cVar = new B0.c(dVar, this, null);
        h0.i iVar = ((j0.b) dVar2).f1072e;
        p0.h.b(iVar);
        u uVar = new u(iVar, (j0.f) dVar2);
        try {
            p.a(2, cVar);
            c0027k = cVar.g(uVar, uVar);
        } catch (Throwable th) {
            c0027k = new C0027k(th, false);
        }
        i0.a aVar = i0.a.f747d;
        if (c0027k == aVar || (objA = uVar.A(c0027k)) == AbstractC0034s.f1367d) {
            objA = aVar;
        } else {
            if (objA instanceof C0027k) {
                throw ((C0027k) objA).f1354a;
            }
            H h2 = objA instanceof H ? (H) objA : null;
            if (h2 != null && (g2 = h2.f1314a) != null) {
                objA = g2;
            }
        }
        return objA == aVar ? objA : f0.g.f725a;
    }

    public final String c() {
        String str;
        ArrayList arrayList = new ArrayList(4);
        h0.j jVar = h0.j.f746d;
        h0.i iVar = this.f5d;
        if (iVar != jVar) {
            arrayList.add("context=" + iVar);
        }
        int i2 = this.f6e;
        if (i2 != -3) {
            arrayList.add("capacity=" + i2);
        }
        int i3 = this.f7f;
        if (i3 != 1) {
            if (i3 == 1) {
                str = "SUSPEND";
            } else if (i3 != 2) {
                str = i3 != 3 ? "null" : "DROP_LATEST";
            } else {
                str = "DROP_OLDEST";
            }
            arrayList.add("onBufferOverflow=".concat(str));
        }
        return getClass().getSimpleName() + '[' + g0.e.U(arrayList, null, null, null, 62) + ']';
    }

    public final String toString() {
        return "block[" + this.f8g + "] -> " + c();
    }
}
