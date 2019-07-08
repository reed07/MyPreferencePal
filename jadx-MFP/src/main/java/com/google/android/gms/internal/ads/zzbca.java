package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@zzark
final class zzbca<T> implements zzbcb<T> {
    private final T value;
    private final zzbcc zzepj = new zzbcc();

    zzbca(T t) {
        this.value = t;
        this.zzepj.zzaaw();
    }

    public final boolean cancel(boolean z) {
        return false;
    }

    public final boolean isCancelled() {
        return false;
    }

    public final boolean isDone() {
        return true;
    }

    public final T get() {
        return this.value;
    }

    public final T get(long j, TimeUnit timeUnit) {
        return this.value;
    }

    public final void zza(Runnable runnable, Executor executor) {
        this.zzepj.zza(runnable, executor);
    }
}
