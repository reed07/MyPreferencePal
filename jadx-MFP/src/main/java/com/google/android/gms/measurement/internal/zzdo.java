package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzdo implements Runnable {
    private final /* synthetic */ AtomicReference zzarg;
    private final /* synthetic */ zzda zzarh;

    zzdo(zzda zzda, AtomicReference atomicReference) {
        this.zzarh = zzda;
        this.zzarg = atomicReference;
    }

    public final void run() {
        synchronized (this.zzarg) {
            try {
                this.zzarg.set(Integer.valueOf(this.zzarh.zzgv().zzb(this.zzarh.zzgk().zzal(), zzai.zzake)));
                this.zzarg.notify();
            } catch (Throwable th) {
                this.zzarg.notify();
                throw th;
            }
        }
    }
}
