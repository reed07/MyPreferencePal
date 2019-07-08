package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;

public final class zztl {
    final long value;
    final String zzbzf;
    final int zzbzg;

    zztl(long j, String str, int i) {
        this.value = j;
        this.zzbzf = str;
        this.zzbzg = i;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof zztl)) {
            return false;
        }
        zztl zztl = (zztl) obj;
        if (zztl.value == this.value && zztl.zzbzg == this.zzbzg) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (int) this.value;
    }
}
