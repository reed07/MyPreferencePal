package com.google.android.gms.internal.measurement;

import java.util.Arrays;

final class zzyk {
    final int tag;
    final byte[] zzbtz;

    zzyk(int i, byte[] bArr) {
        this.tag = i;
        this.zzbtz = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzyk)) {
            return false;
        }
        zzyk zzyk = (zzyk) obj;
        return this.tag == zzyk.tag && Arrays.equals(this.zzbtz, zzyk.zzbtz);
    }

    public final int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzbtz);
    }
}
