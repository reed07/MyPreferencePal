package com.google.android.gms.analytics;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.internal.measurement.zzcx;
import com.google.android.gms.internal.measurement.zzdb;

public final class AnalyticsService extends Service implements zzdb {
    private zzcx<AnalyticsService> zzqz;

    private final zzcx<AnalyticsService> zzn() {
        if (this.zzqz == null) {
            this.zzqz = new zzcx<>(this);
        }
        return this.zzqz;
    }

    @RequiresPermission
    public final void onCreate() {
        super.onCreate();
        zzn().onCreate();
    }

    @RequiresPermission
    public final void onDestroy() {
        zzn().onDestroy();
        super.onDestroy();
    }

    @RequiresPermission
    public final int onStartCommand(Intent intent, int i, int i2) {
        return zzn().onStartCommand(intent, i, i2);
    }

    public final IBinder onBind(Intent intent) {
        zzn();
        return null;
    }

    public final boolean callServiceStopSelfResult(int i) {
        return stopSelfResult(i);
    }

    public final void zza(JobParameters jobParameters, boolean z) {
        throw new UnsupportedOperationException();
    }
}
