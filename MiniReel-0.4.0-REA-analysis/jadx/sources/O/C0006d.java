package O;

import android.util.Log;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;

/* JADX INFO: renamed from: O.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0006d implements OnBackAnimationCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AbstractActivityC0007e f304a;

    public C0006d(AbstractActivityC0007e abstractActivityC0007e) {
        this.f304a = abstractActivityC0007e;
    }

    public final void onBackCancelled() {
        AbstractActivityC0007e abstractActivityC0007e = this.f304a;
        if (abstractActivityC0007e.k("cancelBackGesture")) {
            C0011i c0011i = abstractActivityC0007e.f307b;
            c0011i.c();
            P.c cVar = c0011i.f315b;
            if (cVar != null) {
                cVar.f401i.f519a.h("cancelBackGesture", null, null);
            } else {
                Log.w("FlutterActivityAndFragmentDelegate", "Invoked cancelBackGesture() before FlutterFragment was attached to an Activity.");
            }
        }
    }

    public final void onBackInvoked() {
        AbstractActivityC0007e abstractActivityC0007e = this.f304a;
        if (abstractActivityC0007e.k("commitBackGesture")) {
            C0011i c0011i = abstractActivityC0007e.f307b;
            c0011i.c();
            P.c cVar = c0011i.f315b;
            if (cVar != null) {
                cVar.f401i.f519a.h("commitBackGesture", null, null);
            } else {
                Log.w("FlutterActivityAndFragmentDelegate", "Invoked commitBackGesture() before FlutterFragment was attached to an Activity.");
            }
        }
    }

    public final void onBackProgressed(BackEvent backEvent) {
        AbstractActivityC0007e abstractActivityC0007e = this.f304a;
        if (abstractActivityC0007e.k("updateBackGestureProgress")) {
            C0011i c0011i = abstractActivityC0007e.f307b;
            c0011i.c();
            P.c cVar = c0011i.f315b;
            if (cVar != null) {
                cVar.f401i.f519a.h("updateBackGestureProgress", Y.b.a(backEvent), null);
            } else {
                Log.w("FlutterActivityAndFragmentDelegate", "Invoked updateBackGestureProgress() before FlutterFragment was attached to an Activity.");
            }
        }
    }

    public final void onBackStarted(BackEvent backEvent) {
        AbstractActivityC0007e abstractActivityC0007e = this.f304a;
        if (abstractActivityC0007e.k("startBackGesture")) {
            C0011i c0011i = abstractActivityC0007e.f307b;
            c0011i.c();
            P.c cVar = c0011i.f315b;
            if (cVar != null) {
                cVar.f401i.f519a.h("startBackGesture", Y.b.a(backEvent), null);
            } else {
                Log.w("FlutterActivityAndFragmentDelegate", "Invoked startBackGesture() before FlutterFragment was attached to an Activity.");
            }
        }
    }
}
