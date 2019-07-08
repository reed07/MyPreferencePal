package com.google.android.gms.internal.ads;

final class zzapj implements Runnable {
    private final /* synthetic */ zzaxf zzbqm;
    private final /* synthetic */ zzaph zzdsq;

    zzapj(zzaph zzaph, zzaxf zzaxf) {
        this.zzdsq = zzaph;
        this.zzbqm = zzaxf;
    }

    public final void run() {
        synchronized (this.zzdsq.mLock) {
            zzaph zzaph = this.zzdsq;
            zzaph.zzdsj.zzb(this.zzbqm);
        }
    }
}
