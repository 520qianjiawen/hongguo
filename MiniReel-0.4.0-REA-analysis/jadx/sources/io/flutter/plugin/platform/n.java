package io.flutter.plugin.platform;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;

/* JADX INFO: loaded from: classes.dex */
public final class n extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f915a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public q f916b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f917c;

    public n(Context context, q qVar, Context context2) {
        super(context);
        this.f915a = qVar;
        this.f917c = context2;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Object getSystemService(String str) {
        if (!"window".equals(str)) {
            return super.getSystemService(str);
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i2 = 0; i2 < stackTrace.length && i2 < 11; i2++) {
            if (stackTrace[i2].getClassName().equals(AlertDialog.class.getCanonicalName()) && stackTrace[i2].getMethodName().equals("<init>")) {
                return this.f917c.getSystemService(str);
            }
        }
        if (this.f916b == null) {
            this.f916b = this.f915a;
        }
        return this.f916b;
    }
}
