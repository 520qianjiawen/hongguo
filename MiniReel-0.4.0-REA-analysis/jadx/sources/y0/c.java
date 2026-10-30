package y0;

import C0.p;
import android.os.Handler;
import android.os.Looper;
import h0.i;
import java.util.concurrent.CancellationException;
import p0.h;
import x0.AbstractC0032p;
import x0.AbstractC0039x;
import x0.C0033q;
import x0.InterfaceC0037v;
import x0.J;

/* JADX INFO: loaded from: classes.dex */
public final class c extends AbstractC0032p implements InterfaceC0037v {
    private volatile c _immediate;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Handler f1393f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f1394g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final c f1395h;

    public c(Handler handler, boolean z2) {
        this.f1393f = handler;
        this.f1394g = z2;
        this._immediate = z2 ? this : null;
        c cVar = this._immediate;
        if (cVar == null) {
            cVar = new c(handler, true);
            this._immediate = cVar;
        }
        this.f1395h = cVar;
    }

    @Override // x0.AbstractC0032p
    public final void c(i iVar, Runnable runnable) {
        if (this.f1393f.post(runnable)) {
            return;
        }
        CancellationException cancellationException = new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed");
        J j2 = (J) iVar.i(C0033q.f1363e);
        if (j2 != null) {
            j2.a(cancellationException);
        }
        AbstractC0039x.f1377b.c(iVar, runnable);
    }

    @Override // x0.AbstractC0032p
    public final boolean d() {
        return (this.f1394g && h.a(Looper.myLooper(), this.f1393f.getLooper())) ? false : true;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof c) && ((c) obj).f1393f == this.f1393f;
    }

    public final int hashCode() {
        return System.identityHashCode(this.f1393f);
    }

    @Override // x0.AbstractC0032p
    public final String toString() {
        c cVar;
        String str;
        D0.d dVar = AbstractC0039x.f1376a;
        c cVar2 = p.f83a;
        if (this == cVar2) {
            str = "Dispatchers.Main";
        } else {
            try {
                cVar = cVar2.f1395h;
            } catch (UnsupportedOperationException unused) {
                cVar = null;
            }
            str = this == cVar ? "Dispatchers.Main.immediate" : null;
        }
        if (str != null) {
            return str;
        }
        String string = this.f1393f.toString();
        if (!this.f1394g) {
            return string;
        }
        return string + ".immediate";
    }
}
