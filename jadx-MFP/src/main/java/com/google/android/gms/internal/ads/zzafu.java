package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;

final class zzafu implements zzbbl<zzafr, ParcelFileDescriptor> {
    private final /* synthetic */ zzafl zzdgn;

    zzafu(zzaft zzaft, zzafl zzafl) {
        this.zzdgn = zzafl;
    }

    public final /* synthetic */ zzbcb zzf(Object obj) throws Exception {
        zzafr zzafr = (zzafr) obj;
        zzbcl zzbcl = new zzbcl();
        zzafr.zza(this.zzdgn, new zzafv(this, zzbcl));
        return zzbcl;
    }
}
