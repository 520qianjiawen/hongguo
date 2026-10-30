package z0;

/* JADX INFO: loaded from: classes.dex */
public final class h extends i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Throwable f1450a;

    public h(Throwable th) {
        this.f1450a = th;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof h) {
            return p0.h.a(this.f1450a, ((h) obj).f1450a);
        }
        return false;
    }

    public final int hashCode() {
        Throwable th = this.f1450a;
        if (th != null) {
            return th.hashCode();
        }
        return 0;
    }

    @Override // z0.i
    public final String toString() {
        return "Closed(" + this.f1450a + ')';
    }
}
