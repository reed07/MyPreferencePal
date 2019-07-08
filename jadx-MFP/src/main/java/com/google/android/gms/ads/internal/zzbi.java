package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzabu;
import com.google.android.gms.internal.ads.zzaxz;

final class zzbi implements Runnable {
    private final /* synthetic */ zzabu zzbmq;
    private final /* synthetic */ zzbb zzbqi;

    zzbi(zzbb zzbb, zzabu zzabu) {
        this.zzbqi = zzbb;
        this.zzbmq = zzabu;
    }

    public final void run() {
        try {
            if (this.zzbqi.zzbls.zzbtd != null) {
                this.zzbqi.zzbls.zzbtd.zza(this.zzbmq);
                this.zzbqi.zzb(this.zzbmq.zzsc());
            }
        } catch (RemoteException e) {
            zzaxz.zzd("#007 Could not call remote method.", e);
        }
    }
}
