package P;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f428a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f429b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f430c;

    public f(int i2, String str, String str2) {
        this(str, str2, "io.flutter.embedding.android.", false);
    }

    public f(String str, String str2) {
        this(str, str2, "io.flutter.embedding.android.", true);
    }

    public f(String str, String str2, String str3, boolean z2) {
        this.f428a = str;
        this.f429b = str3.concat(str2);
        this.f430c = z2;
    }
}
