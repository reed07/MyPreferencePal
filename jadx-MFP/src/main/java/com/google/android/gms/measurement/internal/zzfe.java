package com.google.android.gms.measurement.internal;

import android.support.annotation.WorkerThread;

final class zzfe extends zzy {
    private final /* synthetic */ zzfd zzatf;

    zzfe(zzfd zzfd, zzct zzct) {
        this.zzatf = zzfd;
        super(zzct);
    }

    @WorkerThread
    public final void run() {
        this.zzatf.zzlo();
    }
}
