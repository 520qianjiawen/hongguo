package p020w;

import androidx.window.extensions.layout.WindowLayoutComponent;
import o0.a;
import p0.h;
import p0.i;
import p018s.b;
import p019t.e;
import p021y.c;
import p021y.d;

/* JADX INFO: loaded from: classes.dex */
public final class f extends i implements a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f f1290e = new f(0);

    @Override // o0.a
    public final Object a() {
        WindowLayoutComponent windowLayoutComponentA;
        try {
            ClassLoader classLoader = h.class.getClassLoader();
            e eVar = classLoader != null ? new e(classLoader, new b(classLoader)) : null;
            if (eVar == null || (windowLayoutComponentA = eVar.a()) == null) {
                return null;
            }
            h.d(classLoader, "loader");
            b bVar = new b(classLoader);
            int iA = e.a();
            if (iA >= 2) {
                return new d(windowLayoutComponentA);
            }
            return iA == 1 ? new c(windowLayoutComponentA, bVar) : new p021y.a();
        } catch (Throwable unused) {
            g gVar = g.f1291a;
            return null;
        }
    }
}
