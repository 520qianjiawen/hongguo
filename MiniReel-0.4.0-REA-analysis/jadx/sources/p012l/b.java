package p012l;

import android.view.DisplayCutout;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DisplayCutout f1078a;

    public b(DisplayCutout displayCutout) {
        this.f1078a = displayCutout;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f1078a, ((b) obj).f1078a);
    }

    public final int hashCode() {
        return this.f1078a.hashCode();
    }

    public final String toString() {
        return "DisplayCutoutCompat{" + this.f1078a + "}";
    }
}
