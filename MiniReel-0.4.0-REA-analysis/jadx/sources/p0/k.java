package p0;

/* JADX INFO: loaded from: classes.dex */
public final class k implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f1217a;

    public k(Class cls) {
        this.f1217a = cls;
    }

    @Override // p0.d
    public final Class a() {
        return this.f1217a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof k) {
            return h.a(this.f1217a, ((k) obj).f1217a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f1217a.hashCode();
    }

    public final String toString() {
        return this.f1217a.toString() + " (Kotlin reflection is not available)";
    }
}
