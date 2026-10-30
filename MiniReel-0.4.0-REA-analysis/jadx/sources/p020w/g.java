package p020w;

import android.content.Context;
import f0.e;
import java.math.BigInteger;
import java.util.concurrent.locks.ReentrantLock;
import p0.h;
import p0.n;
import p022z.j;
import p022z.l;
import x.a;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ g f1291a = new g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final e f1292b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f1293c;

    static {
        n.a(h.class).b();
        f1292b = new e(f.f1290e);
        f1293c = a.f1272a;
    }

    public static b a(Context context) {
        h.e(context, "context");
        a aVar = (a) f1292b.a();
        if (aVar == null) {
            l lVar = l.f1412c;
            if (l.f1412c == null) {
                ReentrantLock reentrantLock = l.f1413d;
                reentrantLock.lock();
                try {
                    if (l.f1412c == null) {
                        j jVar = null;
                        try {
                            p019t.h hVarB = p022z.h.b();
                            if (hVarB != null) {
                                p019t.h hVar = p019t.h.f1254i;
                                h.e(hVar, "other");
                                Object objA = hVarB.f1259h.a();
                                h.d(objA, "<get-bigInteger>(...)");
                                Object objA2 = hVar.f1259h.a();
                                h.d(objA2, "<get-bigInteger>(...)");
                                if (((BigInteger) objA).compareTo((BigInteger) objA2) >= 0) {
                                    j jVar2 = new j(context);
                                    if (jVar2.e()) {
                                        jVar = jVar2;
                                    }
                                }
                            }
                        } catch (Throwable unused) {
                        }
                        l.f1412c = new l(jVar);
                    }
                    reentrantLock.unlock();
                } catch (Throwable th) {
                    reentrantLock.unlock();
                    throw th;
                }
            }
            aVar = l.f1412c;
            h.b(aVar);
        }
        int i2 = n.f1305b;
        b bVar = new b(aVar);
        f1293c.getClass();
        return bVar;
    }
}
