package com.google.android.gms.internal.ads;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

final class zzdm implements ThreadFactory {
    private final ThreadFactory zztg = Executors.defaultThreadFactory();

    zzdm() {
    }

    public final Thread newThread(Runnable runnable) {
        Thread newThread = this.zztg.newThread(runnable);
        newThread.setName(String.valueOf(newThread.getName()).concat(":"));
        return newThread;
    }
}
