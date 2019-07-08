package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzass extends zzew implements zzasq {
    zzass(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    public final zzasm zzb(zzasi zzasi) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzasi);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        zzasm zzasm = (zzasm) zzey.zza(transactAndReadException, zzasm.CREATOR);
        transactAndReadException.recycle();
        return zzasm;
    }

    public final void zza(zzasi zzasi, zzast zzast) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzasi);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzast);
        zza(2, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzatb zzatb, zzasw zzasw) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzatb);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzasw);
        zza(4, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzatb zzatb, zzasw zzasw) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzatb);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzasw);
        zza(5, obtainAndWriteInterfaceToken);
    }
}
