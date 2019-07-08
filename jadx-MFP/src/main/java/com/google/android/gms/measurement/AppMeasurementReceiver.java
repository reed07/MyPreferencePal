package com.google.android.gms.measurement;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.google.android.gms.measurement.internal.zzbm;
import com.google.android.gms.measurement.internal.zzbp;

public final class AppMeasurementReceiver extends WakefulBroadcastReceiver implements zzbp {
    private zzbm zzadb;

    @MainThread
    public final void onReceive(Context context, Intent intent) {
        if (this.zzadb == null) {
            this.zzadb = new zzbm(this);
        }
        this.zzadb.onReceive(context, intent);
    }

    @MainThread
    public final void doStartService(Context context, Intent intent) {
        startWakefulService(context, intent);
    }

    public final PendingResult doGoAsync() {
        return goAsync();
    }
}
