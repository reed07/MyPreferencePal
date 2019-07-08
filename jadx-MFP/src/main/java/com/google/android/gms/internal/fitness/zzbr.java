package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.GoalsResult;

public abstract class zzbr extends zzb implements zzbq {
    public zzbr() {
        super("com.google.android.gms.fitness.internal.IGoalsReadCallback");
    }

    public static zzbq zzf(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoalsReadCallback");
        if (queryLocalInterface instanceof zzbq) {
            return (zzbq) queryLocalInterface;
        }
        return new zzbs(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((GoalsResult) zzc.zza(parcel, GoalsResult.CREATOR));
        return true;
    }
}
