package com.facebook.ads.internal.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.s.c;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class m {
    private final c a;
    @Nullable
    private Application b;
    @Nullable
    private a c;
    private long d = 0;
    @Nullable
    private String e = null;
    @Nullable
    private a f = null;

    @TargetApi(14)
    private static class a implements ActivityLifecycleCallbacks {
        private final WeakReference<Activity> a;
        @Nullable
        private m b;

        public a(Activity activity, m mVar) {
            this.a = new WeakReference<>(activity);
            this.b = mVar;
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
            if (this.b != null) {
                Activity activity2 = (Activity) this.a.get();
                if (activity2 == null || (activity2 != null && activity.equals(activity2))) {
                    this.b.a();
                    this.b = null;
                }
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }
    }

    private m(c cVar, Activity activity, int i) {
        this.a = cVar;
        this.b = activity.getApplication();
        this.c = new a(activity, this);
    }

    public static m a(c cVar, Activity activity) {
        int i = VERSION.SDK_INT;
        if (activity == null || i < 14) {
            return null;
        }
        return new m(cVar, activity, i);
    }

    private void a(String str, long j, long j2, @Nullable a aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("leave_time", Long.toString(j));
        hashMap.put("back_time", Long.toString(j2));
        if (aVar != null) {
            hashMap.put("outcome", aVar.name());
        }
        this.a.m(str, hashMap);
    }

    @TargetApi(14)
    public void a() {
        a(this.e, this.d, System.currentTimeMillis(), this.f);
        Application application = this.b;
        if (application != null) {
            a aVar = this.c;
            if (aVar != null) {
                application.unregisterActivityLifecycleCallbacks(aVar);
                this.c = null;
                this.b = null;
            }
        }
    }

    public void a(@Nullable a aVar) {
        this.f = aVar;
    }

    @TargetApi(14)
    public void a(String str) {
        this.e = str;
        if (this.c == null || this.b == null) {
            a(str, -1, -1, a.CANNOT_TRACK);
            return;
        }
        this.d = System.currentTimeMillis();
        this.b.registerActivityLifecycleCallbacks(this.c);
    }
}
