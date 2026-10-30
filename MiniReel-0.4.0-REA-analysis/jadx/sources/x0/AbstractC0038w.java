package x0;

import C0.AbstractC0000a;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;

/* JADX INFO: renamed from: x0.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0038w extends D0.h {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1375f;

    public AbstractC0038w(int i2) {
        super(0L, D0.k.f141g);
        this.f1375f = i2;
    }

    public abstract void b(Object obj, CancellationException cancellationException);

    public abstract h0.d d();

    public Throwable e(Object obj) {
        C0027k c0027k = obj instanceof C0027k ? (C0027k) obj : null;
        if (c0027k != null) {
            return c0027k.f1354a;
        }
        return null;
    }

    public final void g(Throwable th, Throwable th2) throws IllegalAccessException, InvocationTargetException {
        if (th == null && th2 == null) {
            return;
        }
        if (th != null && th2 != null) {
            p000a.a.a(th, th2);
        }
        if (th == null) {
            th = th2;
        }
        p0.h.b(th);
        AbstractC0034s.c(d().getContext(), new n0.a("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th));
    }

    public abstract Object h();

    /* JADX WARN: Code duplicated, block: B:21:0x004e  */
    @Override // java.lang.Runnable
    public final void run() throws IllegalAccessException, InvocationTargetException {
        J j2;
        Object objK = f0.g.f725a;
        D0.i iVar = this.f132e;
        try {
            h0.d dVarD = d();
            p0.h.c(dVarD, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTask>");
            C0.h hVar = (C0.h) dVarD;
            j0.b bVar = hVar.f61h;
            Object obj = hVar.f63j;
            h0.i iVar2 = bVar.f1072e;
            p0.h.b(iVar2);
            Object objK2 = AbstractC0000a.k(iVar2, obj);
            if (objK2 != AbstractC0000a.f51f) {
                AbstractC0034s.h(bVar, iVar2);
            }
            try {
                h0.i iVar3 = bVar.f1072e;
                p0.h.b(iVar3);
                Object objH = h();
                Throwable thE = e(objH);
                if (thE == null) {
                    int i2 = this.f1375f;
                    boolean z2 = true;
                    if (i2 != 1 && i2 != 2) {
                        z2 = false;
                    }
                    if (z2) {
                        j2 = (J) iVar3.i(C0033q.f1363e);
                    } else {
                        j2 = null;
                    }
                } else {
                    j2 = null;
                }
                if (j2 != null && !j2.b()) {
                    CancellationException cancellationExceptionT = ((S) j2).t();
                    b(objH, cancellationExceptionT);
                    bVar.resumeWith(p000a.a.k(cancellationExceptionT));
                } else if (thE != null) {
                    bVar.resumeWith(p000a.a.k(thE));
                } else {
                    bVar.resumeWith(f(objH));
                }
                AbstractC0000a.f(iVar2, objK2);
                try {
                    iVar.getClass();
                } catch (Throwable th) {
                    objK = p000a.a.k(th);
                }
                g(null, f0.d.a(objK));
            } catch (Throwable th2) {
                AbstractC0000a.f(iVar2, objK2);
                throw th2;
            }
        } catch (Throwable th3) {
            try {
                iVar.getClass();
            } catch (Throwable th4) {
                objK = p000a.a.k(th4);
            }
            g(th3, f0.d.a(objK));
        }
    }

    public Object f(Object obj) {
        return obj;
    }
}
