package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataSourcesResult;

public final class zzbm extends zza implements zzbk {
    zzbm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IDataSourcesCallback");
    }

    public final void zza(DataSourcesResult dataSourcesResult) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) dataSourcesResult);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
