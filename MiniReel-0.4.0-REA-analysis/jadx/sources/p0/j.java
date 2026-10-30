package p0;

/* JADX INFO: loaded from: classes.dex */
public final class j extends l implements u0.c, o0.l {
    public j(String str, String str2) {
        super(b.f1204d, D.b.class, str, str2, 0);
    }

    @Override // p0.c
    public final u0.a b() {
        n.f1220a.getClass();
        return this;
    }

    public final void f() {
        if (this.f1218j) {
            throw new UnsupportedOperationException("Kotlin reflection is not yet supported for synthetic Java properties. Please follow/upvote https://youtrack.jetbrains.com/issue/KT-55980");
        }
        u0.a aVarE = e();
        if (aVarE == this) {
            throw new n0.a("Kotlin reflection implementation is not found at runtime. Make sure you have kotlin-reflect.jar in the classpath");
        }
        ((j) ((u0.c) aVarE)).f();
    }

    @Override // o0.l
    public final Object h(Object obj) {
        f();
        throw null;
    }
}
