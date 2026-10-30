package p019t;

import f0.e;
import java.math.BigInteger;
import p018s.a;

/* JADX INFO: loaded from: classes.dex */
public final class h implements Comparable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final h f1254i;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1255d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1256e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1257f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f1258g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final e f1259h = new e(new a(1, this));

    static {
        new h(0, 0, 0, "");
        f1254i = new h(0, 1, 0, "");
        new h(1, 0, 0, "");
    }

    public h(int i2, int i3, int i4, String str) {
        this.f1255d = i2;
        this.f1256e = i3;
        this.f1257f = i4;
        this.f1258g = str;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        h hVar = (h) obj;
        p0.h.e(hVar, "other");
        Object objA = this.f1259h.a();
        p0.h.d(objA, "<get-bigInteger>(...)");
        Object objA2 = hVar.f1259h.a();
        p0.h.d(objA2, "<get-bigInteger>(...)");
        return ((BigInteger) objA).compareTo((BigInteger) objA2);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return this.f1255d == hVar.f1255d && this.f1256e == hVar.f1256e && this.f1257f == hVar.f1257f;
    }

    public final int hashCode() {
        return ((((527 + this.f1255d) * 31) + this.f1256e) * 31) + this.f1257f;
    }

    public final String toString() {
        String strConcat;
        int i2 = 0;
        while (true) {
            String str = this.f1258g;
            if (i2 >= str.length()) {
                strConcat = "";
                break;
            }
            char cCharAt = str.charAt(i2);
            if (!Character.isWhitespace(cCharAt) && !Character.isSpaceChar(cCharAt)) {
                strConcat = "-".concat(str);
                break;
            }
            i2++;
        }
        return this.f1255d + '.' + this.f1256e + '.' + this.f1257f + strConcat;
    }
}
