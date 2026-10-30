package Y;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f566a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f567b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f568c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f569d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f570e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f571f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final p f572g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Integer f573h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f574i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final H.e f575j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final String[] f576k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final o[] f577l;
    public final Locale[] m;

    public o(boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, int i2, p pVar, Integer num, String str, H.e eVar, String[] strArr, o[] oVarArr, Locale[] localeArr) {
        this.f566a = z2;
        this.f567b = z3;
        this.f568c = z4;
        this.f569d = z5;
        this.f570e = z6;
        this.f571f = i2;
        this.f572g = pVar;
        this.f573h = num;
        this.f574i = str;
        this.f575j = eVar;
        this.f576k = strArr;
        this.f577l = oVarArr;
        this.m = localeArr;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r8v1 Y.o, still in use, count: 2, list:
          (r8v1 Y.o) from 0x021e: PHI (r8v2 Y.o) = (r8v1 Y.o), (r8v4 Y.o) binds: [B:120:0x0211, B:312:0x04fc] A[DONT_GENERATE, DONT_INLINE]
          (r8v1 Y.o) from 0x01e8: MOVE (r30v5 Y.o) = (r8v1 Y.o) (LINE:489)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:93)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:59)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    public static Y.o a(org.json.JSONObject r35) {
        /*
            Method dump skipped, instruction units count: 1764
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: Y.o.a(org.json.JSONObject):Y.o");
    }
}
