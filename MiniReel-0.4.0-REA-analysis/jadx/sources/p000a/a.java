package p000a;

import G.f;
import J.t;
import K.c;
import P.g;
import S.d;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import h0.b;
import h0.i;
import h0.j;
import io.flutter.plugins.GeneratedPluginRegistrant;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import o0.l;
import org.json.JSONArray;
import org.json.JSONObject;
import p0.h;
import p012l.e;
import p012l.r;
import p020w.m;
import p020w.n;
import x0.AbstractC0032p;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static long f602e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Method f603f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static Method f604g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static Method f605h;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f606d;

    public /* synthetic */ a(int i2) {
        this.f606d = i2;
    }

    public static List A(Object obj) {
        List listSingletonList = Collections.singletonList(obj);
        h.d(listSingletonList, "singletonList(...)");
        return listSingletonList;
    }

    public static f B(Context context) {
        String string;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            Bundle bundle = applicationInfo.metaData;
            String str = g.f431a.f429b;
            String str2 = g.f432b.f429b;
            String string2 = null;
            if (bundle == null) {
                string = null;
            } else {
                string = bundle.getString(str, null);
                if (string == null) {
                    string = bundle.getString(str2);
                }
            }
            Bundle bundle2 = applicationInfo.metaData;
            String str3 = g.f436f.f429b;
            if (bundle2 != null) {
                bundle2.getString(str3, null);
            }
            Bundle bundle3 = applicationInfo.metaData;
            String str4 = g.f437g.f429b;
            if (bundle3 != null) {
                bundle3.getString(str4, null);
            }
            Bundle bundle4 = applicationInfo.metaData;
            String str5 = g.f433c.f429b;
            String str6 = g.f434d.f429b;
            if (bundle4 != null && (string2 = bundle4.getString(str5, null)) == null) {
                string2 = bundle4.getString(str6);
            }
            String str7 = applicationInfo.nativeLibraryDir;
            f fVar = new f();
            if (string == null) {
                string = "libapp.so";
            }
            fVar.f164d = string;
            if (string2 == null) {
                string2 = "flutter_assets";
            }
            fVar.f165e = string2;
            fVar.f166f = str7;
            return fVar;
        } catch (PackageManager.NameNotFoundException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static i C(h0.g gVar, h0.h hVar) {
        h.e(hVar, "key");
        return h.a(gVar.getKey(), hVar) ? j.f746d : gVar;
    }

    public static p019t.h D(String str) {
        String strGroup;
        if (str == null) {
            return null;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (!Character.isWhitespace(cCharAt) && !Character.isSpaceChar(cCharAt)) {
                Matcher matcher = Pattern.compile("(\\d+)(?:\\.(\\d+))(?:\\.(\\d+))(?:-(.+))?").matcher(str);
                if (!matcher.matches() || (strGroup = matcher.group(1)) == null) {
                    return null;
                }
                int i3 = Integer.parseInt(strGroup);
                String strGroup2 = matcher.group(2);
                if (strGroup2 == null) {
                    return null;
                }
                int i4 = Integer.parseInt(strGroup2);
                String strGroup3 = matcher.group(3);
                if (strGroup3 == null) {
                    return null;
                }
                int i5 = Integer.parseInt(strGroup3);
                String strGroup4 = matcher.group(4) != null ? matcher.group(4) : "";
                h.d(strGroup4, "description");
                return new p019t.h(i3, i4, i5, strGroup4);
            }
        }
        return null;
    }

    public static i E(h0.g gVar, i iVar) {
        h.e(iVar, "context");
        return F(gVar, iVar);
    }

    public static i F(i iVar, i iVar2) {
        h.e(iVar2, "context");
        return iVar2 == j.f746d ? iVar : (i) iVar2.f(iVar, new b(1));
    }

    public static void I(d dVar, MediaExtractor mediaExtractor) {
        try {
            int trackCount = mediaExtractor.getTrackCount();
            for (int i2 = 0; i2 < trackCount; i2++) {
                MediaFormat trackFormat = mediaExtractor.getTrackFormat(i2);
                String string = trackFormat.getString("mime");
                if (string != null && string.startsWith("image/")) {
                    int integer = trackFormat.containsKey("rotation-degrees") ? trackFormat.getInteger("rotation-degrees") : 0;
                    int i3 = dVar.f502g;
                    int i4 = dVar.f501f;
                    if (integer != 90 && integer != 270) {
                        i4 = i3;
                        i3 = i4;
                    }
                    dVar.f497b = i3;
                    dVar.f496a = i4;
                    dVar.f498c = integer;
                    return;
                }
            }
        } catch (Exception e2) {
            Log.e("MediaMetadataReader", "Failed to decode HEIF image using MediaExtractor", e2);
        }
    }

    public static void J(P.c cVar) {
        try {
            GeneratedPluginRegistrant.class.getDeclaredMethod("registerWith", P.c.class).invoke(null, cVar);
        } catch (Exception e2) {
            Log.e("GeneratedPluginsRegister", "Tried to automatically register plugins with FlutterEngine (" + cVar + ") but could not find or invoke the GeneratedPluginRegistrant.");
            Log.e("GeneratedPluginsRegister", "Received exception while registering", e2);
        }
    }

    public static void N(Window window, boolean z2) {
        if (Build.VERSION.SDK_INT >= 30) {
            e.a(window, z2);
            return;
        }
        View decorView = window.getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        decorView.setSystemUiVisibility(z2 ? systemUiVisibility & (-1793) : systemUiVisibility | 1792);
    }

    public static boolean O(byte[] bArr, byte[] bArr2) {
        if (bArr2 != null && bArr.length >= bArr2.length) {
            for (int i2 = 0; i2 < bArr2.length; i2++) {
                if (bArr[i2] == bArr2[i2]) {
                }
            }
            return true;
        }
        return false;
    }

    public static final void P(Object obj) throws Throwable {
        if (obj instanceof f0.c) {
            throw ((f0.c) obj).f720d;
        }
    }

    public static String Q(String str) {
        return str.length() <= 127 ? str : str.substring(0, 127);
    }

    public static final boolean R(String str, o0.a aVar) {
        try {
            boolean zBooleanValue = ((Boolean) aVar.a()).booleanValue();
            if (!zBooleanValue && str != null) {
                Log.e("ReflectionGuard", str);
            }
            return zBooleanValue;
        } catch (ClassNotFoundException unused) {
            if (str == null) {
                str = "";
            }
            Log.e("ReflectionGuard", "ClassNotFound: ".concat(str));
            return false;
        } catch (NoSuchMethodException unused2) {
            if (str == null) {
                str = "";
            }
            Log.e("ReflectionGuard", "NoSuchMethod: ".concat(str));
            return false;
        }
    }

    public static Object S(Object obj) {
        if (obj == null) {
            return JSONObject.NULL;
        }
        if ((obj instanceof JSONArray) || (obj instanceof JSONObject) || obj.equals(JSONObject.NULL)) {
            return obj;
        }
        try {
            if (obj instanceof Collection) {
                JSONArray jSONArray = new JSONArray();
                Iterator it = ((Collection) obj).iterator();
                while (it.hasNext()) {
                    jSONArray.put(S(it.next()));
                }
                return jSONArray;
            }
            if (obj.getClass().isArray()) {
                JSONArray jSONArray2 = new JSONArray();
                int length = Array.getLength(obj);
                for (int i2 = 0; i2 < length; i2++) {
                    jSONArray2.put(S(Array.get(obj, i2)));
                }
                return jSONArray2;
            }
            if (obj instanceof Map) {
                JSONObject jSONObject = new JSONObject();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    jSONObject.put((String) entry.getKey(), S(entry.getValue()));
                }
                return jSONObject;
            }
            if ((obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Short) || (obj instanceof String)) {
                return obj;
            }
            if (obj.getClass().getPackage().getName().startsWith("java.")) {
                return obj.toString();
            }
            return null;
        } catch (Exception unused) {
        }
    }

    public static void a(Throwable th, Throwable th2) throws IllegalAccessException, InvocationTargetException {
        h.e(th, "<this>");
        h.e(th2, "exception");
        if (th != th2) {
            Integer num = l0.a.f1106a;
            if (num == null || num.intValue() >= 19) {
                th.addSuppressed(th2);
                return;
            }
            Method method = k0.a.f1077a;
            if (method != null) {
                method.invoke(th, th2);
            }
        }
    }

    public static Bitmap b(Bitmap bitmap, int i2) {
        if (bitmap != null) {
            switch (i2) {
                case 1:
                case 3:
                case 6:
                case 8:
                    break;
                case 2:
                case 4:
                case 5:
                case 7:
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    Matrix matrix = new Matrix();
                    if (i2 == 2 || i2 == 7) {
                        matrix.setScale(-1.0f, 1.0f, width / 2.0f, height / 2.0f);
                    } else {
                        if (i2 != 4 && i2 != 5) {
                            return bitmap;
                        }
                        matrix.setScale(1.0f, -1.0f, width / 2.0f, height / 2.0f);
                    }
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                    if (bitmapCreateBitmap != bitmap) {
                        bitmap.recycle();
                    }
                    return bitmapCreateBitmap;
                default:
                    Log.e("ImageUtils", "Unknown EXIF orientation: " + i2);
                    return bitmap;
            }
        }
        return bitmap;
    }

    public static void c(Context context, e0.b bVar) throws Exception {
        Rect rect;
        p012l.i gVar;
        r rVarB;
        Activity activityO = o(context);
        if (activityO != null) {
            m.f1304a.getClass();
            int i2 = n.f1305b;
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 30) {
                rect = ((WindowManager) activityO.getSystemService(WindowManager.class)).getMaximumWindowMetrics().getBounds();
                h.d(rect, "wm.maximumWindowMetrics.bounds");
            } else {
                Object systemService = activityO.getSystemService("window");
                h.c(systemService, "null cannot be cast to non-null type android.view.WindowManager");
                Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
                h.d(defaultDisplay, "display");
                Point point = new Point();
                defaultDisplay.getRealSize(point);
                rect = new Rect(0, 0, point.x, point.y);
            }
            if (i3 < 30) {
                if (i3 >= 30) {
                    gVar = new p012l.h();
                } else {
                    gVar = i3 >= 29 ? new p012l.g() : new p012l.f();
                }
                rVarB = gVar.b();
                h.d(rVarB, "{\n            WindowInse…ilder().build()\n        }");
            } else {
                if (i3 < 30) {
                    throw new Exception("Incompatible SDK version");
                }
                rVarB = A.b.f0a.a(activityO);
            }
            int i4 = rect.left;
            int i5 = rect.top;
            int i6 = rect.right;
            int i7 = rect.bottom;
            if (i4 > i6) {
                throw new IllegalArgumentException(("Left must be less than or equal to right, left: " + i4 + ", right: " + i6).toString());
            }
            if (i5 <= i7) {
                h.e(rVarB, "_windowInsetsCompat");
                ((P.c) bVar).c(new Rect(i4, i5, i6, i7).width(), new Rect(i4, i5, i6, i7).height(), context.getResources().getDisplayMetrics().density);
            } else {
                throw new IllegalArgumentException(("top must be less than or equal to bottom, top: " + i5 + ", bottom: " + i7).toString());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static long[] j(Serializable serializable) {
        if (!(serializable instanceof int[])) {
            if (serializable instanceof long[]) {
                return (long[]) serializable;
            }
            return null;
        }
        int[] iArr = (int[]) serializable;
        long[] jArr = new long[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            jArr[i2] = iArr[i2];
        }
        return jArr;
    }

    public static final f0.c k(Throwable th) {
        h.e(th, "exception");
        return new f0.c(th);
    }

    public static String l(Object obj, String str) {
        h.e(obj, "value");
        return str + " value: " + obj;
    }

    public static boolean m(Object obj, Object obj2) {
        if ((obj instanceof byte[]) && (obj2 instanceof byte[])) {
            return Arrays.equals((byte[]) obj, (byte[]) obj2);
        }
        if ((obj instanceof int[]) && (obj2 instanceof int[])) {
            return Arrays.equals((int[]) obj, (int[]) obj2);
        }
        if ((obj instanceof long[]) && (obj2 instanceof long[])) {
            return Arrays.equals((long[]) obj, (long[]) obj2);
        }
        if ((obj instanceof double[]) && (obj2 instanceof double[])) {
            return Arrays.equals((double[]) obj, (double[]) obj2);
        }
        if ((obj instanceof Object[]) && (obj2 instanceof Object[])) {
            Object[] objArr = (Object[]) obj;
            Object[] objArr2 = (Object[]) obj2;
            if (objArr.length == objArr2.length) {
                Iterable cVar = new t0.c(0, objArr.length - 1, 1);
                if (!(cVar instanceof Collection) || !((Collection) cVar).isEmpty()) {
                    Iterator it = cVar.iterator();
                    while (((t0.b) it).f1265f) {
                        int iNextInt = ((t0.b) it).nextInt();
                        if (!m(objArr[iNextInt], objArr2[iNextInt])) {
                        }
                    }
                }
                return true;
            }
            return false;
        }
        if ((obj instanceof List) && (obj2 instanceof List)) {
            List list = (List) obj;
            List list2 = (List) obj2;
            if (list.size() == list2.size()) {
                Collection collection = (Collection) obj;
                h.e(collection, "<this>");
                Iterable cVar2 = new t0.c(0, collection.size() - 1, 1);
                if (!(cVar2 instanceof Collection) || !((Collection) cVar2).isEmpty()) {
                    Iterator it2 = cVar2.iterator();
                    while (((t0.b) it2).f1265f) {
                        int iNextInt2 = ((t0.b) it2).nextInt();
                        if (!m(list.get(iNextInt2), list2.get(iNextInt2))) {
                        }
                    }
                }
                return true;
            }
            return false;
        }
        if (!(obj instanceof Map) || !(obj2 instanceof Map)) {
            return h.a(obj, obj2);
        }
        Map map = (Map) obj;
        Map map2 = (Map) obj2;
        if (map.size() == map2.size()) {
            if (!map.isEmpty()) {
                for (Map.Entry entry : map.entrySet()) {
                    if (!map2.containsKey(entry.getKey()) || !m(entry.getValue(), map2.get(entry.getKey()))) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static boolean n(Method method, p0.e eVar) {
        Class clsA = eVar.a();
        h.c(clsA, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        return method.getReturnType().equals(clsA);
    }

    public static Activity o(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return o(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Class q(u0.b bVar) {
        h.e(bVar, "<this>");
        Class clsA = ((p0.d) bVar).a();
        if (clsA.isPrimitive()) {
            String name = clsA.getName();
            switch (name.hashCode()) {
                case -1325958191:
                    if (name.equals("double")) {
                        return Double.class;
                    }
                    break;
                case 104431:
                    if (name.equals("int")) {
                        return Integer.class;
                    }
                    break;
                case 3039496:
                    if (name.equals("byte")) {
                        return Byte.class;
                    }
                    break;
                case 3052374:
                    if (name.equals("char")) {
                        return Character.class;
                    }
                    break;
                case 3327612:
                    if (name.equals("long")) {
                        return Long.class;
                    }
                    break;
                case 3625364:
                    if (name.equals("void")) {
                        return Void.class;
                    }
                    break;
                case 64711720:
                    if (name.equals("boolean")) {
                        return Boolean.class;
                    }
                    break;
                case 97526364:
                    if (name.equals("float")) {
                        return Float.class;
                    }
                    break;
                case 109413500:
                    if (name.equals("short")) {
                        return Short.class;
                    }
                    break;
            }
        }
        return clsA;
    }

    public static final double v(AudioManager audioManager) {
        h.e(audioManager, "<this>");
        double d2 = 10000;
        return Math.rint((((double) audioManager.getStreamVolume(3)) / ((double) audioManager.getStreamMaxVolume(3))) * d2) / d2;
    }

    public static void w(String str, Exception exc) {
        if (exc instanceof InvocationTargetException) {
            Throwable cause = exc.getCause();
            if (!(cause instanceof RuntimeException)) {
                throw new RuntimeException(cause);
            }
            throw ((RuntimeException) cause);
        }
        Log.v("Trace", "Unable to call " + str + " via reflection", exc);
    }

    public static h0.d y(h0.d dVar) {
        h.e(dVar, "<this>");
        j0.b bVar = dVar instanceof j0.b ? (j0.b) dVar : null;
        if (bVar == null || (dVar = bVar.f1073f) != null) {
            return dVar;
        }
        i iVar = bVar.f1072e;
        h.b(iVar);
        h0.f fVar = (h0.f) iVar.i(h0.e.f745d);
        h0.d hVar = fVar != null ? new C0.h((AbstractC0032p) fVar, bVar) : bVar;
        bVar.f1073f = hVar;
        return hVar;
    }

    public static boolean z(Context context) {
        Bundle bundle;
        Context applicationContext = context.getApplicationContext();
        try {
            bundle = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128).metaData;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e("ContentSizingFlag", "Could not get metadata", e2);
            bundle = null;
        }
        if (bundle != null) {
            return bundle.getBoolean("io.flutter.embedding.android.EnableContentSizing", false);
        }
        return false;
    }

    public abstract void G(p006f.f fVar, p006f.f fVar2);

    public abstract void H(p006f.f fVar, Thread thread);

    public abstract a K(String str, l lVar);

    public abstract void M(boolean z2);

    @Override // K.c
    public void d(Object obj, String str) {
        t().d(obj, str);
    }

    @Override // K.c
    public void e(Object obj) {
        t().e(obj);
    }

    public abstract boolean f(p006f.g gVar, p006f.c cVar);

    public abstract boolean g(p006f.g gVar, Object obj, Object obj2);

    public abstract boolean h(p006f.g gVar, p006f.f fVar, p006f.f fVar2);

    public abstract Object i();

    public abstract Object p(String str);

    public abstract String r();

    public boolean s() {
        return Boolean.TRUE.equals(p("noResult"));
    }

    public abstract c t();

    public String toString() {
        switch (this.f606d) {
            case 3:
                return r() + " " + ((String) p("sql")) + " " + ((List) p("arguments"));
            default:
                return super.toString();
        }
    }

    public t u() {
        return new t((String) p("sql"), (List) p("arguments"));
    }

    public abstract boolean x();

    public void L(boolean z2) {
    }
}
