package x0;

/* JADX INFO: renamed from: x0.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0041z implements G {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f1378d;

    public C0041z(boolean z2) {
        this.f1378d = z2;
    }

    @Override // x0.G
    public final boolean b() {
        return this.f1378d;
    }

    @Override // x0.G
    public final T f() {
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Empty{");
        sb.append(this.f1378d ? "Active" : "New");
        sb.append('}');
        return sb.toString();
    }
}
