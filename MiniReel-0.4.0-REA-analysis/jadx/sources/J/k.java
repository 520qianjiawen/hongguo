package J;

import C0.x;

/* JADX INFO: loaded from: classes.dex */
public interface k {
    default void a(g gVar, Runnable runnable) {
        c(new h(gVar == null ? null : new x(2, gVar), runnable));
    }

    void b();

    void c(h hVar);

    void start();
}
