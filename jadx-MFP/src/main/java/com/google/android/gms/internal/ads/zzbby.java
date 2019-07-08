package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final /* synthetic */ class zzbby implements Runnable {
    private final Future zzcal;
    private final zzbcb zzepi;

    zzbby(zzbcb zzbcb, Future future) {
        this.zzepi = zzbcb;
        this.zzcal = future;
    }

    public final void run() {
        zzbcb zzbcb = this.zzepi;
        Future future = this.zzcal;
        if (zzbcb.isCancelled()) {
            future.cancel(true);
        }
    }
}
