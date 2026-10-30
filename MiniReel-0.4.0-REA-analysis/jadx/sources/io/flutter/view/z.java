package io.flutter.view;

import android.hardware.display.DisplayManager;

/* JADX INFO: loaded from: classes.dex */
public final class z implements DisplayManager.DisplayListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DisplayManager f1067a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ B f1068b;

    public z(B b2, DisplayManager displayManager) {
        this.f1068b = b2;
        this.f1067a = displayManager;
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayAdded(int i2) {
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayChanged(int i2) {
        if (i2 == 0) {
            float refreshRate = this.f1067a.getDisplay(0).getRefreshRate();
            B b2 = this.f1068b;
            b2.f926a = (long) (1.0E9d / ((double) refreshRate));
            b2.f927b.setRefreshRateFPS(refreshRate);
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayRemoved(int i2) {
    }
}
