package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzct;
import com.google.android.gms.internal.ads.zzcu;
import java.util.concurrent.Callable;

final class zzbs implements Callable<zzcu> {
    private final /* synthetic */ zzbp zzbra;

    zzbs(zzbp zzbp) {
        this.zzbra = zzbp;
    }

    public final /* synthetic */ Object call() throws Exception {
        return new zzcu(zzct.zza(this.zzbra.zzbob.zzdp, this.zzbra.mContext, false));
    }
}
