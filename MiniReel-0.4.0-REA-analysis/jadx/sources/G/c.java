package G;

import Y.k;
import Z.m;
import android.os.Build;
import android.util.Log;
import io.flutter.embedding.engine.renderer.j;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class c implements V.b, m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public H.e f151d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public f f152e;

    @Override // Z.m
    public final void m(H.a aVar, k kVar) {
        String str = (String) aVar.f170e;
        str.getClass();
        boolean z2 = true;
        switch (str) {
            case "VideoOutputManager.SetSurfaceSize":
                long j2 = Long.parseLong((String) aVar.x("handle"));
                int i2 = Integer.parseInt((String) aVar.x("width"));
                int i3 = Integer.parseInt((String) aVar.x("height"));
                f fVar = this.f152e;
                synchronized (fVar.f166f) {
                    try {
                        Locale locale = Locale.ENGLISH;
                        Log.i("VideoOutputManager", "com.alexmercerind.media_kit_video.VideoOutputManager.setSurfaceSize: " + j2 + " " + i2 + " " + i3);
                        if (((HashMap) fVar.f164d).containsKey(Long.valueOf(j2))) {
                            e eVar = (e) ((HashMap) fVar.f164d).get(Long.valueOf(j2));
                            Objects.requireNonNull(eVar);
                            eVar.d(i2, i3);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                kVar.d(null);
                return;
            case "VideoOutputManager.Dispose":
                long j3 = Long.parseLong((String) aVar.x("handle"));
                f fVar2 = this.f152e;
                synchronized (fVar2.f166f) {
                    try {
                        Locale locale2 = Locale.ENGLISH;
                        Log.i("VideoOutputManager", "com.alexmercerind.media_kit_video.VideoOutputManager.dispose: " + j3);
                        if (((HashMap) fVar2.f164d).containsKey(Long.valueOf(j3))) {
                            e eVar2 = (e) ((HashMap) fVar2.f164d).get(Long.valueOf(j3));
                            Objects.requireNonNull(eVar2);
                            eVar2.a();
                            ((HashMap) fVar2.f164d).remove(Long.valueOf(j3));
                        }
                    } catch (Throwable th2) {
                        throw th2;
                    }
                    break;
                }
                kVar.d(null);
                return;
            case "Utils.IsEmulator":
                try {
                    if (!Build.BRAND.startsWith("generic") || !Build.DEVICE.startsWith("generic")) {
                        String str2 = Build.FINGERPRINT;
                        if (!str2.startsWith("generic") && !str2.startsWith("unknown")) {
                            String str3 = Build.HARDWARE;
                            if (!str3.contains("goldfish") && !str3.contains("ranchu")) {
                                String str4 = Build.MODEL;
                                if (!str4.contains("google_sdk") && !str4.contains("Emulator") && !str4.contains("Android SDK built for x86") && !Build.MANUFACTURER.contains("Genymotion")) {
                                    String str5 = Build.PRODUCT;
                                    if (!str5.contains("sdk_google") && !str5.contains("google_sdk") && !str5.contains("sdk") && !str5.contains("sdk_x86") && !str5.contains("vbox86p") && !str5.contains("emulator") && !str5.contains("simulator")) {
                                        z2 = false;
                                    }
                                }
                            }
                        }
                    }
                } catch (Throwable th3) {
                    Log.e("Utils", "isEmulator", th3);
                }
                kVar.d(Boolean.valueOf(z2));
                return;
            case "VideoOutputManager.Create":
                long j4 = Long.parseLong((String) aVar.x("handle"));
                f fVar3 = this.f152e;
                a aVar2 = new a(this, j4);
                synchronized (fVar3.f166f) {
                    try {
                        Locale locale3 = Locale.ENGLISH;
                        Log.i("VideoOutputManager", "com.alexmercerind.media_kit_video.VideoOutputManager.create: " + j4);
                        if (!((HashMap) fVar3.f164d).containsKey(Long.valueOf(j4))) {
                            ((HashMap) fVar3.f164d).put(Long.valueOf(j4), new e((j) fVar3.f165e, aVar2));
                        }
                    } catch (Throwable th4) {
                        throw th4;
                    }
                    break;
                }
                kVar.d(null);
                return;
            default:
                kVar.b();
                return;
        }
    }

    @Override // V.b
    public final void onAttachedToEngine(V.a aVar) {
        H.e eVar = new H.e(aVar.f517c, "com.alexmercerind/media_kit_video");
        this.f151d = eVar;
        eVar.j(this);
        j jVar = aVar.f518d;
        f fVar = new f();
        fVar.f164d = new HashMap();
        fVar.f166f = new Object();
        fVar.f165e = jVar;
        this.f152e = fVar;
    }

    @Override // V.b
    public final void onDetachedFromEngine(V.a aVar) {
        this.f151d.j(null);
    }
}
