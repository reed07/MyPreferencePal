package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

final class zzafv extends zzafq {
    private final /* synthetic */ zzbcl zzcaf;

    zzafv(zzafu zzafu, zzbcl zzbcl) {
        this.zzcaf = zzbcl;
    }

    public final void zza(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        this.zzcaf.set(parcelFileDescriptor);
    }
}
