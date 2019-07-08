package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

final class zzazu implements zzbbl<Throwable, T> {
    private final /* synthetic */ zzazy zzemz;

    zzazu(zzazs zzazs, zzazy zzazy) {
        this.zzemz = zzazy;
    }

    public final /* synthetic */ zzbcb zzf(Object obj) throws Exception {
        Throwable th = (Throwable) obj;
        zzaxz.zzb("Error occurred while dispatching http response in getter.", th);
        zzbv.zzlj().zza(th, "HttpGetter.deliverResponse.1");
        return zzbbq.zzm(this.zzemz.zzwf());
    }
}
