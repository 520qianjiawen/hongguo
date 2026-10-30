package p0;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import o0.q;
import o0.r;
import o0.s;
import o0.t;
import o0.u;
import o0.v;

/* JADX INFO: loaded from: classes.dex */
public final class e implements u0.b, d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map f1211b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f1212a;

    static {
        Map mapSingletonMap;
        List listT = g0.f.T(o0.a.class, o0.l.class, o0.p.class, z0.b.class, q.class, r.class, s.class, t.class, u.class, v.class, o0.b.class, o0.c.class, o0.d.class, o0.e.class, o0.f.class, o0.g.class, o0.h.class, o0.i.class, o0.j.class, o0.k.class, o0.m.class, o0.n.class, o0.o.class);
        ArrayList arrayList = new ArrayList(listT.size());
        int i2 = 0;
        int i3 = 0;
        for (Object obj : listT) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                throw new ArithmeticException("Index overflow has happened.");
            }
            arrayList.add(new f0.b((Class) obj, Integer.valueOf(i3)));
            i3 = i4;
        }
        int size = arrayList.size();
        if (size == 0) {
            mapSingletonMap = g0.n.f734d;
        } else if (size != 1) {
            int size2 = arrayList.size();
            if (size2 >= 0) {
                size2 = size2 < 3 ? size2 + 1 : size2 < 1073741824 ? (int) ((size2 / 0.75f) + 1.0f) : Integer.MAX_VALUE;
            }
            mapSingletonMap = new LinkedHashMap(size2);
            int size3 = arrayList.size();
            while (i2 < size3) {
                Object obj2 = arrayList.get(i2);
                i2++;
                f0.b bVar = (f0.b) obj2;
                mapSingletonMap.put(bVar.f718d, bVar.f719e);
            }
        } else {
            f0.b bVar2 = (f0.b) arrayList.get(0);
            h.e(bVar2, "pair");
            mapSingletonMap = Collections.singletonMap(bVar2.f718d, bVar2.f719e);
            h.d(mapSingletonMap, "singletonMap(...)");
        }
        f1211b = mapSingletonMap;
    }

    public e(Class cls) {
        h.e(cls, "jClass");
        this.f1212a = cls;
    }

    @Override // p0.d
    public final Class a() {
        return this.f1212a;
    }

    public final String b() {
        String strD;
        Class cls = this.f1212a;
        h.e(cls, "jClass");
        String strConcat = null;
        if (cls.isAnonymousClass()) {
            return null;
        }
        if (!cls.isLocalClass()) {
            if (!cls.isArray()) {
                String strD2 = p.d(cls.getName());
                return strD2 == null ? cls.getSimpleName() : strD2;
            }
            Class<?> componentType = cls.getComponentType();
            if (componentType.isPrimitive() && (strD = p.d(componentType.getName())) != null) {
                strConcat = strD.concat("Array");
            }
            return strConcat == null ? "Array" : strConcat;
        }
        String simpleName = cls.getSimpleName();
        Method enclosingMethod = cls.getEnclosingMethod();
        if (enclosingMethod != null) {
            return w0.g.a(simpleName, enclosingMethod.getName() + '$');
        }
        Constructor<?> enclosingConstructor = cls.getEnclosingConstructor();
        if (enclosingConstructor != null) {
            return w0.g.a(simpleName, enclosingConstructor.getName() + '$');
        }
        int iIndexOf = simpleName.indexOf(36, 0);
        if (iIndexOf == -1) {
            return simpleName;
        }
        String strSubstring = simpleName.substring(iIndexOf + 1, simpleName.length());
        h.d(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof e) && p000a.a.q(this).equals(p000a.a.q((u0.b) obj));
    }

    public final int hashCode() {
        return p000a.a.q(this).hashCode();
    }

    public final String toString() {
        return this.f1212a.toString() + " (Kotlin reflection is not available)";
    }
}
