package com.google.android.gms.internal.ads;

final class zzky implements Runnable {
    private final /* synthetic */ int zzavz;
    private final /* synthetic */ zzfs zzawa;
    private final /* synthetic */ int zzawb;
    private final /* synthetic */ Object zzawc;
    private final /* synthetic */ zzkt zzawg;
    private final /* synthetic */ long zzawl;

    zzky(zzkt zzkt, int i, zzfs zzfs, int i2, Object obj, long j) {
        this.zzawg = zzkt;
        this.zzavz = i;
        this.zzawa = zzfs;
        this.zzawb = i2;
        this.zzawc = obj;
        this.zzawl = j;
    }

    public final void run() {
        this.zzawg.zzavv.zza(this.zzavz, this.zzawa, this.zzawb, this.zzawc, this.zzawg.zzx(this.zzawl));
    }
}
