package p022z;

import O.q;
import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.Window;
import android.view.WindowManager;
import f0.g;
import g0.m;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import p014o.d;
import p020w.k;
import x.a;

/* JADX INFO: loaded from: classes.dex */
public final class l implements a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile l f1412c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ReentrantLock f1413d = new ReentrantLock();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f1414a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArrayList f1415b = new CopyOnWriteArrayList();

    public l(j jVar) {
        this.f1414a = jVar;
        if (jVar != null) {
            jVar.d(new p002b0.a(8, this));
        }
    }

    @Override // x.a
    public final void a(q qVar) {
        synchronized (f1413d) {
            try {
                if (this.f1414a == null) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (k kVar : this.f1415b) {
                    if (kVar.f1410b == qVar) {
                        arrayList.add(kVar);
                    }
                }
                this.f1415b.removeAll(arrayList);
                int size = arrayList.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj = arrayList.get(i2);
                    i2++;
                    Activity activity = ((k) obj).f1409a;
                    CopyOnWriteArrayList copyOnWriteArrayList = this.f1415b;
                    if (copyOnWriteArrayList == null || !copyOnWriteArrayList.isEmpty()) {
                        Iterator it = copyOnWriteArrayList.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (((k) it.next()).f1409a.equals(activity)) {
                                }
                            }
                        }
                    }
                    a aVar = this.f1414a;
                    if (aVar != null) {
                        ((j) aVar).b(activity);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

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
    @Override // x.a
    public final void b(Context context, d dVar, q qVar) {
        Object next;
        WindowManager.LayoutParams attributes;
        g gVar = null;
        iBinder = null;
        IBinder iBinder = null;
        Activity activity = context instanceof Activity ? (Activity) context : null;
        m mVar = m.f733d;
        if (activity != null) {
            ReentrantLock reentrantLock = f1413d;
            reentrantLock.lock();
            try {
                a aVar = this.f1414a;
                if (aVar == null) {
                    qVar.accept(new k(mVar));
                    reentrantLock.unlock();
                    return;
                }
                CopyOnWriteArrayList copyOnWriteArrayList = this.f1415b;
                boolean z2 = false;
                if (copyOnWriteArrayList == null || !copyOnWriteArrayList.isEmpty()) {
                    Iterator it = copyOnWriteArrayList.iterator();
                    while (it.hasNext()) {
                        if (((k) it.next()).f1409a.equals(activity)) {
                            z2 = true;
                            break;
                        }
                    }
                }
                k kVar = new k(activity, dVar, qVar);
                copyOnWriteArrayList.add(kVar);
                if (z2) {
                    Iterator it2 = copyOnWriteArrayList.iterator();
                    do {
                        if (!it2.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it2.next();
                    } while (!activity.equals(((k) next).f1409a));
                    k kVar2 = (k) next;
                    k kVar3 = kVar2 != null ? kVar2.f1411c : null;
                    if (kVar3 != null) {
                        kVar.f1411c = kVar3;
                        kVar.f1410b.accept(kVar3);
                    }
                } else {
                    j jVar = (j) aVar;
                    Window window = activity.getWindow();
                    if (window != null && (attributes = window.getAttributes()) != null) {
                        iBinder = attributes.token;
                    }
                    if (iBinder != null) {
                        jVar.c(iBinder, activity);
                    } else {
                        activity.getWindow().getDecorView().addOnAttachStateChangeListener(new i(jVar, activity));
                    }
                }
                reentrantLock.unlock();
                gVar = g.f725a;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
        if (gVar == null) {
            qVar.accept(new k(mVar));
        }
    }
}
