package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

final class zzem implements Runnable {
    private final /* synthetic */ zzag zzagi;
    private final /* synthetic */ String zzagj;
    private final /* synthetic */ zzk zzaqn;
    private final /* synthetic */ zzeb zzasl;
    private final /* synthetic */ boolean zzasn;
    private final /* synthetic */ boolean zzaso;

    zzem(zzeb zzeb, boolean z, boolean z2, zzag zzag, zzk zzk, String str) {
        this.zzasl = zzeb;
        this.zzasn = z;
        this.zzaso = z2;
        this.zzagi = zzag;
        this.zzaqn = zzk;
        this.zzagj = str;
    }

    public final void run() {
        zzaj zzd = this.zzasl.zzasf;
        if (zzd == null) {
            this.zzasl.zzgt().zzjg().zzby("Discarding data. Failed to send event to service");
            return;
        }
        if (this.zzasn) {
            this.zzasl.zza(zzd, (AbstractSafeParcelable) this.zzaso ? null : this.zzagi, this.zzaqn);
        } else {
            try {
                if (TextUtils.isEmpty(this.zzagj)) {
                    zzd.zza(this.zzagi, this.zzaqn);
                } else {
                    zzd.zza(this.zzagi, this.zzagj, this.zzasl.zzgt().zzjq());
                }
            } catch (RemoteException e) {
                this.zzasl.zzgt().zzjg().zzg("Failed to send event to the service", e);
            }
        }
        this.zzasl.zzcy();
    }
}
