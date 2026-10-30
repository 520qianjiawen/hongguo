package io.flutter.view;

import android.view.accessibility.AccessibilityManager;

/* JADX INFO: loaded from: classes.dex */
public final class e implements AccessibilityManager.TouchExplorationStateChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AccessibilityManager f933a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ n f934b;

    public e(n nVar, AccessibilityManager accessibilityManager) {
        this.f934b = nVar;
        this.f933a = accessibilityManager;
    }

    @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    public final void onTouchExplorationStateChanged(boolean z2) {
        n nVar = this.f934b;
        if (nVar.f1041t) {
            return;
        }
        boolean z3 = false;
        if (!z2) {
            nVar.j(false);
            m mVar = nVar.f1036o;
            if (mVar != null) {
                nVar.g(mVar.f994b, 256);
                nVar.f1036o = null;
            }
        }
        C0.x xVar = nVar.f1039r;
        if (xVar != null) {
            boolean zIsEnabled = this.f933a.isEnabled();
            O.t tVar = (O.t) xVar.f90e;
            if (tVar.f360i.f393a.f793a.getIsSoftwareRenderingEnabled()) {
                tVar.setWillNotDraw(false);
                return;
            }
            if (!zIsEnabled && !z2) {
                z3 = true;
            }
            tVar.setWillNotDraw(z3);
        }
    }
}
