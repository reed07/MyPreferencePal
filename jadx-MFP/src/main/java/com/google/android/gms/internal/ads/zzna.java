package com.google.android.gms.internal.ads;

final class zzna {
    public final boolean zzbcb;
    public final long zzbcc;
    public final long zzbcd;

    public static zzna zza(zznn zznn, long j) {
        int i;
        zznn zznn2 = zznn;
        long j2 = j;
        int size = zznn2.zzbbe.size();
        int i2 = 0;
        long j3 = Long.MAX_VALUE;
        int i3 = 0;
        boolean z = false;
        boolean z2 = false;
        long j4 = 0;
        while (i3 < size) {
            zznd zzgj = ((zznp) ((zzni) zznn2.zzbbe.get(i3)).zzbcn.get(i2)).zzgj();
            if (zzgj == null) {
                zzna zzna = new zzna(true, 0, j);
                return zzna;
            }
            z2 |= zzgj.zzge();
            int zzai = zzgj.zzai(j2);
            if (zzai == 0) {
                i = i3;
                z = true;
                j4 = 0;
                j3 = 0;
            } else if (!z) {
                int zzgd = zzgj.zzgd();
                i = i3;
                long max = Math.max(j4, zzgj.zzaw(zzgd));
                if (zzai != -1) {
                    int i4 = (zzgd + zzai) - 1;
                    j3 = Math.min(j3, zzgj.zzaw(i4) + zzgj.zze(i4, j2));
                    j4 = max;
                } else {
                    j4 = max;
                }
            } else {
                i = i3;
            }
            i3 = i + 1;
            i2 = 0;
        }
        zzna zzna2 = new zzna(z2, j4, j3);
        return zzna2;
    }

    private zzna(boolean z, long j, long j2) {
        this.zzbcb = z;
        this.zzbcc = j;
        this.zzbcd = j2;
    }
}
