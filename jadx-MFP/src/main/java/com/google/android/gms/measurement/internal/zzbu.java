package com.google.android.gms.measurement.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

final class zzbu<V> extends FutureTask<V> implements Comparable<zzbu> {
    private final String zzapd;
    private final /* synthetic */ zzbr zzape;
    private final long zzapf = zzbr.zzapc.getAndIncrement();
    final boolean zzapg;

    zzbu(zzbr zzbr, Callable<V> callable, boolean z, String str) {
        this.zzape = zzbr;
        super(callable);
        Preconditions.checkNotNull(str);
        this.zzapd = str;
        this.zzapg = z;
        if (this.zzapf == Long.MAX_VALUE) {
            zzbr.zzgt().zzjg().zzby("Tasks index overflow");
        }
    }

    zzbu(zzbr zzbr, Runnable runnable, boolean z, String str) {
        this.zzape = zzbr;
        super(runnable, null);
        Preconditions.checkNotNull(str);
        this.zzapd = str;
        this.zzapg = false;
        if (this.zzapf == Long.MAX_VALUE) {
            zzbr.zzgt().zzjg().zzby("Tasks index overflow");
        }
    }

    /* access modifiers changed from: protected */
    public final void setException(Throwable th) {
        this.zzape.zzgt().zzjg().zzg(this.zzapd, th);
        if (th instanceof zzbs) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }

    public final /* synthetic */ int compareTo(@NonNull Object obj) {
        zzbu zzbu = (zzbu) obj;
        boolean z = this.zzapg;
        if (z != zzbu.zzapg) {
            return z ? -1 : 1;
        }
        long j = this.zzapf;
        long j2 = zzbu.zzapf;
        if (j < j2) {
            return -1;
        }
        if (j > j2) {
            return 1;
        }
        this.zzape.zzgt().zzjh().zzg("Two tasks share the same index. index", Long.valueOf(this.zzapf));
        return 0;
    }
}
