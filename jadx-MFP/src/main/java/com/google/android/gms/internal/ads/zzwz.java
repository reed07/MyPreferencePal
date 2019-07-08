package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.RemoteException;

public final class zzwz extends zzew implements zzwx {
    zzwz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdClickListener");
    }

    public final void onAdClicked() throws RemoteException {
        zza(1, obtainAndWriteInterfaceToken());
    }
}
