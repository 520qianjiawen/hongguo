package O;

import android.os.Build;
import java.util.Iterator;

/* JADX INFO: renamed from: O.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0008f implements io.flutter.embedding.engine.renderer.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f310a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f311b;

    public /* synthetic */ C0008f(int i2, Object obj) {
        this.f310a = i2;
        this.f311b = obj;
    }

    @Override // io.flutter.embedding.engine.renderer.k
    public final void a() {
        switch (this.f310a) {
            case 0:
                C0011i c0011i = (C0011i) this.f311b;
                c0011i.f314a.getClass();
                c0011i.f321h = false;
                break;
            case 1:
                t tVar = (t) this.f311b;
                tVar.f359h = false;
                Iterator it = tVar.f358g.iterator();
                while (it.hasNext()) {
                    ((io.flutter.embedding.engine.renderer.k) it.next()).a();
                }
                break;
            case 2:
                break;
            default:
                ((io.flutter.embedding.engine.renderer.j) this.f311b).f796d = false;
                break;
        }
    }

    @Override // io.flutter.embedding.engine.renderer.k
    public final void b() {
        switch (this.f310a) {
            case 0:
                C0011i c0011i = (C0011i) this.f311b;
                AbstractActivityC0007e abstractActivityC0007e = c0011i.f314a;
                if (Build.VERSION.SDK_INT >= 29) {
                    abstractActivityC0007e.reportFullyDrawn();
                } else {
                    abstractActivityC0007e.getClass();
                }
                c0011i.f321h = true;
                c0011i.f322i = true;
                break;
            case 1:
                t tVar = (t) this.f311b;
                tVar.f359h = true;
                Iterator it = tVar.f358g.iterator();
                while (it.hasNext()) {
                    ((io.flutter.embedding.engine.renderer.k) it.next()).b();
                }
                break;
            case 2:
                L l2 = (L) this.f311b;
                l2.f292a.setAlpha(1.0f);
                io.flutter.embedding.engine.renderer.j jVar = l2.f293b;
                if (jVar != null) {
                    jVar.e(this);
                }
                break;
            default:
                ((io.flutter.embedding.engine.renderer.j) this.f311b).f796d = true;
                break;
        }
    }

    private final void c() {
    }
}
