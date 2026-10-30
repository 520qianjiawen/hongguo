package G;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import com.alexmercerind.mediakitandroidhelper.MediaKitAndroidHelper;
import io.flutter.embedding.engine.renderer.j;
import io.flutter.view.TextureRegistry$SurfaceProducer;
import io.flutter.view.y;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class e implements y {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Method f154f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Method f155g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final HashSet f156h = new HashSet();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Handler f157i = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f160c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final TextureRegistry$SurfaceProducer f161d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f158a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f159b = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f162e = new Object();

    static {
        try {
            int i2 = MediaKitAndroidHelper.f669a;
            Method declaredMethod = MediaKitAndroidHelper.class.getDeclaredMethod("newGlobalObjectRef", Object.class);
            f154f = declaredMethod;
            Method declaredMethod2 = MediaKitAndroidHelper.class.getDeclaredMethod("deleteGlobalObjectRef", Long.TYPE);
            f155g = declaredMethod2;
            declaredMethod.setAccessible(true);
            declaredMethod2.setAccessible(true);
        } catch (Throwable unused) {
            Log.i("media_kit", "package:media_kit_libs_android_video missing. Make sure you have added it to pubspec.yaml.");
            throw new RuntimeException("Failed to initialize com.alexmercerind.media_kit_video.VideoOutput.");
        }
    }

    public e(j jVar, a aVar) {
        this.f160c = aVar;
        jVar.getClass();
        TextureRegistry$SurfaceProducer textureRegistry$SurfaceProducerC = jVar.c(1);
        this.f161d = textureRegistry$SurfaceProducerC;
        textureRegistry$SurfaceProducerC.setCallback(this);
    }

    public final void a() {
        synchronized (this.f162e) {
            try {
                this.f161d.getSurface().release();
            } catch (Throwable th) {
                Log.e("VideoOutput", "dispose", th);
            }
            try {
                this.f161d.release();
            } catch (Throwable th2) {
                Log.e("VideoOutput", "dispose", th2);
            }
            c();
        }
    }

    public final void b() {
        long jLongValue;
        synchronized (this.f162e) {
            Log.i("VideoOutput", "onSurfaceAvailable");
            this.f158a = this.f161d.id();
            Surface surface = this.f161d.getSurface();
            Log.i("VideoOutput", String.format(Locale.ENGLISH, "newGlobalRef: object = %s", surface));
            try {
                Object objInvoke = f154f.invoke(null, surface);
                Objects.requireNonNull(objInvoke);
                jLongValue = ((Long) objInvoke).longValue();
            } catch (Throwable th) {
                Log.e("VideoOutput", "newGlobalRef", th);
                jLongValue = 0;
            }
            long j2 = jLongValue;
            this.f159b = j2;
            a aVar = this.f160c;
            long j3 = this.f158a;
            int width = this.f161d.getWidth();
            int height = this.f161d.getHeight();
            aVar.f149a.f151d.h("VideoOutput.Resize", new b(aVar.f150b, j3, j2, width, height), null);
        }
    }

    public final void c() {
        synchronized (this.f162e) {
            try {
                Log.i("VideoOutput", "onSurfaceCleanup");
                a aVar = this.f160c;
                long j2 = this.f158a;
                int width = this.f161d.getWidth();
                int height = this.f161d.getHeight();
                aVar.f149a.f151d.h("VideoOutput.Resize", new b(aVar.f150b, j2, 0L, width, height), null);
                final long j3 = this.f159b;
                if (j3 != 0) {
                    f157i.postDelayed(new Runnable() { // from class: G.d
                        @Override // java.lang.Runnable
                        public final void run() {
                            HashSet hashSet = e.f156h;
                            long j4 = j3;
                            if (hashSet.contains(Long.valueOf(j4))) {
                                Locale locale = Locale.ENGLISH;
                                Log.i("VideoOutput", "deleteGlobalObjectRef: ref = " + j4 + " ALREADY DELETED");
                                return;
                            }
                            if (hashSet.size() > 100) {
                                hashSet.clear();
                            }
                            hashSet.add(Long.valueOf(j4));
                            Locale locale2 = Locale.ENGLISH;
                            Log.i("VideoOutput", "deleteGlobalObjectRef: ref = " + j4);
                            try {
                                e.f155g.invoke(null, Long.valueOf(j4));
                            } catch (Throwable th) {
                                Log.e("VideoOutput", "deleteGlobalObjectRef", th);
                            }
                        }
                    }, 5000L);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void d(int i2, int i3) {
        synchronized (this.f162e) {
            try {
                if (this.f161d.getWidth() == i2 && this.f161d.getHeight() == i3) {
                    return;
                }
                this.f161d.setSize(i2, i3);
                b();
            } catch (Throwable th) {
                Log.e("VideoOutput", "setSurfaceSize", th);
            }
        }
    }
}
