package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzbka<P> {
    private final P zzfde;
    private final byte[] zzfdf;
    private final zzbmy zzfdg;
    private final zzbnq zzfdh;

    public zzbka(P p, byte[] bArr, zzbmy zzbmy, zzbnq zzbnq) {
        this.zzfde = p;
        this.zzfdf = Arrays.copyOf(bArr, bArr.length);
        this.zzfdg = zzbmy;
        this.zzfdh = zzbnq;
    }

    public final P zzafs() {
        return this.zzfde;
    }

    public final byte[] zzaft() {
        byte[] bArr = this.zzfdf;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }
}
