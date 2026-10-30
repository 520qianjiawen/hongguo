package io.flutter.plugin.platform;

import O.C0013k;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes.dex */
public final class b extends C0013k {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a f869h;

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        a aVar = this.f869h;
        if (aVar != null) {
            io.flutter.view.n nVar = aVar.f868a;
            if (nVar == null ? false : nVar.e(motionEvent, true)) {
                return true;
            }
        }
        return super.onHoverEvent(motionEvent);
    }
}
