package androidx.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public final class l extends androidx.lifecycle.a {
    final /* synthetic */ m this$0;

    public static final class a extends androidx.lifecycle.a {
        final /* synthetic */ m this$0;

        public a(m mVar) {
            this.this$0 = mVar;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            p0.h.e(activity, "activity");
            this.this$0.b();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            p0.h.e(activity, "activity");
            m mVar = this.this$0;
            int i2 = mVar.f641a + 1;
            mVar.f641a = i2;
            if (i2 == 1 && mVar.f644d) {
                mVar.f646f.a(d.ON_START);
                mVar.f644d = false;
            }
        }
    }

    public l(m mVar) {
        this.this$0 = mVar;
    }

    @Override // androidx.lifecycle.a, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        p0.h.e(activity, "activity");
        if (Build.VERSION.SDK_INT < 29) {
            int i2 = p.f649b;
            Fragment fragmentFindFragmentByTag = activity.getFragmentManager().findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag");
            p0.h.c(fragmentFindFragmentByTag, "null cannot be cast to non-null type androidx.lifecycle.ReportFragment");
            ((p) fragmentFindFragmentByTag).f650a = this.this$0.f648h;
        }
    }

    @Override // androidx.lifecycle.a, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        p0.h.e(activity, "activity");
        m mVar = this.this$0;
        int i2 = mVar.f642b - 1;
        mVar.f642b = i2;
        if (i2 == 0) {
            Handler handler = mVar.f645e;
            p0.h.b(handler);
            handler.postDelayed(mVar.f647g, 700L);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        p0.h.e(activity, "activity");
        k.a(activity, new a(this.this$0));
    }

    @Override // androidx.lifecycle.a, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        p0.h.e(activity, "activity");
        m mVar = this.this$0;
        int i2 = mVar.f641a - 1;
        mVar.f641a = i2;
        if (i2 == 0 && mVar.f643c) {
            mVar.f646f.a(d.ON_STOP);
            mVar.f644d = true;
        }
    }
}
