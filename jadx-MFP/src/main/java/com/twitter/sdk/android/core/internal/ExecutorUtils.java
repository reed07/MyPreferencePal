package com.twitter.sdk.android.core.internal;

import com.twitter.sdk.android.core.Twitter;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class ExecutorUtils {
    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int MAXIMUM_POOL_SIZE;

    static {
        int i = CPU_COUNT;
        CORE_POOL_SIZE = i + 1;
        MAXIMUM_POOL_SIZE = (i * 2) + 1;
    }

    private ExecutorUtils() {
    }

    public static ExecutorService buildThreadPoolExecutorService(String str) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(), getNamedThreadFactory(str));
        addDelayedShutdownHook(str, threadPoolExecutor);
        return threadPoolExecutor;
    }

    public static ScheduledExecutorService buildSingleThreadScheduledExecutorService(String str) {
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(getNamedThreadFactory(str));
        addDelayedShutdownHook(str, newSingleThreadScheduledExecutor);
        return newSingleThreadScheduledExecutor;
    }

    static ThreadFactory getNamedThreadFactory(final String str) {
        final AtomicLong atomicLong = new AtomicLong(1);
        return new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                Thread newThread = Executors.defaultThreadFactory().newThread(runnable);
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(atomicLong.getAndIncrement());
                newThread.setName(sb.toString());
                return newThread;
            }
        };
    }

    static void addDelayedShutdownHook(String str, ExecutorService executorService) {
        addDelayedShutdownHook(str, executorService, 1, TimeUnit.SECONDS);
    }

    static void addDelayedShutdownHook(String str, ExecutorService executorService, long j, TimeUnit timeUnit) {
        Runtime runtime = Runtime.getRuntime();
        final ExecutorService executorService2 = executorService;
        final long j2 = j;
        final TimeUnit timeUnit2 = timeUnit;
        final String str2 = str;
        AnonymousClass2 r2 = new Runnable() {
            public void run() {
                try {
                    executorService2.shutdown();
                    if (!executorService2.awaitTermination(j2, timeUnit2)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str2);
                        sb.append(" did not shutdown in the allocated time. Requesting immediate shutdown.");
                        Twitter.getLogger().d("Twitter", sb.toString());
                        executorService2.shutdownNow();
                    }
                } catch (InterruptedException unused) {
                    Twitter.getLogger().d("Twitter", String.format(Locale.US, "Interrupted while waiting for %s to shut down. Requesting immediate shutdown.", new Object[]{str2}));
                    executorService2.shutdownNow();
                }
            }
        };
        StringBuilder sb = new StringBuilder();
        sb.append("Twitter Shutdown Hook for ");
        sb.append(str);
        runtime.addShutdownHook(new Thread(r2, sb.toString()));
    }
}
