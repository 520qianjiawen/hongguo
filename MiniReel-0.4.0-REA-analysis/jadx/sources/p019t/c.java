package p019t;

import f0.g;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import p0.e;
import p0.h;
import p0.n;
import p0.p;
import p000a.a;
import p021y.b;

/* JADX INFO: loaded from: classes.dex */
public final class c implements InvocationHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f1242a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f1243b;

    public c(e eVar, b bVar) {
        this.f1242a = eVar;
        this.f1243b = bVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        boolean zIsInstance;
        String strB;
        h.e(obj, "obj");
        h.e(method, "method");
        boolean zA = h.a(method.getName(), "accept");
        b bVar = this.f1243b;
        strB = null;
        strB = null;
        strB = null;
        String strB2 = null;
        boolean z2 = false;
        if (!zA || objArr == null || objArr.length != 1) {
            if ((h.a(method.getName(), "equals") && method.getReturnType().equals(Boolean.TYPE) && objArr != null && objArr.length == 1) == true) {
                return Boolean.valueOf(obj == (objArr != null ? objArr[0] : null));
            }
            if ((h.a(method.getName(), "hashCode") && method.getReturnType().equals(Integer.TYPE) && objArr == null) == true) {
                return Integer.valueOf(bVar.hashCode());
            }
            if (h.a(method.getName(), "toString") && method.getReturnType().equals(String.class) && objArr == null) {
                z2 = true;
            }
            if (z2) {
                return bVar.toString();
            }
            throw new UnsupportedOperationException("Unexpected method call object:" + obj + ", method: " + method + ", args: " + objArr);
        }
        Object obj2 = objArr[0];
        Class cls = this.f1242a.f1212a;
        h.e(cls, "jClass");
        Map map = e.f1211b;
        h.c(map, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.get, V of kotlin.collections.MapsKt__MapsKt.get>");
        Integer num = (Integer) map.get(cls);
        if (num != null) {
            zIsInstance = p.c(num.intValue(), obj2);
        } else {
            zIsInstance = (cls.isPrimitive() ? a.q(n.a(cls)) : cls).isInstance(obj2);
        }
        if (zIsInstance) {
            h.c(obj2, "null cannot be cast to non-null type T of kotlin.reflect.KClasses.cast");
            bVar.h(obj2);
            return g.f725a;
        }
        StringBuilder sb = new StringBuilder("Value cannot be cast to ");
        if (!cls.isAnonymousClass() && !cls.isLocalClass()) {
            if (cls.isArray()) {
                Class<?> componentType = cls.getComponentType();
                if (componentType.isPrimitive() && (strB = p.b(componentType.getName())) != null) {
                    strB2 = strB.concat("Array");
                }
                if (strB2 == null) {
                    strB2 = "kotlin.Array";
                }
            } else {
                strB2 = p.b(cls.getName());
                if (strB2 == null) {
                    strB2 = cls.getCanonicalName();
                }
            }
        }
        sb.append(strB2);
        throw new ClassCastException(sb.toString());
    }
}
