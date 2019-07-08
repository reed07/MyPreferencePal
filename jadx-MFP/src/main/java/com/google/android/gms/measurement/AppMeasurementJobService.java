package com.google.android.gms.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.support.annotation.MainThread;
import com.google.android.gms.measurement.internal.zzey;
import com.google.android.gms.measurement.internal.zzfc;

@TargetApi(24)
public final class AppMeasurementJobService extends JobService implements zzfc {
    private zzey<AppMeasurementJobService> zzadc;

    private final zzey<AppMeasurementJobService> zzfz() {
        if (this.zzadc == null) {
            this.zzadc = new zzey<>(this);
        }
        return this.zzadc;
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public final void zza(Intent intent) {
    }

    @MainThread
    public final void onCreate() {
        super.onCreate();
        zzfz().onCreate();
    }

    @MainThread
    public final void onDestroy() {
        zzfz().onDestroy();
        super.onDestroy();
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        return zzfz().onStartJob(jobParameters);
    }

    @MainThread
    public final boolean onUnbind(Intent intent) {
        return zzfz().onUnbind(intent);
    }

    @MainThread
    public final void onRebind(Intent intent) {
        zzfz().onRebind(intent);
    }

    public final boolean callServiceStopSelfResult(int i) {
        throw new UnsupportedOperationException();
    }

    @TargetApi(24)
    public final void zza(JobParameters jobParameters, boolean z) {
        jobFinished(jobParameters, false);
    }
}
