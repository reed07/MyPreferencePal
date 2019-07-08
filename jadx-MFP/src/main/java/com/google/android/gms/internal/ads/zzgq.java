package com.google.android.gms.internal.ads;

final class zzgq implements Runnable {
    private final /* synthetic */ zzgl zzabk;
    private final /* synthetic */ zzhn zzabs;

    zzgq(zzgl zzgl, zzhn zzhn) {
        this.zzabk = zzgl;
        this.zzabs = zzhn;
    }

    public final void run() {
        this.zzabs.zzds();
        this.zzabk.zzabi.zzb(this.zzabs);
    }
}
