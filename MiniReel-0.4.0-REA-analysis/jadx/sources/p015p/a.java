package p015p;

import H.c;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Trace;
import app.minireel.minireel.tv.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile a f1197d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f1198e = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f1201c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashSet f1200b = new HashSet();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f1199a = new HashMap();

    public a(Context context) {
        this.f1201c = context.getApplicationContext();
    }

    public static a c(Context context) {
        if (f1197d == null) {
            synchronized (f1198e) {
                try {
                    if (f1197d == null) {
                        f1197d = new a(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f1197d;
    }

    public final void a(Bundle bundle) {
        HashSet hashSet;
        String string = this.f1201c.getString(R.string.androidx_startup);
        if (bundle != null) {
            try {
                HashSet hashSet2 = new HashSet();
                Iterator<String> it = bundle.keySet().iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    hashSet = this.f1200b;
                    if (!zHasNext) {
                        break;
                    }
                    String next = it.next();
                    if (string.equals(bundle.getString(next, null))) {
                        Class<?> cls = Class.forName(next);
                        if (b.class.isAssignableFrom(cls)) {
                            hashSet.add(cls);
                        }
                    }
                }
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    b((Class) it2.next(), hashSet2);
                }
            } catch (ClassNotFoundException e2) {
                throw new c(e2);
            }
        }
    }

    public final void b(Class cls, HashSet hashSet) {
        boolean zBooleanValue;
        HashMap map = this.f1199a;
        if (Build.VERSION.SDK_INT >= 29) {
            zBooleanValue = p016q.a.c();
        } else {
            try {
                if (p000a.a.f603f == null) {
                    p000a.a.f602e = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                    p000a.a.f603f = Trace.class.getMethod("isTagEnabled", Long.TYPE);
                }
                zBooleanValue = ((Boolean) p000a.a.f603f.invoke(null, Long.valueOf(p000a.a.f602e))).booleanValue();
            } catch (Exception e2) {
                p000a.a.w("isTagEnabled", e2);
                zBooleanValue = false;
            }
        }
        if (zBooleanValue) {
            try {
                Trace.beginSection(p000a.a.Q(cls.getSimpleName()));
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        if (hashSet.contains(cls)) {
            throw new IllegalStateException("Cannot initialize " + cls.getName() + ". Cycle detected.");
        }
        if (map.containsKey(cls)) {
            map.get(cls);
        } else {
            hashSet.add(cls);
            try {
                b bVar = (b) cls.getDeclaredConstructor(null).newInstance(null);
                List<Class> listA = bVar.a();
                if (!listA.isEmpty()) {
                    for (Class cls2 : listA) {
                        if (!map.containsKey(cls2)) {
                            b(cls2, hashSet);
                        }
                    }
                }
                Object objB = bVar.b(this.f1201c);
                hashSet.remove(cls);
                map.put(cls, objB);
            } catch (Throwable th2) {
                throw new c(th2);
            }
        }
        Trace.endSection();
    }
}
