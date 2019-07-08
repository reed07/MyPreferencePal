package com.google.android.gms.internal.ads;

@zzark
final class zzbfe extends zzax {
    static final zzbfe zzewi = new zzbfe();

    zzbfe() {
    }

    public final zzbc zza(String str, byte[] bArr, String str2) {
        if ("moov".equals(str)) {
            return new zzbe();
        }
        if ("mvhd".equals(str)) {
            return new zzbf();
        }
        return new zzbg(str);
    }
}
