package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

final class zzel implements Runnable {
    private final /* synthetic */ zzk zzaqn;
    private final /* synthetic */ zzeb zzasl;

    zzel(zzeb zzeb, zzk zzk) {
        this.zzasl = zzeb;
        this.zzaqn = zzk;
    }

    public final void run() {
        zzaj zzd = this.zzasl.zzasf;
        if (zzd == null) {
            this.zzasl.zzgt().zzjg().zzby("Failed to send measurementEnabled to service");
            return;
        }
        try {
            zzd.zzb(this.zzaqn);
            this.zzasl.zzcy();
        } catch (RemoteException e) {
            this.zzasl.zzgt().zzjg().zzg("Failed to send measurementEnabled to the service", e);
        }
    }
}
