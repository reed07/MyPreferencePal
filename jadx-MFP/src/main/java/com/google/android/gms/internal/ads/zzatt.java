package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final class zzatt implements Runnable {
    private final /* synthetic */ Future zzeba;

    zzatt(zzatq zzatq, Future future) {
        this.zzeba = future;
    }

    public final void run() {
        if (!this.zzeba.isDone()) {
            this.zzeba.cancel(true);
        }
    }
}
