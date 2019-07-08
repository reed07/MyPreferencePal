package com.google.android.gms.internal.ads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzayg implements ThreadFactory {
    private final AtomicInteger zzehf = new AtomicInteger(1);
    private final /* synthetic */ String zzelb;

    zzayg(String str) {
        this.zzelb = str;
    }

    public final Thread newThread(Runnable runnable) {
        String str = this.zzelb;
        int andIncrement = this.zzehf.getAndIncrement();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 23);
        sb.append("AdWorker(");
        sb.append(str);
        sb.append(") #");
        sb.append(andIncrement);
        return new Thread(runnable, sb.toString());
    }
}
