package com.google.android.gms.internal.ads;

import android.os.Bundle;

@zzark
final class zzaxi {
    private long zzeik = -1;
    private long zzeil = -1;
    private final /* synthetic */ zzaxh zzeim;

    public zzaxi(zzaxh zzaxh) {
        this.zzeim = zzaxh;
    }

    public final long zzxz() {
        return this.zzeil;
    }

    public final void zzya() {
        this.zzeil = this.zzeim.zzehz.elapsedRealtime();
    }

    public final void zzyb() {
        this.zzeik = this.zzeim.zzehz.elapsedRealtime();
    }

    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putLong("topen", this.zzeik);
        bundle.putLong("tclose", this.zzeil);
        return bundle;
    }
}
