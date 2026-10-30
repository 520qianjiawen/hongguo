package Q;

import C0.x;
import android.content.res.AssetManager;
import android.os.Trace;
import android.util.Log;
import io.flutter.embedding.engine.FlutterJNI;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Z.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final FlutterJNI f460d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AssetManager f461e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f462f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final i f463g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final x f464h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f465i;

    public b(FlutterJNI flutterJNI, AssetManager assetManager, long j2) {
        this.f465i = false;
        H.b bVar = new H.b(3, this);
        this.f460d = flutterJNI;
        this.f461e = assetManager;
        this.f462f = j2;
        i iVar = new i(flutterJNI);
        this.f463g = iVar;
        iVar.a("flutter/isolate", bVar, null);
        this.f464h = new x(6, iVar);
        if (flutterJNI.isAttached()) {
            this.f465i = true;
        }
    }

    @Override // Z.f
    public final void a(String str, Z.d dVar, H.b bVar) {
        this.f464h.a(str, dVar, bVar);
    }

    @Override // Z.f
    public final void b(String str, ByteBuffer byteBuffer, Z.e eVar) {
        this.f464h.b(str, byteBuffer, eVar);
    }

    @Override // Z.f
    public final void c(String str, ByteBuffer byteBuffer) {
        this.f464h.c(str, byteBuffer);
    }

    public final void d(a aVar, List list) {
        if (this.f465i) {
            Log.w("DartExecutor", "Attempted to run a DartExecutor that is already running.");
            return;
        }
        e0.a.b("DartExecutor#executeDartEntrypoint");
        try {
            Objects.toString(aVar);
            this.f460d.runBundleAndSnapshotFromLibrary(aVar.f457a, aVar.f459c, aVar.f458b, this.f461e, list, this.f462f);
            this.f465i = true;
            Trace.endSection();
        } catch (Throwable th) {
            try {
                Trace.endSection();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    @Override // Z.f
    public final H.b f(Z.k kVar) {
        return ((i) this.f464h.f90e).f(kVar);
    }

    @Override // Z.f
    public final void i(String str, Z.d dVar) {
        this.f464h.i(str, dVar);
    }
}
