package com.google.android.gms.internal.measurement;

public abstract class zzau extends zzat {
    private boolean zzvz;

    protected zzau(zzaw zzaw) {
        super(zzaw);
    }

    /* access modifiers changed from: protected */
    public abstract void zzag();

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
        zzag();
        this.zzvz = true;
    }
}
