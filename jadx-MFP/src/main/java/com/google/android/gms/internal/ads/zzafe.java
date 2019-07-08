package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener;

@zzark
public final class zzafe extends zzaei {
    private final OnCustomClickListener zzdej;

    public zzafe(OnCustomClickListener onCustomClickListener) {
        this.zzdej = onCustomClickListener;
    }

    public final void zzb(zzadx zzadx, String str) {
        this.zzdej.onCustomClick(zzaea.zza(zzadx), str);
    }
}
