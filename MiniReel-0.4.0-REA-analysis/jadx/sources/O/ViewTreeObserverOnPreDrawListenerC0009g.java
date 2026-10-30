package O;

import android.view.ViewTreeObserver;

/* JADX INFO: renamed from: O.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeObserverOnPreDrawListenerC0009g implements ViewTreeObserver.OnPreDrawListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ t f312a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0011i f313b;

    public ViewTreeObserverOnPreDrawListenerC0009g(C0011i c0011i, t tVar) {
        this.f313b = c0011i;
        this.f312a = tVar;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        C0011i c0011i = this.f313b;
        if (c0011i.f321h && c0011i.f319f != null) {
            this.f312a.getViewTreeObserver().removeOnPreDrawListener(this);
            c0011i.f319f = null;
        }
        return c0011i.f321h;
    }
}
