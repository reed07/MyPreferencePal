package com.google.android.gms.internal.ads;

final class zzgm implements Runnable {
    private final /* synthetic */ zzhn zzabj;
    private final /* synthetic */ zzgl zzabk;

    zzgm(zzgl zzgl, zzhn zzhn) {
        this.zzabk = zzgl;
        this.zzabj = zzhn;
    }

    public final void run() {
        this.zzabk.zzabi.zza(this.zzabj);
    }
}
