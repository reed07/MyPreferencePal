package com.google.android.gms.measurement.internal;

final class zzd implements Runnable {
    private final /* synthetic */ zza zzaff;
    private final /* synthetic */ long zzafg;

    zzd(zza zza, long j) {
        this.zzaff = zza;
        this.zzafg = j;
    }

    public final void run() {
        this.zzaff.zzn(this.zzafg);
    }
}
