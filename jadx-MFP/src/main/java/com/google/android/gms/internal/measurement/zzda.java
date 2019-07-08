package com.google.android.gms.internal.measurement;

final class zzda implements zzcd {
    private final /* synthetic */ Runnable zzacf;
    private final /* synthetic */ zzcx zzacg;

    zzda(zzcx zzcx, Runnable runnable) {
        this.zzacg = zzcx;
        this.zzacf = runnable;
    }

    public final void zza(Throwable th) {
        this.zzacg.handler.post(this.zzacf);
    }
}
