package com.google.android.gms.measurement.internal;

final class zzds implements Runnable {
    private final /* synthetic */ zzda zzarh;
    private final /* synthetic */ long zzarp;

    zzds(zzda zzda, long j) {
        this.zzarh = zzda;
        this.zzarp = j;
    }

    public final void run() {
        this.zzarh.zzgu().zzann.set(this.zzarp);
        this.zzarh.zzgt().zzjn().zzg("Minimum session duration set", Long.valueOf(this.zzarp));
    }
}
