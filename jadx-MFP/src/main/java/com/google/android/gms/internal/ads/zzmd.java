package com.google.android.gms.internal.ads;

import android.util.Log;

final class zzmd implements zzmh {
    private final int[] zzazm;
    private final zzls[] zzazn;

    public zzmd(int[] iArr, zzls[] zzlsArr) {
        this.zzazm = iArr;
        this.zzazn = zzlsArr;
    }

    public final zzii zzb(int i, int i2) {
        int i3 = 0;
        while (true) {
            int[] iArr = this.zzazm;
            if (i3 >= iArr.length) {
                StringBuilder sb = new StringBuilder(36);
                sb.append("Unmatched track of type: ");
                sb.append(i2);
                Log.e("BaseMediaChunkOutput", sb.toString());
                return new zzhy();
            } else if (i2 == iArr[i3]) {
                return this.zzazn[i3];
            } else {
                i3++;
            }
        }
    }

    public final int[] zzfu() {
        int[] iArr = new int[this.zzazn.length];
        int i = 0;
        while (true) {
            zzls[] zzlsArr = this.zzazn;
            if (i >= zzlsArr.length) {
                return iArr;
            }
            if (zzlsArr[i] != null) {
                iArr[i] = zzlsArr[i].zzfk();
            }
            i++;
        }
    }

    public final void zzae(long j) {
        zzls[] zzlsArr;
        for (zzls zzls : this.zzazn) {
            if (zzls != null) {
                zzls.zzae(j);
            }
        }
    }
}
