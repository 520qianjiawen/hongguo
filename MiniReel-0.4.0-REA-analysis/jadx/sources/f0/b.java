package f0;

import java.io.Serializable;
import p0.h;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f718d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Serializable f719e;

    public b(Serializable serializable, Serializable serializable2) {
        this.f718d = serializable;
        this.f719e = serializable2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return h.a(this.f718d, bVar.f718d) && this.f719e.equals(bVar.f719e);
    }

    public final int hashCode() {
        Object obj = this.f718d;
        return this.f719e.hashCode() + ((obj == null ? 0 : obj.hashCode()) * 31);
    }

    public final String toString() {
        return "(" + this.f718d + ", " + this.f719e + ')';
    }
}
