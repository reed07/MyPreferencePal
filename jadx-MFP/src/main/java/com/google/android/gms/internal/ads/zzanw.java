package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.zzbv;

final class zzanw implements Runnable {
    private final /* synthetic */ AdOverlayInfoParcel zzboy;
    private final /* synthetic */ zzanu zzdoy;

    zzanw(zzanu zzanu, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zzdoy = zzanu;
        this.zzboy = adOverlayInfoParcel;
    }

    public final void run() {
        zzbv.zzld();
        zzl.zza(this.zzdoy.zzdow, this.zzboy, true);
    }
}
