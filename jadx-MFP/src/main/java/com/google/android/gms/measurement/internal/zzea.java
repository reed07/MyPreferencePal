package com.google.android.gms.measurement.internal;

final class zzea implements Runnable {
    private final /* synthetic */ zzdy zzasc;
    private final /* synthetic */ zzdx zzasd;

    zzea(zzdy zzdy, zzdx zzdx) {
        this.zzasc = zzdy;
        this.zzasd = zzdx;
    }

    public final void run() {
        this.zzasc.zza(this.zzasd, false);
        zzdy zzdy = this.zzasc;
        zzdy.zzart = null;
        zzdy.zzgl().zza((zzdx) null);
    }
}
