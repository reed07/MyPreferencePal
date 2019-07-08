package com.google.android.gms.internal.ads;

final class zzapp implements Runnable {
    private final /* synthetic */ zzaxf zzdsr;
    private final /* synthetic */ zzapo zzdss;

    zzapp(zzapo zzapo, zzaxf zzaxf) {
        this.zzdss = zzapo;
        this.zzdsr = zzaxf;
    }

    public final void run() {
        this.zzdss.zzdsj.zzb(this.zzdsr);
    }
}
