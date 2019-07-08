package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzabz;
import com.google.android.gms.internal.ads.zzaxz;

final class zzbh implements Runnable {
    private final /* synthetic */ zzabz zzbmn;
    private final /* synthetic */ zzbb zzbqi;

    zzbh(zzbb zzbb, zzabz zzabz) {
        this.zzbqi = zzbb;
        this.zzbmn = zzabz;
    }

    public final void run() {
        try {
            if (this.zzbqi.zzbls.zzbte != null) {
                this.zzbqi.zzbls.zzbte.zza(this.zzbmn);
                this.zzbqi.zzb(this.zzbmn.zzsc());
            }
        } catch (RemoteException e) {
            zzaxz.zzd("#007 Could not call remote method.", e);
        }
    }
}
