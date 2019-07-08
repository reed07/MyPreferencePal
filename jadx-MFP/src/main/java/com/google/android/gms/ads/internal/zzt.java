package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzabu;
import com.google.android.gms.internal.ads.zzaxz;

final class zzt implements Runnable {
    private final /* synthetic */ zzp zzbmo;
    private final /* synthetic */ zzabu zzbmq;

    zzt(zzp zzp, zzabu zzabu) {
        this.zzbmo = zzp;
        this.zzbmq = zzabu;
    }

    public final void run() {
        try {
            if (this.zzbmo.zzbls.zzbtd != null) {
                this.zzbmo.zzbls.zzbtd.zza(this.zzbmq);
                this.zzbmo.zzb(this.zzbmq.zzsc());
            }
        } catch (RemoteException e) {
            zzaxz.zzd("#007 Could not call remote method.", e);
        }
    }
}
