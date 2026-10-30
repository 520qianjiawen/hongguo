package io.flutter.embedding.engine.renderer;

import io.flutter.view.x;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f752d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ x f753e;

    public /* synthetic */ b(x xVar, int i2) {
        this.f752d = i2;
        this.f753e = xVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f752d) {
            case 0:
                ((FlutterRenderer$ImageReaderSurfaceProducer) this.f753e).lambda$dequeueImage$0();
                break;
            default:
                ((g) this.f753e).getClass();
                break;
        }
    }
}
