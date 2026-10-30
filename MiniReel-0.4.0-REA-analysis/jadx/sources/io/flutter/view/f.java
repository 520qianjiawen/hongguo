package io.flutter.view;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public abstract class f extends ContentObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f935a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ n f936b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(n nVar, int i2) {
        super(new Handler());
        this.f936b = nVar;
        this.f935a = i2;
    }

    public abstract boolean a();

    @Override // android.database.ContentObserver
    public final void onChange(boolean z2, Uri uri) {
        n nVar = this.f936b;
        if (nVar.f1041t) {
            return;
        }
        nVar.m(this.f935a, a());
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z2) {
        onChange(z2, null);
    }
}
