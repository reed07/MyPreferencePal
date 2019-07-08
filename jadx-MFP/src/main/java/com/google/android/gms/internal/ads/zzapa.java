package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public final class zzapa extends zzex implements zzaoz {
    public static zzaoz zzz(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
        if (queryLocalInterface instanceof zzaoz) {
            return (zzaoz) queryLocalInterface;
        }
        return new zzapb(iBinder);
    }
}
