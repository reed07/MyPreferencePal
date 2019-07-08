package com.google.android.gms.ads.internal;

final /* synthetic */ class zzba implements Runnable {
    private final Runnable zzbnd;
    private final zzay zzbpu;

    zzba(zzay zzay, Runnable runnable) {
        this.zzbpu = zzay;
        this.zzbnd = runnable;
    }

    public final void run() {
        this.zzbpu.zzb(this.zzbnd);
    }
}
