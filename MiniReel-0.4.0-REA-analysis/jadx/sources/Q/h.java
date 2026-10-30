package Q;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class h implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ExecutorService f480a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentLinkedQueue f481b = new ConcurrentLinkedQueue();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AtomicBoolean f482c = new AtomicBoolean(false);

    public h(ExecutorService executorService) {
        this.f480a = executorService;
    }

    @Override // Q.e
    public final void a(c cVar) {
        this.f481b.add(cVar);
        this.f480a.execute(new J.e(1, this));
    }
}
