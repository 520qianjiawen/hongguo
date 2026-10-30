package io.flutter.view;

import android.view.accessibility.AccessibilityManager;
import io.flutter.embedding.engine.FlutterJNI;

/* JADX INFO: loaded from: classes.dex */
public final class d implements AccessibilityManager.AccessibilityStateChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ n f932a;

    public d(n nVar) {
        this.f932a = nVar;
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public final void onAccessibilityStateChanged(boolean z2) {
        n nVar = this.f932a;
        G.f fVar = nVar.f1024b;
        if (nVar.f1041t) {
            return;
        }
        if (z2) {
            ((FlutterJNI) fVar.f165e).setSemanticsEnabled(true);
        } else {
            nVar.j(false);
            ((FlutterJNI) fVar.f165e).setSemanticsEnabled(false);
        }
        C0.x xVar = nVar.f1039r;
        if (xVar != null) {
            boolean zIsTouchExplorationEnabled = nVar.f1025c.isTouchExplorationEnabled();
            O.t tVar = (O.t) xVar.f90e;
            if (tVar.f360i.f393a.f793a.getIsSoftwareRenderingEnabled()) {
                tVar.setWillNotDraw(false);
            } else {
                tVar.setWillNotDraw((z2 || zIsTouchExplorationEnabled) ? false : true);
            }
        }
    }
}
