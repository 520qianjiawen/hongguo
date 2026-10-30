package io.flutter.embedding.engine.renderer;

import android.graphics.SurfaceTexture;
import android.os.Handler;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.view.TextureRegistry$SurfaceTextureEntry;
import io.flutter.view.w;
import io.flutter.view.x;

/* JADX INFO: loaded from: classes.dex */
public final class g implements TextureRegistry$SurfaceTextureEntry, x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f762a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SurfaceTextureWrapper f763b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f764c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public x f765d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ j f766e;

    public g(j jVar, long j2, SurfaceTexture surfaceTexture) {
        this.f766e = jVar;
        this.f762a = j2;
        SurfaceTextureWrapper surfaceTextureWrapper = new SurfaceTextureWrapper(surfaceTexture, new b(this, 1));
        this.f763b = surfaceTextureWrapper;
        surfaceTextureWrapper.surfaceTexture().setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() { // from class: io.flutter.embedding.engine.renderer.f
            @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
            public final void onFrameAvailable(SurfaceTexture surfaceTexture2) {
                g gVar = this.f761a;
                FlutterJNI flutterJNI = gVar.f766e.f793a;
                if (gVar.f764c || !flutterJNI.isAttached()) {
                    return;
                }
                gVar.f763b.markDirty();
                flutterJNI.scheduleFrame();
            }
        }, new Handler());
    }

    public final void finalize() throws Throwable {
        try {
            if (this.f764c) {
                return;
            }
            j jVar = this.f766e;
            jVar.f797e.post(new h(this.f762a, jVar.f793a));
        } finally {
            super.finalize();
        }
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceTextureEntry
    public final long id() {
        return this.f762a;
    }

    @Override // io.flutter.view.x
    public final void onTrimMemory(int i2) {
        x xVar = this.f765d;
        if (xVar != null) {
            xVar.onTrimMemory(i2);
        }
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceTextureEntry
    public final void release() {
        if (this.f764c) {
            return;
        }
        this.f763b.release();
        long j2 = this.f762a;
        j jVar = this.f766e;
        jVar.f793a.unregisterTexture(j2);
        jVar.f(this);
        this.f764c = true;
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceTextureEntry
    public final void setOnFrameConsumedListener(w wVar) {
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceTextureEntry
    public final void setOnTrimMemoryListener(x xVar) {
        this.f765d = xVar;
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceTextureEntry
    public final SurfaceTexture surfaceTexture() {
        return this.f763b.surfaceTexture();
    }
}
