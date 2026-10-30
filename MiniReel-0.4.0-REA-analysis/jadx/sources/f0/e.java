package f0;

import java.io.Serializable;
import p0.h;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public o0.a f721d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Object f722e = f.f724a;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f723f = this;

    public e(o0.a aVar) {
        this.f721d = aVar;
    }

    public final Object a() {
        Object objA;
        Object obj = this.f722e;
        f fVar = f.f724a;
        if (obj != fVar) {
            return obj;
        }
        synchronized (this.f723f) {
            objA = this.f722e;
            if (objA == fVar) {
                o0.a aVar = this.f721d;
                h.b(aVar);
                objA = aVar.a();
                this.f722e = objA;
                this.f721d = null;
            }
        }
        return objA;
    }

    public final String toString() {
        return this.f722e != f.f724a ? String.valueOf(a()) : "Lazy value not initialized yet.";
    }
}
