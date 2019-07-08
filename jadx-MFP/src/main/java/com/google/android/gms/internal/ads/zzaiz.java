package com.google.android.gms.internal.ads;

final /* synthetic */ class zzaiz implements Runnable {
    private final zzaii zzdjg;

    private zzaiz(zzaii zzaii) {
        this.zzdjg = zzaii;
    }

    static Runnable zzb(zzaii zzaii) {
        return new zzaiz(zzaii);
    }

    public final void run() {
        this.zzdjg.destroy();
    }
}
