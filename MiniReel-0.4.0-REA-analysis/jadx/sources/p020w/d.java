package p020w;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import androidx.window.extensions.core.util.function.Consumer;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import o0.a;
import p0.h;
import p0.i;
import p0.n;

/* JADX INFO: loaded from: classes.dex */
public final class d extends i implements a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1285e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ e f1286f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d(e eVar, int i2) {
        super(0);
        this.f1285e = i2;
        this.f1286f = eVar;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0092  */
    /* JADX WARN: Code duplicated, block: B:31:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:47:0x0163  */
    @Override // o0.a
    public final Object a() throws NoSuchMethodException, ClassNotFoundException {
        boolean z2;
        Class clsB;
        boolean z3;
        boolean z4;
        switch (this.f1285e) {
            case 0:
                Class<?> clsLoadClass = this.f1286f.f1287a.loadClass("androidx.window.extensions.layout.FoldingFeature");
                h.d(clsLoadClass, "loader.loadClass(FOLDING_FEATURE_CLASS)");
                Method method = clsLoadClass.getMethod("getBounds", null);
                Method method2 = clsLoadClass.getMethod("getType", null);
                Method method3 = clsLoadClass.getMethod("getState", null);
                h.d(method, "getBoundsMethod");
                if (p000a.a.n(method, n.a(Rect.class)) && Modifier.isPublic(method.getModifiers())) {
                    h.d(method2, "getTypeMethod");
                    Class cls = Integer.TYPE;
                    if (p000a.a.n(method2, n.a(cls)) && Modifier.isPublic(method2.getModifiers())) {
                        h.d(method3, "getStateMethod");
                        if (p000a.a.n(method3, n.a(cls)) && Modifier.isPublic(method3.getModifiers())) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                    } else {
                        z2 = false;
                    }
                } else {
                    z2 = false;
                }
                return Boolean.valueOf(z2);
            case 1:
                e eVar = this.f1286f;
                try {
                    clsB = eVar.f1288b.b();
                    break;
                } catch (ClassNotFoundException unused) {
                    clsB = null;
                }
                if (clsB == null) {
                    return Boolean.FALSE;
                }
                Class<?> clsLoadClass2 = eVar.f1287a.loadClass("androidx.window.extensions.layout.WindowLayoutComponent");
                h.d(clsLoadClass2, "loader.loadClass(WINDOW_LAYOUT_COMPONENT_CLASS)");
                Method method4 = clsLoadClass2.getMethod("addWindowLayoutInfoListener", Activity.class, clsB);
                Method method5 = clsLoadClass2.getMethod("removeWindowLayoutInfoListener", clsB);
                h.d(method4, "addListenerMethod");
                if (Modifier.isPublic(method4.getModifiers())) {
                    h.d(method5, "removeListenerMethod");
                    if (Modifier.isPublic(method5.getModifiers())) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                } else {
                    z3 = false;
                }
                return Boolean.valueOf(z3);
            case 2:
                Class<?> clsLoadClass3 = this.f1286f.f1287a.loadClass("androidx.window.extensions.layout.WindowLayoutComponent");
                h.d(clsLoadClass3, "loader.loadClass(WINDOW_LAYOUT_COMPONENT_CLASS)");
                Method method6 = clsLoadClass3.getMethod("addWindowLayoutInfoListener", Context.class, Consumer.class);
                Method method7 = clsLoadClass3.getMethod("removeWindowLayoutInfoListener", Consumer.class);
                h.d(method6, "addListenerMethod");
                if (Modifier.isPublic(method6.getModifiers())) {
                    h.d(method7, "removeListenerMethod");
                    if (Modifier.isPublic(method7.getModifiers())) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                } else {
                    z4 = false;
                }
                return Boolean.valueOf(z4);
            default:
                e eVar2 = this.f1286f;
                Class<?> clsLoadClass4 = eVar2.f1289c.f1236a.loadClass("androidx.window.extensions.WindowExtensions");
                h.d(clsLoadClass4, "loader.loadClass(WindowE….WINDOW_EXTENSIONS_CLASS)");
                Method method8 = clsLoadClass4.getMethod("getWindowLayoutComponent", null);
                Class<?> clsLoadClass5 = eVar2.f1287a.loadClass("androidx.window.extensions.layout.WindowLayoutComponent");
                h.d(clsLoadClass5, "loader.loadClass(WINDOW_LAYOUT_COMPONENT_CLASS)");
                h.d(method8, "getWindowLayoutComponentMethod");
                return Boolean.valueOf(Modifier.isPublic(method8.getModifiers()) && method8.getReturnType().equals(clsLoadClass5));
        }
    }
}
