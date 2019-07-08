package com.google.android.gms.measurement.internal;

final class zzbc implements Runnable {
    private final /* synthetic */ boolean zzamy;
    private final /* synthetic */ zzbb zzamz;

    zzbc(zzbb zzbb, boolean z) {
        this.zzamz = zzbb;
        this.zzamy = z;
    }

    public final void run() {
        this.zzamz.zzamx.zzm(this.zzamy);
    }
}
