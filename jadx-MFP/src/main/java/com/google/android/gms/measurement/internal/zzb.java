package com.google.android.gms.measurement.internal;

final class zzb implements Runnable {
    private final /* synthetic */ String zzaee;
    private final /* synthetic */ long zzafe;
    private final /* synthetic */ zza zzaff;

    zzb(zza zza, String str, long j) {
        this.zzaff = zza;
        this.zzaee = str;
        this.zzafe = j;
    }

    public final void run() {
        this.zzaff.zza(this.zzaee, this.zzafe);
    }
}
