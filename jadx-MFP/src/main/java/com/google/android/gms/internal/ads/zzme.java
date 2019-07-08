package com.google.android.gms.internal.ads;

public abstract class zzme implements zzpi {
    public final int type;
    protected final zzov zzagy;
    public final zzoz zzazo;
    public final zzfs zzazp;
    public final int zzazq;
    public final Object zzazr;
    public final long zzazs;
    public final long zzazt;

    public zzme(zzov zzov, zzoz zzoz, int i, zzfs zzfs, int i2, Object obj, long j, long j2) {
        this.zzagy = (zzov) zzpo.checkNotNull(zzov);
        this.zzazo = (zzoz) zzpo.checkNotNull(zzoz);
        this.type = i;
        this.zzazp = zzfs;
        this.zzazq = i2;
        this.zzazr = obj;
        this.zzazs = j;
        this.zzazt = j2;
    }

    public abstract long zzfv();
}
