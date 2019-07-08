package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzdl implements Runnable {
    private final /* synthetic */ AtomicReference zzarg;
    private final /* synthetic */ zzda zzarh;

    zzdl(zzda zzda, AtomicReference atomicReference) {
        this.zzarh = zzda;
        this.zzarg = atomicReference;
    }

    public final void run() {
        synchronized (this.zzarg) {
            try {
                this.zzarg.set(this.zzarh.zzgv().zzay(this.zzarh.zzgk().zzal()));
                this.zzarg.notify();
            } catch (Throwable th) {
                this.zzarg.notify();
                throw th;
            }
        }
    }
}
