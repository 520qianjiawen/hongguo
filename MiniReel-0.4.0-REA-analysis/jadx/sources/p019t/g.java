package p019t;

import C0.z;
import o0.l;
import p0.h;
import p000a.a;

/* JADX INFO: loaded from: classes.dex */
public final class g extends a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f1251i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f1252j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final a f1253k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(Object obj, int i2, a aVar) {
        super(28);
        h.e(obj, "value");
        z.g("verificationMode", i2);
        this.f1251i = obj;
        this.f1252j = i2;
        this.f1253k = aVar;
    }

    @Override // p000a.a
    public final a K(String str, l lVar) {
        Object obj = this.f1251i;
        return ((Boolean) lVar.h(obj)).booleanValue() ? this : new f(obj, str, this.f1253k, this.f1252j);
    }

    @Override // p000a.a
    public final Object i() {
        return this.f1251i;
    }
}
