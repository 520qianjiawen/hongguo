package O;

import android.graphics.Region;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import io.flutter.embedding.engine.FlutterJNI;

/* JADX INFO: loaded from: classes.dex */
public final class m extends SurfaceView implements io.flutter.embedding.engine.renderer.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f334a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f335b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public io.flutter.embedding.engine.renderer.j f336c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f337d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final L f338e;

    public m(AbstractActivityC0007e abstractActivityC0007e, boolean z2) {
        super(abstractActivityC0007e, null);
        this.f334a = false;
        this.f335b = false;
        this.f337d = false;
        L l2 = new L(new SurfaceHolderCallbackC0014l(this), this, this.f336c);
        this.f338e = l2;
        if (z2) {
            getHolder().setFormat(-2);
            setZOrderOnTop(true);
        }
        this.f337d = p000a.a.z(getContext());
        getHolder().addCallback(l2);
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public final void a(io.flutter.embedding.engine.renderer.j jVar) {
        io.flutter.embedding.engine.renderer.j jVar2 = this.f336c;
        if (jVar2 != null) {
            jVar2.h();
        }
        this.f336c = jVar;
        K k2 = this.f338e.f296e;
        switch (k2.f290a) {
            case 0:
                k2.f291b.f293b = jVar;
                break;
            default:
                L l2 = k2.f291b;
                io.flutter.embedding.engine.renderer.j jVar3 = l2.f293b;
                if (jVar3 != null) {
                    jVar3.e(l2.f295d);
                }
                l2.f293b = jVar;
                break;
        }
        d();
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public final void b() {
        if (this.f336c == null) {
            Log.w("FlutterSurfaceView", "pause() invoked when no FlutterRenderer was attached.");
        } else {
            this.f335b = true;
        }
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public final void c() {
        if (this.f336c == null) {
            Log.w("FlutterSurfaceView", "detachFromRenderer() invoked when no FlutterRenderer was attached.");
            return;
        }
        if (getWindowToken() != null) {
            io.flutter.embedding.engine.renderer.j jVar = this.f336c;
            if (jVar == null) {
                throw new IllegalStateException("disconnectSurfaceFromRenderer() should only be called when flutterRenderer is non-null.");
            }
            jVar.h();
        }
        K k2 = this.f338e.f296e;
        switch (k2.f290a) {
            case 0:
                k2.f291b.f293b = null;
                break;
            default:
                L l2 = k2.f291b;
                l2.f292a.setAlpha(0.0f);
                io.flutter.embedding.engine.renderer.j jVar2 = l2.f293b;
                if (jVar2 != null) {
                    jVar2.e(l2.f295d);
                }
                l2.f293b = null;
                break;
        }
        this.f336c = null;
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public final void d() {
        if (this.f336c == null) {
            Log.w("FlutterSurfaceView", "resume() invoked when no FlutterRenderer was attached.");
            return;
        }
        K k2 = this.f338e.f296e;
        switch (k2.f290a) {
            case 0:
                break;
            default:
                L l2 = k2.f291b;
                io.flutter.embedding.engine.renderer.j jVar = l2.f293b;
                if (jVar != null) {
                    jVar.a(l2.f295d);
                }
                break;
        }
        if (this.f334a) {
            e();
        }
        this.f335b = false;
    }

    public final void e() {
        if (this.f336c == null || getHolder() == null) {
            throw new IllegalStateException("connectSurfaceToRenderer() should only be called when flutterRenderer and getHolder() are non-null.");
        }
        io.flutter.embedding.engine.renderer.j jVar = this.f336c;
        Surface surface = getHolder().getSurface();
        boolean z2 = this.f335b;
        FlutterJNI flutterJNI = jVar.f793a;
        if (!z2) {
            jVar.h();
        }
        jVar.f795c = surface;
        if (z2) {
            flutterJNI.onSurfaceWindowChanged(surface);
        } else {
            flutterJNI.onSurfaceCreated(surface);
        }
    }

    @Override // android.view.SurfaceView, android.view.View
    public final boolean gatherTransparentRegion(Region region) {
        if (getAlpha() < 1.0f) {
            return false;
        }
        int[] iArr = new int[2];
        getLocationInWindow(iArr);
        int i2 = iArr[0];
        region.op(i2, iArr[1], (getRight() + i2) - getLeft(), (getBottom() + iArr[1]) - getTop(), Region.Op.DIFFERENCE);
        return true;
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public io.flutter.embedding.engine.renderer.j getAttachedRenderer() {
        return this.f336c;
    }

    @Override // android.view.SurfaceView, android.view.View
    public final void onMeasure(int i2, int i3) {
        if (!this.f337d) {
            super.onMeasure(i2, i3);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        setMeasuredDimension(Math.max(View.MeasureSpec.getSize(i2), mode == 0 ? 1 : 0), Math.max(View.MeasureSpec.getSize(i3), View.MeasureSpec.getMode(i3) == 0 ? 1 : 0));
    }
}
