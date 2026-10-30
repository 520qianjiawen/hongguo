package p0;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class c implements u0.a, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient u0.a f1205d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f1206e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Class f1207f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f1208g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f1209h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f1210i;

    public c(Object obj, Class cls, String str, String str2, boolean z2) {
        this.f1206e = obj;
        this.f1207f = cls;
        this.f1208g = str;
        this.f1209h = str2;
        this.f1210i = z2;
    }

    public abstract u0.a b();

    public final d c() {
        boolean z2 = this.f1210i;
        Class cls = this.f1207f;
        if (!z2) {
            return n.a(cls);
        }
        n.f1220a.getClass();
        return new k(cls);
    }
}
