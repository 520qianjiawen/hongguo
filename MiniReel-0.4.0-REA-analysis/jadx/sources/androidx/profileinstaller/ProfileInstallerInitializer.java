package androidx.profileinstaller;

import android.content.Context;
import java.util.Collections;
import java.util.List;
import p014o.g;
import p014o.i;
import p015p.b;

/* JADX INFO: loaded from: classes.dex */
public class ProfileInstallerInitializer implements b {
    @Override // p015p.b
    public final List a() {
        return Collections.EMPTY_LIST;
    }

    @Override // p015p.b
    public final Object b(Context context) {
        i.a(new g(this, context.getApplicationContext()));
        return new H.b(14);
    }
}
