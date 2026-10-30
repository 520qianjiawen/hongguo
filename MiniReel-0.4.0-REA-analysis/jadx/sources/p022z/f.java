package p022z;

import androidx.window.sidecar.SidecarDisplayFeature;
import o0.l;
import p0.h;
import p0.i;

/* JADX INFO: loaded from: classes.dex */
public final class f extends i implements l {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f f1399e = new f(1);

    @Override // o0.l
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final Boolean h(SidecarDisplayFeature sidecarDisplayFeature) {
        h.e(sidecarDisplayFeature, "$this$require");
        return Boolean.valueOf(sidecarDisplayFeature.getRect().left == 0 || sidecarDisplayFeature.getRect().top == 0);
    }
}
