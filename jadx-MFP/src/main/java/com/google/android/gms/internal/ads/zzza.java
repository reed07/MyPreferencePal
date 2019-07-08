package com.google.android.gms.internal.ads;

final class zzza extends zzww {
    private final /* synthetic */ zzyz zzcms;

    zzza(zzyz zzyz) {
        this.zzcms = zzyz;
    }

    public final void onAdLoaded() {
        this.zzcms.zzcml.zza(this.zzcms.zzbc());
        super.onAdLoaded();
    }

    public final void onAdFailedToLoad(int i) {
        this.zzcms.zzcml.zza(this.zzcms.zzbc());
        super.onAdFailedToLoad(i);
    }
}
