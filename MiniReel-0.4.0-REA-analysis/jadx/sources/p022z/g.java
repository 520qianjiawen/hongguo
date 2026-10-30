package p022z;

import C0.z;
import android.graphics.Rect;
import androidx.window.sidecar.SidecarDeviceState;
import androidx.window.sidecar.SidecarDisplayFeature;
import androidx.window.sidecar.SidecarWindowLayoutInfo;
import g0.m;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p0.h;
import p019t.a;
import p020w.b;
import p020w.c;
import p020w.k;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f1400b = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1401a;

    public g() {
        z.g("verificationMode", 3);
        this.f1401a = 3;
    }

    public static boolean a(SidecarDisplayFeature sidecarDisplayFeature, SidecarDisplayFeature sidecarDisplayFeature2) {
        if (h.a(sidecarDisplayFeature, sidecarDisplayFeature2)) {
            return true;
        }
        if (sidecarDisplayFeature == null || sidecarDisplayFeature2 == null || sidecarDisplayFeature.getType() != sidecarDisplayFeature2.getType()) {
            return false;
        }
        return h.a(sidecarDisplayFeature.getRect(), sidecarDisplayFeature2.getRect());
    }

    public static boolean b(List list, List list2) {
        if (list == list2) {
            return true;
        }
        if (list.size() == list2.size()) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (a((SidecarDisplayFeature) list.get(i2), (SidecarDisplayFeature) list2.get(i2))) {
                }
            }
            return true;
        }
        return false;
    }

    public final ArrayList c(List list, SidecarDeviceState sidecarDeviceState) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            c cVarE = e((SidecarDisplayFeature) it.next(), sidecarDeviceState);
            if (cVarE != null) {
                arrayList.add(cVarE);
            }
        }
        return arrayList;
    }

    public final k d(SidecarWindowLayoutInfo sidecarWindowLayoutInfo, SidecarDeviceState sidecarDeviceState) {
        if (sidecarWindowLayoutInfo == null) {
            return new k(m.f733d);
        }
        SidecarDeviceState sidecarDeviceState2 = new SidecarDeviceState();
        b.d(sidecarDeviceState2, b.b(sidecarDeviceState));
        return new k(c(b.c(sidecarWindowLayoutInfo), sidecarDeviceState2));
    }

    public final c e(SidecarDisplayFeature sidecarDisplayFeature, SidecarDeviceState sidecarDeviceState) {
        b bVar;
        b bVar2 = b.f1276h;
        h.e(sidecarDisplayFeature, "feature");
        int i2 = this.f1401a;
        z.g("verificationMode", i2);
        SidecarDisplayFeature sidecarDisplayFeature2 = (SidecarDisplayFeature) new p019t.g(sidecarDisplayFeature, i2, a.f1237a).K("Type must be either TYPE_FOLD or TYPE_HINGE", c.f1396e).K("Feature bounds must not be 0", d.f1397e).K("TYPE_FOLD must have 0 area", e.f1398e).K("Feature be pinned to either left or top", f.f1399e).i();
        if (sidecarDisplayFeature2 == null) {
            return null;
        }
        int type = sidecarDisplayFeature2.getType();
        if (type == 1) {
            bVar = b.f1278j;
        } else {
            if (type != 2) {
                return null;
            }
            bVar = b.f1279k;
        }
        int iB = b.b(sidecarDeviceState);
        if (iB == 0 || iB == 1) {
            return null;
        }
        if (iB == 2) {
            bVar2 = b.f1277i;
        } else if (iB != 3 && iB == 4) {
            return null;
        }
        Rect rect = sidecarDisplayFeature.getRect();
        h.d(rect, "feature.rect");
        return new c(new p019t.b(rect), bVar, bVar2);
    }
}
