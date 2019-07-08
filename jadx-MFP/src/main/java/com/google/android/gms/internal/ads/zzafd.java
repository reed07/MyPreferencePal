package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;

@zzark
public final class zzafd extends zzaef {
    private final OnContentAdLoadedListener zzdei;

    public zzafd(OnContentAdLoadedListener onContentAdLoadedListener) {
        this.zzdei = onContentAdLoadedListener;
    }

    public final void zza(zzadt zzadt) {
        this.zzdei.onContentAdLoaded(new zzadw(zzadt));
    }
}
