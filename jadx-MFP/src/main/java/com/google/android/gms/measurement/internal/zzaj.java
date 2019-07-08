package com.google.android.gms.measurement.internal;

import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

public interface zzaj extends IInterface {
    List<zzfu> zza(zzk zzk, boolean z) throws RemoteException;

    List<zzo> zza(String str, String str2, zzk zzk) throws RemoteException;

    List<zzfu> zza(String str, String str2, String str3, boolean z) throws RemoteException;

    List<zzfu> zza(String str, String str2, boolean z, zzk zzk) throws RemoteException;

    void zza(long j, String str, String str2, String str3) throws RemoteException;

    void zza(zzag zzag, zzk zzk) throws RemoteException;

    void zza(zzag zzag, String str, String str2) throws RemoteException;

    void zza(zzfu zzfu, zzk zzk) throws RemoteException;

    void zza(zzk zzk) throws RemoteException;

    void zza(zzo zzo, zzk zzk) throws RemoteException;

    byte[] zza(zzag zzag, String str) throws RemoteException;

    void zzb(zzk zzk) throws RemoteException;

    void zzb(zzo zzo) throws RemoteException;

    String zzc(zzk zzk) throws RemoteException;

    void zzd(zzk zzk) throws RemoteException;

    List<zzo> zze(String str, String str2, String str3) throws RemoteException;
}
