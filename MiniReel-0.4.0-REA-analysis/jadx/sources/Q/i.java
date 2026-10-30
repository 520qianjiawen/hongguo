package Q;

import C0.x;
import android.os.Build;
import android.os.Trace;
import android.util.Log;
import io.flutter.embedding.engine.FlutterJNI;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class i implements Z.f, j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final FlutterJNI f483d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashMap f484e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final HashMap f485f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f486g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AtomicBoolean f487h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final HashMap f488i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f489j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final k f490k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final WeakHashMap f491l;
    public final x m;

    public i(FlutterJNI flutterJNI) {
        x xVar = new x(7);
        xVar.f90e = (ExecutorService) G.f.d().f166f;
        this.f484e = new HashMap();
        this.f485f = new HashMap();
        this.f486g = new Object();
        this.f487h = new AtomicBoolean(false);
        this.f488i = new HashMap();
        this.f489j = 1;
        this.f490k = new k();
        this.f491l = new WeakHashMap();
        this.f483d = flutterJNI;
        this.m = xVar;
    }

    @Override // Z.f
    public final void a(String str, Z.d dVar, H.b bVar) {
        e eVar;
        if (dVar == null) {
            synchronized (this.f486g) {
                this.f484e.remove(str);
            }
            return;
        }
        if (bVar != null) {
            eVar = (e) this.f491l.get(bVar);
            if (eVar == null) {
                throw new IllegalArgumentException("Unrecognized TaskQueue, use BinaryMessenger to create your TaskQueue (ex makeBackgroundTaskQueue).");
            }
        } else {
            eVar = null;
        }
        synchronized (this.f486g) {
            try {
                this.f484e.put(str, new f(dVar, eVar));
                List<d> list = (List) this.f485f.remove(str);
                if (list == null) {
                    return;
                }
                for (d dVar2 : list) {
                    d(str, (f) this.f484e.get(str), dVar2.f472a, dVar2.f473b, dVar2.f474c);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // Z.f
    public final void b(String str, ByteBuffer byteBuffer, Z.e eVar) {
        e0.a.b("DartMessenger#send on " + str);
        try {
            int i2 = this.f489j;
            this.f489j = i2 + 1;
            if (eVar != null) {
                this.f488i.put(Integer.valueOf(i2), eVar);
            }
            FlutterJNI flutterJNI = this.f483d;
            if (byteBuffer == null) {
                flutterJNI.dispatchEmptyPlatformMessage(str, i2);
            } else {
                flutterJNI.dispatchPlatformMessage(str, byteBuffer, byteBuffer.position(), i2);
            }
            Trace.endSection();
        } catch (Throwable th) {
            try {
                Trace.endSection();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // Z.f
    public final void c(String str, ByteBuffer byteBuffer) {
        b(str, byteBuffer, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [Q.c] */
    public final void d(final String str, final f fVar, final ByteBuffer byteBuffer, final int i2, final long j2) {
        e eVar = fVar != null ? fVar.f476b : null;
        String strA = e0.a.a("PlatformChannel ScheduleHandler on " + str);
        if (Build.VERSION.SDK_INT >= 29) {
            p016q.a.a(p000a.a.Q(strA), i2);
        } else {
            String strQ = p000a.a.Q(strA);
            try {
                if (p000a.a.f604g == null) {
                    p000a.a.f604g = Trace.class.getMethod("asyncTraceBegin", Long.TYPE, String.class, Integer.TYPE);
                }
                p000a.a.f604g.invoke(null, Long.valueOf(p000a.a.f602e), strQ, Integer.valueOf(i2));
            } catch (Exception e2) {
                p000a.a.w("asyncTraceBegin", e2);
            }
        }
        ?? r1 = new Runnable() { // from class: Q.c
            @Override // java.lang.Runnable
            public final void run() {
                long j3 = j2;
                FlutterJNI flutterJNI = this.f466d.f483d;
                StringBuilder sb = new StringBuilder("PlatformChannel ScheduleHandler on ");
                String str2 = str;
                sb.append(str2);
                String strA2 = e0.a.a(sb.toString());
                int i3 = Build.VERSION.SDK_INT;
                int i4 = i2;
                if (i3 >= 29) {
                    p016q.a.b(p000a.a.Q(strA2), i4);
                } else {
                    String strQ2 = p000a.a.Q(strA2);
                    try {
                        if (p000a.a.f605h == null) {
                            p000a.a.f605h = Trace.class.getMethod("asyncTraceEnd", Long.TYPE, String.class, Integer.TYPE);
                        }
                        p000a.a.f605h.invoke(null, Long.valueOf(p000a.a.f602e), strQ2, Integer.valueOf(i4));
                    } catch (Exception e3) {
                        p000a.a.w("asyncTraceEnd", e3);
                    }
                }
                try {
                    e0.a.b("DartMessenger#handleMessageFromDart on " + str2);
                    f fVar2 = fVar;
                    ByteBuffer byteBuffer2 = byteBuffer;
                    try {
                        if (fVar2 != null) {
                            try {
                                try {
                                    fVar2.f475a.j(byteBuffer2, new g(flutterJNI, i4));
                                } catch (Exception e4) {
                                    Log.e("DartMessenger", "Uncaught exception in binary message listener", e4);
                                    flutterJNI.invokePlatformMessageEmptyResponseCallback(i4);
                                }
                            } catch (Error e5) {
                                Thread threadCurrentThread = Thread.currentThread();
                                if (threadCurrentThread.getUncaughtExceptionHandler() == null) {
                                    throw e5;
                                }
                                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, e5);
                            }
                        } else {
                            flutterJNI.invokePlatformMessageEmptyResponseCallback(i4);
                        }
                        if (byteBuffer2 != null && byteBuffer2.isDirect()) {
                            byteBuffer2.limit(0);
                        }
                        Trace.endSection();
                        flutterJNI.cleanupMessageData(j3);
                    } catch (Throwable th) {
                        try {
                            Trace.endSection();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    flutterJNI.cleanupMessageData(j3);
                    throw th3;
                }
            }
        };
        e eVar2 = eVar;
        if (eVar == null) {
            eVar2 = this.f490k;
        }
        eVar2.a(r1);
    }

    @Override // Z.f
    public final H.b f(Z.k kVar) {
        x xVar = this.m;
        xVar.getClass();
        h hVar = new h((ExecutorService) xVar.f90e);
        H.b bVar = new H.b(4);
        this.f491l.put(bVar, hVar);
        return bVar;
    }

    @Override // Z.f
    public final void i(String str, Z.d dVar) {
        a(str, dVar, null);
    }
}
