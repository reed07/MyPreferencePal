package com.google.firebase.appindexing.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.internal.icing.zza;
import com.google.android.gms.internal.icing.zzc;

public final class zzt extends zza implements zzs {
    zzt(IBinder iBinder) {
        super(iBinder, "com.google.firebase.appindexing.internal.IAppIndexingService");
    }

    public final zzf zza(IStatusCallback iStatusCallback, zzx zzx) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) iStatusCallback);
        zzc.zza(zza, (Parcelable) zzx);
        Parcel zza2 = zza(8, zza);
        zzf zzf = (zzf) zzc.zza(zza2, zzf.CREATOR);
        zza2.recycle();
        return zzf;
    }
}
