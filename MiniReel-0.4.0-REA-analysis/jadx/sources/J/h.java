package J;

import C0.x;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final x f203a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Runnable f204b;

    public h(x xVar, Runnable runnable) {
        this.f203a = xVar;
        this.f204b = runnable;
    }

    public final Integer a() {
        x xVar = this.f203a;
        if (xVar != null) {
            return Integer.valueOf(((g) xVar.f90e).f193c);
        }
        return null;
    }
}
