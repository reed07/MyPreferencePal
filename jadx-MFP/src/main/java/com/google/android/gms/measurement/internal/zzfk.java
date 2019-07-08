package com.google.android.gms.measurement.internal;

final class zzfk extends zzy {
    private final /* synthetic */ zzfn zzata;
    private final /* synthetic */ zzfj zzath;

    zzfk(zzfj zzfj, zzct zzct, zzfn zzfn) {
        this.zzath = zzfj;
        this.zzata = zzfn;
        super(zzct);
    }

    public final void run() {
        this.zzath.cancel();
        this.zzath.zzgt().zzjo().zzby("Starting upload from DelayedRunnable");
        this.zzata.zzlz();
    }
}
