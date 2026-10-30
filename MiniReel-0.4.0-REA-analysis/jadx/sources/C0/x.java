package C0;

import C0.x;
import O.AbstractActivityC0007e;
import O.InterfaceC0010h;
import P.l;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.ImageDecoder;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.autofill.AutofillManager;
import android.view.inputmethod.InputMethodManager;
import io.flutter.embedding.engine.FlutterJNI;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class x implements Z.h, K.c, Z.f, Z.b, Z.m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f89d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f90e;

    public /* synthetic */ x(int i2) {
        this.f89d = i2;
    }

    public static int j(x xVar, int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (i2 == 1) {
            return 1;
        }
        if (i2 == 2) {
            return 2;
        }
        throw new IllegalArgumentException("contentSensitivityIndex " + i2 + " not known to the SensitiveContentChannel.");
    }

    private final void o(H.a aVar, Y.k kVar) {
        x xVar = (x) this.f90e;
        if (((Y.j) xVar.f90e) == null) {
            return;
        }
        String str = (String) aVar.f170e;
        Object obj = aVar.f171f;
        str.getClass();
        switch (str) {
            case "create":
                Map map = (Map) obj;
                boolean z2 = map.containsKey("hybrid") && ((Boolean) map.get("hybrid")).booleanValue();
                ByteBuffer byteBufferWrap = map.containsKey("params") ? ByteBuffer.wrap((byte[]) map.get("params")) : null;
                try {
                    if (((Y.j) xVar.f90e).s()) {
                        ((Y.j) xVar.f90e).i(new Y.g(((Integer) map.get("id")).intValue(), (String) map.get("viewType"), 0.0d, 0.0d, 0.0d, 0.0d, ((Integer) map.get("direction")).intValue(), 0, byteBufferWrap));
                        throw null;
                    }
                    if (z2) {
                        ((Y.j) xVar.f90e).p(new Y.g(((Integer) map.get("id")).intValue(), (String) map.get("viewType"), 0.0d, 0.0d, 0.0d, 0.0d, ((Integer) map.get("direction")).intValue(), 3, byteBufferWrap));
                        throw null;
                    }
                    ((Y.j) xVar.f90e).n(new Y.g(((Integer) map.get("id")).intValue(), (String) map.get("viewType"), map.containsKey("top") ? ((Double) map.get("top")).doubleValue() : 0.0d, map.containsKey("left") ? ((Double) map.get("left")).doubleValue() : 0.0d, ((Double) map.get("width")).doubleValue(), ((Double) map.get("height")).doubleValue(), ((Integer) map.get("direction")).intValue(), map.containsKey("hybridFallback") && ((Boolean) map.get("hybridFallback")).booleanValue() ? 2 : 1, byteBufferWrap));
                    throw null;
                } catch (IllegalStateException e2) {
                    kVar.a("error", Log.getStackTraceString(e2), null);
                    return;
                }
            case "offset":
                Map map2 = (Map) obj;
                try {
                    ((Y.j) xVar.f90e).f(((Integer) map2.get("id")).intValue(), ((Double) map2.get("top")).doubleValue(), ((Double) map2.get("left")).doubleValue());
                    kVar.d(null);
                    return;
                } catch (IllegalStateException e3) {
                    kVar.a("error", Log.getStackTraceString(e3), null);
                    return;
                }
            case "resize":
                Map map3 = (Map) obj;
                try {
                    ((Y.j) xVar.f90e).e(new Y.i(((Integer) map3.get("id")).intValue(), ((Double) map3.get("width")).doubleValue(), ((Double) map3.get("height")).doubleValue()), new C.a());
                    return;
                } catch (IllegalStateException e4) {
                    kVar.a("error", Log.getStackTraceString(e4), null);
                    return;
                }
            case "clearFocus":
                try {
                    ((Y.j) xVar.f90e).b(((Integer) obj).intValue());
                    kVar.d(null);
                    return;
                } catch (IllegalStateException e5) {
                    kVar.a("error", Log.getStackTraceString(e5), null);
                    return;
                }
            case "synchronizeToNativeViewHierarchy":
                try {
                    ((Y.j) xVar.f90e).c(((Boolean) obj).booleanValue());
                    kVar.d(null);
                    return;
                } catch (IllegalStateException e6) {
                    kVar.a("error", Log.getStackTraceString(e6), null);
                    return;
                }
            case "touch":
                List list = (List) obj;
                try {
                    ((Y.j) xVar.f90e).k(new Y.h(((Integer) list.get(0)).intValue(), (Number) list.get(1), (Number) list.get(2), ((Integer) list.get(3)).intValue(), ((Integer) list.get(4)).intValue(), list.get(5), list.get(6), ((Integer) list.get(7)).intValue(), ((Integer) list.get(8)).intValue(), (float) ((Double) list.get(9)).doubleValue(), (float) ((Double) list.get(10)).doubleValue(), ((Integer) list.get(11)).intValue(), ((Integer) list.get(12)).intValue(), ((Integer) list.get(13)).intValue(), ((Integer) list.get(14)).intValue(), ((Number) list.get(15)).longValue()));
                    kVar.d(null);
                    return;
                } catch (IllegalStateException e7) {
                    kVar.a("error", Log.getStackTraceString(e7), null);
                    return;
                }
            case "setDirection":
                Map map4 = (Map) obj;
                try {
                    ((Y.j) xVar.f90e).g(((Integer) map4.get("id")).intValue(), ((Integer) map4.get("direction")).intValue());
                    kVar.d(null);
                    return;
                } catch (IllegalStateException e8) {
                    kVar.a("error", Log.getStackTraceString(e8), null);
                    return;
                }
            case "dispose":
                try {
                    ((Y.j) xVar.f90e).l(((Integer) ((Map) obj).get("id")).intValue());
                    kVar.d(null);
                    return;
                } catch (IllegalStateException e9) {
                    kVar.a("error", Log.getStackTraceString(e9), null);
                    return;
                }
            default:
                kVar.b();
                return;
        }
    }

    private final void p(H.a aVar, Y.k kVar) {
        x xVar = (x) this.f90e;
        if (((p002b0.a) xVar.f90e) == null) {
            return;
        }
        String str = (String) aVar.f170e;
        Object obj = aVar.f171f;
        str.getClass();
        switch (str) {
            case "create":
                Map map = (Map) obj;
                if (map.containsKey("params")) {
                    ByteBuffer.wrap((byte[]) map.get("params"));
                }
                try {
                    ((Integer) map.get("id")).intValue();
                    String str2 = (String) map.get("viewType");
                    ((Integer) map.get("direction")).intValue();
                    if (((io.flutter.plugin.platform.i) ((p002b0.a) xVar.f90e).f661e).f880d.f455a.get(str2) != null) {
                        throw new ClassCastException();
                    }
                    throw new IllegalStateException("Trying to create a platform view of unregistered type: " + str2);
                } catch (IllegalStateException e2) {
                    kVar.a("error", Log.getStackTraceString(e2), null);
                    return;
                }
            case "clearFocus":
                int iIntValue = ((Integer) obj).intValue();
                try {
                    if (((io.flutter.plugin.platform.i) ((p002b0.a) xVar.f90e).f661e).f886j.get(iIntValue) != null) {
                        throw new ClassCastException();
                    }
                    Log.e("PlatformViewsController2", "Clearing focus on an unknown view with id: " + iIntValue);
                    kVar.d(null);
                    return;
                } catch (IllegalStateException e3) {
                    kVar.a("error", Log.getStackTraceString(e3), null);
                    return;
                }
            case "touch":
                List list = (List) obj;
                int iIntValue2 = ((Integer) list.get(0)).intValue();
                ((Integer) list.get(3)).getClass();
                ((Integer) list.get(4)).getClass();
                list.get(5);
                list.get(6);
                ((Integer) list.get(7)).getClass();
                ((Integer) list.get(8)).getClass();
                ((Double) list.get(9)).getClass();
                ((Double) list.get(10)).getClass();
                ((Integer) list.get(11)).getClass();
                ((Integer) list.get(12)).getClass();
                ((Integer) list.get(13)).getClass();
                ((Integer) list.get(14)).getClass();
                ((Number) list.get(15)).longValue();
                try {
                    p002b0.a aVar2 = (p002b0.a) xVar.f90e;
                    aVar2.getClass();
                    io.flutter.plugin.platform.i iVar = (io.flutter.plugin.platform.i) aVar2.f661e;
                    float f2 = iVar.f881e.getResources().getDisplayMetrics().density;
                    if (iVar.f886j.get(iIntValue2) != null) {
                        throw new ClassCastException();
                    }
                    Log.e("PlatformViewsController2", "Sending touch to an unknown view with id: " + iIntValue2);
                    kVar.d(null);
                    return;
                } catch (IllegalStateException e4) {
                    kVar.a("error", Log.getStackTraceString(e4), null);
                    return;
                }
            case "setDirection":
                Map map2 = (Map) obj;
                int iIntValue3 = ((Integer) map2.get("id")).intValue();
                ((Integer) map2.get("direction")).getClass();
                try {
                    if (((io.flutter.plugin.platform.i) ((p002b0.a) xVar.f90e).f661e).f886j.get(iIntValue3) != null) {
                        throw new ClassCastException();
                    }
                    Log.e("PlatformViewsController2", "Setting direction to an unknown view with id: " + iIntValue3);
                    kVar.d(null);
                    return;
                } catch (IllegalStateException e5) {
                    kVar.a("error", Log.getStackTraceString(e5), null);
                    return;
                }
            case "isSurfaceControlEnabled":
                FlutterJNI flutterJNI = ((io.flutter.plugin.platform.i) ((p002b0.a) xVar.f90e).f661e).f883g;
                kVar.d(Boolean.valueOf(flutterJNI != null ? flutterJNI.IsSurfaceControlEnabled() : false));
                return;
            case "dispose":
                try {
                    ((p002b0.a) xVar.f90e).l(((Integer) ((Map) obj).get("id")).intValue());
                    kVar.d(null);
                    return;
                } catch (IllegalStateException e6) {
                    kVar.a("error", Log.getStackTraceString(e6), null);
                    return;
                }
            default:
                kVar.b();
                return;
        }
    }

    private final void q(H.a aVar, Y.k kVar) {
        H.a aVar2 = (H.a) this.f90e;
        if (((p004c0.a) aVar2.f171f) == null) {
            return;
        }
        String str = (String) aVar.f170e;
        Object obj = aVar.f171f;
        str.getClass();
        if (!str.equals("ProcessText.processTextAction")) {
            if (!str.equals("ProcessText.queryTextActions")) {
                kVar.b();
                return;
            }
            try {
                kVar.d(((p004c0.a) aVar2.f171f).b());
                return;
            } catch (IllegalStateException e2) {
                kVar.a("error", e2.getMessage(), null);
                return;
            }
        }
        try {
            ArrayList arrayList = (ArrayList) obj;
            ((p004c0.a) aVar2.f171f).a((String) arrayList.get(0), (String) arrayList.get(1), ((Boolean) arrayList.get(2)).booleanValue(), kVar);
        } catch (IllegalStateException e3) {
            kVar.a("error", e3.getMessage(), null);
        }
    }

    private final void r(H.a aVar, Y.k kVar) {
        x xVar = (x) this.f90e;
        if (((H.a) xVar.f90e) == null) {
            return;
        }
        String str = (String) aVar.f170e;
        str.getClass();
        boolean z2 = true;
        switch (str) {
            case "Scribe.isFeatureAvailable":
                try {
                    H.a aVar2 = (H.a) xVar.f90e;
                    if (Build.VERSION.SDK_INT >= 34) {
                        if (((InputMethodManager) aVar2.f170e).isStylusHandwritingAvailable()) {
                        }
                        kVar.d(Boolean.valueOf(z2));
                        break;
                    } else {
                        aVar2.getClass();
                    }
                    z2 = false;
                    kVar.d(Boolean.valueOf(z2));
                    break;
                } catch (IllegalStateException e2) {
                    kVar.a("error", e2.getMessage(), null);
                    return;
                }
                break;
            case "Scribe.startStylusHandwriting":
                if (Build.VERSION.SDK_INT >= 33) {
                    try {
                        H.a aVar3 = (H.a) xVar.f90e;
                        ((InputMethodManager) aVar3.f170e).startStylusHandwriting((View) aVar3.f171f);
                        kVar.d(null);
                    } catch (IllegalStateException e3) {
                        kVar.a("error", e3.getMessage(), null);
                        return;
                    }
                    break;
                } else {
                    kVar.a("error", "Requires API level 33 or higher.", null);
                    break;
                }
                break;
            case "Scribe.isStylusHandwritingAvailable":
                if (Build.VERSION.SDK_INT >= 34) {
                    try {
                        kVar.d(Boolean.valueOf(((InputMethodManager) ((H.a) xVar.f90e).f170e).isStylusHandwritingAvailable()));
                    } catch (IllegalStateException e4) {
                        kVar.a("error", e4.getMessage(), null);
                        return;
                    }
                    break;
                } else {
                    kVar.a("error", "Requires API level 34 or higher.", null);
                    break;
                }
                break;
            default:
                kVar.b();
                break;
        }
    }

    private final void s(H.a aVar, Y.k kVar) {
        x xVar = (x) this.f90e;
        if (((J.m) xVar.f90e) == null) {
            return;
        }
        String str = (String) aVar.f170e;
        str.getClass();
        int i2 = 2;
        switch (str) {
            case "SensitiveContent.getContentSensitivity":
                try {
                    int iD = ((J.m) xVar.f90e).d();
                    if (iD == 0) {
                        i2 = 0;
                    } else if (iD == 1) {
                        i2 = 1;
                    } else if (iD != 2) {
                        i2 = 3;
                    }
                    kVar.d(Integer.valueOf(i2));
                    break;
                } catch (IllegalArgumentException | IllegalStateException e2) {
                    kVar.a("error", e2.getMessage(), null);
                    return;
                }
                break;
            case "SensitiveContent.setContentSensitivity":
                try {
                    ((J.m) xVar.f90e).e(j(xVar, ((Integer) aVar.f171f).intValue()));
                    break;
                } catch (IllegalArgumentException | IllegalStateException e3) {
                    kVar.a("error", e3.getMessage(), null);
                    return;
                }
                break;
            case "SensitiveContent.isSupported":
                ((J.m) xVar.f90e).getClass();
                kVar.d(Boolean.valueOf(Build.VERSION.SDK_INT >= 35));
                break;
            default:
                kVar.b();
                break;
        }
    }

    private final void t(H.a aVar, Y.k kVar) {
        x xVar = (x) this.f90e;
        if (((io.flutter.plugin.editing.h) xVar.f90e) == null) {
            return;
        }
        String str = (String) aVar.f170e;
        Object obj = aVar.f171f;
        str.getClass();
        if (!str.equals("SpellCheck.initiateSpellCheck")) {
            kVar.b();
            return;
        }
        try {
            ArrayList arrayList = (ArrayList) obj;
            ((io.flutter.plugin.editing.h) xVar.f90e).a((String) arrayList.get(0), (String) arrayList.get(1), kVar);
        } catch (IllegalStateException e2) {
            kVar.a("error", e2.getMessage(), null);
        }
    }

    @Override // Z.f
    public void a(String str, Z.d dVar, H.b bVar) {
        ((Q.i) this.f90e).a(str, dVar, bVar);
    }

    @Override // Z.f
    public void b(String str, ByteBuffer byteBuffer, Z.e eVar) {
        ((Q.i) this.f90e).b(str, byteBuffer, eVar);
    }

    @Override // Z.f
    public void c(String str, ByteBuffer byteBuffer) {
        ((Q.i) this.f90e).b(str, byteBuffer, null);
    }

    @Override // K.c
    public void d(Object obj, String str) {
        ((Y.k) this.f90e).a("sqlite_error", str, obj);
    }

    @Override // K.c
    public void e(Object obj) {
        ((Y.k) this.f90e).d(obj);
    }

    @Override // Z.f
    public H.b f(Z.k kVar) {
        return ((Q.i) this.f90e).f(kVar);
    }

    @Override // Z.b
    public void g(Object obj, H.a aVar) {
        G.f fVar = (G.f) this.f90e;
        if (((io.flutter.view.c) fVar.f166f) == null) {
            aVar.o(null);
            return;
        }
        HashMap map = (HashMap) obj;
        String str = (String) map.get("type");
        HashMap map2 = (HashMap) map.get("data");
        str.getClass();
        switch (str) {
            case "tooltip":
                String str2 = (String) map2.get("message");
                if (str2 != null) {
                    io.flutter.view.n nVar = (io.flutter.view.n) ((io.flutter.view.c) fVar.f166f).f931a;
                    if (Build.VERSION.SDK_INT < 28) {
                        AccessibilityEvent accessibilityEventD = nVar.d(0, 32);
                        accessibilityEventD.getText().add(str2);
                        nVar.h(accessibilityEventD);
                    }
                    break;
                }
                break;
            case "announce":
                String str3 = (String) map2.get("message");
                if (str3 != null) {
                    io.flutter.view.c cVar = (io.flutter.view.c) fVar.f166f;
                    if (Build.VERSION.SDK_INT >= 36) {
                        cVar.getClass();
                        Log.w("AccessibilityBridge", "Using AnnounceSemanticsEvent for accessibility is deprecated on Android. Migrate to using semantic properties for a more robust and accessible user experience.\nFlutter: If you are unsure why you are seeing this bug, it might be because you are using a widget that calls this method. See https://github.com/flutter/flutter/issues/165510 for more details.\nAndroid documentation: https://developer.android.com/reference/android/view/View#announceForAccessibility(java.lang.CharSequence)");
                    }
                    ((io.flutter.view.n) cVar.f931a).f1023a.announceForAccessibility(str3);
                    break;
                }
                break;
            case "tap":
                Integer num = (Integer) map.get("nodeId");
                if (num != null) {
                    io.flutter.view.c cVar2 = (io.flutter.view.c) fVar.f166f;
                    ((io.flutter.view.n) cVar2.f931a).g(num.intValue(), 1);
                    break;
                }
                break;
            case "focus":
                Integer num2 = (Integer) map.get("nodeId");
                if (num2 != null) {
                    io.flutter.view.c cVar3 = (io.flutter.view.c) fVar.f166f;
                    ((io.flutter.view.n) cVar3.f931a).g(num2.intValue(), 8);
                    break;
                }
                break;
            case "longPress":
                Integer num3 = (Integer) map.get("nodeId");
                if (num3 != null) {
                    io.flutter.view.c cVar4 = (io.flutter.view.c) fVar.f166f;
                    ((io.flutter.view.n) cVar4.f931a).g(num3.intValue(), 2);
                    break;
                }
                break;
        }
        aVar.o(null);
    }

    @Override // Z.h
    public void h() {
        this.f90e = null;
    }

    @Override // Z.f
    public void i(String str, Z.d dVar) {
        ((Q.i) this.f90e).a(str, dVar, null);
    }

    @Override // Z.h
    public void k(Object obj, Z.g gVar) {
        this.f90e = gVar;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [S.a] */
    public Bitmap l(ByteBuffer byteBuffer, S.d dVar) {
        try {
            return ImageDecoder.decodeBitmap(ImageDecoder.createSource(byteBuffer), new ImageDecoder.OnHeaderDecodedListener() { // from class: S.a
                @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
                public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
                    x xVar = this.f493a;
                    ColorSpace.Named unused = ColorSpace.Named.SRGB;
                    imageDecoder.setTargetColorSpace(ColorSpace.get(ColorSpace.Named.SRGB));
                    imageDecoder.setAllocator(1);
                    l lVar = (l) xVar.f90e;
                    if (lVar != null) {
                        Size size = imageInfo.getSize();
                        FlutterJNI.nativeImageHeaderCallback(lVar.f456a, size.getWidth(), size.getHeight());
                    }
                }
            });
        } catch (IOException e2) {
            Log.e("FlutterImageDecoderImplDefault", "Failed to decode image", e2);
            return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:211:0x03bd  */
    /* JADX WARN: Code duplicated, block: B:9:0x0035  */
    @Override // Z.m
    public void m(H.a aVar, Y.k kVar) {
        Y.e eVarA;
        ClipDescription primaryClipDescription;
        Bundle bundle;
        byte b2 = 11;
        boolean zHasMimeType = false;
        zHasMimeType = false;
        switch (this.f89d) {
            case 11:
                H.a aVar2 = (H.a) this.f90e;
                if (((x) aVar2.f171f) != null) {
                    String str = (String) aVar.f170e;
                    str.getClass();
                    if (!str.equals("Localization.getStringResource")) {
                        kVar.b();
                    } else {
                        JSONObject jSONObject = (JSONObject) aVar.f171f;
                        try {
                            kVar.d(((x) aVar2.f171f).n(jSONObject.getString("key"), jSONObject.has("locale") ? jSONObject.getString("locale") : null));
                        } catch (JSONException e2) {
                            kVar.a("error", e2.getMessage(), null);
                            return;
                        }
                    }
                    break;
                }
                break;
            case 12:
                x xVar = (x) this.f90e;
                if (((p002b0.a) xVar.f90e) != null) {
                    String str2 = (String) aVar.f170e;
                    try {
                        if (str2.hashCode() == -1307105544 && str2.equals("activateSystemCursor")) {
                            try {
                                ((p002b0.a) xVar.f90e).j((String) ((HashMap) aVar.f171f).get("kind"));
                                kVar.d(Boolean.TRUE);
                            } catch (Exception e3) {
                                kVar.a("error", "Error when setting cursors: " + e3.getMessage(), null);
                            }
                        }
                    } catch (Exception e4) {
                        kVar.a("error", "Unhandled error: " + e4.getMessage(), null);
                        return;
                    }
                    break;
                }
                break;
            case 13:
            case 17:
            case 18:
            case 22:
            case 24:
            default:
                H.a aVar3 = (H.a) this.f90e;
                if (((io.flutter.plugin.editing.j) aVar3.f171f) != null) {
                    String str3 = (String) aVar.f170e;
                    Object obj = aVar.f171f;
                    str3.getClass();
                    switch (str3) {
                        case "TextInput.setPlatformViewClient":
                            try {
                                JSONObject jSONObject2 = (JSONObject) obj;
                                int i2 = jSONObject2.getInt("platformViewId");
                                boolean zOptBoolean = jSONObject2.optBoolean("usesVirtualDisplay", false);
                                io.flutter.plugin.editing.m mVar = ((io.flutter.plugin.editing.j) aVar3.f171f).f847a;
                                View view = mVar.f853a;
                                if (zOptBoolean) {
                                    view.requestFocus();
                                    mVar.f857e = new io.flutter.plugin.editing.l(3, i2);
                                    mVar.f854b.restartInput(view);
                                    mVar.f861i = false;
                                } else {
                                    mVar.f857e = new io.flutter.plugin.editing.l(4, i2);
                                    mVar.f862j = null;
                                }
                                kVar.d(null);
                                break;
                            } catch (JSONException e5) {
                                kVar.a("error", e5.getMessage(), null);
                            }
                            break;
                        case "TextInput.setEditingState":
                            try {
                                ((io.flutter.plugin.editing.j) aVar3.f171f).c(Y.q.a((JSONObject) obj));
                                kVar.d(null);
                                break;
                            } catch (JSONException e6) {
                                kVar.a("error", e6.getMessage(), null);
                                return;
                            }
                            break;
                        case "TextInput.setClient":
                            try {
                                JSONArray jSONArray = (JSONArray) obj;
                                ((io.flutter.plugin.editing.j) aVar3.f171f).a(jSONArray.getInt(0), Y.o.a(jSONArray.getJSONObject(1)));
                                kVar.d(null);
                                break;
                            } catch (NoSuchFieldException | JSONException e7) {
                                kVar.a("error", e7.getMessage(), null);
                                return;
                            }
                            break;
                        case "TextInput.hide":
                            io.flutter.plugin.editing.m mVar2 = ((io.flutter.plugin.editing.j) aVar3.f171f).f847a;
                            if (mVar2.f857e.f851a == 4) {
                                mVar2.c();
                            } else {
                                View view2 = mVar2.f853a;
                                mVar2.c();
                                mVar2.f854b.hideSoftInputFromWindow(view2.getApplicationWindowToken(), 0);
                            }
                            kVar.d(null);
                            break;
                        case "TextInput.show":
                            io.flutter.plugin.editing.m mVar3 = ((io.flutter.plugin.editing.j) aVar3.f171f).f847a;
                            InputMethodManager inputMethodManager = mVar3.f854b;
                            View view3 = mVar3.f853a;
                            Y.o oVar = mVar3.f858f;
                            if (oVar == null || oVar.f572g.f578a != 11) {
                                view3.requestFocus();
                                inputMethodManager.showSoftInput(view3, 0);
                            } else {
                                mVar3.c();
                                inputMethodManager.hideSoftInputFromWindow(view3.getApplicationWindowToken(), 0);
                            }
                            kVar.d(null);
                            break;
                        case "TextInput.sendAppPrivateCommand":
                            try {
                                JSONObject jSONObject3 = (JSONObject) obj;
                                String string = jSONObject3.getString("action");
                                String string2 = jSONObject3.getString("data");
                                if (string2 == null || string2.isEmpty()) {
                                    bundle = null;
                                } else {
                                    bundle = new Bundle();
                                    bundle.putString("data", string2);
                                }
                                io.flutter.plugin.editing.m mVar4 = ((io.flutter.plugin.editing.j) aVar3.f171f).f847a;
                                mVar4.f854b.sendAppPrivateCommand(mVar4.f853a, string, bundle);
                                kVar.d(null);
                                break;
                            } catch (JSONException e8) {
                                kVar.a("error", e8.getMessage(), null);
                                return;
                            }
                            break;
                        case "TextInput.setEditableSizeAndTransform":
                            try {
                                JSONObject jSONObject4 = (JSONObject) obj;
                                double d2 = jSONObject4.getDouble("width");
                                double d3 = jSONObject4.getDouble("height");
                                JSONArray jSONArray2 = jSONObject4.getJSONArray("transform");
                                double[] dArr = new double[16];
                                for (int i3 = 0; i3 < 16; i3++) {
                                    dArr[i3] = jSONArray2.getDouble(i3);
                                }
                                ((io.flutter.plugin.editing.j) aVar3.f171f).b(d2, d3, dArr);
                                kVar.d(null);
                                break;
                            } catch (JSONException e9) {
                                kVar.a("error", e9.getMessage(), null);
                                return;
                            }
                            break;
                        case "TextInput.finishAutofillContext":
                            io.flutter.plugin.editing.j jVar = (io.flutter.plugin.editing.j) aVar3.f171f;
                            boolean zBooleanValue = ((Boolean) obj).booleanValue();
                            if (Build.VERSION.SDK_INT >= 26) {
                                AutofillManager autofillManager = jVar.f847a.f855c;
                                if (autofillManager != null) {
                                    if (zBooleanValue) {
                                        autofillManager.commit();
                                    } else {
                                        autofillManager.cancel();
                                    }
                                }
                            } else {
                                jVar.getClass();
                            }
                            kVar.d(null);
                            break;
                        case "TextInput.clearClient":
                            io.flutter.plugin.editing.m mVar5 = ((io.flutter.plugin.editing.j) aVar3.f171f).f847a;
                            View view4 = mVar5.f853a;
                            if (mVar5.f857e.f851a != 3) {
                                mVar5.f860h.e(mVar5);
                                mVar5.c();
                                mVar5.f858f = null;
                                mVar5.d(null);
                                mVar5.f857e = new io.flutter.plugin.editing.l(1, 0);
                                mVar5.m = null;
                                int i4 = p012l.d.f1079a;
                                p012l.r rVarA = p012l.c.a(view4);
                                if (rVarA != null && !rVarA.f1102a.m(8)) {
                                    mVar5.f854b.restartInput(view4);
                                }
                            }
                            kVar.d(null);
                            break;
                        case "TextInput.requestAutofill":
                            io.flutter.plugin.editing.m mVar6 = ((io.flutter.plugin.editing.j) aVar3.f171f).f847a;
                            View view5 = mVar6.f853a;
                            if (Build.VERSION.SDK_INT >= 26 && mVar6.f855c != null && mVar6.f859g != null) {
                                String str4 = (String) mVar6.f858f.f575j.f174d;
                                int[] iArr = new int[2];
                                view5.getLocationOnScreen(iArr);
                                Rect rect = new Rect(mVar6.m);
                                rect.offset(iArr[0], iArr[1]);
                                mVar6.f855c.notifyViewEntered(view5, str4.hashCode(), rect);
                            }
                            kVar.d(null);
                            break;
                        default:
                            kVar.b();
                            break;
                    }
                }
                break;
            case 14:
                H.a aVar4 = (H.a) this.f90e;
                if (((p002b0.a) aVar4.f171f) != null) {
                    String str5 = (String) aVar.f170e;
                    Object obj2 = aVar.f171f;
                    try {
                        switch (str5.hashCode()) {
                            case -1501580720:
                                if (str5.equals("SystemNavigator.setFrameworkHandlesBack")) {
                                    b2 = 9;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            case -931781241:
                                if (str5.equals("Share.invoke")) {
                                    b2 = 14;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            case -766342101:
                                if (str5.equals("SystemNavigator.pop")) {
                                    b2 = 10;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            case -720677196:
                                if (str5.equals("Clipboard.setData")) {
                                    b2 = 12;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            case -577225884:
                                if (str5.equals("SystemChrome.setSystemUIChangeListener")) {
                                    b2 = 6;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            case -548468504:
                                if (str5.equals("SystemChrome.setApplicationSwitcherDescription")) {
                                    b2 = 3;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            case -247230243:
                                if (str5.equals("HapticFeedback.vibrate")) {
                                    b2 = 1;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            case -215273374:
                                if (str5.equals("SystemSound.play")) {
                                    b2 = 0;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            case 241845679:
                                if (str5.equals("SystemChrome.restoreSystemUIOverlays")) {
                                    b2 = 7;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            case 875995648:
                                if (str5.equals("Clipboard.hasStrings")) {
                                    b2 = 13;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            case 1128339786:
                                if (str5.equals("SystemChrome.setEnabledSystemUIMode")) {
                                    b2 = 5;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            case 1390477857:
                                if (str5.equals("SystemChrome.setSystemUIOverlayStyle")) {
                                    b2 = 8;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            case 1514180520:
                                if (!str5.equals("Clipboard.getData")) {
                                    b2 = -1;
                                }
                                break;
                            case 1674312266:
                                if (str5.equals("SystemChrome.setEnabledSystemUIOverlays")) {
                                    b2 = 4;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            case 2119655719:
                                if (str5.equals("SystemChrome.setPreferredOrientations")) {
                                    b2 = 2;
                                } else {
                                    b2 = -1;
                                }
                                break;
                            default:
                                b2 = -1;
                                break;
                        }
                        switch (b2) {
                            case 0:
                                try {
                                    int iC = z.c((String) obj2);
                                    io.flutter.plugin.platform.e eVar = (io.flutter.plugin.platform.e) ((p002b0.a) aVar4.f171f).f661e;
                                    if (iC == 1) {
                                        eVar.f874a.getWindow().getDecorView().playSoundEffect(0);
                                    }
                                    kVar.d(null);
                                } catch (NoSuchFieldException e10) {
                                    kVar.a("error", e10.getMessage(), null);
                                    return;
                                }
                                break;
                            case 1:
                                try {
                                    ((p002b0.a) aVar4.f171f).u(z.b((String) obj2));
                                    kVar.d(null);
                                } catch (NoSuchFieldException e11) {
                                    kVar.a("error", e11.getMessage(), null);
                                    return;
                                }
                                break;
                            case 2:
                                try {
                                    ((io.flutter.plugin.platform.e) ((p002b0.a) aVar4.f171f).f661e).f874a.setRequestedOrientation(H.a.t(aVar4, (JSONArray) obj2));
                                    kVar.d(null);
                                } catch (NoSuchFieldException | JSONException e12) {
                                    kVar.a("error", e12.getMessage(), null);
                                    return;
                                }
                                break;
                            case 3:
                                try {
                                    JSONObject jSONObject5 = (JSONObject) obj2;
                                    int i5 = jSONObject5.getInt("primaryColor");
                                    if (i5 != 0) {
                                        i5 |= -16777216;
                                    }
                                    String string3 = jSONObject5.getString("label");
                                    Activity activity = ((io.flutter.plugin.platform.e) ((p002b0.a) aVar4.f171f).f661e).f874a;
                                    if (Build.VERSION.SDK_INT < 28) {
                                        activity.setTaskDescription(new ActivityManager.TaskDescription(string3, (Bitmap) null, i5));
                                    } else {
                                        activity.setTaskDescription(A.c.c(string3, i5));
                                    }
                                    kVar.d(null);
                                } catch (JSONException e13) {
                                    kVar.a("error", e13.getMessage(), null);
                                    return;
                                }
                                break;
                            case 4:
                                try {
                                    ((p002b0.a) aVar4.f171f).r(H.a.u(aVar4, (JSONArray) obj2));
                                    kVar.d(null);
                                } catch (NoSuchFieldException | JSONException e14) {
                                    kVar.a("error", e14.getMessage(), null);
                                    return;
                                }
                                break;
                            case 5:
                                try {
                                    ((p002b0.a) aVar4.f171f).t(H.a.v(aVar4, (String) obj2));
                                    kVar.d(null);
                                } catch (NoSuchFieldException | JSONException e15) {
                                    kVar.a("error", e15.getMessage(), null);
                                    return;
                                }
                                break;
                            case 6:
                                io.flutter.plugin.platform.e eVar2 = (io.flutter.plugin.platform.e) ((p002b0.a) aVar4.f171f).f661e;
                                View decorView = eVar2.f874a.getWindow().getDecorView();
                                decorView.setOnSystemUiVisibilityChangeListener(new io.flutter.plugin.platform.d(eVar2, decorView));
                                kVar.d(null);
                                break;
                            case 7:
                                ((io.flutter.plugin.platform.e) ((p002b0.a) aVar4.f171f).f661e).b();
                                kVar.d(null);
                                break;
                            case 8:
                                try {
                                    ((io.flutter.plugin.platform.e) ((p002b0.a) aVar4.f171f).f661e).a(H.a.w(aVar4, (JSONObject) obj2));
                                    kVar.d(null);
                                } catch (NoSuchFieldException | JSONException e16) {
                                    kVar.a("error", e16.getMessage(), null);
                                    return;
                                }
                                break;
                            case 9:
                                boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
                                InterfaceC0010h interfaceC0010h = ((io.flutter.plugin.platform.e) ((p002b0.a) aVar4.f171f).f661e).f876c;
                                if (interfaceC0010h != null) {
                                    ((AbstractActivityC0007e) interfaceC0010h).h(zBooleanValue2);
                                }
                                kVar.d(null);
                                break;
                            case 10:
                                ((io.flutter.plugin.platform.e) ((p002b0.a) aVar4.f171f).f661e).f874a.finish();
                                kVar.d(null);
                                break;
                            case 11:
                                String str6 = (String) obj2;
                                if (str6 != null) {
                                    try {
                                        eVarA = Y.e.a(str6);
                                    } catch (NoSuchFieldException unused) {
                                        kVar.a("error", "No such clipboard content format: ".concat(str6), null);
                                        eVarA = null;
                                    }
                                } else {
                                    eVarA = null;
                                }
                                CharSequence charSequenceM = ((p002b0.a) aVar4.f171f).m(eVarA);
                                if (charSequenceM != null) {
                                    JSONObject jSONObject6 = new JSONObject();
                                    jSONObject6.put("text", charSequenceM);
                                    kVar.d(jSONObject6);
                                } else {
                                    kVar.d(null);
                                }
                                break;
                            case 12:
                                ((ClipboardManager) ((io.flutter.plugin.platform.e) ((p002b0.a) aVar4.f171f).f661e).f874a.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text label?", ((JSONObject) obj2).getString("text")));
                                kVar.d(null);
                                break;
                            case 13:
                                ClipboardManager clipboardManager = (ClipboardManager) ((io.flutter.plugin.platform.e) ((p002b0.a) aVar4.f171f).f661e).f874a.getSystemService("clipboard");
                                if (clipboardManager.hasPrimaryClip() && (primaryClipDescription = clipboardManager.getPrimaryClipDescription()) != null) {
                                    zHasMimeType = primaryClipDescription.hasMimeType("text/*");
                                }
                                JSONObject jSONObject7 = new JSONObject();
                                jSONObject7.put("value", zHasMimeType);
                                kVar.d(jSONObject7);
                                break;
                            case 14:
                                io.flutter.plugin.platform.e eVar3 = (io.flutter.plugin.platform.e) ((p002b0.a) aVar4.f171f).f661e;
                                Intent intent = new Intent();
                                intent.setAction("android.intent.action.SEND");
                                intent.setType("text/plain");
                                intent.putExtra("android.intent.extra.TEXT", (String) obj2);
                                eVar3.f874a.startActivity(Intent.createChooser(intent, null));
                                kVar.d(null);
                                break;
                            default:
                                kVar.b();
                                break;
                        }
                    } catch (JSONException e17) {
                        kVar.a("error", "JSON error: " + e17.getMessage(), null);
                        return;
                    }
                    kVar.a("error", "JSON error: " + e17.getMessage(), null);
                }
                break;
            case 15:
                o(aVar, kVar);
                break;
            case 16:
                p(aVar, kVar);
                break;
            case 19:
                q(aVar, kVar);
                break;
            case 20:
                Y.l lVar = (Y.l) this.f90e;
                String str7 = (String) aVar.f170e;
                Object obj3 = aVar.f171f;
                str7.getClass();
                if (str7.equals("get")) {
                    lVar.f560f = true;
                    if (lVar.f559e || !lVar.f555a) {
                        kVar.d(Y.l.a(lVar.f556b));
                    } else {
                        lVar.f558d = kVar;
                    }
                } else if (str7.equals("put")) {
                    lVar.f556b = (byte[]) obj3;
                    kVar.d(null);
                } else {
                    kVar.b();
                }
                break;
            case 21:
                r(aVar, kVar);
                break;
            case 23:
                s(aVar, kVar);
                break;
            case 25:
                t(aVar, kVar);
                break;
        }
    }

    public String n(String str, String str2) {
        p001a0.a aVar = (p001a0.a) this.f90e;
        Context contextCreateConfigurationContext = aVar.f608b;
        if (str2 != null) {
            Locale localeA = p001a0.a.a(str2);
            Configuration configuration = new Configuration(aVar.f608b.getResources().getConfiguration());
            configuration.setLocale(localeA);
            contextCreateConfigurationContext = aVar.f608b.createConfigurationContext(configuration);
        }
        int identifier = contextCreateConfigurationContext.getResources().getIdentifier(str, "string", aVar.f608b.getPackageName());
        if (identifier != 0) {
            return contextCreateConfigurationContext.getResources().getString(identifier);
        }
        return null;
    }

    public String toString() {
        switch (this.f89d) {
            case 0:
                return "<" + ((String) this.f90e) + '>';
            default:
                return super.toString();
        }
    }

    public /* synthetic */ x(int i2, Object obj) {
        this.f89d = i2;
        this.f90e = obj;
    }

    public x(Q.b bVar, int i2) {
        this.f89d = i2;
        switch (i2) {
            case 17:
                new H.e(bVar, "flutter/platform_views_2", Z.q.f598a, null).j(new x(16, this));
                break;
            case 18:
                new H.e(bVar, "flutter/platform_views", Z.q.f598a, null).j(new x(15, this));
                break;
            case 22:
                new H.e(bVar, "flutter/scribe", Z.k.f594a, null).j(new x(21, this));
                break;
            case 24:
                new H.e(bVar, "flutter/sensitivecontent", Z.q.f598a, null).j(new x(23, this));
                break;
            case 26:
                new H.e(bVar, "flutter/spellcheck", Z.q.f598a, null).j(new x(25, this));
                break;
            default:
                new H.e(bVar, "flutter/mousecursor", Z.q.f598a, null).j(new x(12, this));
                break;
        }
    }

    public x(Z.f fVar) {
        this.f89d = 10;
        new H.e(fVar, "flutter/keyboard", Z.q.f598a, null).j(new H.a(this));
    }
}
