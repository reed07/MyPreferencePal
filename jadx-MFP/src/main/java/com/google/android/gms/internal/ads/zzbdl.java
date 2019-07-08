package com.google.android.gms.internal.ads;

final /* synthetic */ class zzbdl implements Runnable {
    private final zzbdi zzers;

    private zzbdl(zzbdi zzbdi) {
        this.zzers = zzbdi;
    }

    static Runnable zza(zzbdi zzbdi) {
        return new zzbdl(zzbdi);
    }

    public final void run() {
        this.zzers.stop();
    }
}
