package io.flutter.view;

import android.graphics.SurfaceTexture;

/* JADX INFO: loaded from: classes.dex */
public interface TextureRegistry$SurfaceTextureEntry {
    /* synthetic */ long id();

    /* synthetic */ void release();

    default void setOnFrameConsumedListener(w wVar) {
    }

    default void setOnTrimMemoryListener(x xVar) {
    }

    SurfaceTexture surfaceTexture();
}
