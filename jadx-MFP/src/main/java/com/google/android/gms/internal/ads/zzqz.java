package com.google.android.gms.internal.ads;

final class zzqz implements Runnable {
    private final /* synthetic */ zzqv zzbkp;
    private final /* synthetic */ int zzbkq;
    private final /* synthetic */ long zzbkr;

    zzqz(zzqv zzqv, int i, long j) {
        this.zzbkp = zzqv;
        this.zzbkq = i;
        this.zzbkr = j;
    }

    public final void run() {
        this.zzbkp.zzbko.zzh(this.zzbkq, this.zzbkr);
    }
}
