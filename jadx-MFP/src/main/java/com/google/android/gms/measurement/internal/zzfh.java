package com.google.android.gms.measurement.internal;

final class zzfh implements Runnable {
    private final /* synthetic */ long zzafg;
    private final /* synthetic */ zzfd zzatf;

    zzfh(zzfd zzfd, long j) {
        this.zzatf = zzfd;
        this.zzafg = j;
    }

    public final void run() {
        this.zzatf.zzak(this.zzafg);
    }
}
