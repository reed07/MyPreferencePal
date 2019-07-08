package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionStopResult;

public abstract class zzco extends zzb implements zzcn {
    public zzco() {
        super("com.google.android.gms.fitness.internal.ISessionStopCallback");
    }

    public static zzcn zzi(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.ISessionStopCallback");
        if (queryLocalInterface instanceof zzcn) {
            return (zzcn) queryLocalInterface;
        }
        return new zzcp(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((SessionStopResult) zzc.zza(parcel, SessionStopResult.CREATOR));
        return true;
    }
}
