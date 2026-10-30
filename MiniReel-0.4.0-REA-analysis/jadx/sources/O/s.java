package O;

/* JADX INFO: loaded from: classes.dex */
public final class s implements io.flutter.embedding.engine.renderer.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ io.flutter.embedding.engine.renderer.j f348a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ J.e f349b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ t f350c;

    public s(t tVar, io.flutter.embedding.engine.renderer.j jVar, J.e eVar) {
        this.f350c = tVar;
        this.f348a = jVar;
        this.f349b = eVar;
    }

    @Override // io.flutter.embedding.engine.renderer.k
    public final void b() {
        C0013k c0013k;
        this.f348a.e(this);
        this.f349b.run();
        t tVar = this.f350c;
        if ((tVar.f356e instanceof C0013k) || (c0013k = tVar.f355d) == null) {
            return;
        }
        c0013k.c();
        C0013k c0013k2 = tVar.f355d;
        if (c0013k2 != null) {
            c0013k2.f326a.close();
            tVar.removeView(tVar.f355d);
            tVar.f355d = null;
        }
    }

    @Override // io.flutter.embedding.engine.renderer.k
    public final void a() {
    }
}
