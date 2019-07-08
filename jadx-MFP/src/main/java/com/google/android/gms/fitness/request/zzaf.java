package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.fitness.zzb;
import com.google.android.gms.internal.fitness.zzc;

public abstract class zzaf extends zzb implements zzae {
    public zzaf() {
        super("com.google.android.gms.fitness.request.IBleScanCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                onDeviceFound((BleDevice) zzc.zza(parcel, BleDevice.CREATOR));
                break;
            case 2:
                onScanStopped();
                break;
            default:
                return false;
        }
        return true;
    }
}
