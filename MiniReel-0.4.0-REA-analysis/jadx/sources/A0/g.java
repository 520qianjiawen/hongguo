package A0;

import C0.x;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import x0.C0021e;

/* JADX INFO: loaded from: classes.dex */
public final class g extends B0.a implements c, d {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f23h = AtomicReferenceFieldUpdater.newUpdater(g.class, Object.class, "_state");
    private volatile Object _state;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f24g;

    public g(Object obj) {
        this._state = obj;
    }

    @Override // A0.d
    public final Object a(Object obj, j0.b bVar) {
        c(obj);
        return f0.g.f725a;
    }

    /* JADX WARN: Code duplicated, block: B:52:0x00d6 A[Catch: all -> 0x0041, TryCatch #0 {all -> 0x0041, blocks: (B:14:0x003b, B:50:0x00ce, B:52:0x00d6, B:55:0x00dd, B:56:0x00e3, B:58:0x00e6, B:68:0x0107, B:71:0x011b, B:72:0x0135, B:78:0x0145, B:75:0x013c, B:77:0x0142, B:60:0x00ec, B:64:0x00f3, B:21:0x0056, B:24:0x0061, B:49:0x00be), top: B:97:0x0029 }] */
    /* JADX WARN: Code duplicated, block: B:58:0x00e6 A[Catch: all -> 0x0041, TryCatch #0 {all -> 0x0041, blocks: (B:14:0x003b, B:50:0x00ce, B:52:0x00d6, B:55:0x00dd, B:56:0x00e3, B:58:0x00e6, B:68:0x0107, B:71:0x011b, B:72:0x0135, B:78:0x0145, B:75:0x013c, B:77:0x0142, B:60:0x00ec, B:64:0x00f3, B:21:0x0056, B:24:0x0061, B:49:0x00be), top: B:97:0x0029 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:63:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:66:0x0105  */
    /* JADX WARN: Code duplicated, block: B:67:0x0106 A[PHI: r4 r5 r10 r11 r12 r13
      0x0106: PHI (r4v14 java.lang.Object) = (r4v18 java.lang.Object), (r4v20 java.lang.Object) binds: [B:65:0x0103, B:21:0x0056] A[DONT_GENERATE, DONT_INLINE]
      0x0106: PHI (r5v1 int) = (r5v7 int), (r5v0 int) binds: [B:65:0x0103, B:21:0x0056] A[DONT_GENERATE, DONT_INLINE]
      0x0106: PHI (r10v7 x0.J) = (r10v9 x0.J), (r10v13 x0.J) binds: [B:65:0x0103, B:21:0x0056] A[DONT_GENERATE, DONT_INLINE]
      0x0106: PHI (r11v6 A0.i) = (r11v8 A0.i), (r11v12 A0.i) binds: [B:65:0x0103, B:21:0x0056] A[DONT_GENERATE, DONT_INLINE]
      0x0106: PHI (r12v2 A0.d) = (r12v4 A0.d), (r12v7 A0.d) binds: [B:65:0x0103, B:21:0x0056] A[DONT_GENERATE, DONT_INLINE]
      0x0106: PHI (r13v4 A0.g) = (r13v6 A0.g), (r13v10 A0.g) binds: [B:65:0x0103, B:21:0x0056] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:70:0x0119 A[PHI: r0 r5 r10 r11 r12 r13
      0x0119: PHI (r0v27 java.lang.Object) = (r0v19 java.lang.Object), (r0v28 java.lang.Object) binds: [B:69:0x0117, B:83:0x0151] A[DONT_GENERATE, DONT_INLINE]
      0x0119: PHI (r5v8 int) = (r5v2 int), (r5v9 int) binds: [B:69:0x0117, B:83:0x0151] A[DONT_GENERATE, DONT_INLINE]
      0x0119: PHI (r10v11 x0.J) = (r10v8 x0.J), (r10v12 x0.J) binds: [B:69:0x0117, B:83:0x0151] A[DONT_GENERATE, DONT_INLINE]
      0x0119: PHI (r11v9 A0.i) = (r11v7 A0.i), (r11v10 A0.i) binds: [B:69:0x0117, B:83:0x0151] A[DONT_GENERATE, DONT_INLINE]
      0x0119: PHI (r12v5 A0.d) = (r12v3 A0.d), (r12v6 A0.d) binds: [B:69:0x0117, B:83:0x0151] A[DONT_GENERATE, DONT_INLINE]
      0x0119: PHI (r13v7 A0.g) = (r13v5 A0.g), (r13v8 A0.g) binds: [B:69:0x0117, B:83:0x0151] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:69:0x0117 -> B:70:0x0119). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // A0.c
    public final java.lang.Object b(A0.d r17, h0.d r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 369
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: A0.g.b(A0.d, h0.d):java.lang.Object");
    }

    public final void c(Object obj) {
        int i2;
        B0.b[] bVarArr;
        x xVar;
        if (obj == null) {
            obj = B0.e.f38a;
        }
        synchronized (this) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f23h;
            if (p0.h.a(atomicReferenceFieldUpdater.get(this), obj)) {
                return;
            }
            atomicReferenceFieldUpdater.set(this, obj);
            int i3 = this.f24g;
            if ((i3 & 1) != 0) {
                this.f24g = i3 + 2;
                return;
            }
            int i4 = i3 + 1;
            this.f24g = i4;
            B0.b[] bVarArr2 = this.f28d;
            while (true) {
                i[] iVarArr = (i[]) bVarArr2;
                if (iVarArr != null) {
                    for (i iVar : iVarArr) {
                        if (iVar != null) {
                            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = i.f27a;
                            while (true) {
                                Object obj2 = atomicReferenceFieldUpdater2.get(iVar);
                                if (obj2 == null || obj2 == (xVar = h.f26b)) {
                                    break;
                                }
                                x xVar2 = h.f25a;
                                if (obj2 != xVar2) {
                                    do {
                                        if (atomicReferenceFieldUpdater2.compareAndSet(iVar, obj2, xVar2)) {
                                            ((C0021e) obj2).resumeWith(f0.g.f725a);
                                            break;
                                        }
                                    } while (atomicReferenceFieldUpdater2.get(iVar) == obj2);
                                } else {
                                    do {
                                        if (atomicReferenceFieldUpdater2.compareAndSet(iVar, obj2, xVar)) {
                                            break;
                                        }
                                    } while (atomicReferenceFieldUpdater2.get(iVar) == obj2);
                                }
                            }
                        }
                    }
                }
                synchronized (this) {
                    i2 = this.f24g;
                    if (i2 == i4) {
                        this.f24g = i4 + 1;
                        return;
                    }
                    bVarArr = this.f28d;
                }
                bVarArr2 = bVarArr;
                i4 = i2;
            }
        }
    }
}
