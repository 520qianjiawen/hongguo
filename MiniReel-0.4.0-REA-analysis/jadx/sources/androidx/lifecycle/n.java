package androidx.lifecycle;

import android.app.Activity;

/* JADX INFO: loaded from: classes.dex */
public abstract class n {
    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Activity activity, d dVar) {
        j jVarA;
        p0.h.e(dVar, "event");
        if (!(activity instanceof i) || (jVarA = ((i) activity).a()) == null) {
            return;
        }
        jVarA.a(dVar);
    }
}
