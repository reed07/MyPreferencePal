package com.google.android.gms.internal.ads;

import java.util.List;

public final class zznr extends zznp implements zznd {
    private final zznu zzbdq;

    public zznr(String str, long j, zzfs zzfs, String str2, zznu zznu, List<zznm> list) {
        super(str, -1, zzfs, str2, zznu, list);
        this.zzbdq = zznu;
    }

    public final String zzf() {
        return null;
    }

    public final zzno zzgi() {
        return null;
    }

    public final zznd zzgj() {
        return this;
    }

    public final zzno zzax(int i) {
        return this.zzbdq.zza(this, i);
    }

    public final int zzf(long j, long j2) {
        zznu zznu = this.zzbdq;
        int i = zznu.zzbdx;
        int zzai = zznu.zzai(j2);
        if (zzai == 0) {
            return i;
        }
        if (zznu.zzbdy == null) {
            int i2 = zznu.zzbdx + ((int) (j / ((zznu.zzcs * 1000000) / zznu.zzcr)));
            if (i2 < i) {
                return i;
            }
            if (zzai == -1) {
                return i2;
            }
            return Math.min(i2, (i + zzai) - 1);
        }
        int i3 = (zzai + i) - 1;
        int i4 = i;
        while (i4 <= i3) {
            int i5 = ((i3 - i4) / 2) + i4;
            int i6 = (zznu.zzbc(i5) > j ? 1 : (zznu.zzbc(i5) == j ? 0 : -1));
            if (i6 < 0) {
                i4 = i5 + 1;
            } else if (i6 <= 0) {
                return i5;
            } else {
                i3 = i5 - 1;
            }
        }
        return i4 == i ? i4 : i3;
    }

    public final long zzaw(int i) {
        return this.zzbdq.zzbc(i);
    }

    public final long zze(int i, long j) {
        zznu zznu = this.zzbdq;
        if (zznu.zzbdy != null) {
            return (((zznx) zznu.zzbdy.get(i - zznu.zzbdx)).zzcs * 1000000) / zznu.zzcr;
        }
        int zzai = zznu.zzai(j);
        if (zzai == -1 || i != (zznu.zzbdx + zzai) - 1) {
            return (zznu.zzcs * 1000000) / zznu.zzcr;
        }
        return j - zznu.zzbc(i);
    }

    public final int zzgd() {
        return this.zzbdq.zzbdx;
    }

    public final int zzai(long j) {
        return this.zzbdq.zzai(j);
    }

    public final boolean zzge() {
        return this.zzbdq.zzge();
    }
}
