package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

final class zzei implements Runnable {
    private final /* synthetic */ zzdx zzasd;
    private final /* synthetic */ zzeb zzasl;

    zzei(zzeb zzeb, zzdx zzdx) {
        this.zzasl = zzeb;
        this.zzasd = zzdx;
    }

    public final void run() {
        zzaj zzd = this.zzasl.zzasf;
        if (zzd == null) {
            this.zzasl.zzgt().zzjg().zzby("Failed to send current screen to service");
            return;
        }
        try {
            if (this.zzasd == null) {
                zzd.zza(0, (String) null, (String) null, this.zzasl.getContext().getPackageName());
            } else {
                zzd.zza(this.zzasd.zzarr, this.zzasd.zzuw, this.zzasd.zzarq, this.zzasl.getContext().getPackageName());
            }
            this.zzasl.zzcy();
        } catch (RemoteException e) {
            this.zzasl.zzgt().zzjg().zzg("Failed to send current screen to the service", e);
        }
    }
}
