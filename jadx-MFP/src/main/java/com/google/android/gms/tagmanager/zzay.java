package com.google.android.gms.tagmanager;

import java.util.Arrays;

final class zzay {
    final byte[] zzbbu;
    final String zzoj;

    zzay(String str, byte[] bArr) {
        this.zzoj = str;
        this.zzbbu = bArr;
    }

    public final String toString() {
        String str = this.zzoj;
        int hashCode = Arrays.hashCode(this.zzbbu);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 54);
        sb.append("KeyAndSerialized: key = ");
        sb.append(str);
        sb.append(" serialized hash = ");
        sb.append(hashCode);
        return sb.toString();
    }
}
