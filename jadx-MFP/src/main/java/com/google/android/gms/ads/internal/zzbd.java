package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzabq;
import com.google.android.gms.internal.ads.zzaxz;

final /* synthetic */ class zzbd implements Runnable {
    private final zzbb zzbqc;
    private final zzabq zzbqe;

    zzbd(zzbb zzbb, zzabq zzabq) {
        this.zzbqc = zzbb;
        this.zzbqe = zzabq;
    }

    public final void run() {
        zzbb zzbb = this.zzbqc;
        zzabq zzabq = this.zzbqe;
        try {
            if (zzbb.zzbls.zzbtf != null) {
                zzbb.zzbls.zzbtf.zza(zzabq);
            }
        } catch (RemoteException e) {
            zzaxz.zzd("#007 Could not call remote method.", e);
        }
    }
}
