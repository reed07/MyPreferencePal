package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzdg implements Runnable {
    private final /* synthetic */ zzda zzarh;
    private final /* synthetic */ long zzarn;

    zzdg(zzda zzda, long j) {
        this.zzarh = zzda;
        this.zzarn = j;
    }

    public final void run() {
        zzda zzda = this.zzarh;
        long j = this.zzarn;
        zzda.zzaf();
        zzda.zzgg();
        zzda.zzcl();
        zzda.zzgt().zzjn().zzby("Resetting analytics data (FE)");
        zzda.zzgo().zzln();
        if (zzda.zzgv().zzbc(zzda.zzgk().zzal())) {
            zzda.zzgu().zzanh.set(j);
        }
        boolean isEnabled = zzda.zzada.isEnabled();
        if (!zzda.zzgv().zzhz()) {
            zzda.zzgu().zzi(!isEnabled);
        }
        zzda.zzgl().resetAnalyticsData();
        zzda.zzarf = !isEnabled;
        if (this.zzarh.zzgv().zza(zzai.zzald)) {
            this.zzarh.zzgl().zza(new AtomicReference<>());
        }
    }
}
