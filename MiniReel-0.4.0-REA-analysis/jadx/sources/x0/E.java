package x0;

import C0.AbstractC0002c;
import java.lang.reflect.Method;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/* JADX INFO: loaded from: classes.dex */
public final class E extends D implements InterfaceC0037v {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Executor f1312f;

    public E(Executor executor) {
        Method method;
        this.f1312f = executor;
        Method method2 = AbstractC0002c.f53a;
        try {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = executor instanceof ScheduledThreadPoolExecutor ? (ScheduledThreadPoolExecutor) executor : null;
            if (scheduledThreadPoolExecutor != null && (method = AbstractC0002c.f53a) != null) {
                method.invoke(scheduledThreadPoolExecutor, Boolean.TRUE);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // x0.AbstractC0032p
    public final void c(h0.i iVar, Runnable runnable) {
        try {
            this.f1312f.execute(runnable);
        } catch (RejectedExecutionException e2) {
            CancellationException cancellationException = new CancellationException("The task was rejected");
            cancellationException.initCause(e2);
            J j2 = (J) iVar.i(C0033q.f1363e);
            if (j2 != null) {
                j2.a(cancellationException);
            }
            AbstractC0039x.f1377b.c(iVar, runnable);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Executor executor = this.f1312f;
        ExecutorService executorService = executor instanceof ExecutorService ? (ExecutorService) executor : null;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof E) && ((E) obj).f1312f == this.f1312f;
    }

    public final int hashCode() {
        return System.identityHashCode(this.f1312f);
    }

    @Override // x0.AbstractC0032p
    public final String toString() {
        return this.f1312f.toString();
    }
}
