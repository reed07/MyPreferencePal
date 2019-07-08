package com.google.android.gms.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.MainThread;
import com.google.android.gms.measurement.internal.zzey;
import com.google.android.gms.measurement.internal.zzfc;

public final class AppMeasurementService extends Service implements zzfc {
    private zzey<AppMeasurementService> zzadc;

    private final zzey<AppMeasurementService> zzfz() {
        if (this.zzadc == null) {
            this.zzadc = new zzey<>(this);
        }
        return this.zzadc;
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

    @MainThread
    public final int onStartCommand(Intent intent, int i, int i2) {
        return zzfz().onStartCommand(intent, i, i2);
    }

    @MainThread
    public final IBinder onBind(Intent intent) {
        return zzfz().onBind(intent);
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
        return stopSelfResult(i);
    }

    public final void zza(JobParameters jobParameters, boolean z) {
        throw new UnsupportedOperationException();
    }

    public final void zza(Intent intent) {
        AppMeasurementReceiver.completeWakefulIntent(intent);
    }
}
