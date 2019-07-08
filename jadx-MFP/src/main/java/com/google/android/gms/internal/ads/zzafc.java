package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;

@zzark
public final class zzafc extends zzaec {
    private final OnAppInstallAdLoadedListener zzdeh;

    public zzafc(OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
        this.zzdeh = onAppInstallAdLoadedListener;
    }

    public final void zza(zzadp zzadp) {
        this.zzdeh.onAppInstallAdLoaded(new zzads(zzadp));
    }
}
