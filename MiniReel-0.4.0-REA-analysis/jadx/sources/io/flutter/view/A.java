package io.flutter.view;

import android.view.Choreographer;

/* JADX INFO: loaded from: classes.dex */
public final class A implements Choreographer.FrameCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f922a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ B f923b;

    public A(B b2, long j2) {
        this.f923b = b2;
        this.f922a = j2;
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j2) {
        long jNanoTime = System.nanoTime() - j2;
        long j3 = jNanoTime < 0 ? 0L : jNanoTime;
        B b2 = this.f923b;
        b2.f927b.onVsync(j3, b2.f926a, this.f922a);
        b2.f928c = this;
    }
}
