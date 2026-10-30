package io.flutter.view;

import android.content.res.Configuration;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {
    public static boolean a(Configuration configuration) {
        int i2 = configuration.fontWeightAdjustment;
        return i2 != Integer.MAX_VALUE && i2 >= 300;
    }
}
