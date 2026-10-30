package io.flutter.plugins;

import D.b;
import F.a;
import J.s;
import P.c;
import android.util.Log;
import com.github.dart_lang.jni.JniPlugin;
import com.github.dart_lang.jni_flutter.JniFlutterPlugin;

/* JADX INFO: loaded from: classes.dex */
public final class GeneratedPluginRegistrant {
    private static final String TAG = "GeneratedPluginRegistrant";

    public static void registerWith(c cVar) {
        try {
            cVar.f395c.a(new JniPlugin());
        } catch (Exception e2) {
            Log.e(TAG, "Error registering plugin jni, com.github.dart_lang.jni.JniPlugin", e2);
        }
        try {
            cVar.f395c.a(new JniFlutterPlugin());
        } catch (Exception e3) {
            Log.e(TAG, "Error registering plugin jni_flutter, com.github.dart_lang.jni_flutter.JniFlutterPlugin", e3);
        }
        try {
            cVar.f395c.a(new a());
        } catch (Exception e4) {
            Log.e(TAG, "Error registering plugin media_kit_libs_android_video, com.alexmercerind.media_kit_libs_android_video.MediaKitLibsAndroidVideoPlugin", e4);
        }
        try {
            cVar.f395c.a(new G.c());
        } catch (Exception e5) {
            Log.e(TAG, "Error registering plugin media_kit_video, com.alexmercerind.media_kit_video.MediaKitVideoPlugin", e5);
        }
        try {
            cVar.f395c.a(new L.a());
        } catch (Exception e6) {
            Log.e(TAG, "Error registering plugin package_info_plus, dev.fluttercommunity.plus.packageinfo.PackageInfoPlugin", e6);
        }
        try {
            cVar.f395c.a(new b());
        } catch (Exception e7) {
            Log.e(TAG, "Error registering plugin screen_brightness_android, com.aaassseee.screen_brightness_android.ScreenBrightnessAndroidPlugin", e7);
        }
        try {
            cVar.f395c.a(new s());
        } catch (Exception e8) {
            Log.e(TAG, "Error registering plugin sqflite_android, com.tekartik.sqflite.SqflitePlugin", e8);
        }
        try {
            cVar.f395c.a(new I.b());
        } catch (Exception e9) {
            Log.e(TAG, "Error registering plugin volume_controller, com.kurenai7968.volume_controller.VolumeControllerPlugin", e9);
        }
        try {
            cVar.f395c.a(new M.b());
        } catch (Exception e10) {
            Log.e(TAG, "Error registering plugin wakelock_plus, dev.fluttercommunity.plus.wakelock.WakelockPlusPlugin", e10);
        }
    }
}
