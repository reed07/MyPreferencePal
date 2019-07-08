package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzasu extends zzex implements zzast {
    public zzasu() {
        super("com.google.android.gms.ads.internal.request.IAdResponseListener");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((zzasm) zzey.zza(parcel, zzasm.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
