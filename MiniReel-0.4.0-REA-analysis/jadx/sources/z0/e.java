package z0;

import C0.AbstractC0000a;
import C0.x;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import x0.AbstractC0034s;
import x0.C0021e;
import x0.C0027k;
import x0.InterfaceC0020d;
import x0.V;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k f1429a = new k(-1, null, null, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f1430b = AbstractC0000a.i("kotlinx.coroutines.bufferedChannel.segmentSize", 32, 12);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f1431c = AbstractC0000a.i("kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations", 10000, 12);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final x f1432d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final x f1433e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final x f1434f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final x f1435g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final x f1436h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final x f1437i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final x f1438j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final x f1439k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final x f1440l;
    public static final x m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final x f1441n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final x f1442o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final x f1443p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final x f1444q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final x f1445r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final x f1446s;

    static {
        int i2 = 0;
        f1432d = new x(i2, "BUFFERED");
        f1433e = new x(i2, "SHOULD_BUFFER");
        f1434f = new x(i2, "S_RESUMING_BY_RCV");
        f1435g = new x(i2, "RESUMING_BY_EB");
        f1436h = new x(i2, "POISONED");
        f1437i = new x(i2, "DONE_RCV");
        f1438j = new x(i2, "INTERRUPTED_SEND");
        f1439k = new x(i2, "INTERRUPTED_RCV");
        f1440l = new x(i2, "CHANNEL_CLOSED");
        m = new x(i2, "SUSPEND");
        f1441n = new x(i2, "SUSPEND_NO_WAITER");
        f1442o = new x(i2, "FAILED");
        f1443p = new x(i2, "NO_RECEIVE_RESULT");
        f1444q = new x(i2, "CLOSE_HANDLER_CLOSED");
        f1445r = new x(i2, "CLOSE_HANDLER_INVOKED");
        f1446s = new x(i2, "NO_CLOSE_CAUSE");
    }

    public static final boolean a(InterfaceC0020d interfaceC0020d, Object obj, C0.q qVar) {
        C0021e c0021e = (C0021e) interfaceC0020d;
        c0021e.getClass();
        x xVar = AbstractC0034s.f1364a;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = C0021e.f1342j;
        loop0: while (true) {
            Object obj2 = atomicReferenceFieldUpdater.get(c0021e);
            if (!(obj2 instanceof V)) {
                xVar = null;
                break;
            }
            int i2 = c0021e.f1375f;
            if (!(obj instanceof C0027k) && i2 != 1) {
            }
            do {
                if (atomicReferenceFieldUpdater.compareAndSet(c0021e, obj2, obj)) {
                    if (!c0021e.q()) {
                        c0021e.l();
                        break loop0;
                    }
                    break loop0;
                }
            } while (atomicReferenceFieldUpdater.get(c0021e) == obj2);
        }
        if (xVar == null) {
            return false;
        }
        c0021e.m(c0021e.f1375f);
        return true;
    }
}
