package f0;

import java.io.Serializable;
import p0.h;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Throwable f720d;

    public c(Throwable th) {
        h.e(th, "exception");
        this.f720d = th;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof c) {
            return h.a(this.f720d, ((c) obj).f720d);
        }
        return false;
    }

    public final int hashCode() {
        return this.f720d.hashCode();
    }

    public final String toString() {
        return "Failure(" + this.f720d + ')';
    }
}
