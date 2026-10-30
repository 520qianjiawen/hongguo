package A0;

import z0.r;

/* JADX INFO: loaded from: classes.dex */
public final class a extends j0.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public r f1g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public /* synthetic */ Object f2h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ b f3i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(b bVar, j0.b bVar2) {
        super(bVar2);
        this.f3i = bVar;
    }

    @Override // j0.b
    public final Object e(Object obj) {
        this.f2h = obj;
        this.f4j |= Integer.MIN_VALUE;
        return this.f3i.a(null, this);
    }
}
