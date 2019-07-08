package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzbh extends zzau {
    private final zzx zzsq = new zzx();

    zzbh(zzaw zzaw) {
        super(zzaw);
    }

    /* access modifiers changed from: protected */
    public final void zzag() {
        zzca().zzad().zzb(this.zzsq);
        zzdh zzce = zzce();
        String zzaj = zzce.zzaj();
        if (zzaj != null) {
            this.zzsq.setAppName(zzaj);
        }
        String zzak = zzce.zzak();
        if (zzak != null) {
            this.zzsq.setAppVersion(zzak);
        }
    }

    public final zzx zzdf() {
        zzcl();
        return this.zzsq;
    }
}
