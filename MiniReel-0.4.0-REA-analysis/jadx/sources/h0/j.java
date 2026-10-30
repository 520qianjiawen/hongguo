package h0;

import java.io.Serializable;
import o0.p;

/* JADX INFO: loaded from: classes.dex */
public final class j implements i, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final j f746d = new j();

    @Override // h0.i
    public final i g(h hVar) {
        p0.h.e(hVar, "key");
        return this;
    }

    @Override // h0.i
    public final i h(i iVar) {
        p0.h.e(iVar, "context");
        return iVar;
    }

    public final int hashCode() {
        return 0;
    }

    @Override // h0.i
    public final g i(h hVar) {
        p0.h.e(hVar, "key");
        return null;
    }

    public final String toString() {
        return "EmptyCoroutineContext";
    }

    @Override // h0.i
    public final Object f(Object obj, p pVar) {
        return obj;
    }
}
