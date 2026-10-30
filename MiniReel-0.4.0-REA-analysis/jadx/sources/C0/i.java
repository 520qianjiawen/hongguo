package C0;

import x0.AbstractC0032p;
import x0.AbstractC0034s;

/* JADX INFO: loaded from: classes.dex */
public final class i implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Runnable f64d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ j f65e;

    public i(j jVar, Runnable runnable) {
        this.f65e = jVar;
        this.f64d = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        j jVar = this.f65e;
        AbstractC0032p abstractC0032p = jVar.f67f;
        int i2 = 0;
        while (true) {
            try {
                this.f64d.run();
            } catch (Throwable th) {
                AbstractC0034s.c(h0.j.f746d, th);
            }
            Runnable runnableE = jVar.e();
            if (runnableE == null) {
                return;
            }
            this.f64d = runnableE;
            i2++;
            if (i2 >= 16 && abstractC0032p.d()) {
                abstractC0032p.c(jVar, this);
                return;
            }
        }
    }
}
