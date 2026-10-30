package D0;

import x0.AbstractC0034s;

/* JADX INFO: loaded from: classes.dex */
public final class j extends h {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Runnable f134f;

    public j(Runnable runnable, long j2, i iVar) {
        super(j2, iVar);
        this.f134f = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.f134f.run();
        } finally {
            this.f132e.getClass();
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Task[");
        Runnable runnable = this.f134f;
        sb.append(runnable.getClass().getSimpleName());
        sb.append('@');
        sb.append(AbstractC0034s.a(runnable));
        sb.append(", ");
        sb.append(this.f131d);
        sb.append(", ");
        sb.append(this.f132e);
        sb.append(']');
        return sb.toString();
    }
}
