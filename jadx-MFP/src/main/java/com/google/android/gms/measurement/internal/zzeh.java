package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

final class zzeh implements Runnable {
    private final /* synthetic */ zzk zzaqn;
    private final /* synthetic */ zzeb zzasl;

    zzeh(zzeb zzeb, zzk zzk) {
        this.zzasl = zzeb;
        this.zzaqn = zzk;
    }

    public final void run() {
        zzaj zzd = this.zzasl.zzasf;
        if (zzd == null) {
            this.zzasl.zzgt().zzjg().zzby("Discarding data. Failed to send app launch");
            return;
        }
        try {
            zzd.zza(this.zzaqn);
            this.zzasl.zza(zzd, (AbstractSafeParcelable) null, this.zzaqn);
            this.zzasl.zzcy();
        } catch (RemoteException e) {
            this.zzasl.zzgt().zzjg().zzg("Failed to send app launch to the service", e);
        }
    }
}
