package C0;

/* JADX INFO: loaded from: classes.dex */
public final class g extends RuntimeException {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final transient h0.i f58d;

    public g(h0.i iVar) {
        this.f58d = iVar;
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    @Override // java.lang.Throwable
    public final String getLocalizedMessage() {
        return this.f58d.toString();
    }
}
