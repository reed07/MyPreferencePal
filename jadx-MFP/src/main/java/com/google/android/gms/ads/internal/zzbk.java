package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzadx;
import com.google.android.gms.internal.ads.zzaek;
import com.google.android.gms.internal.ads.zzaxz;

final class zzbk implements Runnable {
    private final /* synthetic */ zzadx zzbmr;
    private final /* synthetic */ zzbb zzbqi;

    zzbk(zzbb zzbb, zzadx zzadx) {
        this.zzbqi = zzbb;
        this.zzbmr = zzadx;
    }

    public final void run() {
        try {
            ((zzaek) this.zzbqi.zzbls.zzbth.get(this.zzbmr.getCustomTemplateId())).zzb(this.zzbmr);
        } catch (RemoteException e) {
            zzaxz.zzd("#007 Could not call remote method.", e);
        }
    }
}
