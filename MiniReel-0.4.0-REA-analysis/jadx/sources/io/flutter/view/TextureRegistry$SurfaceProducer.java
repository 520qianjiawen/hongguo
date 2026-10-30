package io.flutter.view;

import android.view.Surface;

/* JADX INFO: loaded from: classes.dex */
public interface TextureRegistry$SurfaceProducer {
    Surface getForcedNewSurface();

    int getHeight();

    Surface getSurface();

    int getWidth();

    boolean handlesCropAndRotation();

    /* synthetic */ long id();

    /* synthetic */ void release();

    void scheduleFrame();

    void setCallback(y yVar);

    void setSize(int i2, int i3);
}
