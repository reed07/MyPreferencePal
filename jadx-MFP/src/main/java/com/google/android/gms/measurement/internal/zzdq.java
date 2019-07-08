package com.google.android.gms.measurement.internal;

final class zzdq implements Runnable {
    private final /* synthetic */ boolean zzaed;
    private final /* synthetic */ zzda zzarh;

    zzdq(zzda zzda, boolean z) {
        this.zzarh = zzda;
        this.zzaed = z;
    }

    public final void run() {
        this.zzarh.zzj(this.zzaed);
    }
}
