package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzxp extends zzew implements zzxo {
    zzxp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdManagerCreator");
    }

    public final IBinder zza(IObjectWrapper iObjectWrapper, zzwf zzwf, String str, zzalg zzalg, int i, int i2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwf);
        obtainAndWriteInterfaceToken.writeString(str);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzalg);
        obtainAndWriteInterfaceToken.writeInt(14300000);
        obtainAndWriteInterfaceToken.writeInt(i2);
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        transactAndReadException.recycle();
        return readStrongBinder;
    }
}
