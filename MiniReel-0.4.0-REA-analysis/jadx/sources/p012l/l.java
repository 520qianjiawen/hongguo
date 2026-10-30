package p012l;

import android.view.DisplayCutout;
import android.view.WindowInsets;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class l extends k {
    public l(r rVar, WindowInsets windowInsets) {
        super(rVar, windowInsets);
    }

    @Override // p012l.p
    public r a() {
        return r.a(null, this.f1091c.consumeDisplayCutout());
    }

    @Override // p012l.p
    public b e() {
        DisplayCutout displayCutout = this.f1091c.getDisplayCutout();
        if (displayCutout == null) {
            return null;
        }
        return new b(displayCutout);
    }

    @Override // p012l.j, p012l.p
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return Objects.equals(this.f1091c, lVar.f1091c) && Objects.equals(this.f1095g, lVar.f1095g);
    }

    @Override // p012l.p
    public int hashCode() {
        return this.f1091c.hashCode();
    }
}
