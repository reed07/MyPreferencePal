package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;

final class zzanq implements zzbip<UnifiedNativeAdMapper, Object> {
    private final /* synthetic */ zzalm zzdop;
    private final /* synthetic */ zzanc zzdot;

    zzanq(zzanm zzanm, zzanc zzanc, zzalm zzalm) {
        this.zzdot = zzanc;
        this.zzdop = zzalm;
    }

    public final void zzbw(String str) {
        try {
            this.zzdot.zzcu(str);
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }
}
