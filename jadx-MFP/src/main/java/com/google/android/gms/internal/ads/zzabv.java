package com.google.android.gms.internal.ads;

final class zzabv implements Runnable {
    private final /* synthetic */ zzabu zzdbn;

    zzabv(zzabu zzabu) {
        this.zzdbn = zzabu;
    }

    public final void run() {
        if (this.zzdbn.zzdbj != null) {
            this.zzdbn.zzdbj.zzsr();
            this.zzdbn.zzdbj.zzsq();
            this.zzdbn.zzdbj.zzjm();
        }
        this.zzdbn.zzdbj = null;
    }
}
