package com.alexmercerind.mediakitandroidhelper;

import android.content.Context;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public class MediaKitAndroidHelper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f669a = 0;
    private static Context applicationContext;

    static {
        System.loadLibrary("mediakitandroidhelper");
        applicationContext = null;
    }

    public static native String copyAssetToFilesDir(String str);

    public static native void deleteGlobalObjectRef(long j2);

    public static native long newGlobalObjectRef(Object obj);

    public static int openFileDescriptorJava(String str) {
        try {
            return applicationContext.getContentResolver().openFileDescriptor(Uri.parse(str), "r").detachFd();
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static native int openFileDescriptorNative(String str);

    public static void setApplicationContextJava(Context context) {
        applicationContext = context;
        setApplicationContextNative(context);
    }

    private static native void setApplicationContextNative(Context context);
}
