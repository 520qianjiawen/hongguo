package x0;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: renamed from: x0.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0035t extends B implements Runnable {
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final RunnableC0035t f1373l;
    public static final long m;

    static {
        Long l2;
        RunnableC0035t runnableC0035t = new RunnableC0035t();
        f1373l = runnableC0035t;
        runnableC0035t.k(false);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l2 = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l2 = 1000L;
        }
        m = timeUnit.toNanos(l2.longValue());
    }

    @Override // x0.C
    public final Thread j() {
        Thread thread;
        Thread thread2 = _thread;
        if (thread2 != null) {
            return thread2;
        }
        synchronized (this) {
            thread = _thread;
            if (thread == null) {
                thread = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
                _thread = thread;
                thread.setDaemon(true);
                thread.start();
            }
        }
        return thread;
    }

    @Override // x0.B
    public final void m(Runnable runnable) {
        if (debugStatus == 4) {
            throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
        }
        super.m(runnable);
    }

    public final synchronized void p() {
        int i2 = debugStatus;
        if (i2 == 2 || i2 == 3) {
            debugStatus = 3;
            B.f1306i.set(this, null);
            B.f1307j.set(this, null);
            notifyAll();
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Z.f1335a.set(this);
        try {
            synchronized (this) {
                int i2 = debugStatus;
                if (i2 == 2 || i2 == 3) {
                    _thread = null;
                    p();
                    if (n()) {
                        return;
                    }
                    j();
                    return;
                }
                debugStatus = 1;
                notifyAll();
                long j2 = Long.MAX_VALUE;
                while (true) {
                    Thread.interrupted();
                    long jO = o();
                    if (jO == Long.MAX_VALUE) {
                        long jNanoTime = System.nanoTime();
                        if (j2 == Long.MAX_VALUE) {
                            j2 = m + jNanoTime;
                        }
                        long j3 = j2 - jNanoTime;
                        if (j3 <= 0) {
                            _thread = null;
                            p();
                            if (n()) {
                                return;
                            }
                            j();
                            return;
                        }
                        if (jO > j3) {
                            jO = j3;
                        }
                    } else {
                        j2 = Long.MAX_VALUE;
                    }
                    if (jO > 0) {
                        int i3 = debugStatus;
                        if (i3 == 2 || i3 == 3) {
                            _thread = null;
                            p();
                            if (n()) {
                                return;
                            }
                            j();
                            return;
                        }
                        LockSupport.parkNanos(this, jO);
                    }
                }
            }
        } catch (Throwable th) {
            _thread = null;
            p();
            if (!n()) {
                j();
            }
            throw th;
        }
    }

    @Override // x0.B, x0.C
    public final void shutdown() {
        debugStatus = 4;
        super.shutdown();
    }
}
