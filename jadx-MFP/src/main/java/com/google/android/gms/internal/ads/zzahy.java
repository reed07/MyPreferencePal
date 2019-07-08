package com.google.android.gms.internal.ads;

final /* synthetic */ class zzahy implements Runnable {
    private final String zzbpk;
    private final zzahv zzdif;

    zzahy(zzahv zzahv, String str) {
        this.zzdif = zzahv;
        this.zzbpk = str;
    }

    public final void run() {
        this.zzdif.zzch(this.zzbpk);
    }
}
