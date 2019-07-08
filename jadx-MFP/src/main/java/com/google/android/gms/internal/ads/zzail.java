package com.google.android.gms.internal.ads;

final /* synthetic */ class zzail implements Runnable {
    private final String zzbpk;
    private final zzaik zzdio;

    zzail(zzaik zzaik, String str) {
        this.zzdio = zzaik;
        this.zzbpk = str;
    }

    public final void run() {
        this.zzdio.zzck(this.zzbpk);
    }
}
