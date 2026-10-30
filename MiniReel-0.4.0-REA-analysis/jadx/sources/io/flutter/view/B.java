package io.flutter.view;

import android.hardware.display.DisplayManager;
import io.flutter.embedding.engine.FlutterJNI;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class B {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static B f924e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static z f925f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final FlutterJNI f927b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f926a = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public A f928c = new A(this, 0);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c f929d = new c(this);

    public B(FlutterJNI flutterJNI) {
        this.f927b = flutterJNI;
    }

    public static B a(DisplayManager displayManager, FlutterJNI flutterJNI) {
        if (f924e == null) {
            f924e = new B(flutterJNI);
        }
        if (f925f == null) {
            B b2 = f924e;
            Objects.requireNonNull(b2);
            z zVar = new z(b2, displayManager);
            f925f = zVar;
            displayManager.registerDisplayListener(zVar, null);
        }
        if (f924e.f926a == -1) {
            float refreshRate = displayManager.getDisplay(0).getRefreshRate();
            f924e.f926a = (long) (1.0E9d / ((double) refreshRate));
            flutterJNI.setRefreshRateFPS(refreshRate);
        }
        return f924e;
    }
}
