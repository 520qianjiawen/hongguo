package p004c0;

import P.d;
import V.b;
import Y.k;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class a implements b, W.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final PackageManager f665d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public W.b f666e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public HashMap f667f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HashMap f668g = new HashMap();

    public a(H.a aVar) {
        this.f665d = (PackageManager) aVar.f170e;
        aVar.f171f = this;
    }

    public final void a(String str, String str2, boolean z2, k kVar) {
        if (this.f666e == null) {
            kVar.a("error", "Plugin not bound to an Activity", null);
            return;
        }
        HashMap map = this.f667f;
        if (map == null) {
            kVar.a("error", "Can not process text actions before calling queryTextActions", null);
            return;
        }
        ResolveInfo resolveInfo = (ResolveInfo) map.get(str);
        if (resolveInfo == null) {
            kVar.a("error", "Text processing activity not found", null);
            return;
        }
        int iHashCode = kVar.hashCode();
        this.f668g.put(Integer.valueOf(iHashCode), kVar);
        Intent intent = new Intent();
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        intent.setClassName(activityInfo.packageName, activityInfo.name);
        intent.setAction("android.intent.action.PROCESS_TEXT");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.PROCESS_TEXT", str2);
        intent.putExtra("android.intent.extra.PROCESS_TEXT_READONLY", z2);
        ((d) this.f666e).f414a.startActivityForResult(intent, iHashCode);
    }

    public final HashMap b() {
        HashMap map = this.f667f;
        PackageManager packageManager = this.f665d;
        if (map == null) {
            this.f667f = new HashMap();
            Intent type = new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
            for (ResolveInfo resolveInfo : Build.VERSION.SDK_INT >= 33 ? packageManager.queryIntentActivities(type, PackageManager.ResolveInfoFlags.of(0L)) : packageManager.queryIntentActivities(type, 0)) {
                this.f667f.put(resolveInfo.activityInfo.name, resolveInfo);
            }
        }
        HashMap map2 = new HashMap();
        for (String str : this.f667f.keySet()) {
            map2.put(str, ((ResolveInfo) this.f667f.get(str)).loadLabel(packageManager).toString());
        }
        return map2;
    }

    @Override // W.a
    public final void onAttachedToActivity(W.b bVar) {
        this.f666e = bVar;
        ((d) bVar).f416c.add(this);
    }

    @Override // W.a
    public final void onDetachedFromActivity() {
        ((d) this.f666e).f416c.remove(this);
        this.f666e = null;
    }

    @Override // W.a
    public final void onDetachedFromActivityForConfigChanges() {
        ((d) this.f666e).f416c.remove(this);
        this.f666e = null;
    }

    @Override // W.a
    public final void onReattachedToActivityForConfigChanges(W.b bVar) {
        this.f666e = bVar;
        ((d) bVar).f416c.add(this);
    }

    @Override // V.b
    public final void onAttachedToEngine(V.a aVar) {
    }

    @Override // V.b
    public final void onDetachedFromEngine(V.a aVar) {
    }
}
