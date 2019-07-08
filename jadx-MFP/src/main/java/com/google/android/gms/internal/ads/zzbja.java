package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzbja extends zzex implements zzbiz {
    public static zzbiz zzaf(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.omid.IOmid");
        if (queryLocalInterface instanceof zzbiz) {
            return (zzbiz) queryLocalInterface;
        }
        return new zzbjb(iBinder);
    }
}
