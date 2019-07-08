package com.google.android.gms.internal.measurement;

import android.os.Looper;

final class zzca implements Runnable {
    private final /* synthetic */ zzbz zzyq;

    zzca(zzbz zzbz) {
        this.zzyq = zzbz;
    }

    public final void run() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.zzyq.zzvy.zzca().zza((Runnable) this);
            return;
        }
        boolean zzej = this.zzyq.zzej();
        this.zzyq.zzyp = 0;
        if (zzej) {
            this.zzyq.run();
        }
    }
}
