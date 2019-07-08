package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionReadResult;

public final class zzcm extends zza implements zzck {
    zzcm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.ISessionReadCallback");
    }

    public final void zza(SessionReadResult sessionReadResult) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) sessionReadResult);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
