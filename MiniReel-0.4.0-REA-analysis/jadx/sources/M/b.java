package M;

import P.d;
import android.app.Activity;
import android.view.Window;
import defpackage.e;
import defpackage.f;
import p0.h;

/* JADX INFO: loaded from: classes.dex */
public final class b implements V.b, f, W.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f269d;

    @Override // W.a
    public final void onAttachedToActivity(W.b bVar) {
        Window window;
        h.e(bVar, "binding");
        a aVar = this.f269d;
        if (aVar != null) {
            Activity activity = ((d) bVar).f414a;
            aVar.f268b = activity;
            if (!aVar.f267a || activity == null || (window = activity.getWindow()) == null) {
                return;
            }
            if (aVar.f267a) {
                window.addFlags(128);
            } else {
                window.clearFlags(128);
            }
        }
    }

    @Override // V.b
    public final void onAttachedToEngine(V.a aVar) {
        h.e(aVar, "flutterPluginBinding");
        Z.f fVar = aVar.f517c;
        h.d(fVar, "getBinaryMessenger(...)");
        e.a(f.f698a, fVar, this);
        this.f269d = new a();
    }

    @Override // W.a
    public final void onDetachedFromActivity() {
        a aVar = this.f269d;
        if (aVar != null) {
            aVar.f268b = null;
        }
    }

    @Override // W.a
    public final void onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity();
    }

    @Override // V.b
    public final void onDetachedFromEngine(V.a aVar) {
        h.e(aVar, "binding");
        Z.f fVar = aVar.f517c;
        h.d(fVar, "getBinaryMessenger(...)");
        e.a(f.f698a, fVar, null);
        this.f269d = null;
    }

    @Override // W.a
    public final void onReattachedToActivityForConfigChanges(W.b bVar) {
        h.e(bVar, "binding");
        onAttachedToActivity(bVar);
    }
}
