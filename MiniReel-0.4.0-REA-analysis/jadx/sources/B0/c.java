package B0;

import A0.h;
import f0.g;
import h0.i;
import j0.f;
import o0.p;
import x0.AbstractC0034s;
import x0.r;
import z0.n;
import z0.q;

/* JADX INFO: loaded from: classes.dex */
public final class c extends f implements p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f31h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f32i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ A0.d f33j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ A0.b f34k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(A0.d dVar, A0.b bVar, h0.d dVar2) {
        super(dVar2);
        this.f33j = dVar;
        this.f34k = bVar;
    }

    @Override // j0.b
    public final h0.d b(h0.d dVar, Object obj) {
        c cVar = new c(this.f33j, this.f34k, dVar);
        cVar.f32i = obj;
        return cVar;
    }

    @Override // j0.b
    public final Object e(Object obj) throws Throwable {
        z0.c nVar;
        int i2 = this.f31h;
        g gVar = g.f725a;
        if (i2 != 0) {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            p000a.a.P(obj);
            return gVar;
        }
        p000a.a.P(obj);
        r rVar = (r) this.f32i;
        A0.b bVar = this.f34k;
        i iVar = bVar.f5d;
        int i3 = bVar.f6e;
        if (i3 == -3) {
            i3 = -2;
        }
        int i4 = bVar.f7f;
        p dVar = new d(bVar, null);
        if (i3 != -2) {
            if (i3 != -1) {
                if (i3 == 0) {
                    nVar = i4 == 1 ? new z0.c(0) : new n(1, i4);
                } else if (i3 != Integer.MAX_VALUE) {
                    nVar = i4 == 1 ? new z0.c(i3) : new n(i3, i4);
                } else {
                    nVar = new z0.c(Integer.MAX_VALUE);
                }
            } else {
                if (i4 != 1) {
                    throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow");
                }
                nVar = new n(1, 2);
            }
        } else if (i4 == 1) {
            z0.g.f1449c.getClass();
            nVar = new z0.c(z0.f.f1448b);
        } else {
            nVar = new n(1, i4);
        }
        q qVar = new q(AbstractC0034s.e(rVar, iVar), nVar);
        qVar.K(3, qVar, dVar);
        this.f31h = 1;
        Object objA = h.a(this.f33j, qVar, true, this);
        i0.a aVar = i0.a.f747d;
        if (objA != aVar) {
            objA = gVar;
        }
        return objA == aVar ? aVar : gVar;
    }

    @Override // o0.p
    public final Object g(Object obj, Object obj2) {
        return ((c) b((h0.d) obj2, (r) obj)).e(g.f725a);
    }
}
