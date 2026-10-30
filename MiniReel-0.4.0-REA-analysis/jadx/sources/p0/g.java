package p0;

/* JADX INFO: loaded from: classes.dex */
public abstract class g extends c implements f, u0.a, f0.a {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f1213j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f1214k;

    public g(int i2, Object obj, Class cls, String str, String str2, int i3) {
        super(obj, cls, str, str2, (i3 & 1) == 1);
        this.f1213j = i2;
        this.f1214k = 0;
    }

    @Override // p0.c
    public final u0.a b() {
        n.f1220a.getClass();
        return this;
    }

    @Override // p0.f
    public final int d() {
        return this.f1213j;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            return this.f1208g.equals(gVar.f1208g) && this.f1209h.equals(gVar.f1209h) && this.f1214k == gVar.f1214k && this.f1213j == gVar.f1213j && this.f1206e.equals(gVar.f1206e) && c().equals(gVar.c());
        }
        if (!(obj instanceof g)) {
            return false;
        }
        u0.a aVar = this.f1205d;
        if (aVar == null) {
            b();
            this.f1205d = this;
            aVar = this;
        }
        return obj.equals(aVar);
    }

    public final int hashCode() {
        c();
        return this.f1209h.hashCode() + ((this.f1208g.hashCode() + (c().hashCode() * 31)) * 31);
    }

    public final String toString() {
        u0.a aVar = this.f1205d;
        if (aVar == null) {
            b();
            this.f1205d = this;
            aVar = this;
        }
        if (aVar != this) {
            return aVar.toString();
        }
        String str = this.f1208g;
        if ("<init>".equals(str)) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + str + " (Kotlin reflection is not available)";
    }
}
