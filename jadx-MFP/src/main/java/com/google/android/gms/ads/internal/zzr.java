package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzabz;
import com.google.android.gms.internal.ads.zzaxz;

final class zzr implements Runnable {
    private final /* synthetic */ zzabz zzbmn;
    private final /* synthetic */ zzp zzbmo;

    zzr(zzp zzp, zzabz zzabz) {
        this.zzbmo = zzp;
        this.zzbmn = zzabz;
    }

    public final void run() {
        try {
            if (this.zzbmo.zzbls.zzbte != null) {
                this.zzbmo.zzbls.zzbte.zza(this.zzbmn);
                this.zzbmo.zzb(this.zzbmn.zzsc());
            }
        } catch (RemoteException e) {
            zzaxz.zzd("#007 Could not call remote method.", e);
        }
    }
}
