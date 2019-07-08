package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzre;

final class zzez implements Runnable {
    private final /* synthetic */ zzex zzbez;
    private final /* synthetic */ zzre zzbfa;

    zzez(zzex zzex, zzre zzre) {
        this.zzbez = zzex;
        this.zzbfa = zzre;
    }

    public final void run() {
        this.zzbez.zzb(this.zzbfa);
    }
}
