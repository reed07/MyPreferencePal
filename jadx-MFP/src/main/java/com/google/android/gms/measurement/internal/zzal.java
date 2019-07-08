package com.google.android.gms.measurement.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzq;
import com.google.android.gms.internal.measurement.zzs;
import java.util.ArrayList;
import java.util.List;

public final class zzal extends zzq implements zzaj {
    zzal(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    public final void zza(zzag zzag, zzk zzk) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzs.zza(obtainAndWriteInterfaceToken, (Parcelable) zzag);
        zzs.zza(obtainAndWriteInterfaceToken, (Parcelable) zzk);
        zza(1, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzfu zzfu, zzk zzk) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzs.zza(obtainAndWriteInterfaceToken, (Parcelable) zzfu);
        zzs.zza(obtainAndWriteInterfaceToken, (Parcelable) zzk);
        zza(2, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzk zzk) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzs.zza(obtainAndWriteInterfaceToken, (Parcelable) zzk);
        zza(4, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzag zzag, String str, String str2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzs.zza(obtainAndWriteInterfaceToken, (Parcelable) zzag);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zza(5, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzk zzk) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzs.zza(obtainAndWriteInterfaceToken, (Parcelable) zzk);
        zza(6, obtainAndWriteInterfaceToken);
    }

    public final List<zzfu> zza(zzk zzk, boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzs.zza(obtainAndWriteInterfaceToken, (Parcelable) zzk);
        zzs.writeBoolean(obtainAndWriteInterfaceToken, z);
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken);
        ArrayList createTypedArrayList = transactAndReadException.createTypedArrayList(zzfu.CREATOR);
        transactAndReadException.recycle();
        return createTypedArrayList;
    }

    public final byte[] zza(zzag zzag, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzs.zza(obtainAndWriteInterfaceToken, (Parcelable) zzag);
        obtainAndWriteInterfaceToken.writeString(str);
        Parcel transactAndReadException = transactAndReadException(9, obtainAndWriteInterfaceToken);
        byte[] createByteArray = transactAndReadException.createByteArray();
        transactAndReadException.recycle();
        return createByteArray;
    }

    public final void zza(long j, String str, String str2, String str3) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeLong(j);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        obtainAndWriteInterfaceToken.writeString(str3);
        zza(10, obtainAndWriteInterfaceToken);
    }

    public final String zzc(zzk zzk) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzs.zza(obtainAndWriteInterfaceToken, (Parcelable) zzk);
        Parcel transactAndReadException = transactAndReadException(11, obtainAndWriteInterfaceToken);
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    public final void zza(zzo zzo, zzk zzk) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzs.zza(obtainAndWriteInterfaceToken, (Parcelable) zzo);
        zzs.zza(obtainAndWriteInterfaceToken, (Parcelable) zzk);
        zza(12, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzo zzo) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzs.zza(obtainAndWriteInterfaceToken, (Parcelable) zzo);
        zza(13, obtainAndWriteInterfaceToken);
    }

    public final List<zzfu> zza(String str, String str2, boolean z, zzk zzk) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzs.writeBoolean(obtainAndWriteInterfaceToken, z);
        zzs.zza(obtainAndWriteInterfaceToken, (Parcelable) zzk);
        Parcel transactAndReadException = transactAndReadException(14, obtainAndWriteInterfaceToken);
        ArrayList createTypedArrayList = transactAndReadException.createTypedArrayList(zzfu.CREATOR);
        transactAndReadException.recycle();
        return createTypedArrayList;
    }

    public final List<zzfu> zza(String str, String str2, String str3, boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        obtainAndWriteInterfaceToken.writeString(str3);
        zzs.writeBoolean(obtainAndWriteInterfaceToken, z);
        Parcel transactAndReadException = transactAndReadException(15, obtainAndWriteInterfaceToken);
        ArrayList createTypedArrayList = transactAndReadException.createTypedArrayList(zzfu.CREATOR);
        transactAndReadException.recycle();
        return createTypedArrayList;
    }

    public final List<zzo> zza(String str, String str2, zzk zzk) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzs.zza(obtainAndWriteInterfaceToken, (Parcelable) zzk);
        Parcel transactAndReadException = transactAndReadException(16, obtainAndWriteInterfaceToken);
        ArrayList createTypedArrayList = transactAndReadException.createTypedArrayList(zzo.CREATOR);
        transactAndReadException.recycle();
        return createTypedArrayList;
    }

    public final List<zzo> zze(String str, String str2, String str3) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        obtainAndWriteInterfaceToken.writeString(str3);
        Parcel transactAndReadException = transactAndReadException(17, obtainAndWriteInterfaceToken);
        ArrayList createTypedArrayList = transactAndReadException.createTypedArrayList(zzo.CREATOR);
        transactAndReadException.recycle();
        return createTypedArrayList;
    }

    public final void zzd(zzk zzk) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzs.zza(obtainAndWriteInterfaceToken, (Parcelable) zzk);
        zza(18, obtainAndWriteInterfaceToken);
    }
}
