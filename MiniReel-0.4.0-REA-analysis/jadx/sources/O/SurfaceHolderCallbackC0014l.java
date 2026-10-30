package O;

import android.view.SurfaceHolder;

/* JADX INFO: renamed from: O.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class SurfaceHolderCallbackC0014l implements SurfaceHolder.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ m f333a;

    public SurfaceHolderCallbackC0014l(m mVar) {
        this.f333a = mVar;
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        m mVar = this.f333a;
        io.flutter.embedding.engine.renderer.j jVar = mVar.f336c;
        if (jVar == null || mVar.f335b) {
            return;
        }
        if (jVar == null) {
            throw new IllegalStateException("changeSurfaceSize() should only be called when flutterRenderer is non-null.");
        }
        jVar.f793a.onSurfaceChanged(i3, i4);
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        m mVar = this.f333a;
        mVar.f334a = true;
        if (mVar.f336c == null || mVar.f335b) {
            return;
        }
        mVar.e();
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        m mVar = this.f333a;
        mVar.f334a = false;
        io.flutter.embedding.engine.renderer.j jVar = mVar.f336c;
        if (jVar == null || mVar.f335b) {
            return;
        }
        if (jVar == null) {
            throw new IllegalStateException("disconnectSurfaceFromRenderer() should only be called when flutterRenderer is non-null.");
        }
        jVar.h();
    }
}
