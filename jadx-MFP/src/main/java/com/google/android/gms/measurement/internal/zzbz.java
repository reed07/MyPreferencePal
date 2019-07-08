package com.google.android.gms.measurement.internal;

final class zzbz implements Runnable {
    private final /* synthetic */ zzk zzaqn;
    private final /* synthetic */ zzby zzaqo;

    zzbz(zzby zzby, zzk zzk) {
        this.zzaqo = zzby;
        this.zzaqn = zzk;
    }

    public final void run() {
        this.zzaqo.zzamx.zzme();
        this.zzaqo.zzamx.zze(this.zzaqn);
    }
}
