package com.google.android.gms.measurement.internal;

final class zzdh implements Runnable {
    private final /* synthetic */ zzcx zzaef;
    private final /* synthetic */ zzda zzarh;

    zzdh(zzda zzda, zzcx zzcx) {
        this.zzarh = zzda;
        this.zzaef = zzcx;
    }

    public final void run() {
        this.zzarh.zza(this.zzaef);
    }
}
