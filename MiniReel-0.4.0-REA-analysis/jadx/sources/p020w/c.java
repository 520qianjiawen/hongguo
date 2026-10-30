package p020w;

import p0.h;
import p019t.b;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f1282a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f1283b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f1284c;

    public c(b bVar, b bVar2, b bVar3) {
        int i2 = bVar.f1239b;
        this.f1282a = bVar;
        this.f1283b = bVar2;
        this.f1284c = bVar3;
        int i3 = bVar.f1240c;
        int i4 = bVar.f1238a;
        if (i3 - i4 == 0 && bVar.f1241d - i2 == 0) {
            throw new IllegalArgumentException("Bounds must be non zero");
        }
        if (i4 != 0 && i2 != 0) {
            throw new IllegalArgumentException("Bounding rectangle must start at the top or left window edge for folding features");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!c.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        h.c(obj, "null cannot be cast to non-null type androidx.window.layout.HardwareFoldingFeature");
        c cVar = (c) obj;
        return h.a(this.f1282a, cVar.f1282a) && h.a(this.f1283b, cVar.f1283b) && h.a(this.f1284c, cVar.f1284c);
    }

    public final int hashCode() {
        return this.f1284c.hashCode() + ((this.f1283b.hashCode() + (this.f1282a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return c.class.getSimpleName() + " { " + this.f1282a + ", type=" + this.f1283b + ", state=" + this.f1284c + " }";
    }
}
