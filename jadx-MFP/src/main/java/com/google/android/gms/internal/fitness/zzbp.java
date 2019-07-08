package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataTypeResult;

public final class zzbp extends zza implements zzbn {
    zzbp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IDataTypeCallback");
    }

    public final void zza(DataTypeResult dataTypeResult) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) dataTypeResult);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
