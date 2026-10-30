package p018s;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import p0.h;
import p0.i;

/* JADX INFO: loaded from: classes.dex */
public final class a extends i implements o0.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1234e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f1235f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(int i2, Object obj) {
        super(0);
        this.f1234e = i2;
        this.f1235f = obj;
    }

    @Override // o0.a
    public final Object a() throws NoSuchMethodException, ClassNotFoundException {
        switch (this.f1234e) {
            case 0:
                b bVar = (b) this.f1235f;
                Class<?> clsLoadClass = bVar.f1236a.loadClass("androidx.window.extensions.WindowExtensionsProvider");
                h.d(clsLoadClass, "loader.loadClass(WindowE…XTENSIONS_PROVIDER_CLASS)");
                Method declaredMethod = clsLoadClass.getDeclaredMethod("getWindowExtensions", null);
                Class<?> clsLoadClass2 = bVar.f1236a.loadClass("androidx.window.extensions.WindowExtensions");
                h.d(clsLoadClass2, "loader.loadClass(WindowE….WINDOW_EXTENSIONS_CLASS)");
                h.d(declaredMethod, "getWindowExtensionsMethod");
                return Boolean.valueOf(declaredMethod.getReturnType().equals(clsLoadClass2) && Modifier.isPublic(declaredMethod.getModifiers()));
            default:
                p019t.h hVar = (p019t.h) this.f1235f;
                return BigInteger.valueOf(hVar.f1255d).shiftLeft(32).or(BigInteger.valueOf(hVar.f1256e)).shiftLeft(32).or(BigInteger.valueOf(hVar.f1257f));
        }
    }
}
