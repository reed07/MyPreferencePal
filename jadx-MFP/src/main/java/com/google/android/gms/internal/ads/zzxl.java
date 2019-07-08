package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzxl extends IInterface {
    void destroy() throws RemoteException;

    Bundle getAdMetadata() throws RemoteException;

    String getAdUnitId() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    zzyp getVideoController() throws RemoteException;

    boolean isLoading() throws RemoteException;

    boolean isReady() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setImmersiveMode(boolean z) throws RemoteException;

    void setManualImpressionsEnabled(boolean z) throws RemoteException;

    void setUserId(String str) throws RemoteException;

    void showInterstitial() throws RemoteException;

    void stopLoading() throws RemoteException;

    void zza(zzabg zzabg) throws RemoteException;

    void zza(zzaow zzaow) throws RemoteException;

    void zza(zzapc zzapc, String str) throws RemoteException;

    void zza(zzavb zzavb) throws RemoteException;

    void zza(zzwf zzwf) throws RemoteException;

    void zza(zzwx zzwx) throws RemoteException;

    void zza(zzxa zzxa) throws RemoteException;

    void zza(zzxq zzxq) throws RemoteException;

    void zza(zzxt zzxt) throws RemoteException;

    void zza(zzxz zzxz) throws RemoteException;

    void zza(zzyv zzyv) throws RemoteException;

    void zza(zzzw zzzw) throws RemoteException;

    void zzap(String str) throws RemoteException;

    boolean zzb(zzwb zzwb) throws RemoteException;

    IObjectWrapper zzie() throws RemoteException;

    zzwf zzif() throws RemoteException;

    void zzih() throws RemoteException;

    zzxt zzir() throws RemoteException;

    zzxa zzis() throws RemoteException;

    String zzje() throws RemoteException;
}
