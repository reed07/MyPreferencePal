package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;

@zzark
public final class zzabj extends zzabh {
    private final OnCustomRenderedAdLoadedListener zzcmp;

    public zzabj(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzcmp = onCustomRenderedAdLoadedListener;
    }

    public final void zza(zzabd zzabd) {
        this.zzcmp.onCustomRenderedAdLoaded(new zzabc(zzabd));
    }
}
