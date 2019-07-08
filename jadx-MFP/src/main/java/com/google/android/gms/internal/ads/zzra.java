package com.google.android.gms.internal.ads;

final class zzra implements Runnable {
    private final /* synthetic */ zzqv zzbkp;
    private final /* synthetic */ int zzbks;
    private final /* synthetic */ int zzbkt;
    private final /* synthetic */ int zzbku;
    private final /* synthetic */ float zzbkv;

    zzra(zzqv zzqv, int i, int i2, int i3, float f) {
        this.zzbkp = zzqv;
        this.zzbks = i;
        this.zzbkt = i2;
        this.zzbku = i3;
        this.zzbkv = f;
    }

    public final void run() {
        this.zzbkp.zzbko.zza(this.zzbks, this.zzbkt, this.zzbku, this.zzbkv);
    }
}
