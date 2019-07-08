package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzdq;

final class zzeq implements Runnable {
    private final /* synthetic */ String zzads;
    private final /* synthetic */ String zzadz;
    private final /* synthetic */ boolean zzaeg;
    private final /* synthetic */ zzdq zzagg;
    private final /* synthetic */ zzk zzaqn;
    private final /* synthetic */ zzeb zzasl;

    zzeq(zzeb zzeb, String str, String str2, boolean z, zzk zzk, zzdq zzdq) {
        this.zzasl = zzeb;
        this.zzads = str;
        this.zzadz = str2;
        this.zzaeg = z;
        this.zzaqn = zzk;
        this.zzagg = zzdq;
    }

    public final void run() {
        Bundle bundle = new Bundle();
        try {
            zzaj zzd = this.zzasl.zzasf;
            if (zzd == null) {
                this.zzasl.zzgt().zzjg().zze("Failed to get user properties", this.zzads, this.zzadz);
                return;
            }
            bundle = zzfx.zzd(zzd.zza(this.zzads, this.zzadz, this.zzaeg, this.zzaqn));
            this.zzasl.zzcy();
            this.zzasl.zzgr().zza(this.zzagg, bundle);
        } catch (RemoteException e) {
            this.zzasl.zzgt().zzjg().zze("Failed to get user properties", this.zzads, e);
        } finally {
            this.zzasl.zzgr().zza(this.zzagg, bundle);
        }
    }
}
