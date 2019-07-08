package com.google.android.gms.measurement.internal;

abstract class zzf extends zze {
    private boolean zzvz;

    zzf(zzbw zzbw) {
        super(zzbw);
        this.zzada.zzb(this);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzgy();

    /* access modifiers changed from: protected */
    public void zzgz() {
    }

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
        if (this.zzvz) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzgy()) {
            this.zzada.zzku();
            this.zzvz = true;
        }
    }

    public final void zzgx() {
        if (!this.zzvz) {
            zzgz();
            this.zzada.zzku();
            this.zzvz = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }
}
