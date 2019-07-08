package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.fitness.zza;
import com.google.android.gms.internal.fitness.zzc;

public final class zzag extends zza implements zzae {
    zzag(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.request.IBleScanCallback");
    }

    public final void onDeviceFound(BleDevice bleDevice) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) bleDevice);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }

    public final void onScanStopped() throws RemoteException {
        transactOneway(2, obtainAndWriteInterfaceToken());
    }
}
