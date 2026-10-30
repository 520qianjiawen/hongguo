package io.flutter.view;

import android.provider.Settings;

/* JADX INFO: loaded from: classes.dex */
public final class k extends f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f965c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f966d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ n f967e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(n nVar) {
        super(nVar, 3);
        this.f967e = nVar;
        this.f965c = "transition_animation_scale";
        this.f966d = 1.0f;
    }

    @Override // io.flutter.view.f
    public final boolean a() {
        return Settings.Global.getFloat(this.f967e.f1028f, this.f965c, this.f966d) == 0.0f;
    }
}
