package O;

import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import io.flutter.embedding.engine.FlutterJNI;

/* JADX INFO: loaded from: classes.dex */
public final class o extends TextureView implements io.flutter.embedding.engine.renderer.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f340a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f341b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public io.flutter.embedding.engine.renderer.j f342c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Surface f343d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f344e;

    public o(AbstractActivityC0007e abstractActivityC0007e) {
        super(abstractActivityC0007e, null);
        this.f340a = false;
        this.f341b = false;
        this.f344e = false;
        setSurfaceTextureListener(new n(this));
        this.f344e = p000a.a.z(getContext());
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public final void a(io.flutter.embedding.engine.renderer.j jVar) {
        io.flutter.embedding.engine.renderer.j jVar2 = this.f342c;
        if (jVar2 != null) {
            jVar2.h();
        }
        this.f342c = jVar;
        d();
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public final void b() {
        if (this.f342c == null) {
            Log.w("FlutterTextureView", "pause() invoked when no FlutterRenderer was attached.");
        } else {
            this.f341b = true;
        }
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public final void c() {
        if (this.f342c == null) {
            Log.w("FlutterTextureView", "detachFromRenderer() invoked when no FlutterRenderer was attached.");
            return;
        }
        if (getWindowToken() != null) {
            io.flutter.embedding.engine.renderer.j jVar = this.f342c;
            if (jVar == null) {
                throw new IllegalStateException("disconnectSurfaceFromRenderer() should only be called when flutterRenderer is non-null.");
            }
            jVar.h();
            Surface surface = this.f343d;
            if (surface != null) {
                surface.release();
                this.f343d = null;
            }
        }
        this.f342c = null;
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public final void d() {
        if (this.f342c == null) {
            Log.w("FlutterTextureView", "resume() invoked when no FlutterRenderer was attached.");
            return;
        }
        if (this.f340a) {
            e();
        }
        this.f341b = false;
    }

    public final void e() {
        if (this.f342c == null || getSurfaceTexture() == null) {
            throw new IllegalStateException("connectSurfaceToRenderer() should only be called when flutterRenderer and getSurfaceTexture() are non-null.");
        }
        Surface surface = this.f343d;
        if (surface != null) {
            surface.release();
            this.f343d = null;
        }
        Surface surface2 = new Surface(getSurfaceTexture());
        this.f343d = surface2;
        io.flutter.embedding.engine.renderer.j jVar = this.f342c;
        boolean z2 = this.f341b;
        FlutterJNI flutterJNI = jVar.f793a;
        if (!z2) {
            jVar.h();
        }
        jVar.f795c = surface2;
        if (z2) {
            flutterJNI.onSurfaceWindowChanged(surface2);
        } else {
            flutterJNI.onSurfaceCreated(surface2);
        }
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public io.flutter.embedding.engine.renderer.j getAttachedRenderer() {
        return this.f342c;
    }

    @Override // android.view.View
    public final void onMeasure(int i2, int i3) {
        if (!this.f344e) {
            super.onMeasure(i2, i3);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        setMeasuredDimension(Math.max(View.MeasureSpec.getSize(i2), mode == 0 ? 1 : 0), Math.max(View.MeasureSpec.getSize(i3), View.MeasureSpec.getMode(i3) == 0 ? 1 : 0));
    }

    public void setRenderSurface(Surface surface) {
        this.f343d = surface;
    }
}
