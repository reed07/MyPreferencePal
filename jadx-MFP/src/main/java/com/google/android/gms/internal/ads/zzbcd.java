package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

final /* synthetic */ class zzbcd implements Runnable {
    private final Runnable zzbnd;
    private final Executor zzepn;

    zzbcd(Executor executor, Runnable runnable) {
        this.zzepn = executor;
        this.zzbnd = runnable;
    }

    public final void run() {
        this.zzepn.execute(this.zzbnd);
    }
}
