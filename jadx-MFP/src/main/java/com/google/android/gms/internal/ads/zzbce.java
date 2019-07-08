package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import javax.annotation.Nullable;

@zzark
final class zzbce<V> extends FutureTask<V> implements zzbcb<V> {
    private final zzbcc zzepj = new zzbcc();

    zzbce(Callable<V> callable) {
        super(callable);
    }

    zzbce(Runnable runnable, @Nullable V v) {
        super(runnable, v);
    }

    public final void zza(Runnable runnable, Executor executor) {
        this.zzepj.zza(runnable, executor);
    }

    /* access modifiers changed from: protected */
    public final void done() {
        this.zzepj.zzaaw();
    }
}
