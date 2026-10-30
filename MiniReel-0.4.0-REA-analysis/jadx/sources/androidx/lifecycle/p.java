package androidx.lifecycle;

import C0.x;
import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class p extends Fragment {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f649b = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public x f650a;

    public static final class a implements Application.ActivityLifecycleCallbacks {
        public static final o Companion = new o();

        public static final void registerIn(Activity activity) {
            Companion.getClass();
            p0.h.e(activity, "activity");
            activity.registerActivityLifecycleCallbacks(new a());
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            p0.h.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            p0.h.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            p0.h.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostCreated(Activity activity, Bundle bundle) {
            p0.h.e(activity, "activity");
            int i2 = p.f649b;
            n.a(activity, d.ON_CREATE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            p0.h.e(activity, "activity");
            int i2 = p.f649b;
            n.a(activity, d.ON_RESUME);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            p0.h.e(activity, "activity");
            int i2 = p.f649b;
            n.a(activity, d.ON_START);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreDestroyed(Activity activity) {
            p0.h.e(activity, "activity");
            int i2 = p.f649b;
            n.a(activity, d.ON_DESTROY);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPrePaused(Activity activity) {
            p0.h.e(activity, "activity");
            int i2 = p.f649b;
            n.a(activity, d.ON_PAUSE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreStopped(Activity activity) {
            p0.h.e(activity, "activity");
            int i2 = p.f649b;
            n.a(activity, d.ON_STOP);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            p0.h.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            p0.h.e(activity, "activity");
            p0.h.e(bundle, "bundle");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            p0.h.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            p0.h.e(activity, "activity");
        }
    }

    public final void a(d dVar) {
        if (Build.VERSION.SDK_INT < 29) {
            Activity activity = getActivity();
            p0.h.d(activity, "activity");
            n.a(activity, dVar);
        }
    }

    @Override // android.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(d.ON_CREATE);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        a(d.ON_DESTROY);
        this.f650a = null;
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        a(d.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        x xVar = this.f650a;
        if (xVar != null) {
            ((m) xVar.f90e).b();
        }
        a(d.ON_RESUME);
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        x xVar = this.f650a;
        if (xVar != null) {
            m mVar = (m) xVar.f90e;
            int i2 = mVar.f641a + 1;
            mVar.f641a = i2;
            if (i2 == 1 && mVar.f644d) {
                mVar.f646f.a(d.ON_START);
                mVar.f644d = false;
            }
        }
        a(d.ON_START);
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        a(d.ON_STOP);
    }
}
