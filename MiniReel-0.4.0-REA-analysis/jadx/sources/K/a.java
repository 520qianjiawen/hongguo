package K;

import H.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class a extends p000a.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Map f259i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final e f260j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f261k;

    public a(Map map, boolean z2) {
        super(3);
        this.f260j = new e();
        this.f259i = map;
        this.f261k = z2;
    }

    public final void T(ArrayList arrayList) {
        if (this.f261k) {
            return;
        }
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        e eVar = this.f260j;
        map2.put("code", (String) eVar.f175e);
        map2.put("message", (String) eVar.f176f);
        map2.put("data", eVar.f177g);
        map.put("error", map2);
        arrayList.add(map);
    }

    public final void U(ArrayList arrayList) {
        if (this.f261k) {
            return;
        }
        HashMap map = new HashMap();
        map.put("result", this.f260j.f174d);
        arrayList.add(map);
    }

    @Override // p000a.a
    public final Object p(String str) {
        return this.f259i.get(str);
    }

    @Override // p000a.a
    public final String r() {
        return (String) this.f259i.get("method");
    }

    @Override // p000a.a
    public final boolean s() {
        return this.f261k;
    }

    @Override // p000a.a
    public final c t() {
        return this.f260j;
    }

    @Override // p000a.a
    public final boolean x() {
        return this.f259i.containsKey("transactionId");
    }
}
