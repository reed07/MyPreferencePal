package com.google.android.gms.internal.measurement;

final /* synthetic */ class zzcy implements Runnable {
    private final zzcx zzaca;
    private final int zzacb;
    private final zzcp zzacc;

    zzcy(zzcx zzcx, int i, zzcp zzcp) {
        this.zzaca = zzcx;
        this.zzacb = i;
        this.zzacc = zzcp;
    }

    public final void run() {
        this.zzaca.zza(this.zzacb, this.zzacc);
    }
}
