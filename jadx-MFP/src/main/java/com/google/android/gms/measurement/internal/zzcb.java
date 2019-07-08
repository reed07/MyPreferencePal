package com.google.android.gms.measurement.internal;

final class zzcb implements Runnable {
    private final /* synthetic */ zzk zzaqn;
    private final /* synthetic */ zzby zzaqo;
    private final /* synthetic */ zzo zzaqp;

    zzcb(zzby zzby, zzo zzo, zzk zzk) {
        this.zzaqo = zzby;
        this.zzaqp = zzo;
        this.zzaqn = zzk;
    }

    public final void run() {
        this.zzaqo.zzamx.zzme();
        this.zzaqo.zzamx.zzb(this.zzaqp, this.zzaqn);
    }
}
