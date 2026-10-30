package p013n;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f1123a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f1124b;

    public e(long j2, long j3) {
        if (j3 == 0) {
            this.f1123a = 0L;
            this.f1124b = 1L;
        } else {
            this.f1123a = j2;
            this.f1124b = j3;
        }
    }

    public final String toString() {
        return this.f1123a + "/" + this.f1124b;
    }
}
