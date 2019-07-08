package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

final class zzaxm extends zzaxv {
    private final /* synthetic */ zzaxk zzejf;

    zzaxm(zzaxk zzaxk) {
        this.zzejf = zzaxk;
    }

    public final void onStop() {
    }

    public final void zzki() {
        zzaap zzaap = new zzaap(this.zzejf.mContext, this.zzejf.zzbob.zzdp);
        synchronized (this.zzejf.mLock) {
            try {
                zzbv.zzlo();
                zzaas.zza(this.zzejf.zzeix, zzaap);
            } catch (IllegalArgumentException e) {
                zzaxz.zzc("Cannot config CSI reporter.", e);
            }
        }
    }
}
