package io.flutter.plugin.editing;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ boolean f848a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ double[] f849b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ double[] f850c;

    public k(boolean z2, double[] dArr, double[] dArr2) {
        this.f848a = z2;
        this.f849b = dArr;
        this.f850c = dArr2;
    }

    public final void a(double d2, double d3) {
        boolean z2 = this.f848a;
        double[] dArr = this.f849b;
        double d4 = 1.0d;
        if (!z2) {
            d4 = 1.0d / (((dArr[7] * d3) + (dArr[3] * d2)) + dArr[15]);
        }
        double d5 = ((dArr[4] * d3) + (dArr[0] * d2) + dArr[12]) * d4;
        double d6 = ((dArr[5] * d3) + (dArr[1] * d2) + dArr[13]) * d4;
        double[] dArr2 = this.f850c;
        if (d5 < dArr2[0]) {
            dArr2[0] = d5;
        } else if (d5 > dArr2[1]) {
            dArr2[1] = d5;
        }
        if (d6 < dArr2[2]) {
            dArr2[2] = d6;
        } else if (d6 > dArr2[3]) {
            dArr2[3] = d6;
        }
    }
}
