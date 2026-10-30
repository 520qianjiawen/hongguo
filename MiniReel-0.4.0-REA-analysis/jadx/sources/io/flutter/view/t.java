package io.flutter.view;

import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: classes.dex */
public final class t extends H.b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f1058e;

    public t(String str) {
        super(11);
        this.f1058e = str;
    }

    @Override // H.b
    public final void b(AccessibilityNodeInfo accessibilityNodeInfo, m mVar) {
        accessibilityNodeInfo.setClassName(this.f1058e);
    }
}
