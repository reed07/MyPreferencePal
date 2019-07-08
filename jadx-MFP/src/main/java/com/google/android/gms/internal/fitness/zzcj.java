package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

public final class zzcj extends zza implements zzch {
    zzcj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IListSubscriptionsCallback");
    }

    public final void zza(ListSubscriptionsResult listSubscriptionsResult) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) listSubscriptionsResult);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
