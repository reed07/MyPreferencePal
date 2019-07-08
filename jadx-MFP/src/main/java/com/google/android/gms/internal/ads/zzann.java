package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzann implements zzbip<Object, Object> {
    private final /* synthetic */ zzamy zzdoo;
    private final /* synthetic */ zzalm zzdop;

    zzann(zzanm zzanm, zzamy zzamy, zzalm zzalm) {
        this.zzdoo = zzamy;
        this.zzdop = zzalm;
    }

    public final void zzbw(String str) {
        try {
            this.zzdoo.zzcu(str);
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }
}
