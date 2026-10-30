package p012l;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p f1102a;

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            int i2 = o.f1099q;
        } else {
            int i3 = p.f1100b;
        }
    }

    public r(WindowInsets windowInsets) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            this.f1102a = new o(this, windowInsets);
            return;
        }
        if (i2 >= 29) {
            this.f1102a = new n(this, windowInsets);
        } else if (i2 >= 28) {
            this.f1102a = new l(this, windowInsets);
        } else {
            this.f1102a = new k(this, windowInsets);
        }
    }

    public static r a(View view, WindowInsets windowInsets) {
        windowInsets.getClass();
        r rVar = new r(windowInsets);
        if (view != null && view.isAttachedToWindow()) {
            int i2 = d.f1079a;
            r rVarA = c.a(view);
            p pVar = rVar.f1102a;
            pVar.o(rVarA);
            pVar.d(view.getRootView());
        }
        return rVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof r) {
            return Objects.equals(this.f1102a, ((r) obj).f1102a);
        }
        return false;
    }

    public final int hashCode() {
        p pVar = this.f1102a;
        if (pVar == null) {
            return 0;
        }
        return pVar.hashCode();
    }

    public r() {
        this.f1102a = new p(this);
    }
}
