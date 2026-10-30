package p019t;

import android.graphics.Rect;
import p0.h;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1238a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1239b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1240c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1241d;

    public b(Rect rect) {
        int i2 = rect.left;
        int i3 = rect.top;
        int i4 = rect.right;
        int i5 = rect.bottom;
        this.f1238a = i2;
        this.f1239b = i3;
        this.f1240c = i4;
        this.f1241d = i5;
        if (i2 > i4) {
            throw new IllegalArgumentException(("Left must be less than or equal to right, left: " + i2 + ", right: " + i4).toString());
        }
        if (i3 <= i5) {
            return;
        }
        throw new IllegalArgumentException(("top must be less than or equal to bottom, top: " + i3 + ", bottom: " + i5).toString());
    }

    public final Rect a() {
        return new Rect(this.f1238a, this.f1239b, this.f1240c, this.f1241d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!b.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        h.c(obj, "null cannot be cast to non-null type androidx.window.core.Bounds");
        b bVar = (b) obj;
        return this.f1238a == bVar.f1238a && this.f1239b == bVar.f1239b && this.f1240c == bVar.f1240c && this.f1241d == bVar.f1241d;
    }

    public final int hashCode() {
        return (((((this.f1238a * 31) + this.f1239b) * 31) + this.f1240c) * 31) + this.f1241d;
    }

    public final String toString() {
        return b.class.getSimpleName() + " { [" + this.f1238a + ',' + this.f1239b + ',' + this.f1240c + ',' + this.f1241d + "] }";
    }
}
