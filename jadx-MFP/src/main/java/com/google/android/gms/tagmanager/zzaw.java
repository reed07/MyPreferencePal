package com.google.android.gms.tagmanager;

final class zzaw implements Runnable {
    private final /* synthetic */ zzat zzbbr;
    private final /* synthetic */ String zzbbt;

    zzaw(zzat zzat, String str) {
        this.zzbbr = zzat;
        this.zzbbt = str;
    }

    public final void run() {
        this.zzbbr.zzdk(this.zzbbt);
    }
}
