package O;

import android.R;
import android.content.Context;
import android.graphics.Matrix;
import android.util.TypedValue;
import java.util.HashMap;

/* JADX INFO: renamed from: O.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0004b {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Matrix f297f = new Matrix();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final io.flutter.embedding.engine.renderer.j f298a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final H.a f299b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f300c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashMap f301d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f302e;

    public C0004b(io.flutter.embedding.engine.renderer.j jVar, boolean z2) {
        this.f298a = jVar;
        if (H.a.f167g == null) {
            H.a.f167g = new H.a(3);
        }
        this.f299b = H.a.f167g;
        this.f300c = z2;
    }

    public static int b(int i2) {
        if (i2 == 0) {
            return 4;
        }
        if (i2 != 1) {
            if (i2 == 5) {
                return 4;
            }
            if (i2 != 6) {
                if (i2 == 2) {
                    return 5;
                }
                if (i2 != 7) {
                    if (i2 == 3) {
                        return 0;
                    }
                    if (i2 != 8) {
                        return -1;
                    }
                }
                return 3;
            }
        }
        return 6;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00a6, code lost:
    
        if (r11 == (-1)) goto L4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(android.view.MotionEvent r29, int r30, int r31, int r32, android.graphics.Matrix r33, java.nio.ByteBuffer r34, android.content.Context r35) {
        /*
            Method dump skipped, instruction units count: 641
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: O.C0004b.a(android.view.MotionEvent, int, int, int, android.graphics.Matrix, java.nio.ByteBuffer, android.content.Context):void");
    }

    public final int c(Context context) {
        if (this.f302e == 0) {
            TypedValue typedValue = new TypedValue();
            if (!context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                return 48;
            }
            this.f302e = (int) typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return this.f302e;
    }
}
