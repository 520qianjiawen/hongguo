package C0;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: renamed from: C0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0001b extends r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f52a = AtomicReferenceFieldUpdater.newUpdater(AbstractC0001b.class, Object.class, "_consensus");
    private volatile Object _consensus = AbstractC0000a.f46a;

    @Override // C0.r
    public final Object a(Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f52a;
        Object obj2 = atomicReferenceFieldUpdater.get(this);
        x xVar = AbstractC0000a.f46a;
        if (obj2 == xVar) {
            x xVarC = c(obj);
            obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 == xVar) {
                while (!atomicReferenceFieldUpdater.compareAndSet(this, xVar, xVarC)) {
                    if (atomicReferenceFieldUpdater.get(this) != xVar) {
                        obj2 = atomicReferenceFieldUpdater.get(this);
                    }
                }
                obj2 = xVarC;
            }
        }
        b(obj, obj2);
        return obj2;
    }

    public abstract void b(Object obj, Object obj2);

    public abstract x c(Object obj);
}
