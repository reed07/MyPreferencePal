package com.uacf.taskrunner;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class Invoker {
    private static final String TAG = "Invoker";
    /* access modifiers changed from: private */
    public AtomicInteger counter = new AtomicInteger();
    private boolean destroyed;
    private ExecutorService executor;
    /* access modifiers changed from: private */
    public ExecutorType executorType = ExecutorType.Cached;
    private Handler handler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public String name;
    /* access modifiers changed from: private */
    public int threadCount = 3;

    public static class Builder<T extends Builder> {
        private Invoker instance = new Invoker();

        public Builder(String str) {
            if (str == null || str.trim().length() == 0) {
                throw new IllegalArgumentException("tag must not be null");
            }
            this.instance.name = str;
            this.instance.threadCount = 3;
        }

        public T setUseCachedExecutor() {
            this.instance.threadCount = 3;
            this.instance.executorType = ExecutorType.Cached;
            return this;
        }

        public Invoker build() {
            return this.instance;
        }
    }

    private enum ExecutorType {
        FixedSize,
        Cached
    }

    public enum InvokeMode {
        Auto,
        Async,
        Post,
        Sync
    }

    public Future<?> async(Runnable runnable) {
        throwIfDestroyed();
        return safeGetExecutor().submit(runnable);
    }

    public void post(Runnable runnable) {
        throwIfDestroyed();
        this.handler.post(runnable);
    }

    public Handler getHandler() {
        return this.handler;
    }

    public synchronized void shutdown() {
        if (this.executor != null) {
            this.executor.shutdownNow();
            this.executor = null;
        }
        this.destroyed = true;
    }

    private ExecutorService createExecutorService() {
        AnonymousClass2 r7 = new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                StringBuilder sb = new StringBuilder();
                sb.append(Invoker.this.name);
                sb.append("-");
                sb.append(String.valueOf(Invoker.this.counter.incrementAndGet()));
                thread.setName(sb.toString());
                return thread;
            }
        };
        switch (this.executorType) {
            case FixedSize:
                return Executors.newFixedThreadPool(this.threadCount, r7);
            case Cached:
                ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10, TimeUnit.SECONDS, new SynchronousQueue(), r7);
                return threadPoolExecutor;
            default:
                throw new RuntimeException("invalid ExecutorType specified. this should never happen!");
        }
    }

    private synchronized ExecutorService safeGetExecutor() {
        if (this.executor == null) {
            this.executor = createExecutorService();
        }
        return this.executor;
    }

    private synchronized void throwIfDestroyed() {
        if (this.destroyed) {
            StringBuilder sb = new StringBuilder();
            sb.append(TAG);
            sb.append(" ");
            sb.append(this.name);
            sb.append(" already destroyed.");
            throw new RuntimeException(sb.toString());
        }
    }
}
