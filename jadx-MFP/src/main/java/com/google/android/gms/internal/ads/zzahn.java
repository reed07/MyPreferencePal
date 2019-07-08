package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzo;
import java.util.Arrays;

@zzark
final class zzahn {
    private final Object[] mParams;

    zzahn(zzwb zzwb, String str, int i) {
        this.mParams = zzo.zza((String) zzwu.zzpz().zzd(zzaan.zzcsb), zzwb, str, i, null);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzahn)) {
            return false;
        }
        return Arrays.equals(this.mParams, ((zzahn) obj).mParams);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.mParams);
    }

    public final String toString() {
        String arrays = Arrays.toString(this.mParams);
        StringBuilder sb = new StringBuilder(String.valueOf(arrays).length() + 24);
        sb.append("[InterstitialAdPoolKey ");
        sb.append(arrays);
        sb.append("]");
        return sb.toString();
    }
}
