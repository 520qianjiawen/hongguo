package O;

import android.view.Surface;
import android.view.SurfaceControl;
import android.view.WindowInsets;

/* JADX INFO: renamed from: O.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC0012j {
    public static /* synthetic */ Surface f(SurfaceControl surfaceControl) {
        return new Surface(surfaceControl);
    }

    public static /* synthetic */ SurfaceControl.Builder g() {
        return new SurfaceControl.Builder();
    }

    public static /* synthetic */ SurfaceControl.Transaction h() {
        return new SurfaceControl.Transaction();
    }

    public static /* bridge */ /* synthetic */ SurfaceControl.Transaction j(Object obj) {
        return (SurfaceControl.Transaction) obj;
    }

    public static /* synthetic */ WindowInsets.Builder l() {
        return new WindowInsets.Builder();
    }

    public static /* synthetic */ WindowInsets.Builder m(WindowInsets windowInsets) {
        return new WindowInsets.Builder(windowInsets);
    }

    public static /* synthetic */ void o() {
    }
}
