package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: IMASDK */
public class ak {
    private final BlockingQueue<Runnable> a = new LinkedBlockingQueue();
    private final ThreadPoolExecutor b;
    private final ArrayDeque<aj> c = new ArrayDeque<>();
    private aj d = null;

    public void a() {
        this.d = null;
        b();
    }

    public ak() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, this.a);
        this.b = threadPoolExecutor;
    }

    public void a(aj ajVar) {
        ajVar.a(this);
        this.c.add(ajVar);
        if (this.d == null) {
            b();
        }
    }

    private void b() {
        this.d = (aj) this.c.poll();
        aj ajVar = this.d;
        if (ajVar != null) {
            ajVar.executeOnExecutor(this.b, new Object[0]);
        }
    }
}
