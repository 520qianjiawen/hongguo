package io.flutter.view;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: classes.dex */
public final class u extends H.b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1059e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ u(int i2) {
        super(11);
        this.f1059e = i2;
    }

    @Override // H.b
    public final void b(AccessibilityNodeInfo accessibilityNodeInfo, m mVar) {
        float f2;
        float f3;
        switch (this.f1059e) {
            case 0:
                accessibilityNodeInfo.setClassName("android.widget.Spinner");
                accessibilityNodeInfo.setCanOpenPopup(true);
                break;
            case 1:
                accessibilityNodeInfo.setClassName("android.widget.Spinner");
                accessibilityNodeInfo.setCanOpenPopup(true);
                break;
            default:
                accessibilityNodeInfo.setClassName("android.widget.ProgressBar");
                if (mVar.f1013r != null) {
                    String str = mVar.f971C;
                    if (str != null) {
                        try {
                            f2 = Float.parseFloat(str);
                        } catch (NumberFormatException unused) {
                            f2 = Float.NEGATIVE_INFINITY;
                        }
                    } else {
                        f2 = Float.NEGATIVE_INFINITY;
                    }
                    String str2 = mVar.f972D;
                    if (str2 != null) {
                        try {
                            f3 = Float.parseFloat(str2);
                        } catch (NumberFormatException unused2) {
                            f3 = Float.POSITIVE_INFINITY;
                        }
                    } else {
                        f3 = Float.POSITIVE_INFINITY;
                    }
                    try {
                        accessibilityNodeInfo.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(1, f2, f3, Float.parseFloat(mVar.f1013r)));
                        break;
                    } catch (NumberFormatException unused3) {
                        if (Build.VERSION.SDK_INT >= 36) {
                            accessibilityNodeInfo.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(3, 0.0f, 0.0f, 0.0f));
                            return;
                        }
                        accessibilityNodeInfo.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(1, 0.0f, 0.0f, 0.0f));
                    }
                }
                break;
        }
    }
}
