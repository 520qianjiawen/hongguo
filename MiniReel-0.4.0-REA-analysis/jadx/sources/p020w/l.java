package p020w;

import p0.h;
import p012l.r;
import p019t.b;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f1302a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r f1303b;

    public l(b bVar, r rVar) {
        h.e(rVar, "_windowInsetsCompat");
        this.f1302a = bVar;
        this.f1303b = rVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!l.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        h.c(obj, "null cannot be cast to non-null type androidx.window.layout.WindowMetrics");
        l lVar = (l) obj;
        return h.a(this.f1302a, lVar.f1302a) && h.a(this.f1303b, lVar.f1303b);
    }

    public final int hashCode() {
        return this.f1303b.hashCode() + (this.f1302a.hashCode() * 31);
    }

    public final String toString() {
        return "WindowMetrics( bounds=" + this.f1302a + ", windowInsetsCompat=" + this.f1303b + ')';
    }
}
