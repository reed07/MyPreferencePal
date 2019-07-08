package com.facebook.ads.internal.w.b;

import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class n implements ThreadFactory {
    protected final AtomicLong a = new AtomicLong();
    private int b = Thread.currentThread().getPriority();

    /* access modifiers changed from: protected */
    public String a() {
        return String.format(Locale.US, "com.facebook.ads thread-%d %tF %<tT", new Object[]{Long.valueOf(this.a.incrementAndGet()), Long.valueOf(System.currentTimeMillis())});
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(null, runnable, a(), 0);
        thread.setPriority(this.b);
        return thread;
    }
}
