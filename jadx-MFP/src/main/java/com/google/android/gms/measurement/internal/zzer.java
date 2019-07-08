package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

final class zzer implements Runnable {
    private final /* synthetic */ zzk zzaqn;
    private final /* synthetic */ zzfu zzaqq;
    private final /* synthetic */ zzeb zzasl;
    private final /* synthetic */ boolean zzaso;

    zzer(zzeb zzeb, boolean z, zzfu zzfu, zzk zzk) {
        this.zzasl = zzeb;
        this.zzaso = z;
        this.zzaqq = zzfu;
        this.zzaqn = zzk;
    }

    public final void run() {
        zzaj zzd = this.zzasl.zzasf;
        if (zzd == null) {
            this.zzasl.zzgt().zzjg().zzby("Discarding data. Failed to set user attribute");
            return;
        }
        this.zzasl.zza(zzd, (AbstractSafeParcelable) this.zzaso ? null : this.zzaqq, this.zzaqn);
        this.zzasl.zzcy();
    }
}
