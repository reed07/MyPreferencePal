package com.google.android.gms.fitness.data;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.fitness.zzb;
import com.google.android.gms.internal.fitness.zzc;

public abstract class zzu extends zzb implements zzt {
    public zzu() {
        super("com.google.android.gms.fitness.data.IDataSourceListener");
    }

    public static zzt zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.data.IDataSourceListener");
        if (queryLocalInterface instanceof zzt) {
            return (zzt) queryLocalInterface;
        }
        return new zzv(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzc((DataPoint) zzc.zza(parcel, DataPoint.CREATOR));
        return true;
    }
}
