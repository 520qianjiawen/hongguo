package com.github.dart_lang.jni;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PortProxyBuilder implements InvocationHandler {
    private static final PortCleaner cleaner = new PortCleaner();
    private static final Method equals;
    private static final Method hashCode;
    private static final Method toString;
    private final long isolateId;
    private final long mainPortId;
    private boolean built = false;
    private final HashMap<String, DartImplementation> implementations = new HashMap<>();
    private final HashSet<String> asyncMethods = new HashSet<>();

    public static final class DartException extends Exception {
        Throwable cause;

        private DartException(String str, Throwable th) {
            super(str);
            this.cause = th;
        }
    }

    public static final class DartImplementation {
        final long pointer;
        final long port;

        public DartImplementation(long j2, long j3) {
            this.port = j2;
            this.pointer = j3;
        }
    }

    static {
        try {
            equals = Object.class.getDeclaredMethod("equals", Object.class);
            hashCode = Object.class.getDeclaredMethod("hashCode", null);
            toString = Object.class.getDeclaredMethod("toString", null);
            System.loadLibrary("dartjni");
        } catch (NoSuchMethodException unused) {
            throw new Error();
        }
    }

    public PortProxyBuilder(long j2, long j3) {
        this.isolateId = j2;
        this.mainPortId = j3;
    }

    private static native void _cleanUp(long j2);

    private static native Object[] _invoke(long j2, long j3, long j4, long j5, Object obj, String str, Object[] objArr, boolean z2);

    private static void appendType(StringBuilder sb, Class<?> cls) {
        if (cls == Void.TYPE) {
            sb.append('V');
            return;
        }
        if (cls == Boolean.TYPE) {
            sb.append('Z');
            return;
        }
        if (cls == Byte.TYPE) {
            sb.append('B');
            return;
        }
        if (cls == Character.TYPE) {
            sb.append('C');
            return;
        }
        if (cls == Short.TYPE) {
            sb.append('S');
            return;
        }
        if (cls == Integer.TYPE) {
            sb.append('I');
            return;
        }
        if (cls == Long.TYPE) {
            sb.append('J');
            return;
        }
        if (cls == Float.TYPE) {
            sb.append('F');
            return;
        }
        if (cls == Double.TYPE) {
            sb.append('D');
            return;
        }
        if (cls.isArray()) {
            sb.append('[');
            appendType(sb, cls.getComponentType());
        } else {
            sb.append('L');
            sb.append(cls.getName().replace('.', '/'));
            sb.append(';');
        }
    }

    private static String getDescriptor(Method method) {
        StringBuilder sb = new StringBuilder();
        sb.append(method.getName());
        sb.append('(');
        for (Class<?> cls : method.getParameterTypes()) {
            appendType(sb, cls);
        }
        sb.append(')');
        appendType(sb, method.getReturnType());
        return sb.toString();
    }

    public void addImplementation(String str, long j2, long j3, List<String> list) {
        this.implementations.put(str, new DartImplementation(j2, j3));
        this.asyncMethods.addAll(list);
    }

    public Object build() {
        if (this.implementations.isEmpty()) {
            throw new IllegalStateException("No interface implementation added");
        }
        if (this.built) {
            throw new IllegalStateException("This proxy has already been built");
        }
        this.built = true;
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.implementations.keySet().iterator();
        while (it.hasNext()) {
            arrayList.add(Class.forName(it.next()));
        }
        Object objNewProxyInstance = Proxy.newProxyInstance(((Class) arrayList.get(0)).getClassLoader(), (Class[]) arrayList.toArray(new Class[0]), this);
        Iterator<DartImplementation> it2 = this.implementations.values().iterator();
        while (it2.hasNext()) {
            cleaner.register(objNewProxyInstance, it2.next().port);
        }
        return objNewProxyInstance;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        if (method.equals(equals)) {
            return Boolean.valueOf(obj == objArr[0]);
        }
        if (method.equals(hashCode)) {
            return Integer.valueOf(System.identityHashCode(obj));
        }
        if (method.equals(toString)) {
            return obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
        }
        DartImplementation dartImplementation = this.implementations.get(method.getDeclaringClass().getName());
        String descriptor = getDescriptor(method);
        boolean zContains = this.asyncMethods.contains(descriptor);
        Object[] objArr_invoke = _invoke(dartImplementation.port, this.isolateId, this.mainPortId, dartImplementation.pointer, obj, descriptor, objArr, !zContains);
        if (zContains) {
            return null;
        }
        _cleanUp(((Long) objArr_invoke[0]).longValue());
        Object obj2 = objArr_invoke[1];
        if (!(obj2 instanceof DartException)) {
            return obj2;
        }
        Throwable th = ((DartException) obj2).cause;
        if (th != null) {
            throw th;
        }
        throw ((DartException) obj2);
    }
}
