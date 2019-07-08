package com.google.android.gms.internal.ads;

final /* synthetic */ class zzajq implements Runnable {
    private final zzajp zzdjw;
    private final zzaii zzdjx;

    zzajq(zzajp zzajp, zzaii zzaii) {
        this.zzdjw = zzajp;
        this.zzdjx = zzaii;
    }

    public final void run() {
        zzajp zzajp = this.zzdjw;
        zzaii zzaii = this.zzdjx;
        zzajp.zzdjv.zzdix.zzh(zzaii);
        zzaii.destroy();
    }
}
