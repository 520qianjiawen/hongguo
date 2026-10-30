package p014o;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.profileinstaller.ProfileInstallerInitializer;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1184d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Context f1185e;

    public /* synthetic */ g(Context context, int i2) {
        this.f1184d = i2;
        this.f1185e = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1184d) {
            case 0:
                (Build.VERSION.SDK_INT >= 28 ? k.a(Looper.getMainLooper()) : new Handler(Looper.getMainLooper())).postDelayed(new g(this.f1185e, 1), new Random().nextInt(Math.max(1000, 1)) + 5000);
                break;
            case 1:
                new ThreadPoolExecutor(0, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue()).execute(new g(this.f1185e, 2));
                break;
            default:
                f.s(this.f1185e, new d(), f.f1174a, false);
                break;
        }
    }

    public /* synthetic */ g(ProfileInstallerInitializer profileInstallerInitializer, Context context) {
        this.f1184d = 0;
        this.f1185e = context;
    }
}
