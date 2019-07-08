package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

final /* synthetic */ class zzbbw implements Runnable {
    private final zzbcl zzcak;
    private final zzbcb zzepa;
    private final Class zzepf;
    private final zzbbl zzepg;
    private final Executor zzeph;

    zzbbw(zzbcl zzbcl, zzbcb zzbcb, Class cls, zzbbl zzbbl, Executor executor) {
        this.zzcak = zzbcl;
        this.zzepa = zzbcb;
        this.zzepf = cls;
        this.zzepg = zzbbl;
        this.zzeph = executor;
    }

    public final void run() {
        zzbbq.zza(this.zzcak, this.zzepa, this.zzepf, this.zzepg, this.zzeph);
    }
}
