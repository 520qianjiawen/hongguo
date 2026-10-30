package O;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Insets;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.LocaleList;
import android.os.Looper;
import android.provider.Settings;
import android.text.Selection;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.DisplayCutout;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.RoundedCorner;
import android.view.Surface;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewStructure;
import android.view.Window;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textservice.SpellCheckerInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.TextServicesManager;
import android.widget.FrameLayout;
import io.flutter.embedding.engine.FlutterJNI;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import x0.AbstractC0017a;
import x0.AbstractC0034s;
import x0.AbstractC0039x;
import x0.C0033q;
import x0.M;
import x0.X;

/* JADX INFO: loaded from: classes.dex */
public final class t extends FrameLayout implements p002b0.b, D {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f351A;
    public v B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f352a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m f353b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o f354c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public C0013k f355d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f356e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f357f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HashSet f358g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f359h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public P.c f360i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final HashSet f361j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public H.a f362k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public io.flutter.plugin.editing.m f363l;
    public io.flutter.plugin.editing.h m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public p001a0.a f364n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public G.f f365o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C0004b f366p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public io.flutter.view.n f367q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public TextServicesManager f368r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public C0.x f369s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final io.flutter.embedding.engine.renderer.i f370t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C0.x f371u;
    public final D.a v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final r f372w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final C0008f f373x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public q f374y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f375z;

    public t(AbstractActivityC0007e abstractActivityC0007e, m mVar) {
        super(abstractActivityC0007e, null);
        this.f352a = false;
        this.f358g = new HashSet();
        this.f361j = new HashSet();
        this.f370t = new io.flutter.embedding.engine.renderer.i();
        this.f371u = new C0.x(4, this);
        this.v = new D.a(this, new Handler(Looper.getMainLooper()), 1);
        this.f372w = new r(this);
        this.f373x = new C0008f(1, this);
        this.B = new v();
        this.f353b = mVar;
        this.f356e = mVar;
        b();
    }

    /* JADX WARN: Type inference failed for: r0v38, types: [android.view.View, io.flutter.embedding.engine.renderer.m] */
    public final void a() {
        UiModeManager uiModeManager;
        Objects.toString(this.f360i);
        if (c()) {
            Iterator it = this.f361j.iterator();
            if (it.hasNext()) {
                it.next().getClass();
                throw new ClassCastException();
            }
            getContext().getContentResolver().unregisterContentObserver(this.v);
            io.flutter.plugin.platform.j jVar = this.f360i.f409r;
            SparseArray sparseArray = jVar.f902n;
            SparseArray sparseArray2 = jVar.f903o;
            SparseArray sparseArray3 = jVar.f905q;
            for (int i2 = 0; i2 < sparseArray3.size(); i2++) {
                jVar.f895f.removeView((io.flutter.plugin.platform.g) sparseArray3.valueAt(i2));
            }
            for (int i3 = 0; i3 < sparseArray2.size(); i3++) {
                if (sparseArray2.valueAt(i3) != null) {
                    throw new ClassCastException();
                }
                jVar.f895f.removeView(null);
            }
            jVar.c();
            SparseArray sparseArray4 = jVar.f904p;
            if (jVar.f895f == null) {
                Log.e("PlatformViewsController", "removeOverlaySurfaces called while flutter view is null");
            } else {
                for (int i4 = 0; i4 < sparseArray4.size(); i4++) {
                    jVar.f895f.removeView((View) sparseArray4.valueAt(i4));
                }
                sparseArray4.clear();
            }
            jVar.f895f = null;
            jVar.f907s = false;
            if (sparseArray.size() > 0) {
                sparseArray.valueAt(0).getClass();
                throw new ClassCastException();
            }
            io.flutter.plugin.platform.i iVar = this.f360i.f410s;
            SparseArray sparseArray5 = iVar.f886j;
            SparseArray sparseArray6 = iVar.f887k;
            for (int i5 = 0; i5 < sparseArray6.size(); i5++) {
                if (sparseArray6.valueAt(i5) != null) {
                    throw new ClassCastException();
                }
                iVar.f882f.removeView(null);
            }
            Surface surface = iVar.f889n;
            if (surface != null) {
                surface.release();
                iVar.f889n = null;
                iVar.f890o = null;
            }
            iVar.f882f = null;
            if (sparseArray5.size() > 0) {
                sparseArray5.valueAt(0).getClass();
                throw new ClassCastException();
            }
            this.f360i.f409r.a();
            this.f360i.f410s.a();
            io.flutter.view.n nVar = this.f367q;
            nVar.f1041t = true;
            nVar.f1027e.a();
            nVar.f1039r = null;
            AccessibilityManager accessibilityManager = nVar.f1025c;
            accessibilityManager.removeAccessibilityStateChangeListener(nVar.f1042u);
            accessibilityManager.removeTouchExplorationStateChangeListener(nVar.v);
            ContentResolver contentResolver = nVar.f1028f;
            contentResolver.unregisterContentObserver(nVar.f1045y);
            contentResolver.unregisterContentObserver(nVar.f1044x);
            if (Build.VERSION.SDK_INT >= 34 && (uiModeManager = (UiModeManager) nVar.f1023a.getContext().getSystemService("uimode")) != null) {
                uiModeManager.removeContrastChangeListener(Y.a.e(nVar.f1043w));
            }
            G.f fVar = nVar.f1024b;
            fVar.f166f = null;
            ((FlutterJNI) fVar.f165e).setAccessibilityDelegate(null);
            this.f367q = null;
            this.f363l.f854b.restartInput(this);
            this.f363l.b();
            int size = ((HashSet) this.f365o.f165e).size();
            if (size > 0) {
                Log.w("KeyboardManager", "A KeyboardManager was destroyed with " + size + " unhandled redispatch event(s).");
            }
            io.flutter.plugin.editing.h hVar = this.m;
            if (hVar != null) {
                hVar.f835a.f90e = null;
                SpellCheckerSession spellCheckerSession = hVar.f837c;
                if (spellCheckerSession != null) {
                    spellCheckerSession.close();
                }
            }
            H.a aVar = this.f362k;
            if (aVar != null) {
                ((C0.x) aVar.f171f).f90e = null;
            }
            io.flutter.embedding.engine.renderer.j jVar2 = this.f360i.f393a;
            this.f359h = false;
            jVar2.e(this.f373x);
            FlutterJNI flutterJNI = jVar2.f793a;
            if (this.f352a) {
                flutterJNI.removeResizingFlutterUiListener(this.f372w);
            }
            jVar2.h();
            flutterJNI.setSemanticsEnabled(false);
            View view = this.f357f;
            if (view != null && this.f356e == this.f355d) {
                this.f356e = view;
            }
            this.f356e.c();
            C0013k c0013k = this.f355d;
            if (c0013k != null) {
                c0013k.f326a.close();
                removeView(this.f355d);
                this.f355d = null;
            }
            this.f357f = null;
            this.f360i = null;
        }
    }

    @Override // android.view.View
    public final void autofill(SparseArray sparseArray) {
        Y.o oVar;
        H.e eVar;
        H.e eVar2;
        io.flutter.plugin.editing.m mVar = this.f363l;
        if (mVar == null || Build.VERSION.SDK_INT < 26 || (oVar = mVar.f858f) == null || mVar.f859g == null || (eVar = oVar.f575j) == null) {
            return;
        }
        HashMap map = new HashMap();
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            Y.o oVar2 = (Y.o) mVar.f859g.get(sparseArray.keyAt(i2));
            if (oVar2 != null && (eVar2 = oVar2.f575j) != null) {
                String str = (String) eVar2.f174d;
                String string = AbstractC0003a.f(sparseArray.valueAt(i2)).getTextValue().toString();
                Y.q qVar = new Y.q(string, string.length(), string.length(), -1, -1);
                if (str.equals((String) eVar.f174d)) {
                    mVar.f860h.f(qVar);
                } else {
                    map.put(str, qVar);
                }
            }
        }
        H.a aVar = mVar.f856d;
        int i3 = mVar.f857e.f852b;
        aVar.getClass();
        map.size();
        HashMap map2 = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            Y.q qVar2 = (Y.q) entry.getValue();
            map2.put((String) entry.getKey(), H.a.y(qVar2.f581a, qVar2.f582b, qVar2.f583c, -1, -1));
        }
        ((H.e) aVar.f170e).h("TextInputClient.updateEditingStateWithTag", Arrays.asList(Integer.valueOf(i3), map2), null);
    }

    public final void b() {
        m mVar = this.f353b;
        if (mVar != null) {
            addView(mVar);
        } else {
            o oVar = this.f354c;
            if (oVar != null) {
                addView(oVar);
            } else {
                addView(this.f355d);
            }
        }
        this.f352a = p000a.a.z(getContext());
        setFocusable(true);
        setFocusableInTouchMode(true);
        if (Build.VERSION.SDK_INT >= 26) {
            setImportantForAutofill(1);
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [android.view.View, io.flutter.embedding.engine.renderer.m] */
    public final boolean c() {
        P.c cVar = this.f360i;
        return cVar != null && cVar.f393a == this.f356e.getAttachedRenderer();
    }

    @Override // android.view.View
    public final boolean checkInputConnectionProxy(View view) {
        P.c cVar = this.f360i;
        if (cVar == null) {
            return super.checkInputConnectionProxy(view);
        }
        HashMap map = cVar.f409r.m;
        if (view == null || !map.containsKey(view.getContext())) {
            return false;
        }
        View view2 = (View) map.get(view.getContext());
        if (view2 == view) {
            return true;
        }
        return view2.checkInputConnectionProxy(view);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x004e  */
    public final void d() {
        boolean z2;
        String str;
        boolean z3;
        char c2 = (getResources().getConfiguration().uiMode & 48) == 32 ? (char) 2 : (char) 1;
        TextServicesManager textServicesManager = this.f368r;
        if (textServicesManager == null) {
            z2 = false;
        } else {
            if (Build.VERSION.SDK_INT >= 31) {
                Iterator it = textServicesManager.getEnabledSpellCheckerInfos().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z3 = false;
                        break;
                    } else if (((SpellCheckerInfo) it.next()).getPackageName().equals("com.google.android.inputmethod.latin")) {
                        z3 = true;
                        break;
                    }
                }
                if (!this.f368r.isSpellCheckerEnabled() || !z3) {
                    z2 = false;
                }
            }
            z2 = true;
        }
        Y.n nVar = this.f360i.f405n;
        nVar.getClass();
        G.f fVar = nVar.f565b;
        HashMap map = new HashMap();
        map.put("textScaleFactor", Float.valueOf(getResources().getConfiguration().fontScale));
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        map.put("nativeSpellCheckServiceDefined", Boolean.valueOf(z2));
        map.put("brieflyShowPassword", Boolean.valueOf(Settings.System.getInt(getContext().getContentResolver(), "show_password", 1) == 1));
        map.put("alwaysUse24HourFormat", Boolean.valueOf(DateFormat.is24HourFormat(getContext())));
        if (c2 == 1) {
            str = "light";
        } else {
            if (c2 != 2) {
                throw null;
            }
            str = "dark";
        }
        map.put("platformBrightness", str);
        Objects.toString(map.get("textScaleFactor"));
        Objects.toString(map.get("alwaysUse24HourFormat"));
        Objects.toString(map.get("platformBrightness"));
        if (Build.VERSION.SDK_INT < 34 || displayMetrics == null) {
            fVar.g(map, null);
            return;
        }
        Y.m mVar = new Y.m(displayMetrics);
        G.f fVar2 = nVar.f564a;
        ((ConcurrentLinkedQueue) fVar2.f164d).add(mVar);
        Y.m mVar2 = (Y.m) fVar2.f166f;
        fVar2.f166f = mVar;
        H.a aVar = mVar2 != null ? new H.a(8, fVar2, mVar2) : null;
        map.put("configurationId", Integer.valueOf(mVar.f562a));
        fVar.g(map, aVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
            getKeyDispatcherState().startTracking(keyEvent, this);
        } else if (keyEvent.getAction() == 1) {
            getKeyDispatcherState().handleUpEvent(keyEvent);
        }
        return (c() && this.f365o.c(keyEvent)) || super.dispatchKeyEvent(keyEvent);
    }

    public final void e() {
        if (!c()) {
            Log.w("FlutterView", "Tried to send viewport metrics from Android to Flutter but this FlutterView was not attached to a FlutterEngine.");
            return;
        }
        float f2 = getResources().getDisplayMetrics().density;
        io.flutter.embedding.engine.renderer.i iVar = this.f370t;
        iVar.f769a = f2;
        iVar.f787t = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        io.flutter.embedding.engine.renderer.j jVar = this.f360i.f393a;
        jVar.getClass();
        int i2 = iVar.f770b;
        ArrayList arrayList = iVar.f792z;
        ArrayList arrayList2 = iVar.f791y;
        if (i2 == 0) {
            int i3 = iVar.f772d;
            int i4 = iVar.f773e;
            if (i3 <= 0 && i4 <= 0) {
                return;
            }
        } else {
            int i5 = iVar.f771c;
            if (i5 == 0) {
                int i6 = iVar.f774f;
                int i7 = iVar.f775g;
                if (i6 <= 0 && i7 <= 0) {
                    return;
                }
            } else if (i2 <= 0 || i5 <= 0 || iVar.f769a <= 0.0f) {
                return;
            }
        }
        arrayList2.size();
        arrayList.size();
        int size = arrayList.size() + arrayList2.size();
        int[] iArr = new int[size * 4];
        int[] iArr2 = new int[size];
        int[] iArr3 = new int[size];
        for (int i8 = 0; i8 < arrayList2.size(); i8++) {
            io.flutter.embedding.engine.renderer.a aVar = (io.flutter.embedding.engine.renderer.a) arrayList2.get(i8);
            int i9 = i8 * 4;
            Rect rect = aVar.f749a;
            iArr[i9] = rect.left;
            iArr[i9 + 1] = rect.top;
            iArr[i9 + 2] = rect.right;
            iArr[i9 + 3] = rect.bottom;
            iArr2[i8] = p014o.c.a(aVar.f750b);
            iArr3[i8] = p014o.c.a(aVar.f751c);
        }
        int size2 = arrayList2.size() * 4;
        for (int i10 = 0; i10 < arrayList.size(); i10++) {
            io.flutter.embedding.engine.renderer.a aVar2 = (io.flutter.embedding.engine.renderer.a) arrayList.get(i10);
            int i11 = (i10 * 4) + size2;
            Rect rect2 = aVar2.f749a;
            iArr[i11] = rect2.left;
            iArr[i11 + 1] = rect2.top;
            iArr[i11 + 2] = rect2.right;
            iArr[i11 + 3] = rect2.bottom;
            iArr2[arrayList2.size() + i10] = p014o.c.a(aVar2.f750b);
            iArr3[arrayList2.size() + i10] = p014o.c.a(aVar2.f751c);
        }
        jVar.f793a.setViewportMetrics(iVar.f769a, iVar.f770b, iVar.f771c, iVar.f776h, iVar.f777i, iVar.f778j, iVar.f779k, iVar.f780l, iVar.m, iVar.f781n, iVar.f782o, iVar.f783p, iVar.f784q, iVar.f785r, iVar.f786s, iVar.f787t, iArr, iArr2, iArr3, iVar.f772d, iVar.f773e, iVar.f774f, iVar.f775g, iVar.f788u, iVar.v, iVar.f789w, iVar.f790x);
    }

    @Override // android.view.View
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        io.flutter.view.n nVar = this.f367q;
        if (nVar == null || !nVar.f1025c.isEnabled()) {
            return null;
        }
        return this.f367q;
    }

    public P.c getAttachedFlutterEngine() {
        return this.f360i;
    }

    public Z.f getBinaryMessenger() {
        return this.f360i.f394b;
    }

    public C0013k getCurrentImageSurface() {
        return this.f355d;
    }

    public io.flutter.embedding.engine.renderer.i getViewportMetrics() {
        return this.f370t;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0136  */
    /* JADX WARN: Code duplicated, block: B:56:0x0187  */
    /* JADX WARN: Code duplicated, block: B:59:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:60:0x01a4  */
    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        char c2;
        double d2;
        int systemWindowInsetLeft;
        int systemWindowInsetBottom;
        int i2;
        Window window;
        DisplayCutout displayCutout;
        WindowInsets windowInsetsOnApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
        int i3 = Build.VERSION.SDK_INT;
        io.flutter.embedding.engine.renderer.i iVar = this.f370t;
        if (i3 == 29) {
            Insets systemGestureInsets = windowInsets.getSystemGestureInsets();
            iVar.f783p = systemGestureInsets.top;
            iVar.f784q = systemGestureInsets.right;
            iVar.f785r = systemGestureInsets.bottom;
            iVar.f786s = systemGestureInsets.left;
        }
        boolean z2 = (getWindowSystemUiVisibility() & 4) == 0;
        boolean z3 = (getWindowSystemUiVisibility() & 2) == 0;
        if (i3 >= 30) {
            Insets insets = windowInsets.getInsets(WindowInsets.Type.systemBars());
            iVar.f776h = insets.top;
            iVar.f777i = insets.right;
            iVar.f778j = insets.bottom;
            iVar.f779k = insets.left;
            Insets insets2 = windowInsets.getInsets(WindowInsets.Type.ime());
            iVar.f780l = insets2.top;
            iVar.m = insets2.right;
            iVar.f781n = insets2.bottom;
            iVar.f782o = insets2.left;
            Insets insets3 = windowInsets.getInsets(WindowInsets.Type.systemGestures());
            iVar.f783p = insets3.top;
            iVar.f784q = insets3.right;
            iVar.f785r = insets3.bottom;
            iVar.f786s = insets3.left;
            DisplayCutout displayCutout2 = windowInsets.getDisplayCutout();
            if (displayCutout2 != null) {
                Insets waterfallInsets = displayCutout2.getWaterfallInsets();
                iVar.f776h = Math.max(Math.max(iVar.f776h, waterfallInsets.top), displayCutout2.getSafeInsetTop());
                iVar.f777i = Math.max(Math.max(iVar.f777i, waterfallInsets.right), displayCutout2.getSafeInsetRight());
                iVar.f778j = Math.max(Math.max(iVar.f778j, waterfallInsets.bottom), displayCutout2.getSafeInsetBottom());
                iVar.f779k = Math.max(Math.max(iVar.f779k, waterfallInsets.left), displayCutout2.getSafeInsetLeft());
            }
            i2 = 0;
        } else {
            if (z3) {
                c2 = 1;
            } else {
                Context context = getContext();
                if (context.getResources().getConfiguration().orientation != 2) {
                    c2 = 1;
                } else {
                    int rotation = ((DisplayManager) context.getSystemService("display")).getDisplay(0).getRotation();
                    if (rotation == 1) {
                        c2 = 3;
                    } else if (rotation == 3) {
                        c2 = 2;
                    } else if (rotation == 0 || rotation == 2) {
                        c2 = 4;
                    } else {
                        c2 = 1;
                    }
                }
            }
            iVar.f776h = z2 ? windowInsets.getSystemWindowInsetTop() : 0;
            iVar.f777i = (c2 == 3 || c2 == 4) ? 0 : windowInsets.getSystemWindowInsetRight();
            if (z3) {
                d2 = 0.18d;
                int systemWindowInsetBottom2 = ((((double) windowInsets.getSystemWindowInsetBottom()) > (((double) getRootView().getHeight()) * 0.18d) ? 1 : (((double) windowInsets.getSystemWindowInsetBottom()) == (((double) getRootView().getHeight()) * 0.18d) ? 0 : -1)) < 0 ? 0 : windowInsets.getSystemWindowInsetBottom()) == 0 ? windowInsets.getSystemWindowInsetBottom() : 0;
                iVar.f778j = systemWindowInsetBottom2;
                if (c2 != 2 || c2 == 4) {
                    systemWindowInsetLeft = 0;
                } else {
                    systemWindowInsetLeft = windowInsets.getSystemWindowInsetLeft();
                }
                iVar.f779k = systemWindowInsetLeft;
                iVar.f780l = 0;
                iVar.m = 0;
                if (windowInsets.getSystemWindowInsetBottom() < ((double) getRootView().getHeight()) * d2) {
                    systemWindowInsetBottom = 0;
                } else {
                    systemWindowInsetBottom = windowInsets.getSystemWindowInsetBottom();
                }
                iVar.f781n = systemWindowInsetBottom;
                i2 = 0;
                iVar.f782o = 0;
            } else {
                d2 = 0.18d;
            }
            iVar.f778j = systemWindowInsetBottom2;
            if (c2 != 2) {
                systemWindowInsetLeft = 0;
            } else {
                systemWindowInsetLeft = 0;
            }
            iVar.f779k = systemWindowInsetLeft;
            iVar.f780l = 0;
            iVar.m = 0;
            if (windowInsets.getSystemWindowInsetBottom() < ((double) getRootView().getHeight()) * d2) {
                systemWindowInsetBottom = 0;
            } else {
                systemWindowInsetBottom = windowInsets.getSystemWindowInsetBottom();
            }
            iVar.f781n = systemWindowInsetBottom;
            i2 = 0;
            iVar.f782o = 0;
        }
        ArrayList arrayList = new ArrayList();
        if (i3 >= 28 && (displayCutout = windowInsets.getDisplayCutout()) != null) {
            for (Rect rect : displayCutout.getBoundingRects()) {
                rect.toString();
                arrayList.add(new io.flutter.embedding.engine.renderer.a(rect, 4, 1));
            }
        }
        ArrayList arrayList2 = iVar.f792z;
        arrayList2.clear();
        arrayList2.addAll(arrayList);
        if (Build.VERSION.SDK_INT >= 35) {
            v vVar = this.B;
            Context context2 = getContext();
            vVar.getClass();
            Activity activityO = p000a.a.o(context2);
            WindowInsets rootWindowInsets = null;
            if (activityO != null && (window = activityO.getWindow()) != null) {
                rootWindowInsets = window.getDecorView().getRootWindowInsets();
            }
            List boundingRects = rootWindowInsets == null ? Collections.EMPTY_LIST : rootWindowInsets.getBoundingRects(WindowInsets.Type.captionBar());
            int iMax = iVar.f776h;
            Iterator it = boundingRects.iterator();
            while (it.hasNext()) {
                iMax = Math.max(iMax, ((Rect) it.next()).bottom);
            }
            iVar.f776h = iMax;
        }
        if (Build.VERSION.SDK_INT >= 31) {
            RoundedCorner roundedCorner = windowInsets.getRoundedCorner(0);
            RoundedCorner roundedCorner2 = windowInsets.getRoundedCorner(1);
            RoundedCorner roundedCorner3 = windowInsets.getRoundedCorner(2);
            RoundedCorner roundedCorner4 = windowInsets.getRoundedCorner(3);
            iVar.f788u = roundedCorner != null ? roundedCorner.getRadius() : i2;
            iVar.v = roundedCorner2 != null ? roundedCorner2.getRadius() : i2;
            iVar.f789w = roundedCorner3 != null ? roundedCorner3.getRadius() : i2;
            iVar.f790x = roundedCorner4 != null ? roundedCorner4.getRadius() : i2;
        }
        e();
        return windowInsetsOnApplyWindowInsets;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        C0.x xVar;
        super.onAttachedToWindow();
        try {
            p020w.g gVar = p020w.h.f1294b;
            Context context = getContext();
            gVar.getClass();
            xVar = new C0.x(5, new H.a(p020w.g.a(context)));
        } catch (NoClassDefFoundError unused) {
            xVar = null;
        }
        this.f369s = xVar;
        Activity activityO = p000a.a.o(getContext());
        C0.x xVar2 = this.f369s;
        if (xVar2 == null || activityO == null) {
            return;
        }
        this.f374y = new q(0, this);
        Context context2 = getContext();
        Executor executorA = Build.VERSION.SDK_INT >= 28 ? p007g.a.a(context2) : new p010j.a(new Handler(context2.getMainLooper()));
        q qVar = this.f374y;
        H.a aVar = (H.a) xVar2.f90e;
        p0.h.e(executorA, "executor");
        p0.h.e(qVar, "consumer");
        H.a aVar2 = (H.a) aVar.f171f;
        p020w.b bVar = (p020w.b) aVar.f170e;
        bVar.getClass();
        p020w.j jVar = new p020w.j(bVar, activityO, null);
        h0.j jVar2 = h0.j.f746d;
        A0.b bVar2 = new A0.b(jVar, jVar2, -2, 1);
        D0.d dVar = AbstractC0039x.f1376a;
        y0.c cVar = C0.p.f83a;
        if (cVar.i(C0033q.f1363e) != null) {
            throw new IllegalArgumentException(("Flow context cannot contain job in it. Had " + cVar).toString());
        }
        if (!cVar.equals(jVar2)) {
            h0.i iVarH = cVar.h(jVar2);
            if (!p0.h.a(iVarH, jVar2)) {
                bVar2 = new A0.b(bVar2.f9h, iVarH, -2, 1);
            }
        }
        LinkedHashMap linkedHashMap = (LinkedHashMap) aVar2.f171f;
        ReentrantLock reentrantLock = (ReentrantLock) aVar2.f170e;
        reentrantLock.lock();
        try {
            if (linkedHashMap.get(qVar) == null) {
                x0.E e2 = new x0.E(executorA);
                C0.e eVar = new C0.e(e2.i(C0033q.f1363e) != null ? e2 : p000a.a.E(e2, new M()));
                u.a aVar3 = new u.a(bVar2, qVar, null);
                AbstractC0017a x2 = new X(AbstractC0034s.e(eVar, jVar2), true);
                x2.K(1, x2, aVar3);
                linkedHashMap.put(qVar, x2);
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) throws Exception {
        super.onConfigurationChanged(configuration);
        if (this.f360i != null) {
            this.f364n.b(configuration);
            d();
            p000a.a.c(getContext(), this.f360i);
        }
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0059 A[PHI: r8
      0x0059: PHI (r8v11 int) = (r8v6 int), (r8v14 int) binds: [B:73:0x00b4, B:31:0x0057] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:71:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:72:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:75:0x00b7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:76:0x00b9  */
    @Override // android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        int i2;
        int i3;
        if (!c()) {
            return super.onCreateInputConnection(editorInfo);
        }
        io.flutter.plugin.editing.m mVar = this.f363l;
        G.f fVar = this.f365o;
        io.flutter.plugin.editing.l lVar = mVar.f857e;
        int i4 = lVar.f851a;
        if (i4 == 1) {
            mVar.f862j = null;
            return null;
        }
        int i5 = 4;
        if (i4 == 4) {
            return null;
        }
        if (i4 == 3) {
            if (mVar.f867p) {
                return mVar.f862j;
            }
            mVar.f863k.r(lVar.f852b);
            throw null;
        }
        Y.o oVar = mVar.f858f;
        Y.p pVar = oVar.f572g;
        boolean z2 = oVar.f566a;
        boolean z3 = oVar.f567b;
        boolean z4 = oVar.f568c;
        boolean z5 = oVar.f569d;
        int i6 = oVar.f571f;
        int i7 = pVar.f578a;
        if (i7 != 2) {
            if (i7 == 5) {
                i5 = pVar.f579b ? 4098 : 2;
                if (pVar.f580c) {
                    i5 |= 8192;
                }
            } else if (i7 == 6) {
                i5 = 3;
            } else if (i7 == 11) {
                i5 = 0;
            } else {
                if (i7 == 7) {
                    i2 = 131073;
                } else if (i7 == 8 || i7 == 13) {
                    i2 = 33;
                } else if (i7 == 9 || i7 == 12) {
                    i2 = 17;
                } else if (i7 == 10) {
                    i2 = 145;
                } else if (i7 == 3) {
                    i2 = 97;
                } else {
                    i2 = i7 == 4 ? 113 : 1;
                }
                if (z2) {
                    i3 = 524416;
                } else {
                    if (z3) {
                        i2 |= 32768;
                    }
                    if (z4) {
                        i5 = i2;
                        if (i6 == 1) {
                            i5 |= 4096;
                        } else if (i6 == 2) {
                            i5 |= 8192;
                        } else if (i6 == 3) {
                            i5 |= 16384;
                        }
                    } else {
                        i3 = 524432;
                    }
                }
                i2 |= i3;
                i5 = i2;
                if (i6 == 1) {
                    i5 |= 4096;
                } else if (i6 == 2) {
                    i5 |= 8192;
                } else if (i6 == 3) {
                    i5 |= 16384;
                }
            }
        }
        editorInfo.inputType = i5;
        editorInfo.imeOptions = 33554432;
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 26 && !z5) {
            editorInfo.imeOptions = 50331648;
        }
        int iIntValue = oVar.f573h.intValue();
        Y.o oVar2 = mVar.f858f;
        String str = oVar2.f574i;
        if (str != null) {
            editorInfo.actionLabel = str;
            editorInfo.actionId = iIntValue;
        }
        editorInfo.imeOptions = iIntValue | editorInfo.imeOptions;
        if (oVar2.m != null) {
            editorInfo.hintLocales = new LocaleList(mVar.f858f.m);
        }
        String[] strArr = mVar.f858f.f576k;
        if (strArr != null) {
            if (i8 >= 25) {
                editorInfo.contentMimeTypes = strArr;
            } else {
                if (editorInfo.extras == null) {
                    editorInfo.extras = new Bundle();
                }
                editorInfo.extras.putStringArray("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES", strArr);
                editorInfo.extras.putStringArray("android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES", strArr);
            }
        }
        if (i8 >= 34) {
            if (editorInfo.extras == null) {
                editorInfo.extras = new Bundle();
            }
            editorInfo.extras.putBoolean("androidx.core.view.inputmethod.EditorInfoCompat.STYLUS_HANDWRITING_ENABLED", true);
        }
        io.flutter.plugin.editing.d dVar = new io.flutter.plugin.editing.d(this, mVar.f857e.f852b, mVar.f856d, fVar, mVar.f860h, editorInfo);
        io.flutter.plugin.editing.g gVar = mVar.f860h;
        gVar.getClass();
        editorInfo.initialSelStart = Selection.getSelectionStart(gVar);
        io.flutter.plugin.editing.g gVar2 = mVar.f860h;
        gVar2.getClass();
        editorInfo.initialSelEnd = Selection.getSelectionEnd(gVar2);
        mVar.f862j = dVar;
        return dVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        q qVar;
        C0.x xVar = this.f369s;
        if (xVar != null && (qVar = this.f374y) != null) {
            H.a aVar = (H.a) ((H.a) xVar.f90e).f171f;
            LinkedHashMap linkedHashMap = (LinkedHashMap) aVar.f171f;
            ReentrantLock reentrantLock = (ReentrantLock) aVar.f170e;
            reentrantLock.lock();
            try {
                x0.J j2 = (x0.J) linkedHashMap.get(qVar);
                if (j2 != null) {
                    j2.a(null);
                }
                reentrantLock.unlock();
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
        this.f374y = null;
        this.f369s = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (c()) {
            C0004b c0004b = this.f366p;
            Context context = getContext();
            c0004b.getClass();
            boolean zIsFromSource = motionEvent.isFromSource(2);
            boolean z2 = motionEvent.getActionMasked() == 7 || motionEvent.getActionMasked() == 8;
            if (zIsFromSource && z2) {
                int iB = C0004b.b(motionEvent.getActionMasked());
                ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(motionEvent.getPointerCount() * 288);
                byteBufferAllocateDirect.order(ByteOrder.LITTLE_ENDIAN);
                c0004b.a(motionEvent, motionEvent.getActionIndex(), iB, 0, C0004b.f297f, byteBufferAllocateDirect, context);
                if (byteBufferAllocateDirect.position() % 288 != 0) {
                    throw new AssertionError("Packet position is not on field boundary.");
                }
                c0004b.f298a.f793a.dispatchPointerDataPacket(byteBufferAllocateDirect, byteBufferAllocateDirect.position());
                return true;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        return !c() ? super.onHoverEvent(motionEvent) : this.f367q.e(motionEvent, false);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i2, int i3) {
        this.f375z = View.MeasureSpec.getMode(i2);
        this.f351A = View.MeasureSpec.getMode(i3);
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    public final void onProvideAutofillVirtualStructure(ViewStructure viewStructure, int i2) {
        Rect rect;
        super.onProvideAutofillVirtualStructure(viewStructure, i2);
        io.flutter.plugin.editing.m mVar = this.f363l;
        if (mVar == null || Build.VERSION.SDK_INT < 26 || mVar.f859g == null) {
            return;
        }
        String str = (String) mVar.f858f.f575j.f174d;
        AutofillId autofillId = viewStructure.getAutofillId();
        for (int i3 = 0; i3 < mVar.f859g.size(); i3++) {
            int iKeyAt = mVar.f859g.keyAt(i3);
            H.e eVar = ((Y.o) mVar.f859g.valueAt(i3)).f575j;
            if (eVar != null) {
                viewStructure.addChildCount(1);
                ViewStructure viewStructureNewChild = viewStructure.newChild(i3);
                viewStructureNewChild.setAutofillId(autofillId, iKeyAt);
                String[] strArr = (String[]) eVar.f175e;
                if (strArr.length > 0) {
                    viewStructureNewChild.setAutofillHints(strArr);
                }
                viewStructureNewChild.setAutofillType(1);
                viewStructureNewChild.setVisibility(0);
                String str2 = (String) eVar.f177g;
                if (str2 != null) {
                    viewStructureNewChild.setHint(str2);
                }
                if (str.hashCode() != iKeyAt || (rect = mVar.m) == null) {
                    viewStructureNewChild.setDimens(0, 0, 0, 0, 1, 1);
                    viewStructureNewChild.setAutofillValue(AutofillValue.forText(((Y.q) eVar.f176f).f581a));
                } else {
                    viewStructureNewChild.setDimens(rect.left, rect.top, 0, 0, rect.width(), mVar.m.height());
                    viewStructureNewChild.setAutofillValue(AutofillValue.forText(mVar.f860h));
                }
            }
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        io.flutter.embedding.engine.renderer.i iVar = this.f370t;
        iVar.f770b = i2;
        iVar.f771c = i3;
        boolean z2 = this.f352a;
        if (z2 && this.f351A == 0) {
            iVar.f774f = 0;
            iVar.f775g = 8192;
        } else {
            iVar.f774f = i3;
            iVar.f775g = i3;
        }
        if (z2 && this.f375z == 0) {
            iVar.f772d = 0;
            iVar.f773e = 8192;
        } else {
            iVar.f772d = i2;
            iVar.f773e = i2;
        }
        e();
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        ByteBuffer byteBuffer;
        if (!c()) {
            return super.onTouchEvent(motionEvent);
        }
        requestUnbufferedDispatch(motionEvent);
        C0004b c0004b = this.f366p;
        Matrix matrix = C0004b.f297f;
        c0004b.getClass();
        int actionMasked = motionEvent.getActionMasked();
        int iB = C0004b.b(motionEvent.getActionMasked());
        char c2 = 5;
        boolean z2 = actionMasked == 0 || actionMasked == 5;
        boolean z3 = !z2 && (actionMasked == 1 || actionMasked == 6);
        int toolType = motionEvent.getToolType(motionEvent.getActionIndex());
        if (toolType == 1) {
            c2 = 0;
        } else if (toolType == 2) {
            c2 = 2;
        } else if (toolType == 3) {
            c2 = 1;
        } else if (toolType == 4) {
            c2 = 3;
        }
        int i2 = (z3 && c2 == 0) ? 1 : 0;
        int pointerCount = motionEvent.getPointerCount();
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect((pointerCount + i2) * 288);
        byteBufferAllocateDirect.order(ByteOrder.LITTLE_ENDIAN);
        if (z2) {
            byteBuffer = byteBufferAllocateDirect;
            c0004b.a(motionEvent, motionEvent.getActionIndex(), iB, 0, matrix, byteBuffer, null);
        } else {
            byteBuffer = byteBufferAllocateDirect;
            if (z3) {
                for (int i3 = 0; i3 < pointerCount; i3++) {
                    if (i3 != motionEvent.getActionIndex() && motionEvent.getToolType(i3) == 1) {
                        c0004b.a(motionEvent, i3, 5, 1, matrix, byteBuffer, null);
                    }
                }
                c0004b.a(motionEvent, motionEvent.getActionIndex(), iB, 0, matrix, byteBuffer, null);
                if (i2 != 0) {
                    c0004b.a(motionEvent, motionEvent.getActionIndex(), 2, 0, matrix, byteBuffer, null);
                }
            } else {
                for (int i4 = 0; i4 < pointerCount; i4++) {
                    c0004b.a(motionEvent, i4, iB, (pointerCount << 8) | 2, matrix, byteBuffer, null);
                }
            }
        }
        if (byteBuffer.position() % 288 != 0) {
            throw new AssertionError("Packet position is not on field boundary");
        }
        c0004b.f298a.f793a.dispatchPointerDataPacket(byteBuffer, byteBuffer.position());
        return true;
    }

    public void setDelegate(v vVar) {
        this.B = vVar;
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        View view = this.f356e;
        if (view instanceof m) {
            ((m) view).setVisibility(i2);
        }
    }

    /* JADX WARN: Type inference failed for: r9v1, types: [java.lang.Object, java.util.List] */
    public void setWindowInfoListenerDisplayFeatures(p020w.k kVar) {
        p020w.b bVar = p020w.b.f1275g;
        ?? r9 = kVar.f1301a;
        ArrayList arrayList = new ArrayList();
        for (p020w.c cVar : r9) {
            cVar.f1282a.a().toString();
            p019t.b bVar2 = cVar.f1282a;
            int i2 = 2;
            int i3 = ((bVar2.f1240c - bVar2.f1238a == 0 || bVar2.f1241d - bVar2.f1239b == 0) ? p020w.b.f1274f : bVar) == bVar ? 3 : 2;
            p020w.b bVar3 = cVar.f1284c;
            if (bVar3 != p020w.b.f1276h) {
                i2 = bVar3 == p020w.b.f1277i ? 3 : 1;
            }
            arrayList.add(new io.flutter.embedding.engine.renderer.a(bVar2.a(), i3, i2));
        }
        ArrayList arrayList2 = this.f370t.f791y;
        arrayList2.clear();
        arrayList2.addAll(arrayList);
        e();
    }

    public t(AbstractActivityC0007e abstractActivityC0007e, o oVar) {
        super(abstractActivityC0007e, null);
        this.f352a = false;
        this.f358g = new HashSet();
        this.f361j = new HashSet();
        this.f370t = new io.flutter.embedding.engine.renderer.i();
        this.f371u = new C0.x(4, this);
        this.v = new D.a(this, new Handler(Looper.getMainLooper()), 1);
        this.f372w = new r(this);
        this.f373x = new C0008f(1, this);
        this.B = new v();
        this.f354c = oVar;
        this.f356e = oVar;
        b();
    }
}
