package com.google.android.gms.internal.ads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzawy implements ThreadFactory {
    private final AtomicInteger zzehf = new AtomicInteger(1);

    zzawy(zzaww zzaww) {
    }

    public final Thread newThread(Runnable runnable) {
        int andIncrement = this.zzehf.getAndIncrement();
        StringBuilder sb = new StringBuilder(42);
        sb.append("AdWorker(SCION_TASK_EXECUTOR) #");
        sb.append(andIncrement);
        return new Thread(runnable, sb.toString());
    }
}
