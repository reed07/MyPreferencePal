package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

@TargetApi(14)
/* compiled from: IMASDK */
public final class abz implements ActivityLifecycleCallbacks {
    private final /* synthetic */ adt a;

    protected abz(adt adt) {
        this.a = adt;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onActivityResumed(Activity activity) {
        if (this.a.f == activity) {
            this.a.a.b(new ado(adq.activityMonitor, adr.appStateChanged, this.a.b, this.a.a("", "", "", "active")));
        }
    }

    public final void onActivityPaused(Activity activity) {
        if (this.a.f == null || this.a.f == activity) {
            this.a.f = activity;
            this.a.a.b(new ado(adq.activityMonitor, adr.appStateChanged, this.a.b, this.a.a("", "", "", "inactive")));
        }
    }

    public final void onActivityDestroyed(Activity activity) {
        if (this.a.f == activity) {
            this.a.f = null;
            Application d = this.a.j();
            if (d != null) {
                d.unregisterActivityLifecycleCallbacks(this.a.e);
            }
        }
    }
}
