package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzavd extends zzew implements zzavb {
    zzavd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
    }

    public final void onRewardedVideoAdLoaded() throws RemoteException {
        zza(1, obtainAndWriteInterfaceToken());
    }

    public final void onRewardedVideoAdOpened() throws RemoteException {
        zza(2, obtainAndWriteInterfaceToken());
    }

    public final void onRewardedVideoStarted() throws RemoteException {
        zza(3, obtainAndWriteInterfaceToken());
    }

    public final void onRewardedVideoAdClosed() throws RemoteException {
        zza(4, obtainAndWriteInterfaceToken());
    }

    public final void zza(zzaur zzaur) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzaur);
        zza(5, obtainAndWriteInterfaceToken);
    }

    public final void onRewardedVideoAdLeftApplication() throws RemoteException {
        zza(6, obtainAndWriteInterfaceToken());
    }

    public final void onRewardedVideoAdFailedToLoad(int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(i);
        zza(7, obtainAndWriteInterfaceToken);
    }

    public final void onRewardedVideoCompleted() throws RemoteException {
        zza(8, obtainAndWriteInterfaceToken());
    }
}
