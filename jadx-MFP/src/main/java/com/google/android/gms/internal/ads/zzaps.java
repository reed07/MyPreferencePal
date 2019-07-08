package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzas;
import java.util.concurrent.CountDownLatch;

final class zzaps implements Runnable {
    private final /* synthetic */ CountDownLatch zzbpn;
    private final /* synthetic */ zzapr zzdsz;

    zzaps(zzapr zzapr, CountDownLatch countDownLatch) {
        this.zzdsz = zzapr;
        this.zzbpn = countDownLatch;
    }

    public final void run() {
        synchronized (this.zzdsz.zzdsn) {
            this.zzdsz.zzdsy = zzas.zza(this.zzdsz.zzdin, this.zzdsz.zzdsx, this.zzbpn);
        }
    }
}
