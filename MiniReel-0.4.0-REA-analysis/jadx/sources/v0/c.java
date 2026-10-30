package v0;

import g0.m;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class c extends e {
    public static List a(b bVar) {
        Iterator it = bVar.iterator();
        if (!it.hasNext()) {
            return m.f733d;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return p000a.a.A(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }
}
