package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.RemoteException;

public final class zzxs extends zzew implements zzxq {
    zzxs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdMetadataListener");
    }

    public final void onAdMetadataChanged() throws RemoteException {
        zza(1, obtainAndWriteInterfaceToken());
    }
}
