package H;

import C0.x;
import C0.z;
import J.l;
import O.A;
import O.C;
import P.k;
import Y.f;
import Y.g;
import Y.j;
import Z.m;
import Z.q;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.plugin.platform.h;
import io.flutter.plugin.platform.i;
import io.flutter.view.n;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class a implements C, m, Z.c, Z.d, h, j, p020w.h {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static a f167g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static G.b f168h;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f169d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f170e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f171f;

    public /* synthetic */ a(int i2, Object obj, Object obj2) {
        this.f169d = i2;
        this.f171f = obj;
        this.f170e = obj2;
    }

    /* JADX WARN: Code duplicated, block: B:54:0x0088 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:55:0x0089 A[RETURN] */
    public static int t(a aVar, JSONArray jSONArray) throws JSONException, NoSuchFieldException {
        int i2;
        String str;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < jSONArray.length(); i5++) {
            String string = jSONArray.getString(i5);
            int[] iArrB = p014o.c.b(4);
            int length = iArrB.length;
            int i6 = 0;
            while (true) {
                if (i6 >= length) {
                    throw new NoSuchFieldException("No such DeviceOrientation: " + string);
                }
                i2 = iArrB[i6];
                if (i2 == 1) {
                    str = "DeviceOrientation.portraitUp";
                } else if (i2 == 2) {
                    str = "DeviceOrientation.portraitDown";
                } else if (i2 == 3) {
                    str = "DeviceOrientation.landscapeLeft";
                } else {
                    if (i2 != 4) {
                        throw null;
                    }
                    str = "DeviceOrientation.landscapeRight";
                }
                if (str.equals(string)) {
                    break;
                }
                i6++;
            }
            int iA = p014o.c.a(i2);
            if (iA == 0) {
                i3 |= 1;
            } else if (iA == 1) {
                i3 |= 4;
            } else if (iA == 2) {
                i3 |= 2;
            } else if (iA == 3) {
                i3 |= 8;
            }
            if (i4 == 0) {
                i4 = i3;
            }
        }
        if (i3 == 0) {
            return -1;
        }
        switch (i3) {
            case 2:
                return 0;
            case 3:
            case 6:
            case 7:
            case 9:
            case 12:
            case 13:
            case 14:
                if (i4 == 2) {
                    return 0;
                }
                if (i4 != 4) {
                    if (i4 != 8) {
                        return 1;
                    }
                    return 8;
                }
                return 9;
            case 4:
                return 9;
            case 5:
                return 12;
            case 8:
                return 8;
            case 10:
                return 11;
            case 11:
                return 2;
            case 15:
                return 13;
            default:
                return 1;
        }
    }

    public static ArrayList u(a aVar, JSONArray jSONArray) throws JSONException, NoSuchFieldException {
        f fVar;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            String string = jSONArray.getString(i2);
            f[] fVarArrValues = f.values();
            int length = fVarArrValues.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    throw new NoSuchFieldException("No such SystemUiOverlay: " + string);
                }
                fVar = fVarArrValues[i3];
                if (fVar.f530d.equals(string)) {
                    break;
                }
                i3++;
            }
            int iOrdinal = fVar.ordinal();
            if (iOrdinal == 0) {
                arrayList.add(f.f527e);
            } else if (iOrdinal == 1) {
                arrayList.add(f.f528f);
            }
        }
        return arrayList;
    }

    public static int v(a aVar, String str) throws NoSuchFieldException {
        String str2;
        for (int i2 : p014o.c.b(4)) {
            if (i2 == 1) {
                str2 = "SystemUiMode.leanBack";
            } else if (i2 == 2) {
                str2 = "SystemUiMode.immersive";
            } else if (i2 == 3) {
                str2 = "SystemUiMode.immersiveSticky";
            } else {
                if (i2 != 4) {
                    throw null;
                }
                str2 = "SystemUiMode.edgeToEdge";
            }
            if (str2.equals(str)) {
                int iA = p014o.c.a(i2);
                if (iA == 0) {
                    return 1;
                }
                if (iA != 1) {
                    return iA != 2 ? 4 : 3;
                }
                return 2;
            }
        }
        throw new NoSuchFieldException("No such SystemUiMode: " + str);
    }

    public static l w(a aVar, JSONObject jSONObject) {
        return new l(!jSONObject.isNull("statusBarColor") ? Integer.valueOf(jSONObject.getInt("statusBarColor")) : null, !jSONObject.isNull("statusBarIconBrightness") ? z.a(jSONObject.getString("statusBarIconBrightness")) : 0, !jSONObject.isNull("systemStatusBarContrastEnforced") ? Boolean.valueOf(jSONObject.getBoolean("systemStatusBarContrastEnforced")) : null, !jSONObject.isNull("systemNavigationBarColor") ? Integer.valueOf(jSONObject.getInt("systemNavigationBarColor")) : null, jSONObject.isNull("systemNavigationBarIconBrightness") ? 0 : z.a(jSONObject.getString("systemNavigationBarIconBrightness")), !jSONObject.isNull("systemNavigationBarDividerColor") ? Integer.valueOf(jSONObject.getInt("systemNavigationBarDividerColor")) : null, jSONObject.isNull("systemNavigationBarContrastEnforced") ? null : Boolean.valueOf(jSONObject.getBoolean("systemNavigationBarContrastEnforced")));
    }

    public static HashMap y(String str, int i2, int i3, int i4, int i5) {
        HashMap map = new HashMap();
        map.put("text", str);
        map.put("selectionBase", Integer.valueOf(i2));
        map.put("selectionExtent", Integer.valueOf(i3));
        map.put("composingBase", Integer.valueOf(i4));
        map.put("composingExtent", Integer.valueOf(i5));
        return map;
    }

    @Override // io.flutter.plugin.platform.h
    public void a() {
        ((io.flutter.plugin.platform.j) this.f170e).a();
        ((i) this.f171f).a();
    }

    @Override // Y.j
    public void b(int i2) {
        ((i) this.f171f).r(i2);
        ((io.flutter.plugin.platform.j) this.f170e).f911x.b(i2);
    }

    @Override // Y.j
    public void c(boolean z2) {
        ((io.flutter.plugin.platform.j) ((io.flutter.plugin.platform.j) this.f170e).f911x.f661e).f908t = z2;
    }

    @Override // io.flutter.plugin.platform.h
    public void d(n nVar) {
        ((io.flutter.plugin.platform.j) this.f170e).f900k.f868a = nVar;
        ((i) this.f171f).f885i.f868a = nVar;
    }

    @Override // Y.j
    public void e(Y.i iVar, C.a aVar) {
        ((i) this.f171f).r(iVar.f549a);
        ((io.flutter.plugin.platform.j) this.f170e).f911x.e(iVar, aVar);
    }

    @Override // Y.j
    public void f(int i2, double d2, double d3) {
        ((i) this.f171f).r(i2);
        ((io.flutter.plugin.platform.j) this.f170e).f911x.f(i2, d2, d3);
    }

    @Override // Y.j
    public void g(int i2, int i3) {
        ((i) this.f171f).r(i2);
        ((io.flutter.plugin.platform.j) this.f170e).f911x.g(i2, i3);
    }

    @Override // io.flutter.plugin.platform.h
    public boolean h(int i2) {
        ((i) this.f171f).r(i2);
        return ((io.flutter.plugin.platform.j) this.f170e).h(i2);
    }

    @Override // Y.j
    public void i(g gVar) {
        k kVar = ((i) ((i) this.f171f).f892q.f661e).f880d;
        String str = gVar.f532b;
        if (kVar.f455a.get(str) != null) {
            throw new ClassCastException();
        }
        throw new IllegalStateException("Trying to create a platform view of unregistered type: " + str);
    }

    @Override // Z.d
    public void j(ByteBuffer byteBuffer, Q.g gVar) {
        switch (this.f169d) {
            case 11:
                G.f fVar = (G.f) this.f171f;
                try {
                    ((Z.b) this.f170e).g(((Z.l) fVar.f166f).a(byteBuffer), new a(10, this, gVar));
                } catch (RuntimeException e2) {
                    Log.e("BasicMessageChannel#" + ((String) fVar.f165e), "Failed to handle message", e2);
                    gVar.a(null);
                    return;
                }
                break;
            default:
                e eVar = (e) this.f171f;
                Z.n nVar = (Z.n) eVar.f176f;
                try {
                    ((m) this.f170e).m(nVar.b(byteBuffer), new Y.k(1, this, gVar));
                } catch (RuntimeException e3) {
                    Log.e("MethodChannel#" + ((String) eVar.f175e), "Failed to handle method call", e3);
                    gVar.a(nVar.e(e3.getMessage(), Log.getStackTraceString(e3)));
                }
                break;
        }
    }

    @Override // Y.j
    public void k(Y.h hVar) {
        ((i) this.f171f).r(hVar.f534a);
        ((io.flutter.plugin.platform.j) this.f170e).f911x.k(hVar);
    }

    @Override // Y.j
    public void l(int i2) {
        ((i) this.f171f).r(i2);
        ((io.flutter.plugin.platform.j) this.f170e).f911x.l(i2);
    }

    @Override // Z.m
    public void m(a aVar, Y.k kVar) {
        x xVar = (x) this.f171f;
        if (((G.f) xVar.f90e) == null) {
            kVar.d((Map) this.f170e);
            return;
        }
        String str = (String) aVar.f170e;
        str.getClass();
        if (!str.equals("getKeyboardState")) {
            kVar.b();
            return;
        }
        try {
            this.f170e = Collections.unmodifiableMap(((A) ((C[]) ((G.f) xVar.f90e).f164d)[0]).f272e);
        } catch (IllegalStateException e2) {
            kVar.a("error", e2.getMessage(), null);
        }
        kVar.d((Map) this.f170e);
    }

    @Override // Y.j
    public long n(g gVar) {
        ((io.flutter.plugin.platform.j) this.f170e).f911x.n(gVar);
        throw null;
    }

    @Override // Z.c
    public void o(Object obj) {
        switch (this.f169d) {
            case 8:
                G.f fVar = (G.f) this.f171f;
                ConcurrentLinkedQueue concurrentLinkedQueue = (ConcurrentLinkedQueue) fVar.f164d;
                Y.m mVar = (Y.m) this.f170e;
                concurrentLinkedQueue.remove(mVar);
                if (!((ConcurrentLinkedQueue) fVar.f164d).isEmpty()) {
                    Log.e("SettingsChannel", "The queue becomes empty after removing config generation " + mVar.f562a);
                }
                break;
            default:
                ((Q.g) this.f170e).a(((Z.l) ((G.f) ((a) this.f171f).f171f).f166f).b(obj));
                break;
        }
    }

    @Override // Y.j
    public void p(g gVar) {
        ((io.flutter.plugin.platform.j) this.f170e).f911x.p(gVar);
        throw null;
    }

    @Override // O.C
    public void q(KeyEvent keyEvent, final M.a aVar) {
        int action = keyEvent.getAction();
        if (action != 0 && action != 1) {
            aVar.a(false);
            return;
        }
        Character chA = ((D0.i) this.f171f).a(keyEvent.getUnicodeChar());
        boolean z2 = action != 0;
        Y.c cVar = (Y.c) this.f170e;
        final Z.c cVar2 = new Z.c() { // from class: O.w
            @Override // Z.c
            public void o(Object obj) {
                w wVar = (w) aVar;
                boolean z3 = false;
                if (obj != null) {
                    try {
                        z3 = ((JSONObject) obj).getBoolean("handled");
                    } catch (JSONException e2) {
                        Log.e("KeyEventChannel", "Unable to unpack JSON message: " + e2);
                    }
                }
                ((M.a) aVar).a(z3);
            }
        };
        G.f fVar = cVar.f520a;
        HashMap map = new HashMap();
        map.put("type", z2 ? "keyup" : "keydown");
        map.put("keymap", "android");
        map.put("flags", Integer.valueOf(keyEvent.getFlags()));
        map.put("plainCodePoint", Integer.valueOf(keyEvent.getUnicodeChar(0)));
        map.put("codePoint", Integer.valueOf(keyEvent.getUnicodeChar()));
        map.put("keyCode", Integer.valueOf(keyEvent.getKeyCode()));
        map.put("scanCode", Integer.valueOf(keyEvent.getScanCode()));
        map.put("metaState", Integer.valueOf(keyEvent.getMetaState()));
        map.put("character", chA.toString());
        map.put("source", Integer.valueOf(keyEvent.getSource()));
        map.put("deviceId", Integer.valueOf(keyEvent.getDeviceId()));
        map.put("repeatCount", Integer.valueOf(keyEvent.getRepeatCount()));
        fVar.g(map, new Z.c() { // from class: O.w
            @Override // Z.c
            public void o(Object obj) {
                w wVar = (w) cVar2;
                boolean z3 = false;
                if (obj != null) {
                    try {
                        z3 = ((JSONObject) obj).getBoolean("handled");
                    } catch (JSONException e2) {
                        Log.e("KeyEventChannel", "Unable to unpack JSON message: " + e2);
                    }
                }
                ((M.a) cVar2).a(z3);
            }
        });
    }

    @Override // io.flutter.plugin.platform.h
    public void r(int i2) {
        ((i) this.f171f).r(i2);
        ((io.flutter.plugin.platform.j) this.f170e).r(i2);
    }

    @Override // Y.j
    public boolean s() {
        FlutterJNI flutterJNI = ((i) this.f171f).f883g;
        if (flutterJNI == null) {
            return false;
        }
        return flutterJNI.IsSurfaceControlEnabled();
    }

    public Object x(String str) {
        Object obj = this.f171f;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            return ((Map) obj).get(str);
        }
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).opt(str);
        }
        throw new ClassCastException();
    }

    public void z(double d2, boolean z2) {
        AudioManager audioManager = (AudioManager) this.f170e;
        if (d2 < 0.0d) {
            d2 = 0.0d;
        } else if (d2 > 1.0d) {
            d2 = 1.0d;
        }
        int iRint = (int) Math.rint(((double) audioManager.getStreamMaxVolume(3)) * d2);
        if (d2 != 0.0d) {
            this.f171f = null;
        }
        audioManager.setStreamVolume(3, iRint, z2 ? 1 : 0);
    }

    public /* synthetic */ a(int i2, boolean z2) {
        this.f169d = i2;
    }

    public a(AudioManager audioManager) {
        this.f169d = 1;
        this.f170e = audioManager;
        this.f171f = null;
    }

    public a(p002b0.b bVar, x xVar) {
        this.f169d = 14;
        this.f170e = bVar;
        this.f171f = xVar;
        xVar.f90e = new p002b0.a(0, this);
    }

    public a(Y.c cVar) {
        this.f169d = 2;
        this.f171f = new D0.i();
        this.f170e = cVar;
    }

    public a(x xVar) {
        this.f169d = 4;
        this.f171f = xVar;
        this.f170e = new HashMap();
    }

    public a(View view, InputMethodManager inputMethodManager, x xVar) {
        this.f169d = 15;
        if (Build.VERSION.SDK_INT >= 33) {
            view.setAutoHandwritingEnabled(false);
        }
        this.f171f = view;
        this.f170e = inputMethodManager;
        xVar.f90e = this;
    }

    public a(Object obj, String str) {
        this.f169d = 12;
        this.f170e = str;
        this.f171f = obj;
    }

    public a(p020w.b bVar) {
        this.f169d = 18;
        a aVar = new a(17);
        this.f170e = bVar;
        this.f171f = aVar;
    }

    public a(int i2) {
        this.f169d = i2;
        switch (i2) {
            case 17:
                this.f170e = new ReentrantLock();
                this.f171f = new LinkedHashMap();
                break;
            default:
                this.f170e = new LongSparseArray();
                this.f171f = new PriorityQueue();
                break;
        }
    }

    public a(Q.b bVar, int i2) {
        this.f169d = i2;
        switch (i2) {
            case 6:
                x xVar = new x(14, this);
                e eVar = new e(bVar, "flutter/platform", Z.k.f594a, null);
                this.f170e = eVar;
                eVar.j(xVar);
                break;
            case 9:
                x xVar2 = new x(27, this);
                e eVar2 = new e(bVar, "flutter/textinput", Z.k.f594a, null);
                this.f170e = eVar2;
                eVar2.j(xVar2);
                break;
            default:
                x xVar3 = new x(11, this);
                e eVar3 = new e(bVar, "flutter/localization", Z.k.f594a, null);
                this.f170e = eVar3;
                eVar3.j(xVar3);
                break;
        }
    }

    public a(Q.b bVar, PackageManager packageManager) {
        this.f169d = 7;
        x xVar = new x(19, this);
        this.f170e = packageManager;
        new e(bVar, "flutter/processtext", q.f598a, null).j(xVar);
    }
}
