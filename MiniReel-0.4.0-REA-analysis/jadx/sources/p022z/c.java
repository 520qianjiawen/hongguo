package p022z;

import androidx.window.sidecar.SidecarDisplayFeature;
import o0.l;
import p0.h;
import p0.i;

/* JADX INFO: loaded from: classes.dex */
public final class c extends i implements l {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c f1396e = new c(1);

    @Override // o0.l
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final Boolean h(SidecarDisplayFeature sidecarDisplayFeature) {
        h.e(sidecarDisplayFeature, "$this$require");
        boolean z2 = true;
        if (sidecarDisplayFeature.getType() != 1 && sidecarDisplayFeature.getType() != 2) {
            z2 = false;
        }
        return Boolean.valueOf(z2);
    }
}
