package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzalm extends IInterface {
    void onAdClicked() throws RemoteException;

    void onAdClosed() throws RemoteException;

    void onAdFailedToLoad(int i) throws RemoteException;

    void onAdImpression() throws RemoteException;

    void onAdLeftApplication() throws RemoteException;

    void onAdLoaded() throws RemoteException;

    void onAdOpened() throws RemoteException;

    void onAppEvent(String str, String str2) throws RemoteException;

    void onVideoEnd() throws RemoteException;

    void onVideoPause() throws RemoteException;

    void zza(zzalp zzalp) throws RemoteException;

    void zzb(zzadx zzadx, String str) throws RemoteException;

    void zzc(zzawd zzawd) throws RemoteException;

    void zzcl(String str) throws RemoteException;

    void zzul() throws RemoteException;
}
