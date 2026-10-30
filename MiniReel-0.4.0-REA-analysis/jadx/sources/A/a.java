package A;

import android.view.WindowInsetsAnimation;
import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class a {
    public static /* bridge */ /* synthetic */ WindowInsetsAnimation i(Object obj) {
        return (WindowInsetsAnimation) obj;
    }

    public static /* synthetic */ AccessibilityNodeInfo.CollectionInfo l(int i2) {
        return new AccessibilityNodeInfo.CollectionInfo(i2, 1, false);
    }

    public static /* synthetic */ AccessibilityNodeInfo.CollectionItemInfo m(int i2, boolean z2) {
        return new AccessibilityNodeInfo.CollectionItemInfo(i2, 1, 0, 1, z2);
    }

    public static /* synthetic */ AccessibilityNodeInfo.CollectionInfo v(int i2) {
        return new AccessibilityNodeInfo.CollectionInfo(1, i2, false);
    }

    public static /* synthetic */ AccessibilityNodeInfo.CollectionItemInfo w(int i2, boolean z2) {
        return new AccessibilityNodeInfo.CollectionItemInfo(0, 1, i2, 1, z2);
    }
}
