package com.google.android.gms.measurement.internal;

final class zzdr implements Runnable {
    private final /* synthetic */ boolean zzaed;
    private final /* synthetic */ zzda zzarh;

    zzdr(zzda zzda, boolean z) {
        this.zzarh = zzda;
        this.zzaed = z;
    }

    public final void run() {
        boolean isEnabled = this.zzarh.zzada.isEnabled();
        boolean zzks = this.zzarh.zzada.zzks();
        this.zzarh.zzada.zzd(this.zzaed);
        if (zzks == this.zzaed) {
            this.zzarh.zzada.zzgt().zzjo().zzg("Default data collection state already set to", Boolean.valueOf(this.zzaed));
        }
        if (this.zzarh.zzada.isEnabled() == isEnabled || this.zzarh.zzada.isEnabled() != this.zzarh.zzada.zzks()) {
            this.zzarh.zzada.zzgt().zzjl().zze("Default data collection is different than actual status", Boolean.valueOf(this.zzaed), Boolean.valueOf(isEnabled));
        }
        this.zzarh.zzlc();
    }
}
