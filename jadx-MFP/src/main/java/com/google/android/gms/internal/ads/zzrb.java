package com.google.android.gms.internal.ads;

import android.view.Surface;

final class zzrb implements Runnable {
    private final /* synthetic */ zzqv zzbkp;
    private final /* synthetic */ Surface zzbkw;

    zzrb(zzqv zzqv, Surface surface) {
        this.zzbkp = zzqv;
        this.zzbkw = surface;
    }

    public final void run() {
        this.zzbkp.zzbko.zza(this.zzbkw);
    }
}
