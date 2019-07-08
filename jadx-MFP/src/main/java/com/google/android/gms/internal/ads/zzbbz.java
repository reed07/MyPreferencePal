package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@zzark
final class zzbbz<T> implements zzbcb<T> {
    private final Throwable cause;
    private final zzbcc zzepj = new zzbcc();

    zzbbz(Throwable th) {
        this.cause = th;
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

    public final T get() throws ExecutionException {
        throw new ExecutionException(this.cause);
    }

    public final T get(long j, TimeUnit timeUnit) throws ExecutionException {
        throw new ExecutionException(this.cause);
    }

    public final void zza(Runnable runnable, Executor executor) {
        this.zzepj.zza(runnable, executor);
    }
}
