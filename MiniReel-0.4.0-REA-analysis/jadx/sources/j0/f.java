package j0;

import p0.h;
import p0.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class f extends b implements p0.f {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f1076g;

    public f(h0.d dVar) {
        super(dVar);
        this.f1076g = 2;
    }

    @Override // p0.f
    public final int d() {
        return this.f1076g;
    }

    @Override // j0.b
    public final String toString() {
        if (this.f1071d != null) {
            return super.toString();
        }
        n.f1220a.getClass();
        String string = getClass().getGenericInterfaces()[0].toString();
        if (string.startsWith("kotlin.jvm.functions.")) {
            string = string.substring(21);
        }
        h.d(string, "renderLambdaToString(...)");
        return string;
    }
}
