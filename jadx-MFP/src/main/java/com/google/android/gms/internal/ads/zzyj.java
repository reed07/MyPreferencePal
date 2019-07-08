package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.RemoteException;

public final class zzyj extends zzew implements zzyh {
    zzyj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IMuteThisAdListener");
    }

    public final void onAdMuted() throws RemoteException {
        zza(1, obtainAndWriteInterfaceToken());
    }
}
