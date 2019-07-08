package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzfb extends zzex implements zzfa {
    public static zzfa zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.clearcut.IClearcut");
        if (queryLocalInterface instanceof zzfa) {
            return (zzfa) queryLocalInterface;
        }
        return new zzfc(iBinder);
    }
}
