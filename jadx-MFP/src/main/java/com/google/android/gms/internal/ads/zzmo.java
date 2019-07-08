package com.google.android.gms.internal.ads;

public abstract class zzmo extends zzme {
    public final int zzbaw;

    public zzmo(zzov zzov, zzoz zzoz, zzfs zzfs, int i, Object obj, long j, long j2, int i2) {
        super(zzov, zzoz, 1, zzfs, i, obj, j, j2);
        zzpo.checkNotNull(zzfs);
        this.zzbaw = i2;
    }

    public abstract boolean zzga();

    public int zzfz() {
        return this.zzbaw + 1;
    }
}
