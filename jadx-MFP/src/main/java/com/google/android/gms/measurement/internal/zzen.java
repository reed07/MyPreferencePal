package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

final class zzen implements Runnable {
    private final /* synthetic */ zzk zzaqn;
    private final /* synthetic */ zzeb zzasl;
    private final /* synthetic */ boolean zzasn;
    private final /* synthetic */ boolean zzaso;
    private final /* synthetic */ zzo zzasp;
    private final /* synthetic */ zzo zzasq;

    zzen(zzeb zzeb, boolean z, boolean z2, zzo zzo, zzk zzk, zzo zzo2) {
        this.zzasl = zzeb;
        this.zzasn = z;
        this.zzaso = z2;
        this.zzasp = zzo;
        this.zzaqn = zzk;
        this.zzasq = zzo2;
    }

    public final void run() {
        zzaj zzd = this.zzasl.zzasf;
        if (zzd == null) {
            this.zzasl.zzgt().zzjg().zzby("Discarding data. Failed to send conditional user property to service");
            return;
        }
        if (this.zzasn) {
            this.zzasl.zza(zzd, (AbstractSafeParcelable) this.zzaso ? null : this.zzasp, this.zzaqn);
        } else {
            try {
                if (TextUtils.isEmpty(this.zzasq.packageName)) {
                    zzd.zza(this.zzasp, this.zzaqn);
                } else {
                    zzd.zzb(this.zzasp);
                }
            } catch (RemoteException e) {
                this.zzasl.zzgt().zzjg().zzg("Failed to send conditional user property to the service", e);
            }
        }
        this.zzasl.zzcy();
    }
}
