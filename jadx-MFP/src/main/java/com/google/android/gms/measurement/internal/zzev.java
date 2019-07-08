package com.google.android.gms.measurement.internal;

final class zzev implements Runnable {
    private final /* synthetic */ zzes zzasu;
    private final /* synthetic */ zzaj zzasv;

    zzev(zzes zzes, zzaj zzaj) {
        this.zzasu = zzes;
        this.zzasv = zzaj;
    }

    public final void run() {
        synchronized (this.zzasu) {
            this.zzasu.zzasr = false;
            if (!this.zzasu.zzasl.isConnected()) {
                this.zzasu.zzasl.zzgt().zzjn().zzby("Connected to remote service");
                this.zzasu.zzasl.zza(this.zzasv);
            }
        }
    }
}
