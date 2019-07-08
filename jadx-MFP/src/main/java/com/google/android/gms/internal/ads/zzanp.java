package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzanp implements zzbip<zzbir, Object> {
    private final /* synthetic */ zzalm zzdop;
    private final /* synthetic */ zzanm zzdor;
    private final /* synthetic */ zzane zzdos;

    zzanp(zzanm zzanm, zzane zzane, zzalm zzalm) {
        this.zzdor = zzanm;
        this.zzdos = zzane;
        this.zzdop = zzalm;
    }

    public final void zzbw(String str) {
        try {
            this.zzdos.zzcu(str);
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }
}
