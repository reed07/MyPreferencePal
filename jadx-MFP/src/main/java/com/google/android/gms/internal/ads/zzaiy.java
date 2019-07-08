package com.google.android.gms.internal.ads;

final /* synthetic */ class zzaiy implements Runnable {
    private final zzait zzdja;
    private final zzajm zzdje;
    private final zzaii zzdjf;

    zzaiy(zzait zzait, zzajm zzajm, zzaii zzaii) {
        this.zzdja = zzait;
        this.zzdje = zzajm;
        this.zzdjf = zzaii;
    }

    public final void run() {
        this.zzdja.zza(this.zzdje, this.zzdjf);
    }
}
