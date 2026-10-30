package io.flutter.plugin.platform;

import android.view.View;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class d implements View.OnSystemUiVisibilityChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ View f872a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ e f873b;

    public d(e eVar, View view) {
        this.f873b = eVar;
        this.f872a = view;
    }

    @Override // android.view.View.OnSystemUiVisibilityChangeListener
    public final void onSystemUiVisibilityChange(final int i2) {
        this.f872a.post(new Runnable() { // from class: io.flutter.plugin.platform.c
            @Override // java.lang.Runnable
            public final void run() {
                H.a aVar = this.f870d.f873b.f875b;
                if ((i2 & 4) == 0) {
                    ((H.e) aVar.f170e).h("SystemChrome.systemUIChange", Arrays.asList(Boolean.TRUE), null);
                } else {
                    ((H.e) aVar.f170e).h("SystemChrome.systemUIChange", Arrays.asList(Boolean.FALSE), null);
                }
            }
        });
    }
}
