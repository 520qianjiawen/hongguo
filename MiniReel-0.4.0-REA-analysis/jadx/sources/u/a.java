package u;

import A0.c;
import f0.g;
import h0.d;
import j0.f;
import o0.p;
import x0.r;

/* JADX INFO: loaded from: classes.dex */
public final class a extends f implements p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1267h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ c f1268i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ p011k.a f1269j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(c cVar, p011k.a aVar, d dVar) {
        super(dVar);
        this.f1268i = cVar;
        this.f1269j = aVar;
    }

    @Override // j0.b
    public final d b(d dVar, Object obj) {
        return new a(this.f1268i, this.f1269j, dVar);
    }

    @Override // j0.b
    public final Object e(Object obj) throws Throwable {
        int i2 = this.f1267h;
        if (i2 == 0) {
            p000a.a.P(obj);
            p002b0.a aVar = new p002b0.a(7, this.f1269j);
            this.f1267h = 1;
            Object objB = this.f1268i.b(aVar, this);
            i0.a aVar2 = i0.a.f747d;
            if (objB == aVar2) {
                return aVar2;
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
        return ((a) b((d) obj2, (r) obj)).e(g.f725a);
    }
}
