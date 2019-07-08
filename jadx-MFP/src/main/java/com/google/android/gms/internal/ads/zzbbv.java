package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final /* synthetic */ class zzbbv implements Runnable {
    private final Future zzepe;

    zzbbv(Future future) {
        this.zzepe = future;
    }

    public final void run() {
        Future future = this.zzepe;
        if (!future.isDone()) {
            future.cancel(true);
        }
    }
}
