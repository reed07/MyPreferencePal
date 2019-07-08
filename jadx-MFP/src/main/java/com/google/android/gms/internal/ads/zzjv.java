package com.google.android.gms.internal.ads;

final class zzjv {
    public final int[] zzagt;
    public final long[] zzagu;
    public final int zzapk;
    public final int zzapp;
    public final int[] zzapr;
    public final long[] zzatl;

    public zzjv(long[] jArr, int[] iArr, int i, long[] jArr2, int[] iArr2) {
        boolean z = true;
        zzpo.checkArgument(iArr.length == jArr2.length);
        zzpo.checkArgument(jArr.length == jArr2.length);
        if (iArr2.length != jArr2.length) {
            z = false;
        }
        zzpo.checkArgument(z);
        this.zzagu = jArr;
        this.zzagt = iArr;
        this.zzapp = i;
        this.zzatl = jArr2;
        this.zzapr = iArr2;
        this.zzapk = jArr.length;
    }

    public final int zzu(long j) {
        for (int zza = zzqe.zza(this.zzatl, j, true, false); zza >= 0; zza--) {
            if ((this.zzapr[zza] & 1) != 0) {
                return zza;
            }
        }
        return -1;
    }

    public final int zzv(long j) {
        for (int zzb = zzqe.zzb(this.zzatl, j, true, false); zzb < this.zzatl.length; zzb++) {
            if ((this.zzapr[zzb] & 1) != 0) {
                return zzb;
            }
        }
        return -1;
    }
}
