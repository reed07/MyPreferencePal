package com.google.android.gms.measurement.internal;

final class zzcd implements Runnable {
    private final /* synthetic */ zzby zzaqo;
    private final /* synthetic */ zzo zzaqp;

    zzcd(zzby zzby, zzo zzo) {
        this.zzaqo = zzby;
        this.zzaqp = zzo;
    }

    public final void run() {
        this.zzaqo.zzamx.zzme();
        this.zzaqo.zzamx.zze(this.zzaqp);
    }
}
