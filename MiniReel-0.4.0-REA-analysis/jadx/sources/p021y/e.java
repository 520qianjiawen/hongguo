package p021y;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Point;
import android.graphics.Rect;
import android.inputmethodservice.InputMethodService;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import androidx.window.extensions.layout.FoldingFeature;
import androidx.window.extensions.layout.WindowLayoutInfo;
import java.util.ArrayList;
import java.util.List;
import p0.h;
import p012l.f;
import p012l.g;
import p012l.i;
import p012l.r;
import p020w.b;
import p020w.c;
import p020w.k;
import p020w.l;
import p020w.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {
    public static c a(l lVar, FoldingFeature foldingFeature) {
        b bVar;
        b bVar2;
        int type = foldingFeature.getType();
        if (type == 1) {
            bVar = b.f1278j;
        } else {
            if (type != 2) {
                return null;
            }
            bVar = b.f1279k;
        }
        int state = foldingFeature.getState();
        if (state == 1) {
            bVar2 = b.f1276h;
        } else {
            if (state != 2) {
                return null;
            }
            bVar2 = b.f1277i;
        }
        Rect bounds = foldingFeature.getBounds();
        h.d(bounds, "oemFeature.bounds");
        int i2 = bounds.left;
        int i3 = bounds.top;
        int i4 = bounds.right;
        int i5 = bounds.bottom;
        if (i2 > i4) {
            throw new IllegalArgumentException(("Left must be less than or equal to right, left: " + i2 + ", right: " + i4).toString());
        }
        if (i3 > i5) {
            throw new IllegalArgumentException(("top must be less than or equal to bottom, top: " + i3 + ", bottom: " + i5).toString());
        }
        Rect rectA = lVar.f1302a.a();
        int i6 = i5 - i3;
        if (i6 == 0 && i4 - i2 == 0) {
            return null;
        }
        int i7 = i4 - i2;
        if (i7 != rectA.width() && i6 != rectA.height()) {
            return null;
        }
        if (i7 < rectA.width() && i6 < rectA.height()) {
            return null;
        }
        if (i7 == rectA.width() && i6 == rectA.height()) {
            return null;
        }
        Rect bounds2 = foldingFeature.getBounds();
        h.d(bounds2, "oemFeature.bounds");
        return new c(new p019t.b(bounds2), bVar, bVar2);
    }

    public static k b(Context context, WindowLayoutInfo windowLayoutInfo) throws Exception {
        boolean z2;
        i gVar;
        l lVar;
        h.e(windowLayoutInfo, "info");
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 30) {
            if (i2 < 29 || !(context instanceof Activity)) {
                throw new UnsupportedOperationException("Display Features are only supported after Q. Display features for non-Activity contexts are not expected to be reported on devices running Q.");
            }
            int i3 = n.f1305b;
            return c(n.a((Activity) context), windowLayoutInfo);
        }
        int i4 = n.f1305b;
        if (i2 >= 30) {
            WindowManager windowManager = (WindowManager) context.getSystemService(WindowManager.class);
            r rVarA = r.a(null, windowManager.getCurrentWindowMetrics().getWindowInsets());
            Rect bounds = windowManager.getCurrentWindowMetrics().getBounds();
            h.d(bounds, "wm.currentWindowMetrics.bounds");
            lVar = new l(new p019t.b(bounds), rVarA);
        } else {
            Context baseContext = context;
            while (true) {
                if (!(baseContext instanceof ContextWrapper)) {
                    throw new IllegalArgumentException("Context " + context + " is not a UiContext");
                }
                z2 = baseContext instanceof Activity;
                if (z2 || (baseContext instanceof InputMethodService)) {
                    break;
                }
                ContextWrapper contextWrapper = (ContextWrapper) baseContext;
                if (contextWrapper.getBaseContext() == null) {
                    break;
                }
                baseContext = contextWrapper.getBaseContext();
                h.d(baseContext, "iterator.baseContext");
            }
            if (z2) {
                lVar = n.a((Activity) context);
            } else {
                if (!(baseContext instanceof InputMethodService)) {
                    throw new IllegalArgumentException(context + " is not a UiContext");
                }
                Object systemService = context.getSystemService("window");
                h.c(systemService, "null cannot be cast to non-null type android.view.WindowManager");
                Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
                h.d(defaultDisplay, "wm.defaultDisplay");
                Point point = new Point();
                defaultDisplay.getRealSize(point);
                Rect rect = new Rect(0, 0, point.x, point.y);
                int i5 = Build.VERSION.SDK_INT;
                if (i5 >= 30) {
                    gVar = new p012l.h();
                } else {
                    gVar = i5 >= 29 ? new g() : new f();
                }
                r rVarB = gVar.b();
                h.d(rVarB, "Builder().build()");
                lVar = new l(new p019t.b(rect), rVarB);
            }
        }
        return c(lVar, windowLayoutInfo);
    }

    public static k c(l lVar, WindowLayoutInfo windowLayoutInfo) {
        c cVarA;
        h.e(windowLayoutInfo, "info");
        List<FoldingFeature> displayFeatures = windowLayoutInfo.getDisplayFeatures();
        h.d(displayFeatures, "info.displayFeatures");
        ArrayList arrayList = new ArrayList();
        for (FoldingFeature foldingFeature : displayFeatures) {
            if (foldingFeature instanceof FoldingFeature) {
                h.d(foldingFeature, "feature");
                cVarA = a(lVar, foldingFeature);
            } else {
                cVarA = null;
            }
            if (cVarA != null) {
                arrayList.add(cVarA);
            }
        }
        return new k(arrayList);
    }
}
