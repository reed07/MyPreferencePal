package com.google.android.gms.internal.ads;

final /* synthetic */ class zzajd implements Runnable {
    private final zzaii zzdjg;

    private zzajd(zzaii zzaii) {
        this.zzdjg = zzaii;
    }

    static Runnable zzb(zzaii zzaii) {
        return new zzajd(zzaii);
    }

    public final void run() {
        this.zzdjg.destroy();
    }
}
