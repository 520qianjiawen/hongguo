package O;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Trace;
import android.provider.Settings;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.view.accessibility.AccessibilityManager;
import android.view.textservice.TextServicesManager;
import android.window.OnBackInvokedCallback;
import app.minireel.minireel.MainActivity;
import io.flutter.embedding.engine.FlutterJNI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: renamed from: O.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractActivityC0007e extends Activity implements InterfaceC0010h, androidx.lifecycle.i {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f305e = View.generateViewId();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f306a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0011i f307b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final androidx.lifecycle.j f308c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final OnBackInvokedCallback f309d;

    public AbstractActivityC0007e() {
        int i2 = Build.VERSION.SDK_INT;
        this.f309d = i2 < 33 ? null : i2 >= 34 ? new C0006d(this) : new OnBackInvokedCallback() { // from class: O.c
            public final void onBackInvoked() {
                this.f303a.onBackPressed();
            }
        };
        this.f308c = new androidx.lifecycle.j(this);
    }

    @Override // androidx.lifecycle.i
    public final androidx.lifecycle.j a() {
        return this.f308c;
    }

    public final String b() {
        String dataString;
        if ((getApplicationInfo().flags & 2) == 0 || !"android.intent.action.RUN".equals(getIntent().getAction()) || (dataString = getIntent().getDataString()) == null) {
            return null;
        }
        return dataString;
    }

    public final int c() {
        if (!getIntent().hasExtra("background_mode")) {
            return 1;
        }
        String stringExtra = getIntent().getStringExtra("background_mode");
        if (stringExtra == null) {
            throw new NullPointerException("Name is null");
        }
        if (stringExtra.equals("opaque")) {
            return 1;
        }
        if (stringExtra.equals("transparent")) {
            return 2;
        }
        throw new IllegalArgumentException("No enum constant io.flutter.embedding.android.FlutterActivityLaunchConfigs.BackgroundMode.".concat(stringExtra));
    }

    public final String d() {
        return getIntent().getStringExtra("cached_engine_id");
    }

    public final String e() {
        if (getIntent().hasExtra("dart_entrypoint")) {
            return getIntent().getStringExtra("dart_entrypoint");
        }
        try {
            Bundle bundleG = g();
            String string = bundleG != null ? bundleG.getString("io.flutter.Entrypoint") : null;
            return string != null ? string : "main";
        } catch (PackageManager.NameNotFoundException unused) {
            return "main";
        }
    }

    public final String f() {
        if (getIntent().hasExtra("route")) {
            return getIntent().getStringExtra("route");
        }
        try {
            Bundle bundleG = g();
            if (bundleG != null) {
                return bundleG.getString("io.flutter.InitialRoute");
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public final Bundle g() {
        return getPackageManager().getActivityInfo(getComponentName(), 128).metaData;
    }

    public final void h(boolean z2) {
        if (z2 && !this.f306a) {
            if (Build.VERSION.SDK_INT >= 33) {
                getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, this.f309d);
                this.f306a = true;
                return;
            }
            return;
        }
        if (z2 || !this.f306a || Build.VERSION.SDK_INT < 33) {
            return;
        }
        getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(this.f309d);
        this.f306a = false;
    }

    public final boolean i() {
        boolean booleanExtra = getIntent().getBooleanExtra("destroy_engine_with_activity", false);
        return (d() != null || this.f307b.f320g) ? booleanExtra : getIntent().getBooleanExtra("destroy_engine_with_activity", true);
    }

    public final boolean j() {
        if (getIntent().hasExtra("enable_state_restoration")) {
            return getIntent().getBooleanExtra("enable_state_restoration", false);
        }
        return d() == null;
    }

    public final boolean k(String str) {
        C0011i c0011i = this.f307b;
        if (c0011i == null) {
            Log.w("FlutterActivity", "FlutterActivity " + hashCode() + " " + str + " called after release.");
            return false;
        }
        if (c0011i.f323j) {
            return true;
        }
        Log.w("FlutterActivity", "FlutterActivity " + hashCode() + " " + str + " called after detach.");
        return false;
    }

    @Override // android.app.Activity
    public final void onActivityResult(int i2, int i3, Intent intent) {
        if (k("onActivityResult")) {
            C0011i c0011i = this.f307b;
            c0011i.c();
            if (c0011i.f315b == null) {
                Log.w("FlutterActivityAndFragmentDelegate", "onActivityResult() invoked before FlutterFragment was attached to an Activity.");
                return;
            }
            Objects.toString(intent);
            P.e eVar = c0011i.f315b.f395c;
            if (!eVar.f()) {
                Log.e("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onActivityResult, but no Activity was attached.");
                return;
            }
            e0.a.b("FlutterEngineConnectionRegistry#onActivityResult");
            try {
                ((P.d) eVar.f427h).a(i2, i3, intent);
                Trace.endSection();
            } catch (Throwable th) {
                try {
                    Trace.endSection();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
        if (k("onBackPressed")) {
            C0011i c0011i = this.f307b;
            c0011i.c();
            P.c cVar = c0011i.f315b;
            if (cVar != null) {
                cVar.f400h.f519a.h("popRoute", null, null);
            } else {
                Log.w("FlutterActivityAndFragmentDelegate", "Invoked onBackPressed() before FlutterFragment was attached to an Activity.");
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:213:0x04e1  */
    /* JADX WARN: Code duplicated, block: B:220:0x05c2  */
    /* JADX WARN: Code duplicated, block: B:223:0x05c8  */
    /* JADX WARN: Code duplicated, block: B:225:0x05cd  */
    /* JADX WARN: Code duplicated, block: B:229:0x0623 A[LOOP:1: B:227:0x061d->B:229:0x0623, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:233:0x0638  */
    /* JADX WARN: Code duplicated, block: B:235:0x063e A[LOOP:2: B:231:0x0632->B:235:0x063e, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:240:0x0653  */
    /* JADX WARN: Code duplicated, block: B:243:0x0662  */
    /* JADX WARN: Code duplicated, block: B:245:0x0668 A[LOOP:3: B:241:0x065c->B:245:0x0668, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:250:0x067d  */
    /* JADX WARN: Code duplicated, block: B:252:0x0689  */
    /* JADX WARN: Code duplicated, block: B:254:0x068d  */
    /* JADX WARN: Code duplicated, block: B:270:0x06ea  */
    /* JADX WARN: Code duplicated, block: B:272:0x06f7  */
    /* JADX WARN: Code duplicated, block: B:274:0x0704  */
    /* JADX WARN: Code duplicated, block: B:301:0x0647 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:303:0x0671 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r11v1, types: [android.view.View, io.flutter.embedding.engine.renderer.m] */
    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        byte[] byteArray;
        io.flutter.embedding.engine.renderer.j jVar;
        boolean zIsEnabled;
        io.flutter.plugin.platform.j jVar2;
        SparseArray sparseArray;
        SparseArray sparseArray2;
        SparseArray sparseArray3;
        int i2;
        int i3;
        io.flutter.plugin.platform.i iVar;
        SparseArray sparseArray4;
        SparseArray sparseArray5;
        int i4;
        Iterator it;
        boolean z2;
        int i5;
        try {
            Bundle bundleG = g();
            if (bundleG != null && (i5 = bundleG.getInt("io.flutter.embedding.android.NormalTheme", -1)) != -1) {
                setTheme(i5);
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("FlutterActivity", "Could not read meta-data for FlutterActivity. Using the launch theme as normal theme.");
        }
        super.onCreate(bundle);
        if (bundle != null) {
            h(bundle.getBoolean("enableOnBackInvokedCallbackState"));
        }
        C0011i c0011i = new C0011i(this);
        this.f307b = c0011i;
        c0011i.c();
        if (c0011i.f315b == null) {
            String strD = c0011i.f314a.d();
            if (strD != null) {
                if (P.k.f454c == null) {
                    P.k.f454c = new P.k(1);
                }
                P.c cVar = (P.c) P.k.f454c.f455a.get(strD);
                c0011i.f315b = cVar;
                c0011i.f320g = true;
                if (cVar == null) {
                    throw new IllegalStateException("The requested cached FlutterEngine did not exist in the FlutterEngineCache: '" + strD + "'");
                }
            } else {
                c0011i.f314a.getClass();
                c0011i.f315b = null;
                String stringExtra = c0011i.f314a.getIntent().getStringExtra("cached_engine_group_id");
                if (stringExtra != null) {
                    if (P.k.f453b == null) {
                        synchronized (P.k.class) {
                            try {
                                if (P.k.f453b == null) {
                                    P.k.f453b = new P.k(0);
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                    P.j jVar3 = (P.j) P.k.f453b.f455a.get(stringExtra);
                    if (jVar3 == null) {
                        throw new IllegalStateException("The requested cached FlutterEngineGroup did not exist in the FlutterEngineGroupCache: '" + stringExtra + "'");
                    }
                    AbstractActivityC0007e abstractActivityC0007e = c0011i.f314a;
                    abstractActivityC0007e.getClass();
                    P.i iVar2 = new P.i(abstractActivityC0007e);
                    c0011i.a(iVar2);
                    c0011i.f315b = jVar3.a(iVar2);
                    c0011i.f320g = false;
                } else {
                    AbstractActivityC0007e abstractActivityC0007e2 = c0011i.f314a;
                    abstractActivityC0007e2.getClass();
                    Intent intent = abstractActivityC0007e2.getIntent();
                    if (intent.getExtras() != null) {
                        for (String str : intent.getExtras().keySet()) {
                            P.f fVarA = P.g.a(str);
                            if (fVarA == null) {
                                fVarA = P.g.a("--".concat(str));
                            }
                            if (fVarA == null) {
                                fVarA = P.g.a("--" + str + "=");
                            }
                            if (fVarA != null) {
                                break;
                            }
                        }
                    }
                    AbstractActivityC0007e abstractActivityC0007e3 = c0011i.f314a;
                    abstractActivityC0007e3.getClass();
                    Intent intent2 = c0011i.f314a.getIntent();
                    ArrayList arrayList = new ArrayList();
                    if (intent2.getBooleanExtra("trace-startup", false)) {
                        arrayList.add("--trace-startup");
                    }
                    if (intent2.getBooleanExtra("start-paused", false)) {
                        arrayList.add("--start-paused");
                    }
                    int intExtra = intent2.getIntExtra("vm-service-port", 0);
                    if (intExtra > 0) {
                        arrayList.add("--vm-service-port=" + intExtra);
                    }
                    if (intent2.getBooleanExtra("disable-service-auth-codes", false)) {
                        arrayList.add("--disable-service-auth-codes");
                    }
                    if (intent2.getBooleanExtra("endless-trace-buffer", false)) {
                        arrayList.add("--endless-trace-buffer");
                    }
                    if (intent2.getBooleanExtra("use-test-fonts", false)) {
                        arrayList.add("--use-test-fonts");
                    }
                    if (intent2.getBooleanExtra("enable-dart-profiling", false)) {
                        arrayList.add("--enable-dart-profiling");
                    }
                    if (intent2.getBooleanExtra("profile-startup", false)) {
                        arrayList.add("--profile-startup");
                    }
                    if (intent2.getBooleanExtra("enable-software-rendering", false)) {
                        arrayList.add("--enable-software-rendering");
                    }
                    if (intent2.getBooleanExtra("skia-deterministic-rendering", false)) {
                        arrayList.add("--skia-deterministic-rendering");
                    }
                    if (intent2.getBooleanExtra("trace-skia", false)) {
                        arrayList.add("--trace-skia");
                    }
                    String stringExtra2 = intent2.getStringExtra("trace-skia-allowlist");
                    if (stringExtra2 != null) {
                        arrayList.add("--trace-skia-allowlist=".concat(stringExtra2));
                    }
                    if (intent2.getBooleanExtra("trace-systrace", false)) {
                        arrayList.add("--trace-systrace");
                    }
                    if (intent2.hasExtra("trace-to-file")) {
                        arrayList.add("--trace-to-file=" + intent2.getStringExtra("trace-to-file"));
                    }
                    if (intent2.hasExtra("profile-microtasks")) {
                        arrayList.add("--profile-microtasks");
                    }
                    if (intent2.hasExtra("enable-impeller")) {
                        if (intent2.getBooleanExtra("enable-impeller", false)) {
                            arrayList.add("--enable-impeller=true");
                        } else {
                            arrayList.add("--enable-impeller=false");
                        }
                    }
                    if (intent2.getBooleanExtra("enable-flutter-gpu", false)) {
                        arrayList.add("--enable-flutter-gpu");
                    }
                    if (intent2.getBooleanExtra("enable-vulkan-validation", false)) {
                        arrayList.add("--enable-vulkan-validation");
                    }
                    if (intent2.hasExtra("enable-hcpp-and-surface-control")) {
                        if (intent2.getBooleanExtra("enable-hcpp-and-surface-control", false)) {
                            arrayList.add("--enable-hcpp-and-surface-control=true");
                        } else {
                            arrayList.add("--enable-hcpp-and-surface-control=false");
                        }
                    }
                    if (intent2.getBooleanExtra("dump-skp-on-shader-compilation", false)) {
                        arrayList.add("--dump-skp-on-shader-compilation");
                    }
                    if (intent2.getBooleanExtra("cache-sksl", false)) {
                        arrayList.add("--cache-sksl");
                    }
                    if (intent2.getBooleanExtra("purge-persistent-cache", false)) {
                        arrayList.add("--purge-persistent-cache");
                    }
                    if (intent2.getBooleanExtra("verbose-logging", false)) {
                        arrayList.add("--verbose-logging");
                    }
                    if (intent2.getBooleanExtra("test-flag", false)) {
                        arrayList.add("--test-flag");
                    }
                    if (intent2.hasExtra("dart-flags")) {
                        arrayList.add("--dart-flags=" + intent2.getStringExtra("dart-flags"));
                    }
                    HashSet hashSet = new HashSet(arrayList);
                    P.j jVar4 = new P.j(abstractActivityC0007e3, (String[]) hashSet.toArray(new String[hashSet.size()]));
                    AbstractActivityC0007e abstractActivityC0007e4 = c0011i.f314a;
                    abstractActivityC0007e4.getClass();
                    P.i iVar3 = new P.i(abstractActivityC0007e4);
                    iVar3.f450e = false;
                    iVar3.f451f = c0011i.f314a.j();
                    c0011i.a(iVar3);
                    c0011i.f315b = jVar4.a(iVar3);
                    c0011i.f320g = false;
                }
            }
        }
        c0011i.f314a.getClass();
        P.e eVar = c0011i.f315b.f395c;
        androidx.lifecycle.j jVar5 = c0011i.f314a.f308c;
        eVar.getClass();
        e0.a.b("FlutterEngineConnectionRegistry#attachToActivity");
        try {
            C0011i c0011i2 = (C0011i) eVar.f426g;
            if (c0011i2 != null) {
                c0011i2.b();
            }
            eVar.e();
            eVar.f426g = c0011i;
            AbstractActivityC0007e abstractActivityC0007e5 = c0011i.f314a;
            abstractActivityC0007e5.getClass();
            eVar.b(abstractActivityC0007e5, jVar5);
            Trace.endSection();
            AbstractActivityC0007e abstractActivityC0007e6 = c0011i.f314a;
            abstractActivityC0007e6.getClass();
            AbstractActivityC0007e abstractActivityC0007e7 = c0011i.f314a;
            P.c cVar2 = c0011i.f315b;
            abstractActivityC0007e7.getClass();
            c0011i.f317d = new io.flutter.plugin.platform.e(abstractActivityC0007e7, cVar2.f403k, abstractActivityC0007e7);
            AbstractActivityC0007e abstractActivityC0007e8 = c0011i.f314a;
            P.c cVar3 = c0011i.f315b;
            abstractActivityC0007e8.getClass();
            int i6 = f305e;
            c0011i.f318e = new J.m(i6, abstractActivityC0007e6, cVar3.m);
            AbstractActivityC0007e abstractActivityC0007e9 = c0011i.f314a;
            P.c cVar4 = c0011i.f315b;
            MainActivity mainActivity = (MainActivity) abstractActivityC0007e9;
            mainActivity.getClass();
            p0.h.e(cVar4, "flutterEngine");
            if (!mainActivity.f307b.f320g) {
                p000a.a.J(cVar4);
            }
            new H.e(cVar4.f394b.f464h, "minireel/device").j(new C.a());
            c0011i.f323j = true;
            C0011i c0011i3 = this.f307b;
            c0011i3.c();
            if (bundle != null) {
                bundle.getBundle("plugins");
                byteArray = bundle.getByteArray("framework");
            } else {
                byteArray = null;
            }
            if (c0011i3.f314a.j()) {
                Y.l lVar = c0011i3.f315b.f402j;
                lVar.f559e = true;
                Y.k kVar = lVar.f558d;
                if (kVar != null) {
                    kVar.d(Y.l.a(byteArray));
                    lVar.f558d = null;
                    lVar.f556b = byteArray;
                } else if (lVar.f560f) {
                    lVar.f557c.h("push", Y.l.a(byteArray), new Y.k(0, lVar, byteArray));
                } else {
                    lVar.f556b = byteArray;
                }
            }
            c0011i3.f314a.getClass();
            P.e eVar2 = c0011i3.f315b.f395c;
            if (eVar2.f()) {
                e0.a.b("FlutterEngineConnectionRegistry#onRestoreInstanceState");
                try {
                    Iterator it2 = ((P.d) eVar2.f427h).f419f.iterator();
                    if (it2.hasNext()) {
                        if (it2.next() != null) {
                            throw new ClassCastException();
                        }
                        throw null;
                    }
                    Trace.endSection();
                } catch (Throwable th2) {
                    try {
                        Trace.endSection();
                        throw th2;
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                        throw th2;
                    }
                }
            } else {
                Log.e("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onRestoreInstanceState, but no Activity was attached.");
            }
            this.f308c.a(androidx.lifecycle.d.ON_CREATE);
            if (c() == 2) {
                getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            C0011i c0011i4 = this.f307b;
            boolean z3 = (c() == 1 ? (char) 1 : (char) 2) == 1;
            c0011i4.c();
            if (c0011i4.f314a.c() == 1) {
                AbstractActivityC0007e abstractActivityC0007e10 = c0011i4.f314a;
                abstractActivityC0007e10.getClass();
                m mVar = new m(abstractActivityC0007e10, c0011i4.f314a.c() != 1);
                c0011i4.f314a.getClass();
                AbstractActivityC0007e abstractActivityC0007e11 = c0011i4.f314a;
                abstractActivityC0007e11.getClass();
                c0011i4.f316c = new t(abstractActivityC0007e11, mVar);
            } else {
                AbstractActivityC0007e abstractActivityC0007e12 = c0011i4.f314a;
                abstractActivityC0007e12.getClass();
                o oVar = new o(abstractActivityC0007e12);
                oVar.setOpaque(c0011i4.f314a.c() == 1);
                c0011i4.f314a.getClass();
                AbstractActivityC0007e abstractActivityC0007e13 = c0011i4.f314a;
                abstractActivityC0007e13.getClass();
                c0011i4.f316c = new t(abstractActivityC0007e13, oVar);
            }
            c0011i4.f316c.f358g.add(c0011i4.f325l);
            c0011i4.f314a.getClass();
            t tVar = c0011i4.f316c;
            P.c cVar5 = c0011i4.f315b;
            C0008f c0008f = tVar.f373x;
            Objects.toString(cVar5);
            if (!tVar.c()) {
                tVar.f360i = cVar5;
                jVar = cVar5.f393a;
                tVar.f359h = jVar.f796d;
                tVar.f356e.a(jVar);
                jVar.a(c0008f);
                if (tVar.f352a) {
                    jVar.f793a.addResizingFlutterUiListener(tVar.f372w);
                }
                tVar.f362k = new H.a(tVar, tVar.f360i.f399g);
                P.c cVar6 = tVar.f360i;
                tVar.f363l = new io.flutter.plugin.editing.m(tVar, cVar6.f408q, cVar6.f404l, cVar6.f409r, cVar6.f410s);
                try {
                    TextServicesManager textServicesManager = (TextServicesManager) tVar.getContext().getSystemService("textservices");
                    tVar.f368r = textServicesManager;
                    tVar.m = new io.flutter.plugin.editing.h(textServicesManager, tVar.f360i.f406o);
                } catch (Exception unused2) {
                    Log.e("FlutterView", "TextServicesManager not supported by device, spell check disabled.");
                }
                new H.a(tVar, tVar.f363l.f854b, tVar.f360i.f404l);
                tVar.f364n = tVar.f360i.f396d;
                G.f fVar = new G.f();
                fVar.f165e = new HashSet();
                fVar.f166f = tVar;
                fVar.f164d = new C[]{new A(tVar.getBinaryMessenger()), new H.a(new Y.c(tVar.getBinaryMessenger()))};
                new C0.x(tVar.getBinaryMessenger()).f90e = fVar;
                tVar.f365o = fVar;
                tVar.f366p = new C0004b(tVar.f360i.f393a, false);
                io.flutter.view.n nVar = new io.flutter.view.n(tVar, cVar5.f397e, (AccessibilityManager) tVar.getContext().getSystemService("accessibility"), tVar.getContext().getContentResolver(), cVar5.f411t);
                tVar.f367q = nVar;
                nVar.f1039r = tVar.f371u;
                zIsEnabled = nVar.f1025c.isEnabled();
                boolean zIsTouchExplorationEnabled = tVar.f367q.f1025c.isTouchExplorationEnabled();
                if (tVar.f360i.f393a.f793a.getIsSoftwareRenderingEnabled()) {
                    tVar.setWillNotDraw(false);
                } else {
                    if (!zIsEnabled || zIsTouchExplorationEnabled) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                    tVar.setWillNotDraw(z2);
                }
                P.c cVar7 = tVar.f360i;
                cVar7.f409r.f900k.f868a = tVar.f367q;
                new C0004b(cVar7.f393a, true);
                P.c cVar8 = tVar.f360i;
                cVar8.f410s.f885i.f868a = tVar.f367q;
                new C0004b(cVar8.f393a, true);
                tVar.f363l.f854b.restartInput(tVar);
                tVar.d();
                tVar.getContext().getContentResolver().registerContentObserver(Settings.System.getUriFor("show_password"), false, tVar.v);
                tVar.e();
                jVar2 = cVar5.f409r;
                sparseArray = jVar2.f902n;
                sparseArray2 = jVar2.f903o;
                sparseArray3 = jVar2.f905q;
                jVar2.f895f = tVar;
                for (i2 = 0; i2 < sparseArray3.size(); i2++) {
                    jVar2.f895f.addView((io.flutter.plugin.platform.g) sparseArray3.valueAt(i2));
                }
                for (i3 = 0; i3 < sparseArray2.size(); i3++) {
                    if (sparseArray2.valueAt(i3) == null) {
                        throw new ClassCastException();
                    }
                    jVar2.f895f.addView(null);
                }
                if (sparseArray.size() <= 0) {
                    sparseArray.valueAt(0).getClass();
                    throw new ClassCastException();
                }
                iVar = cVar5.f410s;
                sparseArray4 = iVar.f886j;
                sparseArray5 = iVar.f887k;
                iVar.f882f = tVar;
                for (i4 = 0; i4 < sparseArray5.size(); i4++) {
                    if (sparseArray5.valueAt(i4) == null) {
                        throw new ClassCastException();
                    }
                    iVar.f882f.addView(null);
                }
                if (sparseArray4.size() <= 0) {
                    sparseArray4.valueAt(0).getClass();
                    throw new ClassCastException();
                }
                it = tVar.f361j.iterator();
                if (!it.hasNext()) {
                    it.next().getClass();
                    throw new ClassCastException();
                }
                if (tVar.f359h) {
                    c0008f.b();
                }
            } else if (cVar5 != tVar.f360i) {
                tVar.a();
                tVar.f360i = cVar5;
                jVar = cVar5.f393a;
                tVar.f359h = jVar.f796d;
                tVar.f356e.a(jVar);
                jVar.a(c0008f);
                if (tVar.f352a) {
                    jVar.f793a.addResizingFlutterUiListener(tVar.f372w);
                }
                tVar.f362k = new H.a(tVar, tVar.f360i.f399g);
                P.c cVar9 = tVar.f360i;
                tVar.f363l = new io.flutter.plugin.editing.m(tVar, cVar9.f408q, cVar9.f404l, cVar9.f409r, cVar9.f410s);
                TextServicesManager textServicesManager2 = (TextServicesManager) tVar.getContext().getSystemService("textservices");
                tVar.f368r = textServicesManager2;
                tVar.m = new io.flutter.plugin.editing.h(textServicesManager2, tVar.f360i.f406o);
                new H.a(tVar, tVar.f363l.f854b, tVar.f360i.f404l);
                tVar.f364n = tVar.f360i.f396d;
                G.f fVar2 = new G.f();
                fVar2.f165e = new HashSet();
                fVar2.f166f = tVar;
                fVar2.f164d = new C[]{new A(tVar.getBinaryMessenger()), new H.a(new Y.c(tVar.getBinaryMessenger()))};
                new C0.x(tVar.getBinaryMessenger()).f90e = fVar2;
                tVar.f365o = fVar2;
                tVar.f366p = new C0004b(tVar.f360i.f393a, false);
                io.flutter.view.n nVar2 = new io.flutter.view.n(tVar, cVar5.f397e, (AccessibilityManager) tVar.getContext().getSystemService("accessibility"), tVar.getContext().getContentResolver(), cVar5.f411t);
                tVar.f367q = nVar2;
                nVar2.f1039r = tVar.f371u;
                zIsEnabled = nVar2.f1025c.isEnabled();
                boolean zIsTouchExplorationEnabled2 = tVar.f367q.f1025c.isTouchExplorationEnabled();
                if (tVar.f360i.f393a.f793a.getIsSoftwareRenderingEnabled()) {
                    if (zIsEnabled) {
                        z2 = false;
                    } else {
                        z2 = false;
                    }
                    tVar.setWillNotDraw(z2);
                } else {
                    tVar.setWillNotDraw(false);
                }
                P.c cVar10 = tVar.f360i;
                cVar10.f409r.f900k.f868a = tVar.f367q;
                new C0004b(cVar10.f393a, true);
                P.c cVar11 = tVar.f360i;
                cVar11.f410s.f885i.f868a = tVar.f367q;
                new C0004b(cVar11.f393a, true);
                tVar.f363l.f854b.restartInput(tVar);
                tVar.d();
                tVar.getContext().getContentResolver().registerContentObserver(Settings.System.getUriFor("show_password"), false, tVar.v);
                tVar.e();
                jVar2 = cVar5.f409r;
                sparseArray = jVar2.f902n;
                sparseArray2 = jVar2.f903o;
                sparseArray3 = jVar2.f905q;
                jVar2.f895f = tVar;
                while (i2 < sparseArray3.size()) {
                    jVar2.f895f.addView((io.flutter.plugin.platform.g) sparseArray3.valueAt(i2));
                }
                while (i3 < sparseArray2.size()) {
                    if (sparseArray2.valueAt(i3) == null) {
                        throw new ClassCastException();
                    }
                    jVar2.f895f.addView(null);
                }
                if (sparseArray.size() <= 0) {
                    sparseArray.valueAt(0).getClass();
                    throw new ClassCastException();
                }
                iVar = cVar5.f410s;
                sparseArray4 = iVar.f886j;
                sparseArray5 = iVar.f887k;
                iVar.f882f = tVar;
                while (i4 < sparseArray5.size()) {
                    if (sparseArray5.valueAt(i4) == null) {
                        throw new ClassCastException();
                    }
                    iVar.f882f.addView(null);
                }
                if (sparseArray4.size() <= 0) {
                    sparseArray4.valueAt(0).getClass();
                    throw new ClassCastException();
                }
                it = tVar.f361j.iterator();
                if (!it.hasNext()) {
                    it.next().getClass();
                    throw new ClassCastException();
                }
                if (tVar.f359h) {
                    c0008f.b();
                }
            }
            c0011i4.f316c.setId(i6);
            if (z3) {
                t tVar2 = c0011i4.f316c;
                if (c0011i4.f314a.c() != 1) {
                    throw new IllegalArgumentException("Cannot delay the first Android view draw when the render mode is not set to `RenderMode.surface`.");
                }
                if (c0011i4.f319f != null) {
                    tVar2.getViewTreeObserver().removeOnPreDrawListener(c0011i4.f319f);
                }
                c0011i4.f319f = new ViewTreeObserverOnPreDrawListenerC0009g(c0011i4, tVar2);
                tVar2.getViewTreeObserver().addOnPreDrawListener(c0011i4.f319f);
            }
            setContentView(c0011i4.f316c);
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            if (Build.VERSION.SDK_INT < 35) {
                window.setStatusBarColor(1073741824);
            }
            window.getDecorView().setSystemUiVisibility(1280);
        } catch (Throwable th4) {
            try {
                Trace.endSection();
                throw th4;
            } catch (Throwable th5) {
                th4.addSuppressed(th5);
                throw th4;
            }
        }
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        if (k("onDestroy")) {
            this.f307b.e();
            this.f307b.f();
        }
        if (Build.VERSION.SDK_INT >= 33) {
            getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(this.f309d);
            this.f306a = false;
        }
        C0011i c0011i = this.f307b;
        if (c0011i != null) {
            c0011i.f314a = null;
            c0011i.f315b = null;
            c0011i.f316c = null;
            c0011i.f317d = null;
            c0011i.f318e = null;
            this.f307b = null;
        }
        this.f308c.a(androidx.lifecycle.d.ON_DESTROY);
    }

    @Override // android.app.Activity
    public final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (k("onNewIntent")) {
            C0011i c0011i = this.f307b;
            c0011i.c();
            P.c cVar = c0011i.f315b;
            if (cVar == null) {
                Log.w("FlutterActivityAndFragmentDelegate", "onNewIntent() invoked before FlutterFragment was attached to an Activity.");
                return;
            }
            P.e eVar = cVar.f395c;
            if (eVar.f()) {
                e0.a.b("FlutterEngineConnectionRegistry#onNewIntent");
                try {
                    Iterator it = ((P.d) eVar.f427h).f417d.iterator();
                    if (it.hasNext()) {
                        if (it.next() != null) {
                            throw new ClassCastException();
                        }
                        throw null;
                    }
                    Trace.endSection();
                } catch (Throwable th) {
                    try {
                        Trace.endSection();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } else {
                Log.e("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onNewIntent, but no Activity was attached.");
            }
            String strD = c0011i.d(intent);
            if (strD == null || strD.isEmpty()) {
                return;
            }
            Y.b bVar = c0011i.f315b.f400h;
            bVar.getClass();
            HashMap map = new HashMap();
            map.put("location", strD);
            bVar.f519a.h("pushRouteInformation", map, null);
        }
    }

    @Override // android.app.Activity
    public final void onPause() {
        super.onPause();
        if (k("onPause")) {
            C0011i c0011i = this.f307b;
            c0011i.c();
            c0011i.f314a.getClass();
            P.c cVar = c0011i.f315b;
            if (cVar != null) {
                Y.d dVar = cVar.f398f;
                dVar.a(3, dVar.f523c);
            }
        }
        this.f308c.a(androidx.lifecycle.d.ON_PAUSE);
    }

    @Override // android.app.Activity
    public final void onPostResume() {
        super.onPostResume();
        if (k("onPostResume")) {
            C0011i c0011i = this.f307b;
            c0011i.c();
            if (c0011i.f315b == null) {
                Log.w("FlutterActivityAndFragmentDelegate", "onPostResume() invoked before FlutterFragment was attached to an Activity.");
                return;
            }
            io.flutter.plugin.platform.e eVar = c0011i.f317d;
            if (eVar != null) {
                eVar.b();
            }
            Iterator it = c0011i.f315b.f409r.f901l.values().iterator();
            if (it.hasNext()) {
                ((io.flutter.plugin.platform.p) it.next()).getClass();
                throw null;
            }
        }
    }

    @Override // android.app.Activity
    public final void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (k("onRequestPermissionsResult")) {
            C0011i c0011i = this.f307b;
            c0011i.c();
            if (c0011i.f315b == null) {
                Log.w("FlutterActivityAndFragmentDelegate", "onRequestPermissionResult() invoked before FlutterFragment was attached to an Activity.");
                return;
            }
            Arrays.toString(strArr);
            Arrays.toString(iArr);
            P.e eVar = c0011i.f315b.f395c;
            if (!eVar.f()) {
                Log.e("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onRequestPermissionsResult, but no Activity was attached.");
                return;
            }
            e0.a.b("FlutterEngineConnectionRegistry#onRequestPermissionsResult");
            try {
                Iterator it = ((P.d) eVar.f427h).f415b.iterator();
                if (!it.hasNext()) {
                    Trace.endSection();
                } else {
                    if (it.next() != null) {
                        throw new ClassCastException();
                    }
                    throw null;
                }
            } catch (Throwable th) {
                try {
                    Trace.endSection();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    @Override // android.app.Activity
    public final void onResume() {
        super.onResume();
        this.f308c.a(androidx.lifecycle.d.ON_RESUME);
        if (k("onResume")) {
            C0011i c0011i = this.f307b;
            c0011i.c();
            c0011i.f315b.f393a.g();
            c0011i.f314a.getClass();
            P.c cVar = c0011i.f315b;
            if (cVar != null) {
                Y.d dVar = cVar.f398f;
                dVar.a(2, dVar.f523c);
            }
        }
    }

    @Override // android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (k("onSaveInstanceState")) {
            C0011i c0011i = this.f307b;
            c0011i.c();
            if (c0011i.f314a.j()) {
                bundle.putByteArray("framework", c0011i.f315b.f402j.f556b);
            }
            c0011i.f314a.getClass();
            Bundle bundle2 = new Bundle();
            P.e eVar = c0011i.f315b.f395c;
            if (eVar.f()) {
                e0.a.b("FlutterEngineConnectionRegistry#onSaveInstanceState");
                try {
                    Iterator it = ((P.d) eVar.f427h).f419f.iterator();
                    if (it.hasNext()) {
                        if (it.next() != null) {
                            throw new ClassCastException();
                        }
                        throw null;
                    }
                    Trace.endSection();
                } catch (Throwable th) {
                    try {
                        Trace.endSection();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } else {
                Log.e("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onSaveInstanceState, but no Activity was attached.");
            }
            bundle.putBundle("plugins", bundle2);
            if (c0011i.f314a.d() == null || c0011i.f314a.i()) {
                return;
            }
            bundle.putBoolean("enableOnBackInvokedCallbackState", c0011i.f314a.f306a);
        }
    }

    @Override // android.app.Activity
    public final void onStart() {
        String string;
        super.onStart();
        this.f308c.a(androidx.lifecycle.d.ON_START);
        if (k("onStart")) {
            C0011i c0011i = this.f307b;
            c0011i.c();
            if (c0011i.f314a.d() == null && !c0011i.f315b.f394b.f465i) {
                String strF = c0011i.f314a.f();
                if (strF == null) {
                    AbstractActivityC0007e abstractActivityC0007e = c0011i.f314a;
                    abstractActivityC0007e.getClass();
                    strF = c0011i.d(abstractActivityC0007e.getIntent());
                    if (strF == null) {
                        strF = "/";
                    }
                }
                AbstractActivityC0007e abstractActivityC0007e2 = c0011i.f314a;
                abstractActivityC0007e2.getClass();
                try {
                    Bundle bundleG = abstractActivityC0007e2.g();
                    string = bundleG != null ? bundleG.getString("io.flutter.EntrypointUri") : null;
                } catch (PackageManager.NameNotFoundException unused) {
                }
                c0011i.f314a.e();
                c0011i.f315b.f400h.f519a.h("setInitialRoute", strF, null);
                String strB = c0011i.f314a.b();
                if (strB == null || strB.isEmpty()) {
                    strB = (String) ((T.c) G.f.d().f164d).f511e.f165e;
                }
                c0011i.f315b.f394b.d(string == null ? new Q.a(strB, c0011i.f314a.e()) : new Q.a(strB, string, c0011i.f314a.e()), (List) c0011i.f314a.getIntent().getSerializableExtra("dart_entrypoint_args"));
            }
            Integer num = c0011i.f324k;
            if (num != null) {
                c0011i.f316c.setVisibility(num.intValue());
            }
        }
    }

    @Override // android.app.Activity
    public final void onStop() {
        super.onStop();
        if (k("onStop")) {
            C0011i c0011i = this.f307b;
            c0011i.c();
            c0011i.f314a.getClass();
            P.c cVar = c0011i.f315b;
            if (cVar != null) {
                Y.d dVar = cVar.f398f;
                dVar.a(5, dVar.f523c);
            }
            c0011i.f324k = Integer.valueOf(c0011i.f316c.getVisibility());
            c0011i.f316c.setVisibility(8);
            P.c cVar2 = c0011i.f315b;
            if (cVar2 != null) {
                cVar2.f393a.d(40);
            }
        }
        this.f308c.a(androidx.lifecycle.d.ON_STOP);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public final void onTrimMemory(int i2) {
        super.onTrimMemory(i2);
        if (k("onTrimMemory")) {
            C0011i c0011i = this.f307b;
            c0011i.c();
            P.c cVar = c0011i.f315b;
            if (cVar != null) {
                if (c0011i.f322i && i2 >= 10) {
                    FlutterJNI flutterJNI = cVar.f394b.f460d;
                    if (flutterJNI.isAttached()) {
                        flutterJNI.notifyLowMemoryWarning();
                    }
                    Y.c cVar2 = c0011i.f315b.f407p;
                    cVar2.getClass();
                    HashMap map = new HashMap(1);
                    map.put("type", "memoryPressure");
                    cVar2.f520a.g(map, null);
                }
                c0011i.f315b.f393a.d(i2);
                io.flutter.plugin.platform.j jVar = c0011i.f315b.f409r;
                if (i2 < 40) {
                    jVar.getClass();
                    return;
                }
                Iterator it = jVar.f901l.values().iterator();
                if (it.hasNext()) {
                    ((io.flutter.plugin.platform.p) it.next()).getClass();
                    throw null;
                }
            }
        }
    }

    @Override // android.app.Activity
    public final void onUserLeaveHint() {
        if (k("onUserLeaveHint")) {
            C0011i c0011i = this.f307b;
            c0011i.c();
            P.c cVar = c0011i.f315b;
            if (cVar == null) {
                Log.w("FlutterActivityAndFragmentDelegate", "onUserLeaveHint() invoked before FlutterFragment was attached to an Activity.");
                return;
            }
            P.e eVar = cVar.f395c;
            if (!eVar.f()) {
                Log.e("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onUserLeaveHint, but no Activity was attached.");
                return;
            }
            e0.a.b("FlutterEngineConnectionRegistry#onUserLeaveHint");
            try {
                Iterator it = ((P.d) eVar.f427h).f418e.iterator();
                if (!it.hasNext()) {
                    Trace.endSection();
                } else {
                    if (it.next() != null) {
                        throw new ClassCastException();
                    }
                    throw null;
                }
            } catch (Throwable th) {
                try {
                    Trace.endSection();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        if (k("onWindowFocusChanged")) {
            C0011i c0011i = this.f307b;
            c0011i.c();
            c0011i.f314a.getClass();
            P.c cVar = c0011i.f315b;
            if (cVar != null) {
                Y.d dVar = cVar.f398f;
                if (z2) {
                    dVar.a(dVar.f521a, true);
                } else {
                    dVar.a(dVar.f521a, false);
                }
            }
        }
    }
}
