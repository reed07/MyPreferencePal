package com.google.android.gms.internal.icing;

final class zzcm {
    private final byte[] buffer;
    private final zzct zzge;

    private zzcm(int i) {
        this.buffer = new byte[i];
        this.zzge = zzct.zzb(this.buffer);
    }

    public final zzce zzas() {
        this.zzge.zzaw();
        return new zzco(this.buffer);
    }

    public final zzct zzat() {
        return this.zzge;
    }

    /* synthetic */ zzcm(int i, zzcf zzcf) {
        this(i);
    }
}
