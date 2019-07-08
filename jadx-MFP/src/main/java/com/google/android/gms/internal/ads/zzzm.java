package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzzm implements Runnable {
    private final /* synthetic */ zzzl zzcnb;

    zzzm(zzzl zzzl) {
        this.zzcnb = zzzl;
    }

    public final void run() {
        if (this.zzcnb.zzbnn != null) {
            try {
                this.zzcnb.zzbnn.onAdFailedToLoad(1);
            } catch (RemoteException e) {
                zzbbd.zzc("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
