package com.google.android.gms.internal.measurement;

import android.app.job.JobParameters;

final /* synthetic */ class zzcz implements Runnable {
    private final zzcx zzaca;
    private final zzcp zzacd;
    private final JobParameters zzace;

    zzcz(zzcx zzcx, zzcp zzcp, JobParameters jobParameters) {
        this.zzaca = zzcx;
        this.zzacd = zzcp;
        this.zzace = jobParameters;
    }

    public final void run() {
        this.zzaca.zza(this.zzacd, this.zzace);
    }
}
