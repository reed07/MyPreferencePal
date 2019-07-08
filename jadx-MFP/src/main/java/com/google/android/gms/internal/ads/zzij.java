package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzij {
    public final int zzahg = 1;
    public final byte[] zzahh;

    public zzij(int i, byte[] bArr) {
        this.zzahh = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzij zzij = (zzij) obj;
        return this.zzahg == zzij.zzahg && Arrays.equals(this.zzahh, zzij.zzahh);
    }

    public final int hashCode() {
        return (this.zzahg * 31) + Arrays.hashCode(this.zzahh);
    }
}
