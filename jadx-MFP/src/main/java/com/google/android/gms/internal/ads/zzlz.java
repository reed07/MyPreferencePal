package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzlz {
    public final int length;
    private int zzaac;
    private final zzfs[] zzayc;

    public zzlz(zzfs... zzfsArr) {
        zzpo.checkState(zzfsArr.length > 0);
        this.zzayc = zzfsArr;
        this.length = zzfsArr.length;
    }

    public final zzfs zzat(int i) {
        return this.zzayc[i];
    }

    public final int zzi(zzfs zzfs) {
        int i = 0;
        while (true) {
            zzfs[] zzfsArr = this.zzayc;
            if (i >= zzfsArr.length) {
                return -1;
            }
            if (zzfs == zzfsArr[i]) {
                return i;
            }
            i++;
        }
    }

    public final int hashCode() {
        if (this.zzaac == 0) {
            this.zzaac = Arrays.hashCode(this.zzayc) + 527;
        }
        return this.zzaac;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzlz zzlz = (zzlz) obj;
        return this.length == zzlz.length && Arrays.equals(this.zzayc, zzlz.zzayc);
    }
}
