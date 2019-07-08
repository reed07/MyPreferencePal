package com.google.android.gms.measurement.internal;

import com.google.android.gms.measurement.AppMeasurement.ConditionalUserProperty;

final class zzdi implements Runnable {
    private final /* synthetic */ zzda zzarh;
    private final /* synthetic */ ConditionalUserProperty zzaro;

    zzdi(zzda zzda, ConditionalUserProperty conditionalUserProperty) {
        this.zzarh = zzda;
        this.zzaro = conditionalUserProperty;
    }

    public final void run() {
        this.zzarh.zzb(this.zzaro);
    }
}
