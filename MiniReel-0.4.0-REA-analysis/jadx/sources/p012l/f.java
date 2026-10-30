package p012l;

import android.graphics.Rect;
import android.util.Log;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class f extends i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f1080b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f1081c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Constructor f1082d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f1083e = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WindowInsets f1084a = c();

    private static WindowInsets c() {
        if (!f1081c) {
            try {
                f1080b = WindowInsets.class.getDeclaredField("CONSUMED");
            } catch (ReflectiveOperationException e2) {
                Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
            }
            f1081c = true;
        }
        Field field = f1080b;
        if (field != null) {
            try {
                WindowInsets windowInsets = (WindowInsets) field.get(null);
                if (windowInsets != null) {
                    return new WindowInsets(windowInsets);
                }
            } catch (ReflectiveOperationException e3) {
                Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e3);
            }
        }
        if (!f1083e) {
            try {
                f1082d = WindowInsets.class.getConstructor(Rect.class);
            } catch (ReflectiveOperationException e4) {
                Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
            }
            f1083e = true;
        }
        Constructor constructor = f1082d;
        if (constructor != null) {
            try {
                return (WindowInsets) constructor.newInstance(new Rect());
            } catch (ReflectiveOperationException e5) {
                Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e5);
            }
        }
        return null;
    }

    @Override // p012l.i
    public r b() {
        a();
        r rVarA = r.a(null, this.f1084a);
        p pVar = rVarA.f1102a;
        pVar.n(null);
        pVar.p(null);
        return rVarA;
    }
}
