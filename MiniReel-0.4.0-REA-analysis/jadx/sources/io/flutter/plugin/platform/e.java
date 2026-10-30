package io.flutter.plugin.platform;

import O.AbstractActivityC0007e;
import O.InterfaceC0010h;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import p012l.s;
import p012l.t;
import p012l.v;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Activity f874a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final H.a f875b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final InterfaceC0010h f876c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public J.l f877d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f878e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f879f = false;

    public e(AbstractActivityC0007e abstractActivityC0007e, H.a aVar, AbstractActivityC0007e abstractActivityC0007e2) {
        p002b0.a aVar2 = new p002b0.a(2, this);
        this.f874a = abstractActivityC0007e;
        this.f875b = aVar;
        aVar.f171f = aVar2;
        this.f876c = abstractActivityC0007e2;
        this.f878e = 1280;
    }

    public final void a(J.l lVar) {
        p000a.a tVar;
        Window window = this.f874a.getWindow();
        window.getDecorView();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            tVar = new v(window);
        } else {
            tVar = i2 >= 26 ? new t(window) : new s(window);
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 30) {
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(201326592);
        }
        int i4 = lVar.f214a;
        if (i4 != 0) {
            int iA = p014o.c.a(i4);
            if (iA == 0) {
                tVar.M(false);
            } else if (iA == 1) {
                tVar.M(true);
            }
        }
        Integer num = (Integer) lVar.f216c;
        if (num != null && i3 < 35) {
            window.setStatusBarColor(num.intValue());
        }
        Boolean bool = (Boolean) lVar.f217d;
        if (bool != null && i3 >= 29) {
            window.setStatusBarContrastEnforced(bool.booleanValue());
        }
        if (i3 >= 26) {
            int i5 = lVar.f215b;
            if (i5 != 0) {
                int iA2 = p014o.c.a(i5);
                if (iA2 == 0) {
                    tVar.L(false);
                } else if (iA2 == 1) {
                    tVar.L(true);
                }
            }
            Integer num2 = (Integer) lVar.f218e;
            if (num2 != null && i3 < 35) {
                window.setNavigationBarColor(num2.intValue());
            }
        }
        Integer num3 = (Integer) lVar.f219f;
        if (num3 != null && i3 >= 28 && i3 < 35) {
            window.setNavigationBarDividerColor(num3.intValue());
        }
        Boolean bool2 = (Boolean) lVar.f220g;
        if (bool2 != null && i3 >= 29) {
            window.setNavigationBarContrastEnforced(bool2.booleanValue());
        }
        this.f877d = lVar;
    }

    public final void b() {
        boolean z2 = this.f879f;
        Activity activity = this.f874a;
        if (z2) {
            activity.getWindow().getDecorView().setSystemUiVisibility(0);
            p000a.a.N(activity.getWindow(), false);
        } else {
            activity.getWindow().getDecorView().setSystemUiVisibility(this.f878e);
        }
        J.l lVar = this.f877d;
        if (lVar != null) {
            a(lVar);
        }
    }
}
