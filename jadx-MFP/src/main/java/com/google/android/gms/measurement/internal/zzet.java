package com.google.android.gms.measurement.internal;

final class zzet implements Runnable {
    private final /* synthetic */ zzaj zzast;
    private final /* synthetic */ zzes zzasu;

    zzet(zzes zzes, zzaj zzaj) {
        this.zzasu = zzes;
        this.zzast = zzaj;
    }

    public final void run() {
        synchronized (this.zzasu) {
            this.zzasu.zzasr = false;
            if (!this.zzasu.zzasl.isConnected()) {
                this.zzasu.zzasl.zzgt().zzjo().zzby("Connected to service");
                this.zzasu.zzasl.zza(this.zzast);
            }
        }
    }
}
