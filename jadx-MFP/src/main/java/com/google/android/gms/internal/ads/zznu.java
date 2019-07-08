package com.google.android.gms.internal.ads;

import java.util.List;

public abstract class zznu extends zznt {
    final int zzbdx;
    final List<zznx> zzbdy;
    final long zzcs;

    public zznu(zzno zzno, long j, long j2, int i, long j3, List<zznx> list) {
        super(zzno, j, j2);
        this.zzbdx = i;
        this.zzcs = j3;
        this.zzbdy = list;
    }

    public abstract zzno zza(zznp zznp, int i);

    public abstract int zzai(long j);

    public final long zzbc(int i) {
        long j;
        List<zznx> list = this.zzbdy;
        if (list != null) {
            j = ((zznx) list.get(i - this.zzbdx)).startTime - this.zzbdw;
        } else {
            j = ((long) (i - this.zzbdx)) * this.zzcs;
        }
        return zzqe.zza(j, 1000000, this.zzcr);
    }

    public boolean zzge() {
        return this.zzbdy != null;
    }
}
