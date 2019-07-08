package com.google.android.gms.internal.ads;

final class zzrc implements Runnable {
    private final /* synthetic */ zzhn zzabs;
    private final /* synthetic */ zzqv zzbkp;

    zzrc(zzqv zzqv, zzhn zzhn) {
        this.zzbkp = zzqv;
        this.zzabs = zzhn;
    }

    public final void run() {
        this.zzabs.zzds();
        this.zzbkp.zzbko.zzf(this.zzabs);
    }
}
