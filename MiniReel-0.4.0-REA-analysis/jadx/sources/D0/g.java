package D0;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import x0.D;

/* JADX INFO: loaded from: classes.dex */
public abstract class g extends D {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b f130f;

    @Override // x0.AbstractC0032p
    public final void c(h0.i iVar, Runnable runnable) {
        b bVar = this.f130f;
        AtomicLongFieldUpdater atomicLongFieldUpdater = b.f116k;
        bVar.b(runnable, k.f141g);
    }
}
