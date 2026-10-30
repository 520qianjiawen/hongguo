package j0;

import C0.AbstractC0000a;
import h0.g;
import h0.i;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import p0.h;
import x0.C0021e;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements h0.d, c, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final h0.d f1071d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final i f1072e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public transient h0.d f1073f;

    public b(h0.d dVar) {
        i context = dVar != null ? dVar.getContext() : null;
        this.f1071d = dVar;
        this.f1072e = context;
    }

    public h0.d b(h0.d dVar, Object obj) {
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }

    @Override // j0.c
    public final c c() {
        h0.d dVar = this.f1071d;
        if (dVar instanceof c) {
            return (c) dVar;
        }
        return null;
    }

    public abstract Object e(Object obj);

    @Override // h0.d
    public final i getContext() {
        i iVar = this.f1072e;
        h.b(iVar);
        return iVar;
    }

    @Override // h0.d
    public final void resumeWith(Object obj) {
        h0.d dVar = this;
        while (true) {
            b bVar = (b) dVar;
            h0.d dVar2 = bVar.f1071d;
            h.b(dVar2);
            try {
                obj = bVar.e(obj);
                if (obj == i0.a.f747d) {
                    return;
                }
            } catch (Throwable th) {
                obj = p000a.a.k(th);
            }
            h0.d dVar3 = bVar.f1073f;
            if (dVar3 != null && dVar3 != bVar) {
                i iVar = bVar.f1072e;
                h.b(iVar);
                g gVarI = iVar.i(h0.e.f745d);
                h.b(gVarI);
                C0.h hVar = (C0.h) dVar3;
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = C0.h.f59k;
                while (atomicReferenceFieldUpdater.get(hVar) == AbstractC0000a.f49d) {
                }
                Object obj2 = atomicReferenceFieldUpdater.get(hVar);
                C0021e c0021e = obj2 instanceof C0021e ? (C0021e) obj2 : null;
                if (c0021e != null) {
                    c0021e.l();
                }
            }
            bVar.f1073f = a.f1070d;
            if (!(dVar2 instanceof b)) {
                dVar2.resumeWith(obj);
                return;
            }
            dVar = dVar2;
        }
    }

    public String toString() {
        int iIntValue;
        String strC;
        Method method;
        Object objInvoke;
        Method method2;
        Object objInvoke2;
        StringBuilder sb = new StringBuilder("Continuation at ");
        d dVar = (d) getClass().getAnnotation(d.class);
        Object name = null;
        str = null;
        str = null;
        str = null;
        str = null;
        str = null;
        String str = null;
        name = null;
        if (dVar != null && dVar.v() >= 1) {
            try {
                Field declaredField = getClass().getDeclaredField("label");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(this);
                Integer num = obj instanceof Integer ? (Integer) obj : null;
                iIntValue = (num != null ? num.intValue() : 0) - 1;
            } catch (Exception unused) {
                iIntValue = -1;
            }
            int i2 = iIntValue >= 0 ? dVar.l()[iIntValue] : -1;
            G.f fVar = e.f1075b;
            G.f fVar2 = e.f1074a;
            if (fVar == null) {
                try {
                    G.f fVar3 = new G.f(Class.class.getDeclaredMethod("getModule", null), getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", null), getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", null));
                    e.f1075b = fVar3;
                    fVar = fVar3;
                } catch (Exception unused2) {
                    e.f1075b = fVar2;
                    fVar = fVar2;
                }
            }
            if (fVar != fVar2 && (method = (Method) fVar.f164d) != null && (objInvoke = method.invoke(getClass(), null)) != null && (method2 = (Method) fVar.f165e) != null && (objInvoke2 = method2.invoke(objInvoke, null)) != null) {
                Method method3 = (Method) fVar.f166f;
                Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, null) : null;
                if (objInvoke3 instanceof String) {
                    str = (String) objInvoke3;
                }
            }
            if (str == null) {
                strC = dVar.c();
            } else {
                strC = str + '/' + dVar.c();
            }
            name = new StackTraceElement(strC, dVar.m(), dVar.f(), i2);
        }
        if (name == null) {
            name = getClass().getName();
        }
        sb.append(name);
        return sb.toString();
    }
}
