package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzyi extends zzex implements zzyh {
    public zzyi() {
        super("com.google.android.gms.ads.internal.client.IMuteThisAdListener");
    }

    public static zzyh zze(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMuteThisAdListener");
        if (queryLocalInterface instanceof zzyh) {
            return (zzyh) queryLocalInterface;
        }
        return new zzyj(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        onAdMuted();
        parcel2.writeNoException();
        return true;
    }
}
