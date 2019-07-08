package com.google.android.gms.tagmanager;

import java.util.List;

final class zzau implements Runnable {
    private final /* synthetic */ List zzbbp;
    private final /* synthetic */ long zzbbq;
    private final /* synthetic */ zzat zzbbr;

    zzau(zzat zzat, List list, long j) {
        this.zzbbr = zzat;
        this.zzbbp = list;
        this.zzbbq = j;
    }

    public final void run() {
        this.zzbbr.zzb(this.zzbbp, this.zzbbq);
    }
}
