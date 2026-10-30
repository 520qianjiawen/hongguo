package D;

import C0.x;
import O.t;
import Z.g;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import p0.h;
import u0.c;

/* JADX INFO: loaded from: classes.dex */
public final class a extends ContentObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f92a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f93b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(Object obj, Handler handler, int i2) {
        super(handler);
        this.f92a = i2;
        this.f93b = obj;
    }

    @Override // android.database.ContentObserver
    public boolean deliverSelfNotifications() {
        switch (this.f92a) {
            case 1:
                return true;
            default:
                return super.deliverSelfNotifications();
        }
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z2) {
        x xVar;
        g gVar;
        g gVar2;
        switch (this.f92a) {
            case 0:
                super.onChange(z2);
                b bVar = (b) this.f93b;
                Context context = bVar.f96e;
                if (context != null) {
                    try {
                        float f2 = Settings.System.getInt(context.getContentResolver(), "screen_brightness");
                        p002b0.a aVar = bVar.f103l;
                        c[] cVarArr = b.f94q;
                        float fFloatValue = f2 / ((Number) aVar.o(cVarArr[0])).floatValue();
                        p002b0.a aVar2 = bVar.m;
                        c cVar = cVarArr[1];
                        Float fValueOf = Float.valueOf(fFloatValue);
                        aVar2.getClass();
                        h.e(cVar, "property");
                        aVar2.f661e = fValueOf;
                        x xVar2 = bVar.f99h;
                        if (xVar2 != null && (gVar2 = (g) xVar2.f90e) != null) {
                            gVar2.a(Float.valueOf(bVar.b()));
                        }
                        if (bVar.f104n == null && (xVar = bVar.f101j) != null && (gVar = (g) xVar.f90e) != null) {
                            gVar.a(Float.valueOf(bVar.b()));
                            break;
                        }
                    } catch (Settings.SettingNotFoundException e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                break;
            default:
                super.onChange(z2);
                t tVar = (t) this.f93b;
                if (tVar.f360i != null) {
                    tVar.d();
                    break;
                }
                break;
        }
    }
}
