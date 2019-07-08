package com.google.android.gms.internal.ads;

final class zzlf implements Runnable {
    private final /* synthetic */ zzlc zzaxn;
    private final /* synthetic */ zzli zzaxo;

    zzlf(zzlc zzlc, zzli zzli) {
        this.zzaxn = zzlc;
        this.zzaxo = zzli;
    }

    public final void run() {
        this.zzaxo.release();
        int size = this.zzaxn.zzawy.size();
        for (int i = 0; i < size; i++) {
            ((zzls) this.zzaxn.zzawy.valueAt(i)).disable();
        }
    }
}
