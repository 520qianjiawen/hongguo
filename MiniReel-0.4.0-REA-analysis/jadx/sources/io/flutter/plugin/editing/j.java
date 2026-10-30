package io.flutter.plugin.editing;

import Y.o;
import Y.q;
import android.graphics.Rect;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class j implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ m f847a;

    public /* synthetic */ j(m mVar) {
        this.f847a = mVar;
    }

    public void a(int i2, o oVar) {
        m mVar = this.f847a;
        mVar.c();
        mVar.f858f = oVar;
        mVar.f857e = new l(2, i2);
        mVar.f860h.e(mVar);
        H.e eVar = oVar.f575j;
        mVar.f860h = new g(eVar != null ? (q) eVar.f176f : null, mVar.f853a);
        mVar.d(oVar);
        mVar.f861i = true;
        if (mVar.f857e.f851a == 3) {
            mVar.f867p = false;
        }
        mVar.m = null;
        mVar.f860h.a(mVar);
    }

    public void b(double d2, double d3, double[] dArr) {
        double[] dArr2 = new double[4];
        boolean z2 = dArr[3] == 0.0d && dArr[7] == 0.0d && dArr[15] == 1.0d;
        double d4 = dArr[12];
        double d5 = dArr[15];
        double d6 = d4 / d5;
        dArr2[1] = d6;
        dArr2[0] = d6;
        double d7 = dArr[13] / d5;
        dArr2[3] = d7;
        dArr2[2] = d7;
        k kVar = new k(z2, dArr, dArr2);
        kVar.a(d2, 0.0d);
        kVar.a(d2, d3);
        kVar.a(0.0d, d3);
        m mVar = this.f847a;
        double d8 = mVar.f853a.getContext().getResources().getDisplayMetrics().density;
        mVar.m = new Rect((int) (dArr2[0] * d8), (int) (dArr2[2] * d8), (int) Math.ceil(dArr2[1] * d8), (int) Math.ceil(dArr2[3] * d8));
    }

    public void c(q qVar) {
        q qVar2;
        int i2;
        int i3;
        m mVar = this.f847a;
        View view = mVar.f853a;
        if (!mVar.f861i && (qVar2 = mVar.f866o) != null && (i2 = qVar2.f584d) >= 0 && (i3 = qVar2.f585e) > i2) {
            int i4 = i3 - i2;
            int i5 = qVar.f585e;
            int i6 = qVar.f584d;
            boolean z2 = true;
            if (i4 == i5 - i6) {
                int i7 = 0;
                while (true) {
                    if (i7 >= i4) {
                        z2 = false;
                        break;
                    } else if (qVar2.f581a.charAt(i7 + i2) != qVar.f581a.charAt(i7 + i6)) {
                        break;
                    } else {
                        i7++;
                    }
                }
            }
            mVar.f861i = z2;
        }
        mVar.f866o = qVar;
        mVar.f860h.f(qVar);
        if (mVar.f861i) {
            mVar.f854b.restartInput(view);
            mVar.f861i = false;
        }
    }
}
