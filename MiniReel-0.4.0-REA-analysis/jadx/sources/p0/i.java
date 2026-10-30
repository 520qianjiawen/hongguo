package p0;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class i implements f, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1216d;

    public i(int i2) {
        this.f1216d = i2;
    }

    @Override // p0.f
    public final int d() {
        return this.f1216d;
    }

    public final String toString() {
        n.f1220a.getClass();
        String string = getClass().getGenericInterfaces()[0].toString();
        if (string.startsWith("kotlin.jvm.functions.")) {
            string = string.substring(21);
        }
        h.d(string, "renderLambdaToString(...)");
        return string;
    }
}
