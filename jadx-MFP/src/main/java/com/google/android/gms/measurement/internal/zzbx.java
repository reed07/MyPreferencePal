package com.google.android.gms.measurement.internal;

final class zzbx implements Runnable {
    private final /* synthetic */ zzcz zzaqj;
    private final /* synthetic */ zzbw zzaqk;

    zzbx(zzbw zzbw, zzcz zzcz) {
        this.zzaqk = zzbw;
        this.zzaqj = zzcz;
    }

    public final void run() {
        this.zzaqk.zza(this.zzaqj);
        this.zzaqk.start();
    }
}
