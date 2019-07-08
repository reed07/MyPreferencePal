package com.google.android.gms.ads.internal;

final /* synthetic */ class zze implements Runnable {
    private final zzbl zzbmc;

    private zze(zzbl zzbl) {
        this.zzbmc = zzbl;
    }

    static Runnable zza(zzbl zzbl) {
        return new zze(zzbl);
    }

    public final void run() {
        this.zzbmc.resume();
    }
}
