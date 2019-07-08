package com.google.android.gms.internal.ads;

final class zzaiq implements Runnable {
    private final /* synthetic */ zzaik zzdis;
    private final /* synthetic */ String zzdit;

    zzaiq(zzaik zzaik, String str) {
        this.zzdis = zzaik;
        this.zzdit = str;
    }

    public final void run() {
        this.zzdis.zzdin.loadUrl(this.zzdit);
    }
}
