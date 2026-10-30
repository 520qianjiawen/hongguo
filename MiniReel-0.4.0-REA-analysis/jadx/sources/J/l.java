package J;

import android.os.HandlerThread;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public final class l implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f214a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f215b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f216c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Serializable f217d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Serializable f218e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Serializable f219f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Serializable f220g;

    public l(int i2, int i3) {
        this.f217d = new LinkedList();
        this.f218e = new HashSet();
        this.f219f = new HashSet();
        this.f220g = new HashMap();
        this.f216c = "Sqflite";
        this.f214a = i2;
        this.f215b = i3;
    }

    @Override // J.k
    public synchronized void b() {
        try {
            for (j jVar : (HashSet) this.f218e) {
                synchronized (jVar) {
                    HandlerThread handlerThread = jVar.f210c;
                    if (handlerThread != null) {
                        handlerThread.quit();
                        jVar.f210c = null;
                        jVar.f211d = null;
                    }
                }
            }
            for (j jVar2 : (HashSet) this.f219f) {
                synchronized (jVar2) {
                    HandlerThread handlerThread2 = jVar2.f210c;
                    if (handlerThread2 != null) {
                        handlerThread2.quit();
                        jVar2.f210c = null;
                        jVar2.f211d = null;
                    }
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // J.k
    public synchronized void c(h hVar) {
        ((LinkedList) this.f217d).add(hVar);
        Iterator it = new HashSet((HashSet) this.f218e).iterator();
        while (it.hasNext()) {
            e((j) it.next());
        }
    }

    public synchronized h d(j jVar) {
        h hVar;
        j jVar2;
        try {
            ListIterator listIterator = ((LinkedList) this.f217d).listIterator();
            do {
                if (!listIterator.hasNext()) {
                    return null;
                }
                hVar = (h) listIterator.next();
                jVar2 = hVar.a() != null ? (j) ((HashMap) this.f220g).get(hVar.a()) : null;
                if (jVar2 == null) {
                    break;
                }
            } while (jVar2 != jVar);
            listIterator.remove();
            return hVar;
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void e(j jVar) {
        try {
            h hVarD = d(jVar);
            if (hVarD != null) {
                ((HashSet) this.f219f).add(jVar);
                ((HashSet) this.f218e).remove(jVar);
                if (hVarD.a() != null) {
                    ((HashMap) this.f220g).put(hVarD.a(), jVar);
                }
                jVar.f211d.post(new i(jVar, hVarD));
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // J.k
    public synchronized void start() {
        for (int i2 = 0; i2 < this.f214a; i2++) {
            j jVar = new j(((String) this.f216c) + i2, this.f215b);
            jVar.a(new i(this, jVar));
            ((HashSet) this.f218e).add(jVar);
        }
    }

    public l(Integer num, int i2, Boolean bool, Integer num2, int i3, Integer num3, Boolean bool2) {
        this.f216c = num;
        this.f214a = i2;
        this.f217d = bool;
        this.f218e = num2;
        this.f215b = i3;
        this.f219f = num3;
        this.f220g = bool2;
    }
}
