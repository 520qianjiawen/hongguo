package O;

/* JADX INFO: loaded from: classes.dex */
public final class J implements io.flutter.embedding.engine.renderer.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Runnable f288a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ L f289b;

    public J(L l2, Runnable runnable) {
        this.f289b = l2;
        this.f288a = runnable;
    }

    @Override // io.flutter.embedding.engine.renderer.k
    public final void b() {
        this.f288a.run();
        io.flutter.embedding.engine.renderer.j jVar = this.f289b.f293b;
        if (jVar != null) {
            jVar.e(this);
        }
    }

    @Override // io.flutter.embedding.engine.renderer.k
    public final void a() {
    }
}
