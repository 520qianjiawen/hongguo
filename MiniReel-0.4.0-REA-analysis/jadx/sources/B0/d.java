package B0;

import f0.g;
import j0.f;
import o0.p;
import z0.r;

/* JADX INFO: loaded from: classes.dex */
public final class d extends f implements p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f35h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f36i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ A0.b f37j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(A0.b bVar, h0.d dVar) {
        super(dVar);
        this.f37j = bVar;
    }

    @Override // j0.b
    public final h0.d b(h0.d dVar, Object obj) {
        d dVar2 = new d(this.f37j, dVar);
        dVar2.f36i = obj;
        return dVar2;
    }

    @Override // j0.b
    public final Object e(Object obj) throws Throwable {
        int i2 = this.f35h;
        if (i2 == 0) {
            p000a.a.P(obj);
            r rVar = (r) this.f36i;
            this.f35h = 1;
            Object objA = this.f37j.a(rVar, this);
            i0.a aVar = i0.a.f747d;
            if (objA == aVar) {
                return aVar;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            p000a.a.P(obj);
        }
        return g.f725a;
    }

    @Override // o0.p
    public final Object g(Object obj, Object obj2) {
        return ((d) b((h0.d) obj2, (r) obj)).e(g.f725a);
    }
}
