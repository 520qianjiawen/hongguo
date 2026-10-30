package J;

import C0.x;
import O.AbstractActivityC0007e;
import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class m implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f221a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f222b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f223c;

    public m(int i2, AbstractActivityC0007e abstractActivityC0007e, x xVar) {
        this.f222b = abstractActivityC0007e;
        this.f221a = i2;
        this.f223c = xVar;
        xVar.f90e = this;
    }

    @Override // J.k
    public void b() {
        HandlerThread handlerThread = (HandlerThread) this.f222b;
        if (handlerThread != null) {
            handlerThread.quit();
            this.f222b = null;
            this.f223c = null;
        }
    }

    @Override // J.k
    public void c(h hVar) {
        ((Handler) this.f223c).post(hVar.f204b);
    }

    public int d() {
        if (Build.VERSION.SDK_INT < 35) {
            return 2;
        }
        Activity activity = (Activity) this.f222b;
        int i2 = this.f221a;
        View viewFindViewById = activity.findViewById(i2);
        if (viewFindViewById != null) {
            return viewFindViewById.getContentSensitivity();
        }
        throw new IllegalArgumentException("FlutterView with ID " + i2 + "not found");
    }

    public void e(int i2) {
        if (Build.VERSION.SDK_INT < 35) {
            throw new IllegalStateException("isSupported() should be called before attempting to set content sensitivity as it is not supported on this device.");
        }
        Activity activity = (Activity) this.f222b;
        int i3 = this.f221a;
        View viewFindViewById = activity.findViewById(i3);
        if (viewFindViewById == null) {
            throw new IllegalArgumentException("FlutterView with ID " + i3 + "not found");
        }
        if (viewFindViewById.getContentSensitivity() == i2) {
            return;
        }
        viewFindViewById.setContentSensitivity(i2);
        viewFindViewById.invalidate();
    }

    @Override // J.k
    public void start() {
        HandlerThread handlerThread = new HandlerThread("Sqflite", this.f221a);
        this.f222b = handlerThread;
        handlerThread.start();
        this.f223c = new Handler(((HandlerThread) this.f222b).getLooper());
    }

    public m(int i2) {
        this.f221a = i2;
    }
}
