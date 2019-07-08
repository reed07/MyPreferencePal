package com.google.android.gms.internal.ads;

final /* synthetic */ class zzbgy implements Runnable {
    private final int zzelz;
    private final int zzema;
    private final boolean zzeyu;
    private final boolean zzeyv;
    private final zzbgw zzfap;

    zzbgy(zzbgw zzbgw, int i, int i2, boolean z, boolean z2) {
        this.zzfap = zzbgw;
        this.zzelz = i;
        this.zzema = i2;
        this.zzeyu = z;
        this.zzeyv = z2;
    }

    public final void run() {
        this.zzfap.zzb(this.zzelz, this.zzema, this.zzeyu, this.zzeyv);
    }
}
