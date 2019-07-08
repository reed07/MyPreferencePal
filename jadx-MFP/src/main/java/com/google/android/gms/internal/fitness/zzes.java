package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.BleDevicesResult;

public abstract class zzes extends zzb implements zzer {
    public zzes() {
        super("com.google.android.gms.fitness.internal.ble.IBleDevicesCallback");
    }

    public static zzer zzk(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.ble.IBleDevicesCallback");
        if (queryLocalInterface instanceof zzer) {
            return (zzer) queryLocalInterface;
        }
        return new zzet(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((BleDevicesResult) zzc.zza(parcel, BleDevicesResult.CREATOR));
        return true;
    }
}
