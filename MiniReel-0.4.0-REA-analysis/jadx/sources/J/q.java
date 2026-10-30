package J;

/* JADX INFO: loaded from: classes.dex */
public final class q implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ g f239d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Y.k f240e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ s f241f;

    public q(s sVar, g gVar, Y.k kVar) {
        this.f241f = sVar;
        this.f239d = gVar;
        this.f240e = kVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (s.f249i) {
            s.a(this.f241f, this.f239d);
        }
        this.f240e.d(null);
    }
}
