package D0;

import C0.AbstractC0000a;
import C0.y;
import java.util.concurrent.Executor;
import x0.AbstractC0032p;
import x0.D;

/* JADX INFO: loaded from: classes.dex */
public final class c extends D implements Executor {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final c f126f = new c();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final AbstractC0032p f127g;

    static {
        AbstractC0032p jVar = l.f143f;
        int i2 = y.f91a;
        if (64 >= i2) {
            i2 = 64;
        }
        int i3 = AbstractC0000a.i("kotlinx.coroutines.io.parallelism", i2, 12);
        jVar.getClass();
        if (i3 < 1) {
            throw new IllegalArgumentException(("Expected positive parallelism level, but got " + i3).toString());
        }
        if (i3 < k.f138d) {
            if (i3 < 1) {
                throw new IllegalArgumentException(("Expected positive parallelism level, but got " + i3).toString());
            }
            jVar = new C0.j(jVar, i3);
        }
        f127g = jVar;
    }

    @Override // x0.AbstractC0032p
    public final void c(h0.i iVar, Runnable runnable) {
        f127g.c(iVar, runnable);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO");
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        c(h0.j.f746d, runnable);
    }

    @Override // x0.AbstractC0032p
    public final String toString() {
        return "Dispatchers.IO";
    }
}
