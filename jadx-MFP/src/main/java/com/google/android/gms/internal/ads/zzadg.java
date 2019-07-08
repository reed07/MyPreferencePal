package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzadg extends zzex implements zzadf {
    public zzadg() {
        super("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
    }

    public static zzadf zzk(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
        if (queryLocalInterface instanceof zzadf) {
            return (zzadf) queryLocalInterface;
        }
        return new zzadh(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzb(parcel.readString(), Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 2:
                IObjectWrapper zzbm = zzbm(parcel.readString());
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzbm);
                break;
            case 3:
                zza(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 4:
                destroy();
                parcel2.writeNoException();
                break;
            case 5:
                zzb(Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                break;
            case 6:
                zzc(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
