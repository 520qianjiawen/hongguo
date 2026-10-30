package p022z;

import androidx.window.sidecar.SidecarDisplayFeature;
import o0.l;
import p0.h;
import p0.i;

/* JADX INFO: loaded from: classes.dex */
public final class e extends i implements l {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final e f1398e = new e(1);

    @Override // o0.l
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final Boolean h(SidecarDisplayFeature sidecarDisplayFeature) {
        h.e(sidecarDisplayFeature, "$this$require");
        boolean z2 = true;
        if (sidecarDisplayFeature.getType() == 1 && sidecarDisplayFeature.getRect().width() != 0 && sidecarDisplayFeature.getRect().height() != 0) {
            z2 = false;
        }
        return Boolean.valueOf(z2);
    }
}
