package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzec implements zzfw {
    private final /* synthetic */ zzeb zzbdx;

    zzec(zzeb zzeb) {
        this.zzbdx = zzeb;
    }

    public final void zza(zzbw zzbw) {
        this.zzbdx.zze(zzbw.zzov());
    }

    public final void zzb(zzbw zzbw) {
        this.zzbdx.zze(zzbw.zzov());
        long zzov = zzbw.zzov();
        StringBuilder sb = new StringBuilder(57);
        sb.append("Permanent failure dispatching hitId: ");
        sb.append(zzov);
        zzdi.v(sb.toString());
    }

    public final void zzc(zzbw zzbw) {
        long zzow = zzbw.zzow();
        if (zzow == 0) {
            this.zzbdx.zze(zzbw.zzov(), this.zzbdx.zzrz.currentTimeMillis());
            return;
        }
        if (zzow + 14400000 < this.zzbdx.zzrz.currentTimeMillis()) {
            this.zzbdx.zze(zzbw.zzov());
            long zzov = zzbw.zzov();
            StringBuilder sb = new StringBuilder(47);
            sb.append("Giving up on failed hitId: ");
            sb.append(zzov);
            zzdi.v(sb.toString());
        }
    }
}
