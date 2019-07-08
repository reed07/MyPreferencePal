package com.google.android.gms.internal.ads;

final /* synthetic */ class zzahx implements Runnable {
    private final String zzbpk;
    private final zzahv zzdif;

    zzahx(zzahv zzahv, String str) {
        this.zzdif = zzahv;
        this.zzbpk = str;
    }

    public final void run() {
        this.zzdif.zzci(this.zzbpk);
    }
}
