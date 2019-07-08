package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzadx;
import com.google.android.gms.internal.ads.zzaek;
import com.google.android.gms.internal.ads.zzaxz;

final class zzu implements Runnable {
    private final /* synthetic */ zzp zzbmo;
    private final /* synthetic */ zzadx zzbmr;

    zzu(zzp zzp, zzadx zzadx) {
        this.zzbmo = zzp;
        this.zzbmr = zzadx;
    }

    public final void run() {
        try {
            ((zzaek) this.zzbmo.zzbls.zzbth.get(this.zzbmr.getCustomTemplateId())).zzb(this.zzbmr);
        } catch (RemoteException e) {
            zzaxz.zzd("#007 Could not call remote method.", e);
        }
    }
}
