package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzde implements Runnable {
    private final /* synthetic */ boolean zzaeg;
    private final /* synthetic */ AtomicReference zzarg;
    private final /* synthetic */ zzda zzarh;

    zzde(zzda zzda, AtomicReference atomicReference, boolean z) {
        this.zzarh = zzda;
        this.zzarg = atomicReference;
        this.zzaeg = z;
    }

    public final void run() {
        this.zzarh.zzgl().zza(this.zzarg, this.zzaeg);
    }
}
