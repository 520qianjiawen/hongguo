package P;

import O.AbstractActivityC0007e;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f452a = new ArrayList();

    public j(AbstractActivityC0007e abstractActivityC0007e, String[] strArr) {
        T.c cVar = (T.c) G.f.d().f164d;
        if (cVar.f508b) {
            return;
        }
        cVar.d(abstractActivityC0007e.getApplicationContext());
        cVar.a(abstractActivityC0007e.getApplicationContext(), strArr);
    }

    public final c a(i iVar) {
        c cVarB;
        Context context = iVar.f446a;
        Q.a aVar = iVar.f447b;
        String str = iVar.f448c;
        List list = iVar.f449d;
        io.flutter.plugin.platform.j jVar = new io.flutter.plugin.platform.j();
        boolean z2 = iVar.f450e;
        boolean z3 = iVar.f451f;
        if (aVar == null) {
            T.c cVar = (T.c) G.f.d().f164d;
            if (!cVar.f508b) {
                throw new AssertionError("DartEntrypoints can only be created once a FlutterEngine is created.");
            }
            aVar = new Q.a((String) cVar.f511e.f165e, "main");
        }
        Q.a aVar2 = aVar;
        ArrayList arrayList = this.f452a;
        if (arrayList.size() == 0) {
            cVarB = new c(context, null, jVar, z2, z3);
            if (str != null) {
                cVarB.f400h.f519a.h("setInitialRoute", str, null);
            }
            cVarB.f394b.d(aVar2, list);
        } else {
            cVarB = ((c) arrayList.get(0)).b(context, aVar2, str, list, jVar, z2, z3);
        }
        arrayList.add(cVarB);
        cVarB.f412u.add(new h(this, cVarB));
        return cVarB;
    }
}
