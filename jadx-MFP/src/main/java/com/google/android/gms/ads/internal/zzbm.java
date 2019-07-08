package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzwb;
import java.lang.ref.WeakReference;

final class zzbm implements Runnable {
    private final /* synthetic */ WeakReference zzbqs;
    private final /* synthetic */ zzbl zzbqt;

    zzbm(zzbl zzbl, WeakReference weakReference) {
        this.zzbqt = zzbl;
        this.zzbqs = weakReference;
    }

    public final void run() {
        this.zzbqt.zzbqp = false;
        zza zza = (zza) this.zzbqs.get();
        if (zza != null) {
            zzwb zzb = this.zzbqt.zzbqo;
            if (zza.zzc(zzb)) {
                zza.zzb(zzb);
            } else {
                zzaxz.zzen("Ad is not visible. Not refreshing ad.");
                zza.zzblr.zzg(zzb);
            }
        }
    }
}
