package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzahh implements Runnable {
    private final /* synthetic */ zzahi zzdgv;
    private final /* synthetic */ zzahj zzdgw;

    zzahh(zzagj zzagj, zzahi zzahi, zzahj zzahj) {
        this.zzdgv = zzahi;
        this.zzdgw = zzahj;
    }

    public final void run() {
        try {
            this.zzdgv.zzb(this.zzdgw);
        } catch (RemoteException e) {
            zzaxz.zzc("Could not propagate interstitial ad event.", e);
        }
    }
}
