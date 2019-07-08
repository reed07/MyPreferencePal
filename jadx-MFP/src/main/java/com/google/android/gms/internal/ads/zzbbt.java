package com.google.android.gms.internal.ads;

final /* synthetic */ class zzbbt implements Runnable {
    private final zzbcl zzcak;
    private final zzbcb zzepc;
    private final zzbbl zzepd;

    zzbbt(zzbcl zzbcl, zzbbl zzbbl, zzbcb zzbcb) {
        this.zzcak = zzbcl;
        this.zzepd = zzbbl;
        this.zzepc = zzbcb;
    }

    public final void run() {
        zzbbq.zza(this.zzcak, this.zzepd, this.zzepc);
    }
}
