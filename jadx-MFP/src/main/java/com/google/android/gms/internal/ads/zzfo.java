package com.google.android.gms.internal.ads;

import android.util.Log;

final class zzfo {
    public final int index;
    private final zzfz[] zzwu;
    private final zzop zzwv;
    private final zzga[] zzxs;
    private final zzfw zzxt;
    private final zzlo zzxz;
    public final zzlm zzym;
    public final Object zzyn;
    public final zzlv[] zzyo;
    private final boolean[] zzyp;
    public final long zzyq;
    public int zzyr;
    public long zzys;
    public boolean zzyt;
    public boolean zzyu;
    public boolean zzyv;
    public zzfo zzyw;
    public zzor zzyx;
    private zzor zzyy;

    public zzfo(zzfz[] zzfzArr, zzga[] zzgaArr, long j, zzop zzop, zzfw zzfw, zzlo zzlo, Object obj, int i, int i2, boolean z, long j2) {
        this.zzwu = zzfzArr;
        this.zzxs = zzgaArr;
        this.zzyq = j;
        this.zzwv = zzop;
        this.zzxt = zzfw;
        this.zzxz = zzlo;
        this.zzyn = zzpo.checkNotNull(obj);
        this.index = i;
        this.zzyr = i2;
        this.zzyt = z;
        this.zzys = j2;
        this.zzyo = new zzlv[zzfzArr.length];
        this.zzyp = new boolean[zzfzArr.length];
        this.zzym = zzlo.zza(i2, zzfw.zzci());
    }

    public final long zzcb() {
        return this.zzyq - this.zzys;
    }

    public final void zzc(int i, boolean z) {
        this.zzyr = i;
        this.zzyt = z;
    }

    public final boolean zzcc() {
        return this.zzyu && (!this.zzyv || this.zzym.zzez() == Long.MIN_VALUE);
    }

    public final boolean zzcd() throws zzff {
        boolean z;
        zzor zza = this.zzwv.zza(this.zzxs, this.zzym.zzex());
        zzor zzor = this.zzyy;
        if (zzor != null) {
            int i = 0;
            while (true) {
                if (i >= zza.zzbfl.length) {
                    z = true;
                    break;
                } else if (!zza.zza(zzor, i)) {
                    z = false;
                    break;
                } else {
                    i++;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            return false;
        }
        this.zzyx = zza;
        return true;
    }

    public final long zzb(long j, boolean z) {
        return zza(j, false, new boolean[this.zzwu.length]);
    }

    public final long zza(long j, boolean z, boolean[] zArr) {
        zzoo zzoo = this.zzyx.zzbfl;
        int i = 0;
        while (true) {
            boolean z2 = true;
            if (i >= zzoo.length) {
                break;
            }
            boolean[] zArr2 = this.zzyp;
            if (z || !this.zzyx.zza(this.zzyy, i)) {
                z2 = false;
            }
            zArr2[i] = z2;
            i++;
        }
        long zza = this.zzym.zza(zzoo.zzgp(), this.zzyp, this.zzyo, zArr, j);
        this.zzyy = this.zzyx;
        this.zzyv = false;
        int i2 = 0;
        while (true) {
            zzlv[] zzlvArr = this.zzyo;
            if (i2 < zzlvArr.length) {
                if (zzlvArr[i2] != null) {
                    zzpo.checkState(zzoo.zzbe(i2) != null);
                    this.zzyv = true;
                } else {
                    zzpo.checkState(zzoo.zzbe(i2) == null);
                }
                i2++;
            } else {
                this.zzxt.zza(this.zzwu, this.zzyx.zzbfk, zzoo);
                return zza;
            }
        }
    }

    public final void release() {
        try {
            this.zzxz.zzb(this.zzym);
        } catch (RuntimeException e) {
            Log.e("ExoPlayerImplInternal", "Period release failed.", e);
        }
    }
}
