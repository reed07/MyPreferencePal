package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;

@zzark
public final class zzaff extends zzael {
    private final OnCustomTemplateAdLoadedListener zzdek;

    public zzaff(OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener) {
        this.zzdek = onCustomTemplateAdLoadedListener;
    }

    public final void zzb(zzadx zzadx) {
        this.zzdek.onCustomTemplateAdLoaded(zzaea.zza(zzadx));
    }
}
