package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzano implements zzbip<zzbiq, Object> {
    private final /* synthetic */ zzalm zzdop;
    private final /* synthetic */ zzana zzdoq;
    private final /* synthetic */ zzanm zzdor;

    zzano(zzanm zzanm, zzana zzana, zzalm zzalm) {
        this.zzdor = zzanm;
        this.zzdoq = zzana;
        this.zzdop = zzalm;
    }

    public final void zzbw(String str) {
        try {
            this.zzdoq.zzcu(str);
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }
}
