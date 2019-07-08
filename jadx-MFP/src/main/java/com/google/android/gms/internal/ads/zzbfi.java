package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

final class zzbfi implements Runnable {
    private final /* synthetic */ zzbfh zzewm;

    zzbfi(zzbfh zzbfh) {
        this.zzewm = zzbfh;
    }

    public final void run() {
        zzbv.zzmd().zzb(this.zzewm);
    }
}
