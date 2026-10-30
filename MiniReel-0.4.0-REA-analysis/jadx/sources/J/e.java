package J;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f188d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f189e;

    public /* synthetic */ e(int i2, Object obj) {
        this.f188d = i2;
        this.f189e = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zIsEmpty;
        switch (this.f188d) {
            case 0:
                g gVar = (g) this.f189e;
                ArrayList arrayList = gVar.f196f;
                while (!arrayList.isEmpty() && gVar.f202l == null) {
                    ((K.d) arrayList.get(0)).f264a.run();
                    arrayList.remove(0);
                }
                return;
            case 1:
                Q.h hVar = (Q.h) this.f189e;
                ExecutorService executorService = hVar.f480a;
                ConcurrentLinkedQueue concurrentLinkedQueue = hVar.f481b;
                AtomicBoolean atomicBoolean = hVar.f482c;
                if (atomicBoolean.compareAndSet(false, true)) {
                    try {
                        Runnable runnable = (Runnable) concurrentLinkedQueue.poll();
                        if (runnable != null) {
                            runnable.run();
                        }
                        if (zIsEmpty) {
                            return;
                        } else {
                            return;
                        }
                    } finally {
                        atomicBoolean.set(false);
                        if (!concurrentLinkedQueue.isEmpty()) {
                            executorService.execute(new e(1, hVar));
                        }
                    }
                    break;
                }
                return;
            case 2:
                ((T.a) this.f189e).f504b.f512f.prefetchDefaultFontManager();
                return;
            case 3:
                androidx.lifecycle.m mVar = (androidx.lifecycle.m) this.f189e;
                androidx.lifecycle.j jVar = mVar.f646f;
                if (mVar.f642b == 0) {
                    mVar.f643c = true;
                    jVar.a(androidx.lifecycle.d.ON_PAUSE);
                }
                if (mVar.f641a == 0 && mVar.f643c) {
                    jVar.a(androidx.lifecycle.d.ON_STOP);
                    mVar.f644d = true;
                    return;
                }
                return;
            default:
                ((io.flutter.plugin.platform.j) this.f189e).e(false);
                return;
        }
    }
}
