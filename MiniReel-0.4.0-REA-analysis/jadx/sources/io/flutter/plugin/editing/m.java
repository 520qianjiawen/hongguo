package io.flutter.plugin.editing;

import C0.x;
import O.AbstractC0003a;
import Y.o;
import Y.q;
import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: loaded from: classes.dex */
public final class m implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f853a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InputMethodManager f854b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AutofillManager f855c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final H.a f856d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public l f857e = new l(1, 0);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public o f858f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public SparseArray f859g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public g f860h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f861i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public d f862j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final io.flutter.plugin.platform.j f863k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final io.flutter.plugin.platform.i f864l;
    public Rect m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ImeSyncDeferringInsetsCallback f865n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public q f866o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f867p;

    public m(View view, H.a aVar, x xVar, io.flutter.plugin.platform.j jVar, io.flutter.plugin.platform.i iVar) {
        this.f853a = view;
        this.f860h = new g(null, view);
        this.f854b = (InputMethodManager) view.getContext().getSystemService("input_method");
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            this.f855c = AbstractC0003a.d(view.getContext().getSystemService(AbstractC0003a.j()));
        } else {
            this.f855c = null;
        }
        if (i2 >= 30) {
            ImeSyncDeferringInsetsCallback imeSyncDeferringInsetsCallback = new ImeSyncDeferringInsetsCallback(view);
            this.f865n = imeSyncDeferringInsetsCallback;
            imeSyncDeferringInsetsCallback.install();
            imeSyncDeferringInsetsCallback.setImeVisibilityListener(new j(this));
        }
        this.f856d = aVar;
        aVar.f171f = new j(this);
        ((H.e) aVar.f170e).h("TextInputClient.requestExistingInputState", null, null);
        this.f863k = jVar;
        jVar.f898i = this;
        this.f864l = iVar;
        iVar.getClass();
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0084, code lost:
    
        if (r7 == r0.f585e) goto L38;
     */
    @Override // io.flutter.plugin.editing.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(boolean r19) {
        /*
            Method dump skipped, instruction units count: 398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugin.editing.m.a(boolean):void");
    }

    public final void b() {
        this.f863k.f898i = null;
        this.f864l.getClass();
        this.f856d.f171f = null;
        c();
        this.f860h.e(this);
        ImeSyncDeferringInsetsCallback imeSyncDeferringInsetsCallback = this.f865n;
        if (imeSyncDeferringInsetsCallback != null) {
            imeSyncDeferringInsetsCallback.remove();
        }
    }

    public final void c() {
        AutofillManager autofillManager;
        o oVar;
        H.e eVar;
        if (Build.VERSION.SDK_INT < 26 || (autofillManager = this.f855c) == null || (oVar = this.f858f) == null || (eVar = oVar.f575j) == null || this.f859g == null) {
            return;
        }
        autofillManager.notifyViewExited(this.f853a, ((String) eVar.f174d).hashCode());
    }

    public final void d(o oVar) {
        H.e eVar;
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        if (oVar == null || (eVar = oVar.f575j) == null) {
            this.f859g = null;
            return;
        }
        o[] oVarArr = oVar.f577l;
        SparseArray sparseArray = new SparseArray();
        this.f859g = sparseArray;
        if (oVarArr == null) {
            sparseArray.put(((String) eVar.f174d).hashCode(), oVar);
            return;
        }
        for (o oVar2 : oVarArr) {
            H.e eVar2 = oVar2.f575j;
            if (eVar2 != null) {
                String str = (String) eVar2.f174d;
                this.f859g.put(str.hashCode(), oVar2);
                this.f855c.notifyValueChanged(this.f853a, str.hashCode(), AutofillValue.forText(((q) eVar2.f176f).f581a));
            }
        }
    }
}
