package x0;

import C0.AbstractC0000a;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: renamed from: x0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0017a extends S implements h0.d, r {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final h0.i f1336f;

    public AbstractC0017a(h0.i iVar, boolean z2) {
        super(z2);
        z((J) iVar.i(C0033q.f1363e));
        this.f1336f = iVar.h(this);
    }

    @Override // x0.S
    public final void D(Object obj) {
        if (!(obj instanceof C0027k)) {
            J(obj);
        } else {
            C0027k c0027k = (C0027k) obj;
            I(c0027k.f1354a, C0027k.f1353b.get(c0027k) != 0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void K(int i2, AbstractC0017a abstractC0017a, o0.p pVar) {
        int iA = p014o.c.a(i2);
        f0.g gVar = f0.g.f725a;
        if (iA == 0) {
            try {
                AbstractC0000a.g(p000a.a.y(((j0.b) pVar).b(this, abstractC0017a)), gVar);
                return;
            } catch (Throwable th) {
                resumeWith(p000a.a.k(th));
                throw th;
            }
        }
        if (iA != 1) {
            if (iA == 2) {
                p000a.a.y(((j0.b) pVar).b(this, abstractC0017a)).resumeWith(gVar);
                return;
            }
            if (iA != 3) {
                throw new H.c();
            }
            try {
                h0.i iVar = this.f1336f;
                Object objK = AbstractC0000a.k(iVar, null);
                try {
                    p0.p.a(2, pVar);
                    Object objG = pVar.g(abstractC0017a, this);
                    AbstractC0000a.f(iVar, objK);
                    if (objG != i0.a.f747d) {
                        resumeWith(objG);
                    }
                } catch (Throwable th2) {
                    AbstractC0000a.f(iVar, objK);
                    throw th2;
                }
            } catch (Throwable th3) {
                resumeWith(p000a.a.k(th3));
            }
        }
    }

    @Override // x0.r
    public final h0.i e() {
        return this.f1336f;
    }

    @Override // h0.d
    public final h0.i getContext() {
        return this.f1336f;
    }

    @Override // x0.S
    public final String p() {
        return getClass().getSimpleName().concat(" was cancelled");
    }

    @Override // h0.d
    public final void resumeWith(Object obj) throws IllegalAccessException, InvocationTargetException {
        Throwable thA = f0.d.a(obj);
        if (thA != null) {
            obj = new C0027k(thA, false);
        }
        Object objA = A(obj);
        if (objA == AbstractC0034s.f1367d) {
            return;
        }
        l(objA);
    }

    @Override // x0.S
    public final void y(H.c cVar) {
        AbstractC0034s.c(this.f1336f, cVar);
    }

    public void J(Object obj) {
    }

    public void I(Throwable th, boolean z2) {
    }
}
