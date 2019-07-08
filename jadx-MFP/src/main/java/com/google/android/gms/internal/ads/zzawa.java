package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzawa extends zzex implements zzavz {
    public zzawa() {
        super("com.google.android.gms.ads.internal.reward.mediation.client.IMediationRewardedVideoAdListener");
    }

    public static zzavz zzad(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.mediation.client.IMediationRewardedVideoAdListener");
        if (queryLocalInterface instanceof zzavz) {
            return (zzavz) queryLocalInterface;
        }
        return new zzawb(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzs(Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 2:
                zzc(Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 3:
                zzt(Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 4:
                zzu(Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 5:
                zzv(Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 6:
                zzw(Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 7:
                zza(Stub.asInterface(parcel.readStrongBinder()), (zzawd) zzey.zza(parcel, zzawd.CREATOR));
                break;
            case 8:
                zzx(Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 9:
                zzd(Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 10:
                zzy(Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 11:
                zzz(Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 12:
                zzc((Bundle) zzey.zza(parcel, Bundle.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
