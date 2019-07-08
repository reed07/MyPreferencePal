package com.google.android.gms.ads.internal;

final /* synthetic */ class zzd implements Runnable {
    private final zzbl zzbmc;

    private zzd(zzbl zzbl) {
        this.zzbmc = zzbl;
    }

    static Runnable zza(zzbl zzbl) {
        return new zzd(zzbl);
    }

    public final void run() {
        this.zzbmc.pause();
    }
}
