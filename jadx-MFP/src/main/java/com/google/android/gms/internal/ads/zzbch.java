package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.Executor;

final class zzbch implements Executor {
    private final Handler zzeps = new zzaya(Looper.getMainLooper());

    zzbch() {
    }

    public final void execute(Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            try {
                runnable.run();
            } catch (Throwable th) {
                zzbv.zzlf();
                zzayh.zza(zzbv.zzlj().getApplicationContext(), th);
                throw th;
            }
        } else {
            this.zzeps.post(runnable);
        }
    }
}
