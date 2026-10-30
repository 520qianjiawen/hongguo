package androidx.lifecycle;

import android.os.Looper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class j extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f634a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public p005d.a f635b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public e f636c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WeakReference f637d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f638e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final A0.g f639f;

    public j(i iVar) {
        new AtomicReference();
        this.f634a = true;
        this.f635b = new p005d.a();
        e eVar = e.f628e;
        this.f636c = eVar;
        new ArrayList();
        this.f637d = new WeakReference(iVar);
        this.f639f = new A0.g(eVar);
    }

    public final void a(d dVar) {
        e eVar;
        p003c.a aVar;
        e eVar2 = e.f627d;
        p0.h.e(dVar, "event");
        if (this.f634a) {
            if (p003c.a.f662j != null) {
                aVar = p003c.a.f662j;
            } else {
                synchronized (p003c.a.class) {
                    try {
                        if (p003c.a.f662j == null) {
                            p003c.a.f662j = new p003c.a(0);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                aVar = p003c.a.f662j;
            }
            ((p003c.a) aVar.f663i).getClass();
            if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
                throw new IllegalStateException("Method handleLifecycleEvent must be called on the main thread");
            }
        }
        switch (c.f626a[dVar.ordinal()]) {
            case 1:
            case 2:
                eVar = e.f629f;
                break;
            case 3:
            case 4:
                eVar = e.f630g;
                break;
            case 5:
                eVar = e.f631h;
                break;
            case 6:
                eVar = eVar2;
                break;
            default:
                throw new IllegalArgumentException(dVar + " has no target state");
        }
        WeakReference weakReference = this.f637d;
        e eVar3 = this.f636c;
        if (eVar3 == eVar) {
            return;
        }
        if (eVar3 == e.f628e && eVar == eVar2) {
            throw new IllegalStateException(("no event down from " + this.f636c + " in component " + weakReference.get()).toString());
        }
        this.f636c = eVar;
        if (this.f638e) {
            return;
        }
        this.f638e = true;
        if (((i) weakReference.get()) == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is already garbage collected. It is too late to change lifecycle state.");
        }
        this.f635b.getClass();
        this.f639f.c(this.f636c);
        this.f638e = false;
        if (this.f636c == eVar2) {
            this.f635b = new p005d.a();
        }
    }
}
