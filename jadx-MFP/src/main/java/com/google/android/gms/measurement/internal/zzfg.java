package com.google.android.gms.measurement.internal;

final class zzfg implements Runnable {
    private final /* synthetic */ long zzafg;
    private final /* synthetic */ zzfd zzatf;

    zzfg(zzfd zzfd, long j) {
        this.zzatf = zzfd;
        this.zzafg = j;
    }

    public final void run() {
        this.zzatf.zzai(this.zzafg);
    }
}
