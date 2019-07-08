package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;

final /* synthetic */ class zzfa implements Runnable {
    private final JobParameters zzace;
    private final zzey zzasw;
    private final zzas zzasz;

    zzfa(zzey zzey, zzas zzas, JobParameters jobParameters) {
        this.zzasw = zzey;
        this.zzasz = zzas;
        this.zzace = jobParameters;
    }

    public final void run() {
        this.zzasw.zza(this.zzasz, this.zzace);
    }
}
