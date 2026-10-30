package x0;

/* JADX INFO: renamed from: x0.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0032p extends h0.a implements h0.f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C0031o f1361e = new C0031o(h0.e.f745d, C0030n.f1358e);

    public AbstractC0032p() {
        super(h0.e.f745d);
    }

    public abstract void c(h0.i iVar, Runnable runnable);

    public boolean d() {
        return !(this instanceof a0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001d, code lost:
    
        if (((h0.g) r3.f1359d.h(r2)) == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0022, code lost:
    
        if (h0.e.f745d == r3) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0026, code lost:
    
        return h0.j.f746d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0027, code lost:
    
        return r2;
     */
    /* JADX WARN: Type inference failed for: r3v3, types: [o0.l, p0.i] */
    @Override // h0.a, h0.i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final h0.i g(h0.h r3) {
        /*
            r2 = this;
            java.lang.String r0 = "key"
            p0.h.e(r3, r0)
            boolean r0 = r3 instanceof x0.C0031o
            if (r0 == 0) goto L20
            x0.o r3 = (x0.C0031o) r3
            h0.h r0 = r2.f741d
            if (r0 == r3) goto L15
            h0.h r1 = r3.f1360e
            if (r1 != r0) goto L14
            goto L15
        L14:
            return r2
        L15:
            p0.i r3 = r3.f1359d
            java.lang.Object r3 = r3.h(r2)
            h0.g r3 = (h0.g) r3
            if (r3 == 0) goto L27
            goto L24
        L20:
            h0.e r0 = h0.e.f745d
            if (r0 != r3) goto L27
        L24:
            h0.j r3 = h0.j.f746d
            return r3
        L27:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: x0.AbstractC0032p.g(h0.h):h0.i");
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [o0.l, p0.i] */
    @Override // h0.a, h0.i
    public final h0.g i(h0.h hVar) {
        h0.g gVar;
        p0.h.e(hVar, "key");
        if (hVar instanceof C0031o) {
            C0031o c0031o = (C0031o) hVar;
            h0.h hVar2 = this.f741d;
            if ((hVar2 == c0031o || c0031o.f1360e == hVar2) && (gVar = (h0.g) c0031o.f1359d.h(this)) != null) {
                return gVar;
            }
        } else if (h0.e.f745d == hVar) {
            return this;
        }
        return null;
    }

    public String toString() {
        return getClass().getSimpleName() + '@' + AbstractC0034s.a(this);
    }
}
