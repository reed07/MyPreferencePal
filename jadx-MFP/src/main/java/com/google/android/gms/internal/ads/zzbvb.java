package com.google.android.gms.internal.ads;

import java.util.Arrays;

final class zzbvb {
    final int tag;
    final byte[] zzflp;

    zzbvb(int i, byte[] bArr) {
        this.tag = i;
        this.zzflp = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbvb)) {
            return false;
        }
        zzbvb zzbvb = (zzbvb) obj;
        return this.tag == zzbvb.tag && Arrays.equals(this.zzflp, zzbvb.zzflp);
    }

    public final int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzflp);
    }
}
