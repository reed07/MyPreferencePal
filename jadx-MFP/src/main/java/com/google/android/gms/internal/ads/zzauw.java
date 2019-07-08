package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzauw extends IInterface {
    void destroy() throws RemoteException;

    Bundle getAdMetadata() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    boolean isLoaded() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setAppPackageName(String str) throws RemoteException;

    void setCustomData(String str) throws RemoteException;

    void setImmersiveMode(boolean z) throws RemoteException;

    void setUserId(String str) throws RemoteException;

    void show() throws RemoteException;

    void zza(zzauu zzauu) throws RemoteException;

    void zza(zzavb zzavb) throws RemoteException;

    void zza(zzavh zzavh) throws RemoteException;

    void zza(zzxq zzxq) throws RemoteException;

    void zzd(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zze(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzf(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzg(IObjectWrapper iObjectWrapper) throws RemoteException;
}
