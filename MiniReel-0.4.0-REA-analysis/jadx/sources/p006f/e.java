package p006f;

import p000a.a;

/* JADX INFO: loaded from: classes.dex */
public final class e extends a {
    @Override // p000a.a
    public final void G(f fVar, f fVar2) {
        fVar.f710b = fVar2;
    }

    @Override // p000a.a
    public final void H(f fVar, Thread thread) {
        fVar.f709a = thread;
    }

    @Override // p000a.a
    public final boolean f(g gVar, c cVar) {
        c cVar2 = c.f702b;
        synchronized (gVar) {
            try {
                if (gVar.f716e != cVar) {
                    return false;
                }
                gVar.f716e = cVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // p000a.a
    public final boolean g(g gVar, Object obj, Object obj2) {
        synchronized (gVar) {
            try {
                if (gVar.f715d != obj) {
                    return false;
                }
                gVar.f715d = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // p000a.a
    public final boolean h(g gVar, f fVar, f fVar2) {
        synchronized (gVar) {
            try {
                if (gVar.f717f != fVar) {
                    return false;
                }
                gVar.f717f = fVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
