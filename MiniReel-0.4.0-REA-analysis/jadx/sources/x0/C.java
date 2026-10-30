package x0;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public abstract class C extends AbstractC0032p {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f1309f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1310g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public g0.c f1311h;

    public final void e() {
        long j2 = this.f1309f - 4294967296L;
        this.f1309f = j2;
        if (j2 <= 0 && this.f1310g) {
            shutdown();
        }
    }

    public abstract Thread j();

    public final void k(boolean z2) {
        this.f1309f = (z2 ? 4294967296L : 1L) + this.f1309f;
        if (z2) {
            return;
        }
        this.f1310g = true;
    }

    public final boolean l() throws IllegalAccessException, InvocationTargetException {
        g0.c cVar = this.f1311h;
        if (cVar == null) {
            return false;
        }
        AbstractC0038w abstractC0038w = (AbstractC0038w) (cVar.isEmpty() ? null : cVar.removeFirst());
        if (abstractC0038w == null) {
            return false;
        }
        abstractC0038w.run();
        return true;
    }

    public abstract void shutdown();
}
