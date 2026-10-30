package p020w;

import A.c;
import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.WindowManager;
import g0.b;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import p0.h;
import p012l.f;
import p012l.g;
import p012l.i;
import p012l.r;

/* JADX INFO: loaded from: classes.dex */
public final class n implements m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f1305b = 0;

    static {
        new ArrayList(new b(new Integer[]{1, 2, 4, 8, 16, 32, 64, 128}, true));
    }

    public static l a(Activity activity) throws Exception {
        Rect rect;
        i gVar;
        r rVarB;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            rect = ((WindowManager) activity.getSystemService(WindowManager.class)).getCurrentWindowMetrics().getBounds();
            h.d(rect, "wm.currentWindowMetrics.bounds");
        } else if (i2 >= 29) {
            Configuration configuration = activity.getResources().getConfiguration();
            try {
                Field declaredField = Configuration.class.getDeclaredField("windowConfiguration");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(configuration);
                Object objInvoke = obj.getClass().getDeclaredMethod("getBounds", null).invoke(obj, null);
                h.c(objInvoke, "null cannot be cast to non-null type android.graphics.Rect");
                rect = new Rect((Rect) objInvoke);
            } catch (IllegalAccessException e2) {
                Log.w("n", e2);
                rect = b(activity);
            } catch (NoSuchFieldException e3) {
                Log.w("n", e3);
                rect = b(activity);
            } catch (NoSuchMethodException e4) {
                Log.w("n", e4);
                rect = b(activity);
            } catch (InvocationTargetException e5) {
                Log.w("n", e5);
                rect = b(activity);
            }
        } else if (i2 >= 28) {
            rect = b(activity);
        } else {
            rect = new Rect();
            Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
            defaultDisplay.getRectSize(rect);
            if (!activity.isInMultiWindowMode()) {
                Point point = new Point();
                defaultDisplay.getRealSize(point);
                Resources resources = activity.getResources();
                int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
                int dimensionPixelSize = identifier > 0 ? resources.getDimensionPixelSize(identifier) : 0;
                int i3 = rect.bottom + dimensionPixelSize;
                if (i3 == point.y) {
                    rect.bottom = i3;
                } else {
                    int i4 = rect.right + dimensionPixelSize;
                    if (i4 == point.x) {
                        rect.right = i4;
                    }
                }
            }
        }
        int i5 = Build.VERSION.SDK_INT;
        if (i5 < 30) {
            if (i5 >= 30) {
                gVar = new p012l.h();
            } else {
                gVar = i5 >= 29 ? new g() : new f();
            }
            rVarB = gVar.b();
            h.d(rVarB, "{\n            WindowInse…ilder().build()\n        }");
        } else {
            if (i5 < 30) {
                throw new Exception("Incompatible SDK version");
            }
            rVarB = A.b.f0a.a(activity);
        }
        return new l(new p019t.b(rect), rVarB);
    }

    public static Rect b(Activity activity) {
        Rect rect = new Rect();
        Configuration configuration = activity.getResources().getConfiguration();
        DisplayCutout displayCutoutJ = null;
        try {
            Field declaredField = Configuration.class.getDeclaredField("windowConfiguration");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(configuration);
            if (activity.isInMultiWindowMode()) {
                Object objInvoke = obj.getClass().getDeclaredMethod("getBounds", null).invoke(obj, null);
                h.c(objInvoke, "null cannot be cast to non-null type android.graphics.Rect");
                rect.set((Rect) objInvoke);
            } else {
                Object objInvoke2 = obj.getClass().getDeclaredMethod("getAppBounds", null).invoke(obj, null);
                h.c(objInvoke2, "null cannot be cast to non-null type android.graphics.Rect");
                rect.set((Rect) objInvoke2);
            }
        } catch (IllegalAccessException e2) {
            Log.w("n", e2);
            activity.getWindowManager().getDefaultDisplay().getRectSize(rect);
        } catch (NoSuchFieldException e3) {
            Log.w("n", e3);
            activity.getWindowManager().getDefaultDisplay().getRectSize(rect);
        } catch (NoSuchMethodException e4) {
            Log.w("n", e4);
            activity.getWindowManager().getDefaultDisplay().getRectSize(rect);
        } catch (InvocationTargetException e5) {
            Log.w("n", e5);
            activity.getWindowManager().getDefaultDisplay().getRectSize(rect);
        }
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        h.d(defaultDisplay, "currentDisplay");
        defaultDisplay.getRealSize(point);
        if (!activity.isInMultiWindowMode()) {
            Resources resources = activity.getResources();
            int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            int dimensionPixelSize = identifier > 0 ? resources.getDimensionPixelSize(identifier) : 0;
            int i2 = rect.bottom + dimensionPixelSize;
            if (i2 == point.y) {
                rect.bottom = i2;
            } else {
                int i3 = rect.right + dimensionPixelSize;
                if (i3 == point.x) {
                    rect.right = i3;
                } else if (rect.left == dimensionPixelSize) {
                    rect.left = 0;
                }
            }
        }
        if ((rect.width() < point.x || rect.height() < point.y) && !activity.isInMultiWindowMode()) {
            try {
                Constructor<?> constructor = Class.forName("android.view.DisplayInfo").getConstructor(null);
                constructor.setAccessible(true);
                Object objNewInstance = constructor.newInstance(null);
                Method declaredMethod = defaultDisplay.getClass().getDeclaredMethod("getDisplayInfo", objNewInstance.getClass());
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(defaultDisplay, objNewInstance);
                Field declaredField2 = objNewInstance.getClass().getDeclaredField("displayCutout");
                declaredField2.setAccessible(true);
                Object obj2 = declaredField2.get(objNewInstance);
                if (c.u(obj2)) {
                    displayCutoutJ = c.j(obj2);
                }
            } catch (ClassNotFoundException e6) {
                Log.w("n", e6);
            } catch (IllegalAccessException e7) {
                Log.w("n", e7);
            } catch (InstantiationException e8) {
                Log.w("n", e8);
            } catch (NoSuchFieldException e9) {
                Log.w("n", e9);
            } catch (NoSuchMethodException e10) {
                Log.w("n", e10);
            } catch (InvocationTargetException e11) {
                Log.w("n", e11);
            }
            if (displayCutoutJ != null) {
                if (rect.left == displayCutoutJ.getSafeInsetLeft()) {
                    rect.left = 0;
                }
                if (point.x - rect.right == displayCutoutJ.getSafeInsetRight()) {
                    rect.right = displayCutoutJ.getSafeInsetRight() + rect.right;
                }
                if (rect.top == displayCutoutJ.getSafeInsetTop()) {
                    rect.top = 0;
                }
                if (point.y - rect.bottom == displayCutoutJ.getSafeInsetBottom()) {
                    rect.bottom = displayCutoutJ.getSafeInsetBottom() + rect.bottom;
                }
            }
        }
        return rect;
    }
}
