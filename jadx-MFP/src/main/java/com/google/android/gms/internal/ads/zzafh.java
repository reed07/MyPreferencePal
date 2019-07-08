package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.PublisherAdView;

final class zzafh implements Runnable {
    private final /* synthetic */ PublisherAdView zzdem;
    private final /* synthetic */ zzxl zzden;
    private final /* synthetic */ zzafg zzdeo;

    zzafh(zzafg zzafg, PublisherAdView publisherAdView, zzxl zzxl) {
        this.zzdeo = zzafg;
        this.zzdem = publisherAdView;
        this.zzden = zzxl;
    }

    public final void run() {
        if (this.zzdem.zza(this.zzden)) {
            this.zzdeo.zzdel.onPublisherAdViewLoaded(this.zzdem);
        } else {
            zzbbd.zzeo("Could not bind.");
        }
    }
}
