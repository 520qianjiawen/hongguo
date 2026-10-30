package p020w;

import android.app.Activity;
import android.content.Context;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.layout.WindowLayoutComponent;
import p0.h;
import p000a.a;
import p018s.b;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ClassLoader f1287a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f1288b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f1289c;

    public e(ClassLoader classLoader, b bVar) {
        this.f1287a = classLoader;
        this.f1288b = bVar;
        this.f1289c = new b(classLoader);
    }

    public final WindowLayoutComponent a() {
        b bVar = this.f1289c;
        bVar.getClass();
        boolean zB = false;
        try {
            h.d(bVar.f1236a.loadClass("androidx.window.extensions.WindowExtensionsProvider"), "loader.loadClass(WindowE…XTENSIONS_PROVIDER_CLASS)");
            if (a.R("WindowExtensionsProvider#getWindowExtensions is not valid", new p018s.a(0, bVar)) && a.R("WindowExtensions#getWindowLayoutComponent is not valid", new d(this, 3)) && a.R("FoldingFeature class is not valid", new d(this, 0))) {
                int iA = p019t.e.a();
                if (iA == 1) {
                    zB = b();
                } else if (2 <= iA && iA <= Integer.MAX_VALUE && b()) {
                    if (a.R("WindowLayoutComponent#addWindowLayoutInfoListener(" + Context.class.getName() + ", androidx.window.extensions.core.util.function.Consumer) is not valid", new d(this, 2))) {
                        zB = true;
                    }
                }
            }
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
        }
        if (!zB) {
            return null;
        }
        try {
            return WindowExtensionsProvider.getWindowExtensions().getWindowLayoutComponent();
        } catch (UnsupportedOperationException unused2) {
            return null;
        }
    }

    public final boolean b() {
        return a.R("WindowLayoutComponent#addWindowLayoutInfoListener(" + Activity.class.getName() + ", java.util.function.Consumer) is not valid", new d(this, 1));
    }
}
