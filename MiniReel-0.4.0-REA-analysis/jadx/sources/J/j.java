package J;

import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f208a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f209b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public HandlerThread f210c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Handler f211d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public i f212e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public h f213f;

    public j(String str, int i2) {
        this.f208a = str;
        this.f209b = i2;
    }

    public final synchronized void a(i iVar) {
        HandlerThread handlerThread = new HandlerThread(this.f208a, this.f209b);
        this.f210c = handlerThread;
        handlerThread.start();
        this.f211d = new Handler(this.f210c.getLooper());
        this.f212e = iVar;
    }
}
