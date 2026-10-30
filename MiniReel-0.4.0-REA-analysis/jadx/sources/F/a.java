package F;

import V.b;
import android.util.Log;
import com.alexmercerind.mediakitandroidhelper.MediaKitAndroidHelper;

/* JADX INFO: loaded from: classes.dex */
public class a implements b {
    static {
        try {
            System.loadLibrary("mpv");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // V.b
    public final void onAttachedToEngine(V.a aVar) {
        Log.i("media_kit", "package:media_kit_libs_android_video attached.");
        try {
            MediaKitAndroidHelper.setApplicationContextJava(aVar.f515a);
            Log.i("media_kit", "Saved application context.");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // V.b
    public final void onDetachedFromEngine(V.a aVar) {
        Log.i("media_kit", "package:media_kit_libs_android_video detached.");
    }
}
