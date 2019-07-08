package com.google.android.gms.measurement.internal;

final class zzfb implements Runnable {
    private final /* synthetic */ Runnable zzacf;
    private final /* synthetic */ zzfn zzata;

    zzfb(zzey zzey, zzfn zzfn, Runnable runnable) {
        this.zzata = zzfn;
        this.zzacf = runnable;
    }

    public final void run() {
        this.zzata.zzme();
        this.zzata.zzg(this.zzacf);
        this.zzata.zzlz();
    }
}
