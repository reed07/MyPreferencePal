package com.google.android.gms.internal.ads;

final /* synthetic */ class zzbdm implements Runnable {
    private final boolean zzduj;
    private final zzbdk zzert;

    zzbdm(zzbdk zzbdk, boolean z) {
        this.zzert = zzbdk;
        this.zzduj = z;
    }

    public final void run() {
        this.zzert.zzas(this.zzduj);
    }
}
