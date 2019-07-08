package com.google.firebase.iid;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class zzi {
    private static final Executor zzad = zzk.zzaf;

    static Executor zze() {
        return zzad;
    }

    static Executor zzf() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(), zzj.zzae);
        return threadPoolExecutor;
    }

    static final /* synthetic */ Thread zza(Runnable runnable) {
        return new Thread(runnable, "firebase-iid-executor");
    }
}
