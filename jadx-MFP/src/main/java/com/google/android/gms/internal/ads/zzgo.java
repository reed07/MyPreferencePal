package com.google.android.gms.internal.ads;

final class zzgo implements Runnable {
    private final /* synthetic */ zzgl zzabk;
    private final /* synthetic */ zzfs zzabo;

    zzgo(zzgl zzgl, zzfs zzfs) {
        this.zzabk = zzgl;
        this.zzabo = zzfs;
    }

    public final void run() {
        this.zzabk.zzabi.zzc(this.zzabo);
    }
}
