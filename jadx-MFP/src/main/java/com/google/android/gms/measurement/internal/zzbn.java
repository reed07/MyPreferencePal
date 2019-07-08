package com.google.android.gms.measurement.internal;

final class zzbn implements Runnable {
    private final /* synthetic */ zzbw zzaoh;
    private final /* synthetic */ zzas zzaoi;

    zzbn(zzbm zzbm, zzbw zzbw, zzas zzas) {
        this.zzaoh = zzbw;
        this.zzaoi = zzas;
    }

    public final void run() {
        if (this.zzaoh.zzkk() == null) {
            this.zzaoi.zzjg().zzby("Install Referrer Reporter is null");
            return;
        }
        zzbj zzkk = this.zzaoh.zzkk();
        zzkk.zzada.zzgg();
        zzkk.zzce(zzkk.zzada.getContext().getPackageName());
    }
}
