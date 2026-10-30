package C0;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f76e = AtomicReferenceFieldUpdater.newUpdater(o.class, Object.class, "_next");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f77f = AtomicLongFieldUpdater.newUpdater(o.class, "_state");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final x f78g = new x(0, "REMOVE_FROZEN");
    private volatile Object _next;
    private volatile long _state;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f79a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f80b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f81c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AtomicReferenceArray f82d;

    public o(int i2, boolean z2) {
        this.f79a = i2;
        this.f80b = z2;
        int i3 = i2 - 1;
        this.f81c = i3;
        this.f82d = new AtomicReferenceArray(i2);
        if (i3 > 1073741823) {
            throw new IllegalStateException("Check failed.");
        }
        if ((i2 & i3) != 0) {
            throw new IllegalStateException("Check failed.");
        }
    }

    public final int a(Object obj) {
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f77f;
            long j2 = atomicLongFieldUpdater.get(this);
            if ((3458764513820540928L & j2) != 0) {
                return (2305843009213693952L & j2) != 0 ? 2 : 1;
            }
            int i2 = (int) (1073741823 & j2);
            int i3 = (int) ((1152921503533105152L & j2) >> 30);
            int i4 = this.f81c;
            if (((i3 + 2) & i4) == (i2 & i4)) {
                return 1;
            }
            boolean z2 = this.f80b;
            AtomicReferenceArray atomicReferenceArray = this.f82d;
            if (z2 || atomicReferenceArray.get(i3 & i4) == null) {
                if (f77f.compareAndSet(this, j2, ((-1152921503533105153L) & j2) | (((long) ((i3 + 1) & 1073741823)) << 30))) {
                    atomicReferenceArray.set(i3 & i4, obj);
                    o oVarC = this;
                    while ((atomicLongFieldUpdater.get(oVarC) & 1152921504606846976L) != 0) {
                        oVarC = oVarC.c();
                        AtomicReferenceArray atomicReferenceArray2 = oVarC.f82d;
                        int i5 = oVarC.f81c & i3;
                        Object obj2 = atomicReferenceArray2.get(i5);
                        if ((obj2 instanceof n) && ((n) obj2).f75a == i3) {
                            atomicReferenceArray2.set(i5, obj);
                        } else {
                            oVarC = null;
                        }
                        if (oVarC == null) {
                            return 0;
                        }
                    }
                    return 0;
                }
            } else {
                int i6 = this.f79a;
                if (i6 < 1024 || ((i3 - i2) & 1073741823) > (i6 >> 1)) {
                    return 1;
                }
            }
        }
    }

    public final boolean b() {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j2;
        do {
            atomicLongFieldUpdater = f77f;
            j2 = atomicLongFieldUpdater.get(this);
            if ((j2 & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j2) != 0) {
                return false;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j2, 2305843009213693952L | j2));
        return true;
    }

    public final o c() {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j2;
        o oVar;
        while (true) {
            atomicLongFieldUpdater = f77f;
            j2 = atomicLongFieldUpdater.get(this);
            if ((j2 & 1152921504606846976L) != 0) {
                oVar = this;
                break;
            }
            long j3 = 1152921504606846976L | j2;
            oVar = this;
            if (atomicLongFieldUpdater.compareAndSet(oVar, j2, j3)) {
                j2 = j3;
                break;
            }
        }
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f76e;
            o oVar2 = (o) atomicReferenceFieldUpdater.get(this);
            if (oVar2 != null) {
                return oVar2;
            }
            o oVar3 = new o(oVar.f79a * 2, oVar.f80b);
            int i2 = (int) (1073741823 & j2);
            int i3 = (int) ((1152921503533105152L & j2) >> 30);
            while (true) {
                int i4 = oVar.f81c;
                int i5 = i2 & i4;
                if (i5 == (i4 & i3)) {
                    break;
                }
                Object nVar = oVar.f82d.get(i5);
                if (nVar == null) {
                    nVar = new n(i2);
                }
                oVar3.f82d.set(oVar3.f81c & i2, nVar);
                i2++;
            }
            atomicLongFieldUpdater.set(oVar3, (-1152921504606846977L) & j2);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, oVar3) && atomicReferenceFieldUpdater.get(this) == null) {
            }
        }
    }

    public final Object d() {
        o oVarC = this;
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f77f;
            long j2 = atomicLongFieldUpdater.get(oVarC);
            if ((j2 & 1152921504606846976L) != 0) {
                return f78g;
            }
            int i2 = (int) (j2 & 1073741823);
            int i3 = oVarC.f81c;
            int i4 = i2 & i3;
            if ((((int) ((1152921503533105152L & j2) >> 30)) & i3) != i4) {
                AtomicReferenceArray atomicReferenceArray = oVarC.f82d;
                Object obj = atomicReferenceArray.get(i4);
                boolean z2 = oVarC.f80b;
                if (obj == null) {
                    if (z2) {
                    }
                } else if (!(obj instanceof n)) {
                    long j3 = (i2 + 1) & 1073741823;
                    if (f77f.compareAndSet(oVarC, j2, (j2 & (-1073741824)) | j3)) {
                        atomicReferenceArray.set(i4, null);
                        return obj;
                    }
                    oVarC = this;
                    if (z2) {
                        while (true) {
                            long j4 = atomicLongFieldUpdater.get(oVarC);
                            int i5 = (int) (j4 & 1073741823);
                            if ((j4 & 1152921504606846976L) != 0) {
                                oVarC = oVarC.c();
                            } else {
                                o oVar = oVarC;
                                if (f77f.compareAndSet(oVar, j4, (j4 & (-1073741824)) | j3)) {
                                    oVar.f82d.set(i5 & oVar.f81c, null);
                                    oVarC = null;
                                } else {
                                    oVarC = oVar;
                                }
                            }
                            if (oVarC == null) {
                                return obj;
                            }
                        }
                    }
                }
            }
            return null;
        }
    }
}
