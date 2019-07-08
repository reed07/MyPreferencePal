package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

final class zzda implements zzdh {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ Bundle zzsj;

    zzda(zzcz zzcz, Activity activity, Bundle bundle) {
        this.val$activity = activity;
        this.zzsj = bundle;
    }

    public final void zza(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityCreated(this.val$activity, this.zzsj);
    }
}
