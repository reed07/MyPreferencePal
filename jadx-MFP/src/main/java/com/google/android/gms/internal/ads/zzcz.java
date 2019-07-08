package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import java.lang.ref.WeakReference;

final class zzcz implements ActivityLifecycleCallbacks {
    private final Application zzsg;
    private final WeakReference<ActivityLifecycleCallbacks> zzsh;
    private boolean zzsi = false;

    public zzcz(Application application, ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.zzsh = new WeakReference<>(activityLifecycleCallbacks);
        this.zzsg = application;
    }

    private final void zza(zzdh zzdh) {
        try {
            ActivityLifecycleCallbacks activityLifecycleCallbacks = (ActivityLifecycleCallbacks) this.zzsh.get();
            if (activityLifecycleCallbacks != null) {
                zzdh.zza(activityLifecycleCallbacks);
                return;
            }
            if (!this.zzsi) {
                this.zzsg.unregisterActivityLifecycleCallbacks(this);
                this.zzsi = true;
            }
        } catch (Exception unused) {
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(new zzda(this, activity, bundle));
    }

    public final void onActivityStarted(Activity activity) {
        zza(new zzdb(this, activity));
    }

    public final void onActivityResumed(Activity activity) {
        zza(new zzdc(this, activity));
    }

    public final void onActivityPaused(Activity activity) {
        zza(new zzdd(this, activity));
    }

    public final void onActivityStopped(Activity activity) {
        zza(new zzde(this, activity));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zza(new zzdf(this, activity, bundle));
    }

    public final void onActivityDestroyed(Activity activity) {
        zza(new zzdg(this, activity));
    }
}
