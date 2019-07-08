package com.google.android.gms.internal.ads;

final /* synthetic */ class zzbew implements zzow {
    private final zzow zzewb;
    private final byte[] zzewc;

    zzbew(zzow zzow, byte[] bArr) {
        this.zzewb = zzow;
        this.zzewc = bArr;
    }

    public final zzov zzgs() {
        zzow zzow = this.zzewb;
        byte[] bArr = this.zzewc;
        return new zzbfa(new zzou(bArr), bArr.length, zzow.zzgs());
    }
}
