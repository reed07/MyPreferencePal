package com.google.android.gms.internal.ads;

final class zzaby implements Runnable {
    private final /* synthetic */ zzabw zzdbr;

    zzaby(zzabw zzabw) {
        this.zzdbr = zzabw;
    }

    public final void run() {
        if (this.zzdbr.zzdbj != null) {
            this.zzdbr.zzdbj.zzsr();
            this.zzdbr.zzdbj.zzsq();
        }
        this.zzdbr.zzdbj = null;
    }
}
