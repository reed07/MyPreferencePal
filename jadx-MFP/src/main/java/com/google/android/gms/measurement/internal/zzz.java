package com.google.android.gms.measurement.internal;

final class zzz implements Runnable {
    private final /* synthetic */ zzct zzaho;
    private final /* synthetic */ zzy zzahp;

    zzz(zzy zzy, zzct zzct) {
        this.zzahp = zzy;
        this.zzaho = zzct;
    }

    public final void run() {
        this.zzaho.zzgw();
        if (zzn.isMainThread()) {
            this.zzaho.zzgs().zzc((Runnable) this);
            return;
        }
        boolean zzej = this.zzahp.zzej();
        this.zzahp.zzyp = 0;
        if (zzej) {
            this.zzahp.run();
        }
    }
}
