package D0;

import C0.AbstractC0000a;
import C0.y;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f135a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f136b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f137c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f138d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f139e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final f f140f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final i f141g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final i f142h;

    static {
        String property;
        int i2 = y.f91a;
        try {
            property = System.getProperty("kotlinx.coroutines.scheduler.default.name");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null) {
            property = "DefaultDispatcher";
        }
        f135a = property;
        f136b = AbstractC0000a.h("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 1L, Long.MAX_VALUE);
        int i3 = y.f91a;
        if (i3 < 2) {
            i3 = 2;
        }
        f137c = AbstractC0000a.i("kotlinx.coroutines.scheduler.core.pool.size", i3, 8);
        f138d = AbstractC0000a.i("kotlinx.coroutines.scheduler.max.pool.size", 2097150, 4);
        f139e = TimeUnit.SECONDS.toNanos(AbstractC0000a.h("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 1L, Long.MAX_VALUE));
        f140f = f.f129a;
        f141g = new i(0);
        f142h = new i(1);
    }
}
