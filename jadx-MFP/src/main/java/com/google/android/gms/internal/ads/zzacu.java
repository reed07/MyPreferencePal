package com.google.android.gms.internal.ads;

final class zzacu implements Runnable {
    private final /* synthetic */ zzach zzddn;
    private final /* synthetic */ zzact zzddo;

    zzacu(zzact zzact, zzach zzach) {
        this.zzddo = zzact;
        this.zzddn = zzach;
    }

    public final void run() {
        this.zzddo.zza(this.zzddn);
    }
}
