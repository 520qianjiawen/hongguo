package p008h;

import android.graphics.Insets;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f736e = new a(0, 0, 0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f737a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f738b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f739c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f740d;

    public a(int i2, int i3, int i4, int i5) {
        this.f737a = i2;
        this.f738b = i3;
        this.f739c = i4;
        this.f740d = i5;
    }

    public static a a(int i2, int i3, int i4, int i5) {
        return (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) ? f736e : new a(i2, i3, i4, i5);
    }

    public static a b(Insets insets) {
        return a(insets.left, insets.top, insets.right, insets.bottom);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return this.f740d == aVar.f740d && this.f737a == aVar.f737a && this.f739c == aVar.f739c && this.f738b == aVar.f738b;
    }

    public final int hashCode() {
        return (((((this.f737a * 31) + this.f738b) * 31) + this.f739c) * 31) + this.f740d;
    }

    public final String toString() {
        return "Insets{left=" + this.f737a + ", top=" + this.f738b + ", right=" + this.f739c + ", bottom=" + this.f740d + '}';
    }
}
