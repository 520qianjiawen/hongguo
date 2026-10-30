package v0;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReference f1270a;

    public a(f fVar) {
        this.f1270a = new AtomicReference(fVar);
    }

    @Override // v0.b
    public final Iterator iterator() {
        b bVar = (b) this.f1270a.getAndSet(null);
        if (bVar != null) {
            return bVar.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}
