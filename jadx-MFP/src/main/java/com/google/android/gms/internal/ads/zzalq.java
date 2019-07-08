package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzalq extends zzex implements zzalp {
    public zzalq() {
        super("com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        int zzur = zzur();
        parcel2.writeNoException();
        parcel2.writeInt(zzur);
        return true;
    }
}
