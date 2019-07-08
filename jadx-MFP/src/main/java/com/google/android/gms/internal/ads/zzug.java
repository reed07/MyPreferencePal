package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;

final class zzug implements BaseConnectionCallbacks {
    final /* synthetic */ zzue zzcad;
    private final /* synthetic */ zzty zzcae;
    private final /* synthetic */ zzbcl zzcaf;

    zzug(zzue zzue, zzty zzty, zzbcl zzbcl) {
        this.zzcad = zzue;
        this.zzcae = zzty;
        this.zzcaf = zzbcl;
    }

    public final void onConnectionSuspended(int i) {
    }

    public final void onConnected(@Nullable Bundle bundle) {
        synchronized (this.zzcad.mLock) {
            if (!this.zzcad.zzcac) {
                this.zzcad.zzcac = true;
                zztx zzd = this.zzcad.zzbzr;
                if (zzd != null) {
                    this.zzcaf.zza(new zzui(this.zzcaf, zzayf.zzc(new zzuh(this, zzd, this.zzcae, this.zzcaf))), zzbcg.zzepp);
                }
            }
        }
    }
}
