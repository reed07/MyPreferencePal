package com.google.android.gms.internal.ads;

final /* synthetic */ class zzbfx implements Runnable {
    private final boolean zzduj;
    private final long zzeve;
    private final zzbdz zzexo;

    zzbfx(zzbdz zzbdz, boolean z, long j) {
        this.zzexo = zzbdz;
        this.zzduj = z;
        this.zzeve = j;
    }

    public final void run() {
        this.zzexo.zza(this.zzduj, this.zzeve);
    }
}
