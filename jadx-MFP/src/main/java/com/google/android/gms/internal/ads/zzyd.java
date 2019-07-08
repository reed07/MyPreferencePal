package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzyd extends zzex implements zzyc {
    public zzyd() {
        super("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza();
                parcel2.writeNoException();
                break;
            case 2:
                setAppVolume(parcel.readFloat());
                parcel2.writeNoException();
                break;
            case 3:
                zzat(parcel.readString());
                parcel2.writeNoException();
                break;
            case 4:
                setAppMuted(zzey.zza(parcel));
                parcel2.writeNoException();
                break;
            case 5:
                zzb(Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                break;
            case 6:
                zza(parcel.readString(), Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 7:
                float zzkj = zzkj();
                parcel2.writeNoException();
                parcel2.writeFloat(zzkj);
                break;
            case 8:
                boolean zzkk = zzkk();
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, zzkk);
                break;
            case 9:
                String zzkl = zzkl();
                parcel2.writeNoException();
                parcel2.writeString(zzkl);
                break;
            case 10:
                zzau(parcel.readString());
                parcel2.writeNoException();
                break;
            case 11:
                zza(zzalh.zzu(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
