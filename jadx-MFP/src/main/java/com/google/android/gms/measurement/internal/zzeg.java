package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzdq;

final class zzeg implements Runnable {
    private final /* synthetic */ zzdq zzagg;
    private final /* synthetic */ zzk zzaqn;
    private final /* synthetic */ zzeb zzasl;

    zzeg(zzeb zzeb, zzk zzk, zzdq zzdq) {
        this.zzasl = zzeb;
        this.zzaqn = zzk;
        this.zzagg = zzdq;
    }

    public final void run() {
        try {
            zzaj zzd = this.zzasl.zzasf;
            if (zzd == null) {
                this.zzasl.zzgt().zzjg().zzby("Failed to get app instance id");
                return;
            }
            String zzc = zzd.zzc(this.zzaqn);
            if (zzc != null) {
                this.zzasl.zzgj().zzcp(zzc);
                this.zzasl.zzgu().zzanj.zzcd(zzc);
            }
            this.zzasl.zzcy();
            this.zzasl.zzgr().zzb(this.zzagg, zzc);
        } catch (RemoteException e) {
            this.zzasl.zzgt().zzjg().zzg("Failed to get app instance id", e);
        } finally {
            this.zzasl.zzgr().zzb(this.zzagg, null);
        }
    }
}
