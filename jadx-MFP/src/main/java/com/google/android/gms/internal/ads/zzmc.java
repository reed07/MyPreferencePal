package com.google.android.gms.internal.ads;

public abstract class zzmc extends zzmo {
    private zzmd zzazk;
    private int[] zzazl;

    public zzmc(zzov zzov, zzoz zzoz, zzfs zzfs, int i, Object obj, long j, long j2, int i2) {
        super(zzov, zzoz, zzfs, i, obj, j, j2, i2);
    }

    public final void zza(zzmd zzmd) {
        this.zzazk = zzmd;
        this.zzazl = zzmd.zzfu();
    }

    public final int zzav(int i) {
        return this.zzazl[i];
    }

    /* access modifiers changed from: protected */
    public final zzmd zzft() {
        return this.zzazk;
    }
}
