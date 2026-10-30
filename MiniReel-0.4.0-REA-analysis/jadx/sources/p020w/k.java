package p020w;

import g0.e;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1301a;

    public k(List list) {
        this.f1301a = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !k.class.equals(obj.getClass())) {
            return false;
        }
        return this.f1301a.equals(((k) obj).f1301a);
    }

    public final int hashCode() {
        return this.f1301a.hashCode();
    }

    public final String toString() {
        return e.U((Collection) this.f1301a, "WindowLayoutInfo{ DisplayFeatures[", "] }", null, 56);
    }
}
