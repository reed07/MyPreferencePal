package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzaxg;

final /* synthetic */ class zzbc implements Runnable {
    private final zzbb zzbqc;
    private final zzaxg zzbqd;

    zzbc(zzbb zzbb, zzaxg zzaxg) {
        this.zzbqc = zzbb;
        this.zzbqd = zzaxg;
    }

    public final void run() {
        zzbb zzbb = this.zzbqc;
        zzaxf zzaxf = new zzaxf(this.zzbqd, null, null, null, null, null, null, null);
        zzbb.zzb(zzaxf);
    }
}
