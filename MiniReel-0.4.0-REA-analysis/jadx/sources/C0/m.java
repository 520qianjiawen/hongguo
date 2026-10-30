package C0;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f74a = AtomicReferenceFieldUpdater.newUpdater(m.class, Object.class, "_cur");
    private volatile Object _cur = new o(8, false);

    public final boolean a(Runnable runnable) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f74a;
            o oVar = (o) atomicReferenceFieldUpdater.get(this);
            int iA = oVar.a(runnable);
            if (iA == 0) {
                return true;
            }
            if (iA == 1) {
                o oVarC = oVar.c();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, oVar, oVarC) && atomicReferenceFieldUpdater.get(this) == oVar) {
                }
            } else if (iA == 2) {
                return false;
            }
        }
    }

    public final void b() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f74a;
            o oVar = (o) atomicReferenceFieldUpdater.get(this);
            if (oVar.b()) {
                return;
            }
            o oVarC = oVar.c();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, oVar, oVarC) && atomicReferenceFieldUpdater.get(this) == oVar) {
            }
        }
    }

    public final int c() {
        o oVar = (o) f74a.get(this);
        oVar.getClass();
        long j2 = o.f77f.get(oVar);
        return (((int) ((j2 & 1152921503533105152L) >> 30)) - ((int) (1073741823 & j2))) & 1073741823;
    }

    public final Object d() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f74a;
            o oVar = (o) atomicReferenceFieldUpdater.get(this);
            Object objD = oVar.d();
            if (objD != o.f78g) {
                return objD;
            }
            o oVarC = oVar.c();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, oVar, oVarC) && atomicReferenceFieldUpdater.get(this) == oVar) {
            }
        }
    }
}
