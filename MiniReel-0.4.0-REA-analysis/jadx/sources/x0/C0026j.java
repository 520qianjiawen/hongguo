package x0;

import java.util.concurrent.CancellationException;

/* JADX INFO: renamed from: x0.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0026j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1349a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o0.l f1350b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f1351c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Throwable f1352d;

    public C0026j(Object obj, o0.l lVar, Object obj2, Throwable th) {
        this.f1349a = obj;
        this.f1350b = lVar;
        this.f1351c = obj2;
        this.f1352d = th;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0026j)) {
            return false;
        }
        C0026j c0026j = (C0026j) obj;
        return p0.h.a(this.f1349a, c0026j.f1349a) && p0.h.a(this.f1350b, c0026j.f1350b) && p0.h.a(this.f1351c, c0026j.f1351c) && p0.h.a(this.f1352d, c0026j.f1352d);
    }

    public final int hashCode() {
        Object obj = this.f1349a;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 961;
        o0.l lVar = this.f1350b;
        int iHashCode2 = (iHashCode + (lVar == null ? 0 : lVar.hashCode())) * 31;
        Object obj2 = this.f1351c;
        int iHashCode3 = (iHashCode2 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.f1352d;
        return iHashCode3 + (th != null ? th.hashCode() : 0);
    }

    public final String toString() {
        return "CompletedContinuation(result=" + this.f1349a + ", cancelHandler=null, onCancellation=" + this.f1350b + ", idempotentResume=" + this.f1351c + ", cancelCause=" + this.f1352d + ')';
    }

    public /* synthetic */ C0026j(Object obj, o0.l lVar, CancellationException cancellationException, int i2) {
        this(obj, (i2 & 4) != 0 ? null : lVar, (Object) null, (i2 & 16) != 0 ? null : cancellationException);
    }
}
