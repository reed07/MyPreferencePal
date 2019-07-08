package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzn;

final class zzanv implements zzn {
    private final /* synthetic */ zzanu zzdoy;

    zzanv(zzanu zzanu) {
        this.zzdoy = zzanu;
    }

    public final void zziv() {
        zzbbd.zzdn("AdMobCustomTabsAdapter overlay is closed.");
        this.zzdoy.zzdox.onAdClosed(this.zzdoy);
    }

    public final void onPause() {
        zzbbd.zzdn("AdMobCustomTabsAdapter overlay is paused.");
    }

    public final void onResume() {
        zzbbd.zzdn("AdMobCustomTabsAdapter overlay is resumed.");
    }

    public final void zziw() {
        zzbbd.zzdn("Opening AdMobCustomTabsAdapter overlay.");
        this.zzdoy.zzdox.onAdOpened(this.zzdoy);
    }
}
