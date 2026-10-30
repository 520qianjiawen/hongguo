package I;

import G.f;
import H.e;
import Y.k;
import Z.m;
import android.content.Context;
import android.media.AudioManager;
import p0.h;

/* JADX INFO: loaded from: classes.dex */
public final class b implements V.b, m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public H.a f180d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public e f181e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public f f182f;

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // Z.m
    public final void m(H.a aVar, k kVar) {
        h.e(aVar, "call");
        String str = (String) aVar.f170e;
        if (str != null) {
            switch (str.hashCode()) {
                case 670514716:
                    if (str.equals("setVolume")) {
                        Double d2 = (Double) aVar.x("volume");
                        Boolean bool = (Boolean) aVar.x("showSystemUI");
                        if (d2 == null || bool == null) {
                            kVar.a("invalid_arguments", "Missing or invalid method arguments.", null);
                            return;
                        }
                        H.a aVar2 = this.f180d;
                        if (aVar2 == null) {
                            h.g("volumeController");
                            throw null;
                        }
                        aVar2.z(d2.doubleValue(), bool.booleanValue());
                        kVar.d(null);
                        return;
                    }
                    break;
                case 885131792:
                    if (str.equals("getVolume")) {
                        H.a aVar3 = this.f180d;
                        if (aVar3 != null) {
                            kVar.d(Double.valueOf(p000a.a.v((AudioManager) aVar3.f170e)));
                            return;
                        } else {
                            h.g("volumeController");
                            throw null;
                        }
                    }
                    break;
                case 1984790939:
                    if (str.equals("setMute")) {
                        Boolean bool2 = (Boolean) aVar.x("isMute");
                        Boolean bool3 = (Boolean) aVar.x("showSystemUI");
                        if (bool2 == null || bool3 == null) {
                            kVar.a("invalid_arguments", "Missing or invalid method arguments.", null);
                            return;
                        }
                        H.a aVar4 = this.f180d;
                        if (aVar4 == null) {
                            h.g("volumeController");
                            throw null;
                        }
                        boolean zBooleanValue = bool2.booleanValue();
                        boolean zBooleanValue2 = bool3.booleanValue();
                        if (zBooleanValue) {
                            aVar4.f171f = Double.valueOf(p000a.a.v((AudioManager) aVar4.f170e));
                            aVar4.z(0.0d, zBooleanValue2);
                        } else {
                            Double d3 = (Double) aVar4.f171f;
                            if (d3 != null) {
                                aVar4.z(d3.doubleValue(), zBooleanValue2);
                                aVar4.f171f = null;
                            }
                        }
                        kVar.d(null);
                        return;
                    }
                    break;
                case 2065669729:
                    if (str.equals("isMuted")) {
                        H.a aVar5 = this.f180d;
                        if (aVar5 != null) {
                            kVar.d(Boolean.valueOf(p000a.a.v((AudioManager) aVar5.f170e) == 0.0d));
                            return;
                        } else {
                            h.g("volumeController");
                            throw null;
                        }
                    }
                    break;
            }
        }
        kVar.b();
    }

    @Override // V.b
    public final void onAttachedToEngine(V.a aVar) {
        h.e(aVar, "flutterPluginBinding");
        Context context = aVar.f515a;
        h.d(context, "getApplicationContext(...)");
        Object systemService = context.getSystemService("audio");
        h.c(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        AudioManager audioManager = (AudioManager) systemService;
        h.e(context, "context");
        f fVar = new f();
        fVar.f164d = context;
        fVar.f165e = audioManager;
        this.f180d = new H.a(audioManager);
        Z.f fVar2 = aVar.f517c;
        f fVar3 = new f(fVar2, "com.kurenai7968.volume_controller.volume_listener_event");
        this.f182f = fVar3;
        fVar3.l(fVar);
        e eVar = new e(fVar2, "com.kurenai7968.volume_controller.method");
        this.f181e = eVar;
        eVar.j(this);
    }

    @Override // V.b
    public final void onDetachedFromEngine(V.a aVar) {
        h.e(aVar, "binding");
        e eVar = this.f181e;
        if (eVar == null) {
            h.g("methodChannel");
            throw null;
        }
        eVar.j(null);
        f fVar = this.f182f;
        if (fVar != null) {
            fVar.l(null);
        } else {
            h.g("eventChannel");
            throw null;
        }
    }
}
