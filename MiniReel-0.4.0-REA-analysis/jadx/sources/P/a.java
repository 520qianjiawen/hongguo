package P;

import android.util.SparseArray;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c f390a;

    public a(c cVar) {
        this.f390a = cVar;
    }

    @Override // P.b
    public final void a() {
        c cVar = this.f390a;
        Iterator it = cVar.f412u.iterator();
        while (it.hasNext()) {
            ((b) it.next()).a();
        }
        io.flutter.plugin.platform.j jVar = cVar.f409r;
        SparseArray sparseArray = jVar.f902n;
        while (sparseArray.size() > 0) {
            jVar.f911x.l(sparseArray.keyAt(0));
        }
        io.flutter.plugin.platform.i iVar = cVar.f410s;
        SparseArray sparseArray2 = iVar.f886j;
        while (sparseArray2.size() > 0) {
            iVar.f892q.l(sparseArray2.keyAt(0));
        }
        cVar.f402j.f556b = null;
    }

    @Override // P.b
    public final void b() {
    }
}
