package com.google.android.gms.internal.ads;

final class zzqx implements Runnable {
    private final /* synthetic */ String zzabl;
    private final /* synthetic */ long zzabm;
    private final /* synthetic */ long zzabn;
    private final /* synthetic */ zzqv zzbkp;

    zzqx(zzqv zzqv, String str, long j, long j2) {
        this.zzbkp = zzqv;
        this.zzabl = str;
        this.zzabm = j;
        this.zzabn = j2;
    }

    public final void run() {
        this.zzbkp.zzbko.zzd(this.zzabl, this.zzabm, this.zzabn);
    }
}
