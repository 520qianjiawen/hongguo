package p012l;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import p008h.a;

/* JADX INFO: loaded from: classes.dex */
public abstract class j extends p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f1086h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static Method f1087i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static Class f1088j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static Field f1089k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static Field f1090l;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final WindowInsets f1091c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a[] f1092d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f1093e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public r f1094f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public a f1095g;

    public j(r rVar, WindowInsets windowInsets) {
        super(rVar);
        this.f1093e = null;
        this.f1091c = windowInsets;
    }

    private a r() {
        r rVar = this.f1094f;
        return rVar != null ? rVar.f1102a.g() : a.f736e;
    }

    private a s(View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
        }
        if (!f1086h) {
            u();
        }
        Method method = f1087i;
        if (method != null && f1088j != null && f1089k != null) {
            try {
                Object objInvoke = method.invoke(view, null);
                if (objInvoke == null) {
                    Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                    return null;
                }
                Rect rect = (Rect) f1089k.get(f1090l.get(objInvoke));
                if (rect != null) {
                    return a.a(rect.left, rect.top, rect.right, rect.bottom);
                }
            } catch (ReflectiveOperationException e2) {
                Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e2.getMessage(), e2);
            }
        }
        return null;
    }

    private static void u() {
        try {
            f1087i = View.class.getDeclaredMethod("getViewRootImpl", null);
            Class<?> cls = Class.forName("android.view.View$AttachInfo");
            f1088j = cls;
            f1089k = cls.getDeclaredField("mVisibleInsets");
            f1090l = Class.forName("android.view.ViewRootImpl").getDeclaredField("mAttachInfo");
            f1089k.setAccessible(true);
            f1090l.setAccessible(true);
        } catch (ReflectiveOperationException e2) {
            Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e2.getMessage(), e2);
        }
        f1086h = true;
    }

    @Override // p012l.p
    public void d(View view) {
        a aVarS = s(view);
        if (aVarS == null) {
            aVarS = a.f736e;
        }
        v(aVarS);
    }

    @Override // p012l.p
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return Objects.equals(this.f1095g, ((j) obj).f1095g);
        }
        return false;
    }

    @Override // p012l.p
    public final a i() {
        if (this.f1093e == null) {
            WindowInsets windowInsets = this.f1091c;
            this.f1093e = a.a(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        }
        return this.f1093e;
    }

    @Override // p012l.p
    public boolean l() {
        return this.f1091c.isRound();
    }

    @Override // p012l.p
    public boolean m(int i2) {
        for (int i3 = 1; i3 <= 256; i3 <<= 1) {
            if ((i2 & i3) != 0 && !t(i3)) {
                return false;
            }
        }
        return true;
    }

    @Override // p012l.p
    public void n(a[] aVarArr) {
        this.f1092d = aVarArr;
    }

    @Override // p012l.p
    public void o(r rVar) {
        this.f1094f = rVar;
    }

    public a q(int i2, boolean z2) {
        a aVarG;
        int i3;
        if (i2 == 1) {
            return z2 ? a.a(0, Math.max(r().f738b, i().f738b), 0, 0) : a.a(0, i().f738b, 0, 0);
        }
        if (i2 == 2) {
            if (z2) {
                a aVarR = r();
                a aVarG2 = g();
                return a.a(Math.max(aVarR.f737a, aVarG2.f737a), 0, Math.max(aVarR.f739c, aVarG2.f739c), Math.max(aVarR.f740d, aVarG2.f740d));
            }
            a aVarI = i();
            r rVar = this.f1094f;
            aVarG = rVar != null ? rVar.f1102a.g() : null;
            int iMin = aVarI.f740d;
            if (aVarG != null) {
                iMin = Math.min(iMin, aVarG.f740d);
            }
            return a.a(aVarI.f737a, 0, aVarI.f739c, iMin);
        }
        a aVar = a.f736e;
        if (i2 == 8) {
            a[] aVarArr = this.f1092d;
            aVarG = aVarArr != null ? aVarArr[3] : null;
            if (aVarG != null) {
                return aVarG;
            }
            a aVarI2 = i();
            a aVarR2 = r();
            int i4 = aVarI2.f740d;
            if (i4 > aVarR2.f740d) {
                return a.a(0, 0, 0, i4);
            }
            a aVar2 = this.f1095g;
            if (aVar2 != null && !aVar2.equals(aVar) && (i3 = this.f1095g.f740d) > aVarR2.f740d) {
                return a.a(0, 0, 0, i3);
            }
        } else {
            if (i2 == 16) {
                return h();
            }
            if (i2 == 32) {
                return f();
            }
            if (i2 == 64) {
                return j();
            }
            if (i2 == 128) {
                r rVar2 = this.f1094f;
                b bVarE = rVar2 != null ? rVar2.f1102a.e() : e();
                if (bVarE != null) {
                    int i5 = Build.VERSION.SDK_INT;
                    return a.a(i5 >= 28 ? a.d(bVarE.f1078a) : 0, i5 >= 28 ? a.f(bVarE.f1078a) : 0, i5 >= 28 ? a.e(bVarE.f1078a) : 0, i5 >= 28 ? a.c(bVarE.f1078a) : 0);
                }
            }
        }
        return aVar;
    }

    public boolean t(int i2) {
        if (i2 != 1 && i2 != 2) {
            if (i2 == 4) {
                return false;
            }
            if (i2 != 8 && i2 != 128) {
                return true;
            }
        }
        return !q(i2, false).equals(a.f736e);
    }

    public void v(a aVar) {
        this.f1095g = aVar;
    }
}
