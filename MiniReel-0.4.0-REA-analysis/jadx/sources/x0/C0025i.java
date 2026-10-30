package x0;

import java.util.concurrent.CancellationException;

/* JADX INFO: renamed from: x0.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0025i extends L implements InterfaceC0024h {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final S f1348h;

    public C0025i(S s2) {
        this.f1348h = s2;
    }

    @Override // x0.InterfaceC0024h
    public final boolean e(Throwable th) {
        S sN = n();
        if (th instanceof CancellationException) {
            return true;
        }
        return sN.m(th) && sN.u();
    }

    @Override // o0.l
    public final /* bridge */ /* synthetic */ Object h(Object obj) {
        o((Throwable) obj);
        return f0.g.f725a;
    }

    @Override // x0.N
    public final void o(Throwable th) {
        this.f1348h.m(n());
    }
}
