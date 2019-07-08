package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionStopResult;

public final class zzcp extends zza implements zzcn {
    zzcp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.ISessionStopCallback");
    }

    public final void zza(SessionStopResult sessionStopResult) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) sessionStopResult);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
