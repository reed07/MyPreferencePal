package com.google.android.gms.internal.ads;

import android.util.SparseArray;

public final class zzmf implements zzib {
    private zzig zzaxa;
    public final zzhz zzaxu;
    private final zzfs zzazu;
    private final SparseArray<zzmg> zzazv = new SparseArray<>();
    private boolean zzazw;
    private zzmh zzazx;
    private zzfs[] zzazy;

    public zzmf(zzhz zzhz, zzfs zzfs) {
        this.zzaxu = zzhz;
        this.zzazu = zzfs;
    }

    public final zzig zzfw() {
        return this.zzaxa;
    }

    public final zzfs[] zzfx() {
        return this.zzazy;
    }

    public final void zza(zzmh zzmh) {
        this.zzazx = zzmh;
        if (!this.zzazw) {
            this.zzaxu.zza((zzib) this);
            this.zzazw = true;
            return;
        }
        this.zzaxu.zzc(0, 0);
        for (int i = 0; i < this.zzazv.size(); i++) {
            ((zzmg) this.zzazv.valueAt(i)).zzb(zzmh);
        }
    }

    public final zzii zzb(int i, int i2) {
        zzmg zzmg = (zzmg) this.zzazv.get(i);
        if (zzmg != null) {
            return zzmg;
        }
        zzpo.checkState(this.zzazy == null);
        zzmg zzmg2 = new zzmg(i, i2, this.zzazu);
        zzmg2.zzb(this.zzazx);
        this.zzazv.put(i, zzmg2);
        return zzmg2;
    }

    public final void zzdy() {
        zzfs[] zzfsArr = new zzfs[this.zzazv.size()];
        for (int i = 0; i < this.zzazv.size(); i++) {
            zzfsArr[i] = ((zzmg) this.zzazv.valueAt(i)).zzazz;
        }
        this.zzazy = zzfsArr;
    }

    public final void zza(zzig zzig) {
        this.zzaxa = zzig;
    }
}
