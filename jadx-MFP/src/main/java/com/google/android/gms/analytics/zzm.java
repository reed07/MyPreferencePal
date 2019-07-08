package com.google.android.gms.analytics;

import android.util.Log;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.FutureTask;

final class zzm extends FutureTask<T> {
    private final /* synthetic */ zza zzsu;

    zzm(zza zza, Runnable runnable, Object obj) {
        this.zzsu = zza;
        super(runnable, obj);
    }

    /* access modifiers changed from: protected */
    public final void setException(Throwable th) {
        UncaughtExceptionHandler zzb = zzk.this.zzsr;
        if (zzb != null) {
            zzb.uncaughtException(Thread.currentThread(), th);
        } else if (Log.isLoggable("GAv4", 6)) {
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 37);
            sb.append("MeasurementExecutor: job failed with ");
            sb.append(valueOf);
            Log.e("GAv4", sb.toString());
        }
        super.setException(th);
    }
}
