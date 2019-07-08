package com.google.android.gms.internal.ads;

final class zzarh implements Runnable {
    private final /* synthetic */ zzbbh zzdvn;
    private final /* synthetic */ String zzdvo;

    zzarh(zzare zzare, zzbbh zzbbh, String str) {
        this.zzdvn = zzbbh;
        this.zzdvo = str;
    }

    public final void run() {
        this.zzdvn.zzed(this.zzdvo);
    }
}
