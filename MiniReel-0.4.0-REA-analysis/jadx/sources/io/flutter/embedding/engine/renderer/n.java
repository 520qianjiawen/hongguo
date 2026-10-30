package io.flutter.embedding.engine.renderer;

import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.view.Surface;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.view.TextureRegistry$GLTextureConsumer;
import io.flutter.view.TextureRegistry$SurfaceProducer;
import io.flutter.view.y;

/* JADX INFO: loaded from: classes.dex */
public final class n implements TextureRegistry$SurfaceProducer, TextureRegistry$GLTextureConsumer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f801a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f802b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f803c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f804d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Surface f805e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final g f806f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Handler f807g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final FlutterJNI f808h;

    public n(long j2, Handler handler, FlutterJNI flutterJNI, g gVar) {
        this.f801a = j2;
        this.f807g = handler;
        this.f808h = flutterJNI;
        this.f806f = gVar;
    }

    public final void finalize() throws Throwable {
        try {
            if (this.f804d) {
                return;
            }
            release();
            this.f807g.post(new h(this.f801a, this.f808h));
        } finally {
            super.finalize();
        }
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public final Surface getForcedNewSurface() {
        this.f805e = null;
        return getSurface();
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public final int getHeight() {
        return this.f803c;
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public final Surface getSurface() {
        Surface surface = this.f805e;
        if (surface == null || !surface.isValid()) {
            this.f805e = new Surface(this.f806f.f763b.surfaceTexture());
        }
        return this.f805e;
    }

    @Override // io.flutter.view.TextureRegistry$GLTextureConsumer
    public final SurfaceTexture getSurfaceTexture() {
        return this.f806f.f763b.surfaceTexture();
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public final int getWidth() {
        return this.f802b;
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public final boolean handlesCropAndRotation() {
        return true;
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public final long id() {
        return this.f801a;
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public final void release() {
        this.f806f.release();
        this.f805e.release();
        this.f805e = null;
        this.f804d = true;
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public final void scheduleFrame() {
        this.f808h.markTextureFrameAvailable(this.f801a);
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public final void setCallback(y yVar) {
    }

    @Override // io.flutter.view.TextureRegistry$SurfaceProducer
    public final void setSize(int i2, int i3) {
        this.f802b = i2;
        this.f803c = i3;
        this.f806f.f763b.surfaceTexture().setDefaultBufferSize(i2, i3);
    }
}
