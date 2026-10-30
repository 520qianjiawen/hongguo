package defpackage;

import H.a;
import M.b;
import Z.f;
import Z.l;
import android.app.Activity;
import android.util.Log;
import android.view.Window;
import java.util.List;
import p0.h;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ e f674a = new e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f0.e f675b = new f0.e(new c());

    public static void a(e eVar, f fVar, final b bVar) {
        eVar.getClass();
        h.e(fVar, "binaryMessenger");
        String strConcat = "".length() > 0 ? ".".concat("") : "";
        f0.e eVar2 = f675b;
        G.f fVar2 = new G.f(fVar, "dev.flutter.pigeon.wakelock_plus_platform_interface.WakelockPlusApi.toggle" + strConcat, (l) eVar2.a());
        if (bVar != null) {
            final int i2 = 0;
            fVar2.i(new Z.b() { // from class: d
                @Override // Z.b
                public final void g(Object obj, a aVar) {
                    List listT;
                    Window window;
                    List listT2;
                    switch (i2) {
                        case 0:
                            f fVar3 = bVar;
                            h.c(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
                            Object obj2 = ((List) obj).get(0);
                            h.c(obj2, "null cannot be cast to non-null type <root>.ToggleMessage");
                            b bVar2 = (b) obj2;
                            try {
                                b bVar3 = (b) fVar3;
                                bVar3.getClass();
                                M.a aVar2 = bVar3.f269d;
                                h.b(aVar2);
                                Boolean bool = bVar2.f659a;
                                h.b(bool);
                                aVar2.f267a = bool.booleanValue();
                                Activity activity = (Activity) aVar2.f268b;
                                if (activity != null && (window = activity.getWindow()) != null) {
                                    if (aVar2.f267a) {
                                        window.addFlags(128);
                                    } else {
                                        window.clearFlags(128);
                                    }
                                }
                                listT = p000a.a.A(null);
                            } catch (Throwable th) {
                                listT = g0.f.T(th.getClass().getSimpleName(), th.toString(), "Cause: " + th.getCause() + ", Stacktrace: " + Log.getStackTraceString(th));
                            }
                            aVar.o(listT);
                            break;
                        default:
                            try {
                                M.a aVar3 = ((b) bVar).f269d;
                                h.b(aVar3);
                                listT2 = p000a.a.A(new a(Boolean.valueOf(aVar3.f267a)));
                            } catch (Throwable th2) {
                                listT2 = g0.f.T(th2.getClass().getSimpleName(), th2.toString(), "Cause: " + th2.getCause() + ", Stacktrace: " + Log.getStackTraceString(th2));
                            }
                            aVar.o(listT2);
                            break;
                    }
                }
            });
        } else {
            fVar2.i(null);
        }
        G.f fVar3 = new G.f(fVar, "dev.flutter.pigeon.wakelock_plus_platform_interface.WakelockPlusApi.isEnabled" + strConcat, (l) eVar2.a());
        if (bVar == null) {
            fVar3.i(null);
        } else {
            final int i3 = 1;
            fVar3.i(new Z.b() { // from class: d
                @Override // Z.b
                public final void g(Object obj, a aVar) {
                    List listT;
                    Window window;
                    List listT2;
                    switch (i3) {
                        case 0:
                            f fVar4 = bVar;
                            h.c(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
                            Object obj2 = ((List) obj).get(0);
                            h.c(obj2, "null cannot be cast to non-null type <root>.ToggleMessage");
                            b bVar2 = (b) obj2;
                            try {
                                b bVar3 = (b) fVar4;
                                bVar3.getClass();
                                M.a aVar2 = bVar3.f269d;
                                h.b(aVar2);
                                Boolean bool = bVar2.f659a;
                                h.b(bool);
                                aVar2.f267a = bool.booleanValue();
                                Activity activity = (Activity) aVar2.f268b;
                                if (activity != null && (window = activity.getWindow()) != null) {
                                    if (aVar2.f267a) {
                                        window.addFlags(128);
                                    } else {
                                        window.clearFlags(128);
                                    }
                                }
                                listT = p000a.a.A(null);
                            } catch (Throwable th) {
                                listT = g0.f.T(th.getClass().getSimpleName(), th.toString(), "Cause: " + th.getCause() + ", Stacktrace: " + Log.getStackTraceString(th));
                            }
                            aVar.o(listT);
                            break;
                        default:
                            try {
                                M.a aVar3 = ((b) bVar).f269d;
                                h.b(aVar3);
                                listT2 = p000a.a.A(new a(Boolean.valueOf(aVar3.f267a)));
                            } catch (Throwable th2) {
                                listT2 = g0.f.T(th2.getClass().getSimpleName(), th2.toString(), "Cause: " + th2.getCause() + ", Stacktrace: " + Log.getStackTraceString(th2));
                            }
                            aVar.o(listT2);
                            break;
                    }
                }
            });
        }
    }
}
