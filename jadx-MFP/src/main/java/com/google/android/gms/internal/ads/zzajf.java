package com.google.android.gms.internal.ads;

final class zzajf implements zzbco {
    private final /* synthetic */ zzait zzdjj;
    private final /* synthetic */ zzajm zzdjm;

    zzajf(zzait zzait, zzajm zzajm) {
        this.zzdjj = zzait;
        this.zzdjm = zzajm;
    }

    public final void run() {
        synchronized (this.zzdjj.mLock) {
            this.zzdjj.zzdiz = 1;
            zzaxz.v("Failed loading new engine. Marking new engine destroyable.");
            this.zzdjm.zzuf();
        }
    }
}
