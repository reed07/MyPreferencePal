package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzagm implements zzahi {
    private final /* synthetic */ int zzdgr;

    zzagm(zzagk zzagk, int i) {
        this.zzdgr = i;
    }

    public final void zzb(zzahj zzahj) throws RemoteException {
        if (zzahj.zzbnn != null) {
            zzahj.zzbnn.onAdFailedToLoad(this.zzdgr);
        }
    }
}
