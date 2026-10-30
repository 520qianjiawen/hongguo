package p002b0;

import A0.d;
import J.l;
import O.t;
import P.k;
import Y.f;
import Y.h;
import Y.i;
import Y.j;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import androidx.profileinstaller.ProfileInstallReceiver;
import f0.g;
import io.flutter.plugin.editing.m;
import io.flutter.plugin.platform.p;
import j0.b;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import p014o.e;
import u0.c;

/* JADX INFO: loaded from: classes.dex */
public final class a implements j, e, d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f660d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f661e;

    public /* synthetic */ a() {
        this.f660d = 6;
    }

    public static boolean q(int i2) {
        return (48 <= i2 && i2 <= 57) || i2 == 35 || i2 == 42;
    }

    @Override // A0.d
    public Object a(Object obj, b bVar) {
        ((p011k.a) this.f661e).accept(obj);
        return g.f725a;
    }

    @Override // Y.j
    public void b(int i2) {
        io.flutter.plugin.platform.j jVar = (io.flutter.plugin.platform.j) this.f661e;
        if (jVar.h(i2)) {
            ((p) jVar.f901l.get(Integer.valueOf(i2))).getClass();
            Log.e("PlatformViewsController", "Clearing focus on a null view with id: " + i2);
            return;
        }
        if (jVar.f902n.get(i2) != null) {
            throw new ClassCastException();
        }
        Log.e("PlatformViewsController", "Clearing focus on an unknown view with id: " + i2);
    }

    @Override // Y.j
    public void c(boolean z2) {
        ((io.flutter.plugin.platform.j) this.f661e).f908t = z2;
    }

    @Override // p014o.e
    public void d() {
        Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
    }

    @Override // Y.j
    public void e(i iVar, C.a aVar) {
        io.flutter.plugin.platform.j jVar = (io.flutter.plugin.platform.j) this.f661e;
        int iG = jVar.g(iVar.f550b);
        int iG2 = jVar.g(iVar.f551c);
        int i2 = iVar.f549a;
        if (!jVar.h(i2)) {
            if (jVar.f902n.get(i2) != null) {
                throw new ClassCastException();
            }
            Log.e("PlatformViewsController", "Resizing unknown platform view with id: " + i2);
            return;
        }
        float f2 = jVar.f894e.getResources().getDisplayMetrics().density;
        p pVar = (p) jVar.f901l.get(Integer.valueOf(i2));
        m mVar = jVar.f898i;
        if (mVar != null) {
            if (mVar.f857e.f851a == 3) {
                mVar.f867p = true;
            }
            pVar.getClass();
        }
        pVar.getClass();
        if ((iG == 0 && iG2 == 0) || Build.VERSION.SDK_INT < 31) {
            throw null;
        }
        throw null;
    }

    @Override // Y.j
    public void f(int i2, double d2, double d3) {
        io.flutter.plugin.platform.j jVar = (io.flutter.plugin.platform.j) this.f661e;
        if (jVar.h(i2)) {
            return;
        }
        Log.e("PlatformViewsController", "Setting offset for unknown platform view with id: " + i2);
    }

    @Override // Y.j
    public void g(int i2, int i3) {
        io.flutter.plugin.platform.j jVar = (io.flutter.plugin.platform.j) this.f661e;
        if (i3 != 0 && i3 != 1) {
            throw new IllegalStateException("Trying to set unknown direction value: " + i3 + "(view id: " + i2 + ")");
        }
        if (jVar.h(i2)) {
            ((p) jVar.f901l.get(Integer.valueOf(i2))).getClass();
            Log.e("PlatformViewsController", "Setting direction to a null view with id: " + i2);
            return;
        }
        if (jVar.f902n.get(i2) != null) {
            throw new ClassCastException();
        }
        Log.e("PlatformViewsController", "Setting direction to an unknown view with id: " + i2);
    }

    @Override // p014o.e
    public void h(int i2, Object obj) {
        String str;
        switch (i2) {
            case 1:
                str = "RESULT_INSTALL_SUCCESS";
                break;
            case 2:
                str = "RESULT_ALREADY_INSTALLED";
                break;
            case 3:
                str = "RESULT_UNSUPPORTED_ART_VERSION";
                break;
            case 4:
                str = "RESULT_NOT_WRITABLE";
                break;
            case 5:
                str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                break;
            case 6:
                str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                break;
            case 7:
                str = "RESULT_IO_EXCEPTION";
                break;
            case 8:
                str = "RESULT_PARSE_EXCEPTION";
                break;
            case 9:
            default:
                str = "";
                break;
            case 10:
                str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                break;
            case 11:
                str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                break;
        }
        if (i2 == 6 || i2 == 7 || i2 == 8) {
            Log.e("ProfileInstaller", str, (Throwable) obj);
        } else {
            Log.d("ProfileInstaller", str);
        }
        ((ProfileInstallReceiver) this.f661e).setResultCode(i2);
    }

    @Override // Y.j
    public void i(Y.g gVar) {
        throw new IllegalStateException("Trying to create an HC++ platform view from within PlatformViewsController1. Request: " + gVar);
    }

    public void j(String str) {
        b bVar = (b) ((H.a) this.f661e).f170e;
        if (H.a.f168h == null) {
            G.b bVar2 = new G.b();
            bVar2.put("alias", 1010);
            bVar2.put("allScroll", 1013);
            bVar2.put("basic", 1000);
            bVar2.put("cell", 1006);
            bVar2.put("click", 1002);
            bVar2.put("contextMenu", 1001);
            bVar2.put("copy", 1011);
            bVar2.put("forbidden", 1012);
            bVar2.put("grab", 1020);
            bVar2.put("grabbing", 1021);
            bVar2.put("help", 1003);
            bVar2.put("move", 1013);
            bVar2.put("none", 0);
            bVar2.put("noDrop", 1012);
            bVar2.put("precise", 1007);
            bVar2.put("text", 1008);
            bVar2.put("resizeColumn", 1014);
            bVar2.put("resizeDown", 1015);
            bVar2.put("resizeUpLeft", 1016);
            bVar2.put("resizeDownRight", 1017);
            bVar2.put("resizeLeft", 1014);
            bVar2.put("resizeLeftRight", 1014);
            bVar2.put("resizeRight", 1014);
            bVar2.put("resizeRow", 1015);
            bVar2.put("resizeUp", 1015);
            bVar2.put("resizeUpDown", 1015);
            bVar2.put("resizeUpLeft", 1017);
            bVar2.put("resizeUpRight", 1016);
            bVar2.put("resizeUpLeftDownRight", 1017);
            bVar2.put("resizeUpRightDownLeft", 1016);
            bVar2.put("verticalText", 1009);
            bVar2.put("wait", 1004);
            bVar2.put("zoomIn", 1018);
            bVar2.put("zoomOut", 1019);
            H.a.f168h = bVar2;
        }
        bVar.setPointerIcon(PointerIcon.getSystemIcon(((t) bVar).getContext(), ((Integer) H.a.f168h.getOrDefault(str, 1000)).intValue()));
    }

    @Override // Y.j
    public void k(h hVar) {
        int i2 = hVar.f534a;
        io.flutter.plugin.platform.j jVar = (io.flutter.plugin.platform.j) this.f661e;
        float f2 = jVar.f894e.getResources().getDisplayMetrics().density;
        if (!jVar.h(i2)) {
            if (jVar.f902n.get(i2) != null) {
                throw new ClassCastException();
            }
            Log.e("PlatformViewsController", "Sending touch to an unknown view with id: " + i2);
            return;
        }
        p pVar = (p) jVar.f901l.get(Integer.valueOf(i2));
        long j2 = hVar.f548p;
        int i3 = hVar.f538e;
        H.a aVar = jVar.f910w;
        aVar.getClass();
        LongSparseArray longSparseArray = (LongSparseArray) aVar.f170e;
        PriorityQueue priorityQueue = (PriorityQueue) aVar.f171f;
        while (!priorityQueue.isEmpty() && ((Long) priorityQueue.peek()).longValue() < j2) {
            longSparseArray.remove(((Long) priorityQueue.poll()).longValue());
        }
        if (!priorityQueue.isEmpty() && ((Long) priorityQueue.peek()).longValue() == j2) {
            priorityQueue.poll();
        }
        longSparseArray.remove(j2);
        List<List> list = (List) hVar.f540g;
        ArrayList arrayList = new ArrayList();
        for (List list2 : list) {
            MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
            pointerCoords.orientation = (float) ((Double) list2.get(0)).doubleValue();
            pointerCoords.pressure = (float) ((Double) list2.get(1)).doubleValue();
            pointerCoords.size = (float) ((Double) list2.get(2)).doubleValue();
            double d2 = f2;
            pointerCoords.toolMajor = (float) (((Double) list2.get(3)).doubleValue() * d2);
            pointerCoords.toolMinor = (float) (((Double) list2.get(4)).doubleValue() * d2);
            pointerCoords.touchMajor = (float) (((Double) list2.get(5)).doubleValue() * d2);
            pointerCoords.touchMinor = (float) (((Double) list2.get(6)).doubleValue() * d2);
            pointerCoords.x = (float) (((Double) list2.get(7)).doubleValue() * d2);
            pointerCoords.y = (float) (((Double) list2.get(8)).doubleValue() * d2);
            arrayList.add(pointerCoords);
        }
        MotionEvent.PointerCoords[] pointerCoordsArr = (MotionEvent.PointerCoords[]) arrayList.toArray(new MotionEvent.PointerCoords[i3]);
        List<List> list3 = (List) hVar.f539f;
        ArrayList arrayList2 = new ArrayList();
        for (List list4 : list3) {
            MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
            pointerProperties.id = ((Integer) list4.get(0)).intValue();
            pointerProperties.toolType = ((Integer) list4.get(1)).intValue();
            arrayList2.add(pointerProperties);
        }
        MotionEvent.obtain(hVar.f535b.longValue(), hVar.f536c.longValue(), hVar.f537d, hVar.f538e, (MotionEvent.PointerProperties[]) arrayList2.toArray(new MotionEvent.PointerProperties[i3]), pointerCoordsArr, hVar.f541h, hVar.f542i, hVar.f543j, hVar.f544k, hVar.f545l, hVar.m, hVar.f546n, hVar.f547o);
        pVar.getClass();
    }

    @Override // Y.j
    public void l(int i2) {
        switch (this.f660d) {
            case 3:
                if (((io.flutter.plugin.platform.j) this.f661e).f902n.get(i2) != null) {
                    throw new ClassCastException();
                }
                Log.e("PlatformViewsController", "Disposing unknown platform view with id: " + i2);
                return;
            default:
                io.flutter.plugin.platform.i iVar = (io.flutter.plugin.platform.i) this.f661e;
                iVar.f891p.remove(Integer.valueOf(i2));
                if (iVar.f886j.get(i2) != null) {
                    throw new ClassCastException();
                }
                Log.e("PlatformViewsController2", "Disposing unknown platform view with id: " + i2);
                return;
        }
    }

    public CharSequence m(Y.e eVar) {
        Activity activity = ((io.flutter.plugin.platform.e) this.f661e).f874a;
        ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService("clipboard");
        CharSequence charSequence = null;
        if (clipboardManager.hasPrimaryClip()) {
            try {
                try {
                    ClipData primaryClip = clipboardManager.getPrimaryClip();
                    if (primaryClip != null) {
                        if (eVar != null) {
                            if (eVar == Y.e.f525d) {
                            }
                        }
                        ClipData.Item itemAt = primaryClip.getItemAt(0);
                        CharSequence text = itemAt.getText();
                        if (text != null) {
                            return text;
                        }
                        try {
                            Uri uri = itemAt.getUri();
                            if (uri == null) {
                                Log.w("PlatformPlugin", "Clipboard item contained no textual content nor a URI to retrieve it from.");
                                return null;
                            }
                            String scheme = uri.getScheme();
                            if (!scheme.equals("content")) {
                                Log.w("PlatformPlugin", "Clipboard item contains a Uri with scheme '" + scheme + "'that is unhandled.");
                                return null;
                            }
                            AssetFileDescriptor assetFileDescriptorOpenTypedAssetFileDescriptor = activity.getContentResolver().openTypedAssetFileDescriptor(uri, "text/*", null);
                            CharSequence charSequenceCoerceToText = itemAt.coerceToText(activity);
                            if (assetFileDescriptorOpenTypedAssetFileDescriptor == null) {
                                return charSequenceCoerceToText;
                            }
                            try {
                                assetFileDescriptorOpenTypedAssetFileDescriptor.close();
                                return charSequenceCoerceToText;
                            } catch (IOException e2) {
                                charSequence = charSequenceCoerceToText;
                                e = e2;
                                Log.w("PlatformPlugin", "Failed to close AssetFileDescriptor while trying to read text from URI.", e);
                                return charSequence;
                            }
                        } catch (IOException e3) {
                            e = e3;
                            charSequence = text;
                        }
                    }
                } catch (IOException e4) {
                    e = e4;
                }
            } catch (FileNotFoundException unused) {
                Log.w("PlatformPlugin", "Clipboard text was unable to be received from content URI.");
                return charSequence;
            } catch (SecurityException e5) {
                Log.w("PlatformPlugin", "Attempted to get clipboard data that requires additional permission(s).\nSee the exception details for which permission(s) are required, and consider adding them to your Android Manifest as described in:\nhttps://developer.android.com/guide/topics/permissions/overview", e5);
                return charSequence;
            }
        }
        return null;
    }

    @Override // Y.j
    public long n(Y.g gVar) {
        io.flutter.plugin.platform.j jVar = (io.flutter.plugin.platform.j) this.f661e;
        io.flutter.plugin.platform.j.b(jVar, gVar);
        int i2 = gVar.f531a;
        if (jVar.f905q.get(i2) != null) {
            throw new IllegalStateException("Trying to create an already created platform view, view id: " + i2);
        }
        if (jVar.f897h == null) {
            throw new IllegalStateException("Texture registry is null. This means that platform views controller was detached, view id: " + i2);
        }
        if (jVar.f895f == null) {
            throw new IllegalStateException("Flutter view is null. This means the platform views controller doesn't have an attached view, view id: " + i2);
        }
        k kVar = jVar.f893d;
        String str = gVar.f532b;
        if (kVar.f455a.get(str) != null) {
            throw new ClassCastException();
        }
        throw new IllegalStateException("Trying to create a platform view of unregistered type: " + str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object o(c cVar) {
        p0.h.e(cVar, "property");
        Float f2 = (Float) this.f661e;
        if (f2 != null) {
            return f2;
        }
        throw new IllegalStateException("Property " + ((p0.c) cVar).f1208g + " should be initialized before get.");
    }

    @Override // Y.j
    public void p(Y.g gVar) {
        io.flutter.plugin.platform.j jVar = (io.flutter.plugin.platform.j) this.f661e;
        io.flutter.plugin.platform.j.b(jVar, gVar);
        if (jVar.f896g.IsSurfaceControlEnabled()) {
            throw new IllegalStateException("Trying to create a Hybrid Composition view with HC++ enabled.");
        }
        k kVar = jVar.f893d;
        String str = gVar.f532b;
        if (kVar.f455a.get(str) != null) {
            throw new ClassCastException();
        }
        throw new IllegalStateException("Trying to create a platform view of unregistered type: " + str);
    }

    public void r(ArrayList arrayList) {
        io.flutter.plugin.platform.e eVar = (io.flutter.plugin.platform.e) this.f661e;
        if (eVar.f879f) {
            eVar.f879f = false;
            p000a.a.N(eVar.f874a.getWindow(), true);
        }
        int i2 = arrayList.isEmpty() ? 5894 : 1798;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            int iOrdinal = ((f) arrayList.get(i3)).ordinal();
            if (iOrdinal == 0) {
                i2 &= -5;
            } else if (iOrdinal == 1) {
                i2 &= -515;
            }
        }
        eVar.f878e = i2;
        eVar.b();
    }

    @Override // Y.j
    public boolean s() {
        return false;
    }

    public void t(int i2) {
        int i3;
        io.flutter.plugin.platform.e eVar = (io.flutter.plugin.platform.e) this.f661e;
        Activity activity = eVar.f874a;
        if (i2 != 4 && eVar.f879f) {
            eVar.f879f = false;
            p000a.a.N(activity.getWindow(), true);
        }
        if (i2 == 1) {
            i3 = 1798;
        } else if (i2 == 2) {
            i3 = 3846;
        } else {
            if (i2 != 3) {
                if (i2 != 4 || Build.VERSION.SDK_INT < 29) {
                    return;
                }
                eVar.f879f = true;
                activity.getWindow().getDecorView().setSystemUiVisibility(0);
                p000a.a.N(activity.getWindow(), false);
                l lVar = eVar.f877d;
                if (lVar != null) {
                    eVar.a(lVar);
                    return;
                }
                return;
            }
            i3 = 5894;
        }
        eVar.f878e = i3;
        eVar.b();
    }

    public String toString() {
        String str;
        switch (this.f660d) {
            case 6:
                StringBuilder sb = new StringBuilder("NotNullProperty(");
                if (((Float) this.f661e) != null) {
                    str = "value=" + ((Float) this.f661e);
                } else {
                    str = "value not initialized yet";
                }
                sb.append(str);
                sb.append(')');
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public void u(int i2) {
        View decorView = ((io.flutter.plugin.platform.e) this.f661e).f874a.getWindow().getDecorView();
        switch (p014o.c.a(i2)) {
            case 0:
                decorView.performHapticFeedback(0);
                break;
            case 1:
                decorView.performHapticFeedback(1);
                break;
            case 2:
                decorView.performHapticFeedback(3);
                break;
            case 3:
                decorView.performHapticFeedback(6);
                break;
            case 4:
                decorView.performHapticFeedback(4);
                break;
            case 5:
                if (Build.VERSION.SDK_INT >= 30) {
                    decorView.performHapticFeedback(16);
                }
                break;
            case 6:
                if (Build.VERSION.SDK_INT >= 30) {
                    decorView.performHapticFeedback(3);
                }
                break;
            case 7:
                if (Build.VERSION.SDK_INT >= 30) {
                    decorView.performHapticFeedback(17);
                }
                break;
        }
    }

    public /* synthetic */ a(int i2, Object obj) {
        this.f660d = i2;
        this.f661e = obj;
    }
}
