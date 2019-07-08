package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzyc extends IInterface {
    void setAppMuted(boolean z) throws RemoteException;

    void setAppVolume(float f) throws RemoteException;

    void zza() throws RemoteException;

    void zza(zzalg zzalg) throws RemoteException;

    void zza(String str, IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzat(String str) throws RemoteException;

    void zzau(String str) throws RemoteException;

    void zzb(IObjectWrapper iObjectWrapper, String str) throws RemoteException;

    float zzkj() throws RemoteException;

    boolean zzkk() throws RemoteException;

    String zzkl() throws RemoteException;
}
