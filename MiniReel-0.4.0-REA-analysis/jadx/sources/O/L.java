package O;

import android.os.Build;
import android.view.SurfaceHolder;

/* JADX INFO: loaded from: classes.dex */
public final class L implements SurfaceHolder.Callback2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m f292a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public io.flutter.embedding.engine.renderer.j f293b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final SurfaceHolderCallbackC0014l f294c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0008f f295d = new C0008f(2, this);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final K f296e;

    public L(SurfaceHolderCallbackC0014l surfaceHolderCallbackC0014l, m mVar, io.flutter.embedding.engine.renderer.j jVar) {
        boolean z2 = Build.VERSION.SDK_INT < 26;
        this.f296e = z2 ? new K(this, 1) : new K(this, 0);
        this.f294c = surfaceHolderCallbackC0014l;
        this.f293b = jVar;
        this.f292a = mVar;
        if (z2) {
            mVar.setAlpha(0.0f);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        SurfaceHolderCallbackC0014l surfaceHolderCallbackC0014l = this.f294c;
        if (surfaceHolderCallbackC0014l != null) {
            surfaceHolderCallbackC0014l.surfaceChanged(surfaceHolder, i2, i3, i4);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        SurfaceHolderCallbackC0014l surfaceHolderCallbackC0014l = this.f294c;
        if (surfaceHolderCallbackC0014l != null) {
            surfaceHolderCallbackC0014l.surfaceCreated(surfaceHolder);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        SurfaceHolderCallbackC0014l surfaceHolderCallbackC0014l = this.f294c;
        if (surfaceHolderCallbackC0014l != null) {
            surfaceHolderCallbackC0014l.surfaceDestroyed(surfaceHolder);
        }
    }

    @Override // android.view.SurfaceHolder.Callback2
    public final void surfaceRedrawNeededAsync(SurfaceHolder surfaceHolder, Runnable runnable) {
        io.flutter.embedding.engine.renderer.j jVar = this.f293b;
        if (jVar == null) {
            return;
        }
        jVar.a(new J(this, runnable));
    }

    @Override // android.view.SurfaceHolder.Callback2
    public final void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
    }
}
