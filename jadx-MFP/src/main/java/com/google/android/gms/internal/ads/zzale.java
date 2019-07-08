package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzale implements Runnable {
    private final /* synthetic */ zzakx zzdns;

    zzale(zzald zzald, zzakx zzakx) {
        this.zzdns = zzakx;
    }

    public final void run() {
        try {
            this.zzdns.zzdnc.destroy();
        } catch (RemoteException e) {
            zzaxz.zzc("Could not destroy mediation adapter.", e);
        }
    }
}
