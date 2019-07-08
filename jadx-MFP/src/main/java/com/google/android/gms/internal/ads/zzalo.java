package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzalo extends zzew implements zzalm {
    zzalo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
    }

    public final void onAdClicked() throws RemoteException {
        zza(1, obtainAndWriteInterfaceToken());
    }

    public final void onAdClosed() throws RemoteException {
        zza(2, obtainAndWriteInterfaceToken());
    }

    public final void onAdFailedToLoad(int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(i);
        zza(3, obtainAndWriteInterfaceToken);
    }

    public final void onAdLeftApplication() throws RemoteException {
        zza(4, obtainAndWriteInterfaceToken());
    }

    public final void onAdOpened() throws RemoteException {
        zza(5, obtainAndWriteInterfaceToken());
    }

    public final void onAdLoaded() throws RemoteException {
        zza(6, obtainAndWriteInterfaceToken());
    }

    public final void zza(zzalp zzalp) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzalp);
        zza(7, obtainAndWriteInterfaceToken);
    }

    public final void onAdImpression() throws RemoteException {
        zza(8, obtainAndWriteInterfaceToken());
    }

    public final void onAppEvent(String str, String str2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zza(9, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzadx zzadx, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzadx);
        obtainAndWriteInterfaceToken.writeString(str);
        zza(10, obtainAndWriteInterfaceToken);
    }

    public final void onVideoEnd() throws RemoteException {
        zza(11, obtainAndWriteInterfaceToken());
    }

    public final void zzcl(String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        zza(12, obtainAndWriteInterfaceToken);
    }

    public final void zzul() throws RemoteException {
        zza(13, obtainAndWriteInterfaceToken());
    }

    public final void zzc(zzawd zzawd) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzawd);
        zza(14, obtainAndWriteInterfaceToken);
    }

    public final void onVideoPause() throws RemoteException {
        zza(15, obtainAndWriteInterfaceToken());
    }
}
