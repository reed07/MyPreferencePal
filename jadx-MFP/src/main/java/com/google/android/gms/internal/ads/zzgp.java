package com.google.android.gms.internal.ads;

final class zzgp implements Runnable {
    private final /* synthetic */ zzgl zzabk;
    private final /* synthetic */ int zzabp;
    private final /* synthetic */ long zzabq;
    private final /* synthetic */ long zzabr;

    zzgp(zzgl zzgl, int i, long j, long j2) {
        this.zzabk = zzgl;
        this.zzabp = i;
        this.zzabq = j;
        this.zzabr = j2;
    }

    public final void run() {
        this.zzabk.zzabi.zza(this.zzabp, this.zzabq, this.zzabr);
    }
}
