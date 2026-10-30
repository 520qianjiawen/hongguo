package z0;

import x0.c0;

/* JADX INFO: loaded from: classes.dex */
public final class n extends c {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f1454n;

    public n(int i2, int i3) {
        super(i2);
        this.f1454n = i3;
        if (i3 == 1) {
            throw new IllegalArgumentException(("This implementation does not support suspension for senders, use " + p0.n.a(c.class).b() + " instead").toString());
        }
        if (i2 >= 1) {
            return;
        }
        throw new IllegalArgumentException(("Buffered channel capacity must be at least 1, but " + i2 + " was specified").toString());
    }

    @Override // z0.c, z0.t
    public final Object d(Object obj) {
        f0.g gVar = f0.g.f725a;
        if (this.f1454n == 3) {
            Object objD = super.d(obj);
            return (!(objD instanceof i) || (objD instanceof h)) ? objD : gVar;
        }
        K.c cVar = e.f1432d;
        k kVar = (k) c.f1423i.get(this);
        while (true) {
            long andIncrement = c.f1419e.getAndIncrement(this);
            long j2 = 1152921504606846975L & andIncrement;
            boolean zO = o(andIncrement, false);
            int i2 = e.f1430b;
            long j3 = i2;
            long j4 = j2 / j3;
            int i3 = (int) (j2 % j3);
            if (kVar.f87c != j4) {
                k kVarB = c.b(this, j4, kVar);
                if (kVarB != null) {
                    kVar = kVarB;
                } else if (zO) {
                    return new h(l());
                }
            }
            int iC = c.c(this, kVar, i3, obj, j2, cVar, zO);
            if (iC == 0) {
                kVar.a();
                return gVar;
            }
            if (iC == 1) {
                return gVar;
            }
            if (iC == 2) {
                if (zO) {
                    kVar.h();
                    return new h(l());
                }
                c0 c0Var = cVar instanceof c0 ? (c0) cVar : null;
                if (c0Var != null) {
                    c0Var.a(kVar, i3 + i2);
                }
                h((kVar.f87c * j3) + ((long) i3));
                return gVar;
            }
            if (iC == 3) {
                throw new IllegalStateException("unexpected");
            }
            if (iC == 4) {
                if (j2 < c.f1420f.get(this)) {
                    kVar.a();
                }
                return new h(l());
            }
            if (iC == 5) {
                kVar.a();
            }
        }
    }

    @Override // z0.c
    public final boolean q() {
        return this.f1454n == 2;
    }
}
