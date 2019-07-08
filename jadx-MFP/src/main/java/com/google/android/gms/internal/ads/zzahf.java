package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzahf implements zzahi {
    private final /* synthetic */ int zzdgr;

    zzahf(zzagy zzagy, int i) {
        this.zzdgr = i;
    }

    public final void zzb(zzahj zzahj) throws RemoteException {
        if (zzahj.zzdhb != null) {
            zzahj.zzdhb.onRewardedVideoAdFailedToLoad(this.zzdgr);
        }
    }
}
