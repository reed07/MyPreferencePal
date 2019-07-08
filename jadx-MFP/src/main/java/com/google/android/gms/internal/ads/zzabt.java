package com.google.android.gms.internal.ads;

final class zzabt implements Runnable {
    private final /* synthetic */ zzabs zzdbk;

    zzabt(zzabs zzabs) {
        this.zzdbk = zzabs;
    }

    public final void run() {
        if (this.zzdbk.zzdbj != null) {
            this.zzdbk.zzdbj.zzsr();
            this.zzdbk.zzdbj.zzsq();
            this.zzdbk.zzdbj.zzjm();
        }
        this.zzdbk.zzdbj = null;
    }
}
