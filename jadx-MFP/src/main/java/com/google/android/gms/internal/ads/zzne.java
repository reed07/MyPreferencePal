package com.google.android.gms.internal.ads;

public final class zzne implements zznd {
    private final zzhw zzbce;

    public zzne(zzhw zzhw) {
        this.zzbce = zzhw;
    }

    public final int zzgd() {
        return 0;
    }

    public final boolean zzge() {
        return true;
    }

    public final int zzai(long j) {
        return this.zzbce.length;
    }

    public final long zzaw(int i) {
        return this.zzbce.zzagw[i];
    }

    public final long zze(int i, long j) {
        return this.zzbce.zzagv[i];
    }

    public final zzno zzax(int i) {
        zzno zzno = new zzno(null, this.zzbce.zzagu[i], (long) this.zzbce.zzagt[i]);
        return zzno;
    }

    public final int zzf(long j, long j2) {
        return this.zzbce.zzq(j);
    }
}
