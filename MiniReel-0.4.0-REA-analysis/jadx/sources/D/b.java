package D;

import C0.x;
import G.f;
import H.e;
import P.d;
import Y.k;
import Z.g;
import Z.m;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.WindowManager;
import java.lang.reflect.Field;
import p0.h;
import p0.j;
import p0.n;
import u0.c;

/* JADX INFO: loaded from: classes.dex */
public final class b implements V.b, m, W.a {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ c[] f94q;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e f95d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f96e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Activity f97f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public f f98g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public x f99h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f f100i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public x f101j;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Float f104n;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final a f102k = new a(this, new Handler(Looper.getMainLooper()), 0);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final p002b0.a f103l = new p002b0.a();
    public final p002b0.a m = new p002b0.a();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f105o = true;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f106p = true;

    static {
        j jVar = new j("maximumScreenBrightness", "getMaximumScreenBrightness()F");
        n.f1220a.getClass();
        f94q = new c[]{jVar, new j("systemScreenBrightness", "getSystemScreenBrightness()F")};
    }

    public static float a(Context context) {
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            if (powerManager == null) {
                throw new ClassNotFoundException();
            }
            Field[] declaredFields = powerManager.getClass().getDeclaredFields();
            h.d(declaredFields, "getDeclaredFields(...)");
            for (Field field : declaredFields) {
                if (field.getName().equals("BRIGHTNESS_ON")) {
                    field.setAccessible(true);
                    Object obj = field.get(powerManager);
                    h.c(obj, "null cannot be cast to non-null type kotlin.Int");
                    return ((Integer) obj).intValue();
                }
            }
            return 255.0f;
        } catch (Exception unused) {
            return 255.0f;
        }
    }

    public final float b() {
        return ((Number) this.m.o(f94q[1])).floatValue();
    }

    public final boolean c(float f2) {
        try {
            Activity activity = this.f97f;
            h.b(activity);
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            h.d(attributes, "getAttributes(...)");
            attributes.screenBrightness = f2;
            Activity activity2 = this.f97f;
            h.b(activity2);
            activity2.getWindow().setAttributes(attributes);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // Z.m
    public final void m(H.a aVar, k kVar) {
        boolean zPutInt;
        h.e(aVar, "call");
        String str = (String) aVar.f170e;
        if (str != null) {
            int iHashCode = str.hashCode();
            p002b0.a aVar2 = this.f103l;
            c[] cVarArr = f94q;
            switch (iHashCode) {
                case -1467693354:
                    if (str.equals("isAutoReset")) {
                        kVar.d(Boolean.valueOf(this.f105o));
                        return;
                    }
                    break;
                case -1436714409:
                    if (str.equals("isAnimate")) {
                        kVar.d(Boolean.valueOf(this.f106p));
                        return;
                    }
                    break;
                case -1106725218:
                    if (str.equals("setAutoReset")) {
                        Object objX = aVar.x("isAutoReset");
                        Boolean bool = objX instanceof Boolean ? (Boolean) objX : null;
                        if (bool == null) {
                            kVar.a("-2", "Unexpected error on null isAutoReset", null);
                            return;
                        } else {
                            this.f105o = bool.booleanValue();
                            kVar.d(null);
                            return;
                        }
                    }
                    break;
                case -1065890690:
                    if (str.equals("resetApplicationScreenBrightness")) {
                        if (this.f96e == null) {
                            kVar.a("-10", "Unexpected error on activity binding", null);
                            return;
                        }
                        if (!c(-1.0f)) {
                            kVar.a("-1", "Unable to reset screen brightness", null);
                            return;
                        }
                        this.f104n = null;
                        float fB = b();
                        x xVar = this.f101j;
                        if (xVar != null) {
                            double d2 = fB;
                            g gVar = (g) xVar.f90e;
                            if (gVar != null) {
                                gVar.a(Double.valueOf(d2));
                            }
                        }
                        kVar.d(null);
                        return;
                    }
                    break;
                case -754168297:
                    if (str.equals("getApplicationScreenBrightness")) {
                        Activity activity = this.f97f;
                        if (activity == null) {
                            kVar.a("-10", "Unexpected error on activity binding", null);
                            return;
                        }
                        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                        h.d(attributes, "getAttributes(...)");
                        float f2 = attributes.screenBrightness;
                        Float fValueOf = Float.valueOf(f2);
                        if (Math.signum(f2) != -1.0f) {
                            kVar.d(fValueOf);
                            return;
                        }
                        try {
                            Context applicationContext = activity.getApplicationContext();
                            h.d(applicationContext, "getApplicationContext(...)");
                            kVar.d(Float.valueOf(Settings.System.getInt(applicationContext.getContentResolver(), "screen_brightness") / ((Number) aVar2.o(cVarArr[0])).floatValue()));
                            return;
                        } catch (Settings.SettingNotFoundException e2) {
                            e2.printStackTrace();
                            kVar.a("-11", "Could not found application screen brightness", null);
                            return;
                        }
                    }
                    break;
                case -618374773:
                    if (str.equals("setApplicationScreenBrightness")) {
                        if (this.f96e == null) {
                            kVar.a("-10", "Unexpected error on activity binding", null);
                            return;
                        }
                        Object objX2 = aVar.x("brightness");
                        Double d3 = objX2 instanceof Double ? (Double) objX2 : null;
                        Float fValueOf2 = d3 != null ? Float.valueOf((float) d3.doubleValue()) : null;
                        if (fValueOf2 == null) {
                            kVar.a("-2", "Unexpected error on null brightness", null);
                            return;
                        }
                        if (!c(fValueOf2.floatValue())) {
                            kVar.a("-1", "Unable to change application screen brightness", null);
                            return;
                        }
                        this.f104n = fValueOf2;
                        float fFloatValue = fValueOf2.floatValue();
                        x xVar2 = this.f101j;
                        if (xVar2 != null) {
                            double d4 = fFloatValue;
                            g gVar2 = (g) xVar2.f90e;
                            if (gVar2 != null) {
                                gVar2.a(Double.valueOf(d4));
                            }
                        }
                        kVar.d(null);
                        return;
                    }
                    break;
                case 232928160:
                    if (str.equals("canChangeSystemBrightness")) {
                        Context context = this.f96e;
                        if (context == null) {
                            kVar.a("-10", "Unexpected error on activity binding", null);
                            return;
                        } else {
                            kVar.d(Boolean.valueOf(Settings.System.canWrite(context)));
                            return;
                        }
                    }
                    break;
                case 576725282:
                    if (str.equals("getSystemScreenBrightness")) {
                        kVar.d(Float.valueOf(b()));
                        return;
                    }
                    break;
                case 1131488993:
                    if (str.equals("hasApplicationScreenBrightnessChanged")) {
                        kVar.d(Boolean.valueOf(this.f104n != null));
                        return;
                    }
                    break;
                case 1420016942:
                    if (str.equals("setSystemScreenBrightness")) {
                        Context context2 = this.f96e;
                        if (context2 == null) {
                            kVar.a("-10", "Unexpected error on activity binding", null);
                            return;
                        }
                        Object objX3 = aVar.x("brightness");
                        Double d5 = objX3 instanceof Double ? (Double) objX3 : null;
                        Float fValueOf3 = d5 != null ? Float.valueOf((float) d5.doubleValue()) : null;
                        if (fValueOf3 == null) {
                            kVar.a("-2", "Unexpected error on null brightness", null);
                            return;
                        }
                        float fFloatValue2 = fValueOf3.floatValue();
                        if (Settings.System.canWrite(context2)) {
                            zPutInt = Settings.System.putInt(context2.getContentResolver(), "screen_brightness", (int) (((Number) aVar2.o(cVarArr[0])).floatValue() * fFloatValue2));
                        } else {
                            Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS", Uri.parse("package:" + context2.getPackageName()));
                            intent.addFlags(268435456);
                            context2.startActivity(intent);
                            zPutInt = false;
                        }
                        if (!zPutInt) {
                            kVar.a("-1", "Unable to change system screen brightness", null);
                            return;
                        }
                        c cVar = cVarArr[1];
                        p002b0.a aVar3 = this.m;
                        aVar3.getClass();
                        h.e(cVar, "property");
                        aVar3.f661e = fValueOf3;
                        float fFloatValue3 = fValueOf3.floatValue();
                        x xVar3 = this.f99h;
                        if (xVar3 != null) {
                            double d6 = fFloatValue3;
                            g gVar3 = (g) xVar3.f90e;
                            if (gVar3 != null) {
                                gVar3.a(Double.valueOf(d6));
                            }
                        }
                        kVar.d(null);
                        return;
                    }
                    break;
                case 2116729887:
                    if (str.equals("setAnimate")) {
                        Object objX4 = aVar.x("isAnimate");
                        Boolean bool2 = objX4 instanceof Boolean ? (Boolean) objX4 : null;
                        if (bool2 == null) {
                            kVar.a("-2", "Unexpected error on null isAnimate", null);
                            return;
                        } else {
                            this.f106p = bool2.booleanValue();
                            kVar.d(null);
                            return;
                        }
                    }
                    break;
            }
        }
        kVar.b();
    }

    @Override // W.a
    public final void onAttachedToActivity(W.b bVar) {
        h.e(bVar, "binding");
        this.f97f = ((d) bVar).f414a;
    }

    @Override // V.b
    public final void onAttachedToEngine(V.a aVar) {
        p002b0.a aVar2 = this.f103l;
        h.e(aVar, "flutterPluginBinding");
        Context context = aVar.f515a;
        Z.f fVar = aVar.f517c;
        e eVar = new e(fVar, "github.com/aaassseee/screen_brightness");
        this.f95d = eVar;
        eVar.j(this);
        this.f98g = new f(fVar, "github.com/aaassseee/screen_brightness/system_brightness_changed");
        this.f100i = new f(fVar, "github.com/aaassseee/screen_brightness/application_brightness_changed");
        try {
            h.d(context, "getApplicationContext(...)");
            float fA = a(context);
            c[] cVarArr = f94q;
            c cVar = cVarArr[0];
            Float fValueOf = Float.valueOf(fA);
            aVar2.getClass();
            h.e(cVar, "property");
            aVar2.f661e = fValueOf;
            float f2 = Settings.System.getInt(context.getContentResolver(), "screen_brightness") / ((Number) aVar2.o(cVarArr[0])).floatValue();
            p002b0.a aVar3 = this.m;
            c cVar2 = cVarArr[1];
            Float fValueOf2 = Float.valueOf(f2);
            aVar3.getClass();
            h.e(cVar2, "property");
            aVar3.f661e = fValueOf2;
        } catch (Settings.SettingNotFoundException e2) {
            e2.printStackTrace();
        }
        this.f96e = context;
        context.getContentResolver().registerContentObserver(Settings.System.getUriFor("screen_brightness"), false, this.f102k);
        x xVar = new x(1);
        this.f99h = xVar;
        f fVar2 = this.f98g;
        if (fVar2 == null) {
            h.g("systemScreenBrightnessChangedEventChannel");
            throw null;
        }
        fVar2.l(xVar);
        x xVar2 = new x(1);
        this.f101j = xVar2;
        f fVar3 = this.f100i;
        if (fVar3 != null) {
            fVar3.l(xVar2);
        } else {
            h.g("applicationScreenBrightnessChangedEventChannel");
            throw null;
        }
    }

    @Override // W.a
    public final void onDetachedFromActivity() {
        this.f97f = null;
    }

    @Override // W.a
    public final void onDetachedFromActivityForConfigChanges() {
        this.f97f = null;
    }

    @Override // V.b
    public final void onDetachedFromEngine(V.a aVar) {
        ContentResolver contentResolver;
        h.e(aVar, "binding");
        Context context = this.f96e;
        if (context != null && (contentResolver = context.getContentResolver()) != null) {
            contentResolver.unregisterContentObserver(this.f102k);
        }
        e eVar = this.f95d;
        if (eVar == null) {
            h.g("methodChannel");
            throw null;
        }
        eVar.j(null);
        f fVar = this.f98g;
        if (fVar == null) {
            h.g("systemScreenBrightnessChangedEventChannel");
            throw null;
        }
        fVar.l(null);
        this.f99h = null;
        f fVar2 = this.f100i;
        if (fVar2 == null) {
            h.g("applicationScreenBrightnessChangedEventChannel");
            throw null;
        }
        fVar2.l(null);
        this.f101j = null;
    }

    @Override // W.a
    public final void onReattachedToActivityForConfigChanges(W.b bVar) {
        h.e(bVar, "binding");
        this.f97f = ((d) bVar).f414a;
    }
}
