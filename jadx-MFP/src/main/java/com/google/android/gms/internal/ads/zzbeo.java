package com.google.android.gms.internal.ads;

final /* synthetic */ class zzbeo implements Runnable {
    private final boolean zzduj;
    private final zzbee zzevd;
    private final long zzeve;

    zzbeo(zzbee zzbee, boolean z, long j) {
        this.zzevd = zzbee;
        this.zzduj = z;
        this.zzeve = j;
    }

    public final void run() {
        this.zzevd.zzc(this.zzduj, this.zzeve);
    }
}
