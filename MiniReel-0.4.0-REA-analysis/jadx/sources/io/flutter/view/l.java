package io.flutter.view;

import android.provider.Settings;

/* JADX INFO: loaded from: classes.dex */
public final class l extends f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f968c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ n f969d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(n nVar) {
        super(nVar, 2);
        this.f969d = nVar;
        this.f968c = "accessibility_display_inversion_enabled";
    }

    @Override // io.flutter.view.f
    public final boolean a() {
        try {
            return Settings.Secure.getInt(this.f969d.f1028f, this.f968c) == 1;
        } catch (Settings.SettingNotFoundException unused) {
        }
    }
}
