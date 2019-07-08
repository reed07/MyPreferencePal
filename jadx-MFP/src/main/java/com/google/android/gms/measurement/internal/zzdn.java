package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzdn implements Runnable {
    private final /* synthetic */ AtomicReference zzarg;
    private final /* synthetic */ zzda zzarh;

    zzdn(zzda zzda, AtomicReference atomicReference) {
        this.zzarh = zzda;
        this.zzarg = atomicReference;
    }

    public final void run() {
        synchronized (this.zzarg) {
            try {
                this.zzarg.set(Long.valueOf(this.zzarh.zzgv().zza(this.zzarh.zzgk().zzal(), zzai.zzakd)));
                this.zzarg.notify();
            } catch (Throwable th) {
                this.zzarg.notify();
                throw th;
            }
        }
    }
}
