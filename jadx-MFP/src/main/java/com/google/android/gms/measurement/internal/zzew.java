package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;

final class zzew implements Runnable {
    private final /* synthetic */ zzes zzasu;

    zzew(zzes zzes) {
        this.zzasu = zzes;
    }

    public final void run() {
        zzeb zzeb = this.zzasu.zzasl;
        Context context = this.zzasu.zzasl.getContext();
        this.zzasu.zzasl.zzgw();
        zzeb.onServiceDisconnected(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
