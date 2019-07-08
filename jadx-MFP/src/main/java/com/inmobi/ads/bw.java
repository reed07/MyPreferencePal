package com.inmobi.ads;

import android.support.annotation.NonNull;
import com.inmobi.a.n;
import com.inmobi.commons.core.network.c;
import com.inmobi.commons.core.network.d;
import com.myfitnesspal.shared.constants.HttpConstants;
import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: VastNetworkClient */
final class bw {
    public static final Executor d;
    /* access modifiers changed from: private */
    public static final String e = "bw";
    private static final int g = Runtime.getRuntime().availableProcessors();
    private static final int h = Math.max(2, Math.min(g - 1, 4));
    private static final int i = ((g * 2) + 1);
    private static final ThreadFactory j = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public final Thread newThread(@NonNull Runnable runnable) {
            StringBuilder sb = new StringBuilder("VastNetworkTask #");
            sb.append(this.a.getAndIncrement());
            return new Thread(runnable, sb.toString());
        }
    };
    private static final BlockingQueue<Runnable> k = new LinkedBlockingQueue(128);
    /* access modifiers changed from: 0000 */
    public c a;
    WeakReference<bv> b;
    long c = 0;
    private final CountDownLatch f;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(h, i, 30, TimeUnit.SECONDS, k, j);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        d = threadPoolExecutor;
    }

    public bw(bv bvVar, int i2, CountDownLatch countDownLatch) {
        this.a = new c(HttpConstants.METHOD_GET, bvVar.a);
        c cVar = this.a;
        cVar.r = i2;
        cVar.A = false;
        this.b = new WeakReference<>(bvVar);
        this.f = countDownLatch;
    }

    /* access modifiers changed from: 0000 */
    public final void a() {
        CountDownLatch countDownLatch = this.f;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public final void a(d dVar) {
        new StringBuilder("Vast Media Header Request fetch failed:").append(dVar.b.b);
        try {
            n.a().a(this.a.g());
            n.a().b(dVar.c());
        } catch (Exception e2) {
            new StringBuilder("Handling Vast Media Header Request fetch failed encountered an unexpected error: ").append(e2.getMessage());
        } finally {
            a();
        }
    }
}
