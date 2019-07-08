package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzma {
    public static final zzma zzazi = new zzma(new zzlz[0]);
    public final int length;
    private int zzaac;
    private final zzlz[] zzazj;

    public zzma(zzlz... zzlzArr) {
        this.zzazj = zzlzArr;
        this.length = zzlzArr.length;
    }

    public final zzlz zzau(int i) {
        return this.zzazj[i];
    }

    public final int zza(zzlz zzlz) {
        for (int i = 0; i < this.length; i++) {
            if (this.zzazj[i] == zzlz) {
                return i;
            }
        }
        return -1;
    }

    public final int hashCode() {
        if (this.zzaac == 0) {
            this.zzaac = Arrays.hashCode(this.zzazj);
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
        zzma zzma = (zzma) obj;
        return this.length == zzma.length && Arrays.equals(this.zzazj, zzma.zzazj);
    }
}
