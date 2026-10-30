package androidx.lifecycle;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ProcessLifecycleInitializer implements p015p.b {
    @Override // p015p.b
    public final List a() {
        return g0.m.f733d;
    }

    @Override // p015p.b
    public final Object b(Context context) {
        p0.h.e(context, "context");
        p015p.a aVarC = p015p.a.c(context);
        p0.h.d(aVarC, "getInstance(context)");
        if (!aVarC.f1200b.contains(ProcessLifecycleInitializer.class)) {
            throw new IllegalStateException("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name='androidx.lifecycle.ProcessLifecycleInitializer'\n                   android:value='androidx.startup' />\n               under InitializationProvider in your AndroidManifest.xml");
        }
        if (!h.f633a.getAndSet(true)) {
            Context applicationContext = context.getApplicationContext();
            p0.h.c(applicationContext, "null cannot be cast to non-null type android.app.Application");
            ((Application) applicationContext).registerActivityLifecycleCallbacks(new g());
        }
        m mVar = m.f640i;
        mVar.getClass();
        mVar.f645e = new Handler();
        mVar.f646f.a(d.ON_CREATE);
        Context applicationContext2 = context.getApplicationContext();
        p0.h.c(applicationContext2, "null cannot be cast to non-null type android.app.Application");
        ((Application) applicationContext2).registerActivityLifecycleCallbacks(new l(mVar));
        return mVar;
    }
}
