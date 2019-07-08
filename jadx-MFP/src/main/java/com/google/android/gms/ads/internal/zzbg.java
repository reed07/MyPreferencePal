package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzabs;
import com.google.android.gms.internal.ads.zzaxz;

final class zzbg implements Runnable {
    private final /* synthetic */ zzabs zzbmp;
    private final /* synthetic */ zzbb zzbqi;

    zzbg(zzbb zzbb, zzabs zzabs) {
        this.zzbqi = zzbb;
        this.zzbmp = zzabs;
    }

    public final void run() {
        try {
            if (this.zzbqi.zzbls.zzbtc != null) {
                this.zzbqi.zzbls.zzbtc.zza(this.zzbmp);
                this.zzbqi.zzb(this.zzbmp.zzsc());
            }
        } catch (RemoteException e) {
            zzaxz.zzd("#007 Could not call remote method.", e);
        }
    }
}
