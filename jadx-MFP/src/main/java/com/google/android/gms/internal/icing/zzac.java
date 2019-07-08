package com.google.android.gms.internal.icing;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.firebase.appindexing.internal.zza;

public final class zzac extends zza implements zzab {
    zzac(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
    }

    public final void zza(zzad zzad, String str, zzx[] zzxArr) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzad);
        zza.writeString(null);
        zza.writeTypedArray(zzxArr, 0);
        zzb(1, zza);
    }

    public final void zza(zzad zzad, zza[] zzaArr) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzad);
        zza.writeTypedArray(zzaArr, 0);
        zzb(7, zza);
    }
}
