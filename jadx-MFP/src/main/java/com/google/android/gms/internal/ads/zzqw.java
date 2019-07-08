package com.google.android.gms.internal.ads;

final class zzqw implements Runnable {
    private final /* synthetic */ zzhn zzabj;
    private final /* synthetic */ zzqv zzbkp;

    zzqw(zzqv zzqv, zzhn zzhn) {
        this.zzbkp = zzqv;
        this.zzabj = zzhn;
    }

    public final void run() {
        this.zzbkp.zzbko.zze(this.zzabj);
    }
}
