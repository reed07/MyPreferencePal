package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzbcg;

final /* synthetic */ class zzaz implements Runnable {
    private final Runnable zzbnd;
    private final zzay zzbpu;

    zzaz(zzay zzay, Runnable runnable) {
        this.zzbpu = zzay;
        this.zzbnd = runnable;
    }

    public final void run() {
        zzbcg.zzepo.execute(new zzba(this.zzbpu, this.zzbnd));
    }
}
