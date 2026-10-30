package D0;

import C0.t;
import C0.x;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.LockSupport;
import x0.AbstractC0034s;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Executor, Closeable {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f116k = AtomicLongFieldUpdater.newUpdater(b.class, "parkedWorkersStack");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f117l = AtomicLongFieldUpdater.newUpdater(b.class, "controlState");
    public static final AtomicIntegerFieldUpdater m = AtomicIntegerFieldUpdater.newUpdater(b.class, "_isTerminated");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final x f118n = new x(0, "NOT_IN_STACK");
    private volatile int _isTerminated;
    private volatile long controlState;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f119d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f120e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f121f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f122g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final e f123h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final e f124i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final t f125j;
    private volatile long parkedWorkersStack;

    public b(int i2, int i3, long j2, String str) {
        this.f119d = i2;
        this.f120e = i3;
        this.f121f = j2;
        this.f122g = str;
        if (i2 < 1) {
            throw new IllegalArgumentException(("Core pool size " + i2 + " should be at least 1").toString());
        }
        if (i3 < i2) {
            throw new IllegalArgumentException(("Max pool size " + i3 + " should be greater than or equals to core pool size " + i2).toString());
        }
        if (i3 > 2097150) {
            throw new IllegalArgumentException(("Max pool size " + i3 + " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (j2 <= 0) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + j2 + " must be positive").toString());
        }
        this.f123h = new e();
        this.f124i = new e();
        this.f125j = new t((i2 + 1) * 2);
        this.controlState = ((long) i2) << 42;
        this._isTerminated = 0;
    }

    public final int a() {
        synchronized (this.f125j) {
            try {
                if (m.get(this) != 0) {
                    return -1;
                }
                AtomicLongFieldUpdater atomicLongFieldUpdater = f117l;
                long j2 = atomicLongFieldUpdater.get(this);
                int i2 = (int) (j2 & 2097151);
                int i3 = i2 - ((int) ((j2 & 4398044413952L) >> 21));
                if (i3 < 0) {
                    i3 = 0;
                }
                if (i3 >= this.f119d) {
                    return 0;
                }
                if (i2 >= this.f120e) {
                    return 0;
                }
                int i4 = ((int) (atomicLongFieldUpdater.get(this) & 2097151)) + 1;
                if (i4 <= 0 || this.f125j.b(i4) != null) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                a aVar = new a(this, i4);
                this.f125j.c(i4, aVar);
                if (i4 != ((int) (2097151 & atomicLongFieldUpdater.incrementAndGet(this)))) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                int i5 = i3 + 1;
                aVar.start();
                return i5;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b(Runnable runnable, i iVar) {
        h jVar;
        int i2;
        k.f140f.getClass();
        long jNanoTime = System.nanoTime();
        if (runnable instanceof h) {
            jVar = (h) runnable;
            jVar.f131d = jNanoTime;
            jVar.f132e = iVar;
        } else {
            jVar = new j(runnable, jNanoTime, iVar);
        }
        boolean z2 = jVar.f132e.f133a == 1;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f117l;
        long jAddAndGet = z2 ? atomicLongFieldUpdater.addAndGet(this, 2097152L) : 0L;
        Thread threadCurrentThread = Thread.currentThread();
        a aVar = threadCurrentThread instanceof a ? (a) threadCurrentThread : null;
        if (aVar == null || !p0.h.a(aVar.f115k, this)) {
            aVar = null;
        }
        if (aVar != null && (i2 = aVar.f110f) != 5 && (jVar.f132e.f133a != 0 || i2 != 2)) {
            aVar.f114j = true;
            m mVar = aVar.f108d;
            mVar.getClass();
            jVar = (h) m.f144b.getAndSet(mVar, jVar);
            if (jVar == null) {
                jVar = null;
            } else {
                AtomicReferenceArray atomicReferenceArray = mVar.f148a;
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = m.f145c;
                if (atomicIntegerFieldUpdater.get(mVar) - m.f146d.get(mVar) != 127) {
                    if (jVar.f132e.f133a == 1) {
                        m.f147e.incrementAndGet(mVar);
                    }
                    int i3 = atomicIntegerFieldUpdater.get(mVar) & 127;
                    while (atomicReferenceArray.get(i3) != null) {
                        Thread.yield();
                    }
                    atomicReferenceArray.lazySet(i3, jVar);
                    atomicIntegerFieldUpdater.incrementAndGet(mVar);
                    jVar = null;
                }
            }
        }
        if (jVar != null) {
            if (!(jVar.f132e.f133a == 1 ? this.f124i.a(jVar) : this.f123h.a(jVar))) {
                throw new RejectedExecutionException(this.f122g + " was terminated");
            }
        }
        if (z2) {
            if (e() || d(jAddAndGet)) {
                return;
            }
            e();
            return;
        }
        if (e() || d(atomicLongFieldUpdater.get(this))) {
            return;
        }
        e();
    }

    public final void c(a aVar, int i2, int i3) {
        while (true) {
            long j2 = f116k.get(this);
            int i4 = (int) (2097151 & j2);
            long j3 = (2097152 + j2) & (-2097152);
            if (i4 == i2) {
                if (i3 == 0) {
                    Object objC = aVar.c();
                    while (true) {
                        if (objC == f118n) {
                            i4 = -1;
                            break;
                        }
                        if (objC == null) {
                            i4 = 0;
                            break;
                        }
                        a aVar2 = (a) objC;
                        int iB = aVar2.b();
                        if (iB != 0) {
                            i4 = iB;
                            break;
                        }
                        objC = aVar2.c();
                    }
                } else {
                    i4 = i3;
                }
            }
            if (i4 >= 0) {
                if (f116k.compareAndSet(this, j2, ((long) i4) | j3)) {
                    return;
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:39:0x0088  */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws InterruptedException {
        int i2;
        h hVarA;
        if (m.compareAndSet(this, 0, 1)) {
            Thread threadCurrentThread = Thread.currentThread();
            a aVar = threadCurrentThread instanceof a ? (a) threadCurrentThread : null;
            if (aVar == null || !p0.h.a(aVar.f115k, this)) {
                aVar = null;
            }
            synchronized (this.f125j) {
                i2 = (int) (f117l.get(this) & 2097151);
            }
            if (1 <= i2) {
                int i3 = 1;
                while (true) {
                    Object objB = this.f125j.b(i3);
                    p0.h.b(objB);
                    a aVar2 = (a) objB;
                    if (aVar2 != aVar) {
                        while (aVar2.isAlive()) {
                            LockSupport.unpark(aVar2);
                            aVar2.join(10000L);
                        }
                        m mVar = aVar2.f108d;
                        e eVar = this.f124i;
                        mVar.getClass();
                        h hVar = (h) m.f144b.getAndSet(mVar, null);
                        if (hVar != null) {
                            eVar.a(hVar);
                        }
                        while (true) {
                            h hVarA2 = mVar.a();
                            if (hVarA2 == null) {
                                break;
                            } else {
                                eVar.a(hVarA2);
                            }
                        }
                    }
                    if (i3 == i2) {
                        break;
                    } else {
                        i3++;
                    }
                }
            }
            this.f124i.b();
            this.f123h.b();
            while (true) {
                if (aVar != null) {
                    hVarA = aVar.a(true);
                    if (hVarA == null) {
                        hVarA = (h) this.f123h.d();
                        if (hVarA == null) {
                            break;
                            break;
                        }
                    }
                } else {
                    hVarA = (h) this.f123h.d();
                    if (hVarA == null && (hVarA = (h) this.f124i.d()) == null) {
                        break;
                    }
                }
                try {
                    hVarA.run();
                } catch (Throwable th) {
                    Thread threadCurrentThread2 = Thread.currentThread();
                    threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th);
                }
            }
            if (aVar != null) {
                aVar.h(5);
            }
            f116k.set(this, 0L);
            f117l.set(this, 0L);
        }
    }

    public final boolean d(long j2) {
        int i2 = ((int) (2097151 & j2)) - ((int) ((j2 & 4398044413952L) >> 21));
        if (i2 < 0) {
            i2 = 0;
        }
        int i3 = this.f119d;
        if (i2 < i3) {
            int iA = a();
            if (iA == 1 && i3 > 1) {
                a();
            }
            if (iA > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean e() {
        x xVar;
        int iB;
        while (true) {
            long j2 = f116k.get(this);
            a aVar = (a) this.f125j.b((int) (2097151 & j2));
            if (aVar == null) {
                aVar = null;
            } else {
                long j3 = (2097152 + j2) & (-2097152);
                Object objC = aVar.c();
                while (true) {
                    xVar = f118n;
                    if (objC == xVar) {
                        iB = -1;
                        break;
                    }
                    if (objC == null) {
                        iB = 0;
                        break;
                    }
                    a aVar2 = (a) objC;
                    iB = aVar2.b();
                    if (iB != 0) {
                        break;
                    }
                    objC = aVar2.c();
                }
                if (iB >= 0) {
                    if (f116k.compareAndSet(this, j2, ((long) iB) | j3)) {
                        aVar.g(xVar);
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            if (aVar == null) {
                return false;
            }
            if (a.f107l.compareAndSet(aVar, -1, 0)) {
                LockSupport.unpark(aVar);
                return true;
            }
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        b(runnable, k.f141g);
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        t tVar = this.f125j;
        int iA = tVar.a();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 1; i7 < iA; i7++) {
            a aVar = (a) tVar.b(i7);
            if (aVar != null) {
                m mVar = aVar.f108d;
                mVar.getClass();
                int i8 = m.f144b.get(mVar) != null ? (m.f145c.get(mVar) - m.f146d.get(mVar)) + 1 : m.f145c.get(mVar) - m.f146d.get(mVar);
                int iA2 = p014o.c.a(aVar.f110f);
                if (iA2 == 0) {
                    i2++;
                    StringBuilder sb = new StringBuilder();
                    sb.append(i8);
                    sb.append('c');
                    arrayList.add(sb.toString());
                } else if (iA2 == 1) {
                    i3++;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(i8);
                    sb2.append('b');
                    arrayList.add(sb2.toString());
                } else if (iA2 == 2) {
                    i4++;
                } else if (iA2 == 3) {
                    i5++;
                    if (i8 > 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(i8);
                        sb3.append('d');
                        arrayList.add(sb3.toString());
                    }
                } else if (iA2 == 4) {
                    i6++;
                }
            }
        }
        long j2 = f117l.get(this);
        StringBuilder sb4 = new StringBuilder();
        sb4.append(this.f122g);
        sb4.append('@');
        sb4.append(AbstractC0034s.a(this));
        sb4.append("[Pool Size {core = ");
        int i9 = this.f119d;
        sb4.append(i9);
        sb4.append(", max = ");
        sb4.append(this.f120e);
        sb4.append("}, Worker States {CPU = ");
        sb4.append(i2);
        sb4.append(", blocking = ");
        sb4.append(i3);
        sb4.append(", parked = ");
        sb4.append(i4);
        sb4.append(", dormant = ");
        sb4.append(i5);
        sb4.append(", terminated = ");
        sb4.append(i6);
        sb4.append("}, running workers queues = ");
        sb4.append(arrayList);
        sb4.append(", global CPU queue size = ");
        sb4.append(this.f123h.c());
        sb4.append(", global blocking queue size = ");
        sb4.append(this.f124i.c());
        sb4.append(", Control State {created workers= ");
        sb4.append((int) (2097151 & j2));
        sb4.append(", blocking tasks = ");
        sb4.append((int) ((4398044413952L & j2) >> 21));
        sb4.append(", CPUs acquired = ");
        sb4.append(i9 - ((int) ((j2 & 9223367638808264704L) >> 42)));
        sb4.append("}]");
        return sb4.toString();
    }
}
