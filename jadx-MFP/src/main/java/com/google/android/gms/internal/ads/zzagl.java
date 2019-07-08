package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzagl implements zzahi {
    zzagl(zzagk zzagk) {
    }

    public final void zzb(zzahj zzahj) throws RemoteException {
        if (zzahj.zzbnn != null) {
            zzahj.zzbnn.onAdClosed();
        }
    }
}
