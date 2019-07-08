package com.google.android.gms.measurement.internal;

final class zzdt implements Runnable {
    private final /* synthetic */ zzda zzarh;
    private final /* synthetic */ long zzarp;

    zzdt(zzda zzda, long j) {
        this.zzarh = zzda;
        this.zzarp = j;
    }

    public final void run() {
        this.zzarh.zzgu().zzano.set(this.zzarp);
        this.zzarh.zzgt().zzjn().zzg("Session timeout duration set", Long.valueOf(this.zzarp));
    }
}
