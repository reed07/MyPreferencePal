package com.google.android.gms.internal.ads;

final class zzgn implements Runnable {
    private final /* synthetic */ zzgl zzabk;
    private final /* synthetic */ String zzabl;
    private final /* synthetic */ long zzabm;
    private final /* synthetic */ long zzabn;

    zzgn(zzgl zzgl, String str, long j, long j2) {
        this.zzabk = zzgl;
        this.zzabl = str;
        this.zzabm = j;
        this.zzabn = j2;
    }

    public final void run() {
        this.zzabk.zzabi.zza(this.zzabl, this.zzabm, this.zzabn);
    }
}
