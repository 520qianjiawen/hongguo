package p0;

/* JADX INFO: loaded from: classes.dex */
public abstract class l extends c implements u0.c {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f1218j;

    public l(Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, (i2 & 1) == 1);
        this.f1218j = false;
    }

    public final u0.a e() {
        if (this.f1218j) {
            return this;
        }
        u0.a aVar = this.f1205d;
        if (aVar != null) {
            return aVar;
        }
        u0.a aVarB = b();
        this.f1205d = aVarB;
        return aVarB;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof l) {
            l lVar = (l) obj;
            return c().equals(lVar.c()) && this.f1208g.equals(lVar.f1208g) && this.f1209h.equals(lVar.f1209h) && this.f1206e.equals(lVar.f1206e);
        }
        if (obj instanceof u0.c) {
            return obj.equals(e());
        }
        return false;
    }

    public final int hashCode() {
        return this.f1209h.hashCode() + ((this.f1208g.hashCode() + (c().hashCode() * 31)) * 31);
    }

    public final String toString() {
        u0.a aVarE = e();
        if (aVarE != this) {
            return aVarE.toString();
        }
        return "property " + this.f1208g + " (Kotlin reflection is not available)";
    }
}
