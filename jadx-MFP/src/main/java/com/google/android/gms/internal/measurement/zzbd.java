package com.google.android.gms.internal.measurement;

final class zzbd implements Runnable {
    private final /* synthetic */ zzcl zzxd;
    private final /* synthetic */ zzbc zzxe;

    zzbd(zzbc zzbc, zzcl zzcl) {
        this.zzxe = zzbc;
        this.zzxd = zzcl;
    }

    public final void run() {
        if (!this.zzxe.zzxa.isConnected()) {
            this.zzxe.zzxa.zzr("Connected to service after a timeout");
            this.zzxe.zzxa.zza(this.zzxd);
        }
    }
}
