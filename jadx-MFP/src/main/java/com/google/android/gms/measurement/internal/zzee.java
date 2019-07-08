package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

final class zzee implements Runnable {
    private final /* synthetic */ zzk zzaqn;
    private final /* synthetic */ zzeb zzasl;

    zzee(zzeb zzeb, zzk zzk) {
        this.zzasl = zzeb;
        this.zzaqn = zzk;
    }

    public final void run() {
        zzaj zzd = this.zzasl.zzasf;
        if (zzd == null) {
            this.zzasl.zzgt().zzjg().zzby("Failed to reset data on the service; null service");
            return;
        }
        try {
            zzd.zzd(this.zzaqn);
        } catch (RemoteException e) {
            this.zzasl.zzgt().zzjg().zzg("Failed to reset data on the service", e);
        }
        this.zzasl.zzcy();
    }
}
