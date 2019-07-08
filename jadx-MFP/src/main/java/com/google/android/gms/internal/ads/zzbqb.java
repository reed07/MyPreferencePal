package com.google.android.gms.internal.ads;

final class zzbqb {
    private final byte[] buffer;
    private final zzbqk zzflo;

    private zzbqb(int i) {
        this.buffer = new byte[i];
        this.zzflo = zzbqk.zzt(this.buffer);
    }

    public final zzbpu zzaks() {
        this.zzflo.zzalv();
        return new zzbqd(this.buffer);
    }

    public final zzbqk zzakt() {
        return this.zzflo;
    }

    /* synthetic */ zzbqb(int i, zzbpv zzbpv) {
        this(i);
    }
}
