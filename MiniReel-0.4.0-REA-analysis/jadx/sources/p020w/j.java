package p020w;

import O.q;
import android.app.Activity;
import f0.g;
import h0.d;
import j0.f;
import o0.p;
import p000a.a;
import z0.r;

/* JADX INFO: loaded from: classes.dex */
public final class j extends f implements p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1297h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f1298i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ b f1299j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ Activity f1300k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(b bVar, Activity activity, d dVar) {
        super(dVar);
        this.f1299j = bVar;
        this.f1300k = activity;
    }

    @Override // j0.b
    public final d b(d dVar, Object obj) {
        j jVar = new j(this.f1299j, this.f1300k, dVar);
        jVar.f1298i = obj;
        return jVar;
    }

    @Override // j0.b
    public final Object e(Object obj) throws Throwable {
        int i2 = this.f1297h;
        if (i2 == 0) {
            a.P(obj);
            r rVar = (r) this.f1298i;
            q qVar = new q(1, rVar);
            b bVar = this.f1299j;
            ((x.a) bVar.f1281e).b(this.f1300k, new p014o.d(), qVar);
            i iVar = new i(bVar, qVar);
            this.f1297h = 1;
            Object objA = z0.j.a(rVar, iVar, this);
            i0.a aVar = i0.a.f747d;
            if (objA == aVar) {
                return aVar;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            a.P(obj);
        }
        return g.f725a;
    }

    @Override // o0.p
    public final Object g(Object obj, Object obj2) {
        return ((j) b((d) obj2, (r) obj)).e(g.f725a);
    }
}
