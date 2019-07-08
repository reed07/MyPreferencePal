package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzamp implements Runnable {
    private final /* synthetic */ zzamj zzdoi;

    zzamp(zzamj zzamj) {
        this.zzdoi = zzamj;
    }

    public final void run() {
        try {
            this.zzdoi.zzdnz.onAdLeftApplication();
        } catch (RemoteException e) {
            zzbbd.zzd("#007 Could not call remote method.", e);
        }
    }
}
