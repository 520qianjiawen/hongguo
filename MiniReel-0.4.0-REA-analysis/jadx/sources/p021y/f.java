package p021y;

import O.q;
import android.content.Context;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.layout.WindowLayoutInfo;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.locks.ReentrantLock;
import p0.h;
import p011k.a;
import p020w.k;

/* JADX INFO: loaded from: classes.dex */
public final class f implements a, Consumer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1389a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public k f1391c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ReentrantLock f1390b = new ReentrantLock();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinkedHashSet f1392d = new LinkedHashSet();

    public f(Context context) {
        this.f1389a = context;
    }

    @Override // p011k.a
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final void accept(WindowLayoutInfo windowLayoutInfo) {
        h.e(windowLayoutInfo, "value");
        ReentrantLock reentrantLock = this.f1390b;
        reentrantLock.lock();
        try {
            this.f1391c = e.b(this.f1389a, windowLayoutInfo);
            Iterator it = this.f1392d.iterator();
            while (it.hasNext()) {
                ((a) it.next()).accept(this.f1391c);
            }
            reentrantLock.unlock();
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final void b(q qVar) {
        ReentrantLock reentrantLock = this.f1390b;
        reentrantLock.lock();
        try {
            k kVar = this.f1391c;
            if (kVar != null) {
                qVar.accept(kVar);
            }
            this.f1392d.add(qVar);
        } finally {
            reentrantLock.unlock();
        }
    }
}
