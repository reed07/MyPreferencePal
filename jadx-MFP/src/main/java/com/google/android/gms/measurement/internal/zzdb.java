package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzdb implements Runnable {
    private final /* synthetic */ AtomicReference zzarg;
    private final /* synthetic */ zzda zzarh;

    zzdb(zzda zzda, AtomicReference atomicReference) {
        this.zzarh = zzda;
        this.zzarg = atomicReference;
    }

    public final void run() {
        synchronized (this.zzarg) {
            try {
                this.zzarg.set(Boolean.valueOf(this.zzarh.zzgv().zzax(this.zzarh.zzgk().zzal())));
                this.zzarg.notify();
            } catch (Throwable th) {
                this.zzarg.notify();
                throw th;
            }
        }
    }
}
