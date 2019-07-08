package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzabs;
import com.google.android.gms.internal.ads.zzaxz;

final class zzs implements Runnable {
    private final /* synthetic */ zzp zzbmo;
    private final /* synthetic */ zzabs zzbmp;

    zzs(zzp zzp, zzabs zzabs) {
        this.zzbmo = zzp;
        this.zzbmp = zzabs;
    }

    public final void run() {
        try {
            if (this.zzbmo.zzbls.zzbtc != null) {
                this.zzbmo.zzbls.zzbtc.zza(this.zzbmp);
                this.zzbmo.zzb(this.zzbmp.zzsc());
            }
        } catch (RemoteException e) {
            zzaxz.zzd("#007 Could not call remote method.", e);
        }
    }
}
