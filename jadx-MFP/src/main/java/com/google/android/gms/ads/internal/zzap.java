package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzl;

final class zzap implements Runnable {
    private final /* synthetic */ AdOverlayInfoParcel zzboy;
    private final /* synthetic */ zzao zzboz;

    zzap(zzao zzao, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zzboz = zzao;
        this.zzboy = adOverlayInfoParcel;
    }

    public final void run() {
        zzbv.zzld();
        zzl.zza(this.zzboz.zzbow.zzbls.zzsp, this.zzboy, true);
    }
}
