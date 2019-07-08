package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@zzark
@ParametersAreNonnullByDefault
public class zzbcl<T> implements zzbcb<T> {
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private T mValue;
    @GuardedBy("mLock")
    private boolean zzdnk;
    @GuardedBy("mLock")
    private Throwable zzepv;
    @GuardedBy("mLock")
    private boolean zzepw;
    private final zzbcc zzepx = new zzbcc();

    public final void zza(Runnable runnable, Executor executor) {
        this.zzepx.zza(runnable, executor);
    }

    public final void set(@Nullable T t) {
        synchronized (this.mLock) {
            if (!this.zzdnk) {
                if (zzaay()) {
                    zzbv.zzlj().zzb(new IllegalStateException("Provided SettableFuture with multiple values."), "SettableFuture.set");
                    return;
                }
                this.zzepw = true;
                this.mValue = t;
                this.mLock.notifyAll();
                this.zzepx.zzaaw();
            }
        }
    }

    public final void setException(Throwable th) {
        synchronized (this.mLock) {
            if (!this.zzdnk) {
                if (zzaay()) {
                    zzbv.zzlj().zzb(new IllegalStateException("Provided SettableFuture with multiple values."), "SettableFuture.setException");
                    return;
                }
                this.zzepv = th;
                this.mLock.notifyAll();
                this.zzepx.zzaaw();
            }
        }
    }

    public T get() throws CancellationException, ExecutionException, InterruptedException {
        T t;
        synchronized (this.mLock) {
            if (!zzaay()) {
                try {
                    this.mLock.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            if (this.zzepv != null) {
                throw new ExecutionException(this.zzepv);
            } else if (!this.zzdnk) {
                t = this.mValue;
            } else {
                throw new CancellationException("SettableFuture was cancelled.");
            }
        }
        return t;
    }

    public T get(long j, TimeUnit timeUnit) throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        T t;
        synchronized (this.mLock) {
            if (!zzaay()) {
                try {
                    long millis = timeUnit.toMillis(j);
                    if (millis != 0) {
                        this.mLock.wait(millis);
                    }
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            if (this.zzepv != null) {
                throw new ExecutionException(this.zzepv);
            } else if (!this.zzepw) {
                throw new TimeoutException("SettableFuture timed out.");
            } else if (!this.zzdnk) {
                t = this.mValue;
            } else {
                throw new CancellationException("SettableFuture was cancelled.");
            }
        }
        return t;
    }

    public boolean cancel(boolean z) {
        if (!z) {
            return false;
        }
        synchronized (this.mLock) {
            if (zzaay()) {
                return false;
            }
            this.zzdnk = true;
            this.zzepw = true;
            this.mLock.notifyAll();
            this.zzepx.zzaaw();
            return true;
        }
    }

    public boolean isCancelled() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdnk;
        }
        return z;
    }

    public boolean isDone() {
        boolean zzaay;
        synchronized (this.mLock) {
            zzaay = zzaay();
        }
        return zzaay;
    }

    @GuardedBy("mLock")
    private final boolean zzaay() {
        return this.zzepv != null || this.zzepw;
    }
}
