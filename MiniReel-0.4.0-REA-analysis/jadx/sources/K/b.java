package K;

import C0.x;
import Y.k;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class b extends p000a.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final x f262i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final H.a f263j;

    /* JADX WARN: Illegal instructions before constructor call */
    public b(H.a aVar, k kVar) {
        int i2 = 3;
        super(i2);
        this.f263j = aVar;
        this.f262i = new x(i2, kVar);
    }

    @Override // p000a.a
    public final Object p(String str) {
        return this.f263j.x(str);
    }

    @Override // p000a.a
    public final String r() {
        return (String) this.f263j.f170e;
    }

    @Override // p000a.a
    public final c t() {
        return this.f262i;
    }

    @Override // p000a.a
    public final boolean x() {
        Object obj = this.f263j.f171f;
        if (obj == null) {
            return false;
        }
        if (obj instanceof Map) {
            return ((Map) obj).containsKey("transactionId");
        }
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).has("transactionId");
        }
        throw new ClassCastException();
    }
}
