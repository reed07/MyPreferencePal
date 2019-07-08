package com.google.android.gms.internal.ads;

import java.util.List;

public final class zznv extends zznu {
    final List<zzno> zzbdz;

    public zznv(zzno zzno, long j, long j2, int i, long j3, List<zznx> list, List<zzno> list2) {
        super(zzno, j, j2, i, j3, list);
        this.zzbdz = list2;
    }

    public final boolean zzge() {
        return true;
    }

    public final zzno zza(zznp zznp, int i) {
        return (zzno) this.zzbdz.get(i - this.zzbdx);
    }

    public final int zzai(long j) {
        return this.zzbdz.size();
    }
}
