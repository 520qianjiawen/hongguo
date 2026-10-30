package Z;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f589a = new AtomicBoolean(false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ G.f f590b;

    public g(G.f fVar) {
        this.f590b = fVar;
    }

    public final void a(Number number) {
        if (this.f589a.get()) {
            return;
        }
        G.f fVar = this.f590b;
        if (((AtomicReference) fVar.f165e).get() != this) {
            return;
        }
        G.f fVar2 = (G.f) fVar.f166f;
        ((f) fVar2.f164d).c((String) fVar2.f165e, ((n) fVar2.f166f).a(number));
    }
}
