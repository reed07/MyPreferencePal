package com.google.android.gms.internal.ads;

final class zzgr implements Runnable {
    private final /* synthetic */ zzgl zzabk;
    private final /* synthetic */ int zzabt;

    zzgr(zzgl zzgl, int i) {
        this.zzabk = zzgl;
        this.zzabt = i;
    }

    public final void run() {
        this.zzabk.zzabi.zzl(this.zzabt);
    }
}
