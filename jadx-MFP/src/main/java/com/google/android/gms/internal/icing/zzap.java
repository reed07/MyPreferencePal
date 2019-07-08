package com.google.android.gms.internal.icing;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzap extends zza implements zzao {
    zzap(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.search.internal.ISearchAuthService");
    }

    public final void zza(zzam zzam, String str, String str2) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzam);
        zza.writeString(str);
        zza.writeString(str2);
        zzb(1, zza);
    }

    public final void zzb(zzam zzam, String str, String str2) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzam);
        zza.writeString(str);
        zza.writeString(str2);
        zzb(2, zza);
    }
}
