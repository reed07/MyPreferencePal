package com.google.android.gms.internal.ads;

final class zzbdp implements Runnable {
    private final /* synthetic */ zzbdk zzeru;
    private final /* synthetic */ boolean zzerv;

    zzbdp(zzbdk zzbdk, boolean z) {
        this.zzeru = zzbdk;
        this.zzerv = z;
    }

    public final void run() {
        this.zzeru.zzb("windowVisibilityChanged", "isVisible", String.valueOf(this.zzerv));
    }
}
