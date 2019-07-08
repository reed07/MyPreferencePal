package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdb;
import com.google.android.gms.stats.WakeLock;

public final class zzcx<T extends Context & zzdb> {
    private static Boolean zzabz;
    /* access modifiers changed from: private */
    public final Handler handler = new zzdl();
    private final T zzaby;

    public zzcx(T t) {
        Preconditions.checkNotNull(t);
        this.zzaby = t;
    }

    public static boolean zze(Context context) {
        Preconditions.checkNotNull(context);
        Boolean bool = zzabz;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean zzc = zzdg.zzc(context, "com.google.android.gms.analytics.AnalyticsService");
        zzabz = Boolean.valueOf(zzc);
        return zzc;
    }

    @RequiresPermission
    public final void onCreate() {
        zzaw.zzc(this.zzaby).zzby().zzq("Local AnalyticsService is starting up");
    }

    @RequiresPermission
    public final void onDestroy() {
        zzaw.zzc(this.zzaby).zzby().zzq("Local AnalyticsService is shutting down");
    }

    @RequiresPermission
    public final int onStartCommand(Intent intent, int i, int i2) {
        try {
            synchronized (zzcw.lock) {
                WakeLock wakeLock = zzcw.zzabx;
                if (wakeLock != null && wakeLock.isHeld()) {
                    wakeLock.release();
                }
            }
        } catch (SecurityException unused) {
        }
        zzcp zzby = zzaw.zzc(this.zzaby).zzby();
        if (intent == null) {
            zzby.zzt("AnalyticsService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzby.zza("Local AnalyticsService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            zzb(new zzcy(this, i2, zzby));
        }
        return 2;
    }

    private final void zzb(Runnable runnable) {
        zzaw.zzc(this.zzaby).zzcc().zza((zzcd) new zzda(this, runnable));
    }

    @TargetApi(24)
    public final boolean onStartJob(JobParameters jobParameters) {
        zzcp zzby = zzaw.zzc(this.zzaby).zzby();
        String string = jobParameters.getExtras().getString("action");
        zzby.zza("Local AnalyticsJobService called. action", string);
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(string)) {
            zzb(new zzcz(this, zzby, jobParameters));
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(zzcp zzcp, JobParameters jobParameters) {
        zzcp.zzq("AnalyticsJobService processed last dispatch request");
        ((zzdb) this.zzaby).zza(jobParameters, false);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(int i, zzcp zzcp) {
        if (((zzdb) this.zzaby).callServiceStopSelfResult(i)) {
            zzcp.zzq("Local AnalyticsService processed last dispatch request");
        }
    }
}
