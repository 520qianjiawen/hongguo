package io.flutter.plugin.platform;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.Display;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: loaded from: classes.dex */
public final class m extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InputMethodManager f914a;

    public m(Context context, InputMethodManager inputMethodManager) {
        super(context);
        this.f914a = inputMethodManager == null ? (InputMethodManager) context.getSystemService("input_method") : inputMethodManager;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Context createDisplayContext(Display display) {
        return new m(super.createDisplayContext(display), this.f914a);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Object getSystemService(String str) {
        return "input_method".equals(str) ? this.f914a : super.getSystemService(str);
    }
}
