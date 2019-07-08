package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzp;

final class zzfh {
    private zzdz<zzp> zzbfr;
    private zzp zzbfs;

    public zzfh(zzdz<zzp> zzdz, zzp zzp) {
        this.zzbfr = zzdz;
        this.zzbfs = zzp;
    }

    public final zzdz<zzp> zzpv() {
        return this.zzbfr;
    }

    public final zzp zzpw() {
        return this.zzbfs;
    }

    public final int getSize() {
        int zzzh = ((zzp) this.zzbfr.getObject()).zzzh();
        zzp zzp = this.zzbfs;
        return zzzh + (zzp == null ? 0 : zzp.zzzh());
    }
}
