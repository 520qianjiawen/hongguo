package I;

import Z.g;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import p0.h;

/* JADX INFO: loaded from: classes.dex */
public final class a extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f178a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AudioManager f179b;

    public a(g gVar, AudioManager audioManager) {
        this.f178a = gVar;
        this.f179b = audioManager;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        h.e(context, "context");
        g gVar = this.f178a;
        if (gVar != null) {
            gVar.a(Double.valueOf(p000a.a.v(this.f179b)));
        }
    }
}
