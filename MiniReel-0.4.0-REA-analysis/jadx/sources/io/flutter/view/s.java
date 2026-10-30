package io.flutter.view;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f1056a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1057b;

    public s(View view, int i2) {
        this.f1056a = view;
        this.f1057b = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return this.f1057b == sVar.f1057b && this.f1056a.equals(sVar.f1056a);
    }

    public final int hashCode() {
        return ((this.f1056a.hashCode() + 31) * 31) + this.f1057b;
    }
}
