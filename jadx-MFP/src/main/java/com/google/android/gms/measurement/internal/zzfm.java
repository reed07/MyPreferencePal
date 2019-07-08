package com.google.android.gms.measurement.internal;

abstract class zzfm extends zzfl {
    private boolean zzvz;

    zzfm(zzfn zzfn) {
        super(zzfn);
        this.zzamx.zzb(this);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzgy();

    /* access modifiers changed from: 0000 */
    public final boolean isInitialized() {
        return this.zzvz;
    }

    /* access modifiers changed from: protected */
    public final void zzcl() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzq() {
        if (!this.zzvz) {
            zzgy();
            this.zzamx.zzmg();
            this.zzvz = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }
}
