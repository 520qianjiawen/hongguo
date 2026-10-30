package O;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView;

/* JADX INFO: loaded from: classes.dex */
public final class n implements TextureView.SurfaceTextureListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ o f339a;

    public n(o oVar) {
        this.f339a = oVar;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        o oVar = this.f339a;
        oVar.f340a = true;
        if (oVar.f342c == null || oVar.f341b) {
            return;
        }
        oVar.e();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        o oVar = this.f339a;
        oVar.f340a = false;
        io.flutter.embedding.engine.renderer.j jVar = oVar.f342c;
        if (jVar != null && !oVar.f341b) {
            if (jVar == null) {
                throw new IllegalStateException("disconnectSurfaceFromRenderer() should only be called when flutterRenderer is non-null.");
            }
            jVar.h();
            Surface surface = oVar.f343d;
            if (surface != null) {
                surface.release();
                oVar.f343d = null;
            }
        }
        Surface surface2 = oVar.f343d;
        if (surface2 == null) {
            return true;
        }
        surface2.release();
        oVar.f343d = null;
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
        o oVar = this.f339a;
        io.flutter.embedding.engine.renderer.j jVar = oVar.f342c;
        if (jVar == null || oVar.f341b) {
            return;
        }
        if (jVar == null) {
            throw new IllegalStateException("changeSurfaceSize() should only be called when flutterRenderer is non-null.");
        }
        jVar.f793a.onSurfaceChanged(i2, i3);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }
}
