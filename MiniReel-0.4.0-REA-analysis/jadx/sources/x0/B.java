package x0;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes.dex */
public abstract class B extends C implements InterfaceC0037v {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f1306i = AtomicReferenceFieldUpdater.newUpdater(B.class, Object.class, "_queue");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f1307j = AtomicReferenceFieldUpdater.newUpdater(B.class, Object.class, "_delayed");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f1308k = AtomicIntegerFieldUpdater.newUpdater(B.class, "_isCompleted");
    private volatile Object _delayed;
    private volatile int _isCompleted = 0;
    private volatile Object _queue;

    @Override // x0.AbstractC0032p
    public final void c(h0.i iVar, Runnable runnable) {
        m(runnable);
    }

    public void m(Runnable runnable) {
        loop0: while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1306i;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (f1308k.get(this) == 0) {
                if (obj == null) {
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(this, null, runnable)) {
                            break loop0;
                        }
                    } while (atomicReferenceFieldUpdater.get(this) == null);
                } else if (obj instanceof C0.o) {
                    C0.o oVar = (C0.o) obj;
                    int iA = oVar.a(runnable);
                    if (iA == 0) {
                        break;
                    }
                    if (iA == 1) {
                        C0.o oVarC = oVar.c();
                        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, oVarC) && atomicReferenceFieldUpdater.get(this) == obj) {
                        }
                    } else if (iA != 2) {
                    }
                } else if (obj != AbstractC0034s.f1365b) {
                    C0.o oVar2 = new C0.o(8, true);
                    oVar2.a((Runnable) obj);
                    oVar2.a(runnable);
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(this, obj, oVar2)) {
                            break loop0;
                        }
                    } while (atomicReferenceFieldUpdater.get(this) == obj);
                }
            }
            RunnableC0035t.f1373l.m(runnable);
            return;
        }
        Thread threadJ = j();
        if (Thread.currentThread() != threadJ) {
            LockSupport.unpark(threadJ);
        }
    }

    public final boolean n() {
        g0.c cVar = this.f1311h;
        if (cVar != null ? cVar.isEmpty() : true) {
            Object obj = f1306i.get(this);
            if (obj != null) {
                if (obj instanceof C0.o) {
                    long j2 = C0.o.f77f.get((C0.o) obj);
                    return ((int) (1073741823 & j2)) == ((int) ((j2 & 1152921503533105152L) >> 30));
                }
                if (obj == AbstractC0034s.f1365b) {
                }
            }
            return true;
        }
        return false;
    }

    public final long o() {
        Runnable runnable;
        if (!l()) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1306i;
            loop0: while (true) {
                Object obj = atomicReferenceFieldUpdater.get(this);
                runnable = null;
                if (obj == null) {
                    break;
                }
                if (obj instanceof C0.o) {
                    C0.o oVar = (C0.o) obj;
                    Object objD = oVar.d();
                    if (objD != C0.o.f78g) {
                        runnable = (Runnable) objD;
                        break;
                    }
                    C0.o oVarC = oVar.c();
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, oVarC) && atomicReferenceFieldUpdater.get(this) == obj) {
                    }
                } else {
                    if (obj == AbstractC0034s.f1365b) {
                        break;
                    }
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(this, obj, null)) {
                            runnable = (Runnable) obj;
                            break loop0;
                        }
                    } while (atomicReferenceFieldUpdater.get(this) == obj);
                }
            }
            if (runnable != null) {
                runnable.run();
                return 0L;
            }
            g0.c cVar = this.f1311h;
            if (((cVar == null || cVar.isEmpty()) ? Long.MAX_VALUE : 0L) != 0) {
                Object obj2 = f1306i.get(this);
                if (obj2 == null) {
                } else if (obj2 instanceof C0.o) {
                    long j2 = C0.o.f77f.get((C0.o) obj2);
                    if (((int) (1073741823 & j2)) != ((int) ((j2 & 1152921503533105152L) >> 30))) {
                        return 0L;
                    }
                } else if (obj2 == AbstractC0034s.f1365b) {
                }
                return Long.MAX_VALUE;
            }
        }
        return 0L;
    }

    @Override // x0.C
    public void shutdown() {
        Z.f1335a.set(null);
        f1308k.set(this, 1);
        C0.x xVar = AbstractC0034s.f1365b;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1306i;
        loop0: while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, null, xVar)) {
                        break loop0;
                    }
                } while (atomicReferenceFieldUpdater.get(this) == null);
            } else if (obj instanceof C0.o) {
                ((C0.o) obj).b();
                break;
            } else {
                if (obj == xVar) {
                    break;
                }
                C0.o oVar = new C0.o(8, true);
                oVar.a((Runnable) obj);
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, obj, oVar)) {
                        break loop0;
                    }
                } while (atomicReferenceFieldUpdater.get(this) == obj);
            }
        }
        while (o() <= 0) {
        }
        System.nanoTime();
    }
}
