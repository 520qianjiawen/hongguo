package x0;

import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class K extends CancellationException {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final transient S f1317d;

    public K(String str, Throwable th, S s2) {
        super(str);
        this.f1317d = s2;
        if (th != null) {
            initCause(th);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof K)) {
            return false;
        }
        K k2 = (K) obj;
        return p0.h.a(k2.getMessage(), getMessage()) && p0.h.a(k2.f1317d, this.f1317d) && p0.h.a(k2.getCause(), getCause());
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public final int hashCode() {
        String message = getMessage();
        p0.h.b(message);
        int iHashCode = (this.f1317d.hashCode() + (message.hashCode() * 31)) * 31;
        Throwable cause = getCause();
        return iHashCode + (cause != null ? cause.hashCode() : 0);
    }

    @Override // java.lang.Throwable
    public final String toString() {
        return super.toString() + "; job=" + this.f1317d;
    }
}
