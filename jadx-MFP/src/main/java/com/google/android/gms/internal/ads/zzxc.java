package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzxc extends zzew implements zzxa {
    zzxc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdListener");
    }

    public final void onAdClosed() throws RemoteException {
        zza(1, obtainAndWriteInterfaceToken());
    }

    public final void onAdFailedToLoad(int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(i);
        zza(2, obtainAndWriteInterfaceToken);
    }

    public final void onAdLeftApplication() throws RemoteException {
        zza(3, obtainAndWriteInterfaceToken());
    }

    public final void onAdLoaded() throws RemoteException {
        zza(4, obtainAndWriteInterfaceToken());
    }

    public final void onAdOpened() throws RemoteException {
        zza(5, obtainAndWriteInterfaceToken());
    }

    public final void onAdClicked() throws RemoteException {
        zza(6, obtainAndWriteInterfaceToken());
    }

    public final void onAdImpression() throws RemoteException {
        zza(7, obtainAndWriteInterfaceToken());
    }
}
