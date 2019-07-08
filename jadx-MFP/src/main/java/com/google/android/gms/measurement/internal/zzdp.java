package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzdp implements Runnable {
    private final /* synthetic */ AtomicReference zzarg;
    private final /* synthetic */ zzda zzarh;

    zzdp(zzda zzda, AtomicReference atomicReference) {
        this.zzarh = zzda;
        this.zzarg = atomicReference;
    }

    public final void run() {
        synchronized (this.zzarg) {
            try {
                this.zzarg.set(Double.valueOf(this.zzarh.zzgv().zzc(this.zzarh.zzgk().zzal(), zzai.zzakf)));
                this.zzarg.notify();
            } catch (Throwable th) {
                this.zzarg.notify();
                throw th;
            }
        }
    }
}
