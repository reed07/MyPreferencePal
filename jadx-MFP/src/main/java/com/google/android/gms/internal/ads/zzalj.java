package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzalj extends IInterface {
    void destroy() throws RemoteException;

    Bundle getInterstitialAdapterInfo() throws RemoteException;

    zzyp getVideoController() throws RemoteException;

    boolean isInitialized() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setImmersiveMode(boolean z) throws RemoteException;

    void showInterstitial() throws RemoteException;

    void showVideo() throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzavz zzavz, List<String> list) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzwb zzwb, String str, zzalm zzalm) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzwb zzwb, String str, zzavz zzavz, String str2) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzwb zzwb, String str, String str2, zzalm zzalm) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzwb zzwb, String str, String str2, zzalm zzalm, zzacp zzacp, List<String> list) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzwf zzwf, zzwb zzwb, String str, zzalm zzalm) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzwf zzwf, zzwb zzwb, String str, String str2, zzalm zzalm) throws RemoteException;

    void zza(zzwb zzwb, String str, String str2) throws RemoteException;

    void zzc(zzwb zzwb, String str) throws RemoteException;

    void zzj(IObjectWrapper iObjectWrapper) throws RemoteException;

    IObjectWrapper zzut() throws RemoteException;

    zzals zzuu() throws RemoteException;

    zzalv zzuv() throws RemoteException;

    Bundle zzuw() throws RemoteException;

    Bundle zzux() throws RemoteException;

    boolean zzuy() throws RemoteException;

    zzadx zzuz() throws RemoteException;

    zzaly zzva() throws RemoteException;
}
