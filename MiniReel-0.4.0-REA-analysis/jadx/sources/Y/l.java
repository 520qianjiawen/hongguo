package Y;

import C0.x;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f555a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f556b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final H.e f557c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public k f558d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f559e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f560f;

    public l(Q.b bVar, boolean z2) {
        H.e eVar = new H.e(bVar, "flutter/restoration", Z.q.f598a, null);
        this.f559e = false;
        this.f560f = false;
        x xVar = new x(20, this);
        this.f557c = eVar;
        this.f555a = z2;
        eVar.j(xVar);
    }

    public static HashMap a(byte[] bArr) {
        HashMap map = new HashMap();
        map.put("enabled", Boolean.TRUE);
        map.put("data", bArr);
        return map;
    }
}
