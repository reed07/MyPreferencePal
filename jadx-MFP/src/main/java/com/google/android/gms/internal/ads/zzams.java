package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzams implements Runnable {
    private final /* synthetic */ zzamj zzdoi;

    zzams(zzamj zzamj) {
        this.zzdoi = zzamj;
    }

    public final void run() {
        try {
            this.zzdoi.zzdnz.onAdClosed();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }
}
