package com.google.android.gms.internal.measurement;

final class zzam implements Runnable {
    private final /* synthetic */ int zzvr;
    private final /* synthetic */ zzal zzvs;

    zzam(zzal zzal, int i) {
        this.zzvs = zzal;
        this.zzvr = i;
    }

    public final void run() {
        this.zzvs.zzvq.zzg(((long) this.zzvr) * 1000);
    }
}
