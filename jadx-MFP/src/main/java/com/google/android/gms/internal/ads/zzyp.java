package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzyp extends IInterface {
    float getAspectRatio() throws RemoteException;

    int getPlaybackState() throws RemoteException;

    boolean isClickToExpandEnabled() throws RemoteException;

    boolean isCustomControlsEnabled() throws RemoteException;

    boolean isMuted() throws RemoteException;

    void mute(boolean z) throws RemoteException;

    void pause() throws RemoteException;

    void play() throws RemoteException;

    void zza(zzys zzys) throws RemoteException;

    float zzqf() throws RemoteException;

    float zzqg() throws RemoteException;

    zzys zzqh() throws RemoteException;
}
