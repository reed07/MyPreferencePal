package com.google.android.gms.measurement.internal;

import android.content.Intent;

final /* synthetic */ class zzez implements Runnable {
    private final int zzacb;
    private final zzey zzasw;
    private final zzas zzasx;
    private final Intent zzasy;

    zzez(zzey zzey, int i, zzas zzas, Intent intent) {
        this.zzasw = zzey;
        this.zzacb = i;
        this.zzasx = zzas;
        this.zzasy = intent;
    }

    public final void run() {
        this.zzasw.zza(this.zzacb, this.zzasx, this.zzasy);
    }
}
