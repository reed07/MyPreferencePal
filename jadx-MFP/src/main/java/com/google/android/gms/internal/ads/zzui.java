package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final /* synthetic */ class zzui implements Runnable {
    private final zzbcl zzcak;
    private final Future zzcal;

    zzui(zzbcl zzbcl, Future future) {
        this.zzcak = zzbcl;
        this.zzcal = future;
    }

    public final void run() {
        zzbcl zzbcl = this.zzcak;
        Future future = this.zzcal;
        if (zzbcl.isCancelled()) {
            future.cancel(true);
        }
    }
}
