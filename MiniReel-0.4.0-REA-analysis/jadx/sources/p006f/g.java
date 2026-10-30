package p006f;

import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import p000a.a;

/* JADX INFO: loaded from: classes.dex */
public abstract class g implements Future {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final boolean f711g = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Logger f712h = Logger.getLogger(g.class.getName());

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final a f713i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Object f714j;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile Object f715d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile c f716e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile f f717f;

    static {
        a eVar;
        try {
            eVar = new d(AtomicReferenceFieldUpdater.newUpdater(f.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(f.class, f.class, "b"), AtomicReferenceFieldUpdater.newUpdater(g.class, f.class, "f"), AtomicReferenceFieldUpdater.newUpdater(g.class, c.class, "e"), AtomicReferenceFieldUpdater.newUpdater(g.class, Object.class, "d"));
            th = null;
        } catch (Throwable th) {
            th = th;
            eVar = new e(14);
        }
        f713i = eVar;
        if (th != null) {
            f712h.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        f714j = new Object();
    }

    public static void b(g gVar) {
        f fVar;
        c cVar;
        do {
            fVar = gVar.f717f;
        } while (!f713i.h(gVar, fVar, f.f708c));
        while (fVar != null) {
            Thread thread = fVar.f709a;
            if (thread != null) {
                fVar.f709a = null;
                LockSupport.unpark(thread);
            }
            fVar = fVar.f710b;
        }
        do {
            cVar = gVar.f716e;
        } while (!f713i.f(gVar, cVar));
        c cVar2 = null;
        while (cVar != null) {
            c cVar3 = cVar.f703a;
            cVar.f703a = cVar2;
            cVar2 = cVar;
            cVar = cVar3;
        }
        while (cVar2 != null) {
            cVar2 = cVar2.f703a;
            try {
                throw null;
            } catch (RuntimeException e2) {
                f712h.log(Level.SEVERE, "RuntimeException while executing runnable null with executor null", (Throwable) e2);
            }
        }
    }

    public static Object c(Object obj) throws ExecutionException {
        if (obj instanceof a) {
            Throwable th = ((a) obj).f701a;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof b) {
            ((b) obj).getClass();
            throw new ExecutionException((Throwable) null);
        }
        if (obj == f714j) {
            return null;
        }
        return obj;
    }

    public static Object d(g gVar) {
        Object obj;
        boolean z2 = false;
        while (true) {
            try {
                obj = gVar.get();
                break;
            } catch (InterruptedException unused) {
                z2 = true;
            } catch (Throwable th) {
                if (z2) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z2) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    public final void a(StringBuilder sb) {
        try {
            Object objD = d(this);
            sb.append("SUCCESS, result=[");
            sb.append(objD == this ? "this future" : String.valueOf(objD));
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e2) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e2.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e3) {
            sb.append("FAILURE, cause=[");
            sb.append(e3.getCause());
            sb.append("]");
        }
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z2) {
        a aVar;
        Object obj = this.f715d;
        if (obj != null) {
            return false;
        }
        if (f711g) {
            aVar = new a(new CancellationException("Future.cancel() was called."), z2);
        } else {
            aVar = z2 ? a.f699b : a.f700c;
        }
        if (!f713i.g(this, obj, aVar)) {
            return false;
        }
        b(this);
        return true;
    }

    public final void e(f fVar) {
        fVar.f709a = null;
        while (true) {
            f fVar2 = this.f717f;
            if (fVar2 == f.f708c) {
                return;
            }
            f fVar3 = null;
            while (fVar2 != null) {
                f fVar4 = fVar2.f710b;
                if (fVar2.f709a != null) {
                    fVar3 = fVar2;
                } else if (fVar3 != null) {
                    fVar3.f710b = fVar4;
                    if (fVar3.f709a == null) {
                    }
                } else if (!f713i.h(this, fVar2, fVar4)) {
                }
                fVar2 = fVar4;
            }
            return;
        }
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j2, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        f fVar = f.f708c;
        long nanos = timeUnit.toNanos(j2);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.f715d;
        if (obj != null) {
            return c(obj);
        }
        long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= 1000) {
            f fVar2 = this.f717f;
            if (fVar2 != fVar) {
                f fVar3 = new f();
                while (true) {
                    a aVar = f713i;
                    aVar.G(fVar3, fVar2);
                    if (aVar.h(this, fVar2, fVar3)) {
                        do {
                            LockSupport.parkNanos(this, nanos);
                            if (Thread.interrupted()) {
                                e(fVar3);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.f715d;
                            if (obj2 != null) {
                                return c(obj2);
                            }
                            nanos = jNanoTime - System.nanoTime();
                        } while (nanos >= 1000);
                        e(fVar3);
                        break;
                    }
                    fVar2 = this.f717f;
                    if (fVar2 == fVar) {
                    }
                }
            }
            return c(this.f715d);
        }
        while (nanos > 0) {
            Object obj3 = this.f715d;
            if (obj3 != null) {
                return c(obj3);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = jNanoTime - System.nanoTime();
        }
        String string = toString();
        String string2 = timeUnit.toString();
        Locale locale = Locale.ROOT;
        String lowerCase = string2.toLowerCase(locale);
        String str = "Waited " + j2 + " " + timeUnit.toString().toLowerCase(locale);
        if (nanos + 1000 < 0) {
            String str2 = str + " (plus ";
            long j3 = -nanos;
            long jConvert = timeUnit.convert(j3, TimeUnit.NANOSECONDS);
            long nanos2 = j3 - timeUnit.toNanos(jConvert);
            boolean z2 = jConvert == 0 || nanos2 > 1000;
            if (jConvert > 0) {
                String str3 = str2 + jConvert + " " + lowerCase;
                if (z2) {
                    str3 = str3 + ",";
                }
                str2 = str3 + " ";
            }
            if (z2) {
                str2 = str2 + nanos2 + " nanoseconds ";
            }
            str = str2 + "delay)";
        }
        if (isDone()) {
            throw new TimeoutException(str + " but future completed as timeout expired");
        }
        throw new TimeoutException(str + " for " + string);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f715d instanceof a;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.f715d != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (this.f715d instanceof a) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            a(sb);
        } else {
            try {
                if (this instanceof ScheduledFuture) {
                    str = "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
                } else {
                    str = null;
                }
            } catch (RuntimeException e2) {
                str = "Exception thrown from implementation: " + e2.getClass();
            }
            if (str != null && !str.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(str);
                sb.append("]");
            } else if (isDone()) {
                a(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws InterruptedException {
        Object obj;
        f fVar = f.f708c;
        if (!Thread.interrupted()) {
            Object obj2 = this.f715d;
            if (obj2 != null) {
                return c(obj2);
            }
            f fVar2 = this.f717f;
            if (fVar2 != fVar) {
                f fVar3 = new f();
                do {
                    a aVar = f713i;
                    aVar.G(fVar3, fVar2);
                    if (aVar.h(this, fVar2, fVar3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.f715d;
                            } else {
                                e(fVar3);
                                throw new InterruptedException();
                            }
                        } while (obj == null);
                        return c(obj);
                    }
                    fVar2 = this.f717f;
                } while (fVar2 != fVar);
            }
            return c(this.f715d);
        }
        throw new InterruptedException();
    }
}
