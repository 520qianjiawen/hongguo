package p018s;

import android.app.Activity;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import p0.e;
import p0.h;
import p019t.c;
import p019t.d;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ClassLoader f1236a;

    public /* synthetic */ b(ClassLoader classLoader) {
        this.f1236a = classLoader;
    }

    public d a(Object obj, e eVar, Activity activity, p021y.b bVar) throws IllegalAccessException, InvocationTargetException {
        c cVar = new c(eVar, bVar);
        Object objNewProxyInstance = Proxy.newProxyInstance(this.f1236a, new Class[]{b()}, cVar);
        h.d(objNewProxyInstance, "newProxyInstance(loader,…onsumerClass()), handler)");
        obj.getClass().getMethod("addWindowLayoutInfoListener", Activity.class, b()).invoke(obj, activity, objNewProxyInstance);
        return new d(obj.getClass().getMethod("removeWindowLayoutInfoListener", b()), obj, objNewProxyInstance);
    }

    public Class b() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.f1236a.loadClass("java.util.function.Consumer");
        h.d(clsLoadClass, "loader.loadClass(\"java.util.function.Consumer\")");
        return clsLoadClass;
    }
}
