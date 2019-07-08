package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzyb extends zzew implements zzxz {
    zzyb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
    }

    public final long getValue() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken());
        long readLong = transactAndReadException.readLong();
        transactAndReadException.recycle();
        return readLong;
    }
}
