package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzzk implements Runnable {
    private final /* synthetic */ zzzj zzcna;

    zzzk(zzzj zzzj) {
        this.zzcna = zzzj;
    }

    public final void run() {
        if (this.zzcna.zzcmz.zzbnn != null) {
            try {
                this.zzcna.zzcmz.zzbnn.onAdFailedToLoad(1);
            } catch (RemoteException e) {
                zzbbd.zzc("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
