package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzoo {
    public final int length;
    private int zzaac;
    private final zzom[] zzbfi;

    public zzoo(zzom... zzomArr) {
        this.zzbfi = zzomArr;
        this.length = zzomArr.length;
    }

    public final zzom zzbe(int i) {
        return this.zzbfi[i];
    }

    public final zzom[] zzgp() {
        return (zzom[]) this.zzbfi.clone();
    }

    public final int hashCode() {
        if (this.zzaac == 0) {
            this.zzaac = Arrays.hashCode(this.zzbfi) + 527;
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
        return Arrays.equals(this.zzbfi, ((zzoo) obj).zzbfi);
    }
}
