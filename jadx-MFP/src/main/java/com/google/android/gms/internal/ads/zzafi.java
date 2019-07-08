package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener;

@zzark
public final class zzafi extends zzaer {
    private final OnUnifiedNativeAdLoadedListener zzdep;

    public zzafi(OnUnifiedNativeAdLoadedListener onUnifiedNativeAdLoadedListener) {
        this.zzdep = onUnifiedNativeAdLoadedListener;
    }

    public final void zza(zzaew zzaew) {
        this.zzdep.onUnifiedNativeAdLoaded(new zzaez(zzaew));
    }
}
