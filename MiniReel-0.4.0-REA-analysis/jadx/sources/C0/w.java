package C0;

/* JADX INFO: loaded from: classes.dex */
public abstract class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f88a = 0;

    static {
        Object objK;
        Object objK2;
        Exception exc = new Exception();
        String simpleName = p000a.a.class.getSimpleName();
        StackTraceElement stackTraceElement = exc.getStackTrace()[0];
        new StackTraceElement("_COROUTINE.".concat(simpleName), "_", stackTraceElement.getFileName(), stackTraceElement.getLineNumber());
        try {
            objK = j0.b.class.getCanonicalName();
        } catch (Throwable th) {
            objK = p000a.a.k(th);
        }
        if (f0.d.a(objK) != null) {
            objK = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
        }
        try {
            objK2 = w.class.getCanonicalName();
        } catch (Throwable th2) {
            objK2 = p000a.a.k(th2);
        }
        if (f0.d.a(objK2) != null) {
            objK2 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
        }
    }
}
