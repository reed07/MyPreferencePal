package com.google.android.gms.internal.ads;

import java.util.concurrent.TimeoutException;

final /* synthetic */ class zzbbu implements Runnable {
    private final zzbcl zzcak;

    zzbbu(zzbcl zzbcl) {
        this.zzcak = zzbcl;
    }

    public final void run() {
        this.zzcak.setException(new TimeoutException());
    }
}
