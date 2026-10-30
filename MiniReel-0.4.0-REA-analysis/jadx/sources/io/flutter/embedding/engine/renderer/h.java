package io.flutter.embedding.engine.renderer;

import io.flutter.embedding.engine.FlutterJNI;

/* JADX INFO: loaded from: classes.dex */
public final class h implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f767d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final FlutterJNI f768e;

    public h(long j2, FlutterJNI flutterJNI) {
        this.f767d = j2;
        this.f768e = flutterJNI;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FlutterJNI flutterJNI = this.f768e;
        if (flutterJNI.isAttached()) {
            flutterJNI.unregisterTexture(this.f767d);
        }
    }
}
