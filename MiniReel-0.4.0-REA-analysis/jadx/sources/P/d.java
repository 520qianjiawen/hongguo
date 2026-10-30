package P;

import android.app.Activity;
import android.content.Intent;
import io.flutter.embedding.engine.plugins.lifecycle.HiddenLifecycleReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class d implements W.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Activity f414a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashSet f415b = new HashSet();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashSet f416c = new HashSet();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashSet f417d = new HashSet();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashSet f418e = new HashSet();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final HashSet f419f;

    public d(Activity activity, androidx.lifecycle.f fVar) {
        new HashSet();
        this.f419f = new HashSet();
        this.f414a = activity;
        new HiddenLifecycleReference(fVar);
    }

    public final boolean a(int i2, int i3, Intent intent) {
        Iterator it = new HashSet(this.f416c).iterator();
        while (true) {
            boolean z2 = false;
            while (it.hasNext()) {
                HashMap map = ((p004c0.a) it.next()).f668g;
                if (map.containsKey(Integer.valueOf(i2))) {
                    ((Y.k) map.remove(Integer.valueOf(i2))).d(i3 == -1 ? intent.getStringExtra("android.intent.extra.PROCESS_TEXT") : null);
                } else if (z2) {
                }
                z2 = true;
            }
            return z2;
        }
    }
}
