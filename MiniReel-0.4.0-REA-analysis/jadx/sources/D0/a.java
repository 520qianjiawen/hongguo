package D0;

import C0.x;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes.dex */
public final class a extends Thread {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f107l = AtomicIntegerFieldUpdater.newUpdater(a.class, "workerCtl");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m f108d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final p0.m f109e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f110f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f111g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f112h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f113i;
    private volatile int indexInArray;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f114j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ b f115k;
    private volatile Object nextParkedWorker;
    private volatile int workerCtl;

    public a(b bVar, int i2) {
        this.f115k = bVar;
        setDaemon(true);
        this.f108d = new m();
        this.f109e = new p0.m();
        this.f110f = 4;
        this.nextParkedWorker = b.f118n;
        r0.a aVar = r0.e.f1233d;
        this.f113i = r0.e.f1233d.a().nextInt();
        f(i2);
    }

    public final h a(boolean z2) {
        h hVarE;
        h hVarE2;
        long j2;
        int i2 = this.f110f;
        b bVar = this.f115k;
        h hVar = null;
        m mVar = this.f108d;
        if (i2 != 1) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = b.f117l;
            do {
                j2 = atomicLongFieldUpdater.get(bVar);
                if (((int) ((9223367638808264704L & j2) >> 42)) == 0) {
                    mVar.getClass();
                    loop1: while (true) {
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = m.f144b;
                        h hVar2 = (h) atomicReferenceFieldUpdater.get(mVar);
                        if (hVar2 == null || hVar2.f132e.f133a != 1) {
                            int i3 = m.f146d.get(mVar);
                            int i4 = m.f145c.get(mVar);
                            while (i3 != i4 && m.f147e.get(mVar) != 0) {
                                i4--;
                                h hVarB = mVar.b(i4, true);
                                if (hVarB != null) {
                                    hVar = hVarB;
                                    break;
                                }
                            }
                            break;
                        }
                        do {
                            if (atomicReferenceFieldUpdater.compareAndSet(mVar, hVar2, null)) {
                                hVar = hVar2;
                                break loop1;
                            }
                        } while (atomicReferenceFieldUpdater.get(mVar) == hVar2);
                    }
                    if (hVar != null) {
                        return hVar;
                    }
                    h hVar3 = (h) bVar.f124i.d();
                    return hVar3 == null ? i(1) : hVar3;
                }
            } while (!b.f117l.compareAndSet(bVar, j2, j2 - 4398046511104L));
            this.f110f = 1;
        }
        if (z2) {
            boolean z3 = d(bVar.f119d * 2) == 0;
            if (z3 && (hVarE2 = e()) != null) {
                return hVarE2;
            }
            mVar.getClass();
            h hVarA = (h) m.f144b.getAndSet(mVar, null);
            if (hVarA == null) {
                hVarA = mVar.a();
            }
            if (hVarA != null) {
                return hVarA;
            }
            if (!z3 && (hVarE = e()) != null) {
                return hVarE;
            }
        } else {
            h hVarE3 = e();
            if (hVarE3 != null) {
                return hVarE3;
            }
        }
        return i(3);
    }

    public final int b() {
        return this.indexInArray;
    }

    public final Object c() {
        return this.nextParkedWorker;
    }

    public final int d(int i2) {
        int i3 = this.f113i;
        int i4 = i3 ^ (i3 << 13);
        int i5 = i4 ^ (i4 >> 17);
        int i6 = i5 ^ (i5 << 5);
        this.f113i = i6;
        int i7 = i2 - 1;
        return (i7 & i2) == 0 ? i6 & i7 : (i6 & Integer.MAX_VALUE) % i2;
    }

    public final h e() {
        int iD = d(2);
        b bVar = this.f115k;
        if (iD == 0) {
            h hVar = (h) bVar.f123h.d();
            return hVar != null ? hVar : (h) bVar.f124i.d();
        }
        h hVar2 = (h) bVar.f124i.d();
        return hVar2 != null ? hVar2 : (h) bVar.f123h.d();
    }

    public final void f(int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f115k.f122g);
        sb.append("-worker-");
        sb.append(i2 == 0 ? "TERMINATED" : String.valueOf(i2));
        setName(sb.toString());
        this.indexInArray = i2;
    }

    public final void g(Object obj) {
        this.nextParkedWorker = obj;
    }

    public final boolean h(int i2) {
        int i3 = this.f110f;
        boolean z2 = i3 == 1;
        if (z2) {
            b.f117l.addAndGet(this.f115k, 4398046511104L);
        }
        if (i3 != i2) {
            this.f110f = i2;
        }
        return z2;
    }

    public final h i(int i2) {
        long j2;
        h hVarB;
        long j3;
        long j4;
        h hVar;
        AtomicLongFieldUpdater atomicLongFieldUpdater = b.f117l;
        b bVar = this.f115k;
        int i3 = (int) (atomicLongFieldUpdater.get(bVar) & 2097151);
        h hVar2 = null;
        if (i3 < 2) {
            return null;
        }
        int iD = d(i3);
        int i4 = 0;
        long jMin = Long.MAX_VALUE;
        while (i4 < i3) {
            iD++;
            if (iD > i3) {
                iD = 1;
            }
            a aVar = (a) bVar.f125j.b(iD);
            if (aVar != null && aVar != this) {
                m mVar = aVar.f108d;
                if (i2 != 3) {
                    mVar.getClass();
                    int i5 = m.f146d.get(mVar);
                    int i6 = m.f145c.get(mVar);
                    boolean z2 = i2 == 1;
                    while (true) {
                        if (i5 != i6) {
                            j2 = 0;
                            if (!z2 || m.f147e.get(mVar) != 0) {
                                int i7 = i5 + 1;
                                hVarB = mVar.b(i5, z2);
                                if (hVarB != null) {
                                    break;
                                }
                                i5 = i7;
                            }
                        } else {
                            j2 = 0;
                        }
                        hVarB = hVar2;
                        break;
                    }
                } else {
                    hVarB = mVar.a();
                    j2 = 0;
                }
                p0.m mVar2 = this.f109e;
                if (hVarB == null) {
                    while (true) {
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = m.f144b;
                        h hVar3 = (h) atomicReferenceFieldUpdater.get(mVar);
                        if (hVar3 == null) {
                            j3 = -1;
                        } else {
                            j3 = -1;
                            if (((hVar3.f132e.f133a == 1 ? 1 : 2) & i2) != 0) {
                                k.f140f.getClass();
                                m mVar3 = mVar;
                                long jNanoTime = System.nanoTime() - hVar3.f131d;
                                long j5 = k.f136b;
                                if (jNanoTime < j5) {
                                    j4 = j5 - jNanoTime;
                                    hVar = null;
                                    break;
                                }
                                do {
                                    hVar = null;
                                    if (atomicReferenceFieldUpdater.compareAndSet(mVar3, hVar3, null)) {
                                        mVar2.f1219d = hVar3;
                                        j4 = -1;
                                        break;
                                    }
                                } while (atomicReferenceFieldUpdater.get(mVar3) == hVar3);
                                mVar = mVar3;
                                hVar2 = null;
                            }
                        }
                        j4 = -2;
                        hVar = hVar2;
                        break;
                    }
                } else {
                    mVar2.f1219d = hVarB;
                    hVar = hVar2;
                    j4 = -1;
                    j3 = -1;
                }
                if (j4 == j3) {
                    h hVar4 = (h) mVar2.f1219d;
                    mVar2.f1219d = hVar;
                    return hVar4;
                }
                if (j4 > j2) {
                    jMin = Math.min(jMin, j4);
                }
            }
            i4++;
            hVar2 = null;
        }
        if (jMin == Long.MAX_VALUE) {
            jMin = 0;
        }
        this.f112h = jMin;
        return null;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        long j2;
        loop0: while (true) {
            boolean z2 = false;
            while (true) {
                if (b.m.get(this.f115k) != 0 || this.f110f == 5) {
                    break loop0;
                }
                h hVarA = a(this.f114j);
                if (hVarA != null) {
                    this.f112h = 0L;
                    b bVar = this.f115k;
                    int i2 = hVarA.f132e.f133a;
                    this.f111g = 0L;
                    if (this.f110f == 3) {
                        this.f110f = 2;
                    }
                    if (i2 != 0 && h(2) && !bVar.e() && !bVar.d(b.f117l.get(bVar))) {
                        bVar.e();
                    }
                    try {
                        hVarA.run();
                    } catch (Throwable th) {
                        Thread threadCurrentThread = Thread.currentThread();
                        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
                    }
                    if (i2 != 0) {
                        b.f117l.addAndGet(bVar, -2097152L);
                        if (this.f110f == 5) {
                            break;
                        }
                        this.f110f = 4;
                        break;
                    }
                    break;
                }
                this.f114j = false;
                if (this.f112h == 0) {
                    Object obj = this.nextParkedWorker;
                    x xVar = b.f118n;
                    if (obj != xVar) {
                        f107l.set(this, -1);
                        while (this.nextParkedWorker != b.f118n) {
                            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f107l;
                            if (atomicIntegerFieldUpdater.get(this) != -1) {
                                break;
                            }
                            b bVar2 = this.f115k;
                            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater2 = b.m;
                            if (atomicIntegerFieldUpdater2.get(bVar2) != 0 || this.f110f == 5) {
                                break;
                            }
                            h(3);
                            Thread.interrupted();
                            if (this.f111g == 0) {
                                j2 = 2097151;
                                this.f111g = System.nanoTime() + this.f115k.f121f;
                            } else {
                                j2 = 2097151;
                            }
                            LockSupport.parkNanos(this.f115k.f121f);
                            if (System.nanoTime() - this.f111g >= 0) {
                                this.f111g = 0L;
                                b bVar3 = this.f115k;
                                synchronized (bVar3.f125j) {
                                    try {
                                        if (!(atomicIntegerFieldUpdater2.get(bVar3) != 0)) {
                                            AtomicLongFieldUpdater atomicLongFieldUpdater = b.f117l;
                                            if (((int) (atomicLongFieldUpdater.get(bVar3) & j2)) > bVar3.f119d) {
                                                if (atomicIntegerFieldUpdater.compareAndSet(this, -1, 1)) {
                                                    int i3 = this.indexInArray;
                                                    f(0);
                                                    bVar3.c(this, i3, 0);
                                                    int andDecrement = (int) (atomicLongFieldUpdater.getAndDecrement(bVar3) & j2);
                                                    if (andDecrement != i3) {
                                                        Object objB = bVar3.f125j.b(andDecrement);
                                                        p0.h.b(objB);
                                                        a aVar = (a) objB;
                                                        bVar3.f125j.c(i3, aVar);
                                                        aVar.f(i3);
                                                        bVar3.c(aVar, andDecrement, i3);
                                                    }
                                                    bVar3.f125j.c(andDecrement, null);
                                                    this.f110f = 5;
                                                }
                                            }
                                        }
                                    } catch (Throwable th2) {
                                        throw th2;
                                    }
                                }
                            }
                        }
                    } else {
                        b bVar4 = this.f115k;
                        if (this.nextParkedWorker == xVar) {
                            AtomicLongFieldUpdater atomicLongFieldUpdater2 = b.f116k;
                            while (true) {
                                long j3 = atomicLongFieldUpdater2.get(bVar4);
                                int i4 = this.indexInArray;
                                this.nextParkedWorker = bVar4.f125j.b((int) (j3 & 2097151));
                                b bVar5 = bVar4;
                                if (b.f116k.compareAndSet(bVar5, j3, ((j3 + 2097152) & (-2097152)) | ((long) i4))) {
                                    break;
                                } else {
                                    bVar4 = bVar5;
                                }
                            }
                        }
                    }
                } else {
                    if (z2) {
                        h(3);
                        Thread.interrupted();
                        LockSupport.parkNanos(this.f112h);
                        this.f112h = 0L;
                        break;
                    }
                    z2 = true;
                }
            }
        }
        h(5);
    }
}
