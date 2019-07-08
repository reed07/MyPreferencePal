package com.google.android.gms.internal.ads;

public final class zzly extends zzgc {
    private static final Object zzazd = new Object();
    private final boolean zzaas;
    private final boolean zzaat;
    private final long zzaze;
    private final long zzazf;
    private final long zzazg;
    private final long zzazh;

    public zzly(long j, boolean z) {
        this(j, j, 0, 0, z, false);
    }

    public final int zzck() {
        return 1;
    }

    public final int zzcl() {
        return 1;
    }

    private zzly(long j, long j2, long j3, long j4, boolean z, boolean z2) {
        this.zzaze = j;
        this.zzazf = j2;
        this.zzazg = 0;
        this.zzazh = 0;
        this.zzaas = z;
        this.zzaat = false;
    }

    public final zzgf zza(int i, zzgf zzgf, boolean z, long j) {
        zzpo.zzc(i, 0, 1);
        return zzgf.zza(null, -9223372036854775807L, -9223372036854775807L, this.zzaas, false, 0, this.zzazf, 0, 0, 0);
    }

    public final zzge zza(int i, zzge zzge, boolean z) {
        zzpo.zzc(i, 0, 1);
        Object obj = z ? zzazd : null;
        return zzge.zza(obj, obj, 0, this.zzaze, 0, false);
    }

    public final int zzc(Object obj) {
        return zzazd.equals(obj) ? 0 : -1;
    }
}
