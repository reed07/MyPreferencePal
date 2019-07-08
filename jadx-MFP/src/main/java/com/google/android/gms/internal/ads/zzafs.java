package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzafs extends zzew implements zzafr {
    zzafs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.httpcache.IHttpAssetsCacheService");
    }

    public final void zza(zzafl zzafl, zzafp zzafp) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzafl);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzafp);
        zzb(2, obtainAndWriteInterfaceToken);
    }
}
