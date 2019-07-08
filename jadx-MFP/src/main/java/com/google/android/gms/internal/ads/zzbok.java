package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public final class zzbok implements zzbjm {
    private final zzbov zzfjn;
    private final zzbjx zzfjo;
    private final int zzfjp;

    public zzbok(zzbov zzbov, zzbjx zzbjx, int i) {
        this.zzfjn = zzbov;
        this.zzfjo = zzbjx;
        this.zzfjp = i;
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] zzn = this.zzfjn.zzn(bArr);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        byte[] copyOf = Arrays.copyOf(ByteBuffer.allocate(8).putLong(((long) bArr2.length) * 8).array(), 8);
        return zzbnx.zza(zzn, this.zzfjo.zzj(zzbnx.zza(bArr2, zzn, copyOf)));
    }
}
