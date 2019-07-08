package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzzr implements Runnable {
    private final /* synthetic */ zzzq zzcnd;

    zzzr(zzzq zzzq) {
        this.zzcnd = zzzq;
    }

    public final void run() {
        if (this.zzcnd.zzcnc != null) {
            try {
                this.zzcnd.zzcnc.onRewardedVideoAdFailedToLoad(1);
            } catch (RemoteException e) {
                zzbbd.zzc("Could not notify onRewardedVideoAdFailedToLoad event.", e);
            }
        }
    }
}
