package Q;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f457a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f458b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f459c;

    public a(String str, String str2) {
        this.f457a = str;
        this.f458b = null;
        this.f459c = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        if (this.f457a.equals(aVar.f457a)) {
            return this.f459c.equals(aVar.f459c);
        }
        return false;
    }

    public final int hashCode() {
        return this.f459c.hashCode() + (this.f457a.hashCode() * 31);
    }

    public final String toString() {
        return "DartEntrypoint( bundle path: " + this.f457a + ", function: " + this.f459c + " )";
    }

    public a(String str, String str2, String str3) {
        this.f457a = str;
        this.f458b = str2;
        this.f459c = str3;
    }
}
