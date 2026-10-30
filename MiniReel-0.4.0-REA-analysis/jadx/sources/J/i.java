package J;

import C0.x;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f205d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ j f206e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f207f;

    public /* synthetic */ i(j jVar, h hVar) {
        this.f206e = jVar;
        this.f207f = hVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        x xVar;
        switch (this.f205d) {
            case 0:
                j jVar = this.f206e;
                h hVar = (h) this.f207f;
                jVar.getClass();
                hVar.f204b.run();
                jVar.f213f = hVar;
                jVar.f212e.run();
                return;
            default:
                l lVar = (l) this.f207f;
                j jVar2 = this.f206e;
                synchronized (lVar) {
                    try {
                        HashSet hashSet = new HashSet((HashSet) lVar.f218e);
                        ((HashSet) lVar.f219f).remove(jVar2);
                        ((HashSet) lVar.f218e).add(jVar2);
                        h hVar2 = jVar2.f213f;
                        if (hVar2 == null || (xVar = hVar2.f203a) == null || !((g) xVar.f90e).k()) {
                            h hVar3 = jVar2.f213f;
                            if ((hVar3 != null ? hVar3.a() : null) != null) {
                                HashMap map = (HashMap) lVar.f220g;
                                h hVar4 = jVar2.f213f;
                                map.remove(hVar4 != null ? hVar4.a() : null);
                            }
                        }
                        lVar.e(jVar2);
                        Iterator it = hashSet.iterator();
                        while (it.hasNext()) {
                            lVar.e((j) it.next());
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return;
        }
    }

    public /* synthetic */ i(l lVar, j jVar) {
        this.f207f = lVar;
        this.f206e = jVar;
    }
}
