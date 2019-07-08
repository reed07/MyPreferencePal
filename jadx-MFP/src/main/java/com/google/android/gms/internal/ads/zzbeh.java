package com.google.android.gms.internal.ads;

final /* synthetic */ class zzbeh implements Runnable {
    private final String zzbpk;
    private final zzbee zzevd;

    zzbeh(zzbee zzbee, String str) {
        this.zzevd = zzbee;
        this.zzbpk = str;
    }

    public final void run() {
        this.zzevd.zzeu(this.zzbpk);
    }
}
